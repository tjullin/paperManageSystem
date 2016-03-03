<%--
  Created by IntelliJ IDEA.
  User: imac
  Date: 16/1/14
  Time: 下午4:44
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

    <title>下载排行</title>

    <!-- Bootstrap Core CSS -->
    <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../bower_components/bootstrap/dist/css/bootstrap.datepicker.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet">


    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

</head>
<c:if test="${empty user}">
    <script>window.location.href = "localhost:8080/jsp/login.jsp"</script>
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
                <h1 class="page-header">下载排行</h1>
            </div>
            <div class="input-daterange input-group" id="datepicker" style="float: right;">
                <form action="/servlet/analysisData" method="post">
                    开始时间: <input type="text" class="datepicker" name="startDate"/>
                    结束时间: <input type="text" class="datepicker" name="endDate" />
                    <input type="submit" class="btn btn-primary" value="筛选">
                </form>
            </div>
            <!-- /.col-lg-12 -->
        </div><br>
        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        用户获得积分排行
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>用户名</th>
                                    <th>获得积分</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${userList}" var="user">
                                    <tr>
                                        <td>${user.user.uname}</td>
                                        <td>${user.score}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        论文下载次数排行
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>论文标题</th>
                                    <th>下载次数</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${paperList}" var="paper">
                                    <tr>
                                        <td>${paper.paper.title}</td>
                                        <td>${paper.downloadCnt}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
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
    <script src="../bower_components/bootstrap/dist/js/bootstrap.datepicker.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- DataTables JavaScript -->
    <script src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
        $(document).ready(function() {
            $('#dataTables-example').DataTable({
                responsive: true
            });
        });
        $('.datepicker').datepicker().on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
    </script>
</div>
</body>
<script>
    function logout() {
        window.location.href = "http://localhost:8080/servlet/logout";
    }
</script>

</html>


