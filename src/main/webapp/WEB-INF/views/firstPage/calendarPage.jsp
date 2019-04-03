<%@ page language="java" contentType="text/html; charset=utf-8"  
    pageEncoding="utf-8"%>  
<!DOCTYPE html>
<html lang="zh">
	<head>
    	<meta charset="UTF-8">
    	<%@ include file="/WEB-INF/views/common/meta.jsp" %> 
    </head>
	<body>
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<div class="row calendarBox">
				<div class="col-sm-9">
					<div class="space"></div>
					<div id="calendar"></div>
				</div>
				<div class="col-sm-3" id="externalEventBox">
				</div>	
			</div>
			<!-- PAGE CONTENT ENDS -->
		</div><!-- /.col -->
		<script type="text/javascript">
			jQuery(function($) {
				seajs.use(['calendar','confirm'], function(A){
					var calendar = new A.calendar({
						render : '#calendar',
						aspectRatio:2.5//宽高比
	            	}).render();
					
				});
			});
        </script>
    </body>
</html>