package com.blog.util.gather;


public class Gather implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String name;/**/
	private String url;/*访问路径*/
	private String charset;/*编码*/
	private String userName;/*作者*/
	private String prefix;/*过滤的URL*/
	private Integer titleMark;/*1:id 2:标签 3:class*/
	private String titleRegex;/*标题名称*/
	private Integer contentMark;/*1:id 2:标签 3:class*/
	private String contentRegex;/*内容名称*/
	private Integer imgMark;/*1:id 2:name 3:class*/
	private String imgRegex;/*图片名称*/
	
	public Gather(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getTitleRegex() {
		return titleRegex;
	}

	public void setTitleRegex(String titleRegex) {
		this.titleRegex = titleRegex;
	}

	public String getContentRegex() {
		return contentRegex;
	}

	public void setContentRegex(String contentRegex) {
		this.contentRegex = contentRegex;
	}

	public String getImgRegex() {
		return imgRegex;
	}

	public void setImgRegex(String imgRegex) {
		this.imgRegex = imgRegex;
	}

	public Integer getTitleMark() {
		return titleMark;
	}

	public void setTitleMark(Integer titleMark) {
		this.titleMark = titleMark;
	}

	public Integer getContentMark() {
		return contentMark;
	}

	public void setContentMark(Integer contentMark) {
		this.contentMark = contentMark;
	}

	public Integer getImgMark() {
		return imgMark;
	}

	public void setImgMark(Integer imgMark) {
		this.imgMark = imgMark;
	}
	
	
	
}
