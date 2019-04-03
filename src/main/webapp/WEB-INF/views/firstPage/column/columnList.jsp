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
					<a href="#" onclick="firstPage()">首页</a>
				</li>
				<li>
					首页管理
				</li>
				<li class="active">栏目管理</li>
			</ul><!-- /.breadcrumb -->
		</div>
		<div class="page-content">
				<div class="col-lg-12 col-md-12 col-sm-12 search-content  padding-zero">
				 <div class="form-inline text-left" role="form">
	          	  <div class="clearfix">
	                <div class="form-group col-lg-3 col-md-3 col-sm-6 col-xs-12 padding-zero">
						<label class="searchLabel" for="form-field-1" >标题</label>：
		               	<input id="searchtitle" class="inputWidth text-left" placeholder="请输入标题" type="text">
                   </div>
	                <div class="form-group col-lg-3 col-md-3 col-sm-6 col-xs-12 padding-zero">
						<label class="searchLabel" for="form-field-1" >类型</label>：
						<div class="padding-zero inputWidth  text-left">
	                   	<select id="searchType" class="form-control chosen-select" ></select>
	                   	</div>
                   </div>
	               <div class="form-group col-lg-3 col-md-3 col-sm-6 col-xs-12 padding-zero">
						<label class="searchLabel " for="form-field-1" >更多</label>：
		               	<input id="searchmoreUrl" class="inputWidth text-left" placeholder="请输入更多" type="text">
                   </div>
    			  <div class="form-group col-lg-3 col-md-3 col-sm-6 col-xs-12 padding-zero">
						<label class="searchLabel " for="form-field-1" >链接地址</label>：
		               	<input id="searchUrl" class="inputWidth text-left" placeholder="请输入广告链接地址" type="text">
                   </div>
	          </div>
                <div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-zero btnSearchBottom" style="text-align:right; ">
				    <div class="clearfix"> 
 					<button id="btnSearch" class="btn btn-xs btn-primary newBtnSearch"><!--newBtnSearch -->
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
						<h5 class='table-title header smaller blue' >栏目列表</h5>
						<table id="column_table" class="table table-striped table-bordered table-hover" style="width:100%;">
							
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
	                                <th>类型</th>
	                                <th>更多</th>
	                                <th>标题图标</th>
	                                <th>链接地址</th>
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
					var searchTypeCombobox = new A.combobox({
						render: "#searchType",
						name: 'type',
						datasource:${types},
						//multiple为true时select可以多选
						multiple:false,
						//allowBlank为false表示不允许为空
						allowBlank: true,
						options:{
							"allow_single_deselect": true,
							"disable_search_threshold":10
						}
					}).render();
					var columnDatatables = new A.datatables({
						render: '#column_table',
						options: {
					        "ajax": {
					            "url": format_url("/column/search"),
					            "contentType": "application/json",
					            "type": "POST",
					            "dataType": "JSON",
					            "data": function (d) {
					            	if(d.search.value){
						            	d.conditions = JSON.parse(d.search.value);
						            	if(d.conditions){
						            		for(var index in d.conditions){
						            			if(d.conditions[index].type==1){
						            			$("#"+d.conditions[index].name).val(d.conditions[index].value);
						            			$("#"+d.conditions[index].name).trigger("chosen:updated");
						            			}
						            			if(d.conditions[index].type==2){
						            				searchunitId.setValue(d.conditions[index].value);
							            		}
						            		}
						            	}
					            	}
					            	//d.conditions = conditions;
					                return JSON.stringify(d);
					              }
					        },
					        multiple : true,
							ordering: true,
							order:[["0",'desc']],
							optWidth: 80,
							bStateSave: true,
							searching: true,
							iCookieDuration:cookieTime,
							columns: [{data:"id", visible:false,orderable:true}, 
							          {data: "title",width: "15%",orderable: true},
							          {data: "typeString",name:"type",width: "15%",orderable: true},
							          {data: "moreUrl",width: "20%",orderable: true},
							          {data: "headIcon",width: "20%",orderable: true},
							          {data: "url",width: "20%",orderable: true}],
							toolbars: [{
        						label:"新增",
        						icon:"glyphicon glyphicon-plus",
        						className:"btn-success",
        						events:{
            						click:function(event){
            							A.loadPage({
											render : '#page-container',
											url : format_url("/column/getAdd")
										});
            						}
        						}
        					}, {
								label:"删除",
								icon:"glyphicon glyphicon-trash",
								className:"btn-danger",
								events:{
									click: function(event){
										var data = columnDatatables.getSelectRowDatas();
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
										var url = format_url('/column/bulkDelete/');
										A.confirm('您确认删除么？',function(){
											$.ajax({
												url : url,
												contentType : 'application/json',
												dataType : 'JSON',
												type : 'DELETE',
												data : JSON.stringify(ids),
												success: function(result){
													
													if(result.result=="success"){
														alert('删除成功');
														columnDatatables.draw(false);
													}else{
														alert(result.errorMsg);
													}
													
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
								render: function(btnNode, data){
									if(data.type!="slider" && data.type!="echartTable" && data.type!="advertisement" && data.type!="noHeaderBox"){
										btnNode.hide();
									}
								},
								events:{
									click: function(event, nRow, nData){
										var id = nData.id;
										A.loadPage({
											render : '#page-container',
											url:format_url('/column/getEdit/' + id),
										});
									}
								}
							},{
								id: "show",
								label:"查看",
								icon: "icon iconfont icon-chakan",
								className: "blue",
								events:{
									click: function(event, nRow, nData){
										var id = nData.id;
										A.loadPage({
											render : '#page-container',
											url:format_url('/column/getDetail/' + id),
										});
									}
								}
							}, {
								id:"delete",
								label:"删除",
								icon: "icon iconfont icon-shanchu",
								className: "red del",
								render: function(btnNode, data){
									if(data.type!="slider" && data.type!="echartTable" && data.type!="advertisement" && data.type!="noHeaderBox" ){
										btnNode.hide();
									}
								},
								events:{
									click: function(event, nRow, nData){
										var id = nData.id;
										var url =format_url('/column/'+ id);
										A.confirm('您确认删除么？',function(){
											$.ajax({
												url : url,
        										contentType : 'application/json',
        										dataType : 'JSON',
        										type : 'DELETE',
        										success: function(result){
        											if(result.result=="success"){
														alert('删除成功');
														columnDatatables.draw(false);
													}else{
														alert(result.errorMsg);
													}
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
					$('#btnSearch').on('click',function(){
						conditions=[];
						
						if($("#searchType").val()){
	    					conditions.push({
	        					field: 'type',
	        					fieldType:'STRING',
	        					matchType:'EQ',
	        					name:"searchType",
	        					type:1,
	        					value:$("#searchType").val().trim()
	        				});
						}
						if($("#searchtitle").val()){
	    					conditions.push({
	        					field: 'title',
	        					fieldType:'STRING',
	        					matchType:'LIKE',
	        					name:"searchtitle",
	        					type:1,
	        					value:$("#searchtitle").val().trim()
	        				});
						}
						if($("#searchmoreUrl").val()){
	    					conditions.push({
	        					field: 'moreUrl',
	        					fieldType:'STRING',
	        					matchType:'LIKE',
	        					name:"searchmoreUrl",
	        					type:1,
	        					value:$("#searchmoreUrl").val().trim()
	        				});
						}
						if($("#searchUrl").val()){
	    					conditions.push({
	        					field: 'url',
	        					fieldType:'STRING',
	        					matchType:'LIKE',
	        					name:"searchUrl",
	        					type:1,
	        					value:$("#searchUrl").val().trim()
	        				});
						}
						columnDatatables._datatables.search(JSON.stringify(conditions)).draw();
					});
					$('#btnReset').on('click',function(){
						
						$("#searchType").val("");
						$("#searchType").trigger("chosen:updated");
						$("#searchUrl").val("");
						$("#searchmoreUrl").val("");
						$("#searchtitle").val("");
					});
				});
			});
        </script>
    </body>
</html>