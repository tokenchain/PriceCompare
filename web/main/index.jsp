<%@ page import="com.price.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>京东优选</title>
    <link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
    <script type="text/javascript" src="../jslib/jquery-3.2.0.js"></script>
    <script type="text/javascript" src="../jslib/bootstrap.js"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script>
        $(function () {
            $("#searchSubmit").click(function () {
                $("#searchForm").submit();
            })
            $("#searchInputBar").keydown(function (event) {
                if(event.keyCode == 13) {
                    $("#searchForm").submit();
                }
            })
        })
    </script>
</head>
<body>
    <%--导航栏-------------------------------%>
    <%@ include file="titleBar.jsp"%>
    <%--导航栏-------------------------------%>
    <%--搜索框-------------------------------%>
    <div class="container-fluid" id="LG">
        <div class="row-fluid">
            <div class="col-md-12" style="text-align: center; padding-top: 20px">
                <h1>京东优选</h1>
            </div>
        </div>
        <div class="row-fluid">
            <div class="col-md-2 col-xs-1"></div>
            <div class="col-md-8 col-xs-10">
                <div style="padding: 80px 100px 10px;">
                    <form id="searchForm" class="bs-example bs-example-form" role="form" action="http://localhost:8080/search/search">
                        <div class="input-group">
                            <input type="text" class="form-control" name="page" value="1" style="display: none">
                            <input type="text" class="form-control" id="searchInputBar" name="keyword" spellcheck="false" autocomplete="off">
                            <span class="input-group-btn">
                                  <button id="searchSubmit" class="btn btn-default" type="button">搜索</button>
                            </span>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-md-2 col-xs-1"></div>
        </div>
    </div>
    <%--搜索框-------------------------------%>
</body>
</html>
