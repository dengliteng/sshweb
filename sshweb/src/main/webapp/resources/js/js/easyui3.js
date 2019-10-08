$(function(){

//	$("#accordion").accordion({
//		multiple:true,
//		selected:1,
//	});

/*	$(document).bind("contextmenu",function(e){
		e.preventDefault();
		$("#mm").menu("show",{
			left:e.pageX,
			top:e.pageY,
			onClick:function(item){
				if(item.name=="new"){
					alert("new被点击了")
				}
			}
		})
	})*/
	
/*	$("#btn").linkbutton({
		iconCls:"icon-save",
	})
	
	$("#btn1").menubutton({
		menu:"#mm",
		
		
	});
	$("#mm").menu({
		onClick:function(item){
			if(item.name=="new"){
				alert("new")
			}
			if(item.name=="open file"){
				alert("open file")
			}
		}
	})*/
/*	$('#ff').form({
	    url:"/sshweb/panel",
	    onSubmit: function(){
			//var name = $("#name").val(); //获取文本输入的值
	    	//var name = $("#name").textbox("getValue");  //获取文本输入的值，easyui推荐使用
	    	//alert(name)
	    	//$("#name").textbox("setValue","admin");
	    	return $("ff").form("validate")
	    },
	    success:function(data){
	    	var objdata = eval('(' + data + ')');//讲string转js的object
			alert(objdata);
	    	alert(data);
	    }
	});
	
});
$.extend($.fn.validatebox.defaults.rules, {
	equals: {
		validator: function(value,param){
			return value == $(param[0]).val();
		},
		message: '前后输入的密码不一致.'
    },
    minlength: {
		validator: function(value,param){
			return value.length > param[0];
		},
		message: '输入的密码长度不足.'
    },*/
	
/*	组合框
  	$("#combo").combo();
	$("#sp").appendTo($("#combo").combo("panel"))
	$("#sp input").click(function(){
		var value = $(this).val()
		var text = $(this).next("span").text();
		$("#combo").combo("setValue",value).combo("setText",text).combo("hidePanel");
	})*/
	
/*	$("#com").combobox({
		url:"/sshweb/comboboxtext", //获取回来的json格式[{"id":1,"text":"xxx"},{},{}]
		valueField:"id",
	    textField:"text",
	    onSelect:function(rs){
	    	var url = "/sshweb/comboboxtext1?id="+rs.id;
	    	$("#cc3").combobox("reload",url)
	    }
	});*/
	
/*	$("#calendar").calendar({
		onSelect: function(date){
			alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
		}
	});*/
	
/*	$('#ddtime').datebox({
	    formatter : function(date){
	    	var y = date.getFullYear();
	    	var m = date.getMonth()+1;
	    	var d = date.getDate();
	    	return y + "年"+ m + "月" + d + "日";
	    },
	    parser : function(data){

	    	if(data == ""||data == null) return new Data();
	    	var y = parseInt(data.substring(0,data.indexOf("年"),10));
	    	var m = parseInt(data.substring(data.indexOf("年")+1,data.indexOf("月"),10));
	    	var d = parseInt(data.substring(data.indexOf("月")+1,data.indexOf("日"),10));
	    	if(!isNaN(y) && !isNaN(m) && isNaN(d)){
	    		return new Data(y,m-1,d);
	    	}else{
	    		return new Date();
	    	}
	    },		
	});
	
	$("#ddtime").datebox().datebox("calendar").calendar({
		validator:function(date){
			var now = new Date();
			return date < now;
		}
	});*/
	
/*	$("#window").window({
		
		title:"widow窗口"
	});*/
	
	$("#dialog").dialog({
		title:"dialog窗口",
		minimizable:true,
		maximizable:true,
		toolbar:[{
			text:'Edit',
			iconCls:'icon-edit',
			handler:function(){alert('edit被点击了')}
		}],
		buttons:[{
			text:'Save',
			iconCls:"icon-ok",
			handler:function(){
				alert("ok被点击了")
			}
		}]
	});
	

	
	
/*	$('#ee').calendar({
		onSelect: function(date){  //获取点击的日期
		alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
		}
		});*/
	
});



