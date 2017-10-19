package com.blog.service.gather.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.bean.gather.GatherContent;
import com.blog.dao.mapper.gather.GatherContentMapper;
import com.blog.service.gather.api.IGatherContent;
import com.blog.util.gather.Gather;
import com.blog.util.gather.GatherUtil;
import com.blog.util.method.HtmlGenerator;
import com.blog.util.method.SimpleDateFormatUtils;
import com.blog.util.method.StringUtil;
import com.blog.util.pojo.PageVo;

@Service
public class GatherContentImpl implements IGatherContent {

	@Autowired
	private GatherContentMapper gatherContentMapper;

	@Override
	public Map<String,Object> gather(Gather g) {
		Map<String,Object> map = new HashMap<String,Object>();
		String htmlSource = GatherUtil.get().getHtmlSource(g.getUrl(),g.getCharset());//HTML源代码
		Document document = Jsoup.parse(htmlSource);//获取document对象
		Elements elements =document.getElementsByTag("a");//获取所有a标签
		Set<String> sets = new HashSet<String>();/*去掉重复的URL*/ 
		for (Element e : elements) { 
			String href = e.attr("href");
			if(StringUtil.isNotEmpty(href) && href.startsWith(g.getPrefix())){
				sets.add(href);
			}
		}
		int count = 0;
		/*循环URL,进行抓取*/
		for (String url : sets) {
			try {
//				Document doc = Jsoup.connect(url).get();/*connect方法解析URL*/
				Document doc = Jsoup.parse(GatherUtil.get().getHtmlSource(url,g.getCharset()));//HTML源代码
				/*页面标题*/
				String title = doc.title();
				/*文章标题*/
				String titleContent="";
				if(g.getTitleMark()==1){
					titleContent = doc.getElementById(g.getTitleRegex()).text();
				}else if(g.getTitleMark()==2){
					titleContent = doc.getElementsByTag(g.getTitleRegex()).first().text();
				}else if(g.getTitleMark()==3){
					titleContent = doc.getElementsByClass(g.getTitleRegex()).first().text();
				}
				/*文章内容*/
				String content="";
				if(g.getContentMark()==1){
					content = doc.getElementById(g.getContentRegex()).html();
				}else if(g.getContentMark()==2){
					content = doc.getElementsByTag(g.getContentRegex()).first().html();
				}else if(g.getContentMark()==3){
					content = doc.getElementsByClass(g.getContentRegex()).first().html();
				}
				System.out.println("====================================================");
				System.out.println("页面标题:"+title);
				System.out.println("文章标题:"+titleContent);
				System.out.println("内容:"+content);
				GatherContent cms = new GatherContent();
				cms.setContent(content);
				cms.setTitle(titleContent);
				cms.setHisUrl(url);
//				list.add(cms);
				int c = gatherContentMapper.insert(cms);//添加数据库
				if(c>0){
					count++;
				}
			} catch (Exception e) {
				continue;
			}
		}
		int urlSize = sets.size();
		map.put("urlSize", urlSize);//有效的URL条数
		map.put("count", count);//保存成功条数
		return map;
	}
	
	
	
	//后台内容新闻列表
	@Override
	public PageVo<GatherContent> getContentList(Integer isOnline, Integer type, Integer isHot, Integer isTop, String startDate, String endDate, Integer limit, Integer offset) {
		Date start = null;
		Date end = null;
		if(StringUtil.isNotEmpty(startDate)){
			start =SimpleDateFormatUtils.convert(startDate, false);
		}
		if(StringUtil.isNotEmpty(endDate)){
			end = SimpleDateFormatUtils.convertForce(endDate, true);
		}
		//获取数量
		int count = gatherContentMapper.getCount(isOnline,type,isHot,isTop,start,end);
		PageVo<GatherContent> page= new PageVo<GatherContent>(count,offset,limit);
		//根据条件分页获取所有数据
		List<GatherContent> rows = gatherContentMapper.getContentByPage(isOnline,type,isHot,isTop,start,end,page.getPageStartNumber(),page.getPageEndNumber());
		page.setRows(rows);
		return page;
	}

	//根据ID查询信息
	@Override
	public GatherContent getContentById(Long id) {
		return gatherContentMapper.selectByPrimaryKey(id);
	}

	//页面静态化
	@Override
	public String staticHtml(String url,String realPath,Long id) {
		try {
			String link = null;
			GatherContent content = gatherContentMapper.selectByPrimaryKey(id);
			ResourceBundle res = ResourceBundle.getBundle("config");
			String ip = res.getString("ip");
			if(content!=null && StringUtil.isNotEmpty(content.getStaticLink())){
				link = content.getStaticLink();
			}
			HtmlGenerator generator = new HtmlGenerator(ip);
			link = generator.createHtmlPage(url,realPath,link);
			if(StringUtil.isEmpty(link)){
				return "500";
			}
			GatherContent contentG = new GatherContent();
			contentG.setId(id);
			contentG.setStaticLink(link);
			contentG.setUpdateTime(new Date());
			gatherContentMapper.updateByPrimaryKeySelective(contentG);
			return "200";
		} catch (Exception e) {
			e.printStackTrace();
			return "500";
		}
	}

	@Override
	public Map<String, Object> del(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isEmpty(id)){
			map.put("code",400);
			return map;
		}
		Integer c = gatherContentMapper.deleteByPrimaryKey(Long.valueOf(id));
		if(c>0){
			map.put("code",200);
		}else{
			map.put("code",300);
		}
		return map;
	}
	
	
}
