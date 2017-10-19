<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 头部start --%>
<nav class="nav navbar-default navbar-mystyle navbar-fixed-top">
	<div class="navbar-header">
		<button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<a class="navbar-brand mystyle-brand">
			<span class="glyphicon glyphicon-home"></span>
		</a>
	</div>
	<div class="collapse navbar-collapse">
		<ul class="nav navbar-nav">
			<li class="li-border">
				<a class="mystyle-color" href="javascript:void(0)">个人博客系统</a>
			</li>
		</ul>
		<!-- 左侧 -->
		<ul class="nav navbar-nav pull-right">
			<li class="li-border">
				<a href="javascript:void(0)" class="mystyle-color"> 
					<span class="glyphicon glyphicon-bell"></span> 
					<span class="topbar-num">0</span>
				</a>
			</li>
			<li class="li-border dropdown">
				<a href="javascript:void(0)" class="mystyle-color" data-toggle="dropdown"> 
					<span class="glyphicon glyphicon-search"></span>搜索
				</a>
				<div class="dropdown-menu search-dropdown">
					<div class="input-group">
						<input type="text" class="form-control"> <span
							class="input-group-btn">
							<button type="button" class="btn btn-default">搜索</button>
						</span>
					</div>
				</div>
			</li>
			<li class="dropdown li-border">
				<a href="javascript:void(0)" class="dropdown-toggle mystyle-color" data-toggle="dropdown">
					帮助与文档 <span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="javascript:void(0)">帮助与文档</a></li>
					<li class="divider"></li>
					<li><a href="javascript:void(0)">论坛</a></li>
					<li class="divider"></li>
					<li><a href="javascript:void(0)">博客</a></li>
				</ul>
			</li>
			<li class="dropdown li-border">
				<a href="javascript:void(0)" class="dropdown-toggle mystyle-color" data-toggle="dropdown">
					那洪金福<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="${basePath}/bk/n/user/loginOut">退出</a></li>
				</ul>
			</li>
			<li class="dropdown" id="change_skin">
				<a href="javascript:void(0)" class="dropdown-toggle mystyle-color" data-toggle="dropdown">
					换肤<span class="caret"></span>
				</a>
				<ul class="dropdown-menu changecolor">
					<li id="blue"><a href="javascript:void(0)">蓝色</a></li>
					<li class="divider"></li>
					<li id="green"><a href="javascript:void(0)">绿色</a></li>
					<li class="divider"></li>
					<li id="orange"><a href="javascript:void(0)">橙色</a></li>
				</ul>
			</li>
		</ul>
	</div>
</nav>
<%-- 头部end --%>
