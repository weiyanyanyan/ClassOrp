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
                <li role="presentation">
                    <div class="dropdown">
                        <button type="button" class="btn dropdown-toggle" id="dropdownMenu3" data-toggle="dropdown"
                                style="color: #585858 !important;">
                            Evaluation of teaching results
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1"
                            style="min-width:100%;color:#FC3 !important; background:#fff !important;">
                            <li role="presentation">
                                <a role="menuitem" tabindex="-1" href="/ClassOrp/login/teacherEvaluationRes?"
                                   style="color: #428bca !important;">Teacher</a>
                            </li>
                        </ul>
                    </div>
                </li>
                <li role="presentation">
                    <div class="dropdown">
                        <button type="button" class="btn dropdown-toggle" id="dropdownMenu2" data-toggle="dropdown"
                                style="color: #585858 !important;">
                            personnel management
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1"
                            style="min-width:100%;color:#FC3 !important; background:#fff !important;">
                            <li role="presentation">
                                <a role="menuitem" tabindex="-1" href="/ClassOrp/login/person?role=3"
                                   style="color: #428bca !important;">Admin</a>
                            </li>
                            <li role="presentation">
                                <a role="menuitem" tabindex="-1" href="/ClassOrp/login/person?role=2"
                                   style="color: #428bca !important;">Teacher</a>
                            </li>
                            <li role="presentation" class="divider"></li>
                            <li role="presentation">
                                <a role="menuitem" tabindex="-1" href="/ClassOrp/login/person?role=1"
                                   style="color: #428bca !important;">Student</a>
                            </li>
                        </ul>
                    </div>
                </li>
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
        <h2>问卷命题规则</h2>
        <p class="lead" style="font-size: medium" align="left"><b>1、能正确反映调查目的，具体问题，突出重点，能使被访者乐意合作，协助达到调查目的。<br>
            2、结构合理、逻辑性强。问题的排列应有一定的逻辑顺序，符合应答者的思维程序。一般是先易后难、先简后繁、先具体后抽象。<br>
            3、通俗易懂。问卷应使应答者一目了然，并愿意如实回答。问卷中语气要亲切，符合应答者的理解能力和认识能力，避免使用专业术语。对敏感性问题采取一定的技巧调查，使问卷具有合理性和可答性，避免主观性和暗示性，以免答案失真。<br>
            4、控制问卷的长度。回答问卷的时间控制在20分钟左右，问卷中既不浪费一个问句，也不遗漏一个问句。<br>
            5、便于资料的校验、整理和统计</b></p>
        <p><a class="btn btn-lg btn-success" href="/ClassOrp/login/createQuestionProblem?userId=${userLoginInfo.userId}"
              role="button">创建问卷</a></p>
    </div>

    <div class="row marketing">
        <div class="col-lg-6">
            <c:forEach items="${questionInfoOne}" var="data">
                <a href="/ClassOrp/login/seeQuestionProblem?question_id=${data.questionId}&start_time=${data.startTime}&end_time=${data.endTime}">
                    <h4>${data.questionName}</h4>
                </a>
                <p>开放日期：${data.startTime}至${data.endTime}</p>
            </c:forEach>
        </div>

        <div class="col-lg-6">
            <c:forEach items="${questionInfoTwo}" var="data">
                <a href="/ClassOrp/login/seeQuestionProblem?question_id=${data.questionId}&start_time=${data.startTime}&end_time=${data.endTime}">
                    <h4>${data.questionName}</h4>
                </a>
                <p>开放日期：${data.startTime} 至 ${data.endTime}</p>
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
</script>
</body>
</html>