package com.blog.action;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
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

import com.blog.dao.bean.gather.GatherContent;
import com.blog.service.gather.api.IGatherContent;
import com.blog.util.gather.Gather;
import com.blog.util.pojo.PageVo;
import com.blog.util.web.UrlRegulation;

/**
 * 数据抓取
 */
@Controller
@RequestMapping(UrlRegulation.SecurityPrefix.BACKGROUONT+UrlRegulation.RequestPrefix.REQ_LOGIN+UrlRegulation.BizPrefixBackend.GATHER)
public class GatherAction extends BaseAction {
	
	protected final Logger  logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IGatherContent gatherContentImpl;
	/**
	 * 到内容管理页面
	 * @return
	 */
	@RequestMapping("/toContent")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin) {
		return new ModelAndView("/backend/gather/content");
	}
	
	/**
	 * 后台内容新闻列表
	 * @param isOnline 是否上线
	 * @param type	新闻类型
	 * @param isHot	是否热点
	 * @param isTop	是否置顶
	 * @param startDate 开始时间
	 * @param endDate	结束时间
	 * @param limit 一页数量
	 * @param offset 当前页码
	 * @return
	 */
	@RequestMapping("/contentList")
	@ResponseBody
	public PageVo<GatherContent> contentList(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="isOnline",required=false)Integer isOnline,
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="isHot",required=false)Integer isHot,
			@RequestParam(value="isTop",required=false)Integer isTop,
			@RequestParam(value="startDate",required=false)String startDate,
			@RequestParam(value="endDate",required=false)String endDate,
			@RequestParam(value="pageSize",defaultValue="20")Integer limit,
			@RequestParam(value="pageNumber",defaultValue="1")Integer offset){
		PageVo<GatherContent> list = gatherContentImpl.getContentList(isOnline,type,isHot,isTop,startDate,endDate,limit,offset);
		return list;
	}
	
	/**
	 * 开始爬虫
	 * @param url  要爬的网站
	 * @param prefix 过滤URL
	 * @param titleMark 标题类型 1：ID 2：标签 3Class
	 * @param titleRegex 标题
	 * @param contentMark 内容类型 1：ID 2：标签 3Class
	 * @param contentRegex 内容
	 * @param charset 字符编码
	 * @return
	 */
	@RequestMapping("/gatherContent")
	@ResponseBody
	public Map<String,Object>  gather(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="url",required=false)String url,
			@RequestParam(value="prefix",required=false)String prefix,
			@RequestParam(value="titleMark",required=false)Integer titleMark,
			@RequestParam(value="titleRegex",required=false)String titleRegex,
			@RequestParam(value="contentMark",required=false)Integer contentMark,
			@RequestParam(value="contentRegex",required=false)String contentRegex,
			@RequestParam(value="charset",required=false)String charset){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			Gather g = new Gather();
			g.setCharset(charset);
			g.setContentMark(contentMark);
			g.setContentRegex(contentRegex);
			g.setPrefix(prefix);
			g.setTitleMark(titleMark);
			g.setTitleRegex(titleRegex);
			g.setUrl(url);
			map = gatherContentImpl.gather(g);
			Integer count = (Integer) map.get("count");
			Integer urlSize = (Integer) map.get("urlSize");
			String info = "匹配的URL条数:"+urlSize+",保存成功的条数:"+count;
			logger.info(info);
			map.put("code",200);
		} catch (Exception e) {
			map.put("code",500);
//			return MethodUtil.string2Unicode("参数错误,请重新填写");
		}
		return map;
	}
	
	@RequestMapping("/staticHtml")
	@ResponseBody
	public String staticHtml(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="url",required=false)String url,
			@RequestParam(value="id",required=false)Long id){
		String realPath = request.getSession().getServletContext().getRealPath("/");
		return gatherContentImpl.staticHtml(url,realPath,id);
	}
	
	
	/**
	 * 删除数据
	 * @param url  要爬的网站
	 * @param prefix 过滤URL
	 * @param titleMark 标题类型 1：ID 2：标签 3Class
	 * @param titleRegex 标题
	 * @param contentMark 内容类型 1：ID 2：标签 3Class
	 * @param contentRegex 内容
	 * @param charset 字符编码
	 * @return
	 */
	@RequestMapping("/del")
	@ResponseBody
	public Map<String,Object>  del(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="id",required=false)String id){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			map = gatherContentImpl.del(id);
			Integer count = (Integer) map.get("count");
			Integer urlSize = (Integer) map.get("urlSize");
		} catch (Exception e) {
			map.put("code",500);
//			return MethodUtil.string2Unicode("参数错误,请重新填写");
		}
		return map;
	}
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		//1:导入jsoup.jar
		//2:获取网页源代码
		//3:匹配网页源代码的内容  url、图片、文本
		//4:获取URL中的内容,保存到数据库中
		//抓取信息第一种方式(没有请求头,不能模拟浏览器访问)
//		Document document = Jsoup.parse(new URL("https://www.baidu.com/"), 3000);
////	String s =document.html();
//		String s =document.text();
//		System.out.println(s);
		
		
	}
	
	
	

}
