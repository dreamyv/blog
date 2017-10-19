package com.blog.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.blog.dao.bean.sys.SysUser;
import com.blog.util.method.StringUtil;
import com.blog.util.web.SessionUtils;
import com.blog.util.web.UrlRegulation;

/**
 * 类描述： 权限拦截-springMVC拦截器
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

	protected final Logger  logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 返回true放行
	 * false：拦截 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
//		logger.info(TmDateUtil.getNowPlusTime()+"spring拦截器==访问url:"+path);
		if(path.indexOf(UrlRegulation.SecurityPrefix.BACKGROUONT+UrlRegulation.RequestPrefix.REQ_LOGIN+"/")!=-1){
			SysUser user = SessionUtils.getUserBackend(request);
//			执行是一个ajax请求还是一个普通请求
			String requestType = request.getHeader("X-Requested-With");
//			如果是ajax请求
			if(StringUtil.isNotEmpty(requestType) && requestType.equalsIgnoreCase("XMLHttpRequest")){
				if(user==null){
					logger.info("该用户登录超时");
					response.getWriter().print("logout");
					return false;
				}
			}else{
//				未登录，到登陆页
				if(user==null){
					response.sendRedirect(request.getContextPath()+"/bk/n/user/login");
					return false;
				}
			}
		}
		//放行
		return super.preHandle(request, response, handler);
	}
	

	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
//		System.out.println("postHandle拦截前置=============================:"+new Date().getTime());
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) throws Exception {
//		System.out.println("afterCompletion拦截后置=============================:"+new Date().getTime());
		super.afterCompletion(request, response, handler, ex);
	}

}
