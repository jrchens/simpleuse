<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script>
    function getErrMessage(xhr) {
        var content = xhr.responseJSON.msg;
        content = content.replace(/::/g,'<br>');
        return content;
    }
</script>
<script src="http://res.local.com/layuiadmin/layui/layui.js"></script>