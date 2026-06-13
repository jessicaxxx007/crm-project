package com.yjx.utils;

import java.security.MessageDigest;
import java.util.Base64;

/**
 * MD5密码加密工具类
 *
 * 为什么密码要用MD5加密存储？
 *   ① 防止数据库管理员偷看用户密码（他们也看不到明文）
 *   ② 防止数据库被脱库，攻击者拿到的是密文
 *   ③ 合规要求：等保、GDPR等安全标准都要求密码加密存储
 *
 * MD5的特点：
 *   ① 单向加密：MD5只能明文→密文，不能反向解密
 *   ② 雪崩效应：输入差一个字符，MD5结果完全不一样
 *   ③ 定长输出：16字节，Base64编码后约24字符，好存储
 *   ④ 提速比对：定长32位，好比较
 *
 * MD5的安全问题：
 *   ① 已被认为不够安全，可以被暴力破解
 *   ② 彩虹表攻击：常用密码的MD5已经被穷举了
 *   ③ 生产环境应该用BCrypt或SHA-256，带盐值更安全
 *
 * 这里用MD5是为教学简单，实际项目建议用BCrypt
 */
public class Md5Util {

    /**
     * MD5加密核心方法
     *
     * 加密过程：
     *   明文 → MD5算法 → 16字节摘要 → Base64编码 → 最终字符串
     *
     * 为什么加密后还要Base64编码？
     *   MD5结果是16字节的二进制，里面有不可见字符
     *   直接存储/传输会出问题
     *   Base64编码把二进制转成可见的ASCII字符，好存储好传输
     *
     * @param msg 需要加密的明文字符串
     * @return String MD5+Base64加密后的字符串（形如 "Jkn1yjOr0QjO0zqY3dq0tQ=="）
     */
    public static String encode(String msg) {
        try {
            // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            // 第一步：获取MD5算法实例
            // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            // MessageDigest是Java的摘要算法工具
            // "md5"指定使用MD5算法
            MessageDigest messageDigest = MessageDigest.getInstance("md5");

            // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            // 第二步：执行MD5计算
            // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            // digest()方法接收字符串的字节数组
            // 返回16字节的MD5摘要（原始二进制）
            byte[] digest = messageDigest.digest(msg.getBytes());

            // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            // 第三步：Base64编码
            // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            // 把16字节的二进制，转成24个可见字符
            // 比如：123456 → Jkn1yjOr0QjO0zqY3dq0tQ==
            return Base64.getEncoder().encodeToString(digest);

        } catch (Exception e) {
            // 如果加密失败，打印异常并返回null
            // 正常情况下不会发生，除非JVM没有MD5算法
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 测试main方法
     *
     * 演示：
     *   123456 → Jkn1yjOr0QjO0zqY3dq0tQ==
     *
     * 观察雪崩效应：
     *   123456 和 1234567 只差一个字符
     *   但MD5结果完全不一样
     */
    public static void main(String[] args) {
        // 测试密码123456的MD5值
        System.out.println("123456 的MD5：" + encode("123456"));
    }
}
