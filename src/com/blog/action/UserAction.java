package com.blog.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.blog.dao.bean.sys.SysUser;
import com.blog.service.sys.api.ISysUser;
import com.blog.util.ip.TmIpUtil;
import com.blog.util.web.CommonCode;
import com.blog.util.web.Constant;
import com.blog.util.web.SessionUtils;
import com.blog.util.web.UrlRegulation;

@Controller
@RequestMapping(UrlRegulation.SecurityPrefix.BACKGROUONT+UrlRegulation.RequestPrefix.REQ_NO_LOGIN+UrlRegulation.BizPrefixBackend.USER)
public class UserAction extends BaseAction {
//	private  Logger logger = Logger.getLogger(UserAction.class);
	protected final Logger  logger = LoggerFactory.getLogger(this.getClass());  
	
	@Autowired
	private ISysUser sysUserImpl;
	
	@Autowired
	private OtherAction otherAction;
	
	/**
	 * 到登陆页面
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin) {
		return new ModelAndView("/backend/login");
	}
	
	/**
	 * 登录 
	 * @param account 账号
	 * @param pwd	密码
	 * @param code 验证码
	 *  @param clientDevice 设备1PC,2手机,3微信,4ios,5andoird,99未知
	 * @return
	 */
	@RequestMapping("/toLogin")
	@ResponseBody
	public String toLogin(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="account") String account,
			@RequestParam(value="pwd") String pwd,
			@RequestParam(value="code") String code,
			@RequestParam(value="clientDevice",defaultValue="1") Integer clientDevice){
		try {
			String ip = TmIpUtil.getIpAddress(request);
			String ipAddress = TmIpUtil.ipLocation(request);
			String sessionCode = (String) SessionUtils.getAttr(request,Constant.SESSION_BK_LOGIN);
			Map<String,Object> map = sysUserImpl.login(account,pwd,code,sessionCode,ip,clientDevice,ipAddress);
			Integer retCode = (Integer) map.get(CommonCode.LoginCode.USER_CODE);
			if(retCode.equals(CommonCode.Login.SUCCESS)){
				SysUser user = (SysUser) map.get(CommonCode.LoginCode.USER_BACKDE);//登录用户
				SessionUtils.setUserBackend(request, user);//存session
			}
			logger.info("登录返回CODE:"+retCode);
			return retCode.toString();
		} catch (Exception e) {
			logger.info("后台登录出错:"+e.getMessage());
			return null;
		}
	}
	
	/**
	 * 登录图片验证码
	 */
	@RequestMapping("/code")
	public void code(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin) throws Exception{
		otherAction.sendKaptchaImage(request, response, Constant.SESSION_BK_LOGIN);
	}
	
	
	/**
	 * 登录图片验证码
	 */
	@RequestMapping("/loginOut")
	public ModelAndView loginOut(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin) throws Exception{
		SessionUtils.removeUseBackendr(request);//清除登录对象
		return forword("/login", null);
	}
	
	
	
}
