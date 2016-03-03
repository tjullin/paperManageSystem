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

    <title>用户管理</title>

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

<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <div class="alert alert-info">
                当前用户: ${sessionScope.user.uname} &nbsp;&nbsp; (管理员账户)
            </div>
        </div>
        <button class="btn btn-warning" style="position: absolute;top: 20px; right: 30px;" onclick="logout()">注销</button>
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li>
                        <a href="/servlet/userList"><i class="fa fa-dashboard fa-fw"></i>全部用户</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">用户列表</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        管理用户
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                <tr>
                                    <th>用户ID</th>
                                    <th>用户名</th>
                                    <th>密码</th>
                                    <th>积分</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${userList.data}" var="user">
                                    <tr class="odd gradeX">
                                        <td>${user.uid}</td>
                                        <td>${user.uname}</td>
                                        <td>${user.pwd}</td>
                                        <td class="center">${user.score}</td>
                                        <td class="center">
                                            <p class="fa fa-times" onclick="deleteUser('${user.uid}')"><a onclick="deleteUser('${user.uid}')" class="alert-link">删除</a></p>&nbsp;&nbsp;
                                            <p class="fa fa-edit"><a data-toggle="modal" data-target="#myModal">修改密码</a></p>
                                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                            <h4 class="modal-title" id="myModalLabel">修改密码</h4>
                                                        </div>
                                                        <div class="panel panel-default">
                                                            <div class="panel-body">
                                                                <form action="/servlet/editUser?uid=${user.uid}&score=${user.score}&uname=${user.uname}" method="post" id="myform">
                                                                    <div class="form-group">
                                                                        <label>用户名: ${user.uname}</label>
                                                                    </div><br>
                                                                    <div class="form-group">
                                                                        <label>输入新密码</label>&nbsp;&nbsp;
                                                                        <input class="form-control" placeholder="新密码" name="pwd">
                                                                    </div>
                                                                    <div class="modal-footer">
                                                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                                        <button type="button" class="btn btn-primary" onclick="changePwd()">确认修改</button>
                                                                    </div>
                                                                </form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
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
        function deleteUser(uid) {
            window.location.href = "http://localhost:8080/servlet/delUser?uid=" + uid;
        }
        function changePwd() {
            var form = document.getElementById("myform");
            form.submit();
        }
        function logout() {
            window.location.href = "http://localhost:8080/servlet/logout";
        }
    </script>
    </div>
</body>

</html>

