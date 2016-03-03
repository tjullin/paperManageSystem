<%--
  Created by IntelliJ IDEA.
  User: llin
  Date: 16/1/13
  Time: 上午1:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>上传论文</title>

    <!-- Bootstrap Core CSS -->
    <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

</head>
<c:if test="${empty user}">
    <script>window.location.href = "localhost:8080/jsp/login.jsp"</script>
</c:if>

<c:if test="${!empty blank}">
        <script>window.alert('${blank}')</script>
</c:if>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <div class="alert alert-info">
                当前用户: ${sessionScope.user.uname} &nbsp;&nbsp; 当前积分: ${sessionScope.user.score}
            </div>
        </div>
        <button class="btn btn-warning" style="position: absolute;top: 20px; right: 30px;" onclick="logout()">注销</button>
        <!-- /.navbar-header -->
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li>
                        <a href="/servlet/paperList"><i class="fa fa-dashboard fa-fw"></i>全部论文</a>
                    </li>
                    <li>
                        <a href="/jsp/uploadPaper.jsp"><i class="fa fa-table fa-fw"></i> 上传论文</a>
                    </li>
                    <li>
                        <a href="/servlet/analysisData"><i class="fa fa-edit fa-fw"></i> 下载排行</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">上传论文</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <c:if test="${success==true}">
            <div class="alert alert-success">
                上传成功!
            </div>
        </c:if>
        <c:if test="${success==false}">
            <div class="alert alert-danger">
                上传论文失败,请重试!
            </div>
        </c:if>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        请填写上传信息
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-6">
                                <form method="post" action="/servlet/addPaper" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label>上传论文</label>
                                        <input type="file" name="file">
                                    </div>
                                    <div class="form-group">
                                        <label>题目</label>
                                        <input class="form-control" placeholder="请输入论文题目" name="title">
                                    </div>
                                    <div class="form-group">
                                        <label>关键词</label>
                                        <input class="form-control" placeholder="请输入关键词,以;隔开" name="keyword">
                                    </div>
                                    <div class="form-group">
                                        <label>描述</label>
                                        <textarea class="form-control" rows="3" name="description"></textarea>
                                    </div>
                                    <div class="form-group input-group">
                                        <input type="text" class="form-control" placeholder="请输入下载所需积分" name="score">
                                        <span class="input-group-addon">积分</span>
                                    </div>
                                    <input type="submit" class="btn btn-default" value="提交"/>
                                    <input type="reset" class="btn btn-default" value="重填"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="../bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="../dist/js/sb-admin-2.js"></script>
<script>
    function logout() {
        window.location.href = "http://localhost:8080/servlet/logout";
    }
</script>
</body>

</html>

