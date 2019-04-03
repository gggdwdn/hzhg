<%@ page language="java" contentType="text/html; charset=utf-8"  
    pageEncoding="utf-8"%>
    <%@ page import="com.aptech.common.system.config.domain.SysConfigEntity" %>
        <%@ page import="com.aptech.common.system.user.domain.SysUserEntity" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>     
	<%
	SysConfigEntity sysConfigEntity= (SysConfigEntity)request.getSession().getAttribute("sysConfigEntity");
	SysUserEntity sysUserEntity= (SysUserEntity)request.getSession().getAttribute("session_key_user");
	%>
		<div id="navbar" class="navbar navbar-default  ace-save-state" style='text-align:center'>
			<div class="navbar-container ace-save-state" id="navbar-container" style='padding-right:0px !important'>
<%-- 				<span class='navbarSpanTitle'><%=sysConfigEntity.getName()%></span> --%>
				<div class="navbar-header" style='float:left'>
					<a id='indexImgA' class="navbar-brand" style="height:45px;line-height:45px;float:none;cursor:pointer">
						<span >
							<img alt="" src="../static/images/newImg/newTitle.png" style="float: left;margin-top:14px">
						</span>
					</a>
				</div>

				<div class="navbar-buttons navbar-header pull-right" role="navigation" style='margin-top:10px;'>
					<ul class="nav ace-nav">
						<%-- <li>
							<div class="clock" id='clockDiv' style='width:0px;height:45px'>
								<main>
								  <div style="display:none">
								    <div id="top-test-anim" class="num-anim top-anim" style="display:none;">
								      <div class="top-half-num"></div>
								    </div>
								    <div id="bottom-test-anim" class="num-anim bottom-anim" style="display:none;">
								      <div class="bottom-half-num">
								        <div class="dropper"></div></div>
								    </div>
								  </div>
								  <canvas id="my-canvas"></canvas>
								  <div id="clock">
								    <div class="time-container hours">
								      <div class="digit">
								        <div class="fade">&nbsp;</div>
								        <span class="num top" id="hour-tens-top"></span>
								        <span class="num bottom" id="hour-tens-bottom">
								          <div class="bottom-container"></div></span>
								          <div class="swapper">
								            <div id="top-hour-tens-anim" class="num-anim top-anim" style="display:none;">
								              <div class="top-half-num"></div>
								            </div>
								            <div id="bottom-hour-tens-anim" class="num-anim bottom-anim" style="display:none;">
								              <div class="bottom-half-num">
								                <div class="dropper"></div></div>
								            </div>
								        </div>
								        <div class="ring ring-left"></div>
								        <div class="ring ring-right"></div>
								      </div>
								      <div class="digit">
								        <div class="fade">&nbsp;</div>
								        <span class="num top" id="hour-ones-top"></span>
								        <span class="num bottom" id="hour-ones-bottom">
								          <div class="bottom-container"></div></span>
								          <div class="swapper">
								            <div id="top-hour-ones-anim" class="num-anim top-anim" style="display:none;">
								              <div class="top-half-num"></div>
								            </div>
								            <div id="bottom-hour-ones-anim" class="num-anim bottom-anim" style="display:none;">
								              <div class="bottom-half-num">
								                <div class="dropper"></div></div>
								            </div>
								        </div>
								        <div class="ring ring-left"></div>
								        <div class="ring ring-right"></div>
								      </div>
								    </div>
								    <div class="time-container minutes">
								      <div class="digit">
								        <div class="fade">&nbsp;</div>
								        <span class="num top" id="minute-tens-top"></span>
								        <span class="num bottom" id="minute-tens-bottom">
								          <div class="bottom-container"></div></span>
								          <div class="swapper">
								            <div id="top-minute-tens-anim" class="num-anim top-anim" style="display:none;">
								              <div class="top-half-num"></div>
								            </div>
								            <div id="bottom-minute-tens-anim" class="num-anim bottom-anim" style="display:none;">
								              <div class="bottom-half-num">
								                <div class="dropper"></div></div>
								            </div>
								        </div>
								        <div class="ring ring-left"></div>
								        <div class="ring ring-right"></div>
								      </div>
								      <div class="digit">
								        <div class="fade">&nbsp;</div>
								        <span class="num top" id="minute-ones-top"></span>
								        <span class="num bottom" id="minute-ones-bottom">
								          <div class="bottom-container"></div></span>
								          <div class="swapper">
								            <div id="top-minute-ones-anim" class="num-anim top-anim" style="display:none;">
								              <div class="top-half-num"></div>
								            </div>
								            <div id="bottom-minute-ones-anim" class="num-anim bottom-anim" style="display:none;">
								              <div class="bottom-half-num">
								                <div class="dropper"></div></div>
								            </div>
								        </div>
								        <div class="ring ring-left"></div>
								        <div class="ring ring-right"></div>
								      </div>
								    </div>
								    
								  </div>
								</main>
							</div>
						</li> --%>
						<li class="headIcon">
							<a  href="#" id='productManage' title='产品管理'>
								<i class="ace-icon icon iconfont icon-chanpinguanli"></i>
							</a>
						</li>
						<li class="headIcon">
							<a  href="#" id='feedbackManage' title='反馈管理'>
								<i class="ace-icon icon iconfont icon-fankuiguanli"></i>
							</a>
						</li>
						<li class="headIcon">
							<a  href="#" id='investmentAgency' title='招商代理'>
								<i class="ace-icon icon iconfont icon-zhaoshangdaili"></i>
							</a>
						</li>
						<li class="headIcon">
							<a  href="#" id='systemConfiguration' title='系统配置'>
								<i class="ace-icon icon iconfont icon-xitongpeizhi" style='font-size: 25px;'></i>
							</a>
						</li>
						<li class="headIcon">
							<a  href="#" id='waittingMission' title='待办事项'>
								<i class="ace-icon icon iconfont icon-daibanrenwu1"></i>
								<span class="badge badge-grey" ></span>
								<label class='warn-point'></label>
							</a>
						</li>

						<li class="headIcon">
							<a  href="#" title='消息' class="messageShowPageA" id='messageShowPage'>
								<i class="ace-icon icon iconfont icon-xiaoxi"></i>
								<span class="badge badge-important"></span>
								<label class='warn-point'></label>
							</a>
						</li>
						<li class='clockDiv'>
							<div class="clock" id='clockDiv' style='width:120px;height:45px'></div>
						</li>
						<li class="useInfoHead">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle" style='background-color:#fff;color:#000;padding-left: 20px;text-align:left;min-width: 180px;padding-right: 20px;'>
								<img class="nav-user-photo" id='headImg' />
								<span class="user-info">
									<%=sysUserEntity.getName()%>
									<small>管理员</small>
								</span>
<!-- 								<i class="ace-icon fa fa-caret-down" style='position:absolute;top: 10px; right: 7px;'></i>  -->
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<!-- <li>
									<a href="#">
										<i class="ace-icon fa fa-cog"></i>
										Settings
									</a>
								</li> -->

								<li>
									<a id="info" href="#">
										<i class="ace-icon fa fa-user"></i>
										个人信息
									</a>
								</li>
								<li>
									<a id="changeUnit" href="#">
										<i class="ace-icon fa fa-user"></i>
										单位变更
									</a>
								</li>
								<li>
									<a id="password" href="#">
										<i class="ace-icon fa fa-key"></i>
										密码修改
									</a>
								</li>
								<li class="divider"></li>

								<li>
									<a id="logout" href="#">
										<i class="ace-icon fa fa-sign-out"></i>
										退出
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div><!-- /.navbar-container -->
		</div>