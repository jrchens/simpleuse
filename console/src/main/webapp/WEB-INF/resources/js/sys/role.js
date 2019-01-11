layui.define(['table', 'form'], function(exports){
    var $ = layui.$
        ,table = layui.table
        ,form = layui.form;

    /* config list */
    var queryUrl = $('#sys-role-index-query-form').attr('action');
    table.render({
        elem: '#sys-role-index-data-table'
        ,url: queryUrl
        ,request: {
            pageName: 'pageNum' //页码的参数名称，默认：page
            ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
        }
        ,cols: [[
            {type: 'checkbox', fixed: 'left'}
            ,{field: 'id', width: 100, title: 'ID', sort: true}
            ,{field: 'rolename', title: '角色代码', minWidth: 100}
            ,{field: 'viewname', title: '角色名称'}
            ,{field: 'plus', title: '加权', templet: '#sys-role-index-data-table-plus', minWidth: 80, align: 'center'}
            ,{title: '操作', minWidth: 150, align: 'center', fixed: 'right', toolbar: '#sys-role-index-data-table-opt'}
        ]]
        ,page: true
        ,limit: 10
        ,limits: [10, 15, 20, 25, 30]
    });

    table.on('tool(sys-role-index-data-table)', function(obj){
        var data = obj.data;
        // console.log(obj);
        if(obj.event === 'del'){
            layer.confirm('确定删除吗？删除后数据将无法恢复！', function(index){
                var removeForm = $('#sys-role-index-remove-form');
                $('#sys-role-index-remove-form-id',removeForm).val(JSON.stringify({id:data.id}));
                removeForm.submit();
            });
        } else if(obj.event === 'edit'){
            $(this).attr('href','http://api.local.com/sys/role/edit?id='.concat(data.id));
        }
    });


    form.on('submit(sys-role-index-query-form-search)', function(data){
        var field = data.field;
        table.reload('sys-role-index-data-table', {
            where: field
        });
        return false;
    });

    $('#sys-role-index-batch-remove-btn').off('click').on('click', function(){
        var checkStatus = table.checkStatus('sys-role-index-data-table')
            ,checkedData = checkStatus.data
            ,reqData = [];

        // console.log('checkedData',checkedData);

        if(checkedData.length === 0){
            return layer.msg('请先选择需要删除的数据!');
        }

        $.each(checkedData,function (i,e) {
            reqData.push({id:e.id});
        });
        // console.log('reqData',reqData);

        layer.confirm('确定删除吗？删除后数据将无法恢复！', function(index) {

            var batchRemoveForm = $('#sys-role-index-batch-remove-form');
            $('#sys-role-index-batch-remove-form-ids',batchRemoveForm).val(JSON.stringify(reqData));
            batchRemoveForm.submit();
        });

    });

    form.render(null,'sys-role-create-form');
    form.on('switch(sys-role-create-form-plus)', function(data){
        var plus = $('#sys-role-create-form-hidden-plus');
        if (data.elem.checked){
            plus.val(1);
        }else{
            plus.val(0);
        }
    });
    form.on('submit(sys-role-create-form-submit)', function(data){
        return true;
    });


    form.render(null,'sys-role-edit-form');
    form.on('switch(sys-role-edit-form-plus)', function(data){
        var plus = $('#sys-role-create-form-hidden-plus');
        if (data.elem.checked){
            plus.val(1);
        }else{
            plus.val(0);
        }
    });
    form.on('submit(sys-role-edit-form-submit)', function(data){
        return true;
    });

    exports('sys_role', {})
});