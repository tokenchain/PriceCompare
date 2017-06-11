<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/formValidation.min.css">
    <script type="text/javascript" src="../jslib/jquery-3.2.0.js"></script>
    <script type="text/javascript" src="../jslib/bootstrap.js"></script>
    <script type="text/javascript" src="../jslib/formValidation.min.js"></script>
    <script type="text/javascript" src="../jslib/framework/bootstrap.min.js"></script>
    <script>
        //表单验证
        $(document).ready(function() {
            $('#registerForm').formValidation({
                framework: 'bootstrap',
                message: 'This value is not valid',
                /*icon: {
                    //valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },*/

                fields: {
                    username: {
                        validators: {
                            notEmpty: {
                                message: '用户名不能为空'
                            },
                            stringLength: {
                                min: 3,
                                max: 15,
                                message: '用户名长度必须在3到15位之间'
                            },
                            regexp: {
                                regexp: /^[a-zA-Z0-9_\u4E00-\u9FA5]+$/,
                                message: '用户名格式不正确'
                            }
                        }
                    },
                    password: {
                        validators: {
                            notEmpty: {
                                message: '密码不能为空'
                            },
                            stringLength: {
                                min: 3,
                                max: 20,
                                message: '密码长度必须在3到20位之间'
                            },
                            regexp: {
                                regexp: /^[a-zA-Z0-9_]+$/,
                                message: '密码只能包含字母、数字和下划线'
                            },
                        }
                    },
                    passwordRepeat: {
                        validators: {
                            notEmpty: {
                                message: '密码不能为空'
                            },
                            identical: {//相同
                                field: 'password', //需要进行比较的input name值
                                message: '两次密码不一致'
                            },
                        }
                    },
                    email: {
                        validators: {
                            notEmpty: {
                                message: '邮箱不能为空'
                            },
                            emailAddress: {
                                message: '邮件格式错误'
                            }
                        }
                    },
                }
            });
        });
        //表单提交
        $(function () {
            $("#register").click(function () {
                    // 获取表单
                    var formValidation = $("#registerForm").data('formValidation');
                    // 重新验证表单
                    formValidation.validate();
                    if(formValidation.isValid()){
                        //alert("1");
                        $.post("//localhost:8080/user/new",
                            {username:$("#inputUsername").val(),password:$("#inputPassword").val(),passwordRepeat:$("#inputPasswordRepeat").val(),
                                email:$("#inputEmail").val(),verify:$("#captcha").val()},
                            function (data) {
                                alert(data);
                            },"json")
                    }
                })
        })
        //验证码刷新
        $(function () {
            $("#captchaImg").click(function () {
                alert("1");
                $(this).attr("src","//localhost:8080/img/verification1.png");
            })
        })
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
    <div class="row-fluid">
        <div class="col-md-4"></div>
        <div class="col-md-4" style="padding: 9px;">
            <h3 style="padding-top: 20px;padding-bottom: 10px;font-size:32px;border-bottom: 1px;border-bottom-width: 1px;
            border-bottom-style: solid;border-bottom-color: rgb(229, 229, 229);">注册</h3>
        </div>
        <div class="col-md-4"></div>
    </div>
    <div class="row-fluid">
        <div class="col-md-12">
            <form class="form-horizontal" role="form" id="registerForm">
                <div class="form-group">
                    <div class="col-md-2"></div>
                    <label class="col-md-2 control-label" for="inputUsername">用户名</label>
                    <div class="col-md-4">
                        <input id="inputUsername" type="text" class="form-control" name="username" autocomplete="off" />
                    </div>
                    <div class="col-md-4"></div>
                </div>
                <div class="form-group">
                    <div class="col-md-2"></div>
                    <label class="col-md-2 control-label" for="inputPassword">密码</label>
                    <div class="col-md-4">
                        <input id="inputPassword" type="password"class="form-control" name="password" />
                    </div>
                    <div class="col-md-4"></div>
                </div>
                <div class="form-group">
                    <div class="col-md-2"></div>
                    <label class="col-md-2 control-label" for="inputPasswordRepeat">确认密码</label>
                    <div class="col-md-4">
                        <input id="inputPasswordRepeat" type="password"class="form-control" name="passwordRepeat" />
                    </div>
                    <div class="col-md-4"></div>
                </div>
                <div class="form-group">
                    <div class="col-md-2"></div>
                    <label class="col-md-2 control-label" for="inputEmail">Email</label>
                    <div class="col-md-4">
                        <input id="inputEmail" type="text" class="form-control" name="email" autocomplete="off" />
                    </div>
                    <div class="col-md-4"></div>
                </div>
                <div class="form-group">
                    <div class="col-md-2"></div>
                    <label class="col-md-2 control-label" for="captcha">验证码</label>
                    <div class="col-md-3">
                        <input id="captcha" type="text" class="form-control" name="validateCode" autocomplete="off" />
                    </div>
                    <div class="col-md-1"><img id="captchaImg" class="img-rounded" style="display: block;height:30px;max-height:100%;width: auto;max-width:100%" src="//localhost:8080/img/verification.png"></div>
                    <div class="col-md-4"></div>
                </div>
                <div class="form-group">
                    <div class="col-md-2"></div>
                    <div class="col-md-offset-2 col-md-4">
                        <button id="register" type="submit" class="btn btn-primary" onclick="return false">注册</button>
                    </div>
                    <div class="col-md-4"></div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
