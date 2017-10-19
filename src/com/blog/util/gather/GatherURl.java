package com.blog.util.gather;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class GatherURl {

	
	public static void main(String[] args) {
		String urlLocation = "http://mp3-cdn2.luoo.net/low/luoo/radio927/01.mp3";
//							  http://img-cdn2.luoo.net/pics/albums/12641/59569637e71e9.jpg!/fwfh/580x580
//							http://mp3-cdn2.luoo.net/low/luoo/radio927/02.mp3 
//						http://mp3-cdn2.luoo.net/low/luoo/radio927/05.mp3
									  
		for (int i = 1; i <= 10; i++) {
			String path = "1264";
			urlLocation = "http://mp3-cdn2.luoo.net/low/luoo/radio927/0"+i+".mp3";
			GatherURl.donwlond(urlLocation,"E://"+path,System.currentTimeMillis()+i+".mp3");
		}
	}
	

	
	/**
	 * 根据URL下载文件
	 * @param urlLocation
	 * @param path	文件存放地址
	 * @param fileName	文件名称
	 */
	public static void donwlond(String urlLocation,String path,String fileName){
		try {
			File file = new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			URL url  = new URL(urlLocation);//获取URL对象
			URLConnection conn = url.openConnection();//获取连接对象
			InputStream in = conn.getInputStream();//获取输入对象
			byte[] bs = new byte[2048];//缓存
			int len = 0;
			long tlen = conn.getContentLengthLong();//文件总大小
			long clen = 0;//剩余大小
			FileOutputStream out = new FileOutputStream(new File(path, fileName));//输出到硬盘的文件
			while((len = in.read(bs)) != -1){
				clen += len;
				System.out.println("总大小:"+tlen+"已下载:"+clen+"剩余:"+(tlen-clen)+"百分比:"+(clen/(float)tlen)*1000+"%");
				out.write(bs, 0, len);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public  void test(){
		String urlLocation = "http://img-cdn2.luoo.net/pics/vol/5962702f700b3.jpg!/fwfh/640x452";
		try {
			URL url  = new URL(urlLocation);
			URLConnection conn = url.openConnection();
			InputStream in = conn.getInputStream();
			byte[] bs = new byte[2048];
			int len = 0;
			long tlen = conn.getContentLengthLong();//总大小
			long clen = 0;
			FileOutputStream out = new FileOutputStream("E://11111111.jpg");
			while((len = in.read(bs)) != -1){
				clen += len;
				System.out.println("总大小:"+tlen+"已下载:"+clen+"剩余:"+(tlen-clen)+"百分比:"+(clen/(float)tlen)*1000+"%");
				out.write(bs, 0, len);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
