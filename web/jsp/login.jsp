<%--
  Created by IntelliJ IDEA.
  User: llin
  Date: 16/1/13
  Time: 下午6:28
  To change this template use File | Settings | File Templates.
--%>
<%--利用page命令定义JSP页面的属性--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--利用taglib定义使用JSTL时的前缀prefix和识别JSTL的字符串uri--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>论文管理系统登录</title>

    <!-- Bootstrap Core CSS -->
    <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">登录</h3>
                </div>
                <div class="panel-body">
                    <!--role是bootstrap框架的规范(给读屏软件使用),提交表单方式为post,id为myform-->
                    <form role="form" method="post" id="myform">
                        <fieldset>
                            <c:if test="${!empty success}">
                                <h5 style="color:red;">${success}</h5>
                            </c:if>
                            <div class="form-group">
                                用户名:
                                <input class="form-control" placeholder="请输入用户名" name="uname" type="email" autofocus>
                            </div>
                            <div class="form-group">
                                密码:
                                <input class="form-control" placeholder="请输入密码" name="pwd" type="password" value="">
                            </div>
                            <div class="form-group">
                                <div class="radio-inline">
                                    <label>
                                        <input type="radio" name="type" id="optionsRadios1" value="0" checked>用户
                                    </label>
                                </div>
                                <div class="radio-inline">
                                    <label>
                                        <input type="radio" name="type" id="optionsRadios2" value="1">管理员
                                    </label>
                                </div>
                            </div>
                            <p>
                                <input class="btn btn-success btn-sm" onclick="login()" value="登录"/>
                                <input class="btn btn-warning btn-sm" onclick="addUser()" value="注册"/>
                            </p>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="../bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="../dist/js/sb-admin-2.js"></script>

<script>
    function login() {
        var form = document.getElementById("myform");
        form.action = "/servlet/login";
        form.submit();
    }
    function addUser() {
        var form = document.getElementById("myform");
        form.action = "/servlet/addUser";
        form.submit();
    }
</script>

</body>

</html>

