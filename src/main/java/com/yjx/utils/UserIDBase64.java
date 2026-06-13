package com.yjx.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Base64;

/**
 * 用户ID加密工具类
 *
 * 为什么用户ID也要加密？
 *   ❌ 危险！直接返回 userId=1
 *   ① 前端知道有userId=1，可以改成2查别人的数据
 *   ② URL里暴露 /main?userId=1，可以被改成2
 *   ③ Cookie里存明文userId，可以被伪造攻击
 *
 *   ✅ 安全！返回加密后的 "#AjMzgjM##QN1AjN4gTOzgjM3UTM"
 *   ① 前端只知道这个乱码，不知道真实ID是几
 *   ② 想改成2？后端解密出来对不上，校验失败
 *
 * 加密算法（简单混淆，非真正安全加密）：
 *   1. userId转Base64 → "MQ=="（就是数字1的Base64）
 *   2. 拼接时间戳的Base64 → 多重混淆
 *   3. 反转字符串 → 更乱
 *   4. 用#替换= → 最终变成 "#AjMzgjM..."
 *
 * 注意：
 *   这是简单的混淆算法，不是真正的加密
 *   防止普通用户篡改ID可以，但防不住专业攻击者
 *   生产环境建议用真正的加密算法如AES
 */
public class UserIDBase64 {

    /**
     * 用户ID解密
     *
     * 加密过程可逆，这里反向操作还原
     *
     * 为什么解密要try-catch？
     *   因为如果被人故意篡改字符串格式，解密会失败
     *   比如：把"abc"改成"xyz"，解密出来是乱码
     *   Integer.parseInt(乱码)会抛异常
     *
     * @param encodedUserID 加密后的用户ID字符串（形如 "#AjMzgjM##QN1AjN4gTOzgjM3UTM"）
     * @return Integer 真实的用户ID（形如 1），如果解密失败返回null
     */
    public static Integer decoderUserID(String encodedUserID) {

        // 如果为空，直接返回null
        if (StringUtils.isBlank(encodedUserID)) {
            return null;
        }

        try {
            // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            // 解密第一步：把#替换回=（加密时用#替换了=）
            // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            // "#AjMzgjM##QN1AjN4gTOzgjM3UTM" → "AjMzgjM==QN1AjN4gTOzgjM3UTM"
            String reversedString = new StringBuffer(encodedUserID).reverse().toString();
            String base64String = reversedString.replaceAll("#", "=");

            // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            // 解密第二步：找到真实Base64的起始位置
            // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            // 时间戳Base64部分结尾有"=="，跳过它往后找真正的userId
            int userIDPos = base64String.indexOf("==") + 6;
            String realBase64UserID = base64String.substring(userIDPos);

            // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            // 解密第三步：Base64解码
            // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            // "MQ==" 解码 → "1"
            String base64Decoded = new String(Base64.getDecoder().decode(realBase64UserID.getBytes()));

            // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            // 解密第四步：转成Integer
            // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            // "1" → 1
            return Integer.parseInt(base64Decoded);

        } catch (Exception e) {
            // 解密失败，返回null
            // 拦截器会认为这是无效登录
            return null;
        }
    }

    /**
     * 用户ID加密
     *
     * 加密过程：
     *   1. userId转Base64 → "MQ=="（就是数字1的Base64）
     *   2. 生成时间戳的Base64 → 多重混淆
     *   3. 拼接两者 → 反转 → 用#替换=
     *
     * @param userID 用户真实ID（形如 1）
     * @return String 加密后的字符串（形如 "#AjMzgjM##QN1AjN4gTOzgjM3UTM"）
     */
    public static String encoderUserID(Integer userID) {

        // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        // 加密第一步：userId转Base64
        // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        // 1 → "MQ=="
        String base64UserIDEncoded = Base64.getEncoder().encodeToString((userID + "").getBytes());

        // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        // 加密第二步：时间戳转Base64，用于混淆
        // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        // System.currentTimeMillis() → "1647830400000" → Base64
        String currentStringBase64Encoded = Base64.getEncoder()
                .encodeToString((System.currentTimeMillis() + "").getBytes());

        // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        // 加密第三步：拼接 + 混淆
        // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        // 格式：时间戳Base64 + 时间戳Base64中间4位 + userId的Base64
        String keyString = currentStringBase64Encoded
                + currentStringBase64Encoded.substring(4, 8)
                + base64UserIDEncoded;

        // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        // 加密第四步：字节反转
        // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        // 让顺序完全乱掉
        byte[] codeBytes = keyString.getBytes();
        byte[] ordedBytes = new byte[codeBytes.length];
        for (int i = 0; i < codeBytes.length; i++) {
            ordedBytes[i] = codeBytes[codeBytes.length - i - 1];
        }

        // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        // 加密第五步：用#替换=，让字符串更安全
        // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        // Base64通常以=结尾，替换掉防止被识别
        return new String(ordedBytes).replaceAll("=", "#");
    }

    /**
     * 测试main方法
     * 演示加密和解密的过程
     */
    public static void main(String[] args) {

        // 加密：1 → "#AjMzgjM##QN1AjN4gTOzgjM3UTM"
        String encoded = encoderUserID(20);
        System.out.println("加密后：" + encoded);

        // 解密："#AjMzgjM##QN1AjN4gTOzgjM3UTM" → 20
        Integer decoded = decoderUserID("#AjMzgjM##QN1AjN4gTOzgjM3UTM");
        System.out.println("解密后：" + decoded);
    }
}
