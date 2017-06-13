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
            $("#modifyPassword").click(function () {
                var oldPassword = $("#oldPassword").val();
                var newPassword = $("#newPassword").val();
                var newPasswordRepeat = $("#newPasswordRepeat").val();
                if(verifyPassword(newPassword, newPasswordRepeat, oldPassword)) {
                    $.post("//localhost:8080/user/modifyPassword",
                        {"oldPassword":oldPassword,"newPassword":newPassword,"newPasswordRepeat":newPasswordRepeat,},
                        function (data) {
                            switch(data) {
                                //修改成功
                                case 0 :location.reload(true);break;
                                //密码错误
                                case 1 :$("#prompt").text("原密码错误");break;
                                //新旧密码相同
                                case 2 :$("#prompt").text("新旧密码相同");break;
                                //密码不一致
                                case 3 :$("#prompt").text("密码不一致");break;
                                //修改失败
                                case 4 :;
                                default :$("#prompt").text("修改失败");break;
                            }

                        })
                }

            })

            $("#oldPassword").focus(function () {
                $("#prompt").text("");
            })
            $("#newPassword").focus(function () {
                $("#prompt").text("");
            })
            $("#newPasswordRepeat").focus(function () {
                $("#prompt").text("");
            })
        })

        function verifyPassword(newPassword , newPasswordRepeat, oldPassword) {
            if(newPassword.trim() == "" || oldPassword.trim() == "") {
                $("#prompt").text("密码不能为空");
                return false;
            }
            if(newPassword.length < 3 || newPassword.length > 20) {
                $("#prompt").text("密码长度须在3-20之间");
                return false;
            }
            var regex = /^[a-zA-Z0-9_]+$/;
            if(regex.test(newPassword) != true) {
                $("#prompt").text("密码格式有误");
                return false;
            }
            if(newPassword != newPasswordRepeat) {
                $("#prompt").text("两次密码输入不相同");
                return false;
            }
            if(newPassword == oldPassword) {
                $("#prompt").text("新密码与原密码相同");
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
                    <a href="//localhost:8080/user/colletions" class="list-group-item">商品收藏<span class="badge"></span></a>
                    <a href="//localhost:8080/user/security" class="list-group-item active">安全设置</a>
                </div>
            </div>
            <div class="col-md-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <img src="../img/head.png" alt="120x120" class="img-circle" style="display: block;width: auto;max-width:10%" />
                    </div>
                    <div class="panel-body">
                        <a id="modal-408253"  href="#modal-container-408253" data-toggle="modal" style="font-size: small">修改密码</a>
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
                    修改密码
                </h4>
            </div>
            <div class="modal-body">
                <div>请输入原密码：</div>
                <div style="padding-top: 10px"><input id="oldPassword" type="text" class="form-control" name="oldPassword" autocomplete="off"  spellcheck="false"/></div>

                <div style="padding-top: 10px">请输入新密码：</div>
                <div style="padding-top: 10px"><input id="newPassword" type="text" class="form-control" name="newPassword" autocomplete="off"  spellcheck="false"/></div>
                <div style="padding-top: 10px">请再次输入新密码：</div>
                <div style="padding-top: 10px"><input id="newPasswordRepeat" type="text" class="form-control" name="newPasswordRepeat" autocomplete="off"  spellcheck="false"/></div>
                <div id="prompt" style="font-size: small;color: #F00;"></div>
            </div>
            <div class="modal-footer">
                <button id="modifyPassword" type="button" class="btn btn-primary">修改</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>

    </div>

</div>
</body>
</html>
