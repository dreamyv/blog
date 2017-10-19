package com.blog.core.tag;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.blog.util.method.StringUtil;
import com.blog.util.method.TmDateUtil;


/**
 * JSP自定义标签
 * TmFunctions.java
 * 创建人: jinmengyu       
 * 时间: 2016-4-21 上午12:53:48
 */
public class TmFunctions {
	/**
	 *将字符串转换为大写
     * 创建人: jinmengyu       
     * 时间: 2016-4-21 上午12:57:07     
     * @param content 传入字符串
     * @return 大写字符
	 */
	public static  String toUpper(String content){
		if(content==null) return null;
		return content.toUpperCase();
	} 
	/**
	 * 转成小写
     * 创建人: jinmengyu       
     * 时间: 2016-4-21 下午5:24:37     
     * @param content 传入字符
     * @return 小写字符
	 */
	public static  String toLower(String content){
		if(content==null) return null;
		return content.toLowerCase();
	} 
	
	/**
	 * 格式化日期自定义函数 
     * 创建人: jinmengyu       
     * 时间: 2016-4-21 下午4:26:16     
     * @param dataString 日期字符串
     * @param format 格式
     * @return 有格式的日期字符串
     * 
        System.out.println(dataFormat("2016-04-21 16:27:30","yyyy"));//获取年
		System.out.println(dataFormat("2016-04-21 16:27:30","MM"));//获取月份
		System.out.println(dataFormat("2016-04-21 16:27:30","dd"));//获取天
		System.out.println(dataFormat("2016-04-21 16:27:30","HH:mm:ss"));//获取24小时格式日期
		System.out.println(dataFormat("2016-04-21 16:27:30","hh:mm:ss"));//获取12小时格式日期
		System.out.println(dataFormat("2016-04-21 16:27:30","yyyy-MM-dd HH:mm:ss"));//获取全部
	 */
	public static String dataFormat(String dataString,String format){
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dataString);//转换Date类型
			return (new SimpleDateFormat(format).format(date));//转换成string格式
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 空判断
     * 创建人: jinmengyu       
     * 时间: 2016-4-21 下午5:16:19     
     * @param str 判断的字符串
     * @return true=空
	 */
	public static boolean isEmpty(String str) {
		return StringUtil.isEmpty(str);
	}
	
	/**
	 * 非空判断
     * 创建人: jinmengyu       
     * 时间: 2016-4-21 下午5:17:07     
     * @param str 判断的字符串
     * @return true=不为空
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	/**
	 * 传入日期格式字符串返回具体的几秒钟以前
     * 创建人: jinmengyu       
     * 时间: 2016-4-21 下午6:27:01     
     * @param startTime 传入字符串格式时间参数
     * @return 几秒钟以前，几年前
	 */
	public static String dateToString(String startTime){
		return TmDateUtil.getTimeFormat(startTime);
	}
	
	/**
	 * 传入日期返回具体的几秒钟以前
     * 创建人: jinmengyu       
     * 时间: 2016-4-21 下午7:11:34     
     * @param startTime 时间类型
     * @return 几秒钟以前，几年前
	 */
	public static String dateToString2(Date startTime){
		return TmDateUtil.getTimeFormat(startTime);
	}
	/**
	 * 将数字转换成对应的中文
     * 创建人: jinmengyu       
     * 时间: 2016-4-21 下午6:58:12     
     * @param  num 传入数字
     * @return 返回中文,如:1236 ==> 一千二百三十六
	 */
	public static String intToChnNumConverter(int num){
		return StringUtil.intToChnNumConverter(num);
	}
	
	/**
	 * 传入阿拉伯数字1~26返回对应的字母
     * 创建人: jinmengyu       
     * 时间: 2016-4-21 下午7:45:13     
     * @param num 1~26数字
     * @return A~Z
	 */
	public static String getCharacter(int num){
		return StringUtil.getCharacter(num);
	}
	
	/**
	 * 截取字符串
     * 创建人: jinmengyu       
     * 时间: 2016-4-24 下午11:15:45     
     * @param content 主体内容
     * @param begin 开始截取下标
     * @param end 结束下标
     * @return 改造后的字符串
	 */
	public static String cutContent(String content,int begin,int end){
		if(StringUtil.isEmpty(content)) return "";
		String con = content.trim();
		int len=con.length();//内容长度
		if(len > end){
			String start=con.substring(0,end);
			String reslut="<span style='display:none;'>"+con.substring(end,len)+"</span>";
			return start+reslut+"&nbsp;&nbsp;<a onclick='tm_show_exapend(this);' href='javascript:void(0);'>展开</a>";
		}else{
			return con;
		}
	}
	/**
	 * 是否包含
	 * @param reg
	 * @param val
	 * @return
	 */
	public static int indexOf(String reg,String val){
		if(StringUtil.isEmpty(reg) ||StringUtil.isEmpty(val)){
			return -1;
		}
		int index = reg.indexOf(val);
		return index;
	}
}
