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
            <div class="layui-card-header">新建群组</div>
            <div class="layui-card-body" style="padding: 15px;">
                <form:form servletRelativeAction="/sys/group/save" method="post" commandName="group" cssClass="layui-form" lay-filter="sys-group-create-form">
                    <div class="layui-form-item">
                        <label class="layui-form-label">代码</label>
                        <div class="layui-input-block">
                            <form:input path="groupname" lay-verify="required" autocomplete="off" placeholder="请输入代码" cssClass="layui-input"></form:input>
                            <form:errors path="groupname"></form:errors>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">名称</label>
                        <div class="layui-input-block">
                            <form:input path="viewname" lay-verify="required" autocomplete="off" placeholder="请输入名称" cssClass="layui-input"></form:input>
                            <form:errors path="viewname"></form:errors>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <div class="layui-footer" style="left: 0;">
                                <button type="submit" class="layui-btn" lay-submit lay-filter="sys-group-create-form-submit">保存</button>
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
<script src="../../resources/js/sys/group.js"></script>
</body>
</html>
