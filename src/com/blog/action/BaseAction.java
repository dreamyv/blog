package com.blog.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import com.blog.core.base.DateEditor;
import com.blog.util.ip.TmIpUtil;
import com.blog.util.method.StringUtil;
import com.blog.util.web.Constant;
import com.blog.util.web.HtmlUtil;


@Controller
public class BaseAction {

	protected final Logger  logger = LoggerFactory.getLogger(this.getClass());  
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());// 注册,时间类型装换
	}
	
	/**
	 * 获取IP地址
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 转发
	 * @param viewName 页面名称
	 * @param context 传值
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView forword(String viewName, Map context) {
		return new ModelAndView("backend/"+viewName, context);
	}

	public ModelAndView error(String errMsg) {
		return new ModelAndView("error");
	}

	/**
	 * 提示成功信息
	 * @param message
	 */
	public void sendSuccessMessage(HttpServletResponse response, String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.SUCCESS, true);
		if(StringUtil.isEmpty(message)){
			message = "发送成功";
		}
		result.put(Constant.MSG, message);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 提示失败信息
	 * @param message
	 */
	public void sendFailureMessage(HttpServletResponse response, messageCode c) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.SUCCESS, false);
		result.put(Constant.MSG, c.message);
		result.put(Constant.CODE, c.code);
		HtmlUtil.writerJson(response, result);
	}
	
	/**
	 * 提示失败信息
	 * @param message
	 */
	public void sendFailureMessage(HttpServletResponse response, String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.SUCCESS, false);
		result.put(Constant.MSG, message);
		HtmlUtil.writerJson(response, result);
	}

	
	
	//messageCode
 	public static enum messageCode{
 		NNACCOUNT(1001,"账户不可为空！"),
 		NNMOBILE(1002,"手机不可为空！"),
 		NNPWD(1002,"密码不能为空！"),
 		NRMORPWD(2005,"账户或密码不正确！"),
 		NROLDPWD(2006,"旧密码不正确！"),
 		SYSERROR(3001,"系统异常！"),
 		APPERROR(3002,"应用错误！"),
 		SQLERROR(3003,"数据库异常！"),
 		VARERROR(3004,"非法参数！"),
 		FORBIDDEN(3009,"非法访问！"),
 		SMSCOUNT(4001,"今天发送的次数过多，请稍后再试。"),
 		INUSEEMAIL(4002,"该email已被使用！"),
 		INUSEMOBILE(4003,"该手机号已被使用！"),
 		INUSEIDCARD(4004,"该身份证号已被使用！"),
 		DENIEDUSER(4005,"该用户被禁用！"),
 		TIMEOUT(4006,"登陆超时，请重新登陆！");
 		
 		public int code;
		public String message;
		private messageCode(int code, String message) {
			this.code = code;
			this.message = message;
		}
		public static messageCode get(int code) {
			messageCode[] values = messageCode.values();
			for (messageCode object : values) {
				if (object.code == code) {
					return object;
				}
			}
			return null;
		}
 	}
 	
 	/**
 	 * 获取登录对象
 	 */
// 	public DdEtpbind  getUserObj(HttpServletRequest request){
// 		DdEtpbind ddetpbind = (DdEtpbind) SessionUtils.getAttr(request, Constant.SESSION_CRMUSERBIND);//获取登录对象
// 		return ddetpbind;
// 	}
 	
 	
	
}
