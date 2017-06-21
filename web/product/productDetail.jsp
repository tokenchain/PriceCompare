<%@ page import="com.price.model.Product" %>
<%@ page import="com.price.util.CategoryUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
    Product product = (Product)session.getAttribute("productDetail");
    CategoryUtil.Category[] category = (CategoryUtil.Category[])session.getAttribute("productCategoryDetail");
    CategoryUtil.Category thirdClassCategory = category[0];
    CategoryUtil.Category secondClassCategory = category[1];
    CategoryUtil.Category firstClassCategory = category[2];
    boolean hasFollow = (boolean)session.getAttribute("hasFollow");
%>

<html>
<head>
    <title>商品详情</title>
    <link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
    <script type="text/javascript" src="../jslib/jquery-3.2.0.js"></script>
    <script type="text/javascript" src="../jslib/bootstrap.js"></script>
    <script type="text/javascript" src="../jslib/echarts.js"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script>
        $(function () {
            $("#searchSubmit").click(function () {
                $("#searchForm").submit();
            });
            $("#searchInputBar").keydown(function (event) {
                if(event.keyCode == 13) {
                    $("#searchForm").submit();
                }
            })
            $("#follow").click(function () {
                $.post("//localhost:8080/product/follow?productId=<%=product.getId()%>",
                    {cancelFollow : $(this).attr("name")},
                function (data) {
                    switch (data) {
                        case 1:alert("收藏成功");followSwitch(true);break;
                        case 2:alert("收藏失败");break;
                        case 3:alert("取消收藏成功");followSwitch(false);break;
                        case 4:alert("取消收藏失败");break;
                        case 0:
                        default :alert("收藏失败");break;
                    }
                });
            })
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('chart_price'));
            var option = {
                title: {
                    text: '近期价格走势'
                },
                grid: {
                    left: '1%',
                    right: '6%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : []
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'价格趋势',
                        type:'line',
                        stack: '总量',
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        /*areaStyle: {normal: {}},*/
                        data : []
                    }
                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            //myChart.setOption(option);

            $.get("http://localhost:8080/product/historyPrice?getHistoryPriceId=<%=product.getId()%>",
            function (data) {
                var prices = eval(data);
                var xAxis = new Array();
                var priceData = new Array();
                for(var i = 0; i < prices.length; i++) {
                    var price = prices[i].price;
                    var date = prices[i].date.substring(0,10);
                    xAxis[i] = date;
                    priceData[i] = price;
                }
                //alert(xAxis);
                //alert(priceData);
                var option = {
                    title: {
                        text: '近期价格走势'
                    },
                    grid: {
                        left: '1%',
                        right: '6%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis : [
                        {
                            type : 'category',
                            boundaryGap : false,
                            data : xAxis
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            name:'价格趋势',
                            type:'line',
                            stack: '总量',
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top'
                                }
                            },
                            /*areaStyle: {normal: {}},*/
                            data:priceData
                        }
                    ]
                }

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            })
        })

        function followSwitch(data) {
            $("#follow").attr("name", data);
            if(data == true) {
                $("#follow").text("取消收藏");
            } else {
                $("#follow").text("收藏商品");
            }
        }

    </script>
</head>
<body>
<%--导航栏-------------------------------%>
<%@ include file="../main/titleBar.jsp"%>
<%--导航栏-------------------------------%>
<div class="container-fluid" id="LG">
    <%--搜索框--%>
    <div class="row-fluid">
        <div class="col-md-12">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <form id="searchForm" class="bs-example bs-example-form" role="form" action="//localhost:8080/search/search">
                    <div class="input-group">
                        <input type="text" class="form-control" name="page" value="1" style="display: none"/>
                        <input type="text" class="form-control" id="searchInputBar" name="keyword" spellcheck="false" autocomplete="off"/>
                        <span class="input-group-btn">
                                  <button id="searchSubmit" class="btn btn-default" type="button">搜索</button>
                            </span>
                    </div>
                </form>
            </div>
            <div  class="col-md-3"></div>
        </div>
    </div>
    <%--面包屑导航栏--%>
    <div class="row-fluid">
        <div class="col-md-12">
            <ul class="breadcrumb">
                <li>
                    <a href="<%=firstClassCategory.getId()%>"><%=firstClassCategory.getName()%></a>
                </li>
                <li>
                    <a href="<%=secondClassCategory.getId()%>"><%=secondClassCategory.getName()%></a>
                </li>
                <li class="active">
                    <a href="<%=thirdClassCategory.getId()%>"><%=thirdClassCategory.getName()%></a>
                </li>
            </ul>
        </div>
    </div>
    <%--商品详情--%>
    <div class="row-fluid">
        <div class="col-md-12">
            <div class="col-md-1"></div>
            <div class="col-md-4">
                <div class="thumbnail">
                    <img alt="380x380" src="<%=product.getImg_url()%>" />
                </div>
            </div>
            <div class="col-md-7">
                <h4 class="text-left" style="margin-top: 20px;margin-bottom: 20px">
                <%=product.getName()%>
                </h4>

                <dl class="dl-horizontal" style="margin-top: 40px">
                <dt class="text-left" style="width:80px">
                    <p class="text-left">最新价格：</p>
                </dt>
                <dd class="text-left" style="margin-left: 60px">
                    <p class="text-left">
                        <%
                            if (product.getLast_price() > 0) {
                        %>
                        ￥<%=product.getLast_price()%>
                        <%
                            } else {
                        %>
                        暂无报价
                        <%
                            }
                        %>
                    </p>
                </dd>
                <dt class="text-left" style="width:80px">
                    <p class="text-left">历史低价：</p>
                </dt>
                <dd class="text-left" style="margin-left: 60px">
                    <p class="text-left">
                        <%
                            if (product.getLowest_price() > 0) {
                        %>
                        ￥<%=product.getLowest_price()%>
                        <%
                        } else {
                        %>
                        -
                        <%
                            }
                        %>
                    </p>
                </dd>
                <dt class="text-left" style="width:80px">
                    <p class="text-left">价格走势：</p>
                </dt>
                <dd class="text-left" style="margin-left: 60px">
                    <p class="text-left">
                        <%
                            if(product.getPrice_trend() > 2) {
                        %>
                        近期上涨 <span class="glyphicon glyphicon-export"></span>
                        <%
                            } else if (product.getPrice_trend() < -2 && product.getPrice_trend() > -100) {
                        %>
                        近期下跌 <span class="glyphicon glyphicon-import"></span>
                        <%
                            } else {
                        %>
                        近期平稳 <span class="glyphicon glyphicon-minus"></span>
                        <%
                            }
                        %>
                    </p>
                </dd>
                </dl>

                <%--<blockquote style="margin-top: 40px;margin-bottom: 60px">
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.
                    </p> <small>Someone famous <cite>Source Title</cite></small>
                </blockquote>--%>
                <%
                    if(hasFollow) {
                %>
                <button id="follow" type="button" class="btn btn-default" name="true">取消收藏</button>
                <%
                    } else {
                %>
                <button id="follow" type="button" class="btn btn-default" name="false">收藏商品</button>
                <%
                    }
                %>

                <button type="button" class="btn btn-default">查看历史价格</button>

                <div id="chart_price" style="width: 600px;height:300px;margin-top: 10px;margin-bottom: 10px"></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
