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
    <div class="layui-card">
        <form:form id="sys-role-index-query-form" servletRelativeAction="/sys/role/query.json" method="get" class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">角色名称</label>
                    <div class="layui-input-inline">
                        <input type="text" name="viewname" placeholder="请输入角色名称" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <%--<div class="layui-inline">--%>
                    <%--<label class="layui-form-label">文章标签</label>--%>
                    <%--<div class="layui-input-inline">--%>
                        <%--<select name="label">--%>
                            <%--<option value="">请选择标签</option>--%>
                            <%--<option value="0">美食</option>--%>
                            <%--<option value="1">新闻</option>--%>
                            <%--<option value="2">八卦</option>--%>
                            <%--<option value="3">体育</option>--%>
                            <%--<option value="4">音乐</option>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-list" lay-submit lay-filter="sys-role-index-query-form-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                    </button>
                </div>
            </div>
        </form:form>

        <div class="layui-card-body">
            <div style="padding-bottom: 10px;">
                <a href="${WEB_CONTEXT_PATH}/sys/role/create" class="layui-btn">添加</a>
                <%--<button class="layui-btn layuiadmin-btn-list" id="LAY-sys-config-add" data-type="add"></button>--%>
                <button class="layui-btn" id="sys-role-index-batch-remove-btn">删除</button>
            </div>
            <table id="sys-role-index-data-table" lay-filter="sys-role-index-data-table"></table>
            <script type="text/html" id="sys-role-index-data-table-plus">
                {{#  if(d.plus){ }}
                <a href="javascript:;" class="layui-btn layui-bg-orange layui-btn-xs">是</a>
                {{#  } else { }}
                <a href="javascript:;" class="layui-btn layui-bg-gray layui-btn-xs">否</a>
                {{#  } }}
            </script>
            <script type="text/html" id="sys-role-index-data-table-opt">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
            </script>
        </div>
    </div>
</div>
<div class="layui-hide">
    <form:form commandName="role" servletRelativeAction="/sys/role/remove" method="post" id="sys-role-index-remove-form">
        <input type="hidden" id="sys-role-index-remove-form-id" name="id">
    </form:form>
    <form:form commandName="role" servletRelativeAction="/sys/role/batch-remove" method="post" id="sys-role-index-batch-remove-form">
        <input type="hidden" id="sys-role-index-batch-remove-form-ids" name="ids">
    </form:form>
</div>

<%@ include file="../../../resources/js.jsp"%>
<script src="../../../resources/js/sys/role.js"></script>
</body>
</html>