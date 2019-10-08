$(function(){
	
	$("#tree").tree({
		animate:true,
		checkbox:true,
		url:"/sshweb/getAllTree",

		//编辑节点后触发。
		onAfterEdit:function(node){
			//把添加的节点信息，写入数据库，就是在这里实现的
			var nodeText = node.text;
			var parentNode = $("#tree").tree("getParent",node.target);
				
			if(parentNode != null){
				$.post("/sshweb/addNode",{
					text:nodeText,
					parentId:parentNode.id
				},function(newid){});
			}else{
				$.post("/sshweb/addNode",{
					text:nodeText,
				},function(newid){});
			}
			
		},
		//	当右键点击节点时触发
		onContextMenu:function(e,node){
			//右键点击的时候，发送ajax请求，获取数据库中最新的节点ID，赋值给最新的ID全部变量
			$.post("/sshweb/getNewId",{},function(newid){id=newid});
			
			//点击鼠标右键使浏览器的实效
			e.preventDefault();
			//点击右键绑定菜单
			$("#tree").tree("select",node.target);
			//让点击的鼠标显示
			$("#menu").menu("show",{
				left:e.pageX,
				top:e.pageY,
			});
		}
	})
	
	
});
var id;
//添加兄弟节点
function addBoread(){
	//获取tree的节点对象
	var getSelected = $("#tree").tree("getSelected");
	//新建一个节点对象
	var node = {
		after: getSelected.target,	
		data:{
		      	id:++id,
		      	text:''
			}
		};
	//把要创建的节点加进去
	$("#tree").tree("insert",node);
	var actNode = $("#tree").tree("find",id);
	$("#tree").tree("beginEdit",actNode.target);
}

//添加子节点
function add(){
	//获取tree的节点对象
	var getSelected = $("#tree").tree("getSelected");
	//新建一个节点对象
	var node = {
		parent: getSelected.target,	
		data:{
	      	id:++id,
	      	text:''
		}
	}
	//把要创建的节点加进去
	$("#tree").tree("append",node);
	var actNode = $("#tree").tree("find",id);
	$("#tree").tree("beginEdit",actNode.target);
}

//删除节点
function deleteTree(){
	var getSelected = $("#tree").tree("getSelected");
	$("#tree").tree("remove",getSelected.target);
	$.post("/sshweb/deleteNode",{id:getSelected.id},function(rs){})
}

//全选状态
function allchk(){
	var nodes = $("#tree").tree("getChecked","checked");
	var arr = new Array();
	for(var i=0;i<nodes.length;i++){
		arr[i] = nodes[i].text;
	}
	alert(arr.join(" , "))
	
}

//半选
function unchk(){
	var nodes = $("#tree").tree("getChecked","indeterminate");
	var arr = new Array();
	for(var i=0;i<nodes.length;i++){
		arr[i] = nodes[i].text;
	}
	alert(arr.join(" , "))
}

//没选
function nochk(){
	var nodes = $("#tree").tree("getChecked","unchecked");
	var arr = new Array();
	for(var i=0;i<nodes.length;i++){
		arr[i] = nodes[i].text;
	}
	alert(arr.join(" , "))
}


