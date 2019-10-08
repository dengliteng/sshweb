$(function(){

	$("#dg").datagrid({
		title:"用户对象",
		url:"/sshweb/getAllLimtDatagrid",
		pagination:true,
		columns:[[
		        {field:'ck',checkbox:"true"},
		    {field:"id",title:"id",width:100},
		    {field:"username",title:"姓名",width:100,editor:{type:"textbox"}},
		    {field:"password",title:"密码",width:100,editor:{type:"textbox"}},
		    {field:"state",title:"状态",width:100,editor:{type:"numberbox"},formatter:function(value,row,index){
		    	if(value==1){
		    		return "启动状态"
		    	}else{
		    		return "停止状态"
		    	}
		    }},
		    {field:"regDate",title:"时间",width:150,editor:{type:"datebox"}},
		    {field:"height",title:"身高",width:100,editor:{
		    	type:"numberbox",
		    	options:{
		    		precision:2
		    	}}},
		    {field:"roles",title:"角色",width:100,formatter:function(value,row,index){
		    	var roleNameStr = "";
		    	if(value!=null && value.length>0) {
		    		roleNameStr = value[0].roleName;
		    	}
		    	return roleNameStr; 
		    },editor:{
		    	type:"combobox",
		    	options:{
		    		url:"/sshweb/getRoles",
			    	method:"post",
			    	valueField:"id",
			    	textField:"roleName",
			    	required:true,
			    	panelHeight:162
			    	}},
		    	}
		    	
		    ]],
		striped:true,
		checkbox:true,
		toolbar:"#tb",
		onClickRow:function(index){
			if(editIndex != index){
				$("#dg").datagrid("rejectChanges");
			}
			$("#dg").datagrid("beginEdit",index);
			$("#dg").datagrid("selectRow",index);
			editIndex = index;
		} 
	});
	
	var editIndex;
	$("#linkbutton").click(function(){
		var queryParams = $("#dg").datagrid("options").queryParams;
		queryParams.id = $("#numberbox").numberbox("getValue");
		queryParams.username = $("#textbox").textbox("getValue");
		$("#dg").datagrid("load");
	});
    var demo=$("#dg").datagrid('getPager');
    $(demo).pagination({
        pageList:[10,20,50],
        beforePageText:'第',
        afterPageText:'页   共{pages}页',
        displayMsg:'当前显示{from} - {to}条记录   共{total}条记录'
    });
    
    //保存修改
    $("#save_user").click(function(){
    	//根据editIndex拿到这个row对象
    	var row = $("#dg").datagrid("getRows")[editIndex];
    	//根据editIndex拿到行的编辑器,简单类型的字段,直接获取数据值,roles是Role对象列表,单独处理
    	var editor = $("#dg").datagrid("getEditor",{index:editIndex,field:"roles"});
    	var roleId = $(editor.target).combobox("getValue");
    	var roleText = $(editor.target).combobox("getText");
    	//让data接受修改
    	$("#dg").datagrid("acceptChanges");
    	
    	//通过ajax发出修改用户的请求
    	$.ajax({
    		url:"/sshweb/updatUser",
    		data:JSON.stringify({
    			id:row.id,
    			username:row.username,
    			password:row.password,
    			state:row.state,
    			regDate:row.regDate,
    			height:row.height,
    			roles:[{id:roleId,roleName:roleText}],
    		}),
    		method:"post",
    		contentType:"application/json;charset=utf-8",
    		success:function(rs){
    			if(rs=="success"){
    				editIndex = undefined;
    				$("#dg").datagrid("reload");
    			}else{
    				alert("加载失败")
    			}
    		}
    	})
    	
    });
    
    //取消修改
    $("#undo_user").click(function(){
    	$("#dg").datagrid("rejectChanges");
    	editIndex = undefined;
    })
});


