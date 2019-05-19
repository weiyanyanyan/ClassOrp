<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
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
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
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
                    PS：The total score of each question is 10 points, including excellent, good, general and poor scores of 4, 3, 2 and 1 points respectively.
                </p>
            </h1>

            <!-- <div class="row placeholders">
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>Label</h4>
                    <span class="text-muted">Something else</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>Label</h4>
                    <span class="text-muted">Something else</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>Label</h4>
                    <span class="text-muted">Something else</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>Label</h4>
                    <span class="text-muted">Something else</span>
                </div>
            </div>
            -->

            <h2 class="sub-header">Answer Sheet For ${teacherId}:${classId}:${questionId}:${userId}</h2>
            <div class="table-responsive">
                <form class="form-signin" action="/ClassOrp/login/submitEvaluation" method="post">

                  <input type="hidden" name="teacher_id"  value="${teacherId}" >
                    <input type="hidden" name="class_id"  value="${classId}" >
                    <input type="hidden" name="question_id"  value="${questionId}" >
                    <input type="hidden" name="user_id"  value="${userId}" >

                    <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>题目</th>
                        <th>评分</th>
                        <th>建议</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${questionDesList}" var="data" varStatus="i" >
                    <tr>
                            <td>${i.count}</td>
                            <td>${data}</td>
                            <td>
                                <label>
                                    <input type="radio" name="${i.count}" id="${i}" value="4" checked> 优秀
                                </label>
                                <label>
                                    <input type="radio" name="${i.count}" id="${i}" value="3" checked> 良好
                                </label>
                                <label>
                                    <input type="radio" name="${i.count}" id="${i}" value="2" checked> 一般
                                </label>
                                <label>
                                    <input type="radio" name="${i.count}" id="${i}" value="1" checked> 较差
                                </label>
                            </td>
                            <td>
                                <input class="form-control" id="focusedInput${i}" type="text" value="请输入建议！">
                            </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                    <div align="center">
                    <button type="submit" class="btn btn-lg btn-success">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
