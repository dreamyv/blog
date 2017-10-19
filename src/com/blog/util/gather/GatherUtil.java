package com.blog.util.gather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blog.util.method.StringUtil;

/**
 * 数据收集工具类
 * @author jmy
 *
 */
public class GatherUtil {

	protected final Logger  logger = LoggerFactory.getLogger(this.getClass());
	private static GatherUtil gatherutil;
	
	public static GatherUtil get(){
		if(gatherutil == null){
			gatherutil = new GatherUtil();
		}
		return gatherutil;
	}
	
	/**
	 * 获取URL内容,模拟浏览器请求
	 * @param netLink 连接路径
	 * @param charset 编码
	 */
	public  String getHtmlSource(String netLink,String charset){
		if(StringUtil.isEmpty(charset))charset="UTF-8";
		BufferedReader br = null;
		InputStream is = null;
		InputStreamReader isr = null;
		try {
			StringBuffer sb = new StringBuffer();
			URL url = new URL(netLink);
			URLConnection conn= url.openConnection();
			/*设置请求头*/
			conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
			is  = conn.getInputStream();
			isr = new InputStreamReader(is, charset);
			br = new BufferedReader(isr);
			String line = null;
			while((line=br.readLine())!=null){
				sb.append(line+"\n"); 
			}
			return sb.toString();
		} catch (Exception e) {
			logger.info("抓取URL内容失败。URL:"+netLink);
			return "";
		}finally{
			try {
				br.close();
				isr.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		Gather g = new Gather();
		g.setCharset("GBK");
		g.setContentMark(1);
		g.setContentRegex("Cnt-Main-Article-QQ");
//		g.setImgMark(1);
//		g.setImgRegex("");
		g.setPrefix("http://news.qq.com/a/");
		g.setTitleMark(2);
		g.setTitleRegex("h1");
		g.setUrl("http://news.qq.com/");
//		GatherUtil.get().gather(g);
	}
	
	
}
