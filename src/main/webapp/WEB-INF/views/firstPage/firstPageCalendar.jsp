<%@ page language="java" contentType="text/html; charset=utf-8"  
    pageEncoding="utf-8"%>  
<!DOCTYPE html>
<html lang="zh">
	<head>
    	<meta charset="UTF-8">
    	<%@ include file="/WEB-INF/views/common/meta.jsp" %> 
    </head>
	<body>
		<div id="calendar"></div>
		<script type="text/javascript">
			jQuery(function($) {
				seajs.use(['calendar','confirm'], function(A){
					$.ajax({
						url : format_url("/calendarNew/findAll"),
						contentType : 'application/json',
						dataType : 'JSON',
// 						data : JSON.stringify(obj),
						cache: false,
						type : 'POST',
						success: function(result){
							var date = new Date();
							var d = date.getDate();
							var m = date.getMonth();
							var y = date.getFullYear();
							var getData = [];
							if(result.data.length>0){
								for(var i=0;i<result.data.length;i++){
									var getJson = JSON.parse(result.data[i]);
									getData.push(getJson);
								}
							}
							var calendar = new A.calendar({
			            		render:'#calendar',
			            		aspectRatio:2.26,//宽高比
			            		events:getData
// 			            			[
// 			            		     {
// 			            		    	title: '事件',
// 			 							className:'label-info',
// 			 							start: new Date(y, m, d),
// 			 							end: new Date(y, m, d+3)
// 			            		     }   
// 		           		        ]
							,
			            		sureCallBack:function(title,start,end,className){
			            			var obj = {};
			            			var entity={};
			            			entity.title = title;
			            			entity.start = start+'';
			            			entity.end = end+'';
			            			entity.className = className;
			            			obj.detail = JSON.stringify(entity);
									var url = format_url("/calendarNew/saveAddPage");
			            			$.ajax({
										url : url,
										contentType : 'application/json',
										dataType : 'JSON',
										data : JSON.stringify(obj),
										cache: false,
										type : 'POST',
										success: function(result){
											alert('保存成功');
											A.loadPage({
												render : '#page-container',
												url : format_url('/login/firstPage')
											});
										},
										error:function(v,n){
											alert('保存失败');
										}
									});
			            		},
			            		deleteBack:function(id){
			            			$.ajax({
										url : format_url("/calendarNew/singleDelete/"+id),
										contentType : 'application/json',
										dataType : 'JSON',
// 										data : JSON.stringify(obj),
										cache: false,
										type : 'POST',
										success: function(result){
											alert('删除成功');
		 									A.loadPage({
											render : '#page-container',
											url : format_url('/login/firstPage')
										});
										},
										error:function(v,n){
											alert('删除失败');
										}
									});
			            		},
			            		editBack:function(id,title,start,end,className){
			            			var obj = {};
			            			var entity={};
			            			entity.title = title;
			            			entity.start = start+'';
			            			entity.end = end+'';
			            			entity.className = className;
			            			obj.detail = JSON.stringify(entity);
			            			$.ajax({
										url : format_url("/calendarNew/edit/"+id),
										contentType : 'application/json',
										dataType : 'JSON',
										data : JSON.stringify(obj),
										cache: false,
										type : 'POST',
										success: function(result){
											alert('修改成功');
// 											A.loadPage({
// 												render : '#page-container',
// 												url : format_url('/login/firstPage')
// 											});
										},
										error:function(v,n){
											alert('修改失败');
										}
									});
			            		}
			            	}).render();
						},
						error:function(v,n){
							alert('保存失败');
						}
					});
					
					$("#calendar").parents('.widgetContent').children('.loginClass').hide();//隐藏等待图标
					
				});
			});
        </script>
    </body>
</html>