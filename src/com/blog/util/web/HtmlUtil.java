package com.blog.util.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.blog.util.json.InterFaceJSONUtil;
import com.blog.util.json.JSONUtil;


/**
 * 版权所有：2016
 * 类描述：输出流工具类
 * 创建人：jinmengyu 
 * 创建时间：2016-12-26 上午10:35:34
 */
public class HtmlUtil {
	
	/**
	 * 传入字符串输出
	 * @param response
	 * @param jsonStr json字符串
	 */
	public static void writerJson(HttpServletResponse response,String jsonStr) {
			writer(response,jsonStr);
	}
	
	/**
	 * 传入对象输出json格式 
	 * @param response
	 * @param object 对象
	 */
	public static void writerJson(HttpServletResponse response,Object object){
			try {
				response.setContentType("application/json");
				writer(response,JSONUtil.toJSONString(object));
			} catch (JSONException e) {
				e.printStackTrace();
			}
	}
	
	public static void writerJson(HttpServletResponse response,Object object,String callback){
		try {
			response.setContentType("application/json");
			if(callback==null || callback.isEmpty()){
				writer(response,JSONUtil.toJSONString(object));
			}else{
				writer(response,callback+"("+JSONUtil.toJSONString(object)+")");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	public static void writerJson(HttpServletResponse response,Object object,String column,String callback){
		try {
			response.setContentType("application/json");
			if(callback==null || callback.isEmpty()){
				writer(response,InterFaceJSONUtil.toJSONStringG(object,column));
			}else{
				writer(response,callback+"("+InterFaceJSONUtil.toJSONStringG(object,column)+")");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 输出HTML代码
	 * @param response
	 * @param htmlStr 
	 */
	public static void writerHtml(HttpServletResponse response,String htmlStr) {
		writer(response,htmlStr);
	}
	
	/**
	 * 向页面输出
	 * @param response
	 * @param str 要输出的字符串
	 */
	private static void writer(HttpServletResponse response,String str){
		try {
			StringBuffer result = new StringBuffer();
			//设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out= null;
			out = response.getWriter();
			System.out.println("向页面输出的json:"+str);
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}
