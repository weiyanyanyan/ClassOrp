<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Dashboard Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link type="text/css" href="../css/dashboard.css" rel="stylesheet">

</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/ClassOrp/login/aboutSystem">Class Orp</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Dashboard</a></li>
                <li><a href="#">FeedBack</a></li>
                <li><a href="/ClassOrp/login/help">Help</a></li>
                <li><a href="/ClassOrp/login/index">Quit</a></li>
            </ul>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
            </form>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">
                <p>
                    PS：The result of teaching evaluation must be confirmed by myself.
                </p>
            </h1>

            <h2 class="sub-header">Answer Sheet</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>编号</th>
                        <th>姓名</th>
                        <th>角色</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userInfoList}" var="data" varStatus="i">
                        <tr>
                            <td>${i.count}</td>
                            <td>${data.userId}</td>
                            <td>${data.userName}</td>
                            <c:if test="${data.role == 1}">
                                <td>学生</td>
                            </c:if>
                            <c:if test="${data.role == 2}">
                                <td>老师</td>
                            </c:if>
                            <c:if test="${data.role == 3}">
                                <td>管理员</td>
                            </c:if>
                            <td>
                                <!-- 按钮触发模态框 -->
                                <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#${i.count}">
                                    编辑
                                </button>
                                <!-- 模态框（Modal） -->
                                <div class="modal fade" id="${i.count}" tabindex="-1" role="dialog"
                                     aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <form action="/ClassOrp/login/personUpdate" method="post"
                                              class="needs-validation">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-hidden="true">
                                                        &times;
                                                    </button>
                                                    <h4 class="modal-title" id="myModalLabel">
                                                        编辑-${data.userName}
                                                    </h4>
                                                </div>
                                                <div class="modal-body">
                                                    <label for="inputId">编号</label>
                                                    <input value="${data.userId}" name="inputId" type="text"
                                                           id="inputId" class="form-control"/>
                                                    <br>
                                                    <label for="inputName">姓名</label>
                                                    <input value="${data.userName}" name="inputName" type="text"
                                                           id="inputName" class="form-control"/>
                                                    <label for="usertype">角色</label>
                                                    <select id="usertype" name="usertype"
                                                            class="selectpicker show-tick form-control" multiple
                                                            data-max-options="1" data-live-search="true">
                                                        <option value="1">Student</option>
                                                        <option value="2">Teacher</option>
                                                        <option value="3">Admin</option>
                                                    </select>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        关闭
                                                    </button>
                                                    <button type="submit" class="btn btn-primary">
                                                        提交
                                                    </button>
                                                </div>
                                            </div><!-- /.modal-content -->
                                        </form>
                                    </div><!-- /.modal -->
                                </div>
                                <!-- 按钮触发模态框 -->
                                <button class="btn btn-primary btn-lg" data-toggle="modal"
                                        data-target="#myModal${i.count}">
                                    删除
                                </button>
                                <!-- 模态框（Modal） -->
                                <div class="modal fade" id="myModal${i.count}" tabindex="-1" role="dialog"
                                     aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <form action="/ClassOrp/login/personDelete" method="post"
                                              class="needs-validation">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-hidden="true">
                                                        &ti
                                                    </button>

                                                    <h4 class="modal-title" id="myModalLabe2">
                                                        删除-${data.userName}
                                                    </h4>
                                                </div>
                                                <div class="modal-body">
                                                    删除后不可恢复!
                                                    <input value="${data.userId}" name="userId" type="hidden"
                                                           id="userId" class="form-control"/>
                                                    <input value="${data.role}" name="role" type="hidden" id="role"
                                                           class="form-control"/>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        关闭
                                                    </button>
                                                    <button type="submit" class="btn btn-primary">
                                                        删除
                                                    </button>
                                                </div>
                                            </div><!-- /.modal-content -->
                                        </form>
                                    </div><!-- /.modal -->
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div align="center">
                    <!-- 按钮触发模态框 -->
                    <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal0000">
                        新增
                    </button>
                    <br>
                    <label style="color:red;">${msg}</label>
                    <!-- 模态框（Modal） -->
                    <div class="modal fade" id="myModal0000" tabindex="-1" role="dialog"
                         aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <form action="/ClassOrp/login/personAdd" method="post" class="needs-validation">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title" id="myModalLabe3">
                                            新增
                                        </h4>
                                    </div>
                                    <div class="modal-body">
                                        <label for="inputIdAdd" style="float: left">编号</label>
                                        <input name="inputIdAdd" type="text" id="inputIdAdd" class="form-control"/>
                                        <br>
                                        <label for="inputNameAdd" style="float: left">姓名</label>
                                        <input name="inputNameAdd" type="text" id="inputNameAdd" class="form-control"/>
                                        <label for="inputWordAdd" style="float: left">密码</label>
                                        <input name="inputWordAdd" type="text" id="inputWordAdd" class="form-control"/>
                                        <label for="usertypeAdd" style="float: left">角色</label>
                                        <select id="usertypeAdd" name="usertypeAdd"
                                                class="selectpicker show-tick form-control" multiple
                                                data-max-options="1" data-live-search="true">
                                            <option value="1">Student</option>
                                            <option value="2">Teacher</option>
                                            <option value="3">Admin</option>
                                        </select>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">
                                            关闭
                                        </button>
                                        <button type="submit" class="btn btn-primary">
                                            新增
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </form>
                        </div><!-- /.modal -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
