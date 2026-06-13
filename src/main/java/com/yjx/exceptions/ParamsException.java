package com.yjx.exceptions;

/**
 * 参数异常
 *
 * 什么时候抛这个异常？
 *   ① 用户提交的参数为空（userName==null 或 userName.trim().isEmpty()）
 *   ② 参数格式不对（email格式错误、手机号格式错误）
 *   ③ 参数值不在允许范围内（分页page<1 或 page>总页数）
 *   ④ 业务校验失败（开始时间 > 结束时间）
 *
 * 为什么单独定义这个异常？
 *   ① 区分"参数错误"和"系统错误"
 *   ② GlobalExceptionResolver可以根据异常类型决定：
 *      - 参数异常 → 提示用户"参数不对，请检查"
 *      - 系统异常 → 提示用户"系统繁忙，请稍后再试"
 *
 * 与AuthException的区别：
 *   ParamsException = 参数有问题，用户需要修改参数重试
 *   AuthException = 权限有问题，用户需要联系管理员
 *
 * 为什么继承RuntimeException？
 *   不需要显式处理，调用方可以更灵活地选择是否捕获
 */
public class ParamsException extends RuntimeException {

    /**
     * 错误状态码
     * 默认300，表示"参数错误"
     * 可以被覆盖：new ParamsException(400, "邮箱格式错误")
     */
    private Integer code = 300;

    /**
     * 错误消息
     * 要友好且具体，让用户知道哪里错了
     * 好：new ParamsException("用户名不能为空")
     * 不好：new ParamsException("参数错误")
     */
    private String msg = "参数异常!";

    /**
     * 无参构造函数
     * 使用默认消息，代码更简洁
     */
    public ParamsException() {
        super("参数异常!");
    }

    /**
     * 只传消息的构造函数
     * 推荐使用这种方式，消息更具体
     * 场景：AssertUtil.isTrue(null == userName, "用户名不能为空")
     */
    public ParamsException(String msg) {
        super(msg);
        this.msg = msg;
    }

    /**
     * 只传状态码的构造函数
     * 场景：new ParamsException(400) → 想返回400状态码
     */
    public ParamsException(Integer code) {
        super("参数异常!");
        this.code = code;
    }

    /**
     * 传状态码和消息的构造函数
     * 最灵活，可以同时指定状态码和消息
     */
    public ParamsException(Integer code, String msg) {
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
