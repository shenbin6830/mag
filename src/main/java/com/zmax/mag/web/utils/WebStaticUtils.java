package com.zmax.mag.web.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.zmax.common.entity.angularjsFileUpload.SimpAfu;
import com.zmax.common.utils.file.FileNameUtils;
import com.zmax.common.utils.image.AverageImageScale;
import com.zmax.common.utils.image.ImageInfo;
import com.zmax.common.utils.string.UrlUtil;
import com.zmax.mag.domain.conf.Conf;
import com.zmax.mag.domain.conf.Rt;

public class WebStaticUtils {
	
	/**
	 * 服务器支持远程跨域
	 * @param res
	 */
	public static void makeCROS(HttpServletResponse res){
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		res.setHeader("Access-Control-Max-Age", "3600");
		res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

	}
	/**
	 * 写文件到硬盘
	 * url带系统名
	 * hd不带系统名
	 * @param entry SimpAfu
	 */
	public static void writeDisk(SimpAfu entry){
		String sdest=null;
		Integer userId=entry.getUserId();
		if(userId==null)
			userId=-1;
		// /xxx/userfiles/users/2/201409/20140931235959abcd.png
		sdest=MessageFormat.format(Conf.fileUuploadPath,userId,new SimpleDateFormat("yyyyMM").format(new Date()))
				+"/"+entry.getWritefilename();
		// ex.D:\z_java\sb\sbdemo\src\main\webapp\ + /userfiles/users/2/201409/20140931235959abcd.png
		//sdest=FileNameUtils.generateRandomUrlYMdHms(sdest);
		//url路径
		String url_dest=UrlUtil.urlAdd(Conf.JAVABASE, sdest);
		//URL版文件名
		entry.setFilenameurl(url_dest);
		//硬盘路径
		String hd_dest=UrlUtil.urlAdd(Rt.runtime_harddisk_rootpath_userupload, sdest);
		entry.setFilenamefulldisk(hd_dest);

		File dest=new File(hd_dest);
		if(dest.exists())dest.delete();

		//目标目录的父目录，不存在则创建
		if (!dest.getParentFile().exists()) 
			dest.getParentFile().mkdirs();
		
		//开始写硬盘
		try {
			FileOutputStream fos = new FileOutputStream(dest);
			fos.write(entry.getData());
			fos.close();
			
			//如果是图 加上缩略图
			if(new ImageInfo(entry.getData()).check()){
				String ext=hd_dest.substring(hd_dest.lastIndexOf(".")+1);
				AverageImageScale.resizeFixWithWidth(dest, new File(hd_dest+".thumbnail."+ext), 300);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}


	}
}
