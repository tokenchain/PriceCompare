<%@ page import="com.price.dto.SearchResultDTO" %>
<%@ page import="com.price.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    int pageNow = 0;
    int pageTotal = 0;
    int keywordCount = 0;
    int productCountThisPage = 0;
    String keyword = "";
    List<Product> products = null;
    SearchResultDTO searchResultDTO = (SearchResultDTO) session.getAttribute("products");
    if(searchResultDTO != null) {
        pageNow = searchResultDTO.getPage();
        pageTotal = searchResultDTO.getTotalPage();
        keywordCount = searchResultDTO.getKeywordCount();
        keyword = searchResultDTO.getKeyword();
        products = searchResultDTO.getProducts();
        productCountThisPage = products.size();
    }
    int row = productCountThisPage / 5;
    int productsLeftCount = productCountThisPage - row * 5;
    /*String ids = "";
    for (Product p : products) {
        ids += p.getId() + ",";
    }
    ids = ids.substring(0, ids.length() - 1);*/
%>
<html>
<head>
    <title><%=keyword%> - 商品搜索 京东优选</title>
    <link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
    <script type="text/javascript" src="../jslib/jquery-3.2.0.js"></script>
    <script type="text/javascript" src="../jslib/bootstrap.js"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../css/searchResult.css">
    <script>
        $(function () {
            /*getPrice();
            getLowestPrice();*/
            $("#searchSubmit").click(function () {
                $("#searchForm").submit();
            })
            $("#searchInputBar").keydown(function (event) {
                if(event.keyCode == 13) {
                    $("#searchForm").submit();
                }
            })
        })

/*
        function getPrice() {
            $.post("//localhost:8080/product/price?skuIds=",
            function (data) {
                var prices = eval(data);
                for(var i = 0; i < prices.length; i++) {
                    var price = prices[i].price;
                    if(price > 0) {
                        $(".price").eq(i).text("￥"+prices[i].price);
                    } else {
                        $(".price").eq(i).text("暂无报价");
                    }
                }
            })
        }

        function getLowestPrice() {
            $.post("//localhost:8080/product/lowestPrice?skuIds=",
                function (data) {
                    var prices = eval(data);
                    for(var i = 0; i < prices.length; i++) {
                        var price = prices[i].price;
                        if(price > 0) {
                            $(".price_lowest").eq(i).text("历史最低价：￥"+prices[i].price);
                        } else {
                            $(".price_lowest").eq(i).text("");
                        }
                    }
                })
        }
*/

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
                        <input type="text" class="form-control" name="page" value="1" style="display: none">
                        <input type="text" class="form-control" id="searchInputBar" name="keyword" spellcheck="false" autocomplete="off" value="<%=keyword%>">
                        <span class="input-group-btn">
                                  <button id="searchSubmit" class="btn btn-default" type="button">搜索</button>
                        </span>
                    </div>
                </form>
            </div>
            <div  class="col-md-3"></div>
        </div>
    </div>
    <%--分页--%>
    <div class="row-fluid">
        <div class="col-md-12">
            <div class="col-md-10"></div>
            <div class="col-md-2">
                <ul class="pagination pagination-sm" style="margin: 0px">
                    <li <% if(pageNow == 1){ %> class="disabled" <% } %> >
                        <a <% if(pageNow > 1){ %> href="//localhost:8080/search/search?keyword=<%=keyword%>&page=<%=pageNow-1%>" <% } %> ><</a>
                    </li>
                    <li>
                        <span><%=pageNow%>/<%=pageTotal%></span>
                    </li>
                    <li <% if(pageNow == pageTotal){ %> class="disabled" <% } %> >
                        <a <% if(pageNow < pageTotal){ %> href="//localhost:8080/search/search?keyword=<%=keyword%>&page=<%=pageNow+1%>" <% } %> >></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="row-fluid">
        <div class="col-md-12">
            <hr style="height: 3px"/>
        </div>
    </div>
    <%--商品详情列表开始--%>

        <%
            for(int i = 0; i < row; i++) {
        %>
        <div class="row-fluid">
            <div class="col-md-12">
                <div class="col-md-1"></div>
                <%
                    for(int j = 0; j < 5; j++) {
                %>
                <div class="col-md-2">
                    <div class="thumbnail">
                        <a href="//localhost:8080/product/item?id=<%=products.get(5*i + j).getId()%>">
                            <img alt="220x220" src="<%=products.get(5*i + j).getImg_url()%>" />
                        </a>
                        <hr style="margin-top: 0px; margin-bottom: 0px"/>
                        <div class="caption">
                            <p class="price">
                                ￥<%=products.get(5*i + j).getLast_price()%>
                            </p>
                            <p class="price_lowest">
                                历史最低价：￥<%=products.get(5*i + j).getLowest_price()%>
                            </p>
                            <p class="product_name" >
                                <a href="//localhost:8080/product/item?id=<%=products.get(5*i + j).getId()%>"><%=products.get(5*i + j).getName()%></a>
                            </p>
                            <p class="shop_name">
                                <%=products.get(5*i + j).getShop_name()%> <span class="glyphicon glyphicon-tags"/>
                            </p>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>
                <div class="col-md-1"></div>
            </div>
        </div>
        <%
            }
        %>
        <div class="row-fluid">
        <div class="col-md-12">
            <div class="col-md-1"></div>
            <%
                for(int i = 0; i < productsLeftCount; i++) {
            %>
            <div class="col-md-2">
                <div class="thumbnail">
                    <a href="//localhost:8080/product/item?id=<%=products.get(5*row + i).getId()%>">
                        <img alt="220x220" src="<%=products.get(5*row + i).getImg_url()%>" />
                    </a>
                    <hr style="margin-top: 0px; margin-bottom: 0px"/>
                    <div class="caption">
                        <p class="price">
                            ￥<%=products.get(5*row + i).getLast_price()%>
                        </p>
                        <p class="price_lowest">
                            历史最低价：￥<%=products.get(5*row + i).getLowest_price()%>
                        </p>
                        <p class="product_name" >
                            <a href="//localhost:8080/product/item?id=<%=products.get(5*row + i).getId()%>"><%=products.get(5*row + i).getName()%></a>
                        </p>
                        <p class="shop_name">
                            <%=products.get(5*row + i).getShop_name()%> <span class="glyphicon glyphicon-tags"/>
                        </p>
                    </div>
                </div>
            </div>
            <%
                }
            %>
            <div class="col-md-1"></div>
        </div>
        </div>

</div>

    <%--商品详情列表结束--%>
    <div class="row-fluid">
        <div class="col-md-12">
            <hr style="height: 3px"/>
        </div>
    </div>
    <%--分页--%>
    <div class="row-fluid">
        <div class="col-md-12">
            <div class="col-md-6"></div>
            <div class="col-md-6">
                <ul class="pagination">
                    <li <% if(pageNow == 1){ %> class="disabled" <%}%> >
                        <a <% if(pageNow > 1){ %> href="//localhost:8080/search/search?keyword=<%=keyword%>&page=<%=pageNow-1%>" <% } %> >上一页</a>
                    </li>

                    <% if(pageNow > 4) { %>
                    <%--如果page大于4--%>
                    <li>
                        <a href="//localhost:8080/search/search?keyword=<%=keyword%>&page=1">1</a>
                    </li>
                    <% } %>

                    <% if(pageNow > 5) { %>
                    <%--如果page大于5--%>
                    <li>
                        <a>...</a>
                    </li>
                    <% } %>

                    <% if(pageNow > 3) { %>
                    <%--如果page大于3--%>
                    <li>
                        <a href="//localhost:8080/search/search?keyword=<%=keyword%>&page=<%=pageNow-3%>"><%=pageNow-3%></a>
                    </li>
                    <% } %>

                    <% if(pageNow > 2) { %>
                    <%--如果page大于2--%>
                    <li>
                        <a href="//localhost:8080/search/search?keyword=<%=keyword%>&page=<%=pageNow-2%>"><%=pageNow-2%></a>
                    </li>
                    <% } %>

                    <% if(pageNow > 1) { %>
                    <%--如果page大于1--%>
                    <li>
                        <a href="//localhost:8080/search/search?keyword=<%=keyword%>&page=<%=pageNow-1%>"><%=pageNow-1%></a>
                    </li>
                    <% } %>

                    <li class="active">
                        <a href="//localhost:8080/search/search?keyword=<%=keyword%>&page=<%=pageNow%>"><%=pageNow%></a>
                    </li>

                    <% if(pageNow < pageTotal) { %>
                    <%--如果page小于 pageTotal --%>
                    <li>
                        <a href="//localhost:8080/search/search?keyword=<%=keyword%>&page=<%=pageNow+1%>"><%=pageNow+1%></a>
                    </li>
                    <% } %>

                    <% if(pageNow < pageTotal - 1) { %>
                    <%--如果page小于 pageTotal --%>
                    <li>
                        <a href="//localhost:8080/search/search?keyword=<%=keyword%>&page=<%=pageNow+2%>"><%=pageNow+2%></a>
                    </li>
                    <% } %>

                    <% if(pageNow < pageTotal - 3) { %>
                    <%--如果page小于 pageTotal-3 --%>
                    <li>
                        <a>...</a>
                    </li>
                    <% } %>

                    <% if(pageNow < pageTotal - 2) { %>
                    <%--如果page小于 pageTotal-2 --%>
                    <li>
                        <a href="//localhost:8080/search/search?keyword=<%=keyword%>&page=<%=pageTotal%>"><%=pageTotal%></a>
                    </li>
                    <% } %>

                    <li <% if(pageNow == pageTotal){ %> class="disabled" <%}%>>
                        <a <% if(pageNow < pageTotal){ %> href="//localhost:8080/search/search?keyword=<%=keyword%>&page=<%=pageNow+1%>" <% } %> >下一页</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
