<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tag/Purview.tld" prefix="p"%> 
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="UTF-8">
		<%@ include file="/WEB-INF/views/common/meta.jsp" %>
	</head>
	<body>
		<div class="breadcrumbs ace-save-state" id="breadcrumbs">
			<ul class="breadcrumb">
				<li>
					<i class="ace-icon fa fa-home home-icon"></i>
					<a href="javascript:void(0);" onclick="firstPage()">首页</a>
				</li>
				<li>系统管理</li>
				<li class="active">公告管理</li>
			</ul><!-- /.breadcrumb -->
		</div>
		<div class="page-content">
			<div class="col-lg-12 col-md-12 col-sm-12 search-content  padding-zero">
				<div class="form-inline text-left" role="form">
	            <div class="clearfix">
	                <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12  padding-zero">
							<label  class="searchLabel"  for="searchStartpublishDateDiv">发布时间</label>：
	                    <div class="form-group dateInputOther padding-zero text-left">
			                    <div id="searchStartpublishDateDiv" style="border:none; padding:0px;"></div>
			                </div>
	                    <div class="toLabel" for="searchStartTimeRightDiv" >~</div>
	                    <div class="form-group dateInputOther padding-zero text-left">
	                            <div id="searchEndpublishDateDiv" style="border:none; padding:0px;"></div>
						    </div>
	                    </div>
	                	<div class="form-group col-lg-3 col-md-3 col-sm-3 col-xs-12 padding-zero">
		                    <label  class="searchLabel"  for="searchpublisher">发布人</label>：
			                <!-- <input id="searchpublisher" class="form-control" placeholder="请输入发布人" type="text" > -->
			                <div class="inputWidth padding-zero  text-left">
					                <select id="searchpublisher" ></select>
							</div>
	                    </div>
			           <!-- <div class="form-group">
							<label class="" for="form-field-1">发布人：</label>
			                <input id="searchpublisher" class="form-control" placeholder="请输入发布人" type="text" >
	                   </div> -->
	                <div class="form-group col-lg-3 col-md-3 col-sm-3 col-xs-12 padding-zero btnSearchBottom" style="text-align:right; ">
							<button id="btnSearch" class="btn btn-xs btn-primary newBtnSearch">
								<i class="glyphicon glyphicon-search"></i>
								查询
							</button>
							<button id="btnReset" class="btn btn-xs btn-primary">
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
						<h5 class='table-title header smaller blue' >公告</h5>
						<table id="notice_table" class="table table-striped table-bordered table-hover" style="width:100%;">
							<thead>
								<tr>
									<th style="display:none;">主键</th>
									<th class="center sorting_disabled" style="width:50px;">
        								<label class="pos-rel">
        									<input type="checkbox" class="ace" />
        									<span class="lbl"></span>
        								</label>
        							</th>
	                                <th>标题</th>
	                                <th class='onlyLeft'>发布时间</th>
	                                <th>发布人</th>
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
			var listFormDialog;
			jQuery(function($) {
				seajs.use(['datatables', 'confirm', 'dialog','combobox','combotree','my97datepicker'], function(A){
					var conditions=[];
					
					var startDatePicker = new A.my97datepicker({
						id: 'publishStartDate',
						name: 'startTime',
						render:'#searchStartpublishDateDiv',
						options : {
								isShowWeek : false,
								skin : 'ext',
								maxDate: "",
								minDate: "",
								dateFmt: 'yyyy-MM-dd HH:mm:ss'
						}
					}).render();
					var endDatePicker = new A.my97datepicker({
						id: 'publishEndDate',
						name: 'startTime',
						render:'#searchEndpublishDateDiv',
						options : {
								isShowWeek : false,
								skin : 'ext',
								maxDate: "",
								minDate: "#F{$dp.$D(\\'publishStartDate\\')}",
								dateFmt: 'yyyy-MM-dd HH:mm:ss'
						}
					}).render();
					var publisherData = ${allUser};
					var publisherCombobox = new A.combobox({
						render: "#searchpublisher",
						datasource:publisherData,
						//multiple为true时select可以多选
						multiple:false,
						//allowBlank为false表示不允许为空
						allowBlank: true,
						options:{
							"disable_search_threshold":10
						}
					}).render();
					var noticeDatatables = new A.datatables({
						render: '#notice_table',
						options: {
					        "ajax": {
					            "url": format_url("/notice/data/list"),
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
							order:[["3",'desc']],
							columns: [
							          {data:"id", visible:false,orderable:false}, 
							          {data: "title",width: "30%",orderable: true},
							          {data: "publishDate",width: "30%",orderable: true,sClass:'center'}, 
							          {data: "publisher",width: "30%",orderable: true}
							          ],
							toolbars: [{
        						label:"新增",
        						icon:"glyphicon glyphicon-plus",
        						className:"btn-success",
        						events:{
            						click:function(event){
                						listFormDialog = new A.dialog({
                    						width: 750,
                    						height: 350,
                    						title: "公告发布",
                    						url:format_url('/notice/getAdd'),
                    						closed: function(){
                    							noticeDatatables.draw(false);
                    						}	
                    					}).render()
            						}
        						}
        					}, {
								label:"删除",
								icon:"glyphicon glyphicon-trash",
								className:"btn-danger",
								events:{
									click: function(event){
										var data = noticeDatatables.getSelectRowDatas();
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
										var url = format_url('/notice/bulkDelete/');
										A.confirm('您确认删除么？',function(){
											$.ajax({
												url : url,
												contentType : 'application/json',
												dataType : 'JSON',
												type : 'DELETE',
												data : JSON.stringify(ids),
												success: function(result){
													alert('删除成功');
													noticeDatatables.draw(false);
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
								id: "edit",
								label:"修改",
								icon: "icon iconfont icon-xiugai",
								className: "green edit",
								events:{
									click: function(event, nRow, nData){
										var id = nData.id;
										listFormDialog = new A.dialog({
											width:750 ,
											height:350 ,
											title: "公告编辑",
											url:format_url('/notice/getEdit/' + id),
											closed: function(){
												noticeDatatables.draw(false);
											}
										}).render();
									}
								}
							}, {
								id:"delete",
								label:"删除",
								icon: "icon iconfont icon-shanchu",
								className: "red del",
								events:{
									click: function(event, nRow, nData){
										var id = nData.id;
										var url =format_url('/notice/'+ id);
										A.confirm('您确认删除么？',function(){
											$.ajax({
												url : url,
        										contentType : 'application/json',
        										dataType : 'JSON',
        										type : 'DELETE',
        										success: function(result){
        											alert('删除成功');
        											noticeDatatables.draw(false);
        										},
        										error:function(v,n){
        											alert('操作失败');
        										}
											});
										});
									}
								}
						}, {
						 	label: "查看",
							icon: "icon iconfont icon-chakan",
							className: "blue search",
							events:{
								click: function(event, nRow, nData){
									var id = nData.id;
									listFormDialog = new A.dialog({
										width:750 ,
										height:350 ,
										title: "公告查看",
										url:format_url('/notice/showPage/' + id),
										closed: function(){
											noticeDatatables.draw(false);
										}
									}).render();
								}
							}
						}]
						}
					}).render();
					$('#btnSearch').on('click',function(){
						conditions=[];
						//开始时间下限
						if($("#publishStartDate").val()){
    						conditions.push({
    							field: 'publishDate',
    							fieldType:'STRING',
    							matchType:'GE',
    							value:$("#publishStartDate").val()
    						});
						}
						//开始时间上限
						if($("#publishEndDate").val()){
    						conditions.push({
    							field: 'publishDate',
    							fieldType:'STRING',
    							matchType:'LE',
    							value:$("#publishEndDate").val()
    						});
						}
						if($('#searchpublisher').val()){
        					conditions.push({
        						field: 'publisher',
        						fieldType:'STRING',
        						matchType:'LIKE',
        						value:$('#searchpublisher').val()
        					});
						}

						noticeDatatables.draw();
					});
					$('#btnReset').on('click',function(){
						$('#publishStartDate').val('')
						$('#publishEndDate').val('')
						publisherCombobox.setValue(99)
					});
				});
			});
        </script>
    </body>
</html>