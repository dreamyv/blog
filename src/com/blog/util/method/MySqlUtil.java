package com.blog.util.method;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时备份SQL
 */
public class MySqlUtil {

	private final static Logger logger = LoggerFactory.getLogger(MySqlUtil.class);
	private static InputStream in = null;
	private static InputStreamReader xx = null;
	private static BufferedReader br = null;
	private static OutputStreamWriter writer = null;
	private static FileOutputStream fout = null;

	public static void backup() {
		try {
			// 读取配置文件中的信息
			ResourceBundle res = ResourceBundle.getBundle("config");
			// MySql的安装bin目录路径和dump等参数
			String sqlurl = res.getString("sqlurl");
			// 保存备份文件的路径
			String path = res.getString("path");
			File file = new File(path);
			if (!file.exists())file.mkdirs();//如果文件夹不存在，创建
			path = path + "//myblog_" + TmDateUtil.getNowFormateDate() + ".sql";//SQL文件名称
			Runtime rt = Runtime.getRuntime(); // 返回与当前的Java应用程序的运行时对象
			// 调用 调用mysql的安装目录的命令
			Process child = rt.exec(sqlurl);
			// 设置导出编码为utf-8。这里必须是utf-8
			// 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
			in = child.getInputStream();// 控制台的输出信息作为输入流
			xx = new InputStreamReader(in, "utf-8");
			// 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			// 组合控制台输出信息字符串
			br = new BufferedReader(xx);
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
			// 要用来做导入用的sql目标文件：
			fout = new FileOutputStream(path);
			writer = new OutputStreamWriter(fout, "utf-8");
			writer.write(outStr);
			writer.flush();
			logger.info("定时备份SQL成功!");
		} catch (Exception e) {
			logger.error("定时备份SQL出错!\r\n"+e.getMessage());
		} finally {
			try {
				if(in!=null)in.close();
				if(xx!=null)xx.close();
				if(br!=null)br.close();
				if(writer!=null)writer.close();
				if(fout!=null)fout.close();
			} catch (IOException e) {
				logger.error("定时备份SQL关闭流出错!\r\n"+e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		MySqlUtil.backup(); // 备份数据库
	}

}
