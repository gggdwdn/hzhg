define(function(require, exports, module){
	require("../thirdparty/iviewerimg/jquery.iviewer.css");
	require("../thirdparty/iviewerimg/jquery-ui.min");
	require("../thirdparty/iviewerimg/jquery.mousewheel.min");
	require("../thirdparty/iviewerimg/jquery.iviewer");	
	Aptech.iviewer = function(config){
		this._options =config;
	}
	Aptech.iviewer.prototype = {
		render:function(){
			var getSrc = this._options.url;
			if(getSrc.length>0){
				var getLength = getSrc.length;
				$('body').append('<div class="viewerBox"  style="position:absolute;top:0; left:0;width:100%; height:100%; background:rgba(0,0,0,0.7); z-index:9999;overflow:hidden;"></div>');
				$('.viewerBox').append('<i  id="closeSelfId" style="font-size: 50px;color: #fff;float: right;margin-right: 10px;margin-top: 10px;overflow:hidden"></i>');
				for(var i=0;i<getSrc.length;i++){
					var getId = "viewer1_"+i;
					var getLocalId = '#'+getId;
					var getName = "viewName_"+i;
					
					$('.viewerBox').append('<div id="'+getId+'" class="viewer" style="width:100%;height:100%;"></div>');
					$(getLocalId).append('<span title="上一张" class="iviewerArrow iviewerArrowLeft" aria-hidden="true" style="display:none" onclick="checkIviewerLeftSpan(this)" data-index="'+i+'" data-number="'+getLength+'"></span>')
					$(getLocalId).append('<span title="下一张" class="iviewerArrow iviewerArrowRight" aria-hidden="true" style="display:none" onclick="checkIviewerRightSpan(this)" data-index="'+i+'" data-number="'+getLength+'"></span>')
					$(getLocalId).append('<div class="indexPageDiv" style="position: absolute;bottom: 10px;top: auto;padding:0 10px;height: 28px;line-height: 28px;left:360px;background:#fff;font-size: 15px;"><span class="indexPageNum">1</span>/<span>'+getLength+'</span></div>')	
					getName = $(getLocalId).iviewer({
		                src:getSrc[i],
		                update_on_resize: false,
		                zoom_animation: false,
		                mousewheel: true,
		                onMouseMove: function(ev, coords) { },
		                onStartDrag: function(ev, coords) { return false; }, //this image will not be dragged
		                onDrag: function(ev, coords) { }
		           });
					$(getLocalId+' img').draggable();
		            $("#in").click(function(){ getName.iviewer('zoom_by', 1); });
		            $("#out").click(function(){ getName.iviewer('zoom_by', -1); });
		            $("#fit").click(function(){ getName.iviewer('fit'); });
		            $("#orig").click(function(){ getName.iviewer('set_zoom', 100); });
		            $("#update").click(function(){ getName.iviewer('update_container_info'); });
				}
				if(getLength>1){
					$('.iviewerArrow').show();
					for(var i=0;i<getLength;i++){
						var getId = "#viewer1_"+i;
						$(getId).hide();
					}
					$('#viewer1_0').show();
				}else{
					$('.indexPageDiv').css('left','240px');
				}
				$('body').on('click','#closeSelfId',function(){
					$('.viewerBox').remove();
				})
			}
			
      	},
	}
	return Aptech;
})
//左边箭头
function checkIviewerLeftSpan(self){
	var getIndex = $(self).attr('data-index');
	var getThisNum = '';//记录当前是第几张图片
	var getNum = parseInt($(self).attr('data-number'));
	if(getNum>1&&getIndex>0){
		for(var i=0;i<getNum;i++){
			var getId = "#viewer1_"+i;
			$(getId).hide();
		}
		getThisNum = parseInt(getIndex);
		getIndex--;
		var getNowDiv ='#viewer1_' +getIndex;
		$(getNowDiv).show();
	}else if(getNum>1&&getIndex<=0){//如果是第一张从转到最后一张
		for(var i=0;i<getNum;i++){
			var getId = "#viewer1_"+i;
			$(getId).hide();
		}
		getIndex = getNum-1;
		getThisNum = getNum;
		var getNowDiv ='#viewer1_' +getIndex;
		$(getNowDiv).show();
	}
	$('.indexPageNum').text(getThisNum);
}
function checkIviewerRightSpan(self){
	var getIndex = $(self).attr('data-index');
	var getThisNum = getIndex;
	var getNum = parseInt($(self).attr('data-number'));
	if(getNum>1&&getIndex<getNum-1){
		for(var i=0;i<getNum;i++){
			var getId = "#viewer1_"+i;
			$(getId).hide();
		}
		getIndex++;
		getThisNum = parseInt(getIndex)+1;
		var getNowDiv ='#viewer1_' +getIndex;
		$(getNowDiv).show();
	}else if(getNum>1&&getIndex>=getNum-1){
		for(var i=0;i<getNum;i++){
			var getId = "#viewer1_"+i;
			$(getId).hide();
		}
		getIndex = 0;
		getThisNum = getIndex+1;
		var getNowDiv ='#viewer1_' +getIndex;
		$(getNowDiv).show();
	}
	$('.indexPageNum').text(getThisNum);
}