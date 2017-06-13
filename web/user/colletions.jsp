<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的优选</title>
    <link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
    <script type="text/javascript" src="../jslib/jquery-3.2.0.js"></script>
    <script type="text/javascript" src="../jslib/bootstrap.js"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script>
        $(function () {
            $("#modifyUsername").click(function () {
                if(verifyUnername($("#newUsername").val())) {
                    alert("ok");
                }

            })

            $("#newUsername").focus(function () {
                $("#prompt").text("");
            })
        })

        function verifyUnername(data) {
            if(data.trim() == "") {
                $("#prompt").text("用户名不能为空");
                return false;
            }
            if(data.length < 3 || data.length > 15) {
                $("#prompt").text("用户名长度须在3-15之间");
                return false;
            }
            var regex = /^[a-zA-Z0-9_\u4E00-\u9FA5]+$/;
            if(regex.test(data) != true) {
                $("#prompt").text("用户名格式有误");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<%--导航栏-------------------------------%>
<%@ include file="../main/titleBar.jsp" %>
<%--导航栏-------------------------------%>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-md-12">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div class="page-header">
                    <h2>
                        我的优选 <small>超值你的网购</small>
                    </h2>
                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
    </div>
    <div class="row-fluid">
        <div class="col-md-12">
            <div class="col-md-2"></div>
            <div class="col-md-1">
                <div class="list-group">
                    <a href="//localhost:8080/user/homepage" class="list-group-item">个人账号</a>
                    <a href="//localhost:8080/user/colletions" class="list-group-item active">商品收藏<span class="badge"></span></a>
                    <a href="//localhost:8080/user/security" class="list-group-item">安全设置</a>
                </div>
            </div>
            <div class="col-md-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <img src="../img/head.png" alt="120x120" class="img-circle" style="display: block;width: auto;max-width:10%" />
                    </div>
                    <div class="panel-body">

                    </div>

                    <div class="panel-footer">

                    </div>
                </div>

            </div>
            <div class="col-md-3"></div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-container-408253" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    修改用户名
                </h4>
            </div>
            <div class="modal-body">
                <div>请输入新的用户名：</div>
                <div style="padding-top: 10px"><input id="newUsername" type="text" class="form-control" name="username" autocomplete="off"  spellcheck="false"/></div>
                <div style="font-size: small">*用户名只能修改一次</div>
                <div id="prompt" style="font-size: small;color: #F00;"></div>
            </div>
            <div class="modal-footer">
                <button id="modifyUsername" type="button" class="btn btn-primary">修改</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>

    </div>

</div>
</body>
</html>
