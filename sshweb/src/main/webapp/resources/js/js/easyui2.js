$(function(){
/*	$("#rr").resizable({
		maxWidth:500,
		maxHeight:500
	});*/
	
	
//	$("#searct").searchbox({
//		menu:("#car"),
//		searcher:function(name,value){
//			alert(value+","+name)
//		}
//	});
	
/*	$("#bar").progressbar({
		value:2
	});	*/

/*	$("#pagination").pagination({
		links:10,
		total:100,
		beforePageText:"第",
		afterPageText:"页",
		displayMsg:"第 {from}页 {to}  {total}"
	});*/
	
/*	$("#panel").panel({
		width:500,
		height:200,
		title:"面板测试",
		iconCls:"icon-save",
		minimizable:true,
		maximizable:true,
		closable:true,
		collapsible:true,
	});*/
	
	$("#tabs").tabs({
		onSelect:function(title){
			$("#tabs").tabs("getTab","tabs2").panel({
				href:"../sshweb/panel",}	
			);
		},
		tools:[{
			iconCls:'icon-add',
			handler:function(){
				$("#tabs").tabs("add",{
					title:"new tabs",
					content:"aaabbb",
					closable:true,
					selected:true,
					cls:"tab",
					tools:[
					       {
						iconCls:"icon-mini-refresh",
						handler:function(){
							var tab = $("#tabs").tabs("getSelected");
							tab.panel("refresh","../sshweb/panel");
						}
					}
					]
				});
			}
		},{
			iconCls:'icon-remove',
			handler:function(){
				var tab = $("#tabs").tabs("getSelected");
				var index = $("#tabs").tabs("getTabIndex",tab);
				$("#tabs").tabs("close",index);
			}
		}]
	});
	
});
/*function add(){
	var value = $("#bar").progressbar("getValue");
	
	$("#bar").progressbar("setValue",value+3)
}
*/


