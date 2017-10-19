package com.blog.action;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.blog.util.web.UrlRegulation;
import com.google.code.kaptcha.Producer;

@Controller
@RequestMapping(UrlRegulation.SecurityPrefix.BACKGROUONT)
public class OtherAction {

	protected final Logger  logger = LoggerFactory.getLogger(this.getClass());  
	
	@Autowired
	private Producer captchaProducer;

	@RequestMapping("")
	public ModelAndView index(){
		return new ModelAndView("/backend/login");
	}
	
	/**
	 * 生成图片验证码
	 * @param key session键
	 */
	public void sendKaptchaImage(HttpServletRequest request,HttpServletResponse response,String key) throws Exception {
		HttpSession session = request.getSession();
		//清除浏览器的缓存
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/jpeg");
		//浏览器记忆功能-----当前过浏览器和服务器交互成功以后下载的图片和资源会进行缓存一次。下次刷新的时候就不会在到服务器去下载。
		// 获取KAPTCHA验证的随机文本
		String capText = captchaProducer.createText();
		// 讲生成好的图片放入会话中
		session.setAttribute(key, capText);
		logger.info("图片验证码:"+capText);
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		// write the data out
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();//关闭
		}
	}

}
