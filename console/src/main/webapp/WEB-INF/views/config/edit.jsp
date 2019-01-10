<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../resources/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../../resources/meta.jsp"%>
    <%@ include file="../../resources/css.jsp"%>
</head>
<body>

<div class="layui-fluid">
    <div class="layui-col-md6">
        <div class="layui-card">
            <div class="layui-card-header">编辑配置</div>
            <div class="layui-card-body" style="padding: 15px;">
                <form:form servletRelativeAction="/sys/config/update" method="post" commandName="config" cssClass="layui-form" lay-filter="sys-config-edit-form">
                    <form:hidden path="id"></form:hidden>
                    <div class="layui-form-item">
                        <label class="layui-form-label">代码</label>
                        <div class="layui-input-block">
                            <form:input path="cfgCode" readonly="true" cssClass="layui-input layui-disabled" ></form:input>
                            <form:errors path="cfgCode" cssClass="layui-bg-red"></form:errors>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">名称</label>
                        <div class="layui-input-block">
                            <form:input path="cfgName" cssClass="layui-input" lay-verify="required"></form:input>
                            <form:errors path="cfgName" cssClass="layui-bg-red"></form:errors>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">值</label>
                        <div class="layui-input-block">
                            <form:textarea path="cfgValue" cssClass="layui-textarea" lay-verify="required"></form:textarea>
                            <form:errors path="cfgValue" cssClass="layui-bg-red"></form:errors>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <div class="layui-footer" style="left: 0;">
                                <button type="submit" class="layui-btn" lay-submit lay-filter="sys-config-edit-form-submit">保存</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<%@ include file="../../resources/js.jsp"%>
<script src="../../resources/js/sys/config.js"></script>
</body>
</html>
