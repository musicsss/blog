$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/users/list',
        datatype: "json",
        colModel: [
            {label: '用户唯一标识码', name: 'userId', index: 'userId', width: 50, key: true, hidden: true},
            {label: '用户名', name: 'userName', index: 'userName', width: 50},
            {label: '手机号', name: 'phone', index: 'phone', width: 50},
            {label: '性别', name: 'gender', index: 'gender', width: 20},
            {label: '生日', name: 'birthday', index: 'birthday', width: 30},
            {label: '邮箱', name: 'emailAddress', index: 'emailAddress', width: 120},
            {label: '地址', name: 'address', index: 'address', width: 120},
            {label: '用户状态(0正常，1被锁定)', name: 'lockedFlag', index: 'lockedFlag', width: 180},
            {label: '添加时间', name: 'createTime', index: 'createTime', width: 80},
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
        mtype : "post",
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

    new AjaxUpload('#uploadCarouselImage', {
        action: '/admin/upload/file',
        name: 'file',
        autoSubmit: true,
        responseType: "json",
        onSubmit: function (file, extension) {
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))) {
                alert('只支持jpg、png、gif格式的文件！');
                return false;
            }
        },
        onComplete: function (file, r) {
            if (r != null && r.resultCode == 200) {
                $("#carouselImg").attr("src", r.data);
                $("#carouselImg").attr("style", "width: 128px;height: 128px;display:block;");
                return false;
            } else {
                alert("error");
            }
        }
    });
});