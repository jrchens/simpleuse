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
            <div class="layui-card-header">新建配置</div>
            <div class="layui-card-body" style="padding: 15px;">
                <form:form servletRelativeAction="/sys/config/save" method="post" commandName="config" cssClass="layui-form" lay-filter="sys-config-create-form">
                    <div class="layui-form-item">
                        <label class="layui-form-label">代码</label>
                        <div class="layui-input-block">
                            <input type="text" name="cfgCode" lay-verify="required" autocomplete="off" placeholder="请输入代码" class="layui-input">
                            <form:errors path="cfgCode"></form:errors>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">名称</label>
                        <div class="layui-input-block">
                            <input type="text" name="cfgName" lay-verify="required" autocomplete="off" placeholder="请输入名称" class="layui-input">
                            <form:errors path="cfgName"></form:errors>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">值</label>
                        <div class="layui-input-block">
                            <textarea name="cfgValue" class="layui-textarea" lay-verify="required" autocomplete="off" placeholder="请输入值" ></textarea>
                            <form:errors path="cfgValue"></form:errors>
                        </div>
                    </div>
                    <%--<div class="layui-form-item">--%>
                        <%--<label class="layui-form-label">禁用</label>--%>
                        <%--<div class="layui-input-block">--%>
                            <%--<input type="text" name="disabled" lay-verify="required" autocomplete="off" placeholder="请输入值" class="layui-input">--%>
                            <%--<form:errors path="disabled"></form:errors>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <div class="layui-footer" style="left: 0;">
                                <button type="submit" class="layui-btn" lay-submit lay-filter="sys-config-create-form-submit">保存</button>
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
