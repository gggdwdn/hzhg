<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tag/Purview.tld" prefix="p"%> 
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="UTF-8">
		<%@ include file="/WEB-INF/views/common/meta.jsp" %>
		<style>
			
		</style>
	</head>
<body>
	<table class="table table-striped table-bordered table-hover forHeightTable noBackgroundTables" id='notice-table-firstPage' style="width:100%;border: none;"></table>
	<script type="text/javascript">
	    jQuery(function($){
	    	seajs.use(['datatables', 'confirm', 'dialog'], function(A){
	    		noticeDatatables = new A.datatables({
            		//公告列表的数据
					render: '#notice-table-firstPage',
					options: {
						"ajax": {
				            "url": format_url("/notice/recList"),
				            "contentType": "application/json",
				            "type": "POST",
				            "dataType": "JSON",
				            "data": function (d) {
				                return JSON.stringify(d);
				              },
				              "complete":function(){
				            	  $("#notice-table-firstPage").parents('.widgetContent').children('.loginClass').hide();//隐藏等待图标
				              }
				        },
				        checked:false,
						bInfo:false,
						paging:false,
						ordering:true,
						columns: [
							{data:"id", "visible": false, orderable: false},      
							{ orderable: true,"width":"60%", "render": function(data, type, row,other){
								var getRow = other.row;
								return "<a href='#'  onclick='checkNotice("+getRow+")'>"+row.title+"</a>";
								}
							},
							{data:"publishDate", "width":"30%",sClass:'right'}
						]
					}
				}).render();
	    	});	
	    });
    	function checkNotice(index){
 			var getId = noticeDatatables.getDatas()[index].id;
 			listFormDialog = new A.dialog({
				width: 750,
				height: 350,
				title: "公告查看",
				url:format_url('/notice/showPage/' + getId),
				closed: function(){
					//宋毅提供，和消息快捷方式同步
					changeHeaderMessage();
				}
			}).render();
 		}
	</script>
	</body>
</html>