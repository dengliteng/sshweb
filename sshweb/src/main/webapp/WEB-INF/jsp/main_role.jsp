<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/easyui/themes/icon.css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jq/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/local/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<div class="easyui-layout">
		
		<div id="rolesToolbar">
			<div class="usersToolbar_button">
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="roles_openAdd()">添加</a>
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="roles_openEdit()">修改</a>
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="roles_remove()">删除</a>
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="roles_cancel()">取消</a>
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="roles_reload()">刷新</a>
		</div>
		</div>	
		<table id="roleDatagrid"></table>		
	</div>
 	<div id="role_dialog" class="easyui-dialog" title="添加用户" style="width:400px"
    data-options="iconCls:'icon-save',closed:true">
		<form id="role_dialog_form" method="post">
			<table>
				<tr>
					<td width="100" align="right">角 色 名:<input type="hidden" id="id" name="id"></td>
					<td><input type="text" id="rolename" name="rolename" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td width="100" align="right">角色状态:</td>
					<td><input type="text" id="state" name="state" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td width="100" align="right">角色权限:</td>
					<td><input type="text" id="permissions" name="permissions" class="easyui-combotree"  data-options="url:'/sshweb/getPagerRoles',required:true,multiple:true"></td>
				</tr>
			</table>
		</form>
	</div> 
	
	 <script type="text/javascript">
		$(function(){			
			$("#roleDatagrid").datagrid({
				 url:"/sshweb/getAllLimtPermission",
				 pagination:true,
				 toolbar:"#rolesToolbar",
				    columns:[[
						{field:'ck',checkbox:"true"},  
						{field:"id",title:"角色编号",width:100},
						{field:'roleName',title:'角色名',width:100},
						{field:'state',title:'角色状态',width:100,formatter:function(value,row,index){
							if(value==1){
								return "启动状态";
							}else{
								return "停止状态";
							}
						}},
						{field:'permissions',title:'角色权限',formatter:function(value,row,index){
							
							var permissionsStr = "";
							if(value!=null&&value.length>0){
								for(var i=0;i<value.length;i++){
									permissionsStr += value[i].resource+",";
								}
								return permissionsStr;
							}
						}},
				    ]]
			})
		})
		
		/* 用户添加 */
		function roles_openAdd(){
			$("#role_dialog_form").form("clear");
			$("#role_dialog").dialog({
				closed:false,
				modal:true,
				buttons:[
			      {text:"确定",iconCls:"icon-ok",
			    	//第一种数据提交的时候,后台通过对象的封装来传值
						//前台显示的数据 1,2,3  ["id:"1]
			    	handler:addRole		    	  
			      },
			      {text:"取消",iconCls:"icon-cancel",
			      handler:function(){
			    	  //实现数据的取消
			    	  $("#role_dialog").dialog("close");
			      }}
				]
			})
		}
		
		
		//用户修改 
		function roles_openEdit(){
			$("#role_dialog_form").form("clear");
			var edit = $("#roleDatagrid").datagrid("getSelections");
			if(edit.length>1){
				$.messager.alert("信息提示","每次修改只能选中一行","info");
			}else if(edit.length==0){
				$.messager.alert("信息提示","每次修改最少选中一行","info");	
			}else{
				var edit_user = edit[0];
				$("#role_dialog_form").form("load",edit_user);
				$("#role_dialog").dialog({
					closed:false,
					modal:true,
					buttons:[
				      {text:"确定",iconCls:"icon-ok",
				      handler:updaterole},
				      {text:"取消",iconCls:"icon-cancel",
				      handler:function(){
				    	  //实现数据的取消
				    	  $("#role_dialog").dialog("close");
				      }}
					]
				})
			}
			
		}
		
		function addRole(){
			doAjax("/sshweb/addPrgerRoles")
		}
		
		function updaterole(){
			doAjax("/sshweb/updatePrgerRoles")
		}
		
		function doAjax(param){
			var roleAll = $("#permissions").val().split(",");
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
	    			roleName:$("#rolename").val(),
	    			state:$("#state").val(),
	    			permissions:roleObject,
	    		}),
	    		success:function(data){
	    			if(data=="ok"){
	    				$("#role_dialog").dialog("close");
    					$("#roleDatagrid").datagrid("reload");
	    			}else{
	    					$.messager.alert("信息提示","提交失败","info");
	    				}
	    		}	
	    	})	  
		}
		
		//用户删除
		function roles_remove(){
			var edit = $("#roleDatagrid").datagrid("getSelections");
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
							url:"/sshweb/deletePrgerRoles",
							data:{ids:ids},
							method:"post",
							success:function(data){
								if(data=="ok"){
				    				$("#role_dialog").dialog("close");
									$("#roleDatagrid").datagrid("reload");
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
		function roles_cancel(){
			$("#roleDatagrid").datagrid("rejectChanges");
		}
		
		
		//刷新用户选择
		function roles_reload(){
			$("#roleDatagrid").datagrid("reload");
		}
		
	</script> 
</body>
</html>