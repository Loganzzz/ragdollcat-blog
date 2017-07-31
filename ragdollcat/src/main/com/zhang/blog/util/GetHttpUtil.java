package main.com.zhang.blog.util;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** * @author zhang_chl 
    * @date 2017��7��14������10:54:41
    */
public class GetHttpUtil {

	
	public static String getHttp(String url){
		StringBuffer res=new StringBuffer();
		String line=null;
		try {
			URL uRL = new URL(url);
			URLConnection connection = uRL.openConnection();
			connection.connect();
			InputStream inputStream = connection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			while((line = bufferedReader.readLine())!=null){
				res.append(line+"\n");

			}
				
			
			bufferedReader.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return res.toString();		
	}
	
	public static String getObject(String regex, String match){
		String url = null;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(match);
		
		if(matcher.find()){
			url = matcher.group(0);
		}
		return url;
		
	}
	
	
	public static void main(String[] args) {
		String sr = "http://cn.bing.com/";
		String source = getHttp(sr);
		String url = getObject("az/hprichbg/rb/.+?jpg",source);
		String[] split = url.split("/");
		String filename = split[split.length-1];
		System.out.println(filename);
		try {
			URL uRL = new URL(sr+url);
			URLConnection connection = uRL.openConnection();
			connection.connect();
			InputStream in = connection.getInputStream();
			File file = new File("G://bk//"+filename);
			FileOutputStream fos = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int length = 0;
			while((length=in.read(buffer))!=-1){
				fos.write(buffer, 0, length);
			}
			file.createNewFile();
			fos.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
