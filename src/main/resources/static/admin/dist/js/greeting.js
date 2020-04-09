$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/greetings/list',
        datatype: "json",
        colModel: [
            // {label: 'id', name: 'greetingId', index: 'greetingId', width: 50, key: true, hidden: true},
            {label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true},
            {label: '内容', name: 'content', index: 'content', width: 100},
            {label: '添加时间', name: 'createTime', index: 'createTime', width: 100}
        ],
        height: 560,
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });
});

/**
 * jqGrid重新加载
 */
function reload() {
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

function greetingAdd() {
    reset();
    $('.modal-title').html('问候语添加');
    $('#greetingModal').modal('show');
}

//绑定modal上的保存按钮
$('#saveButton').click(function () {
    var greetingId = $("#greetingId").val();
    var greetingContent = $("#greetingContent").val();

    var params = $("#greetingForm").serialize();
    var url = '/admin/greetings/save';
    if (greetingId != null && greetingId > 0) {
        url = '/admin/greetings/update';
    }
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        data: params,
        success: function (result) {
            if (result.resultCode == 200 && result.data) {
                $('#greetingModal').modal('hide');
                swal("保存成功", {
                    icon: "success",
                });
                reload();
            }
            else {
                $('#greetingModal').modal('hide');
                swal("保存失败", {
                    icon: "error",
                });
            }
            ;
        },
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
        }
    });

});

function greetingEdit() {
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    reset();
    //请求数据
    $.get("/admin/greetings/info/" + id, function (r) {
        if (r.resultCode == 200 && r.data != null) {
            //填充数据至modal
            $("#greetingContent").val(r.data.content);
            //根据原greetingType值设置select选择器为选中状态
            if (r.data.greetingType == 1) {
                $("#greetingType option:eq(1)").prop("selected", 'selected');
            }
            if (r.data.greetingType == 2) {
                $("#greetingType option:eq(2)").prop("selected", 'selected');
            }
        }
    });
    $('.modal-title').html('问候语修改');
    $('#greetingModal').modal('show');
    $("#greetingId").val(id);
}

function deleteGreeting() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认要删除数据吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/admin/greetings/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.resultCode == 200) {
                            swal("删除成功", {
                                icon: "success",
                            });
                            $("#jqGrid").trigger("reloadGrid");
                        } else {
                            swal(r.message, {
                                icon: "error",
                            });
                        }
                    }
                });
            }
        }
    );
}

function reset() {
    $("#greetingContent").val('');

    $('#edit-error-msg').css("display", "none");
    $("#greetingType option:first").prop("selected", 'selected');
}