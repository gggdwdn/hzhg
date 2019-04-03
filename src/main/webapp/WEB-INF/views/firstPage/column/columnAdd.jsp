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
				<li>首页管理</li>
				<li class="active">栏目管理</li>
				<li class="active">新增</li>
			</ul>
		</div>
		<div class="page-content">
			<div class="col-md-12" >
				<div style="margin-right:100px;margin-top:10px;">
        			<button id="backBtn" class="btn btn-xs btn-primary pull-right">
    					<i class="fa fa-reply"></i>
    					返回
    				</button>
        		</div>
        		<h5 class="table-title-withoutbtn header smaller blue" style="margin-left:30px">基本信息</h5>
				<form class="form-horizontal" role="form"  style="margin-right:100px;margin-top:30px;" id="addForm">
		       		<div class="form-group" >
				    	<label class="col-md-2 control-label no-padding-right">
							<span style="color:red">*</span>标题
				    	</label>
				    	<div class="col-md-4">
							<input class="col-md-12" id="title" name="title" type="text" placeholder="" maxlength="64" value="">
	                    </div>
	                    <label class="col-md-2 control-label no-padding-right">
							<span style="color:red">*</span>类型
						</label>
						<div class="col-md-4">
							<select id="type" class="form-control chosen-select" ></select>
	                	</div>
               		</div>
               		<div class="form-group" id="customDiv" style="display:none;">
			       		<label class="col-md-2 control-label no-padding-right">
							<span style="color:red">*</span>标题图标
						</label>
						<div class="col-md-4">
		                   <input class="col-md-12" id="headIcon" name="headIcon" type="text" placeholder="" maxlength="64" value="">
	                	</div>
	                    <label class="col-md-2 control-label no-padding-right">
							<span style="color:red;"></span>更多
					    </label>
					    <div class="col-md-4">
							<input class="col-md-12" id="moreUrl" name="moreUrl" type="text" placeholder="" maxlength="256" value="">
	                    </div>
               		</div>
		       		<div class="form-group" id="sliderDiv" style="display:none;">
					    <label class="col-md-2 control-label no-padding-right">
							<span style="color:red">*</span>上传轮播图
					    </label>
					    <div class="col-xs-10" id="divfile">
						</div>
               		</div>
              		<div class="form-group" id="advertisementDiv" style="display:none;">
						<label class="col-md-2 control-label no-padding-right" >
							<span style="color:red">*</span>广告图片
						</label>
						<div class="col-md-10">
						<input id="testFile" multiple=""  name="file"  type="file" class="col-md-12">
						 <input type="hidden" id="headPhoto" />
						  <input type="hidden" id="headUrl"	 name="advertUrl"/>
						</div>										
					</div>
					<div class="form-group" id="advertisementImgDiv" style="display:none;">
						<label class="col-md-2 control-label no-padding-right" ></label>
						<div class="col-md-10">
							<img style="width:40%;height:100px;"  id="image" />
						</div>										
					</div>
				    <div class="form-group" id='linkDiv'>
				    </div>
			       	<div class="form-group" id="urlDiv" style="display:none;" >
						<label class="col-md-2 control-label no-padding-right" >
							<span style="color:red">*</span>链接地址
						</label>
						<div class="col-md-10">
		                   <input class="col-md-12" id="url" name="url" type="text" placeholder="广告类型链接地址需添加http://或者https://" maxlength="256" value="">
	                	</div>
	               	</div>
            	</form>
	    		<div style="margin-right:100px;">
<!-- 	    			<button class="btn btn-xs btn-danger pull-right"  id="cancelBtn"> -->
<!-- 	    				<i class="ace-icon fa fa-times"></i> -->
<!-- 	    				取消 -->
<!-- 	    			</button> -->
	    			<button id="saveBtn" class="btn btn-xs btn-success pull-right"  >
	    				<i class="ace-icon fa fa-floppy-o"></i>
	    				保存
	    			</button>
	    		</div>
			</div>
		</div>
		<script type="text/javascript">
			jQuery(function($) {
				seajs.use(['combobox','combotree','my97datepicker','uploaddropzone','uploadify'], function(A){
					//附件上传
					var uploaddropzone =new A.uploaddropzone({
						render : "#divfile",
						fileId:[],
						autoProcessQueue:true,//是否自动上传
						addRemoveLinks : true,//显示删除按钮
						successCallBack:true,
						successCallBackFunction:function(){
							var divList = "<div class='linkDivList' style='height:40px;'><label class='col-md-2 control-label no-padding-right' ><span style='color:red'>*</span>链接地址</label><div class='col-md-10'><input class='col-md-12 urlInput' id='url'  type='text'  maxlength='256' value='' placeholder='如果不需要链接地址请填写为#'></div></div>";
							$('#linkDiv').append(divList);
						},
						deleteCallBack:true,
						deleteCallBackFunction:function(){
							var getSuccess = $('#dropzone').children('.dz-success').length;
							var getLink = $('#linkDiv').children('.linkDivList').length-1;
							if(getSuccess==getLink){$('#linkDiv').children('.linkDivList:last-child').remove();}
						}
					}).render();
					
					var searchTypeCombobox = new A.combobox({
						render: "#type",
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
					var testFileUploader = new A.uploadify({
						render: "#testFile",
						name: "file",
						url : format_url("/file/upload"),
						type: "image",
						options:{
							droppable: true,
							thumbnail: false
						},
						callback : function(result){
							$('#headPhoto').val(result.name);
							$('#headUrl').val(result.url);
							$('#image').attr("src","../"+result.url);
						}
					}).render();
					searchTypeCombobox.change(function(){
						var type = searchTypeCombobox.getValue();
						if(type=="slider"){
							$("#sliderDiv").show();
							$("#advertisementDiv").hide();
							$("#urlDiv").hide();
							$("#customDiv").hide();
							$("#advertisementImgDiv").hide();
					        $('#linkDiv').show();
					        $("#headIcon").rules("remove");  
					        $("#url").rules("remove");  
						}else if(type=="advertisement"){
							$("#advertisementDiv").show();
							$("#customDiv").hide();
							$("#urlDiv").show();
					        $('#linkDiv').hide();
							$("#sliderDiv").hide();
							$("#url").rules("add",{required:true,maxlength:256});  
						    $("#headIcon").rules("remove"); 
						}else if(type=="noHeaderBox"){
							$("#advertisementDiv").hide();
							$("#customDiv").hide();
							$("#urlDiv").show();
					        $('#linkDiv').hide();
							$("#advertisementImgDiv").hide();
							$("#sliderDiv").hide();
						    $("#url").rules("add",{required:true,maxlength:256});  
					        $("#headIcon").rules("remove");  
						}else{
							$("#sliderDiv").hide();
							$("#customDiv").show();
							$("#urlDiv").show();
							$("#linkDiv").hide();
					        $("#url").rules("add",{required:true,maxlength:256});  
					        $("#headIcon").rules("add",{required:true,maxlength:64});  
							$("#advertisementDiv").hide();
							$("#advertisementImgDiv").hide();
						}
					});
					$('#addForm').validate({
						debug:true,
						rules:  {
							title:{ required:true,   maxlength:64},
							type:{  required:true,    maxlength:20},
							moreUrl:{      maxlength:256},
							headIcon:{      maxlength:64},
							url:{      maxlength:256},},
						submitHandler: function (form) {
							//添加按钮
							var url = format_url("/column");
							//serializeObject()用于Jquery将form转换成用于ajax参数的Javascript Object
							var obj = $("#addForm").serializeObject();
							obj.images=JSON.stringify(uploaddropzone.getValue());

							var type = searchTypeCombobox.getValue();
							if(type=="slider"){
								if(uploaddropzone.getValue()==null || uploaddropzone.getValue()+""=="" ||uploaddropzone.getValue()+""=="[]"){
									alert("请上传轮播图")
									return;
								}
								
								var flag = true;
								var urlLink = [];
								$('.urlInput').each(function(){
									if($(this).val()=="" || $(this).val()==null){
										$(this).addClass("error");
										flag = false;
									}else{
										$(this).removeClass("error");
									}
									
									urlLink.push($(this).val());
								});
								if(!flag){
									return;
								}
								obj.url = urlLink.join(",");
								obj.advertUrl = "";   
							}else if(type=="advertisement"){
								if( $('#headUrl').val()==null ||  $('#headUrl').val()==""){
									alert("请上传广告图片")
									return;
								}
								obj.images="[]";
								var fdStart = obj.url.indexOf("http://");
								var fdStart2 = obj.url.indexOf("https://");
								if(fdStart == -1 && fdStart2 ==-1){
									alert("广告类型链接地址需以http://或者https://开头");
									return;
								}
							}else {
								if($("#url").val()==null || $("#url").val()==""){
									alert("请填写链接地址");
									return;
								}
								obj.images="[]";
								obj.advertUrl="";
							}
							$.ajax({
								url : url,
								contentType : 'application/json',
								dataType : 'JSON',
								data : JSON.stringify(obj),
								cache: false,
								type : 'POST',
								success: function(result){
									if(result.result=="success"){
										alert('添加成功');
										$("#page-container").load(format_url('/column/index'));
									}else{
										alert(result.errorMsg);
									}
									
								},
								error:function(v,n){
									alert('添加失败');
								}
							});
						}
					});
					$("#saveBtn").on("click", function(){
						$("#addForm").submit();
    				});
					$("#cancelBtn").on("click", function(){
					    $("#page-container").load(format_url('/column/index'));
   					});
					$("#backBtn").on("click", function(){
					    $("#page-container").load(format_url('/column/index'));
   					});
				});
			});
        </script>
    </body>
</html>