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
                    PS：The result of teaching evaluation must be confirmed by yourself.
                </p>
            </h1>

            <h2 class="sub-header">Answer Sheet</h2>
            <div class="table-responsive">
                <form class="form-signin" action="/ClassOrp/login/submitEvaluationIdentifyStatus" method="post">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>问卷编号</th>
                            <th>课程号</th>
                            <th>课程名称</th>
                            <!--
                            <th>评分人学号</th>
                            -->
                            <th>分数</th>
                            <th>是否确认</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${questionDesList}" var="data" varStatus="i">
                            <tr>
                                <td>${i.count}</td>
                                <td>${data.questionId}</td>
                                <td>${data.classId}</td>
                                <td>${data.className}</td>
                                <!--
                                <td>${data.userId}</td>
                                -->
                                <td>${data.score}</td>
                                <td>${data.identifyDes}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div align="center">
                        <c:forEach items="${questionDesList}" var="data" varStatus="i" end="0">
                            <c:if test="${data.identifyStatus !='1'}">
                                <a href="/ClassOrp/login/evaluationIdentifyStatus?teacher_id=${data.teacherId}&question_id=${data.questionId}&class_id=${data.classId}&user_id=${data.userId}&identify=1">
                                    <button type="button" class="btn btn-default" id="ok">确认</button>
                                </a>
                            </c:if>
                            <c:if test="${data.identifyStatus =='1'}">
                                <a href="/ClassOrp/login/evaluationIdentifyStatusClose?teacher_id=${data.teacherId}">

                                    <button type="button" class="btn btn-default" id="close">关闭</button>
                                </a>
                            </c:if>
                        </c:forEach>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $("#ok").click(function () {
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '<%=request.getContextPath()%>/User/list',
            dataType: 'json',
            data: 'id=111&str=abc',
            success: function (data) {
                if (data && data.success == "true") {
                    alert("共" + data.total + "条数据。");
                    $.each(data.data, function (i, item) {
                        alert("姓名：" + item.user_name + "，年龄：" + item.user_age
                            + "，性别：" + item.user_sex);
                    });
                }
            },
            error: function () {
                alert("error")
            }
        });
    })
</script>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
