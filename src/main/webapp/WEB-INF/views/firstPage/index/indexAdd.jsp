<%@ page language="java" contentType="text/html; charset=utf-8"  
    pageEncoding="utf-8"%>  
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
				<li class="active">首页配置</li>
			</ul><!-- /.breadcrumb -->
		</div>
		
		<div class="col-md-12" >
			<form class="form-horizontal" role="form"  style="margin-right:100px;margin-top:30px;" id="addForm">
			   <input class="col-md-12" id="id" name="id" type="hidden" placeholder="" maxlength="20" value="${entity.id }">
<!-- 		       <div class="form-group"> -->
<!-- 				    <label class="col-md-2 control-label no-padding-right"> -->
<!-- 						<span style="color:red;">*</span>新闻内容 -->
<!-- 				    </label> -->
<!-- 				    <div class="col-md-10"> -->
<%-- 						<input class="col-md-12" id="news" name="news" type="text" placeholder="" maxlength="150" value="${entity.news }"> --%>
<!--                     </div> -->
<!--                </div> -->
<!-- 		       <div class="form-group"> -->
<!-- 					<label class="col-md-2 control-label no-padding-right"> -->
<!-- 						<span style="color:red;">*</span>是否含有链接 -->
<!-- 					</label> -->
<!-- 					<div class="col-md-4"> -->
<!-- 						<select id="isUrl" class="form-control chosen-select" ></select> -->
<!--                 	</div> -->
<!--                </div> -->
<!--                <div class="form-group"> -->
<!-- 				    <label class="col-md-2 control-label no-padding-right"> -->
<!-- 						新闻链接地址 -->
<!-- 				    </label> -->
<!-- 				    <div class="col-md-10"> -->
<%-- 						<input class="col-md-12" id="newsUrl" name="newsUrl" type="text" placeholder="" maxlength="" value="${entity.newsUrl }"> --%>
<!--                     </div> -->
<!--                </div> -->
            </form>
      <div class="col-xs-12" >	
	    <h5 class="table-title header smaller blue" >首页配置</h5>
		<div class="widget-main no-padding">
			<form id="indexColumn" >
				<table id="indexColumn-table"  class="table table-striped table-bordered table-hover" style="width:100%;">
					<thead>
						<tr>
							<th   class=' center' rowSpan="2">行数</th>
							<th class=' center' colSpan="2">第一列</th>
                       		<th class=' center' colSpan="2">第二列</th>
                       		<th class=' center' colSpan="2">第三列</th>
                       		<th  class=' center' rowSpan="2">行高度</th>
                       		
                          	<th   class=' center' rowSpan="2">操作</th>
						</tr>
						<tr>
							<th >所占列数</th>
                       		<th >栏目</th>
							<th >所占列数</th>
                       		<th >栏目</th>
							<th >所占列数</th>
                       		<th >栏目</th>
						</tr>
					</thead>
				</table>
			</form>
		</div>
		</div>
    		<div class="col-xs-12"  style="margin-top: 20px;">
    			<button id="saveBtn" class="btn btn-xs btn-success pull-right" >
    				<i class="ace-icon fa fa-floppy-o"></i>
    				保存
    			</button>
    		</div>
		</div>
		<script type="text/javascript">
			jQuery(function($) {
				seajs.use(['combobox','combotree','my97datepicker'], function(A){
					var columnSelect  = [{"code":"1","name":"1"},{"code":"2","name":"2"},{"code":"3","name":"3"}];
					var entityJson = ${entityJson};
					var searchTypeCombobox = new A.combobox({
						render: "#isUrl",
						name: 'isUrl',
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
					searchTypeCombobox.setValue(entityJson.isUrl);
					var conditions=[];
					var indexColumnDatatables = new A.datatables({
						render: '#indexColumn-table',
						options: {
							    serverSide: false,
						        multiple : true,
						        checked:false,
								bInfo:false,
								paging:false,
								ordering:true,
								optWidth: 120,
								order:[],
							    "fnDrawCallback"    : function(){
								this.api().column(0).nodes().each(function(cell, i) {
								cell.innerHTML =  i + 1;
							});
						},
							columns: [
							 {data: "row",width: "3%",orderable: false, editType:"input",sClass:'noError'}, 
							 {data: "columnSpan1",width: "13%",orderable: false,editType:"combobox",sClass:'noError',cfg:{ datasource :columnSelect, allowBlank: true,}}, 
							 {data: "columnId1",width: "13%",orderable: false,editType:"combobox",sClass:'noError',cfg:{ datasource : ${columnCombobox}, allowBlank: true,}}, 
							 {data: "columnSpan2",width: "13%",orderable: false,editType:"combobox",sClass:'noError',cfg:{ datasource :columnSelect, allowBlank: true,}}, 
							 {data: "columnId2",width: "13%",orderable: false,editType:"combobox",sClass:'noError',cfg:{ datasource : ${columnCombobox}, allowBlank: true,}}, 
							 {data: "columnSpan3",width: "13%",orderable: false,editType:"combobox",sClass:'noError',cfg:{ datasource :columnSelect, allowBlank: true,}}, 
							 {data: "columnId3",width: "13%",orderable: false,editType:"combobox",sClass:'noError',cfg:{ datasource : ${columnCombobox}, allowBlank: true,}},
							 {data: "height",width: "15%",orderable: false, editType:"input",sClass:'noError'}, 
							 ],
							optWidth:15,
							toolbars: [{
        						label:"新增",
        						icon:"glyphicon glyphicon-plus",
        						className:"btn-success",
        						events:{
            						click:function(event){
            							indexColumnDatatables.addRow({"row":null, "columnSpan1":"", "columnId1":"", 
            								"columnSpan2":"","columnId2":"", "columnSpan3":"", "columnId3":"","height":""});
            							$('input[name="height"]').bind("input propertychange", function() { 
            								if($(this).val().length>10){
            									$(this).val($(this).val().substr(0,10));
            								}
            							}); 
            						}
        						}
        					}],
							btns:[
								{
									id:"delete",
									label:"删除",
									icon: "icon iconfont icon-shanchu",
									className: "red del",
									events:{
										"click": function(event, nRow, nData){
											var id = nData.id;
											A.confirm('您确认删除么？',function(){
												indexColumnDatatables.deleteSelectRow(nRow);
											});		
										}
									}
								}
							]
						}
					}).render();
					
					function initDataTable(){
						var params = {};
						params.length = 10;
						params.start = 0;
						params.draw = 0;
						params.conditions = [];
						$.ajax({
							url: format_url("/indexCollocation/search"),
							contentType: "application/json",
							dataType: 'JSON',
							type: 'POST',
							data : JSON.stringify(params),
							success: function(result){
								var data = result.data;
								if(data.length<1){
									return;
								}
								var maxrow = 1;
								for(var i=0 ;i<data.length;i++){
									if(data[i].row>maxrow){
										maxrow = data[i].row;
									}
								}
								for(var i=1 ;i<=maxrow;i++){
									var rowdata = {};
									rowdata.columnSpan1 = "";
									rowdata.columnId1 ="";
									rowdata.columnSpan2 = "";
									rowdata.columnId2 = "";
									rowdata.columnSpan3 = "";
									rowdata.columnId3 = "";
									for(var j=0;j<data.length;j++){
										if(i == data[j].row){
											rowdata.row = data[j].row;
											rowdata.height = data[j].height;
											if(data[j].column==1){
												rowdata.columnSpan1 = data[j].columnSpan;
												rowdata.columnId1 = data[j].columnId;
											}
											if(data[j].column==2){
												rowdata.columnSpan2 = data[j].columnSpan;
												rowdata.columnId2 = data[j].columnId;
											}
											if(data[j].column==3){
												rowdata.columnSpan3 = data[j].columnSpan;
												rowdata.columnId3 = data[j].columnId;
											}
										}
									}
									indexColumnDatatables.addRow({"row":rowdata.row,
										"columnSpan1":rowdata.columnSpan1, "columnId1":rowdata.columnId1, 
										"columnSpan2":rowdata.columnSpan2, "columnId2":rowdata.columnId2, 
										"columnSpan3":rowdata.columnSpan3, "columnId3":rowdata.columnId3,"height":rowdata.height 
	    								});
								}
							}
							
						})
					};
					initDataTable();
					
					$('#addForm').validate({
						debug:true,
						rules:  {
							id:{      maxlength:20},
							news:{    maxlength:150},
							isUrl:{   maxlength:20},
							newsUrl:{      maxlength:256},
							},
						submitHandler: function (form) {
							//添加按钮
							var url = format_url("/index");
							var obj = $("#addForm").serializeObject();
							var indexColumnTable = $("#indexColumn").arraySerializeObject();
							var indexColumnLength = indexColumnTable.length;
							var indexColumnList = [];
							var indexColumnIds = [];
							for(var i=0;i<indexColumnLength;i++){
								var colsum = 0 ;
								if(indexColumnTable[i].columnSpan1!=null && indexColumnTable[i].columnSpan1!=""){
									colsum +=parseInt(indexColumnTable[i].columnSpan1);
									var indexColumnObj = {};
									indexColumnObj.row = i+1;
									indexColumnObj.column = 1;
									indexColumnObj.height = indexColumnTable[i].height;
									indexColumnObj.columnSpan = indexColumnTable[i].columnSpan1;
									indexColumnObj.columnId = indexColumnTable[i].columnId1;
									if(indexColumnTable[i].columnId1==null || indexColumnTable[i].columnId1==""){
										alert("请选择第"+(i+1)+"行,第1列的栏目");
										return;
									}
									if(isInArray(indexColumnIds,indexColumnTable[i].columnId1)){
										alert("第"+(i+1)+"行,第1列的栏目重复无法保存");
										return;
									}
									if(indexColumnTable[i].columnId1==1 && indexColumnTable[i].columnSpan1==1){
										alert("代办任务栏目最少占用2列，请重新填写");
										return;
									}
									indexColumnIds.push(indexColumnTable[i].columnId1);
									indexColumnList.push(indexColumnObj);
								}
								if(indexColumnTable[i].columnSpan2!=null && indexColumnTable[i].columnSpan2!=""){
									colsum +=parseInt(indexColumnTable[i].columnSpan2);
									var indexColumnObj = {};
									indexColumnObj.row = i+1;
									indexColumnObj.column = 2;
									indexColumnObj.height = indexColumnTable[i].height;
									indexColumnObj.columnSpan = indexColumnTable[i].columnSpan2;
									indexColumnObj.columnId = indexColumnTable[i].columnId2;
									if(indexColumnTable[i].columnId2==null || indexColumnTable[i].columnId2==""){
										alert("请选择第"+(i+1)+"行,第2列的栏目");
										return;
									}
									if(isInArray(indexColumnIds,indexColumnTable[i].columnId2)){
										alert("第"+(i+1)+"行,第2列的栏目重复无法保存");
										return;
									}
									if(indexColumnTable[i].columnId2==1 && indexColumnTable[i].columnSpan2==1){
										alert("代办任务栏目最少占用2列，请重新填写");
										return;
									}
									indexColumnIds.push(indexColumnTable[i].columnId2);
									indexColumnList.push(indexColumnObj);
								}
								if(indexColumnTable[i].columnSpan3!=null && indexColumnTable[i].columnSpan3!=""){
									colsum +=parseInt(indexColumnTable[i].columnSpan3);
									var indexColumnObj = {};
									indexColumnObj.row = i+1;
									indexColumnObj.column = 3;
									indexColumnObj.height = indexColumnTable[i].height;
									indexColumnObj.columnSpan = indexColumnTable[i].columnSpan3;
									indexColumnObj.columnId = indexColumnTable[i].columnId3;
									if(indexColumnTable[i].columnId3==null || indexColumnTable[i].columnId3==""){
										alert("请选择第"+(i+1)+"行,第3列的栏目");
										return;
									}
									if(isInArray(indexColumnIds,indexColumnTable[i].columnId3)){
										alert("第"+(i+1)+"行,第3列的栏目重复无法保存");
										return;
									}
									if(indexColumnTable[i].columnId3==1 && indexColumnTable[i].columnSpan3==1){
										alert("代办任务栏目最少占用2列，请重新填写");
										return;
									}
									indexColumnIds.push(indexColumnTable[i].columnId3);
									indexColumnList.push(indexColumnObj);
								}
								if(colsum!=3){
									alert("第"+(i+1)+"行的所占列数不合理请重写填写");
									return;
								}
								
							}
							obj.collocationList = indexColumnList;
							
							$.ajax({
								url : url,
								contentType : 'application/json',
								dataType : 'JSON',
								data : JSON.stringify(obj),
								cache: false,
								type : 'POST',
								success: function(result){
									alert('保存成功');
// 									listFormDialog.close();
									A.loadPage({
										render : '#page-container',
										url : format_url('/index/index')
									});
								},
								error:function(v,n){
									alert('保存失败');
								}
							});
						}
					});
					$("#saveBtn").on("click", function(){
						if(checkSoft2Form().form()){
							$("#addForm").submit();
						}
    				});
					/**
					 * 使用循环的方式判断一个元素是否存在于一个数组中
					 * @param {Object} arr 数组
					 * @param {Object} value 元素值
					 */
					function isInArray(arr,value){
					    for(var i = 0; i < arr.length; i++){
					        if(value === arr[i]){
					            return true;
					        }
					    }
					    return false;
					}
					function checkSoft2Form(){
						return  $('#indexColumn').validate({
							 debug:true,
							 rules: {height: { number: true } }
						 }) ;
					}
				});
			});
        </script>
    </body>
</html>