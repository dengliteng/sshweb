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
		
		<div id="permissionsToolbar">
			<div class="usersToolbar_button">
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="permissions_openAdd()">添加</a>
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="permissions_openEdit()">修改</a>
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="permissions_remove()">删除</a>
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="permissions_cancel()">取消</a>
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="permissions_reload()">刷新</a>
		</div>
		</div>	
		<table id="permissionDatagrid"></table>		
	</div>
 	<div id="permission_dialog" class="easyui-dialog" title="添加用户" style="width:400px"
    data-options="iconCls:'icon-save',closed:true">
		<form id="permission_dialog_form" method="post">
			<table>
				<tr>
					<td width="100" align="right">任务 名:<input type="hidden" id="id" name="id"></td>
					<td><input type="text" id="resource" name="resource" class="easyui-textbox"></td>
				</tr>
				<tr>
					<td width="100" align="right">任务状态:</td>
					<td><input type="text" id="state" name="state" class="easyui-textbox"></td>
				</tr>
			</table>
		</form>
	</div> 
	
	 <script type="text/javascript">
		$(function(){			
			$("#permissionDatagrid").datagrid({
				 url:"/sshweb/getAllPermission",
				 pagination:true,
				 toolbar:"#permissionsToolbar",
				    columns:[[
						{field:'ck',checkbox:"true"},  
						{field:"id",title:"任务编号",width:100},
						{field:'resource',title:'任务名',width:100},
						{field:'state',title:'任务状态',width:100,formatter:function(value,row,index){
							if(value==1){
								return "启动状态";
							}else{
								return "停止状态";
							}
						}},	
				    ]]
			})
		})
		
		/* 用户添加 */
		function permissions_openAdd(){
			$("#permission_dialog_form").form("clear");
			$("#permission_dialog").dialog({
				closed:false,
				modal:true,
				buttons:[
			      {text:"确定",iconCls:"icon-ok",
			    	//第一种数据提交的时候,后台通过对象的封装来传值
						//前台显示的数据 1,2,3  ["id:"1]
			    	handler:addpermission		    	  
			      },
			      {text:"取消",iconCls:"icon-cancel",
			      handler:function(){
			    	  //实现数据的取消
			    	  $("#permission_dialog").dialog("close");
			      }}
				]
			})
		}
		
		
		//用户修改 
		function permissions_openEdit(){
			$("#permission_dialog_form").form("clear");
			var edit = $("#permissionDatagrid").datagrid("getSelections");
			if(edit.length>1){
				$.messager.alert("信息提示","每次修改只能选中一行","info");
			}else if(edit.length==0){
				$.messager.alert("信息提示","每次修改最少选中一行","info");	
			}else{
				var edit_user = edit[0];
				$("#permission_dialog_form").form("load",edit_user);
				$("#permission_dialog").dialog({
					closed:false,
					modal:true,
					buttons:[
				      {text:"确定",iconCls:"icon-ok",
				      handler:updatepermission},
				      {text:"取消",iconCls:"icon-cancel",
				      handler:function(){
				    	  //实现数据的取消
				    	  $("#permission_dialog").dialog("close");
				      }}
					]
				})
			}
			
		}
		
		function addpermission(){
			doAjax("/sshweb/addPermissions")
		}
		
		function updatepermission(){
			doAjax("/sshweb/updatePermissions")
		}
		
		function doAjax(param){			
	    	$.ajax({
	    		url:param,
	    		method:"post",
	    		contentType:"application/json;charset=utf-8",
	    		data:JSON.stringify({
	    			id:$("#id").val(),
	    			resource:$("#resource").val(),
	    			state:$("#state").val(),
	    		}),
	    		success:function(data){
	    			if(data=="ok"){
	    				$("#permission_dialog").dialog("close");
    					$("#permissionDatagrid").datagrid("reload");
	    			}else{
	    					$.messager.alert("信息提示","提交失败","info");
	    				}
	    		}	
	    	})	  
		}
		
		//用户删除
		function permissions_remove(){
			var edit = $("#permissionDatagrid").datagrid("getSelections");
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
							url:"/sshweb/deletePermissions",
							data:{ids:ids},
							method:"post",
							success:function(data){
								if(data=="ok"){
				    				$("#permission_dialog").dialog("close");
									$("#permissionDatagrid").datagrid("reload");
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
		function permissions_cancel(){
			$("#permissionDatagrid").datagrid("rejectChanges");
		}
		
		
		//刷新用户选择
		function permissions_reload(){
			$("#permissionDatagrid").datagrid("reload");
		}
		
	</script> 
</body>
</html>