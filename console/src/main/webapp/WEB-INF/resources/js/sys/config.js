layui.define(['table', 'form'], function(exports){
    var $ = layui.$
        ,table = layui.table
        ,form = layui.form;

    /* config list */
    table.render({
        elem: '#LAY-sys-config-list'
        ,url: 'http://api.local.com/sys/config/query.json'
        ,request: {
            pageName: 'pageNum' //页码的参数名称，默认：page
            ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
        }
        ,cols: [[
            {type: 'checkbox', fixed: 'left'}
            ,{field: 'id', width: 100, title: 'ID', sort: true}
            ,{field: 'cfgName', title: '配置名称'}
            ,{field: 'cfgCode', title: '配置键', minWidth: 100}
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
                $.post({
                    url: 'http://api.local.com/sys/config/remove.json'
                    , data: JSON.stringify({id:data.id})
                    , dataType: 'json'
                    , contentType: 'application/json;charset=UTF-8'
                }).fail(function (jqXHR, textStatus, errorThrown) {
                    layer.msg(getErrMessage(jqXHR),{icon:5,closeBtn:2,time:0,shade: [0.3,'#000']});
                }).done(function (data, textStatus, jqXHR) {
                    table.reload('LAY-sys-config-list');
                    layer.msg('删除成功!');
                }).always(function( dataOrjqXHR, textStatus, jqXHROrerrorThrown ){
                    layer.close(index);
                });
            });
        } else if(obj.event === 'edit'){
            $(this).attr('href','http://api.local.com/sys/config/edit?id='.concat(data.id));
        }
    });


    form.on('submit(LAY-sys-config-search)', function(data){
        var field = data.field;
        table.reload('LAY-sys-config-list', {
            where: field
        });
        return false;
    });


    $('#LAY-sys-config-batchdel').off('click').on('click', function(){
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
            $.post({
                  url: 'http://api.local.com/sys/config/batch-remove.json'
                , data: JSON.stringify(reqData)
                , dataType: 'json'
                , contentType: 'application/json;charset=UTF-8'
            }).fail(function (jqXHR, textStatus, errorThrown) {
                layer.msg(getErrMessage(jqXHR),{icon:5,closeBtn:2,time:0,shade: [0.3,'#000']});
            }).done(function (data, textStatus, jqXHR) {
                table.reload('LAY-sys-config-list');
                layer.msg('删除成功!');
            }).always(function( dataOrjqXHR, textStatus, jqXHROrerrorThrown ){
                layer.close(index);
            });
        });

    });

    exports('sys_config', {})
});