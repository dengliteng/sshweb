<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<div class="easyui-layout">
		
		<div id="usersToolbar">
			<div class="usersToolbar_button">
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="users_openAdd()">添加</a>
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="users_openEdit()">修改</a>
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="users_remove()">删除</a>
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="users_cancel()">取消</a>
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="users_reload()">刷新</a>
		</div>
		<div>
			<label>用户名:</label><input class="easyui-textbox" id="datagridButUser" style="width:160px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-seae" id="datagridBut">开始搜索</a>
		</div>
		</div>	
		<table id="datagrid"></table>		
	</div>
	<div id="user_dialog" class="easyui-dialog" title="添加用户" style="width:400px"
    data-options="iconCls:'icon-save',closed:true">
		<form id="user_dialog_form" method="post">
			<table>
				<tr>
					<td width="100" align="right">用 户 名:<input type="hidden" id="id" name="id"></td>
					<td><input type="text" id="username" name="username" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td width="100" align="right">用户密码:</td>
					<td><input type="text" id="password" name="password" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td width="100" align="right">用户状态:</td>
					<td><input type="text" id="state" name="state" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td width="100" align="right">注册日期:</td>
					<td><input type="text" id="regDate" name="regDate" class="easyui-datebox"></td>
				</tr>
				<tr>
					<td width="100" align="right">用户权限:</td>
					<td><input type="text" id="roles" name="roles" class="easyui-combotree"  data-options="url:'/sshweb/getAllRole',required:true,multiple:true"></td>
				</tr>
			</table>
		</form>
	</div>
	
	<script type="text/javascript">
		$(function(){
			
			$("#datagridBut").bind("click",function(){
				var limitUser = $("#datagrid").datagrid("options").queryParams;
				limitUser.username = $("#datagridButUser").textbox("getValue");
				$("#datagrid").datagrid("load")
			})
			
			$("#datagrid").datagrid({
				 url:"/sshweb/getAllLimtDatagrid",
				 pagination:true,
				 toolbar:"#usersToolbar",
				    columns:[[
						{field:'ck',checkbox:"true"},  
						{field:"id",title:"用户编号",width:100},
						{field:'username',title:'用户名',width:100},
						{field:'password',title:'用户密码'},
						{field:'state',title:'用户状态',width:100,formatter:function(value,row,index){
							if(value==1){
								return "启动状态";
							}else{
								return "停止状态";
							}
						}},
						{field:'regDate',title:'注册日期',width:100},
						{field:'roles',title:'用户角色',width:500,formatter:function(value,row,index){
							var roleNameStr = "";
							if(value!=null&&value.length>0){
								for(var i=0;i<value.length;i++){
									roleNameStr += value[i].roleName+",";
								}
								return roleNameStr;
							}
						}},
				    ]]
			})
		})
		
		/* 用户添加 */
		function users_openAdd(){
			$("#user_dialog_form").form("clear");
			$("#user_dialog").dialog({
				closed:false,
				modal:true,
				onOpen:function(){
					$("#username").textbox({disabled:false})
				},
				buttons:[
			      {text:"确定",iconCls:"icon-ok",
			    	//第一种数据提交的时候,后台通过对象的封装来传值
						//前台显示的数据 1,2,3  ["id:"1]
			    	handler:addUser
	
						  
			    	 
	//第二中数据提交的时候后台通过属性传值的方法
			    	 //实现数据的添加
//			    	  $('#user_dialog_form').form('submit', {
//			    		    url:"/sshweb/addUser",
//			    		    success:function(data){
//			    				if(data=="ok"){
//			    					$("#user_dialog").dialog("close");
//			    					$("#datagrid").datagrid("reload");
//			    				}else{
//			    					$.messager.alert("信息提示","提交失败","info");
//			    				}
//			    		    }
//			    		}); 
			    	  
			      },
			      {text:"取消",iconCls:"icon-cancel",
			      handler:function(){
			    	  //实现数据的取消
			    	  $("#user_dialog").dialog("close");
			      }}
				]
			})
		}
		
		
		//用户修改 
		function users_openEdit(){
			
			$("#user_dialog_form").form("clear");
			var edit = $("#datagrid").datagrid("getSelections");
			if(edit.length>1){
				$.messager.alert("信息提示","每次修改只能选中一行","info");
			}else if(edit.length==0){
				$.messager.alert("信息提示","每次修改最少选中一行","info");	
			}else{
				
				var edit_user = edit[0];
				edit_user.password = "";
				$("#user_dialog_form").form("load",edit_user);
				$("#user_dialog").dialog({
					closed:false,
					modal:true,
					onOpen:function(){
						$("#username").textbox({disabled:true})
					},
					buttons:[
				      {text:"确定",iconCls:"icon-ok",
				      handler:updateUser},
				      {text:"取消",iconCls:"icon-cancel",
				      handler:function(){
				    	  //实现数据的取消
				    	  $("#user_dialog").dialog("close");
				      }}
					]
				})
			}
			
		}
		
		function addUser(){
			doAjax("/sshweb/addUser_1")
		}
		
		function updateUser(){
			doAjax("/sshweb/updateUser")
		}
		
		function doAjax(param){
			var roleAll = $("#roles").val().split(",");
			var roleAllId = "[";
			for(var i=0;i<roleAll.length;i++){
				roleAllId += "{id:"+roleAll[i]+"},";
			}				
			roleAllId = roleAllId.substring(0,roleAllId.length-1)+"]";
			var roleObject = eval("("+roleAllId+")")					
	    	$.ajax({
	    		url:param,
	    		method:"post",
	    		contentType:"application/json;charset=utf-8",
	    		data:JSON.stringify({
	    			id:$("#id").val(),
	    			username:$("#username").val(),
	    			password:$("#password").val(),
	    			state:$("#state").val(),
	    			regDate:$("#regDate").val(),
	    			roles:roleObject,
	    		}),
	    		success:function(data){
	    			if(data=="ok"){
	    				$("#user_dialog").dialog("close");
    					$("#datagrid").datagrid("reload");
	    			}else{
	    					$.messager.alert("信息提示","提交失败","info");
	    				}
	    		}	
	    	})	  
		}
		
		//用户删除
		function users_remove(){
			var edit = $("#datagrid").datagrid("getSelections");
			if(edit.length<1){
				$.messager.alert("信息提示","请选中要删除的行","info");
			}else{
				$.messager.confirm("信息提示","确定要删除选中的记录吗?",function(result){
					if(result){
						//拿到选中的没一行,返回一个数组
						
						var ids = [];
						$(edit).each(function(){
							ids.push(this.id)
						})
						
						$.ajax({
							url:"/sshweb/deleteUser",
							data:{ids:ids},
							method:"post",
							success:function(data){
								if(data=="ok"){
				    				$("#user_dialog").dialog("close");
									$("#datagrid").datagrid("reload");
				    			}else{
				    					$.messager.alert("信息提示","提交失败","info");
				    				}
							} 
						});
					}
				})
			}			
		}
		
		
		//取消用户选择 
		function users_cancel(){
			$("#datagrid").datagrid("rejectChanges");
		}
		
		
		//刷新用户选择
		function users_reload(){
			$("#datagrid").datagrid("reload");
		}
		
	</script>
</body>
</html>