<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <title>ClassOrp Teaching Evaluation for Student</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link type="text/css" href="../css/jumbotron-narrow.css" rel="stylesheet">

</head>

<body>

<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right" id="menu">
                <li role="presentation" class="active"><a href="#">Home</a></li>
                <li role="presentation"><a href="/ClassOrp/login/aboutSystem">About System</a></li>
                <li role="presentation">
                    <div class="dropdown">
                        <button type="button" class="btn dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown"
                                style="color: #585858 !important;">
                            ${userLoginInfo.userName}
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1"
                            style="min-width:100%;color:#FC3 !important; background:#fff !important;">
                            <li role="presentation">
                                <a role="menuitem" tabindex="-1" href="#" style="color: #428bca !important;">Person</a>
                            </li>
                            <li role="presentation" class="divider"></li>
                            <li role="presentation">
                                <a role="menuitem" tabindex="-1" href="/ClassOrp/login/index"
                                   style="color: #428bca !important;">Quit</a>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </nav>
        <h3 class="text-muted">Evaluation Orp</h3>
    </div>

    <div class="jumbotron">
        <h2>问卷说明</h2>
        <p class="lead" style="font-size:medium" align="left"><b>1．依法执教，严守法纪。遵守国家法律法规，无不利于民族团结、社会和谐的言行。<br>
            2．认真批改作业，指导学生毕业论文、毕业设计、课程认真负责，教学工作量达到学校规定的要求；上课认真负责，执行考勤制度，不迟到、不早退，课堂纪律严格，认真解答学生提出的问题。<br>
            3．热爱学生，尊重学生，关心学生。平等公正对待学生。<br>
            4．教书育人，诲人不倦。寓德育于智育中，以良好的思想政治素质引领学生。注意与学生交流思想，关心学生成长。<br>
            5．以身作则，为人师表。模范遵守社会公德，严于律己，生活作风严谨正派；仪表端庄大方，语言规范健康，举止文明礼貌，衣着整洁得体；美其言，慎其行。<br>
            6．严谨治学，潜心钻研。不断改进教育教学方法，并注重将科研成果应用到教学中。</b></p>
        <p><a class="btn btn-lg btn-success" href="javascript:void(0)" role="button" onclick="skip()">${process}</a></p>
    </div>

    <div class="row marketing" id="dataShow">
        <div class="col-lg-6">
            <c:forEach items="${classInfoOne}" var="data">
                <c:if test="${data.score > 0}"><h4>${data.teacherName}</h4></c:if>
                <c:if test="${data.score <= 0}">
                    <a href="/ClassOrp/login/evaluation?teacher_id=${data.teacherId}&question_id=${data.questionId}&class_id=${data.classId}&user_id=${userLoginInfo.userId}">
                        <h4>${data.teacherName}</h4>
                    </a>
                </c:if>
                <p>${data.className} ${data.score}分</p>
            </c:forEach>
        </div>

        <div class="col-lg-6">
            <c:forEach items="${classInfoTwo}" var="data">
                <c:if test="${data.score > 0}"><h4>${data.teacherName}</h4></c:if>
                <c:if test="${data.score <= 0}">
                    <a href="/ClassOrp/login/evaluation?teacher_id=${data.teacherId}&question_id=${data.questionId}&class_id=${data.classId}&user_id=${userLoginInfo.userId}">
                        <h4>${data.teacherName}</h4>
                    </a>
                </c:if>
                <p>${data.className} ${data.score}分</p>
            </c:forEach>
        </div>
    </div>

    <footer class="footer">
        <p>&copy; 2019 Company, W&M.</p>
    </footer>

</div> <!-- /container -->
<script type="text/javascript">
    // 下拉菜单
    var ul = document.getElementById("menu")
    var lis = ul.getElementsByTagName("li")
    for (var i = 0; i < lis.length; i++) {
        lis[i].onmouseover = function () {
            var oUl = this.getElementsByTagName("ul")[0];
            oUl.style.display = "block";
        }
        lis[i].onmouseout = function () {
            var oUl = this.getElementsByTagName("ul")[0];
            oUl.style.display = "none";
        }
    }
    function skip() {
        document.querySelector("#dataShow").scrollIntoView(true);
    }
</script>
</body>
</html>