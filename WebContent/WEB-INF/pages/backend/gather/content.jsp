<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/taglib.jsp"%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>内容管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="/WEB-INF/pages/backend/common/common.jsp" %>
</head>

<body>
<!-- 头部 -->
<%@include file="/WEB-INF/pages/backend/frame/top.jsp" %>
	<div class="down-main">
	<!-- 左侧导航栏 -->
	<%@include file="/WEB-INF/pages/backend/frame/left.jsp" %>
		<div class="right-product view-product right-full">
			<div class="container-fluid">
				<div class="manage account-manage info-center">
					<div class="page-header">
						<div class="pull-left">
							<h4>新闻爬虫管理</h4>
						</div>
					</div>
					<!-- 主体start -->	
					<div class="panel-body">
						<div class="panel panel-default">
						    <div class="panel-heading">查询条件</div>
						    <div class="panel-body">
								<form class="form-horizontal form-label-left" id="news_search" action="#" novalidate>
									 <!--创建时间-->
	                                <div class="row">
									  <div class="item form-group">
									    <label class="control-label col-md-2 col-sm-2 col-xs-12" for="first-name">创建时间:</label>
									        <div class="col-md-5 col-sm-6 col-xs-12">
									           <input id="crstartTime"   name="strStartDate" value="" type="hidden">
									            <input id="crendTime"   name="strEndDate" value="" type="hidden">
									            <div id="reportrange1" class="form-control col-md-12 col-xs-12">
									                <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
									                <span style="color:#555;">&nbsp;&nbsp;请选择时间范围&nbsp;</span>
									            </div>
									        </div>
									    </div>
									</div>
                                 
									<div class="row">
			                            <label class="control-label col-md-2 col-sm-2 col-xs-12" for="first-name"></label>
			                            <div class="col-md-5 col-sm-6 col-xs-12">
			                            	<!-- 解决bootstrap与AJAX异步提交表单的冲突 -->
							              	<input type="text" value="" hidden/>
							                <button type="button" class="btn btn-success" id="transfer_frmSearch"><i class="fa fa-search"></i>&nbsp;&nbsp;查 询</button>
											<button type="button" id="btn_create" class="btn btn-warning" ><i class="fa fa-plus"></i>&nbsp;新增</button>
											<button type="button" id="btn_gather" class="btn btn-warning" ><i class="fa fa-plus"></i>&nbsp;开始爬虫</button>
									     </div>
									 </div>
								</form>
						    </div>
						</div>
						<!-- 表格数据 -->   
						<div class="fit-body">   
							<table id="tb_departments" style="word-break:break-all; word-wrap:break-all;"></table>
						</div>
					</div>
					<!-- 主体end -->	
				</div>
			</div>
		</div>
	</div>
	
	<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">用户管理</h4>
		            </div>
		            <div class="modal-body">
		            	
                            <form  id="form_role" action="#" class="form-horizontal form-label-left" method="POST" novalidate>
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">
                                        <span class="required">*</span>角色名</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12">
                                        	<input type="hidden" value="" id="id" name="id" maxlength="12"/>
                                            <input type="text" value="" id="roleName" name="roleName" maxlength="10" required="required"  class="form-control " maxlength="11" />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">
                                        <span class="required">*</span>描述</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12">
                                            <input type="text" value=""  id="roleDesc" name="roleDesc" maxlength="25"   class="form-control "  />
                                        </div>
                                    </div>
                                </div>
                                
                            </form>
                            
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-default" id="sub_s" data-dismiss="modal">关闭</button>
		                <button type="submit" class="btn btn-primary" id="sub_data">提交更改</button>
		            </div>
		        </div>
		 	</div>
		</div>
	<!-- 模态框（Modal） -->
	
	
	
	<!-- 模态框（Modal）爬取网站的信息 -->
		<div class="modal fade" id="myModal_gather" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">用户管理</h4>
		            </div>
		            <div class="modal-body">
		            	
                            <form  id="form_gather" action="#" class="form-horizontal form-label-left" method="POST" novalidate>
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">
                                        <span class="required">*</span>URL</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12">
                                        	<input type="hidden" value="" id="id" name="id" maxlength="12"/>
                                            <input type="text" value="http://news.qq.com/" id="url" name="url" maxlength="50" required="required"  class="form-control "  />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">
                                        <span class="required">*</span>过滤的URL</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12">
                                            <input type="text" value="http://news.qq.com/a/"  id="prefix" name="prefix" maxlength="50" required="required"  class="form-control "  />
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="row">
								  <div class="item form-group">
								    	<label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">标题类型:</label>
								        <div id="gender" class="col-md-5 col-sm-6 col-xs-12 btn-group" data-toggle="buttons">
								        	<label id="" class="btn btn-default active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
								            <input type="radio" checked  name="titleMark" value="1" class="state_type"/>ID</label>
								            <label id="" class="btn btn-default " data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
								            <input type="radio"   name="titleMark" value="2" class="state_type" />标签</label>
								            <label id="" class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
								            <input type="radio"  name="titleMark" value="3" class="state_type" />Class</label>
								        </div>
								    </div>
								</div>
									
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">
                                        <span class="required">*</span>标题</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12">
                                            <input type="text" value="h1"  id="titleRegex" name="titleRegex" maxlength="50" required="required"   class="form-control "  />
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="row">
								  <div class="item form-group">
								    	<label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">内容类型:</label>
								        <div id="gender" class="col-md-5 col-sm-6 col-xs-12 btn-group" data-toggle="buttons">
								        	<label id="" class="btn btn-default active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
								            <input type="radio" checked  name="contentMark" value="1" class=""/>ID</label>
								            <label id="" class="btn btn-default " data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
								            <input type="radio"   name="contentMark" value="2" class="" />标签</label>
								            <label id="" class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
								            <input type="radio"  name="contentMark" value="3" class="" />Class</label>
								        </div>
								    </div>
								</div>
								
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">
                                        <span class="required">*</span>内容</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12">
                                            <input type="text" value="titdd-Article"  id="contentRegex" name="contentRegex" maxlength="50" required="required"   class="form-control "  />
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="row">
								  <div class="item form-group">
								    	<label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">字符编码:</label>
								        <div id="gender" class="col-md-5 col-sm-6 col-xs-12 btn-group" data-toggle="buttons">
								        	<label id="" class="btn btn-default active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
								            <input type="radio" checked  name="charset" value="UTF-8" class=""/>UTF-8</label>
								            <label id="" class="btn btn-default " data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
								            <input type="radio"   name="charset" value="GBK" class="" />GBK</label>
								        </div>
								    </div>
								</div>
                                
                            </form>
                            
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-default" id="sub_s" data-dismiss="modal">关闭</button>
		                <button type="submit" class="btn btn-primary" id="sub_gather">提交</button>
		            </div>
		        </div>
		 	</div>
		</div>
	<!-- 模态框（Modal） -->
	
<div style="width:100%;height:100%;position:fixed;top:0;z-index:99999;display:none;" id="dig"></div>
</body>
<script type="text/javascript" src="${basePath}/resources/bk/js/gather/content.js"></script>
<script type="text/javascript">

	
</script>
</html>