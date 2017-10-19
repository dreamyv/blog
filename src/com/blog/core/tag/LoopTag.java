package com.blog.core.tag;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 迭代循环标签
 * ContentTag.java
 * 创建人: jinmengyu       
 * 时间: 2016-4-24 下午3:33:53
 */
public class LoopTag extends TagSupport{

	private static final long serialVersionUID = 1L;

	//在标签里定义的属性一定要生成set方法。/因为在定义标签是要用set方法为属性赋值。
	private Integer begin;
	private Integer end;
	private String var;//在标签里定义的变量，用于调用值
	private Object items;//集合
	private int index=0;//
	//定义一个集合的迭代器
	@SuppressWarnings("rawtypes")
	private Iterator iterator;
	//标签开始时调用的方法
	@SuppressWarnings("rawtypes")
	public int doStartTag() throws JspException {
		//如果传入集合为空
		if(items==null)return SKIP_BODY;//忽略标签主题内容
		//强制把items装换成集合类型
		iterator=((Collection)items).iterator();//初始化迭代器
		if(iterator.hasNext()) {//判断是否有值
			this.pageContext.setAttribute(var, iterator.next());//放入值
			this.pageContext.setAttribute(var+"_index", index);//放入index
			return EVAL_BODY_INCLUDE;//要求容器执行主题标签内容	
		}else{
			return SKIP_BODY;//忽略标签主题内容
		}
		//两个固定的返回值：EVAL_BODY_INCLUDE  和   SKIP_BODY
		//SKIP_BODY:忽略标签主题内容
		//EVAL_BODY_INCLUDE:要去容器执行主题标签内容
	}
	//标签循环时调用的方法(主体)
	public int doAfterBody() throws JspException {
		if(iterator.hasNext()) {//判断是否有值
			this.pageContext.setAttribute(var, iterator.next());//放入值
			index++;
			this.pageContext.setAttribute(var+"_index",index);//放入index
			return EVAL_BODY_AGAIN;//要求容器重复执行标签主体内容	
		}else{
			return SKIP_BODY;//忽略标签主题内容
		}
		//两个固定的返回值：EVAL_BODY_AGAIN  和   SKIP_BODY
		//SKIP_BODY:忽略标签主题内容
		//EVAL_BODY_AGAIN:重复执行标签体的内容
	}

	//标签结束后调用的方法
	public int doEndTag() throws JspException {
		index=0;
		return EVAL_PAGE;
		//两个固定的返回值：EVAL_PAGE  和   SKIP_PAGE
		//SKIP_PAGE:继续运行标签后面的内容
		//EVAL_PAGE:忽略标签后面的内容
	}

	//标签结束后释放资源
	public void release() {
		iterator=null;//迭代器赋空
		var=null;
	}
	
	//getter/setter
	public Integer getBegin() {
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public String getVar() {
		return var;
	}
	public void setVar(String var) {
		this.var = var;
	}
	public Object getItems() {
		return items;
	}
	public void setItems(Object items) {
		this.items = items;
	}
	
	
}
