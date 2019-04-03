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
				<li class="active">栏目管理</li>
				<li class="active">查看</li>
			</ul><!-- /.breadcrumb -->
			<div style="margin-right:118px;margin-top:10px;">
        		<button id="backBtn" class="btn btn-xs btn-primary pull-right">
    				<i class="fa fa-reply"></i>
    				返回
    			</button>
        	</div>
        	<h5 class="table-title header smaller blue" style="margin-left:30px">基本信息</h5>
		</div>
		<div class="page-content">
		<div class="col-md-12" >
			<form class="form-horizontal" role="form"  style="margin-right:100px;margin-top:30px;" id="editForm">
				<input class="col-md-12" id="id" name="id" type="hidden" placeholder="" maxlength="20" value="${ entity.id }">
			
			  <div class="form-group" id="titleDiv">
				    <label class="col-md-2 control-label no-padding-right">
						<span style="color:red"></span>标题
				    </label>
				    <div class="col-md-4">
						<input class="col-md-12" id="title" name="title" type="text" readOnly placeholder="" maxlength="64" value="${ entity.title }">
                    </div>
                    <label class="col-md-2 control-label no-padding-right">
						<span style="color:red"></span>类型
					</label>
					<div class="col-md-4">
						<input class="col-md-12" id="type" name="type" type="text" readOnly placeholder="" maxlength="64" value="${ entity.typeString }">
                	</div>
               </div>
                <div class="form-group" id="customDiv" style="display:none;">
		       		<label class="col-md-2 control-label no-padding-right">
						<span style="color:red"></span>标题图标
					</label>
					<div class="col-md-4">
	                   <input class="col-md-12" id="headIcon" readOnly name="headIcon" type="text" placeholder="" maxlength="64" value="${ entity.headIcon }">
                	</div>
                    <label class="col-md-2 control-label no-padding-right">
						更多
				    </label>
				    <div class="col-md-4">
						<input class="col-md-12" id="moreUrl" readOnly name="moreUrl" type="text" placeholder="" maxlength="256" value="${ entity.moreUrl }">
                    </div>
               </div>
		       <div class="form-group" id="sliderDiv" style="display:none;">
				    <label class="col-md-2 control-label no-padding-right">
						<span style="color:red"></span>上传轮播图
				    </label>
				    <div class="col-xs-10" id="divfile">
					</div>
               </div>
              	<div class="form-group" id="advertisementDiv" style="display:none;">
					<label class="col-md-2 control-label no-padding-right" >
						<span style="color:red"></span>广告图片
					</label>
					<div class="col-md-10" id="image">
						<img style="width:40%;height:100px;" id="image" src="../${ entity.advertUrl }"/>
					</div>										
				</div>
				 <div class="form-group" id='linkDiv'>
		         	
               	 </div>
		         <div class="form-group" id="urlDiv" style="display:none;">
					<label class="col-md-2 control-label no-padding-right" >
						链接地址
					</label>
					<div class="col-md-10" >
	                   <input class="col-md-12" id="url" readOnly name="url" type="text" placeholder="" maxlength="256" value="${ entity.url }">
                	</div>
                </div>
			</form>
        </div>
        </div>
		<script type="text/javascript">
			jQuery(function($) {
				seajs.use(['combobox','combotree','my97datepicker','uploaddropzone','uploadify','iviewer'], function(A){
        			var appData = ${entityJson};
        			var uploaddropzone =new A.uploaddropzone({
						render : "#divfile",
						fileId:${entity.images},
						autoProcessQueue:false,//是否自动上传
						addRemoveLinks : false,//显示删除按钮
						successCallBack:true,
						successCallBackFunction:function(){
							var divList = "<div class='linkDivList' style='height:40px;'><label class='col-md-2 control-label no-padding-right' ><span style='color:red'>*</span>链接地址</label><div class='col-md-10'><input class='col-md-12 urlInput' id='url'  type='text' readOnly maxlength='256' value='' placeholder='如果不需要链接地址请填写为#'></div></div>";
							$('#linkDiv').append(divList);
						},
						deleteCallBack:true,
						deleteCallBackFunction:function(){
							var getSuccess = $('#dropzone').children('.dz-success').length;
							var getLink = $('#linkDiv').children('.linkDivList').length-1;
							if(getSuccess==getLink){$('#linkDiv').children('.linkDivList:last-child').remove();}
						}
					}).render();
        			//附件上传
        			var type=appData.type;
        			if(type=="slider"){
						$("#sliderDiv").show();
						$("#advertisementDiv").hide();
						$("#customDiv").hide();
						$("#urlDiv").hide();
					   
				        if(appData.url!=null && appData.url!=""){
	            			var linkArr = appData.url.split(',');
	            			for(var i=0;i<$('#linkDiv').children('.linkDivList').length;i++){
	            				$($('#linkDiv').children('.linkDivList')[i]).find('input').val(linkArr[i]);
	            			}
	        			}
				        $('#linkDiv').show();
						$("#advertisementImgDiv").hide();
					}else if(type=="advertisement"){
						$("#advertisementDiv").show();
						$("#customDiv").hide();
						$("#advertisementImgDiv").show();
						$("#sliderDiv").hide();
						$('#linkDiv').hide();
						$('#urlDiv').show();
					    $("#url").val(appData.url);
					}else if(type=="noHeaderBox"){
						$("#advertisementDiv").hide();
						$("#customDiv").hide();
						$("#urlDiv").show();
				        $('#linkDiv').hide();
						$("#advertisementImgDiv").hide();
						$("#sliderDiv").hide();
				        $("#url").val(appData.url);
					}else {
						$("#sliderDiv").hide();
						$("#customDiv").show();
						$("#advertisementDiv").hide();
						$("#advertisementImgDiv").hide();
						$('#linkDiv').hide();
						$('#urlDiv').show();
					    $("#url").val(appData.url);

					}
					$("#backBtn").on("click", function(){
					    $("#page-container").load(format_url('/column/index'));
   					});
					$("#image").on("click", function(){
        				var imgShowArray = [];
                        imgShowArray.push("../"+appData.advertUrl);
					    var iviewerImg = new A.iviewer({
                            url:imgShowArray
                        }).render();   					
					});
				});
			});
        </script>
    </body>
</html>