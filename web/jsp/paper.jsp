<%--
  Created by IntelliJ IDEA.
  User: llin
  Date: 16/1/13
  Time: 上午3:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>论文详情</title>

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
<c:if test="${!empty success}">
    <script> window.alert('${success}')</script>
</c:if>
<c:if test="${!empty comment}">
    <script> window.alert('${comment}')</script>
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
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">${paper.title}</h1>
                <button type="button" class="btn btn-primary" style="float: right;" onclick="download('${paper.pid}','${paper.score}')">下载论文( 需要积分 ${paper.score} )</button>
            </div>
        </div>
        <!-- /.row -->
        <div class="row">
            <!-- /.col-lg-4 -->
            <div class="col-lg-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        文章描述
                    </div>
                    <div class="panel-body">
                        <p class="lead">${paper.description}</p>
                        <h4>关键字:</h4>
                        <p class="text-left">${paper.keyword}</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel-footer">
            <div class="input-group">
                <form method="post" action="/servlet/makeComment?pid=${paper.pid}">
                    <input id="btn-input" type="text" class="form-control input-sm" placeholder="发表评论(已下载的用户可发表)" name="content"/>
                                <span class="input-group-btn">
                                    <input class="btn btn-warning btn-sm" id="btn-chat" type="submit">
                                        发表
                                    </input>
                                </span>
                </form>
            </div>
        </div>
        <div class="panel-body">
            <ul class="chat">
                <c:forEach items="${commentList}" var="comment">
                    <li class="left clearfix">
                                    <span class="chat-img pull-left">
                                        <img src="http://placehold.it/50/55C1E7/fff" alt="User Avatar" class="img-circle" />
                                    </span>
                        <div class="chat-body clearfix">
                            <div class="header">
                                <strong class="primary-font">${comment.uname}</strong>
                                <small class="pull-right text-muted">
                                    <i class="fa fa-clock-o fa-fw"></i> ${comment.curtime}
                                </small>
                            </div>
                            <p>
                                ${comment.content}
                            </p>
                        </div>
                    </li>
                </c:forEach>
            </ul>
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

</body>
<script>
    function download(pid,score) {//下载按钮的监听器
        if (confirm('下载该论文需要消耗 '+score+' 积分,确认下载?')) {
            window.open("http://localhost:8080/servlet/downloadPaper?pid=" + pid);
            window.location = "http://localhost:8080/servlet/showPaper?pid="+pid;
        }
    }
</script>
<script>
    function logout() {//注销按钮的监听器
        window.location.href = "http://localhost:8080/servlet/logout";
    }
</script>

</html>

