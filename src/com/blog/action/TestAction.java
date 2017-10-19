package com.blog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.blog.util.web.UrlRegulation;

@Controller
@RequestMapping(UrlRegulation.SecurityPrefix.BACKGROUONT+UrlRegulation.RequestPrefix.REQ_LOGIN+UrlRegulation.BizPrefixBackend.TEST)
public class TestAction extends BaseAction {

//	@Autowired
//	private TUserServceApi tUserServce;
	
	@RequestMapping("/test")
	public void test(HttpServletRequest request, HttpServletResponse response) {
		sendSuccessMessage(response, "123");
	}
	@RequestMapping("/test1")
	@ResponseBody
	public String test1(HttpServletRequest request, HttpServletResponse response) {
		return "123123";
	}
	/**
	 * echarts图文报表-地图
	 * @return
	 */
	@RequestMapping("/echarts")
	public ModelAndView echarts(HttpServletRequest request, HttpServletResponse response) {
		return forword("/test/echarts", null);
	}
	/**
	 * 树形菜单 keke
	 */
	@RequestMapping("/tree")
	public ModelAndView tree(HttpServletRequest request, HttpServletResponse response) {
		return forword("/test/tree", null);
	}
	/**
	 * 树形菜单 ztree
	 */
	@RequestMapping("/ztree")
	public ModelAndView ztree(HttpServletRequest request, HttpServletResponse response) {
		return forword("/test/ztree", null);
	}
	
	
	
	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			String url = "http://www.yangqq.com/jstt/bj/2015-02-14/744.html";
//			String htmlSource =
//			GatherUtil.getHtmlSource(url,"GBK");//HTML源代码
			//System.out.println(htmlSource);
					System.out.println(i);
		}
		
	}
}
