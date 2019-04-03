<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tag/Purview.tld" prefix="p"%> 
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="UTF-8">
		<%@ include file="/WEB-INF/views/common/meta.jsp" %>
	</head>
	<body>
		<div class="page-content">
			<div class=" col-lg-12 col-md-12 col-sm-12  padding-zero search-content">
				<div class="form-inline text-left" role="form">
					<div class="clearfix">
						<div class="form-group col-lg-3 col-md-3 col-sm-3 col-xs-12 padding-zero">
		                    <label class="searchLabel" for="searchMessageSendType">消息类型</label>：
		                    <div class="inputWidth padding-zero  text-left">
		                        <select id="searchMessageSendType" name=searchMessageSendType  class="form-control chosen-select"></select>
		                    </div>
		                </div>
<!-- 		                <div class="form-group col-lg-3 col-md-3 col-sm-3 col-xs-12 padding-zero"> -->
<!-- 		                    <label class="searchLabel" for="searchSendStatus">消息状态</label>： -->
<!-- 		                    <div class="inputWidth padding-zero  text-left"> -->
<!-- 		                        <select id="searchSendStatus" name=searchSendStatus  class="form-control chosen-select"></select> -->
<!-- 		                    </div> -->
<!-- 		                </div> -->
						<div class="form-group  col-lg-6 col-md-6 col-sm-6 padding-zero" style="height: 31px;">
							<label class="searchLabel" for="searchStartInstockTimeDiv">发送时间</label>：
							<div class="form-group dateInput padding-zero text-left">
			                    <div id="searchStartSendSendTimeDiv" style="border:none; padding:0px;"></div>
			                </div>
							<div class="toLabel" for="searchStartTimeRightDiv" >~</div>
							<div class="form-group dateInput padding-zero text-left">
	                            <div id="searchEndSendSendTimeDiv" style="border:none; padding:0px;"></div>
						    </div>
	                    </div>
					</div>
				<div class="clearfix">
	              <div  class="form-group col-lg-12 col-md-12 col-sm-12 padding-zero text-right btnSearchBottom">
						<button id="btnSendSearch" class="btn btn-xs btn-primary newBtnSearch">
							<i class="glyphicon glyphicon-search"></i>
							查询
						</button>
						<button id="btnSendReset" class="btn btn-xs btn-primary">
							<i class="glyphicon glyphicon-repeat"></i>
							重置
						</button>								
					</div>
                </div>
                </div>
            </div>
			<div class="row">
				<div class="col-xs-12">
					<!-- div.dataTables_borderWrap -->
					<div class="widget-main no-padding">
						<h5 class='table-title header smaller blue' >消息内容</h5>
						<table id="messageCenter_send_table" class="table table-striped table-bordered table-hover" style="width:100%;">
							<thead>
								<tr>
									<th style="display:none;">主键</th>
									<th class="center sorting_disabled" style="width:50px;">
        								<label class="pos-rel">
        									<input type="checkbox" class="ace" />
        									<span class="lbl"></span>
        								</label>
        							</th>
	                                <th>主题</th>
	                                <th>消息内容</th>
	                                <th class='onlyLeft'>发送时间</th>
	                                <th>接收人</th>
	                                <th>消息类型</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			var listFormSendDialog;
			jQuery(function($) {
				seajs.use(['datatables', 'confirm', 'dialog','combobox','combotree','my97datepicker','selectbox'], function(A){
					var conditions=[];
					//消息类型下拉框
					var messageSendTypeCombo = ${messageSendTypeCombo};
					var messageSendTypeCombobox = new A.combobox({
						render: "#searchMessageSendType",
						//返回数据待后台返回
						datasource:messageSendTypeCombo,
						//multiple为true时select可以多选
						multiple:false,
						//allowBlank为false表示不允许为空
						allowBlank: true,
						options:{
							"disable_search_threshold":10
						}
					}).render();
					//消息状态下拉框
// 					var messageStatusSendCombo = ${messageStatusSendCombo};
// 					var messageStatusCombobox = new A.combobox({
// 						render: "#searchSendStatus",
// 						//返回数据待后台返回
// 						datasource:messageStatusSendCombo,
// 						//multiple为true时select可以多选
// 						multiple:false,
// 						//allowBlank为false表示不允许为空
// 						allowBlank: true,
// 						options:{
// 							"disable_search_threshold":10
// 						}
// 					}).render();
					
					//消息发送开始日期
					var startDateSendPicker = new A.my97datepicker({
						id: 'searchSendStartTime',
						name: 'sendTime',
						render:'#searchStartSendSendTimeDiv',
						options : {
								isShowWeek : false,
								skin : 'ext',
								maxDate: "",
								minDate: "",
								dateFmt: 'yyyy-MM-dd HH:mm'
						}
					}).render();
					
					//消息发送截止日期
					var endDateSendPicker = new A.my97datepicker({
						id: 'searchSendEndTime',
						name: 'sendTime',
						render:'#searchEndSendSendTimeDiv',
						options : {
								isShowWeek : false,
								skin : 'ext',
								maxDate: "",
								minDate: "",
								dateFmt: 'yyyy-MM-dd HH:mm'
						}
					}).render();
					var messageCenterSendDatatables = new A.datatables({
						render: '#messageCenter_send_table',
						options: {
					        "ajax": {
					            "url": format_url("/messageCenter/sendList"),
					            "contentType": "application/json",
					            "type": "POST",
					            "dataType": "JSON",
					            "data": function (d) {
					            	d.conditions = conditions;
					                return JSON.stringify(d);
					              }
					        },
					        multiple : true,
							ordering: true,
							optWidth: 80,
							columns: [
							          {data:"id", visible:false,orderable:false}, 
							          {data: "title",width: "12%",orderable: true,render:function(data,type,row,meta){
						        		  if(data!=null && data.length>13){
						        			  return "<div title='"+data+"'>"+data.substring(0,13)+"..."+"</div>";
						        		  }else{
						        			  return data;
						        		  }
						        	  }}, 
							          {data: "context",width: "42%",orderable: true,
						        		  render:function(data,type,row,meta){
							        		  if(data!=null && data.length>55){
							        			  return "<div title='"+data+"'>"+data.substring(0,55)+"..."+"</div>";
							        		  }else{
							        			  return data;
							        		  }
							        	  }}, 
							          {data: "sendTime",width: "15%",orderable: true,sClass:'center'}, 
							          {data: "receivePerson",width: "15%",orderable: true,
							        	  render:function(data,type,row,meta){
							        		  if(data!=null && data.length>17){
							        			  return "<div title='"+data+"'>"+data.substring(0,17)+"..."+"</div>";
							        		  }else{
							        			  return data;
							        		  }
						        	  }}, 
							          {data: "messageTypeName",width: "9%",orderable: true},
							         ],
							toolbars: [{
        						label:"新增",
        						icon:"glyphicon glyphicon-plus",
        						className:"btn-success",
        						events:{
        							click: function(event, nRow, nData){
										listFormSendDialog = new A.dialog({
                    						width: 850,
                    						height: 471,
                    						title: "新增消息",
                    						url:format_url('/messageCenter/sendIndex'),
                    						closed: function(){
                    							messageCenterSendDatatables.draw(false);
                    						}	
                    					}).render()
									}
        						}
        					},{
								label:"删除",
								icon:"glyphicon glyphicon-trash",
								className:"btn-danger",
								events:{
									click: function(event){
										var data = messageCenterSendDatatables.getSelectRowDatas();
										var ids = [];
										if(data.length && data.length>0){
											for(var i =0; i<data.length; i++){
												ids.push(data[i].id);
											}
										}
										if(ids.length < 1){
											alert('请选择要删除的数据');
											return;
										}
										var url = format_url('/messageCenter/bulkDeleteSend/');
										A.confirm('您确认删除么？',function(){
											$.ajax({
												url : url,
												contentType : 'application/json',
												dataType : 'JSON',
												type : 'DELETE',
												data : JSON.stringify(ids),
												success: function(result){
													alert('删除成功');
													messageCenterSendDatatables.draw(false);
												},
												error:function(v,n){
													alert('操作失败');
												}
											});
										});
									}
								}
							}],
							btns: [{
								id:"delete",
								label:"删除",
								icon: "icon iconfont icon-shanchu",
								className: "red del",
								events:{
									click: function(event, nRow, nData){
										var id = nData.id;
										var url =format_url('/messageCenter/deleteSend/'+ id);
										A.confirm('您确认删除么？',function(){
											$.ajax({
												url : url,
        										contentType : 'application/json',
        										dataType : 'JSON',
        										type : 'DELETE',
        										success: function(result){
        											alert('删除成功');
        											messageCenterSendDatatables.draw(false);
        										},
        										error:function(v,n){
        											alert('操作失败');
        										}
											});
										});
									}
								}
						},
// 						{
// 							id:"show",
// 						 	label: "查看",
// 							icon: "icon iconfont icon-chakan",
// 							className: "blue search",
// 							events:{
// 								click: function(event, nRow, nData){
// 									var id = nData.id;
// 									listFormSendDialog = new A.dialog({
//                 						width: 850,
//                 						height: 471,
//                 						title: "消息查看",
//                 						url:format_url('/messageCenter/showPage/' + id),
//                 						closed: function(){
//                 							//宋毅提供，和消息快捷方式同步
//                 							changeHeaderMessage();
//                 							messageCenterSendDatatables.draw(false);
//                 						}
//                 					}).render()
// 								}
// 							}
// 						},
						{
							id:"again",
						 	label: "再次发送",
							icon: "icon iconfont icon-fasong",
							className: "blue search",
							events:{
								click: function(event, nRow, nData){
									var id = nData.id;
									var url =format_url('/messageCenter/againSend/'+ id);
									A.confirm('您确认再次发送么？',function(){
										$.ajax({
											url : url,
    										contentType : 'application/json',
    										dataType : 'JSON',
    										success: function(result){
    											alert('发送成功');
    											messageCenterSendDatatables.draw(false);
    										},
    										error:function(v,n){
    											alert('操作失败');
    										}
										});
									});
								}
							}
						}]
						}
					}).render();
					$('#btnSendSearch').on('click',function(){
						conditions=[];
						if($('#searchMessageSendType').val()){
							conditions.push({
	    						field: 'type',
	    						fieldType:'STRING',
	    						matchType:'EQ',
	    						value:$('#searchMessageSendType').val()
	    					});
						}
						if($("#searchSenderDivId").next('input').val()){
							conditions.push({
	    						field: 'sendPerson',
	    						fieldType:'STRING',
	    						matchType:'EQ',
	    						value:$("#searchSenderDivId").next('input').val()
	    					});
						}
						if($('#searchSendStatus').val()){
							conditions.push({
	    						field: 'status',
	    						fieldType:'STRING',
	    						matchType:'EQ',
	    						value:$('#searchSendStatus').val()
	    					});
						}
						//发送时间开始
						if($("#searchSendStartTime").val()){
        					conditions.push({
        						field: 'sendTime',
        						fieldType:'DATE',
        						matchType:'GE',
        						value:$("#searchSendStartTime").val()
        					});
						}
						//发送时间结束
						if($("#searchSendEndTime").val()){
        					conditions.push({
        						field: 'sendTime',
        						fieldType:'DATE',
        						matchType:'LE',
        						value:$("#searchSendEndTime").val()
        					});
						}
						messageCenterSendDatatables.draw();
					});
					$('#btnSendReset').on('click',function(){
						$('#searchSender').val('');
						$("#searchSendStartTime").val('');
						$("#searchSendEndTime").val('');
						messageSendTypeCombobox.setValue('99');
						messageStatusCombobox.setValue('99');
						flowManagerNamesDiv.setValue('');
					});
				});
			});
        </script>
    </body>
</html>