layui.use(['element', 'layer', 'layuimini','jquery','jquery_cookie'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        $ = layui.jquery_cookie($);

    // 菜单初始化
    $('#layuiminiHomeTabIframe').html('<iframe width="100%" height="100%" frameborder="0"  src="welcome"></iframe>')
    layuimini.initTab();


    // 清除所有登录Cookie
    function clearLoginCookies() {
        $.removeCookie("userIdStr",{domain:"localhost",path:"/crm"});
        $.removeCookie("userName",{domain:"localhost",path:"/crm"});
        $.removeCookie("trueName",{domain:"localhost",path:"/crm"});
        $.removeCookie("roleName",{domain:"localhost",path:"/crm"});
    }

    // 退出登录
    $(".login-out").click(function () {
        clearLoginCookies();
        window.parent.location.href=ctx+"/index";
    })

    // 切换账户
    $(".switch-account").click(function () {
        clearLoginCookies();
        window.parent.location.href=ctx+"/index";
    })





});

