<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Easyui+spring+springmvc</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/easyui/themes/icon.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/easyui3.css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jq/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/js/easyui3.js"></script>
</head>
<body class="easyui-layout" id="cc">
	
<!-- 	<div id="accordion">
		<div title="title1" class="tit">
			电脑<br>
			键盘<br>
			鼠标<br>
		</div>
		<div title="title2" class="tit">
			java<br>
			c++<br>
			c#<br></div>
		<div title="title3" class="tit">
			上海<br>
			北京<br>
			山西<br>
		</div>
	</div> -->
	
<!--  	<div data-options="region:'north',title:'北区 North Title',split:true,border:false" style="height:100px;"></div>
    <div data-options="region:'south',title:'南区South Title',split:true,border:false" style="height:100px;"></div>
    <div data-options="region:'east',title:'东区East',split:true,border:false" style="width:100px;"></div>
    <div data-options="region:'west',title:'西区West',split:true,border:false" style="width:100px;"></div>
    <div data-options="region:'center',title:'中区center title',border:false" style="padding:5px;background:#eee;"></div> -->
	
<!-- 	<div id="mm" class="easyui-menu" style="width:120px;">
    <div data-options="name:'new'">New</div>
    <div>
		<span>Open</span>
		<div style="width:150px;">
			<div><b>Word</b></div>
			<div>Excel</div>
			<div>PowerPoint</div>
		</div>
    </div>
    <div data-options="iconCls:'icon-save'">
    	<span >Save</span>
    	<div style="width:150px">
			<div>username</div>
			<div>password</div>
			<div>cs</div>
		</div>
    </div>
    <div class="menu-sep"></div>
    <div>Exit</div>
</div> -->
	
<!-- 	<a id="btn" href="http://www.baidu.com">链接按钮</a><br/><br/>
	<a id="btn1" href="#">菜单按钮</a>
	<div id="mm">
		<div data-options="name:'new'">new</div>
		<div>
			<span>file</span>
			<div style="width:150px">
			<div>new</div>
			<div data-options="name:'open file'">open file</div>
			<div>save</div>
			</div>
		</div>
		<div class="menu-sep"></div>
		<div>Edit</div>
	</div> -->
	
	<!-- <form id="ff" method="post">
    <div>
		<label for="name">姓名：</label>
		<input id="name" class="easyui-textbox" name="name" data-options="required:true" />
    </div><br>
    <div>
    	<label for="password">密码：</label>
    	<input class="easyui-textbox" type="password" name="password" data-options="required:true" />
    </div><br>
    <div>
		<label for="email">邮箱：</label>
		<input class="easyui-textbox" type="text" name="email" data-options="validType:'email'" />
    </div><br> 
    
     <label for="pwd">密 码 ：</label>
    <input id="pwd" class="easyui-validatebox" type="password" name="pwd" data-options="required:true,missingMessage:'请输入密码',validType:'length[5,20]'" >
    <br>
    <label for="rpwd">确定密码：</label>
    <input id="rpwd" class="easyui-validatebox" type="password" name="rpwd" required="required" validType="equals['#pwd'] " missingMessage='请再次输入密码'>
    <br>
    <input type="submit">
</form> -->
	
	 	
	<!-- 组合框 -->
	<!--<div id="combo" style="width:150px"></div>	
 	<div id="sp">
 		<span>请选择</span><br/>
 		<input type="radio" name="lan" value="11" /><span>java</span><br/>
 		<input type="radio" name="lan" value="22" /><span>c#</span><br/>
 		<input type="radio" name="lan" value="33" /><span>php</span><br/>
 		<input type="radio" name="lan" value="44" /><span>python</span><br/>
 	</div> -->
 	
<!--  	<select id="cc" class="easyui-combobox" name="dept" style="width:200px;">
 		<option value="aa">电脑</option>
 		<option>书包</option>
 		<option>笔记本</option>
 		<option>手机</option>
 	</select> -->
 	
 	<!-- <input id="com" name="dep" value="">  -->
 	
 	<!-- 二级联动 -->
 	<!-- <input id="com" name="dep" value="">
	<input id="cc3" class="easyui-combobox" data-options="valueField:'id',textField:'text'"> -->
	
	<!-- 数字框 -->
	<!-- <input type="text" class="easyui-numberbox" value="100" data-options="min:0,precision:2" groupSeparator=","> -->
	
	<!-- 日期和时间框 -->
	<!-- <div id="ee" class="easyui-calendar" style="width:180px;height:180px;"></div> -->

	<!-- <div id="calendar" style="width:180px;height:180px;"></div> -->
	
	<!-- <input id="ddtime" type="text"> -->

<!-- 	<div id="window" >
		定义的窗口
	</div> -->
	
	<div id="dialog">
		dialog内容
	</div>

</body>
</html>



