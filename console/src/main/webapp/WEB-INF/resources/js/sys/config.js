layui.define(['table', 'form'], function(exports){
    var $ = layui.$
        ,table = layui.table
        ,form = layui.form;

    /* config list */
    var queryUrl = $('#config-index-query-form').attr('action');
    table.render({
        elem: '#LAY-sys-config-list'
        ,url: queryUrl
        ,request: {
            pageName: 'pageNum' //页码的参数名称，默认：page
            ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
        }
        ,cols: [[
            {type: 'checkbox', fixed: 'left'}
            ,{field: 'id', width: 100, title: 'ID', sort: true}
            ,{field: 'cfgCode', title: '配置键', minWidth: 100}
            ,{field: 'cfgName', title: '配置名称'}
            ,{field: 'cfgValue', title: '配置值'}
            ,{field: 'disabled', title: '禁用', templet: '#table-sys-config-list-disabled-btn', minWidth: 80, align: 'center'}
            ,{title: '操作', minWidth: 150, align: 'center', fixed: 'right', toolbar: '#table-sys-config-list-opt'}
        ]]
        ,page: true
        ,limit: 10
        ,limits: [10, 15, 20, 25, 30]
    });

    table.on('tool(LAY-sys-config-list)', function(obj){
        var data = obj.data;
        // console.log(obj);
        if(obj.event === 'del'){
            layer.confirm('确定删除吗？删除后数据将无法恢复！', function(index){
                var removeForm = $('#sys-config-index-remove-form');
                $('#sys-config-index-remove-form-id',removeForm).val(data.id);
                removeForm.submit();
                // $.post({
                //     url: 'http://api.local.com/sys_config/remove.json'
                //     , data: {id:data.id}
                //     , dataType: 'json'
                // }).fail(function (jqXHR, textStatus, errorThrown) {
                //     layer.msg(getErrMessage(jqXHR),{icon:5,closeBtn:2,time:0,shade: [0.3,'#000']});
                // }).done(function (data, textStatus, jqXHR) {
                //     table.reload('LAY-sys-config-list');
                //     layer.msg('删除成功!');
                // }).always(function( dataOrjqXHR, textStatus, jqXHROrerrorThrown ){
                //     layer.close(index);
                // });
            });
        } else if(obj.event === 'edit'){
            $(this).attr('href','http://api.local.com/sys_config/edit?id='.concat(data.id));
        }
    });


    form.on('submit(LAY-sys-config-search)', function(data){
        var field = data.field;
        table.reload('LAY-sys-config-list', {
            where: field
        });
        return false;
    });

    $('#sys-config-index-disable-enable').off('click').on('click', function(){
        var checkStatus = table.checkStatus('LAY-sys-config-list')
            ,checkedData = checkStatus.data
            ,reqData = [];

        // console.log('checkedData',checkedData);

        if(checkedData.length === 0){
            return layer.msg('请先选择需要禁用的数据!');
        }

        $.each(checkedData,function (i,e) {
            reqData.push({id:e.id,disabled:e.disabled});
        });
        // console.log('reqData',reqData);
        layer.confirm('确定禁用吗？', function(index) {

            var disableEnableForm = $('#sys-config-index-batch-disable-enable-form');
            $('#sys-config-index-batch-disable-enable-form-list',disableEnableForm).val(JSON.stringify(reqData));
            disableEnableForm.submit();

            // $.post({
            //     url: 'http://api.local.com/sys_config/batch-disable-enable.json'
            //     , data: JSON.stringify(reqData)
            //     , dataType: 'json'
            //     , contentType: 'application/json;charset=UTF-8'
            // }).fail(function (jqXHR, textStatus, errorThrown) {
            //     layer.msg(getErrMessage(jqXHR), {icon: 5, closeBtn: 2, time: 0, shade: [0.3, '#000']});
            // }).done(function (data, textStatus, jqXHR) {
            //     table.reload('LAY-sys-config-list');
            //     layer.msg('禁用成功!');
            // }).always(function (dataOrjqXHR, textStatus, jqXHROrerrorThrown) {
            //     layer.close(index);
            // });
        });
    });

    $('#sys-config-batchdel').off('click').on('click', function(){
        var checkStatus = table.checkStatus('LAY-sys-config-list')
            ,checkedData = checkStatus.data
            ,reqData = [];

        // console.log('checkedData',checkedData);

        if(checkedData.length === 0){
            return layer.msg('请先选择需要删除的数据!');
        }

        $.each(checkedData,function (i,e) {
            reqData.push(e.id);
        });
        // console.log('reqData',reqData);

        layer.confirm('确定删除吗？删除后数据将无法恢复！', function(index) {

            var disableEnableForm = $('#sys-config-index-batch-remove-form');
            $('#sys-config-index-batch-remove-form-ids',disableEnableForm).val(JSON.stringify(reqData));
            disableEnableForm.submit();

            // $.post({
            //       url: 'http://api.local.com/sys_config/batch-remove.json'
            //     , data: JSON.stringify(reqData)
            //     , dataType: 'json'
            //     , contentType: 'application/json;charset=UTF-8'
            // }).fail(function (jqXHR, textStatus, errorThrown) {
            //     layer.msg(getErrMessage(jqXHR),{icon:5,closeBtn:2,time:0,shade: [0.3,'#000']});
            // }).done(function (data, textStatus, jqXHR) {
            //     table.reload('LAY-sys-config-list');
            //     layer.msg('删除成功!');
            // }).always(function( dataOrjqXHR, textStatus, jqXHROrerrorThrown ){
            //     layer.close(index);
            // });
        });

    });


    form.render(null,'sys-config-create-form');
    form.on('submit(sys-config-create-form-submit)', function(data){
        return true;
    });


    form.render(null,'sys-config-edit-form');
    form.on('submit(sys-config-edit-form-submit)', function(data){
        return true;
    });

    exports('sys_config', {})
});