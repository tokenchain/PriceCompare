<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/formValidation.min.css">
    <script type="text/javascript" src="../jslib/jquery-3.2.0.js"></script>
    <script type="text/javascript" src="../jslib/bootstrap.js"></script>
    <script type="text/javascript" src="../jslib/formValidation.js"></script>
    <script type="text/javascript" src="../jslib/framework/bootstrap.min.js"></script>
    <script>
        //表单验证
        $(document).ready(function() {
            $('#loginForm').formValidation({
                framework: 'bootstrap',
                message: 'This value is not valid',

                fields: {
                    email: {
                        validators: {
                            notEmpty: {
                                message: '请输入邮箱地址'
                            },
                        }
                    },
                    password: {
                        validators: {
                            notEmpty: {
                                message: '请填写密码'
                            },
                        }
                    },
                }
            });

        });

        //表单提交
        function submitForm() {
            // 获取表单
            var formValidation = $("#loginForm").data('formValidation');
            // 重新验证表单
            formValidation.validate();
            if(formValidation.isValid()){
                $.post("//localhost:8080/user/signIn",
                    {email:$("#inputEmail").val(),password:$("#inputPassword").val()},
                    function (data) {
                        if(data == 1) {
                            //登陆成功
                            window.location.href="http://localhost:8080/main/index";
                        } else {
                            if(data == 2) {
                                //账号未激活
                                alert("账号未激活，请激活后登录")
                            }else {
                                //登陆失败
                                alert("用户名或密码错误");
                            }
                        }
                    },"json");
            }
        }

    </script>
</head>
<body>
<%--导航栏-------------------------------%>
<%@ include file="../main/titleBar.jsp"%>
<%--导航栏-------------------------------%>
<div class="container-fluid">
    <%--H3标题-------------------------------%>
    <div class="row-fluid">
        <div class="col-md-4"></div>
        <div class="col-md-4" style="padding: 9px;">
            <h3 style="padding-top: 20px;padding-bottom: 10px;font-size:32px;border-bottom: 1px;border-bottom-width: 1px;
            border-bottom-style: solid;border-bottom-color: rgb(229, 229, 229);">登录</h3>
        </div>
        <div class="col-md-4"></div>
    </div>
    <%--标题-------------------------------%>

        <%--表格-------------------------------%>
        <div class="row-fluid">
            <div class="col-md-12">
                <form class="form-horizontal" role="form" id="loginForm" onkeydown="if(event.keyCode==13){submitForm();return false;}">
                    <%--email-------------------------------%>
                    <div class="form-group">
                        <div class="col-md-2"></div>
                        <label class="col-md-2 control-label" for="inputEmail">Email</label>
                        <div class="col-md-4">
                            <input id="inputEmail" type="text" class="form-control" name="email" autocomplete="off"  spellcheck="false"/>
                        </div>
                        <div class="col-md-4"></div>
                    </div>
                    <%--密码-------------------------------%>
                    <div class="form-group">
                        <div class="col-md-2"></div>
                        <label class="col-md-2 control-label" for="inputPassword">密码</label>
                        <div class="col-md-4">
                            <input id="inputPassword" type="password"class="form-control" name="password" />
                        </div>
                        <div class="col-md-4"></div>
                    </div>
                    <%--注册按钮-------------------------------%>
                    <div class="form-group">
                        <div class="col-md-2"></div>
                        <div class="col-md-offset-2 col-md-4">
                            <button id="register" type="submit" class="btn btn-primary" onclick="submitForm();return false;">登录</button>
                        </div>
                        <div class="col-md-4"></div>
                    </div>
                </form>
            </div>
        </div>
        <%--表格-------------------------------%>
</div>
</body>
</html>
