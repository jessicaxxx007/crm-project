package com.yjx.exceptions;

/**
 * 未登录异常
 *
 * 什么时候抛这个异常？
 *   ① 用户没登录，直接访问需要登录的接口
 *   ② Cookie过期了或被人删了
 *   ③ Cookie被篡改，解密失败
 *   ④ 拦截器检测到用户未登录
 *
 * 异常会被谁捕获？
 *   GlobalExceptionResolver全局异常处理器
 *   捕获后：return new ModelAndView("redirect:/index") → 重定向到登录页
 *
 * 为什么继承RuntimeException而不是Exception？
 *   ① RuntimeException是unchecked异常，不需要显式try-catch或throws
 *      如果继承Exception（checked），调用者必须处理，否则编译器报错
 *   ② 业务逻辑异常通常用RuntimeException，更灵活
 *   ③ Spring默认对RuntimeException进行事务回滚
 *
 * 为什么有多个构造函数？
 *   为了满足不同的创建场景：
 *   ① 无参：new NoLoginException() → 使用默认code=300, msg="用户未登录!"
 *   ② 只传msg：new NoLoginException("请先登录") → 自定义消息
 *   ③ 只传code：new NoLoginException(500) → 自定义状态码
 *   ④ 两者都传：new NoLoginException(500, "请先登录") → 自定义所有
 */
public class NoLoginException extends RuntimeException {

    /**
     * 错误状态码
     * 默认300，表示"未登录"
     * 其他模块可以根据需要覆盖
     */
    private Integer code = 300;

    /**
     * 错误消息
     * 默认"用户未登录!"，用户体验更好
     */
    private String msg = "用户未登录!";

    /**
     * 无参构造函数
     * 使用默认的code和msg
     * 场景：new NoLoginException() → 抛默认的"用户未登录!"
     */
    public NoLoginException() {
        super("用户未登录!");
    }

    /**
     * 只传消息的构造函数
     * @param msg 自定义错误消息
     * 场景：new NoLoginException("请先登录再操作")
     */
    public NoLoginException(String msg) {
        super(msg);
        this.msg = msg;
    }

    /**
     * 只传状态码的构造函数
     * @param code 自定义错误状态码
     * 场景：new NoLoginException(401) → HTTP 401 Unauthorized
     */
    public NoLoginException(Integer code) {
        super("用户未登录!");
        this.code = code;
    }

    /**
     * 传状态码和消息的构造函数
     * @param code 自定义错误状态码
     * @param msg 自定义错误消息
     * 场景：new NoLoginException(401, "Token已过期，请重新登录")
     */
    public NoLoginException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
