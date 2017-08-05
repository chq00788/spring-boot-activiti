<!DOCTYPE html>
<html lang="zh-CN" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Activiti</title>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/bootstrap-select/bootstrap-select.min.css"/>
    <link rel="stylesheet" href="/bootstrap-table/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="/toastr/toastr.min.css"/>
</head>
<body>
<div class="container">
    <h1>Hello</h1>
    <button type="button" class="btn btn-primary" onclick="showAdd()">新增用户</button>
    <button type="button" class="btn btn-primary" onclick="showGroup()">设置组</button>
    <div class="" style="margin: 5px">
        <table id="table">
            <thead>
            <tr>
                <th data-field="state" data-checkbox="true"></th>
                <th data-field="id">ID</th>
                <th data-field="firstName">FirstName</th>
                <th data-field="lastName">LastName</th>
                <th data-field="email">Email</th>
                <th data-field="operate" data-formatter="operateFormatter">Operation</th>
            </tr>
            </thead>
        </table>
    </div>

</div>

<!--新增用户-->
<div id="addModal" class="modal fade addModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增用户</h4>
            </div>
            <div class="modal-body">
                <form id="addForm" role="form">

                    <div class="form-group">
                        <label for="firstName">FirstName</label><input class="form-control" id="firstName"
                                                                       name="firstName"/>
                    </div>
                    <div class="form-group">
                        <label for="lastName">LastName</label><input class="form-control" id="lastName"
                                                                     name="lastName"/>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label><input class="form-control" id="email"
                                                               name="email"/>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label><input class="form-control" id="password"
                                                                     name="password"/>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="save()">保存</button>
            </div>
        </div>
    </div>
</div>
<!--设置组-->
<div id="groupModal" class="modal fade addModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                >&times;</span></button>
                <h4 class="modal-title">设置组</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="userId">
                <form id="groupForm">
                    <select id="groupSelect" class="form-control">
                    </select>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="saveGroup()">保存</button>
            </div>
        </div>
    </div>
</div>
<!--删除用户-->
<div id="deleteModal" class="modal fade " tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                >&times;</span></button>
                <h4 class="modal-title">确定删除用户？</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="deleteUserId">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="deleteUser()">确定</button>
            </div>
        </div>
    </div>
</div>
<script src="/js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/bootstrap-select/bootstrap-select.min.js"></script>
<script src="/bootstrap-table/bootstrap-table.min.js"></script>
<script src="/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
<script src="/toastr/toastr.min.js"></script>
<script type="text/javascript">
    $(function () {
        $('#table').bootstrapTable({
            url: '/user/query',
            striped: true,
            showRefresh: true,
            pagination: true,
            sidePagination: 'server',
            pageNumber: 1,
            pageSize: 2,
            pageList: [2, 4, 8, 10],
            search: true,
            silent: true,
            dataType: "json",
            responseHandler: function (data) {
                return {
                    "total": data.total,//总页数
                    "rows": data.rows   //数据
                };
            },
            onLoadSuccess: function (data) {  //加载成功时执行
//                toastr.info("数据加载成功！");
            },
            onLoadError: function () {  //加载失败时执行
                alert("加载数据失败");
            }
        });
        toastr.options = {
            closeButton: true,
            positionClass: "toast-bottom-right"
        }
    });

    function showAdd() {
        $('#addModal').modal({backdrop: 'static', keyboard: true});
    }

    function showGroup(id) {
        $("#userId").val(id);
        getGroupList();
        $('#groupModal').modal({backdrop: 'static', keyboard: true});
    }

    function save() {
        $.ajax({
            type: "POST",
            url: "/user/add",
            data: $("#addForm").serialize(),
            success: function (data) {
                if (data.success == true) {
                    $('#addForm')[0].reset();
                    $('#addModal').modal('hide');
                    $('#table').bootstrapTable('refresh');
                    toastr.success("保存成功");
                } else {

                }
            }
        });
    }

    function operateFormatter(value, row, index) {
        return [
            '<div class="pull-center">',
            '<a class="btn btn-default btn-sm" href="javascript:void(0)" title="Like" onclick="showGroup(\'' + row.id + '\')">',
            '<i class="glyphicon glyphicon-paperclip"></i>',
            '</a>  ',
            '<a class="btn btn-default btn-sm" href="javascript:void(0)" title="Remove" onclick="showDelete(\'' + row.id + '\')">',
            '<i class="glyphicon glyphicon-remove"></i>',
            '</a>',
            '</div>'
        ].join('');
    }

    function getGroupList() {
        $("#groupSelect").empty();
        $.ajax({
            type: 'post',
            url: '/group/query',
            success: function (data) {
                var options = data.rows;
                for (var i = 0; i < options.length; i++) {
                    $("#groupSelect").append("<option value='" + options[i].id + "'>" + options[i].name + "</option>>");
                }
            }
        });
    }

    function saveGroup() {
        var groupId = $("#groupSelect").val();
        var userId = $("#userId").val();
        $.ajax({
            type: 'post',
            url: '/user/setGroup',
            data: "userId=" + userId + "&groupId=" + groupId,
            success: function (data) {
                $('#groupModal').modal('hide');
            }
        });
    }

    function showDelete(id) {
        $("#deleteUserId").val(id);
        $('#deleteModal').modal({backdrop: 'static', keyboard: true});
    }

    function deleteUser() {
        var userId = $("#deleteUserId").val();
        $.ajax({
            type: 'post',
            url: '/user/delete',
            data: "userId=" + userId,
            success: function (data) {
                $('#deleteModal').modal('hide');
                $('#table').bootstrapTable('refresh');
            }
        });
    }
</script>
</body>
</html>