package com.zmax.mag.web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;
import com.zmax.common.entity.angularjsFileUpload.SimpAfu;
import com.zmax.common.utils.easyui.Json;
import com.zmax.common.utils.file.FileNameUtils;
import com.zmax.mag.domain.bean.User;
import com.zmax.mag.domain.utils.AESUtil;
import com.zmax.mag.web.utils.WebStaticUtils;




/**
 * 图库单图/文件上传
 * 页面打开资源管理器，选择图片后，确定就直接上传
 * 上传时，会带上不同token
 * 后台根据act以及参数，写完硬盘后，返回
 * 
 * AngularJs版的文件上传Servlet
 * 顺序：
 * (1)[options upload] 响应：无所谓了，前端不会处理
 * (2)[post upload] 
 * 内容：
 * -----------------------------24604253807794
 * Content-Disposition: form-data; name="file"; filename="16027850_980x1200_0.jpg"
 * Content-Type: image/png ...PNG...
 * 响应：
 * 成功：new Json(true, "文件Url")
 * 失败：new Json(false, "错误信息")
 * 
 * 页面上是先post后options的，但后台没有特别明显的顺序。
 * 有
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/rest/upload")
public class FileUploadAngServlet extends HttpServlet {
	private static final long serialVersionUID = -8244073279641189889L;

	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());

		
	/**key=ipAddress + req.getParameter("name") */
	//static Map<String, AngularFileUpEnt> sizeMap2 = new ConcurrentHashMap<>();
	int counter;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		service2(req, res);
	} 

	//@Override
	protected void service2(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			if (logger.isDebugEnabled())
				logger.debug("Method:"+req.getMethod());
			
			req.setCharacterEncoding("utf-8");
			res.setCharacterEncoding("utf-8");

			WebStaticUtils.makeCROS(res);
			
			//响应 OPTIONS
			if (req.getMethod().equalsIgnoreCase("OPTIONS")) {
				actOptions(res);
				return;
			}
			
			//响应返回错误指令
			if (!"OPTIONS".equalsIgnoreCase(req.getMethod()) && req.getParameter("errorCode") != null) {
				//				res.getWriter().write(req.getParameter("errorMessage"));
				//				res.getWriter().flush();
				res.sendError(Integer.parseInt(req.getParameter("errorCode")), req.getParameter("errorMessage"));
				return;
			}
			
			//响应POST
			
			String ipAddress = req.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
				ipAddress = req.getRemoteAddr();
			}

			//响应POST文件指令
			if (req.getHeader("Content-Type") != null
					&& req.getHeader("Content-Type").startsWith("multipart/form-data")) {
				//POST
				SimpAfu ret=actPost(req);
				if(logger.isDebugEnabled())
					logger.debug("ret=" + ret);
				if (logger.isDebugEnabled())logger.debug("Method:"+req.getMethod()+",name=" + req.getParameter("name")+",editorid=" + req.getParameter("editorid"));
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("Orgname", ret.getOrgname());
				res.getWriter().write(JSON.toJSONString(new Json(true, ret.getFilenameurl(),map)));
				return;
			} else {
				//OPTIONS				
				if (logger.isDebugEnabled())logger.debug("OPTIONS,Content-Type == null");
				//putBuff(entry, req.getInputStream(),false);
			}

			res.getWriter().write(JSON.toJSONString(new Json(false, "unkown")));

		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}


	/**
	 * 响应OPTIONS指令
	 * @param res
	 * @throws IOException
	 */
	private void actOptions(HttpServletResponse res) throws IOException{
		res.getWriter().write(JSON.toJSONString(new Json(true)));
	}
	/**
	 * 读POST的文件数据，写到AngularFileUpEnt，并且返回
	 * @param ipAddress
	 * @param sb
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	private  SimpAfu actPost (HttpServletRequest req) throws Exception{
		//这时的act prjpic
		String thistimeact="";
		User tokenUser=new User(-1);
		
		SimpAfu ret=new SimpAfu();

		ServletFileUpload upload = new ServletFileUpload();
		FileItemIterator iterator = upload.getItemIterator(req);

		while (iterator.hasNext()) {
			FileItemStream item = iterator.next();
			//Content-Disposition: form-data; name="file"; filename="16027850_980x1200_0.jpg"
			//Content-Type: image/png ...PNG...
			String fieldName=item.getFieldName();
			String name=item.getName();
			if (item.isFormField()) {
				String value=Streams.asString(item.openStream());
				if (logger.isDebugEnabled())
				logger.debug("Form fieldName=" + fieldName+",name=" + name+",value="+value);
				if(fieldName.equals("token")){
					tokenUser = AESUtil.getInstance().decryptUser(value);
					if(tokenUser==null){
						tokenUser=new User(-1);
					}
					ret.setUserId(tokenUser.getId());
				}
			}else{
				if (logger.isDebugEnabled())
					logger.debug("File fieldName=" + fieldName+",name=" + name);
				//文件流在item.openStream()中
				byte[] data=putBuff(item.openStream());

				ret.setOrgname(name);
				String ext=name.substring(name.lastIndexOf(".")+1);
				ret.setWritefilename(FileNameUtils.generateRandomFilenameYMdHms()+"."+ext);
				ret.setData(data);
				
				//写硬盘
				WebStaticUtils.writeDisk(ret);
				
			}


			//name="file"; filename="16027850_980x1200_0.jpg"
		}
		return ret;

	}

	/**
	 * 
	 * @param entry
	 * @param stream
	 * @param isAdd
	 * @return
	 */
	protected byte[] putBuff(InputStream stream) {
		int length=0;
		int size=0;
		byte[] buffer = new byte[200000];
		byte[] dest=new byte[0];

		try {
			while ((size = stream.read(buffer)) != -1) {
				length += size;
				//				for (int i = 0; i < 10; i++) {
				//					System.out.print(","+(int) buffer[i]);
				//				}
				//				System.out.println("=============("+size+")");
				dest=byteAppend(dest ,buffer, size);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return dest;
	}
	/**
	 * 读数据转成文本
	 * @param stream
	 * @return String 
	 */
	protected String read(InputStream stream) {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				//ignore
			}
		}
		return sb.toString();
	}
	/**
	 * 文本转整数，如果是""，错误之类就返回0
	 * @param str
	 * @return
	 */
	public static int valueOfStr(String str){
		if(StringUtils.isBlank(str))
			return 0;
		try {
			return Integer.valueOf(str);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 合并byte , src=src+append[len]
	 * @param src 源
	 * @param append 增加的
	 * @param len 增加的长度
	 * @return src+append
	 */
	public static byte[] byteAppend( byte[] src,byte[] append,int len) {
		if(len==-1)
			return src;
		byte[] newByte = new byte[len + src.length];
		System.arraycopy(src, 0, newByte, 0, src.length);
		System.arraycopy(append, 0, newByte, src.length, len);
		return newByte;
	}



}


