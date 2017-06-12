<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
    <link href="../css/bootstrap-theme.min.css" rel="stylesheet">
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
                            //登陆失败
                            alert("用户名或密码错误");
                        }
                    },"json");
            }
        }

    </script>
</head>
<body>
<%--导航栏-------------------------------%>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="//localhost:8080/main/index">京东优选</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="//localhost:8080/main/index">主页</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        类别 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">家用电器</a></li>
                        <li><a href="#">手机/运营商/数码</a></li>
                        <li><a href="#">电脑/办公</a></li>
                        <li><a href="#">家居/家具/家装/厨具</a></li>
                        <li><a href="#">男装/女装/童装/内衣</a></li>
                        <li><a href="#">美妆个护/宠物</a></li>
                        <li><a href="#">女鞋/箱包/钟表/珠宝</a></li>
                        <li><a href="#">男鞋/运动/户外</a></li>
                        <li><a href="#">汽车/汽车用品</a></li>
                        <li><a href="#">母婴/玩具乐器</a></li>
                        <li><a href="#">食品/酒类/生鲜/特产</a></li>
                        <li><a href="#">礼品鲜花/农资绿植</a></li>
                        <li><a href="#">医药保健/计生情趣</a></li>
                        <li><a href="#">图书/音像/电子书</a></li>
                        <li><a href="#">机票/酒店/旅游/生活</a></li>
                        <li><a href="#">理财/众筹/白条/保险</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="//localhost:8080/user/login"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
            <li><a href="//localhost:8080/user/reg"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
        </ul>
    </div>
</nav>
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
