<%@ page language="java" contentType="text/html; charset=utf-8"  
    pageEncoding="utf-8"%>  
<!DOCTYPE html>
<html lang="zh">
	<head>
    	<meta charset="UTF-8">
    	<%@ include file="/WEB-INF/views/common/meta.jsp" %> 
    </head>
	<body>
		<div class="col-md-12" >
			<form class="form-horizontal" role="form"  style="margin-right:100px;" id="editForm">
			<input type="hidden" name="id" value="${entity.id}">
			   <div class="form-group">
				    <label class="col-md-2 control-label no-padding-right">
					    标题
				    </label>
				    <div class="col-md-4">
					    <input class="col-md-12" id="title" name="title" type="text" placeholder="请输入标题" maxlength="64" value="${ entity.title }" readonly="readonly">
				    </div>	
    		     </div>
			      <div class="form-group">
				    <label class="col-md-2 control-label no-padding-right">
					   公告内容
				    </label>
				    <div class="col-md-10">
					    <textarea name="content" placeholder="" style="height:100px; resize:none;" class="col-md-12" maxlength="100" readonly="readonly">${ entity.content }</textarea>
				    </div>
			     </div>
			     <div class="form-group">
				    <label class="col-md-2 control-label no-padding-right">附件</label>
					<div class="col-md-10" id="editAttachmentDiv" readonly="readonly"></div>
			     </div>
			</form>
<!--     		<div style="margin-right:100px;"> -->
<!--         		<button id="closeBtn" class="btn btn-xs btn-primary pull-right"> -->
<!--         			<i class="ace-icon fa fa-reply"></i> -->
<!--         			返回 -->
<!--         		</button> -->
<!--         	</div> -->
        </div>
		<script type="text/javascript">
			jQuery(function($) {
				seajs.use(['combobox','combotree','my97datepicker','uploaddropzone'], function(A){
					//附件上传fileId传数组
					var uploaddropzone=new A.uploaddropzone({
						render : "#editAttachmentDiv",
						fileId:${entity.attachment},
						autoProcessQueue:false,//是否自动上传
						addRemoveLinks : false,//显示删除按钮
						chargeUp:true
					}).render();
					
					$("#closeBtn").on("click", function(){
						listFormDialog.close();
    				});
				});
			});
        </script>
    </body>
</html>