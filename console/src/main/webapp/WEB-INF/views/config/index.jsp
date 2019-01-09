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
    <div class="layui-card">
        <form:form action="${WEB_CONTEXT_PATH}/sys/config/query.json" method="get" class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">配置名称</label>
                    <div class="layui-input-inline">
                        <input type="text" name="cfgName" placeholder="请输入" autocomplete="off" class="layui-input">
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
                    <button class="layui-btn layuiadmin-btn-list" lay-submit lay-filter="LAY-sys-config-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                    </button>
                </div>
            </div>
        </form:form>

        <div class="layui-card-body">
            <div style="padding-bottom: 10px;">
                <a href="${WEB_CONTEXT_PATH}/sys/config/create" class="layui-btn">添加</a>
                <%--<button class="layui-btn layuiadmin-btn-list" id="LAY-sys-config-add" data-type="add"></button>--%>
                <button class="layui-btn" id="LAY-sys-config-batchdel">删除</button>
            </div>
            <table id="LAY-sys-config-list" lay-filter="LAY-sys-config-list"></table>
            <script type="text/html" id="table-sys-config-list-disabled-btn">
                {{#  if(d.disabled){ }}
                <a href="javascript:;" class="layui-btn layui-btn-danger layui-btn-xs">是</a>
                {{#  } else { }}
                <a href="javascript:;" class="layui-btn layui-btn-normal layui-btn-xs">否</a>
                {{#  } }}
            </script>
            <script type="text/html" id="table-sys-config-list-opt">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
            </script>
        </div>
    </div>
</div>

<%@ include file="../../resources/js.jsp"%>
<script src="${WEB_CONTEXT_PATH}/resources/js/sys/config.js"></script>
</body>
</html>