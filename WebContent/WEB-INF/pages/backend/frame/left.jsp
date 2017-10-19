<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="j" uri="/WEB-INF/tld/j.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%   
	String url=request.getAttribute("javax.servlet.forward.request_uri").toString();  
 	pageContext.setAttribute("url", url);/*获取当前URL*/
%>  
<!-- 左侧导航栏start -->
<div class="left-main left-full">
	<div class="sidebar-fold">
		<span class="glyphicon glyphicon-menu-hamburger"></span>
	</div>
	<!-- 左侧菜单列表 -->
	<div class="subNavBox" id="muenList">
	</div>
</div>
<!-- 左侧导航栏end -->