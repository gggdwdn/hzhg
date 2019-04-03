<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tag/Purview.tld" prefix="p"%> 
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="UTF-8">
		<%@ include file="/WEB-INF/views/common/meta.jsp" %>
	</head>
<body>
<div class="main-container ace-save-state firstPageDiv" id="main-container">
		<script type="text/javascript">
	        try{ace.settings.loadState('main-container')}catch(e){}
		</script>
		<div class="main-content">
			<div class="main-content-inner">
				<%-- <div class='advertising'>
					<div id='newBack'>
						<span><span class='glyphicon glyphicon-bullhorn' style='margin-right:10px;'></span>
						<c:if test="${indexEntity.isUrl=='0'}"><a href="${indexEntity.newsUrl}" style="text-decoration:underline;color:#159fae;" target="_blank">${indexEntity.news }</a></c:if>
						<c:if test="${indexEntity.isUrl=='1'}">${indexEntity.news }</c:if>
						</span>
						<span style="margin:0 80px"><span class='glyphicon glyphicon-bullhorn' style='margin-right:10px;'></span>
						<c:if test="${indexEntity.isUrl=='0'}"><a href="${indexEntity.newsUrl}" style="text-decoration:underline;color:#159fae;" target="_blank">${indexEntity.news }</a></c:if>
						<c:if test="${indexEntity.isUrl=='1'}">${indexEntity.news }</c:if>
						</span>
					</div>
				</div> --%>
				<div class="page-content index">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div class="invisible" id="main-widget-container">
						   </div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div>
		</div><!-- /.main-content -->
	</div><!-- /.main-container -->
</div>
	<script type="text/javascript">
// 		var setJsonArr = [{row:1,column:0,cospan:2,render:'render1',title:'待办任务',type:'haveBackgroundTable',url:"/firstPage/todoTaskTable",more_url:"/todoTask/list/1/10"},
// 		                  {row:0,column:0,cospan:2,render:'render3',title:'',type:'slider',arr:['../static/images/headImg/imgHead3.jpg','../static/images/headImg/imgHead1.jpg','../static/images/headImg/imgHead2.jpg']},
// 		                  {row:1,column:2,cospan:1,render:'render4',title:'消息',type:'noBackgroundTable',url:'/firstPage/messageCenterTable',more_url:"/messageCenter/index",headIcon:'glyphicon glyphicon-comment'},
// 		                  {row:2,column:2,cospan:1,render:'render5',title:'消缺类型占比',type:'echartTable',url:'/firstPage/findSolveScaleEchart'},
// 		                  {row:2,column:0,cospan:1,render:'render7',title:'发电量统计',type:'echartTable',url:'/firstPage/findEnergyCountEchart'} ,
// 		                  {row:0,column:2,cospan:1,render:'render2',title:'公告',type:'noBackgroundTable',url:'/firstPage/noticeTable',more_url:"/notice/index",headIcon:"glyphicon glyphicon-bullhorn"},
//  		                  {row:2,column:1,cospan:1,render:'render6',title:'各单位消缺率',type:'echartTable',url:'/firstPage/findScaleEchart'}]; 
// 		                  {row:2,column:1,cospan:1,render:'render6',title:'',type:'calendar',url:'/firstPage/firstPageCalendar'}]; 
		                  var setJsonArr = ${showList};
	    jQuery(function($){
	    	seajs.use(['datatables','confirm','dialog'], function(A){
	    	      $('#newBack').liMarquee();
	    		var widgets = ace.data.get('demo', 'widget-state', true);
	            if(widgets != null) {
	                for(var id in widgets) if(widgets.hasOwnProperty(id)) {
	                    var state = widgets[id];
	                    var widget = $('#'+id);
	                    if
	                    (
	                        (state == 'shown' && widget.hasClass('collapsed'))
	                        ||
	                        (state == 'hidden' && !widget.hasClass('collapsed'))
	                    )
	                    {
	                        widget.widget_box('toggleFast');
	                    }
	                    else if(state == 'closed') {
	                        widget.widget_box('closeFast');
	                    }
	                }
	            }
	            $('#main-widget-container').removeClass('invisible');//显示可拖拽框的基础
	            
	    		 setFirstPageContent(setJsonArr);
		    	 function setFirstPageContent(jsonArr){
		    		 var jsonNewArr = [];//将数组按照顺序进行重新排列
		    		 for(var i=0;i<jsonArr.length;i++){
		    			 jsonNewArr=jsonNewArr.concat(jsonArr[i]);
		    			 var rowNum = i+1;
		    			 $('#main-widget-container').append('<div class="col-xs-12 outRowBox-'+rowNum+' newPadding"></div>');
		    		 }
		    		 for(var i=0;i<jsonNewArr.length;i++){
		    			var getRow = '.outRowBox-'+jsonNewArr[i].row;//模块所在的行
		             	var getList = 4*jsonNewArr[i].cospan;//获取模块站比
		             	var getListStr  = 'col-xs-'+getList;
		             	//获取外部的div
		             	var getOutBox = '<div class="row '+getListStr+'"><div class="widget-container-col"><div class="widget-box"><div class="widgetContent widget-header  padding-zero '+jsonNewArr[i].render+'"><img src="../static/images/login.gif" class="loginClass"></div></div></div>';	
		             	//公告，消息等无背景色的table
			            if(jsonNewArr[i].type == 'noBackgroundTable'){
			            	$(getRow).append(getOutBox);
			            	var tableBox = '<div class="headDiv"><label  class="'+jsonNewArr[i].headIcon+'" aria-hidden="true"></label><span class="leftHeadSpan">'+jsonNewArr[i].title+'</span><a  class="rightHeadSpan moreTableInformation" data-url="'+jsonNewArr[i].more_url+'">更多&nbsp;&gt;</a></div><div class="rowHeigth paddingTable"id="'+jsonNewArr[i].render+'"></div>';
			            
			            	var getIdName = '.'+jsonNewArr[i].render;
		            		$(getIdName).append(tableBox);
			            	var getIframe = '#'+jsonNewArr[i].render;
			            	var setSrc = format_url(jsonNewArr[i].url+"?render="+jsonNewArr[i].render);
			            	A.loadPage({
			    				render : getIframe,
			    				url : setSrc
			    			});
			            }
			          	//有斑马条纹背景颜色的table;
			            if(jsonNewArr[i].type == 'haveBackgroundTable'&&jsonNewArr[i].title!="待办任务"){
			            	$(getRow).append(getOutBox);
			            	var tableBox='<div class="headDiv"><label  class="'+jsonNewArr[i].headIcon+'" aria-hidden="true"></label><span class="leftHeadSpan">'+jsonNewArr[i].title+'</span><a  class="rightHeadSpan moreTableInformation"  data-url="'+jsonNewArr[i].more_url+'">更多&nbsp;&gt;</a></div><div class="rowHeigth" style="overflow-x:hidden" id="'+jsonNewArr[i].render+'"></div>'
			            	var getIdName = '.'+jsonNewArr[i].render;
			            	
		            		$(getIdName).append(tableBox);
			            	var getIframe = '#'+jsonNewArr[i].render;
			            	var setSrc = format_url(jsonNewArr[i].url);
			            	A.loadPage({
			    				render : getIframe,
			    				url : setSrc
			    			});
			            }
			            //待办任务+消息
			            if(jsonNewArr[i].type == 'haveBackgroundTable'&&jsonNewArr[i].title=="待办任务"){
			            	getOutBox = '<div class="row '+getListStr+'"><div class="widget-container-col" style="display:inline-block;width:76%;"><div class="widget-box"><div class="widgetContent widget-header  padding-zero '+jsonNewArr[i].render+'"><img src="../static/images/login.gif" class="loginClass"></div></div></div><div class="waitingWorkMsg"></div>';
			            	$(getRow).append(getOutBox);
			            	
			            	var out_ul = '<ul class="ulBox">'+
			            	'<li class="msg_li" id="topMsgLi"><div><i class="ace-icon icon iconfont icon-yidu"></i><span>当月已读消息</span><strong ></strong><span class="times">次</span></div></li>'+
			            	'<li class="msg_li" id="centerMsgLi"><div><i class="ace-icon icon iconfont icon-banli"></i><span>当月办理业务</span><strong></strong><span class="times">次</span></div></li>'+
			            	'<li class="msg_li" id="bottomMsgLi"><div><i class="icon iconfont icon-wancheng"></i><span>当月任务完成单</span><strong></strong><span class="times">次</span></div></li>'+'</ul>';		
			            	$('.waitingWorkMsg').append(out_ul);
			            	var tableBox='<div class="headDiv"><label  class="glyphicon glyphicon-check" aria-hidden="true"></label><span class="leftHeadSpan">'+jsonNewArr[i].title+'</span><a  class="rightHeadSpan moreTableInformation"  data-url="'+jsonNewArr[i].more_url+'">更多&nbsp;&gt;</a></div><div class="rowHeigth" style="overflow-x:hidden" id="'+jsonNewArr[i].render+'"></div>'
			            	var getIdName = '.'+jsonNewArr[i].render;
		            		$(getIdName).append(tableBox);
			            	var getIframe = '#'+jsonNewArr[i].render;
			            	var setSrc = format_url(jsonNewArr[i].url);
			            	//消息请求的接口
			            	$.ajax({
								url : format_url('/messageCenter/getMessageNum'),
								contentType : 'application/json',
								dataType : 'JSON',
								type : 'POST',
								success: function(result){
									var messageNum = result.data;
									$("#topMsgLi").find("strong").html(messageNum);
								},
								error:function(v,n){
									alert('操作失败');
								}
							});
			            	//已办业务的数量的接口
			            	$.ajax({
								url : format_url('/wf/finishTask/getFinishTaskNum'),
								contentType : 'application/json',
								dataType : 'JSON',
								type : 'POST',
								success: function(result){
									var finishTaskNum = result.data;
									$("#centerMsgLi").find("strong").html(finishTaskNum);
								},
								error:function(v,n){
									alert('操作失败');
								}
							});
			            	//已办任务完成单
			            	$.ajax({
								url : format_url('/taskManagement/getTaskManagerNum'),
								contentType : 'application/json',
								dataType : 'JSON',
								type : 'POST',
								success: function(result){
									var taskManagerNum = result.data;
									$("#bottomMsgLi").find("strong").html(taskManagerNum);
								},
								error:function(v,n){
									alert('操作失败');
								}
							});
			            	A.loadPage({
			    				render : getIframe,
			    				url : setSrc
			    			});
			            }
			            //只有外框
			            if(jsonNewArr[i].type == 'noHeaderBox'){
			            	getOutBox = '<div class="row '+getListStr+'"><div class="widget-container-col"><div class="widget-box"><div class="widgetContent widget-header  padding-zero '+jsonNewArr[i].render+'"  id="'+jsonNewArr[i].render+'"></div></div></div>';
			            	$(getRow).append(getOutBox);
			            	var getIframe = '#'+jsonNewArr[i].render;
			            	var setSrc = format_url(jsonNewArr[i].url);
			            	A.loadPage({
			    				render : getIframe,
			    				url : setSrc
			    			});
			            }
			            //新闻广告条
			            if(jsonNewArr[i].type == 'advertisement'){
			            	$(getRow).append(getOutBox);
			            	var getNewRow = '.outRowBox-'+jsonNewArr[i].row;//广告所在的行
			            	$(getNewRow).empty();
			            	var setHeight = 100;
			            	if(jsonNewArr[i].height){
			            		setHeigth = jsonNewArr[i].height;
			            	}
			            	$(getNewRow).append('<a href="'+jsonNewArr[i].url+'" target="_blank"><div style="height:'+setHeight+'px;width: 100%;margin:2px auto 15px;padding:0 10px;"><img src = ../'+jsonNewArr[i].advertUrl+' style="height:100%;width: 100%;"/></div></a>');
			            }
			          //slider轮播图
		            	if(jsonNewArr[i].type == 'slider'){
		            		getOutBox = '<div class="row '+getListStr+'"><div class="widget-container-col"><div class="widget-box" style="border:none"><div class="widgetContent widget-header  padding-zero '+jsonNewArr[i].render+'"><img src="../static/images/login.gif" class="loginClass"></div></div></div>';	
		            		$(getRow).append(getOutBox);
		            		var bannerBox = '<div class="rowHeigth headImgRow"><div class="banner" id="banner"><ul style="margin:0;padding:0" class="headImgUL"></ul></div></div>';
		            		var getIdName = '.'+jsonNewArr[i].render;
		            		$(getIdName).children('.loginClass').css('display','none');
		            		var getImgArr = jsonNewArr[i].urlList;
		            		$(getIdName).append(bannerBox);
		            		for(var j=0;j<getImgArr.length;j++){
								var setSrc =  getImgArr[j].img;
								var img_li = '<li style="display:inline-block;float:left"><a href="'+getImgArr[j].url+'" target="_blank"><img src="../'+setSrc+'" height="100%"></a></li>';
								$('.headImgUL').append(img_li);
							}
		            		//轮播图
					        var unslider = $('#banner').unslider({
			       	        	dots: true
				        	});
				        	var banner = unslider.data('unslider');
				            var container_list = ace.data.get('demo', 'widget-order', true);
				            if(container_list) {
				                for(var container_id in container_list) if(container_list.hasOwnProperty(container_id)) {
				                    var widgets_inside_container = container_list[container_id];
				                    if(widgets_inside_container.length == 0) continue;
				                    for(var m = 0; m < widgets_inside_container.length; m++) {
				                        var widget = widgets_inside_container[m];
				                        $('#'+widget).appendTo('#'+container_id);
				                    }
				                }
				            } 
		            	}//轮播结束
		            	//echart
			            if(jsonNewArr[i].type == 'echartTable'){
			            	$(getRow).append(getOutBox);
// 			            	var tableBox = ''
// 			            	if(jsonNewArr[i].more_url){
// 			            		tableBox = '<div class="headDiv"><label  class="'+jsonNewArr[i].headIcon+'" aria-hidden="true"></label><span class="leftHeadSpan">'+jsonNewArr[i].title+'</span><a  class="rightHeadSpan moreTableInformation"  data-url="'+jsonNewArr[i].more_url+'">更多&nbsp;&gt;</a></div><div class="rowHeigth" id="'+jsonNewArr[i].render+'"></div>'
// 			            	}else{
// 			            		tableBox = '<div class="headDiv"><label  class="'+jsonNewArr[i].headIcon+'" aria-hidden="true"></label><span class="leftHeadSpan">'+jsonNewArr[i].title+'</span></div><div class="rowHeigth" id="'+jsonNewArr[i].render+'"></div>'
// 			            	}
			            	var tableBox = '<div class="headDiv"><label  class="'+jsonNewArr[i].headIcon+'" aria-hidden="true"></label><span class="leftHeadSpan">'+jsonNewArr[i].title+'</span></div><div class="rowHeigth" id="'+jsonNewArr[i].render+'"></div>'
			            	var getIdName = '.'+jsonNewArr[i].render;
		            		$(getIdName).append(tableBox);
			            	var getIframe = '#'+jsonNewArr[i].render;
			            	var setSrc = format_url(jsonNewArr[i].url);
			            	A.loadPage({
			    				render : getIframe,
			    				url : setSrc
			    			});
			            }
		            	//calendar
		            	if(jsonNewArr[i].type == 'calendar'){
		            		$(getRow).append(getOutBox);
		            		var tableBox = '<div class="calendarHeigth" id="'+jsonNewArr[i].render+'"></div>';
		            		var getIdName = '.'+jsonNewArr[i].render;
		            		$(getIdName).append(tableBox);
		            		var getIframe = '#'+jsonNewArr[i].render;
			            	var setSrc = format_url(jsonNewArr[i].url);
			            	A.loadPage({
			    				render : getIframe,
			    				url : setSrc
			    			});
		            	}
		    		 }
	           	}
		    	 /* $('.calendarHeigth').on('click',function(){
			    		A.loadPage({
							render : '#page-container',
							url : format_url('/firstPage/calendarPage')
						});
			    	}) */
		    	//更多内容
		    	$('.moreTableInformation').on('click',function(){
		    		A.loadPage({
						render : '#page-container',
						url : format_url($(this).attr('data-url'))
					});
		    	})
	    	});
	    });
	</script>
	</body>
</html>