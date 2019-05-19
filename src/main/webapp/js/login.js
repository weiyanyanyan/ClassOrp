$("#login").click(function () {
    alert('login in');
    $.ajax({
        type: "post",
        url: "checks",
        dataType: "json",    //data传递的是一个json类型的值，而不是字符串，且必须标明dataType的类型，否则会出现400错误或者其他错误。
        data: {
            "username": $("#inputName").val(),
            "password": $("#inputPassword").val()
        },
        success: function (data) {
            if (data.result == "success") {
                window.location.href = 'main.do';
            } else
                alert("密码错误");
        },
        error: function () {
            alert("网络错误");
        }
    });
});