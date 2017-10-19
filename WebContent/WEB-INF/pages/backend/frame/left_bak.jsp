<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="j" uri="/WEB-INF/tld/j.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%   
	String url=request.getAttribute("javax.servlet.forward.request_uri").toString();  
 	pageContext.setAttribute("url", url);
%>  
<!-- 左侧导航栏start -->
<div class="left-main left-full">
	<div class="sidebar-fold">
		<span class="glyphicon glyphicon-menu-hamburger"></span>
	</div>
	<div class="subNavBox">
		<div class="sBox">
			<div class="subNav 
				<j:if test="${j:indexOf(url,'/index/')!=-1}">
						<j:then>sublist-down</j:then>
						<j:else>sublist-up</j:else>
				</j:if>
			" 
				<j:if test="${j:indexOf(url,'/index/')!=-1}">
						<j:then>data-slide="1"</j:then>
						<j:else>data-slide="0"</j:else>
				</j:if>
			 >
				<span class="title-icon glyphicon  
					<j:if test="${j:indexOf(url,'/index/')!=-1}">
						<j:then> glyphicon-chevron-down</j:then>
						<j:else> glyphicon-chevron-up</j:else>
					</j:if>
				"></span>
				<span class="sublist-title">用户中心</span>
			</div>
			<ul class="navContent" 
				<j:if test="${j:indexOf(url,'/index/')!=-1}">
					<j:then>style="display:block"</j:then>
					<j:else>style="display:none"</j:else>
				</j:if>
			>
				<li
					<j:if test="${j:indexOf(url,'/index/index')!=-1}">
						<j:then>class="active"</j:then>
						<j:else></j:else>
					</j:if>
				>
					<a href="${basePath}/bk/l/index/index">
						<span class="sublist-icon glyphicon glyphicon-user"></span>
						<span class="sub-title">账号管理</span>
					</a>
				</li>
				<li>
					<a  href="javascript:void(0)">
						<span class="sublist-icon glyphicon glyphicon-envelope"></span>
						<span class="sub-title">消息中心</span>
					</a>
				</li>
				<li>
					<a  href="javascript:void(0)"><span
						class="sublist-icon glyphicon glyphicon-bullhorn"></span><span
						class="sub-title">短信</span>
					</a>
				</li>
				<li>
					<a  href="javascript:void(0)">
					<span class="sublist-icon glyphicon glyphicon-credit-card"></span>
						<span class="sub-title">实名认证</span>
					</a>
				</li>
			</ul>
		</div>
		<%-- 网页特效 --%>
		<div class="sBox">
			<div class="subNav 
				<j:if test="${j:indexOf(url,'/test/')!=-1}">
						<j:then>sublist-down</j:then>
						<j:else>sublist-up</j:else>
				</j:if>
			"
				<j:if test="${j:indexOf(url,'/test/')!=-1}">
						<j:then>data-slide="1"</j:then>
						<j:else>data-slide="0"</j:else>
				</j:if>
			>
				<span class="title-icon glyphicon 
					<j:if test="${j:indexOf(url,'/test/')!=-1}">
						<j:then>glyphicon-chevron-down</j:then>
						<j:else>glyphicon-chevron-up</j:else>
					</j:if>"></span>
				<span class="sublist-title">网页特效</span>
			</div>
			<ul class="navContent" 
				<j:if test="${j:indexOf(url,'/test/')!=-1}">
					<j:then>style="display:block"</j:then>
					<j:else>style="display:none"</j:else>
				</j:if>
			>
				<li
					<j:if test="${j:indexOf(url,'/echarts')!=-1}">
						<j:then>class="active"</j:then>
						<j:else></j:else>
					</j:if>
				>
					<a  href="${basePath}/bk/l/test/echarts">
						<span class="sublist-icon glyphicon glyphicon-credit-card"></span>
						<span class="sub-title">图文报表</span>
					</a>
				</li>
				<li
					<j:if test="${j:indexOf(url,'/tree')!=-1}">
						<j:then>class="active"</j:then>
						<j:else></j:else>
					</j:if>
				>
					<a  href="${basePath}/bk/l/test/tree">
						<span class="sublist-icon glyphicon glyphicon-credit-card"></span>
						<span class="sub-title">keke树形菜单</span>
					</a>
				</li>
				<li
					<j:if test="${j:indexOf(url,'/ztree')!=-1}">
						<j:then>class="active"</j:then>
						<j:else></j:else>
					</j:if>
				>
					<a  href="${basePath}/bk/l/test/ztree">
						<span class="sublist-icon glyphicon glyphicon-credit-card"></span>
						<span class="sub-title">ztree树形菜单</span>
					</a>
				</li>
			</ul>
		</div>
		
		<%-- 系统管理 --%>
		<div class="sBox">
			<div class="subNav 
				<j:if test="${j:indexOf(url,'/sys/')!=-1}">
						<j:then>sublist-down</j:then>
						<j:else>sublist-up</j:else>
				</j:if>
			"
				<j:if test="${j:indexOf(url,'/sys/')!=-1}">
						<j:then>data-slide="1"</j:then>
						<j:else>data-slide="0"</j:else>
				</j:if>
			>
				<span class="title-icon glyphicon 
					<j:if test="${j:indexOf(url,'/sys/')!=-1}">
						<j:then>glyphicon-chevron-down</j:then>
						<j:else>glyphicon-chevron-up</j:else>
					</j:if>"></span>
				<span class="sublist-title">系统管理</span>
			</div>
			<ul class="navContent" 
				<j:if test="${j:indexOf(url,'/sys/')!=-1}">
					<j:then>style="display:block"</j:then>
					<j:else>style="display:none"</j:else>
				</j:if>
			>
				<li
					<j:if test="${j:indexOf(url,'/toUser')!=-1}">
						<j:then>class="active"</j:then>
						<j:else></j:else>
					</j:if>
				>
					<a  href="${basePath}/bk/l/sys/toUser">
						<span class="sublist-icon glyphicon glyphicon-credit-card"></span>
						<span class="sub-title">用户管理</span>
					</a>
				</li>
				<li
					<j:if test="${j:indexOf(url,'/toRole')!=-1}">
						<j:then>class="active"</j:then>
						<j:else></j:else>
					</j:if>
				>
					<a  href="${basePath}/bk/l/sys/toRole">
						<span class="sublist-icon glyphicon glyphicon-credit-card"></span>
						<span class="sub-title">角色管理</span>
					</a>
				</li>
			</ul>
		</div>
		<%-- 爬虫管理 --%>
		<div class="sBox">
			<div class="subNav 
				<j:if test="${j:indexOf(url,'/gather/')!=-1}">
						<j:then>sublist-down</j:then>
						<j:else>sublist-up</j:else>
				</j:if>
			"
				<j:if test="${j:indexOf(url,'/gather/')!=-1}">
						<j:then>data-slide="1"</j:then>
						<j:else>data-slide="0"</j:else>
				</j:if>
			>
				<span class="title-icon glyphicon 
					<j:if test="${j:indexOf(url,'/gather/')!=-1}">
						<j:then>glyphicon-chevron-down</j:then>
						<j:else>glyphicon-chevron-up</j:else>
					</j:if>"></span>
				<span class="sublist-title">爬虫管理</span>
			</div>
			<ul class="navContent" 
				<j:if test="${j:indexOf(url,'/gather/')!=-1}">
					<j:then>style="display:block"</j:then>
					<j:else>style="display:none"</j:else>
				</j:if>
			>
				<li
					<j:if test="${j:indexOf(url,'/toContent')!=-1}">
						<j:then>class="active"</j:then>
						<j:else></j:else>
					</j:if>
				>
					<a  href="${basePath}/bk/l/gather/toContent">
						<span class="sublist-icon glyphicon glyphicon-credit-card"></span>
						<span class="sub-title">新闻内容管理</span>
					</a>
				</li>
				
			</ul>
		</div>
		
		<%-- 音乐管理 --%>
		<div class="sBox">
			<div class="subNav 
				<j:if test="${j:indexOf(url,'/music/')!=-1}">
						<j:then>sublist-down</j:then>
						<j:else>sublist-up</j:else>
				</j:if>
			"
				<j:if test="${j:indexOf(url,'/music/')!=-1}">
						<j:then>data-slide="1"</j:then>
						<j:else>data-slide="0"</j:else>
				</j:if>
			>
				<span class="title-icon glyphicon 
					<j:if test="${j:indexOf(url,'/music/')!=-1}">
						<j:then>glyphicon-chevron-down</j:then>
						<j:else>glyphicon-chevron-up</j:else>
					</j:if>"></span>
				<span class="sublist-title">音乐管理</span>
			</div>
			<ul class="navContent" 
				<j:if test="${j:indexOf(url,'/music/')!=-1}">
					<j:then>style="display:block"</j:then>
					<j:else>style="display:none"</j:else>
				</j:if>
			>
				<li
					<j:if test="${j:indexOf(url,'/music')!=-1}">
						<j:then>class="active"</j:then>
						<j:else></j:else>
					</j:if>
				>
					<a  href="${basePath}/bk/l/music">
						<span class="sublist-icon glyphicon glyphicon-credit-card"></span>
						<span class="sub-title">音乐管理</span>
					</a>
				</li>
				<li
					<j:if test="${j:indexOf(url,'/toContent')!=-1}">
						<j:then>class="active"</j:then>
						<j:else></j:else>
					</j:if>
				>
					<a  href="${basePath}/bk/l/music/toMusicType">
						<span class="sublist-icon glyphicon glyphicon-credit-card"></span>
						<span class="sub-title">音乐类型管理</span>
					</a>
				</li>
				
			</ul>
		</div>
		
		
	</div>
</div>
<!-- 左侧导航栏end -->
