package com.blog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.blog.util.web.UrlRegulation;

@Controller
@RequestMapping(UrlRegulation.SecurityPrefix.BACKGROUONT+UrlRegulation.RequestPrefix.REQ_LOGIN+UrlRegulation.BizPrefixPc.INDEX)
public class IndexAction extends BaseAction {

	/**
	 * 到后台主页
	 */
	@RequestMapping("/index")
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) {
		return forword("/index/index", null);
	}

}
