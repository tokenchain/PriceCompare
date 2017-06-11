<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
    <script type="text/javascript" src="../jslib/jquery-3.2.0.js"></script>
    <script type="text/javascript" src="../jslib/bootstrap.js"></script>
    <link href="../css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
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
        <div class="col-md-8">
            <h3>登录</h3>
        </div>
    </div>
    <div class="row-fluid">
        <div class="col-md-12">
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <div class="col-md-2"></div>
                    <label class="col-md-2 control-label" for="inputEmail">Email</label>
                    <div class="col-md-4">
                        <input id="inputEmail" type="text" class="form-control" />
                    </div>
                    <div class="col-md-4"></div>
                </div>
                <div class="form-group">
                    <div class="col-md-2"></div>
                    <label class="col-md-2 control-label" for="inputPassword">密码</label>
                    <div class="col-md-4">
                        <input id="inputPassword" type="password"class="form-control"  />
                    </div>
                    <div class="col-md-4"></div>
                </div>
                <div class="form-group">
                    <div class="col-md-2"></div>
                    <div class="col-md-offset-2 col-md-4">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox">请记住我
                            </label>
                        </div>
                    </div>
                    <div class="col-md-4"></div>
                </div>
                <div class="form-group">
                    <div class="col-md-2"></div>
                    <div class="col-sm-offset-2 col-md-4">
                        <button type="submit" class="btn btn-default">登录</button>
                    </div>
                    <div class="col-md-4"></div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
