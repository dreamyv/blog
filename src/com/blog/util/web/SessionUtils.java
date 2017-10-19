package com.blog.util.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.blog.dao.bean.sys.SysUser;

/**
 * 版权所有：2017
 * 项目名称：music   
 * 类描述：session 工具类
 * 创建人：jinmengyu 
 * 创建时间：2017-1-23 下午5:39:21
 */
public final class SessionUtils {
	protected static final Logger logger = Logger.getLogger(SessionUtils.class);
	 
	private static final String SESSION_BK_USER = "session_bk_user";//后台用户session
	/**
	  * 设置session的值
	  * @param request
	  * @param key
	  * @param value
	  */
	 public static void setAttr(HttpServletRequest request,String key,Object value){
		 request.getSession(true).setAttribute(key, value);
	 }
	 
	 /**
	  * 获取session的值
	  * @param request
	  * @param key
	  * @param value
	  */
	 public static Object getAttr(HttpServletRequest request,String key){
		 return request.getSession(true).getAttribute(key);
	 }
	 
	 /**
	  * 删除Session值
	  * @param request
	  * @param key
	  */
	 public static void removeAttr(HttpServletRequest request,String key){
		 request.getSession(true).removeAttribute(key);
	 }
	 
	 /**
	  * 设置后台用户信息 到session
	  * @param request
	  * @param user
	  */
	 public static void setUserBackend(HttpServletRequest request,SysUser user){
		 request.getSession(true).setAttribute(SESSION_BK_USER, user);
	 }
	 
	 
	 /**
	  * 从session中获取后台用户信息
	  * @param request
	  * @return Users
	  */
	 public static SysUser getUserBackend(HttpServletRequest request){
		return (SysUser)request.getSession(true).getAttribute(SESSION_BK_USER);
	 }
	 
	 /**
	  * 从session中移除用户信息
	  * @param request
	  * @return Users
	  */
	 public static void removeUseBackendr(HttpServletRequest request){
		removeAttr(request, SESSION_BK_USER);
	 }
	 
//	 /**
//	  * 判断当前登录用户是否超级管理员
//	  * @param request
//	  * @return
//	  */
//	 public static void setAccessUrl(HttpServletRequest request,List<String> accessUrls){ //判断登录用户是否超级管理员
//		 setAttr(request, SESSION_ACCESS_URLS, accessUrls);
//	 }
	 
	 /**
	  * 判断URL是否可访问
	  * @param request
	  * @return
	  */
//	 public static boolean isAccessUrl(HttpServletRequest request,String url){ 
//		 List<String> accessUrls = (List)getAttr(request, SESSION_ACCESS_URLS);
//		 if(accessUrls == null ||accessUrls.isEmpty() || !accessUrls.contains(url)){
//			 return false;
//		 }
//		 return true;
//	 }
	 
	 /**
	  * 判断当前登录用户是否超级管理员
	  * @param request
	  * @return
	  */
//	 public static boolean isAdmin(HttpServletRequest request){ //判断登录用户是否超级管理员
//		 Users user =  getUser(request);
//		 if(user == null  || user.getSuperAdmin() != SuperAdmin.YES.key){
//			 return false;
//		 }
//		 return true;
//	 }
	 
	 /**
	  * 设置菜单按钮
	  * @param request
	  * @param btnMap
	  */
//	 public static void setMemuBtnMap(HttpServletRequest request,Map<String,List> btnMap){ //判断登录用户是否超级管理员
//		 setAttr(request, SESSION_MENUBTN_MAP, btnMap);
//	 }
	 
	 /**
	  * 获取菜单按钮
	  * @param request
	  * @param btnMap
	  */
//	 public static List<String> getMemuBtnListVal(HttpServletRequest request,String menuUri){ //判断登录用户是否超级管理员
//		 Map btnMap  = (Map)getAttr(request, SESSION_MENUBTN_MAP);
//		 if(btnMap == null || btnMap.isEmpty()){
//			 return null;
//		 }
//		 return (List<String>)btnMap.get(menuUri);
//	 }
	 
} 