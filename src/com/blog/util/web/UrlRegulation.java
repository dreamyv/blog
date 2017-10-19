package com.blog.util.web;

/**
 * URL统一协议
 */
public class UrlRegulation {

	/* 请求根路径，这个不做前缀处理 */
	public static final String ROOT = "/";

	/**
	 * 第1层,https安全层
	 */
	public class SecurityPrefix {
		/* https访问地址前缀 */
		public static final String HTTPS = "/hs";
		/* http访问地址前缀 */
		public static final String HTTP = "/h";
		/* 后台前缀*/
		public static final String BACKGROUONT = "/bk";
	}

	/**
	 *  第2层,用户登陆安全层
	 */
	public class RequestPrefix {
		/* 请求需登录的前缀 */
		public static final String REQ_LOGIN = "/l";
		/* 请求无需登录的前缀 */
		public static final String REQ_NO_LOGIN = "/n";
		/* 普通请求 */
		public static final String REQ_COMMON = "/c";
	}

	/**
	 * Controller第3层,业务描述层
	 */
	public class BizPrefixPc {
		/* 配置管理 */
		public static final String CONFIGURE = "/configure";
		/* 首页 */
		public static final String INDEX = "/index";
		/* 登录  */
		public static final String LOGIN = "/login";
		/* 登录  */
		public static final String STATICHTML = "/statichtml";
		/* 音乐管理  */
		public static final String MUSIC = "/music";
		
	}

	/**
	 * action第3层,业务描述层
	 */
	public class BizPrefixBackend {
		/* 登录  */
		public static final String LOGIN = "/login";
		/* 其他  */
		public static final String OTHER = "/other";
		/* 测试  */
		public static final String TEST = "/test";
		/* 用户  */
		public static final String USER = "/user";
		/* 系统  */
		public static final String SYS = "/sys";
		/* 数据抓取  */
		public static final String GATHER = "/gather";
		
		
	}

}
