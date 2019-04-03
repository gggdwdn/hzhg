<%@ page language="java" contentType="text/html; charset=utf-8"  
    pageEncoding="utf-8"%>
      <style>
#sidebar-collapseOther{
    background: url(../static/css/themes/default1/newStyle/newStyle/menu_jiantou.png);
    background-repeat: no-repeat;
    position:fixed;
    top: 0;
    background-position-y: 50%;
    z-index: 2;
    left:2px;
    cursor: pointer;
}
.sidebar.menu-min{
	width:2px !important;
}
#sidebar p{
	position: absolute;
    top: 68px;
    left: 0px;
    width: 2px;
    height: 100%;
    z-index: 3;
    background: -webkit-gradient(linear, left top, left bottom, from(#003c6d), color-stop(#00b2cf), to(#003c6d));
    background: -webkit-linear-gradient(#003c6d, #00b2cf, #003c6d);
    background: -o-linear-gradient(#003c6d, #00b2cf, #003c6d);
    background: linear-gradient(#003c6d, #00b2cf, #003c6d);
}
#sidebar{
	width:2px;
}
.sidebar+.main-content{
	margin-left:2px;
}
</style>   
 <div id="sidebar" class="sidebar  responsive ace-save-state">
	<script type="text/javascript">
		try{ace.settings.loadState('sidebar')}catch(e){}
	</script>
	<!-- <div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success">
				<i class="ace-icon fa fa-signal"></i>
			</button>

			<button class="btn btn-info">
				<i class="ace-icon fa fa-pencil"></i>
			</button>

			<button class="btn btn-warning">
				<i class="ace-icon fa fa-users"></i>
			</button>

			<button class="btn btn-danger">
				<i class="ace-icon fa fa-cogs"></i>
			</button>
		</div>
		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span>

			<span class="btn btn-info"></span>

			<span class="btn btn-warning"></span>

			<span class="btn btn-danger"></span>
		</div>					
	</div> -->
	<ul id="sidebar-menu" class="nav nav-list">
		
	</ul>
	<p id='border_p'></p>
	<div  id="sidebar-collapseOther" class='menu-min' style='height:100%;width:20px;'>
	</div>
</div>

