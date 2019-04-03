<%@ page language="java" contentType="text/html; charset=utf-8"  
    pageEncoding="utf-8"%>  
<!DOCTYPE html>
<html lang="zh">
<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<%@ include file="/WEB-INF/views/common/meta.jsp" %>	
		<title>404 Error Page</title>
</head>
	<body>
		<div class="breadcrumbs ace-save-state" id="breadcrumbs">
			<ul class="breadcrumb">
				<li>
					<i class="ace-icon fa fa-home home-icon"></i>
					<a href="javascript:void(0);" onclick="firstPage()">首页</a>
				</li>
	
				<li>
					<a href="#">错误页面</a>
				</li>
				<li class="active">Error 404</li>
			</ul><!-- /.breadcrumb -->
		</div>
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->

					<div class="error-container">
						<div class="well">
							<h1 class="grey lighter smaller">
								<span class="blue bigger-125">
									<i class="ace-icon fa fa-sitemap"></i>
									404
								</span>
								Page Not Found
							</h1>

							<hr />
							<h3 class="lighter smaller">岳能小伙伴已经尽力了, 但是没有找到页面...</h3>

							<div>
								<form class="form-search">
									<span class="input-icon align-middle">
										<i class="ace-icon fa fa-search"></i>

										<input type="text" class="search-query" placeholder="Give it a search..." />
									</span>
									<button class="btn btn-sm" type="button">Go!</button>
								</form>

								<div class="space"></div>
								<h4 class="smaller">Try one of the following:</h4>

								<ul class="list-unstyled spaced inline bigger-110 margin-15">
									<li>
										<i class="ace-icon fa fa-hand-o-right blue"></i>
										Re-check the url for typos
									</li>

									<li>
										<i class="ace-icon fa fa-hand-o-right blue"></i>
										Read the faq
									</li>

									<li>
										<i class="ace-icon fa fa-hand-o-right blue"></i>
										Tell us about it
									</li>
								</ul>
							</div>

							<hr />
							<div class="space"></div>

							<div class="center">
								<a href="javascript:history.back()" class="btn btn-grey">
									<i class="ace-icon fa fa-arrow-left"></i>
									返回
								</a>

								<a href="#" class="btn btn-primary">
									<i class="ace-icon fa fa-tachometer"></i>
									首页
								</a>
							</div>
						</div>
					</div>

					<!-- PAGE CONTENT ENDS -->
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.page-content -->
	</body>
</html>