package com.blog.core.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * TzIFTag
 * 创建人:xuchengfei 
 * 时间：2015年9月19日-下午11:28:46 
 * @version 1.0.0
 * 
 */
public class TmThenTag extends TagSupport{
	
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		TmIFTag parent = (TmIFTag)this.getParent();
		if(parent!=null && parent.getTest()){
			return EVAL_BODY_INCLUDE;//继续去执行标签提的内容
		}else{
			return SKIP_BODY;
		}
	}
	
}
