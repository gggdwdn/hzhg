<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tag/Purview.tld" prefix="p"%> 
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="UTF-8">
		<%@ include file="/WEB-INF/views/common/meta.jsp" %>
	</head>
<body>
	<table class="table table-striped table-bordered table-hover forHeightTable noBackgroundTables" id='finishTask-table-firstPage' style="width:100%;border: none;"></table>
	<script type="text/javascript">
	    jQuery(function($){
	    	seajs.use(['datatables', 'confirm', 'dialog'], function(A){
	    		//请求消息列表的数据
		        finishTaskDatatables = new A.datatables({
					render: '#finishTask-table-firstPage',
					options: {
						"ajax": {
				            "url": format_url("/messageCenter/recList"),
				            "contentType": "application/json",
				            "type": "POST",
				            "dataType": "JSON",
				            "data": function (d) {
				                return JSON.stringify(d);
				              },
			              	"complete":function(){
			              		$("#finishTask-table-firstPage").parents('.widgetContent').children('.loginClass').hide();//隐藏等待图标
			              	}
				        },
				        checked:false,
						bInfo:false,
						paging:false,
						ordering:true,
						columns: [
							{data:"id", "visible": false, orderable: false}, 
// 							{data:"status", orderable: false,"width":"auto", "render": function(data, type, row,other){
// 								if(data==1){
// 										return "<span style='color:red'>[已读]</span>";
// 									}else{
// 										return "<span style='color:#999'>[未读]</span>";
// 									}
// 								}
// 							},
							{ orderable: true,"width":"auto",sClass:'left', "render": function(data, type, row,other){
								var getRow = other.row;
								var status = row.status;
									if(status==0){
										return "<span style='color:red'>[未读]</span><a href='#'  onclick='checkCodeA("+getRow+")'>"+row.title+"</a>";
									}else if(status==1){
										return "<span style='color:#999'>[已读]</span><a href='#'  onclick='checkCodeA("+getRow+")'>"+row.title+"</a>";
									}
									return "";
								}
							},
							{data:"sendTime", "width":"auto",sClass:'right'}
						]
					}
				}).render();
			});
	    });
	    function checkCodeA(index){
 			var getId = finishTaskDatatables.getDatas()[index].id;
 			listFormDialog = new A.dialog({
				width: 850,
				height: 471,
				title: "消息查看",
				url:format_url('/messageCenter/showPage/' + getId),
				closed: function(){
					//宋毅提供，和消息快捷方式同步
					changeHeaderMessage();
					finishTaskDatatables.reLoad(format_url("/messageCenter/recList"));
				}
			}).render();
 		}
	</script>
	</body>
</html>