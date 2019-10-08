<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Easyui+spring+springmvc</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/easyui/themes/icon.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/easyui4.css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jq/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/local/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/js/easyui5.js"></script>
</head>
<body>
	
	<table id="dg"></table>
	<div id="tb">
	    <a id="save_user" data-options="iconCls:'icon-save',plain:true" class="easyui-linkbutton">保存</a>
	    <a id="undo_user" data-options="iconCls:'icon-undo',plain:true" class="easyui-linkbutton">取消</a>
		用户id: <div id="numberbox" class="easyui-numberbox"></div>
		用户名: <div id="textbox" class="easyui-textbox"></div>
		<a id="linkbutton" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
	</div>

</body>
</html>



