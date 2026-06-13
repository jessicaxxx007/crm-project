package com.yjx.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录用户工具类
 *
 * 职责：
 *   从Cookie中获取当前登录用户的真实ID
 *
 * 为什么从Cookie而不从Session获取？
 *   ① Cookie存在客户端，服务器无状态，更容易扩展
 *   ② Session存在服务器端，占内存，分布式麻烦
 *   ③ 这里把userId加密后存Cookie，比存明文安全
 *
 * 工作流程：
 *   Cookie: userIdStr="#AjMzgjM##QN1AjN4gTOzgjM3UTM"
 *           ↓
 *   LoginUserUtil.releaseUserIdFromCookie()
 *           ↓
 *   UserIDBase64.decoderUserID()
 *           ↓
 *   return userId=1
 */
public class LoginUserUtil {

    /**
     * 从Cookie中获取当前登录用户的ID
     *
     * 为什么用户ID也要加密存储？
     *   ❌ 危险！Cookie里直接存userId=1
     *   ① 用户可以自己修改Cookie，把1改成2，查别人的数据
     *   ② 攻击者可以伪造Cookie，用别人的身份登录
     *
     *   ✅ 安全！Cookie里存加密后的"#AjMzgjM..."
     *   ① 用户不知道真实ID是多少，没法篡改
     *   ② 后端解密后才能知道ID，解密失败就认为没登录
     *
     * @param request HTTP请求对象（从中获取Cookie）
     * @return int 用户真实ID，如果Cookie无效则返回0
     *
     * 返回0的特殊情况：
     *   ① 首次访问，Cookie还没设置
     *   ② Cookie过期了
     *   ③ Cookie被人删了
     *   ④ Cookie被篡改了，解密失败
     *
     * 注意：返回0而不是返回null或抛异常
     *   拦截器会判断：if (userId == 0)，走未登录流程
     *   这样设计更简单，不用处理null
     */
    public static int releaseUserIdFromCookie(HttpServletRequest request) {

        // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        // 第一步：从Cookie中获取加密的userIdStr
        // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        // CookieUtil.getCookieValue做了两件事：
        //   ① 遍历request的所有Cookie，找到name为"userIdStr"的
        //   ② URLDecode解码（因为存Cookie时URLEncoder编码了）
        // 如果找不到这个Cookie，返回null
        String userIdString = CookieUtil.getCookieValue(request, "userIdStr");

        // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        // 第二步：判断Cookie是否为空
        // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        // 如果为空（首次访问或Cookie失效），直接返回0
        // 返回0会被拦截器认为"未登录"
        if (org.apache.commons.lang3.StringUtils.isBlank(userIdString)) {
            return 0;
        }

        // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        // 第三步：解密userIdStr，还原成真实的userId
        // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        // 调用UserIDBase64的解密方法
        //   输入："#AjMzgjM##QN1AjN4gTOzgjM3UTM"
        //   输出：1
        //
        // 如果解密失败（比如Cookie被篡改），返回null
        Integer userId = UserIDBase64.decoderUserID(userIdString);

        if (userId == null) {
            return 0;
        }
        return userId;
    }
}
