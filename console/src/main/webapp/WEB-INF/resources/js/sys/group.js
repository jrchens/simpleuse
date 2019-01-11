layui.define(['table', 'form'], function(exports){
    var $ = layui.$
        ,table = layui.table
        ,form = layui.form;

    var queryUrl = $('#sys-group-index-query-form').attr('action');
    table.render({
        elem: '#sys-group-index-data-table'
        ,url: queryUrl
        ,request: {
            pageName: 'pageNum' //页码的参数名称，默认：page
            ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
        }
        ,autoSort:false
        ,cols: [[
            {type: 'checkbox', fixed: 'left'}
            ,{field: 'id', width: 100, title: 'ID', sort: true}
            ,{field: 'groupname', title: '群组代码', minWidth: 100}
            ,{field: 'viewname', title: '群组名称',sort:true}
            /*,{field: 'plus', title: '加权', templet: '#sys-group-index-data-table-plus', minWidth: 80, align: 'center'}*/
            ,{title: '操作', minWidth: 150, align: 'center', fixed: 'right', toolbar: '#sys-group-index-data-table-opt'}
        ]]
        ,page: true
        ,limit: 10
        ,limits: [10, 15, 20, 25, 30]
    });

    table.on('sort(sys-group-index-data-table)', function(obj){
        table.reload('sys-group-index-data-table',{
            initSort:obj
            ,where:{
                sort:obj.field
                ,order:obj.type
            }
        });
        // table.reload('idTest', {
        //     initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。
        //     ,where: { //请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）
        //         field: obj.field //排序字段
        //         ,order: obj.type //排序方式
        //     }
        // });
    });

    table.on('tool(sys-group-index-data-table)', function(obj){
        var data = obj.data;
        // console.log(obj);
        if(obj.event === 'del'){
            layer.confirm('确定删除吗？删除后数据将无法恢复！', function(index){
                var removeForm = $('#sys-group-index-remove-form');
                $('#sys-group-index-remove-form-id',removeForm).val(JSON.stringify({id:data.id}));
                removeForm.submit();
            });
        } else if(obj.event === 'edit'){
            $(this).attr('href','http://api.local.com/sys/group/edit?id='.concat(data.id));
        }
    });


    form.on('submit(sys-group-index-query-form-search)', function(data){
        var field = data.field;
        table.reload('sys-group-index-data-table', {
            where: field
        });
        return false;
    });

    $('#sys-group-index-batch-remove-btn').off('click').on('click', function(){
        var checkStatus = table.checkStatus('sys-group-index-data-table')
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

            var batchRemoveForm = $('#sys-group-index-batch-remove-form');
            $('#sys-group-index-batch-remove-form-ids',batchRemoveForm).val(JSON.stringify(reqData));
            batchRemoveForm.submit();
        });

    });

    form.render(null,'sys-group-create-form');
    form.on('switch(sys-group-create-form-plus)', function(data){
        var plus = $('#sys-group-create-form-hidden-plus');
        if (data.elem.checked){
            plus.val(1);
        }else{
            plus.val(0);
        }
    });
    form.on('submit(sys-group-create-form-submit)', function(data){
        return true;
    });


    form.render(null,'sys-group-edit-form');
    form.on('switch(sys-group-edit-form-plus)', function(data){
        var plus = $('#sys-group-create-form-hidden-plus');
        if (data.elem.checked){
            plus.val(1);
        }else{
            plus.val(0);
        }
    });
    form.on('submit(sys-group-edit-form-submit)', function(data){
        return true;
    });

    exports('sys_group', {})
});