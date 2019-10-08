$(function(){
	
	$(".sshweb_side_tree li a").bind("click",function(){
		var title = $(this).text();
		var iconCls = $(this).attr("data-icon");
		var url = $(this).attr("data-link");
		var iframe = $(this).attr("iframe")==1?true:false;
		addTab(title,iconCls,url,iframe);
	});
	
});

function addTab(title,iconCls,url,iframe){
	var tabPanel = $("#sshweb_tabs");
	if(!tabPanel.tabs("exists",title)){
		var content = "<iframe scrolling='auto' frameborder='0' src='"+url+"' style='width:100%,height100%></iframe>'"
		if(iframe){
			tabPanel.tabs("add",{
				title:title,
				content:content,
				iconCls:iconCls,
				fit:true,
				closable:true
			});
		}else{
			tabPanel.tabs("add",{
				title:title,
				href:url,
				iconCls:iconCls,
				fit:true,
				closable:true
			});
		}
	}else{
		tabPanel.tabs("select",title)
	}
}



