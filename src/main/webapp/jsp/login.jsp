<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>学生公寓管理系统</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/xznstatic/css/bootstrap4.2.1.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/xznstatic/css/style.default.css" id="theme-stylesheet">

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets2/js/jquery.min.js"></script>
</head>

<body>
<div class="page login-page">
    <div class="container d-flex align-items-center">
        <div class="form-holder has-shadow">
            <div class="row">
                <div class="col-lg-6">
                    <div class="info d-flex align-items-center">
                        <div class="content">
                            <div class="logo">
                                <h1>欢迎登录</h1>
                            </div>
                            <p>学生公寓管理系统</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 bg-white">
                    <div class="form d-flex align-items-center">
                        <div class="content">
                            <form id="loginForm" action="" method="post" class="form-validate">
                                <input type="text" required placeholder="用户名" id="username" name="username" class="input-material">
                                <input type="password" name="password" required placeholder="密码" class="input-material">
                                <div style="margin: 10px 0;" id="submitBtn"></div>
                                <button type="submit" class="btn btn-primary" onclick="login()">登录</button>
                            </form>
                            <br />
                            <%--<a href="modules/register/register.jsp" class="signup">注册</a>--%>
                            <a href="modules/yonghu/register.jsp" class="signup">注册</a>
                            <%--<a href="modules/forgetThePassword/forgetThePassword.jsp" class="signup" style="float: right;margin-right: 94px">忘记密码</a>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/resources/xznstatic/js/bootstrap4.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/xznstatic/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/xznstatic/js/front.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>

<script type="text/javascript">

    <%@ include file="utils/menu.jsp"%>

            <%@ include file="utils/baseUrl.jsp"%>

    var role = "";
    var accountTableName = "";
    //渲染角色选择
    function setRoleOption() {
        for (var i = 0; i < menus.length; i++) {
            var divRoot = '<div class="custom-control custom-checkbox "><input type="radio" name="chk" class="custom-control-input" id="check' + i + '" value="' + menus[i].roleName + '" onclick="checkRole(\'' + menus[i].roleName + '\', \'' + menus[i].tableName + '\')"><label class="custom-control-label" for="check' + i + '">' + menus[i].roleName + '</label></div>';
            $('#submitBtn').append(divRoot);
        }
    }
    function checkRole(roleName, tableName) {
        role = roleName;
        $('#loginForm').attr('action', baseUrl + tableName + '/login?role='+role);
        accountTableName = tableName;
    }
    function login() {
        $("#loginForm").ajaxForm(function(res) {
            if (role == "" || role == null) {
                alert("请选择角色后再登录");
            } else {
                if (res.code == 0) {
                    alert("登录成功");
                    var username = $('#username').val();
                    window.sessionStorage.setItem('accountTableName',accountTableName)
                    window.sessionStorage.setItem('username',username);
                    window.sessionStorage.setItem('token', res.token);
                    window.sessionStorage.setItem('role', role);
                    window.location.href = "../index.jsp";
                } else {
                    alert(res.msg);
                }
            }
        });
    }
    function ready() {
        setRoleOption();
    }
    document.addEventListener("DOMContentLoaded", ready);
</script>
</body>

</html>
