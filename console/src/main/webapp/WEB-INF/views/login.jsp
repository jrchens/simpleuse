<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../resources/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../resources/meta.jsp"%>
    <%@ include file="../resources/css.jsp"%>
    <link rel="stylesheet" href="http://res.local.com/layuiadmin/style/login.css" media="all">
</head>
<body>


<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">

    <div class="layadmin-user-login-main">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <h2>容易软件</h2>
            <p>容易软件内容管理系统</p>
        </div>
        <form:form action="login" method="post" class="layadmin-user-login-box layadmin-user-login-body layui-form">
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label>
                <input type="text" name="username" id="LAY-user-login-username" lay-verify="required" placeholder="用户名" class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
                <input type="password" name="password" id="LAY-user-login-password" lay-verify="required" placeholder="密码" class="layui-input">
            </div>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-login-submit">登 入</button>
            </div>
        </form:form>
    </div>

    <div class="layui-trans layadmin-user-login-footer">
        <p id="copyright"></p>
    </div>

    <!--<div class="ladmin-user-login-theme">
      <script type="text/html" template>
        <ul>
          <li data-theme=""><img src="{{ layui.setter.base }}style/res/bg-none.jpg"></li>
          <li data-theme="#03152A" style="background-color: #03152A;"></li>
          <li data-theme="#2E241B" style="background-color: #2E241B;"></li>
          <li data-theme="#50314F" style="background-color: #50314F;"></li>
          <li data-theme="#344058" style="background-color: #344058;"></li>
          <li data-theme="#20222A" style="background-color: #20222A;"></li>
        </ul>
      </script>
    </div>-->

</div>
<script>
    var cp = '&#169;\t'.concat((new Date()).getFullYear()).concat('\t\t<a target="_blank" href="www.simpleuse.com.cn">容易软件</a>');
    document.getElementById('copyright').innerHTML = cp;
</script>

<%@ include file="../resources/js.jsp"%>
<script>
    layui.use(['form'], function(){
        var form = layui.form;
        form.render();
        form.on('submit(LAY-user-login-submit)', function(data){
            // console.log(data.field);
            return true;
        });

        <c:if test="${not empty err}">
            layer.msg('${err}',{icon:5});
            <c:remove var="err"></c:remove>
        </c:if>
    });
</script>

</body>
</html>
