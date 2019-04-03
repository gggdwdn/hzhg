<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<link rel="icon" type="image/x-icon" href="../static/images/top_center_logo1.png" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/WEB-INF/views/common/meta.jsp"%>

<script type="text/javascript">
			Aptech = A = {};
			Aptech.CONTEXT_ROOT_PATH = "${ctx}";
			Aptech.UID = "${uid}";
		</script>
<!-- bootstrap & fontawesome -->
<link rel="stylesheet"
	href="${ctx}/static/css/themes/${theme}/firstPage.css" />
<link rel="stylesheet"
	href="${ctx}/static/css/themes/${theme}/bootstrap.min.css" />
<link rel="stylesheet"
	href="${ctx}/static/fonts/awesome/css/font-awesome.min.css" />

<!-- page specific plugin styles -->
<link rel="stylesheet"
	href="${ctx}/static/css/themes/${theme}/select2.min.css" />

<!-- text fonts -->
<link rel="stylesheet"
	href="${ctx}/static/css/themes/${theme}/fonts.googleapis.com.css" />

<!-- ace styles -->
<link rel="stylesheet"
	href="${ctx}/static/css/themes/${theme}/ace.min.css"
	class="ace-main-stylesheet" id="main-ace-style" />

<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
<link rel="stylesheet"
	href="${ctx}/static/css/themes/${theme}/ace-skins.min.css" />
<link rel="stylesheet"
	href="${ctx}/static/css/themes/${theme}/ace-rtl.min.css" />

<!-- 滚动样式 -->
<link rel="stylesheet"
	href="${ctx}/static/css/themes/${theme}/liMarquee.css" />
<!-- gritter -->
<link rel="stylesheet"
	href="${ctx}/static/css/themes/${theme}/jquery.gritter.min.css" />
<!-- 计时器组件 -->
<link rel="stylesheet"
	href="${ctx}/static/css/themes/${theme}/flipclock.css" />

<link rel="stylesheet"
	href="${ctx}/static/css/themes/${theme}/style.css" />
<link rel="stylesheet"
	href="${ctx}/static/css/themes/${theme}/iconfont.css" />
<link rel="stylesheet"
	href="${ctx}/static/css/themes/${theme}/iconfont2.css" />
<link rel="stylesheet"
	href="${ctx}/static/css/themes/${theme}/iconfont3.css" />
<!-- <link rel="stylesheet" href="${ctx}/static/css/core.css" /> 
		<link href="${ctx}/favicon.ico" rel="shortcut icon">-->

<%-- 		<link rel="stylesheet" href="${ctx}/static/css/themes/${theme}/nwScroll.css" /> --%>
<link rel="stylesheet"
	href="${ctx}/static/css/themes/${theme}/jquery.jscrollpane.css" />
<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${ctx}/static/css/ace-ie.min.css" />
		<![endif]-->
<!-- inline styles related to this page -->
<link href="${ctx }/static/css/themes/default/css/exam.css"
	rel="stylesheet" type="text/css">
<!-- ace settings handler -->
<script src="${ctx}/static/js/ace/ace-extra.min.js"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
<!--[if lte IE 8]>
		<script src="assets/js/html5shiv.min.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->

<title>主页面</title>
<style>

/* #messageShowPage>a { */
/* 	cursor: pointer; */
/* } */
</style>
</head>
<body class="no-skin" scroll="no">
	<%@ include file="/WEB-INF/views/common/header2.jsp"%>
	<%@ include file="/WEB-INF/views/common/sidebar2.jsp"%>
	<div class="main-container ace-save-state" id="main-container">
		<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>
		<%-- 				<%@ include file="/WEB-INF/views/common/sidebar.jsp" %>  --%>
		<%-- 				<%@ include file="/WEB-INF/views/common/sidebarOther.jsp" %> --%>
		<div class="main-content">
			<!-- <div class="ace-settings-container" id="ace-settings-container">
						<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
							<i class="ace-icon fa fa-cog bigger-130"></i>
						</div>
						<div class="ace-settings-box clearfix" id="ace-settings-box" style='height:39px;'>
							<div class="pull-left width-50">
								<div class="ace-settings-item">
									<div class="pull-left">
										<select id="skin-colorpicker" class="hide">
											<option data-skin="no-skin" value="#438EB9">#438EB9</option>
											<option data-skin="skin-1" value="#222A2D">#222A2D</option>
											<option data-skin="skin-2" value="#C6487E">#C6487E</option>
											<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
										</select>
									</div>
									<span>更换皮肤</span>
								</div>
							</div>
						</div>
					</div> -->
			<div class="main-content-inner" id="page-container">
				<!-- 页面渲染 -->
			</div>
		</div>
		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
		<!-- <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
					<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
				</a> -->
<!-- 		<div id='messageShowPage'> -->
<!-- 			<a>您收到一条新消息，请您及时查看!</a> -->
<!-- 		</div> -->
	</div>
	<%@ include file="/WEB-INF/views/common/script.jsp"%>

	<script type="text/javascript">
				template.openTag = '<!--[';
				template.closeTag = ']-->';
// 				$('#sidebar').niceScroll({
// 					horizrailenabled:false,
// 				    cursorcolor: "#023b59",//#CC0071 光标颜色
// 				    cursoropacitymax: 1, //改变不透明度非常光标处于活动状态（scrollabar“可见”状态），范围从1到0
// 				    touchbehavior: false, //使光标拖动滚动像在台式电脑触摸设备
// 				    cursorwidth: "5px", //像素光标的宽度
// 				    cursorborder: "0", // 游标边框css定义
// 				    cursorborderradius: "5px",//以像素为光标边界半径
// 				    autohidemode: 'scroll'  //是否隐藏滚动条
// 				});
				/* setTimeout(function(){
					$('#sidebar').perfectScrollbar();
				},2000) */
				var settings = {
						showArrows: true,
						autoReinitialise: true
					};
				var pane = $('#sidebar')
				pane.jScrollPane(settings);
				$('#page-container').niceScroll({
					horizrailenabled:false,
				    cursorcolor: "#ccc",//#CC0071 光标颜色
				    cursoropacitymax: 1, //改变不透明度非常光标处于活动状态（scrollabar“可见”状态），范围从1到0
				    touchbehavior: false, //使光标拖动滚动像在台式电脑触摸设备
				    cursorwidth: "5px", //像素光标的宽度
				    cursorborder: "0", // 游标边框css定义
				    cursorborderradius: "5px",//以像素为光标边界半径
				    autohidemode: 'scroll' //是否隐藏滚动条
				});
				$("#btnHome").on("click", function(){
					window.location.href=format_url("/centralized/home");
				});
				$("#btnLight").on("click", function(){
					window.location.href=format_url("/centralized/light?sid=3");
				});
				$("#btnWind").on("click", function(){
					window.location.href=format_url("/realTimeDisplay/windfield/index/damaoqi");
				});
				$("#test").on("click", function(){
					window.location.href=format_url("/sysUser/test");
				});
				$("#btnScreen").on('click', function(){
					window.location.href=format_url("/centralized/home");
				});
				$("#logo-title").on('click', function(){
					A.loadPage({
						render : '#page-container',
						url : format_url("/demo/index")
					});
				});
				seajs.use([ 'dialog'], function(A) {
					$("#info").on("click", function() {
						var id = <%=sysUserEntity.getId()%>;
						profileDialog = new A.dialog({
							title:"个人信息维护",
							height:470,
							width:650,
							url:format_url("/sysUser/profileView/"+ id),
						}).render();
					});
					$("#password").on("click", function() {
						var id = <%=sysUserEntity.getId()%>;
						userDialog = new A.dialog({
							title:"修改密码",
							height:350,
							width:550,
							url:format_url("/sysUser/reset/"+ id),
						}).render();
					});
					/* var clock = new A.clock({
						id:'#clock'
					}).render(); */
				});
// 		 		var int = self.setInterval('changeHeaderMessage()',300000);
				changeHeaderMessage();
				function changeHeaderMessage(){
// 					$.ajax({
// 						url :format_url("/messageCenter/searchMessageCount"),
// 						contentType : 'application/json',
// 						dataType : 'JSON',
// 						cache: false,
// 						type : 'POST',
// 						success: function(result){
// 							var messageNum = result.data;
// 							messageNum>99?messageNum='99+':messageNum=messageNum;
// 							messageNum==0?messageNum='0':messageNum=messageNum;
// 							if(messageNum>0||messageNum=='99+'){
// // 								$('#messageShowPage').find('.badge').show();
// // 								$('#messageShowPage span').text(messageNum);
// 								$('#messageShowPage').find('.warn-point').show();
// 							}
// 						}
// 					});
					$.ajax({
						url :format_url("/messageCenter/searchTaskCount"),
						contentType : 'application/json',
						dataType : 'JSON',
						cache: false,
						type : 'POST',
						success: function(result){
							var waittingNum = result.data;
							waittingNum>99?waittingNum='99+':waittingNum=waittingNum;
							waittingNum==0?waittingNum='0':waittingNum=waittingNum;
							if(waittingNum>0||waittingNum=='99+'){
// 								$('#waittingMission').find('.badge').show();
// 								$('#waittingMission span').text(waittingNum);
								$('#waittingMission').find('.warn-point').show();
							}
						}
					});
				}
// 				$('.messageShowPageA').on('click',function(){
// 					A.loadPage({
// 						render : '#page-container',
// 						url : format_url('/messageCenter/index')
// 					});	
// 				})
				$('#waittingMessage').on('click',function(){
					A.loadPage({
						render : '#page-container',
						url : format_url('/messageCenter/index')
					});	
				})
				$('#waittingMission').on('click',function(){
					A.loadPage({
						render : '#page-container',
						url : format_url('/todoTask/list/1/10')
					});	
				})
				function getHeadImg(){
					var url = "<%=request.getContextPath()%><%=sysUserEntity.getHeadUrl()%>";
					$.ajax({
						type : "GET",
						cache : false,
						url : url,
						data : "",
						success : function() {
							$('#headImg').attr('src', url);
						},
						error : function() {
							$('#headImg').attr('src',
									'${ctx}/static/images/avatars/user.png');
						}
					});
					/* 					var xmlHttp ;
					 if (window.ActiveXObject)
					 {
					 xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
					 }
					 else if (window.XMLHttpRequest)
					 {
					 xmlHttp = new XMLHttpRequest();
					 }
					 xmlHttp.open("Get",url,false);
					 xmlHttp.send();
					 if(xmlHttp.status==404){
					 $('#headImg').attr('src','${ctx}/static/images/avatars/user.png');
					 }
					 else{
					 $('#headImg').attr('src',url);
					 } */
		}

		getHeadImg();
		function chargeKeepChecked(id) {
			var getText = jQuery.trim($(id)[0].innerText);
			var chargeTrue = false;
			if (getText == '保存') {
				$(id)[0].innerText = '提交中...';
				$(id).addClass('disabled');
			} else {
				chargeTrue = true;
			}
			return chargeTrue;
		}
		function firstPage() {
			A.loadPage({
				render : '#page-container',
				url : format_url('/login/firstPage')
			});
			if ($('#sidebar-collapseOther').hasClass('menu-min')) {
				$(this).css('left', '2px');
			} else {
				$(this).css('left', '190px');
			}
		}
		//初始化加载首页公告栏
		firstPage();
		//首页跳转
		$('#indexImgA').on('click', function() {
			firstPage();
		})
		$('#sidebar-collapseOther').on('click', function() {
			if ($(this).hasClass('menu-min')) {
				$(this).removeClass('menu-min');
				$(this).css('left', '190px');
				$('#sidebar').css('width', '190px');
				$('.sidebar+.main-content').css('margin-left', '190px');
				$('#border_p').css('left', '190px');
			} else {
				$(this).addClass('menu-min')
				$(this).css('left', '2px');
				$('#sidebar').css('width', '2px');
				$('.sidebar+.main-content').css('margin-left', '2px');
				$('#border_p').css('left', '0px');
			}
		});
		$("#changeUnit").on("click", function() {
			var id =
	<%=sysUserEntity.getId()%>
		;
			profileDialog = new A.dialog({
				title : "单位变更",
				height : 250,
				width : 650,
				url : format_url("/sysUser/getChangeUnit/" + id),
			}).render();
		});
		var clock = $('.clock').FlipClock({
			clockFace : 'TwentyFourHourClock',
			showSeconds : false
		});
		/* var clock = new FlipClock($('.clock'),1, {
			clockFace: 'Counter',
		});
		setTimeout(function() {
			setInterval(function() {
				clock.increment();
			}, 1000*60*60*24);
		}); */
		$("#logout").on("click", function() {
			var storage = window.localStorage;
			storage.clear();
			window.location.href = format_url("/login/logout");
		});
		// 				if(chargeKeepChecked("#editInstockBtn")){
		// 					return;
		// 				}
		
		//手写textarea验证
      function chargeNull(id){
        var myId = $(id);
        var myParentDiv = myId.parent();
        var charge = '';
        if(!myId.val()){
        	if(!myParentDiv.hasClass("has-error")){
                myParentDiv.addClass("has-error");
                myId.addClass("error");
                myParentDiv.append("<span class='spanError'>必填项</span>");
        	}
          charge = true;
        }else{
          myParentDiv.removeClass("has-error");
          myId.removeClass("error");
          myParentDiv.children(".spanError").remove();
          charge = false;
        }
        myId.keyup(function(){
          if(!myId.val()){
        	  if(!myParentDiv.hasClass("has-error")){
                  myParentDiv.addClass("has-error");
                  myId.addClass("error");
                  myParentDiv.append("<span class='spanError'>必填项</span>");
          	  }
            charge = true;
          }else{
            myParentDiv.removeClass("has-error");
            myId.removeClass("error");
            myParentDiv.children(".spanError").remove();
            charge = false;
          }
        });
        return charge;
      }
	</script>
</body>
</html>
