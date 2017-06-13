<%@ page import="com.price.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String hasLogin = (String)session.getAttribute("hasLogin");
    User user = (User) session.getAttribute("userInfo");
%>
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
            <%
                if(hasLogin == null) {
            %>
            <li><a href="//localhost:8080/user/login"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
            <li><a href="//localhost:8080/user/reg"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
            <%
            } else {
            %>
            <li><a href="//localhost:8080/user/homepage"><span class="glyphicon glyphicon-user"></span> <%=user.getUsername()%></a></li>
            <li><a href="//localhost:8080/user/signOut"><span class="glyphicon glyphicon-log-out"></span> 注销</a></li>
            <%
                }
            %>
        </ul>
    </div>
</nav>
<%--导航栏-------------------------------%>