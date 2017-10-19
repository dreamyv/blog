<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/taglib.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图文报表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/WEB-INF/pages/backend/common/common.jsp" %>
<!-- 引用tree控件 -->
<link rel="stylesheet" href="${basePath}/resources/bk/sg/tree/tree_keke/tm_tree.css"/>
<script type="text/javascript" src="${basePath}/resources/bk/sg/tree/tree_keke/sgutil.js"></script>
<script type="text/javascript" src="${basePath}/resources/bk/sg/tree/tree_keke/tm_tree.js"></script>

</head>
<body>
<!-- 头部 -->
<%@include file="/WEB-INF/pages/backend/frame/top.jsp" %>
	<div class="down-main">
	<!-- 左侧导航栏 -->
	<%@include file="/WEB-INF/pages/backend/frame/left.jsp" %>
	<!-- 主体start -->		
		<div class="right-product view-product right-full">
			<div class="container-fluid">
				<div class="manage account-manage info-center">
					<div class="page-header">
						<div class="pull-left">
							<h4>树形菜单</h4>
						</div>
					</div>
					<!-- 面板start -->
                      <div id="permissiontree" ></div>
					<!-- 面板end -->
				</div>
					
			</div>
		</div>
	</div>
	
</body>
<script type="text/javascript">
$(function(){
	var root = [
		{name:"2",opid:1,pid:"root1"},
		{name:"2",opid:2,pid:"root2"},
		{name:"2",opid:3,pid:"root3"},
		{name:"2",opid:4,pid:"root4"},
		{name:"2",opid:5,pid:"root5"}
		
	]
	var children={root2:[
		{name:"2",opid:11,pid:"root11"},
		{name:"2",opid:12,pid:"root12"},
		{name:"2",opid:13,pid:"root13"},
		{name:"2",opid:14,pid:"root14"}
		
	],
	root11:[
			{name:"2",opid:15,pid:"root111"},
			{name:"2",opid:16,pid:"root121"}
	]
	};


	//root:root,children:children,
	$("#permissiontree").tmTree({
		type:"checkbox"
	});
}); 

</script>
</html>