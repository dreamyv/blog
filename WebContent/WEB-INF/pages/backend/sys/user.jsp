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
							<h4>用户管理</h4>
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
									    <label class="control-label col-md-2 col-sm-2 col-xs-12" for="first-name">用户状态:</label>
									        <div id="gender" class="col-md-5 col-sm-6 col-xs-12 btn-group" data-toggle="buttons">
									        	<label id="" class="btn btn-default active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
									            <input type="radio" checked  name="state" value="-1" class="state_type"/> 全部</label>
									            <label id="" class="btn btn-default " data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
									            <input type="radio"   name="state" value="1" class="state_type" />正常</label>
									            <label id="" class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
									            <input type="radio"  name="state" value="0" class="state_type" />停用</label>
									        </div>
									    </div>
									</div>
									 <!--创建时间-->
	                                 <div class="row">
	                                     <div class="item form-group">
	                                         <label class="control-label col-md-2 col-sm-2 col-xs-12" for="first-name">创建时间:</label>
	                                         <div class="col-md-2 col-sm-6 col-xs-12">
	                                             <input size="16" type="text" id="datetimeStart" placeholder="开始时间" readonly class="form_datetime form-control " >
	                                         </div>
	                                         <div class="col-md-2 col-sm-6 col-xs-12">
												<input size="16" type="text" id="datetimeEnd" placeholder="结束时间" readonly class="form_datetime form-control ">
	                                         </div>
	                                     </div>
	                                 </div>
                                 	
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
											<button type="button" id="btn_create_user" class="btn btn-warning" ><i class="fa fa-plus"></i>&nbsp;新增</button>
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
		            	
                            <form  id="form_user" action="#" class="form-horizontal form-label-left" method="POST" novalidate>
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">
                                        <span class="required">*</span>账户</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12">
                                        	<input type="hidden" value="" id="id" name="id" maxlength="12"/>
                                            <input type="text" value="" id="account" name="account" maxlength="12" required="required"  class="form-control " maxlength="11" />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">
                                        <span class="required">*</span>密码</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12">
                                            <input type="password" value=""  id="password" name="password" maxlength="12" required="required"  class="form-control "  />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">真实姓名</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12">
                                            <input type="text"  id="realName" name="realName" maxlength="12"   class="form-control "  />
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">邮箱</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12">
                                            <input type="text"  id="emails" name="emails" maxlength="22"  required="required"  class="form-control " dataType="Email" msg="信箱格式不正确" />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">年龄</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12">
                                            <input type="number"  id="age" name="age" maxlength="2"    class="form-control " />
                                        </div>
                                    </div>
                                </div>
                                 <!--是否允许登录-->
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">是否允许登录:</label>
                                        <div id="gender" class="col-md-5 col-sm-6 col-xs-12 btn-group" data-toggle="buttons">
                                            <label id="isTrue" class="btn btn-default active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                <input type="radio" checked   name="isTrue" value="1" id="isTrue_checked"> 是
                                            </label>
                                            <label id="isFalse" class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                                <input type="radio" name="isTrue" value="0"> 否
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">电话</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12">
                                            <input type="text"  id="phone" name="phone" maxlength="11"   class="form-control " />
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">头像</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12">
                                            <input type="text"  id="img" name="img" maxlength="22"   class="form-control " />
                                        </div>
                                    </div>
                                </div>
                                 
                                <!-- 职位 -->
                            	<div class="row" id="shelvesMode">
                                    <div class="item form-group ">
                                          <label class="control-label col-md-3 col-sm-2 col-xs-12" for="first-name">职位:</label>
                                          <div class="col-md-5 col-sm-6 col-xs-12">
                                            <select class="input-md form-control" id="SearCompanyId" name="companyId" >
                                                 <option value="4">普通用户</option>
                                                 <option value="1">超级管理员</option>
                                                 <option value="2">管理员</option>
                                                 <option value="3">测试</option>
                                            </select>
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
	
	
	<!-- 设置权限 start-->

            <div class="modal fade uas-modal-role" id="my_role" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog modal-md">
                    <div class="modal-content">

                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span>
                            </button>
                            <h4 class="modal-title" id="myUASModalLabel">用户管理</h4>
                        </div>
                        <div class="modal-body">

                            <form  id="form_role" action="#" class="form-horizontal form-label-left" method="POST" novalidate>
                                <input type="hidden" id="userId"/>
                                <div class="row">
                                    <div class="item form-group">
                                        <label class="control-label col-md-2 col-sm-2 col-xs-12" for="first-name">设置角色</label>
                                        <div class="col-md-5 col-sm-6 col-xs-12" id="ckb_user_role_list">

                                            <div id="tree" class="ztree" style=""></div>
                                            
                                        </div>
                                    </div>
                                </div>

                            </form>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" id="sub_user_role" class="btn btn-primary" data-id="">提交</button>
                        </div>

                    </div>
                </div>
            </div>
            <!-- 设置权限 end-->
</body>
<script type="text/javascript" src="${basePath}/resources/bk/js/sys/user.js"></script>


<script type="text/javascript">
	

	$(document).ready(function(){
	    
	    
		
	});
	
</script>
</html>