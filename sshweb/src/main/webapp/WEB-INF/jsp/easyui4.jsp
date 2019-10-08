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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/js/easyui4.js"></script>
</head>
<body>
	
	<div id="tree"></div>
	<div id="menu" class="easyui-menu" style="width:100px">
		<div onclick="addBoread()">添加兄弟节点</div>
		<div onclick="add()">添加子节点</div>
		<div onclick="deleteTree()">删除节点</div>
	</div><br>
	<button onclick="allchk()">全选</button>
	<button onclick="unchk()">半选</button>
	<button onclick="nochk()">没选</button>

</body>
</html>



