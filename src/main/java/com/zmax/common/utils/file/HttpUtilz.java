package com.zmax.common.utils.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtilz {
	
	/**接收的编码格式*/
	public static String ENC_RECV="UTF-8";
	/**发送的编码格式*/
	public static String ENC_SEND="ISO-8859-1";
	/**AGENT*/
	public static String agent = "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)";

	public static String get(String surl) {
		try {

			URL url=new URL(surl);
			URLConnection connect=url.openConnection();

			connect.setDoInput(true);   
			connect.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(connect.getOutputStream(),"UTF-8");
			out.flush();
			out.close();
			
			StringBuffer buffer = new StringBuffer();
			InputStream is=connect.getInputStream();
			BufferedReader in=new BufferedReader(new InputStreamReader(is));
			String temp;
			while ((temp = in.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
			in.close();
			return buffer.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	/**
	 * HTTP-GET
	 * @param surl 已经经过URLENCODER编码过的地址
	 * @return
	 */
	public static String getHttp(String surl) {
		HttpURLConnection connect=null;
		try {

			URL url=new URL(surl);
			connect=(HttpURLConnection)url.openConnection();
			connect.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:12.0) Gecko/20100101 Firefox/12.0" );
			connect.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8" );
			connect.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3" );
			connect.setRequestProperty("Connection", "keep-alive" );
			connect.setRequestProperty("Cache-Control", "max-age=0" );

			
			connect.setRequestProperty("Content-Type", "application/json");
			
			//Accept-Encoding: gzip, deflate

			
			
			
			
			
			connect.setRequestMethod("GET");
			connect.setDoInput(true);   
			connect.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(connect.getOutputStream(),ENC_SEND);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//if(connect!=null)connect.disconnect();
		}
		
		
		//读取返回内容

		StringBuffer buffer = new StringBuffer();
		BufferedReader in=null;
		try {
			in = new BufferedReader(new InputStreamReader(connect.getInputStream(),ENC_RECV));
			String temp;
			while ((temp = in.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
			in.close();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally{
			
		}
		return buffer.toString();

	}
	/**
	 * 
	 * @param surl 已经经过URLENCODER编码过的地址
	 * @param postData 已经经过URLENCODER编码过的数据,ex:aaa=joe&bbb=guessme
	 * @return
	 */
	public static String post(String surl,String postData) {
		HttpURLConnection connect=null;
		try {

			URL url=new URL(surl);
			
			connect=(HttpURLConnection)url.openConnection();
			connect.setRequestMethod("POST");
			connect.setDoOutput(true);
			connect.setDoInput(true);
			connect.setUseCaches(false);
			connect.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connect.setRequestProperty("User-Agent", agent );
			connect.setRequestProperty("Content-Length", ""+postData.length());

			OutputStreamWriter out = new OutputStreamWriter(connect.getOutputStream(),ENC_SEND);
			out.flush();
			out.close();
		} catch (Exception e) {

		}finally{
			if(connect!=null)
				connect.disconnect();
		}
		
		
		//读取返回内容

		StringBuffer buffer = new StringBuffer();
		BufferedReader in=null;
		try {
			in = new BufferedReader(new InputStreamReader(connect.getInputStream(),ENC_RECV));
			String temp;
			while ((temp = in.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
			in.close();	
		} catch (UnsupportedEncodingException e1) {

		} catch (IOException e1) {

		}finally{
			
		}
		return buffer.toString();
	}


	/**
	 * @param args
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//http://127.0.0.1:18080/user/servicej/iso
		//http://127.0.0.1:18080/user/servicej/getString?clazz=teacher&id=4&field=xm
		//System.out.println("get="+getHttp("http://127.0.0.1:8080/user/thirdparty/fckeditor/myconfig.js")); 
		System.out.println("get="+getHttp("http://127.0.0.1:8080/user/test/test9")); 
	
	
	
	}
	 */

}
