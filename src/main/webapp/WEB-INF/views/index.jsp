<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String scheme = request.getScheme();
    String serverName = request.getServerName();
    String contextPath = request.getContextPath();
    int port = request.getServerPort();

    //网站的访问跟路径
    String baseURL = scheme + "://" + serverName + ":" + port
            + contextPath;
    request.setAttribute("baseURL", baseURL);
    System.out.println("baseURL:" + baseURL);
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Signin ClassOrp for School</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="https://v3.bootcss.com/examples/signin/signin.css" rel="stylesheet">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body style="background-image: url(../images/index.jpg);background-repeat: no-repeat;background-size: cover;">
<div class="container">
    <form class="form-signin" action="/ClassOrp/login/checks" method="post">
        <h3 class="form-signin-heading" align="center">Please sign in ClassOrp</h3>
        <label for="inputName" class="sr-only">User Name</label>
        <input onchange="msgClose()" name="user_id" type="text" id="inputName" class="form-control" placeholder="User Name" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <br>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <label style="color: #B40404;" id="msg">${msg}</label>
        <button class="btn btn-lg btn-primary btn-block" type="submit" id="login">Sign in</button>
    </form>
</div>
</body>
<script>
    function msgClose() {
        var msg=document.getElementById("msg");
        msg.style.display="none";
    }
</script>
</html>
