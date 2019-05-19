<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- 引入 ECharts 文件 -->
    <script src="../js/echarts.min.js"></script>
    <script src="../js/jquery.min.js"></script>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link type="text/css" href="../css/jumbotron-narrow.css" rel="stylesheet">
    <title>Line Of Echart</title>
</head>
<body>
<div class="container">
    <div class="header clearfix">

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
    </div>
    <div class="jumbotron" style="width: 800px;height: auto">
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 ">
            <div id="main" style="width: 700px;height:500px;margin-left:-150px;"></div>
        </div>
        <footer class="footer">
            <p>&copy; 2019 Company, W&M.</p>
        </footer>
    </div>
</div>
</body>
<script type="text/javascript">

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '${className}'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['分值','分值']
        },
        toolbox: {
            show : true,
            feature : {
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis: {
            data: [
                <c:forEach var="i" begin="0" end="${list.size()-1}"> '${list[i].studentId}', </c:forEach>

            ],
        },
        yAxis: {},
        series: [
            {   //系列，这个数组可以存放多个对象，每个对象就是一组同质的数据，如销量、收入、支出。
                name: '分值',   //当前这组数据的名称
                type: 'bar',   //当前这组数据以什么样的形式展现。bar：条形图；line：折线图；pie：饼图，除此之外还有散点图、雷达图等等很多
                data: [<c:forEach var="i" begin="0" end="${list.size()-1}"> '${list[i].score}', </c:forEach>]
            }, {
                name: '分值',
                type: 'line',
                data: [<c:forEach var="i" begin="0" end="${list.size()-1}"> '${list[i].score}', </c:forEach>]
            }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</html>