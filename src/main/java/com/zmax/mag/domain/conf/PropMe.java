package com.zmax.mag.domain.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * 这里主要是配置文件获取的配置，特殊配置，每个项目/模式都要变的
 * @author Zhf
 *
 */
@Component
@ConfigurationProperties(prefix="prop.me",locations = "classpath:application.yml")
public class PropMe {
	/**App id 会员版*/
	private String memberAppId;
    /**App key 会员版*/
	private String memberAppKey;
    /**App Secret 会员版*/
	private String memberAppSecret;
    /**App MasterSecret 会员版*/
	private String memberMasterSecret;
    /**App id 医生版*/
	private String doctorAppId;
    /**App key 医生版*/
	private String doctorAppKey;
    /**App Secret 医生版*/
	private String doctorAppSecret;
    /**App MasterSecret 医生版*/
	private String doctorMasterSecret;
  	/**是否是大御医*/
	private String isYjtcm;
	/**是否运行微信监听服务端，开发测试环境下不监听微信 true/false/test/dbdb */  
	private String isRunWxServer;
	/**是否运行定时任务，多应用中，只有一台运行定时任务*/  
	private boolean task;
	/**阿里大于短信签名*/
	private String aliSmsFreeSignName;
	public String getMemberAppId() {
		return memberAppId;
	}
	public void setMemberAppId(String memberAppId) {
		this.memberAppId = memberAppId;
	}
	public String getMemberAppKey() {
		return memberAppKey;
	}
	public void setMemberAppKey(String memberAppKey) {
		this.memberAppKey = memberAppKey;
	}
	public String getMemberAppSecret() {
		return memberAppSecret;
	}
	public void setMemberAppSecret(String memberAppSecret) {
		this.memberAppSecret = memberAppSecret;
	}
	public String getMemberMasterSecret() {
		return memberMasterSecret;
	}
	public void setMemberMasterSecret(String memberMasterSecret) {
		this.memberMasterSecret = memberMasterSecret;
	}
	public String getDoctorAppId() {
		return doctorAppId;
	}
	public void setDoctorAppId(String doctorAppId) {
		this.doctorAppId = doctorAppId;
	}
	public String getDoctorAppKey() {
		return doctorAppKey;
	}
	public void setDoctorAppKey(String doctorAppKey) {
		this.doctorAppKey = doctorAppKey;
	}
	public String getDoctorAppSecret() {
		return doctorAppSecret;
	}
	public void setDoctorAppSecret(String doctorAppSecret) {
		this.doctorAppSecret = doctorAppSecret;
	}
	public String getDoctorMasterSecret() {
		return doctorMasterSecret;
	}
	public void setDoctorMasterSecret(String doctorMasterSecret) {
		this.doctorMasterSecret = doctorMasterSecret;
	}
	public String getIsYjtcm() {
		return isYjtcm;
	}
	public void setIsYjtcm(String isYjtcm) {
		this.isYjtcm = isYjtcm;
	}
	public String getIsRunWxServer() {
		return isRunWxServer;
	}
	public void setIsRunWxServer(String isRunWxServer) {
		this.isRunWxServer = isRunWxServer;
	}
	public boolean isTask() {
		return task;
	}
	public void setTask(boolean task) {
		this.task = task;
	}
	public String getAliSmsFreeSignName() {
		return aliSmsFreeSignName;
	}
	public void setAliSmsFreeSignName(String aliSmsFreeSignName) {
		this.aliSmsFreeSignName = aliSmsFreeSignName;
	}
	@Override
	public String toString() {
		return "PropMe [memberAppId=" + memberAppId + ", memberAppKey="
				+ memberAppKey + ", memberAppSecret=" + memberAppSecret
				+ ", memberMasterSecret=" + memberMasterSecret
				+ ", doctorAppId=" + doctorAppId + ", doctorAppKey="
				+ doctorAppKey + ", doctorAppSecret=" + doctorAppSecret
				+ ", doctorMasterSecret=" + doctorMasterSecret + ", isYjtcm="
				+ isYjtcm + ", isRunWxServer=" + isRunWxServer + ", task="
				+ task + ", aliSmsFreeSignName=" + aliSmsFreeSignName + "]";
	}
}
