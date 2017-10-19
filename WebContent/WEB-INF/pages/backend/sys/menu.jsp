<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/taglib.jsp"%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>主页</title>
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
							<h4>角色管理</h4>
						</div>
					</div>
					<!-- 主体start    -->	
					<div class="panel-body">
						<div class="panel panel-default">
						    <div class="panel-heading">查询条件</div>
						    <div class="panel-body">
								<form class="form-horizontal form-label-left" id="news_search" action="#" novalidate>
									<div class="row">
									  <div class="item form-group">
									    <label class="control-label col-md-2 col-sm-2 col-xs-12" for="first-name">角色状态:</label>
									        <div id="gender" class="col-md-5 col-sm-6 col-xs-12 btn-group" data-toggle="buttons">
									        	<label id="" class="btn btn-default active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
									            <input type="radio" checked  name="state" value="-1" class="state_type"/>全部</label>
									            <label id="" class="btn btn-default " data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
									            <input type="radio"   name="state" value="1" class="state_type" />启用</label>
									            <label id="" class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
									            <input type="radio"  name="state" value="0" class="state_type" />停用</label>
									        </div>
									    </div>
									</div>
	                                 
                                 	<!--创建时间 进口车的插件-->
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
		                <h4 class="modal-title" id="myModalLabel">角色管理</h4>
		            </div>
		            <div class="modal-body">
		            	
                            <form  id="form_user" action="#" class="form-horizontal form-label-left" method="POST" novalidate>
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">
                                        <span class="required">*</span>菜单名称</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12">
                                        	<input type="hidden" value="" id="id" name="id" maxlength="12"/>
                                            <input type="text" value="" id="menuName" name="menuName" maxlength="22" required="required"  class="form-control " maxlength="11" />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">
                                        <span class="required">*</span>URL</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12">
                                            <input type="text" value=""  id="url" name="url" maxlength="100" required="required"  class="form-control "  />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">
                                        <span class="required">*</span>描述</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12">
                                            <input type="text" value=""  id="menuDesc" name="menuDesc" maxlength="200" required="required"  class="form-control "  />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">
                                        <span class="required">*</span>是否启用</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12">
<!--                                             <input type="text" value=""  id="isTrue" name="roleDesc" maxlength="12" required="required"  class="form-control "  /> -->
                                            <div  class="btn-group" data-toggle="buttons">
									            <label id="isTrue1" class="btn btn-default active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
									            <input type="radio"   name="isTrue" value="1" class="isTrue" checked="checked"/>启用</label>
									            <label id="isTrue2" class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
									            <input type="radio"  name="isTrue" value="0" class="isTrue" />停用</label>
									        </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">
                                        <span class="required">*</span>父节点ID</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12">
                                            <input type="text" value=""  id="parentId" name="parentId" maxlength="20" required="required" onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');"  class="form-control "  />
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">
                                        <span class="required">*</span>排序</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12">
                                            <input type="text" value=""  id="topNum" name="topNum" value="0" maxlength="20"  required="required" onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');" required="required"  class="form-control "  />
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">ICON</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12">
                                            <input type="text" value=""  id="icon" name="icon" maxlength="500" placeholder="字体图标class"   class="form-control "  />
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
	
	
	<!-- 提示框（Modal） -->
		<div class="modal fade" id="my_dialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">用户管理</h4>
		            </div>
		            <div class="modal-body">
                         <p>确定删除吗?</p>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-default" id="sub_s" data-dismiss="modal">关闭</button>
		                <button type="submit" class="btn btn-primary" id="sub_data">确定</button>
		            </div>
		        </div>
		 	</div>
		</div>
	<!-- 提示框（Modal） -->
	
	
</body>
<script type="text/javascript" src="${basePath}/resources/bk/js/sys/menu.js"></script>


<script type="text/javascript">
	
</script>
</html>