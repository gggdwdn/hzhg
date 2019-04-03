<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tag/Purview.tld" prefix="p"%> 
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="UTF-8">
		<%@ include file="/WEB-INF/views/common/meta.jsp" %>
	</head>
<body>
	<table class="table table-striped table-bordered table-hover paddingLeftTable haveBackgroundTables" id='todo-table-firstpage' style="width:100%;border: none;"></table>
	<script type="text/javascript">
	    jQuery(function($){
	    	seajs.use(['datatables', 'confirm', 'dialog'], function(A){
	    		//代办任务加载信息
		        todoTaskDatatables = new A.datatables({
					render: '#todo-table-firstpage',
					options: {
				        "ajax": {
				            "url": format_url("/todoTask/search"),
				            "contentType": "application/json",
				            "type": "POST",
				            "dataType": "JSON"  ,
				            "data": function (d) {
				            	var conditions=[];
				            	conditions.push({
									'field':'task.end_time_',
									'fieldType':'STRING',
									'matchType':'NULL',
									'value':""
								});
				            	d.conditions = conditions;
				                return JSON.stringify(d);
				              },
				              "complete":function(){
				            	  $("#todo-table-firstpage").parents('.widgetContent').children('.loginClass').hide();//隐藏等待图标
				              }
				        },
						ordering:true,
						checked:false,
						bInfo:false,
						paging:false,
						columns: [
							{data:"id_", "visible": false, orderable: false},  
							{ orderable: false, "width":"auto", "render": function(data, type, row,other){
								var getRow = other.row;
								var setData = JSON.stringify(row);
								return "<a href='#' style='color:#ff7d01' onclick='checkCodeWork("+getRow+","+setData+")'>"+row.startUserName+" 发起的 "+row.processName+" 等待您的办理";"</a>";
								}
							},
							{ data:"unitName", "width":"auto"},
							{ data:"startTime", "width":"auto",sClass:'right'}
						]
					}
				}).render();
			});
	    });
    	function checkCodeWork(index,nData){
 			var getId = nData.id_;
             var currentPage = todoTaskDatatables.getCurrentPage();
             var pageSize = todoTaskDatatables.getPageSize();
             var procInstId = nData.procInstId;//流程实例ID
             var procDefId = nData.procDefId;////流程定义ID
             var formURL=nData.startJspFile+"/"+nData.businessKey_;
             A.loadPage({
               render : '#page-container',
               url : format_url("/todoTask/detail?id=" + getId + "&currentPage="+ currentPage 
                   + "&pageSize=" + pageSize+"&procInstId="+procInstId+
                   "&procDefId="+procDefId+"&formURL="+formURL)
             });
 		}
	</script>
	</body>
</html>