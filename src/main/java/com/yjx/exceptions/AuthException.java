package com.yjx.exceptions;

/**
 * 认证异常（权限不足异常）
 *
 * 什么时候抛这个异常？
 *   ① 用户已登录，但操作没有权限
 *   ② 角色权限不够，比如普通员工想删管理员
 *   ③ AOP切面PermissionProxy检测到用户没有方法上注解的权限码
 *
 * 与NoLoginException的区别：
 *   NoLoginException = 用户没登录
 *   AuthException = 用户已登录，但权限不够
 *
 * 异常会被谁捕获？
 *   GlobalExceptionResolver全局异常处理器
 *   捕获后：
 *   ① 如果是JSON请求 → 返回 {code:400, msg:"暂无权限"}
 *   ② 如果是页面请求 → 返回 error.html
 *
 * 为什么继承RuntimeException？
 *   ① 不需要显式处理，更灵活
 *   ② 业务逻辑异常用RuntimeException是标准做法
 */
public class AuthException extends RuntimeException {

    /**
     * 错误状态码
     * 默认400，表示"无权限/禁止访问"
     * 400是HTTP标准的状态码 Forbidden
     */
    private Integer code = 400;

    /**
     * 错误消息
     * 默认"暂无权限"，用户体验友好
     * 不能直接说"你没有权限访问XXX"，会暴露系统信息
     */
    private String msg = "暂无权限";

    /**
     * 无参构造函数
     * 使用默认的code和msg
     */
    public AuthException() {
        super("暂无权限!");
    }

    /**
     * 只传消息的构造函数
     * 为什么消息不能太详细？
     *   防止攻击者通过错误消息推断系统权限配置
     */
    public AuthException(String msg) {
        super(msg);
        this.msg = msg;
    }

    /**
     * 只传状态码的构造函数
     */
    public AuthException(Integer code) {
        super("暂无权限!");
        this.code = code;
    }

    /**
     * 传状态码和消息的构造函数
     */
    public AuthException(Integer code, String msg) {
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
