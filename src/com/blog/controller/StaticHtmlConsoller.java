package com.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.blog.dao.bean.gather.GatherContent;
import com.blog.service.gather.api.IGatherContent;
import com.blog.util.web.UrlRegulation;

@Controller
@RequestMapping(UrlRegulation.SecurityPrefix.HTTP+UrlRegulation.RequestPrefix.REQ_LOGIN+UrlRegulation.BizPrefixPc.STATICHTML)
public class StaticHtmlConsoller  {

	@Autowired
	private IGatherContent gatherContentImpl;
	
	/**
	 * 根据ID访问新闻页面
	 */
	@RequestMapping("/{id}")
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id")Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id",id);
		GatherContent content = gatherContentImpl.getContentById(id);
		map.put("content",content);
		return new ModelAndView("/pc/content",map);
	}
	
	
	

}
