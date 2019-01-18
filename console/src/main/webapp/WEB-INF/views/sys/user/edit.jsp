<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../resources/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../../../resources/meta.jsp"%>
    <%@ include file="../../../resources/css.jsp"%>
</head>
<body>

<div class="layui-fluid">
    <div class="layui-col-md6">
        <div class="layui-card">
            <div class="layui-card-header">编辑用户</div>
            <div class="layui-card-body" style="padding: 15px;">
                <form:form servletRelativeAction="/sys/user/update" method="post" commandName="user" cssClass="layui-form" lay-filter="sys-user-edit-form">
                    <input type="hidden" name="_csrf_token" value="${_csrf_token}">
                    <form:hidden path="id"></form:hidden>
                    <div class="layui-form-item">
                        <label class="layui-form-label">用户名称</label>
                        <div class="layui-input-block">
                            <form:input path="username" readonly="true" lay-verify="required" autocomplete="off" placeholder="请输入用户名称" cssClass="layui-input layui-disabled"></form:input>
                            <form:errors path="username"></form:errors>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">显示名称</label>
                        <div class="layui-input-block">
                            <form:input path="viewname" lay-verify="required" autocomplete="off" placeholder="请输入显示名称" cssClass="layui-input"></form:input>
                            <form:errors path="viewname"></form:errors>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">用户密码</label>
                        <div class="layui-input-block">
                            <form:password path="password" lay-verify="required" autocomplete="off" placeholder="请输入用户密码" cssClass="layui-input"></form:password>
                            <form:errors path="password"></form:errors>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <div class="layui-footer" style="left: 0;">
                                <button type="submit" class="layui-btn" lay-submit lay-filter="sys-user-edit-form-submit">保存</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../../../resources/js.jsp"%>
<script src="../../resources/js/sys/user.js"></script>
</body>
</html>
