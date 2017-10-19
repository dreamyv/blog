<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/taglib.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台登录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/WEB-INF/pages/common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${basePath}/resources/bk/css/login/login.css" />
</head>
<body>
	<div class="admin_login">
		<ul>
			<li>
				<strong>个人博客后台管理系统</strong> 
				<em>Management System</em>
			</li>
			<li class="user_icon">
				<span class='fa fa-user icon'></span>
				<input type="text" placeholder="账号" class="login_txtbx" id="account" maxlength="11" value="admin" />
			</li>
			<li class="pwd_icon">
				<span class='fa fa-lock icon'></span>
				<input type="password" placeholder="密码" class="login_txtbx" id="pwd" maxlength="11" value="111111" />
			</li>
			<li class="val_icon">
				<div class="checkcode">
				<span class='fa fa-shield icon'></span>
					<input type="text" id="J_codetext" placeholder="验证码" maxlength="6" class="login_txtbx">
					<div class="codeimg_box" onclick="createCode()">
						<img alt="图片验证码" title="点我刷新" src="${basePath}/bk/n/user/code" width="95" height="40" id="code_img" >
					</div>
				</div>
			</li>
			<li>
				<input type="button" value="立即登录" class="submit_btn" />
			</li>
			<li>
				<p>© 2017-2018 spring 版权所有</p>
				<p>沪A1-20170611-1</p>
			</li>
		</ul>
	</div>
</body>
<script src="${basePath}/resources/bk/js/login/jquery.js"></script>
<script src="${basePath}/resources/bk/js/login/login.js"></script>
<script src="${basePath}/resources/bk/js/login/Particleground.js"></script>
<script>
	
</script>
</html>
