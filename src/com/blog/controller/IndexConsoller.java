package com.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.blog.util.web.UrlRegulation;

@Controller
@RequestMapping(UrlRegulation.SecurityPrefix.HTTP+UrlRegulation.RequestPrefix.REQ_NO_LOGIN+UrlRegulation.BizPrefixPc.INDEX)
public class IndexConsoller  {

	/**
	 * 到后台主页
	 */
	@RequestMapping("")
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/pc/index", null);
	}

}
