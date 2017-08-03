package com.zmax.mag.web.controller.restful.entity;
/**
 * REST的客户端信息
 * @author zmax
 *
 */
public class ClientInfo {
	/**客户端版本号*/
	Integer ver;
	/**客户端类型0/NULL为H5,1安卓,2苹果,3微信*/
	Integer cli;
	/**微信openid*/
	String openid;
	/**微信openidMd5*/
	String openidmd5;
	/**客户token*/
	String token;
	/**客户ip,这个不进客户端*/
	String ip;	
	
	/**客户端版本号*/
	public Integer getVer() {
		return ver;
	}
	/**客户端版本号*/
	public void setVer(Integer ver) {
		this.ver = ver;
	}
	/**客户端类型0/NULL为H5,1安卓,2苹果,3微信*/
	public Integer getCli() {
		return cli;
	}
	/**客户端类型0/NULL为H5,1安卓,2苹果,3微信*/
	public void setCli(Integer cli) {
		this.cli = cli;
	}
	/**微信openid*/
	public String getOpenid() {
		return openid;
	}
	/**微信openid*/
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**微信openidMd5*/
	public String getOpenidmd5() {
		return openidmd5;
	}
	/**微信openidMd5*/
	public void setOpenidmd5(String openidmd5) {
		this.openidmd5 = openidmd5;
	}
	/**客户token*/
	public String getToken() {
		return token;
	}
	/**客户token*/
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 修整
	 */
	public void fix(){
		
	}
	@Override
	public String toString() {
		return "ClientInfo [ver=" + ver + ", cli=" + cli + ", openid=" + openid
				+ ", openidmd5=" + openidmd5 + ", token=" + token + ", ip="
				+ ip + "]";
	}

	
}
