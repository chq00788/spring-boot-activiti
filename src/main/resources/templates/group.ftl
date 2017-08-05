<!DOCTYPE html>
<html lang="zh-CN" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Activiti</title>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/jquery.bootgrid/jquery.bootgrid.min.css"/>
    <link rel="stylesheet" href="/bootstrap-table/bootstrap-table.min.css"/>
</head>
<body>
<div class="container">
    <h1>Hello</h1>
    <button type="button" class="btn btn-primary" onclick="showAdd()">新增组</button>
    <div class="" style="margin: 5px">
        <table id="table">
            <thead>
            <tr>
                <th data-field="state" data-checkbox="true"></th>
                <th data-field="id">ID</th>
                <th data-field="name">Name</th>
                <th data-field="type">Type</th>
                <th data-field="operate" data-formatter="operateFormatter">Operation</th>
            </tr>
            </thead>
        </table>
    </div>

</div>

<!--新增组-->
<div id="addModal" class="modal fade addModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span>&times;</span>
                </button>
                <h4 class="modal-title">新增组</h4>
            </div>
            <div class="modal-body">
                <form id="addForm">

                    <div class="form-group">
                        <label for="name">Name</label><input class="form-control" id="name" name="name"/>
                    </div>
                    <div class="form-group">
                        <label for="type">Type</label><input class="form-control" id="type" name="type"/>
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

<script src="/js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/bootstrap-table/bootstrap-table.min.js"></script>
<script src="/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript">
    $(function () {
        $('#table').bootstrapTable({
            method: 'post',
            url: '/group/query',
            striped: true,
            showRefresh: true,
            pagination: true,
            sidePagination: 'server',
            pageNumber: 1,
            pageSize: 2,
            pageList: [2, 4, 8, 10],
            search: true,
            dataType: "json",
            responseHandler: function (data) {
                console.log(data);
                return {
                    "total": 5,//总页数
                    "rows": data.rows   //数据
                };
            },
            onLoadSuccess: function (data) {  //加载成功时执行
                console.log(data);
            },
            onLoadError: function () {  //加载失败时执行
                alert("加载数据失败");
            },
        });
    });


    function showAdd() {
        $('#addModal').modal({backdrop: 'static', keyboard: true});
    }

    function save() {
        $.ajax({
            type: "POST",
            url: "/group/add",
            data: $("#addForm").serialize(),
            success: function (data) {
                if (data.success == true) {
                    $('#addForm')[0].reset();
                    $('#addModal').modal('hide');
                    $("#grid").bootgrid('reload');
                } else {

                }
            }
        });
    }

    function operateFormatter(value, row, index) {
        return [
            '<div class="pull-center">',
            '<a class="btn btn-default btn-sm" href="javascript:void(0)" title="Like" onclick="showAdd()">',
            '<i class="glyphicon glyphicon-pencil"></i>',
            '</a>  ',
            '<a class="btn btn-default btn-sm" href="javascript:void(0)" title="Remove">',
            '<i class="glyphicon glyphicon-remove"></i>',
            '</a>',
            '</div>'
        ].join('');
    }
</script>
</body>
</html>