package com.zmax.mag.domain.conf;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * 这里主要是配置文件获取的配置，非通常配置，不太会变的
 * @author Administrator
 *
 */
@Component
@ConfigurationProperties(prefix="prop.my",locations = "classpath:application.yml")
public class PropMy {
	/**作者*/
    String name;
    /**一些静态图的图床 ex.http://aaa.com */
    String imgbase; 
    /**静态资源站点  ex.http://aaa.com or "" 根目录用空格不要用/ */
    String resbase;    
    /**登录地址 /r"$"{base}/login.html*/
    String  loginurl; 
    /**user路径下不需要登录的地址 */
    List<String> loginignor;
    /**默认的管理员密码*/
    String adminpwd;
    /**硬盘上的运行目录 */
    String hddir;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgbase() {
		return imgbase;
	}
	public void setImgbase(String imgbase) {
		this.imgbase = imgbase;
	}
	public String getResbase() {
		return resbase;
	}
	public void setResbase(String resbase) {
		this.resbase = resbase;
	}
	public String getLoginurl() {
		return loginurl;
	}
	public void setLoginurl(String loginurl) {
		this.loginurl = loginurl;
	}
	public List<String> getLoginignor() {
		return loginignor;
	}
	public void setLoginignor(List<String> loginignor) {
		this.loginignor = loginignor;
	}
	public String getAdminpwd() {
		return adminpwd;
	}
	public void setAdminpwd(String adminpwd) {
		this.adminpwd = adminpwd;
	}
	public String getHddir() {
		return hddir;
	}
	public void setHddir(String hddir) {
		this.hddir = hddir;
	}
	@Override
	public String toString() {
		return "PropMy [name=" + name + ", imgbase=" + imgbase + ", resbase="
				+ resbase + ", loginurl=" + loginurl + ", loginignor="
				+ loginignor + ", adminpwd=" + adminpwd + ", hddir=" + hddir
				+ "]";
	}
}
