package com.yjx.service;


import com.yjx.base.BaseService;
import com.yjx.dao.RoleMapper;
import com.yjx.dao.UserMapper;
import com.yjx.model.UserModel;
import com.yjx.utils.AssertUtil;
import com.yjx.utils.Md5Util;
import com.yjx.utils.UserIDBase64;
import com.yjx.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.yjx.utils.LoginUserUtil;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class UserService extends BaseService<User, Integer> {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    //用户登录的业务逻辑
    public UserModel userlogin(String userName,String password){
        //参数检验 自定义方法
        checkLoginParams(userName,password);
        //调用mapper层方法
        User user=userMapper.queryUserByUserName(userName);
        //用户存在校验
        AssertUtil.isTrue(null==user,"用户名不存在");
        //密码校验(核心安全)
        //第一个password表示的是输入的密码
        //第二个password表示的是数据库中的密码
        checkUserPwd(password,user.getUserPwd());
        //登录成功 构建我们的返回数据
        //返回给我们的前端的数据只有我们的账户数据  不包含密码
        return buildUserInfo(user);
    }
    //构建登录成功后的返回用户信息
    //密码不能返回给前端
    private UserModel buildUserInfo(User user) {
        //创建返回对象
        UserModel userModel=new UserModel();
        //userId加密 登录的关键
        //加密算法  Base64 + 时间戳混淆，字符串反转
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        //用户名和真实姓名可以返回给前端 没安全问题就返回
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        //查询角色名称
        String roleIds = user.getRoleIds();
        if (StringUtils.isNotBlank(roleIds)) {
            String roleName = roleMapper.queryRoleNamesByRoleIds(roleIds);
            userModel.setRoleName(roleName);
        }
        return userModel;
    }



    //密码参数校验
    /**
     * 为什么要单独拆分出来一个方法进行实现呢？
     * 1.代码复用性 添加用户也要验证密码
     * 2.可读性 让主体逻辑更加清晰
     * 3.单一性 添加用户时 密码校验逻辑和登录逻辑重复了
     *
     * **/
    private void checkUserPwd(String password, String userPwd) {
        //旧密码不能为空
//        AssertUtil.isTrue(StringUtils.isBlank(password),"密码不能为空");
        //密码加密
        //123456 --> '4QrcOUm6Wau+VuBX8g+IPg=='
        //增加了一个安全性
        password= Md5Util.encode(password);
        //进行判断
        AssertUtil.isTrue(!password.equals(userPwd),"密码错误");
    }

    //登录参数检验方法
    private void checkLoginParams(String userName, String password) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(password),"密码不能为空");
    }

    public void saveUser(User user) {
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()), "用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getTrueName()), "真实姓名不能为空");
        AssertUtil.isTrue(null != userMapper.queryUserByUserName(user.getUserName()), "用户名已存在");
        user.setUserPwd(Md5Util.encode("123456"));
        user.setIsValid(1);
        user.setCreateDate(new Date());
        AssertUtil.isTrue(insertSelective(user) < 1, "添加用户失败");
    }

    public void updateUser(User user) {
        AssertUtil.isTrue(user.getId() == null, "待更新记录不存在");
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()), "用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getTrueName()), "真实姓名不能为空");
        AssertUtil.isTrue(updateByPrimaryKeySelective(user) < 1, "更新用户失败");
    }

    public void deleteUser(Integer[] ids) {
        AssertUtil.isTrue(ids == null || ids.length == 0, "请选择待删除记录");
        AssertUtil.isTrue(deleteBatch(ids) < 1, "删除失败");
    }

    public void updatePassword(HttpServletRequest request, String oldPassword,
                                String newPassword, String confirmPassword) {
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword), "旧密码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(newPassword), "新密码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(confirmPassword), "确认密码不能为空");
        AssertUtil.isTrue(!newPassword.equals(confirmPassword), "两次密码输入不一致");

        int userId = LoginUserUtil.releaseUserIdFromCookie(request);
        AssertUtil.isTrue(userId == 0, "用户未登录");

        User user = selectByPrimaryKey(userId);
        AssertUtil.isTrue(null == user, "用户不存在");

        checkUserPwd(oldPassword, user.getUserPwd());

        User updateUser = new User();
        updateUser.setId(userId);
        updateUser.setUserPwd(Md5Util.encode(newPassword));
        AssertUtil.isTrue(updateByPrimaryKeySelective(updateUser) < 1, "密码修改失败");
    }

}
