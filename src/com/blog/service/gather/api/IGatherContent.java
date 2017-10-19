package com.blog.service.gather.api;

import java.util.Map;

import com.blog.dao.bean.gather.GatherContent;
import com.blog.util.gather.Gather;
import com.blog.util.pojo.PageVo;

public interface IGatherContent {

	//爬取信息
	public Map<String,Object> gather(Gather g);
	//后台内容新闻列表
	public PageVo<GatherContent> getContentList(Integer isOnline, Integer type, Integer isHot, Integer isTop, String startDate, String endDate, Integer limit, Integer offset);
	//根据ID查询信息
	public GatherContent getContentById(Long id);
	//页面静态化
	public String staticHtml(String url,String realPath,Long id);
	
	public Map<String, Object> del(String id);
	
}
