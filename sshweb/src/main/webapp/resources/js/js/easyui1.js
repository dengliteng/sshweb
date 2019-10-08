$(function(){
	/*	$("#d1").draggable({
		handle:"#d2",
		proxy:"clone",
	});
	 */

	/*	$("#div2").draggable({
		onDrag:function(event){
			//通过event拿到拖动的html元素节点对象
			var div = event.data;
			console.log(div);
			if(div.left<0){
				div.left = 0;
			}
			if(div.top<0){
				div.top = 0;
			}
			if(div.left + $(div.target).innerWidth() > $(div.parent).innerWidth()){
				div.left = $(div.parent).innerWidth() - $(div.target).innerWidth();
			}
			if(div.top + $(div.target).innerHeight() > $(div.parent).innerHeight()){
			div.top = $(div.parent).innerHeight() - $(div.target).innerHeight();
			}
		}
	});*/

	/*	$(".cla").draggable({
		proxy:"clone",
		revert:true,
	});
	$("#t2").droppable({
		accept:"#cla1,#cla3",
		onDrop:function(e,source){
			$(this).append(source)
		},
		onDragEnter:function(e,source){
			$(source).draggable("options").cursor="pointer"
			$(source).draggable("proxy").css("background-color","yellow");
		}
	})*/
	var arrow = $("<div class='arrows'>&gt&gt&gt<div>").appendTo("body");
	$(".dragg").draggable({
		revert:true,
	}).droppable({
		onDragOver:function(e,source){
			arrow.css({
				display:"block",
				left:$(this).offset().left-20,
				top:$(this).offset().top+$(this).outerHeight()-15,
			});
		},
		onDragLeave:function(e,source){
			arrow.hide();
		},
		onDrop:function(e,source){
			$(source).insertAfter(this);
			arrow.hide();
		}
	});

});




