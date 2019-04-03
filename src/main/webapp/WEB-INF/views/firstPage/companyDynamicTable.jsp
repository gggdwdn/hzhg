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
	<table class="table table-striped table-bordered table-hover forHeightTable noBackgroundTables" id='companyDynamic-table-firstPage' style="width:100%;border: none;"></table>
	<script type="text/javascript">
	    jQuery(function($){
	    	seajs.use(['datatables', 'confirm', 'dialog'], function(A){
	    		companyDynamicDatatables = new A.datatables({
            		//公司动态列表的数据
					render: '#companyDynamic-table-firstPage',
					options: {
						"ajax": {
				            "url": format_url("/companyDynamic/recList"),
				            "contentType": "application/json",
				            "type": "POST",
				            "dataType": "JSON",
				            "data": function (d) {
				                return JSON.stringify(d);
				              },
				              "complete":function(){
				            	  $("#companyDynamic-table-firstPage").parents('.widgetContent').children('.loginClass').hide();//隐藏等待图标
				              }
				        },
				        checked:false,
						bInfo:false,
						paging:false,
						ordering:true,
						columns: [
							{data:"id", "visible": false, orderable: false},   
							{orderable: true,"width":"55%", "render": function(data, type, row,other){
								var getRow = other.row;
								var status = row.status;
								if(status==1){
									return "<span style='color:#999'>[已读]</span><a href='#'  onclick='checkNotice("+getRow+")'>"+row.title+"</a>";
								}else{
									return "<span style='color:red'>[未读]</span><a href='#'  onclick='checkNotice("+getRow+")'>"+row.title+"</a>";
								}
								}
							},
							{data:"attachmentStatus", orderable: true,"width":"5%", sClass:'right',"render": function(data, type, row,other){
					
								if(data=="1"){
									return "<img src='../static/images/fileIcon.png' width=12px/>";
									}
								return "";
								}
							}, 
							{data:"publishDate", "width":"30%",sClass:'right'}
						]
					}
				}).render();
	    	});	
	    });
    	function checkNotice(index){
 			var getId = companyDynamicDatatables.getDatas()[index].id;
 			listFormDialog = new A.dialog({
				width: 750,
				height: 420,
				title: "公司动态查看",
				url:format_url('/companyDynamic/showPage/' + getId),
				closed: function(){
					companyDynamicDatatables.reLoad(format_url("/companyDynamic/recList"));
				}
			}).render();
 		}
	</script>
	</body>
</html>