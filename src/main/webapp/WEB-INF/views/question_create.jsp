<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Questionnaire filling</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link type="text/css" href="../css/jumbotron-narrow.css" rel="stylesheet">

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

    <style>
        .note:before{
            color: red;
            content: "*";
            position: absolute;
            margin-left: -15px;
        }
    </style>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <script src="https://cdn.bootcss.com/moment.js/2.22.0/moment-with-locales.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
</head>

<body class="bg-light">
<div class="container">
    <div class="row marketing">

        <div class="col-md-8 order-md-1">
                <h2 class="sub-header">Questionnaire filling</h2>
                <form class="needs-validation" action="/ClassOrp/login/createQuestionProblemAdmin" method="post" novalidate>
                    <input type="hidden" name="userId"  value="${userId}" >
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="datetimepickerStartTime" class="note">有效起始时间</label>
                            <div class='input-group date' id='datetimepickerStartTime'>
                                <input type='text' class="form-control " name="datetimepickerStartTime"/>
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="datetimepickerEndTime" class="note">有效终止时间</label>
                            <div class='input-group date' id='datetimepickerEndTime'>
                                <input type='text' class="form-control" name="datetimepickerEndTime"/>
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="questionId" class="note">Question Id</label>
                        <input type="text" class="form-control" id="questionId" placeholder="Question Id" name="questionId">
                    </div>
                    <div class="mb-3">
                        <label for="questionName" class="note">Question Name</label>
                        <input type="text" class="form-control" id="questionName" placeholder="Question Name" name="questionName">
                    </div>
                    <div class="mb-3">
                        <label for="questionOne" class="note">Question 1</label>
                            <input type="text" class="form-control" id="questionOne" placeholder="Question 1" name="questionOne">
                    </div>

                    <div class="mb-3">
                        <label for="questionTwo" class="note">Question 2</label>
                            <input type="text" class="form-control" id="questionTwo" placeholder="Question 2" name="questionTwo">
                    </div>

                    <div class="mb-3">
                        <label for="questionThree" class="note">Question 3</label>
                            <input type="text" class="form-control" id="questionThree" placeholder="Question 3" name="questionThree">
                    </div>

                    <div class="mb-3">
                        <label for="questionFour" class="note">Question 4 </label>
                            <input type="text" class="form-control" id="questionFour" placeholder="Question 4" name="questionFour">
                    </div>
                    <div class="mb-3">
                        <label for="questionFive" class="note">Question 5 </label>
                            <input type="text" class="form-control" id="questionFive" placeholder="Question 5" name="questionFive">
                    </div>
                    <div class="mb-3">
                        <label for="questionSix" class="note">Question 6 </label>
                            <input type="text" class="form-control" id="questionSix" placeholder="Question 6" name="questionSix">
                    </div>
                    <div class="mb-3">
                        <label for="questionSeven" class="note">Question 7 </label>
                            <input type="text" class="form-control" id="questionSeven" placeholder="Question 7" name="questionSeven">
                    </div>
                    <div class="mb-3">
                        <label for="questionEight" class="note">Question 8 </label>
                            <input type="text" class="form-control" id="questionEight" placeholder="Question 8" name="questionEight">
                    </div>
                    <div class="mb-3">
                        <label for="questionNine" class="note">Question 9 </label>
                            <input type="text" class="form-control" id="questionNine" placeholder="Question 9" name="questionNine">
                    </div>
                    <div class="mb-3">
                        <label for="questionTen" class="note">Question 10 </label>
                            <input type="text" class="form-control" id="questionTen" placeholder="Question 10" name="questionTen">
                    </div>
                    <br>
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Submit</button>
                </form>
            </div>
        </div>

    <footer class="footer">
        <p>&copy; 2019 Company, W&M.</p>
    </footer>
</div>
<script type="text/javascript">
    $(function () {
        var picker1 = $('#datetimepickerStartTime').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-cn'),
            Kind:dtkTime
        });
        var picker2 = $('#datetimepickerEndTime').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-cn'),
            DataTime:Now()
        });
        //动态设置最小值
        picker1.on('dp.change', function (e) {
            picker2.data('DateTimePicker').minDate(e.date);
        });
        //动态设置最大值
        picker2.on('dp.change', function (e) {
            picker1.data('DateTimePicker').maxDate(e.date);
        });
    });
    $('#datetimepickerStartTime').datetimepicker({
        format: 'YYYY-MM-DD',
        locale: moment.locale('zh-cn')
//        defaultDate: "2019-05-01 00:00:00"
    });
    $('#datetimepickerEndTime').datetimepicker({
        format: 'YYYY-MM-DD',
        locale: moment.locale('zh-cn')
//        defaultDate: "2019-05-01 23:59:59"
    });
</script>
</body>
</html>
