<%--
  Created by IntelliJ IDEA.
  User: llin
  Date: 16/1/13
  Time: 上午1:44
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

    <title>论文列表</title>

    <!-- Bootstrap Core CSS -->
    <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="../bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

</head>
<c:if test="${empty user}">
    <script>window.location.href = "localhost:8080/jsp/login.jsp"</script>
</c:if>
<!--如果session中的当前用户为空，那么跳转回登陆界面-->
<c:if test="${!empty add}">
    <script>window.alert('${add}')</script>
</c:if>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <!--创建随页面一起滚动的导航栏-->
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
                        <a href="/servlet/paperList"><i class="fa fa-dashboard fa-fw"></i> 全部论文</a>
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
                <h1 class="page-header">论文列表</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        查看论文
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                <div class="dataTable_wrapper">
                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                        <thead><!--定义表头-->
                        <tr>
                            <th>题目</th>
                            <th>关键字</th>
                            <th>描述</th>
                            <th>积分</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${paperList.data}" var="paper">
                            <tr class="odd gradeX">
                                <td><a onclick="showPaper('${paper.pid}')">${paper.title}</a></td>
                                <td>${paper.keyword}</td>
                                <td>${paper.description}</td>
                                <td class="center" id="score">${paper.score}</td>
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
    <script>
        function d(pid,score) {//点击下载的按钮的事件监听
            if(confirm('下载该论文需要消耗 '+score+' 积分,确认下载?')) {
                window.open("http://localhost:8080/servlet/downloadPaper?pid=" + pid);
                window.location.href = "http://localhost:8080/servlet/paperList";
            }
        }
        function showPaper(pid) {//展示单个论文的信息
            window.location.href = "http://localhost:8080/servlet/showPaper?pid=" + pid;
        }
    </script>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="../bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- DataTables JavaScript -->
    <script src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
        <!--window加载之前加载或触发-->
        $(document).ready(function() {
            $('#dataTables-example').DataTable({
                responsive: true
            });
        });
    </script>
</div>
<script>
    function logout() {//注销操作的事件监听，返回登陆界面
        window.location.href = "http://localhost:8080/servlet/logout";
    }
</script>
</body>

</html>

