package com.blog.util.method;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Random;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 生成静态HTML页面
 */
public class HtmlGenerator {

	HttpClient httpClient = null; //HttpClient实例  
    GetMethod getMethod =null; //GetMethod实例  
    String page = null; //页面内容
    String webappname = null;//项目路径前缀
    BufferedWriter fw = null;//写入对象
    BufferedReader br = null;//读出对象  
    InputStream in = null;  
    StringBuffer sb = null;//缓冲字符串 
    String htmlPath = null;//页面全路径
    
    protected final Logger  logger = LoggerFactory.getLogger(this.getClass());
    
    //构造方法  
    public HtmlGenerator(String webappname){  
        this.webappname = webappname;  
    }  
      
    /**
     * 根据模版及参数产生静态页面  
     * @param url 路径
     * @param path 静态页面存放路径
     * @return 相对路径
     */
    public String createHtmlPage(String url,String path,String link){  
        int statusCode = 0;               
        try{  
        	String ret = "";//返回出去的文件相对路径
        	String html = "";//文件名称
        	if(StringUtil.isEmpty(link)){
        		link ="/pages/"+TmDateUtil.formatDate(new Date(), "yyyy/MM/dd");//路径
        		html="/"+getRandomString(10)+".html";//新文件名称
        		path = path+link;
            	ret = link+html;
    			File rootFile = new File(path);
    			//如果不存在，就创建
    			if(!rootFile.exists())rootFile.mkdirs();
        	}else{
        		path = path+link;
        		ret = link;
        		File targetFile = new File(path);//目标文件
    			File parentPath = targetFile.getParentFile();//防止父级目录丢失，而造成静态化失败，判断是否父亲文件夹存在
    			if(!parentPath.exists())parentPath.mkdirs();//如果不存在，就创建父级目录
        	}
			htmlPath = path+html;//绝对路径
            //创建一个HttpClient实例充当模拟浏览器  
            httpClient = new HttpClient();  
            //设置httpclient读取内容时使用的字符集  
            httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");           
            //创建GET方法的实例  
            getMethod = new GetMethod(url);  
            //使用系统提供的默认的恢复策略，在发生异常时候将自动重试3次  
            getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());  
            //设置Get方法提交参数时使用的字符集,以支持中文参数的正常传递  
            getMethod.addRequestHeader("Content-Type","text/html;charset=UTF-8");  
            //执行Get方法并取得返回状态码，200表示正常，其它代码为异常  
            statusCode = httpClient.executeMethod(getMethod);             
            if (statusCode!=200) {  
            	logger.info("静态页面引擎在解析"+url+",产生静态页面"+htmlPath+"时出错!/n错误码:"+statusCode);  
            	return null;
            }else{  
                //读取解析结果  
                sb = new StringBuffer();  
                in = getMethod.getResponseBodyAsStream();  
                //br = new BufferedReader(new InputStreamReader(in));//此方法默认会乱码，经过长时期的摸索，下面的方法才可以  
                br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
                String line = null;  
                while((line=br.readLine())!=null){  
                    sb.append(line+"\n");  
                }  
                if(br!=null)br.close();  
                page = sb.toString();  
                //将页面中的相对路径替换成绝对路径，以确保页面资源正常访问  
                page = formatPage(page);  
                //将解析结果写入指定的静态HTML文件中，实现静态HTML生成
                writeHtml(htmlPath,page);  
                return ret;  
            }             
        }catch(Exception ex){  
        	ex.printStackTrace();
            logger.info("静态页面引擎在解析"+url+"产生静态页面,"+htmlPath+"时出错:"+ex.getMessage()+"/n错误码:"+statusCode);
            return null;
        }finally{  
            //释放http连接  
            getMethod.releaseConnection();  
        }  
    }  
      
    //将解析结果写入指定的静态HTML文件中  
    private synchronized void writeHtml(String path,String content) throws Exception{  
        fw = new BufferedWriter(new FileWriter(path));  
        OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(path),"UTF-8");  
        fw.write(page);   
        if(fw!=null)fw.close();       
    }  
      
    //将页面中的相对路径替换成绝对路径，以确保页面资源正常访问  
    private String formatPage(String page){       
        page = page.replaceAll("\\.\\./\\.\\./\\.\\./", webappname+"/");  
        page = page.replaceAll("\\.\\./\\.\\./", webappname+"/");  
        page = page.replaceAll("\\.\\./", webappname+"/");            
        return page;  
    }  
     
    //生成文件的名称
	private  String getRandomString(int length) {
		StringBuffer bu = new StringBuffer();
		String[] arr = { "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c",
				"d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "p", "q",
				"r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
				"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "P",
				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		Random random = new Random();
		while (bu.length() < length) {
			String temp = arr[random.nextInt(57)];
			if (bu.indexOf(temp) == -1) {
				bu.append(temp);
			}
		}
		return bu.toString();
	}
    
    //测试方法  
    public static void main(String[] args){  
        HtmlGenerator h = new HtmlGenerator("webappname");  
//        h.createHtmlPage("http://127.0.0.1/blog/h/l/statichtml/141","E:\\");  
        System.out.println("静态页面已经生成到c:/a.html");  
    }  
    
    
}
