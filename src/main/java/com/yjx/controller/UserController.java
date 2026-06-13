package com.yjx.controller;

import com.yjx.base.BaseController;
import com.yjx.base.ResultInfo;
import com.yjx.model.UserModel;
import com.yjx.query.UserQuery;
import com.yjx.service.SaleChanceService;
import com.yjx.service.UserService;
import com.yjx.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;
    @Resource
    private SaleChanceService saleChanceService;

    @PostMapping("login")
    @ResponseBody
    public ResultInfo userlogin(String userName, String userPwd) {
        ResultInfo resultInfo = new ResultInfo();
        UserModel userModel = userService.userlogin(userName, userPwd);
        resultInfo.setResult(userModel);
        return resultInfo;
    }

    @RequestMapping("index")
    public String index() {
        return "user/user";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(UserQuery query) {
        return userService.queryByParamsForTable(query);
    }

    @PostMapping("save")
    @ResponseBody
    public ResultInfo save(User user) {
        userService.saveUser(user);
        return success();
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(User user) {
        userService.updateUser(user);
        return success();
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer[] ids) {
        userService.deleteUser(ids);
        return success();
    }

    @RequestMapping("addOrUpdateUserPage")
    public String addOrUpdatePage(Integer id, Model model) {
        if (id != null) {
            model.addAttribute("user", userService.selectByPrimaryKey(id));
        }
        return "user/add_update";
    }

    @GetMapping("toPasswordPage")
    public String toPasswordPage() {
        return "user/password";
    }

    @GetMapping("toSettingPage")
    public String toSettingPage() {
        return "user/password";
    }

    @PostMapping("updatePassword")
    @ResponseBody
    public ResultInfo updatePassword(HttpServletRequest request, String oldPassword,
                                      String newPassword, String confirmPassword) {
        userService.updatePassword(request, oldPassword, newPassword, confirmPassword);
        return success();
    }

    @PostMapping("queryAllSales")
    @ResponseBody
    public List<Map<String, Object>> queryAllSales() {
        return saleChanceService.queryAllSales();
    }
}
