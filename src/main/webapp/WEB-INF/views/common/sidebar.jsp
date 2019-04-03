<%@ page language="java" contentType="text/html; charset=utf-8"  
    pageEncoding="utf-8"%>
    
 <div id="sidebar" class="sidebar  responsive ace-save-state">
	<script type="text/javascript">
		try{ace.settings.loadState('sidebar')}catch(e){}
		function showLi(shortcut){
			$("#sidebar-menu li a").each(function(){
				var dataurl = $(this).attr("data-menuid");
				if(dataurl==shortcut){
					$(this).parent().parent().parent().siblings().removeClass("open active");
					$(this).parent().parent().parent().addClass("open active");

					$(this).click();
					
				}
			});
		}
	</script>
	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success" onclick ="showLi('${sysConfigEntity.shortcutOne}')">
				<i class="ace-icon fa fa-signal"></i>
			</button>

			<button class="btn btn-info" onclick ="showLi('${sysConfigEntity.shortcutTwo}')">
				<i class="ace-icon fa fa-pencil"></i>
			</button>

			<button class="btn btn-warning" onclick ="showLi('${sysConfigEntity.shortcutThree}')">
				<i class="ace-icon fa fa-users"></i>
			</button>

			<button class="btn btn-danger" onclick ="showLi('${sysConfigEntity.shortcutFour}')">
				<i class="ace-icon fa fa-cogs"></i>
			</button>
		</div>
		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span>

			<span class="btn btn-info"></span>

			<span class="btn btn-warning"></span>

			<span class="btn btn-danger"></span>
		</div>					
	</div>
	<ul id="sidebar-menu" class="nav nav-list">
		<li class="active">
			<a href="index.html">
				<i class="menu-icon glyphicon glyphicon-home "></i>
				<span class="menu-text"> 首页 </span>
			</a>

			<b class="arrow"></b>
		</li>

		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-align-justify"></i>
				<span class="menu-text">
					基本信息
				</span>

				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>
		</li>	
		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-flag"></i>

				运行管理
				<b class="arrow fa fa-angle-down"></b>
			</a>
			<b class="arrow"></b>
		</li>
		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-table"></i>

				两票管理
				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>
		</li>
		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-cog"></i>
				<span class="menu-text"> 设备管理 </span>

				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>
		</li>

		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-table"></i>
				<span class="menu-text"> 缺陷管理 </span>

				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>
		</li>

		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-cog"></i>

				<span class="menu-text">
					检修管理
				</span>

				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>
		</li>
		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-table"></i>

				<span class="menu-text">
					项目管理
				</span>

				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>
		</li>
		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-table"></i>

				<span class="menu-text">
					编码管理
				</span>

				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>
		</li>
		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-table"></i>

				<span class="menu-text">
					信息管理
				</span>

				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>
		</li>
		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-cubes"></i>

				<span class="menu-text">
					备品备件
				</span>

				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>
		</li>
		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-university"></i>

				<span class="menu-text">
					应用管理
				</span>

				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>
		</li>
		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-cog"></i>

				<span class="menu-text">
					系统管理
				</span>

				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>
		</li>
		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-info"></i>

				<span class="menu-text">
					流程管理
				</span>

				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>
		</li>
		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-bell"></i>

				<span class="menu-text">
					消息管理
				</span>

				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>
		</li>
		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-cubes"></i>

				<span class="menu-text">
					巡查管理
				</span>

				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>
		</li>
	</ul>
	<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
		<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
	</div>
</div>
