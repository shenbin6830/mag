package com.zmax.mag.domain.utils;

import java.security.InvalidKeyException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zmax.mag.domain.bean.User;
import com.zmax.mag.domain.conf.Conf;


public final class AESUtil {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	
	public static String testUserId = "999999999999";
	public static String seed = "+=-_O^O_-=+";
	public static String splitChar ="|";
	/**用B64还是Hex,B64=true,HEX=false*/
	public static boolean isB64OrHex=true;
	static AESUtil inst=null;
	public static AESUtil getInstance(){
		try {
			if(inst==null){
				inst = new AESUtil();
			}
			return inst;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}  

	KeyGenerator kgen ;
	SecureRandom secureRandom;
	SecretKey secretKey;
	SecretKeySpec key;
	Cipher cipher;
	private AESUtil() throws Exception {
		kgen = KeyGenerator.getInstance("AES");
		secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(seed.getBytes("utf-8"));
		kgen.init(128, secureRandom);
		secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		key = new SecretKeySpec(enCodeFormat, "AES");
		cipher = Cipher.getInstance("AES");
	}
	/**
	 * 加密
	 * @param src
	 * @return BASE64格式的密文
	 */
	private  String encrypt(String content) {
		try {

			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(byteContent);
			return parseByte2HexStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	/**
	 * 解密
	 * @param b64 Base64格式的密文
	 * @return 明文
	 * @throws InvalidKeyException 
	 */
	private  String decrypt(String b64) throws Exception {
		try {
			byte[] content=parseHexStr2Byte(b64);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] result = cipher.doFinal(content);
			return new String(result, "utf-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("解密TOKEN失败：["+b64+"],msg="+e.getMessage());
		}
		return null;
	}
	/**
	 * 加密一个用户
	 * 如果是测试用户：101234xxx 返回：10123456789_userId_roleId_其它测试内容
	 * @param user
	 * @return String b64 
	 */
	public  String encryptUser(User user) {
		if(user!=null && user.getUsername()!=null && user.getUsername().indexOf(Conf.TESTUSER)==0){
			//10123456789_userId_roleId_其它测试内容
			return ""+user.getUsername()+"_"+user.getId()+"_"+user.getRoleId()+"_12+34";
		}
		return encrypt(""+user.getId()+splitChar+user.getRoleId()+splitChar+(new Date()).getTime());
	}
	/**
	 * 解密，产生出只带了关键字的非数据库用户
	 * @param b64
	 * @return User
	 */
	public  User decryptUser(String b64) throws Exception{
		User user=null;
		if(StringUtils.isBlank(b64)){
			logger.error("解密TOKEN失败：b64为空");
			return null;
		}
		if(b64.indexOf(Conf.TESTUSER)==0){
			//10123456789_userId_roleId_其它测试内容
			String[] at=b64.split("_");
			user=new User();
			user.setUsername(at[0]);
			user.setId(Integer.parseInt(at[1]));
			user.setRoleId(Integer.parseInt(at[2]));
			String other=at[3];
			if (logger.isDebugEnabled()){
				logger.debug("Conf.TESTUSER.b64=" + b64);
				logger.debug("other=" + other);
			}
			return user;
		}
		String str = decrypt(b64);
		if(StringUtils.isBlank(str)){
			return null;
		}
			
		String[] astr=str.split("\\|");
		
		if(astr.length>2){
			user=new User();
			user.setRoleId(-1);
			user.setId(Integer.parseInt(astr[0]));
			user.setRoleId(Integer.parseInt(astr[1]));
			Calendar now=Calendar.getInstance();
			now.setTimeInMillis(Long.parseLong(astr[2]));
			user.setGmtModified(now.getTime());
		}
		return user;
	}
	public static String parseByte2HexStr(byte[] buf) {
		if(isB64OrHex)
			return Base64.encodeBase64String(buf);
		else
			return Hex.encodeHexString(buf);
	}

	public static byte[] parseHexStr2Byte(String hexStr) {
		if(isB64OrHex){
			return Base64.decodeBase64(hexStr);
		}else{
			try {
				return Hex.decodeHex(hexStr.toCharArray());
			} catch (DecoderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}
	/*
	public static void main(String[] args) throws Exception {
		String content = "user:刘备" + "|time:" + System.currentTimeMillis();
		// 加密
		System.out.println("加密前：" + content);
		String b64 = AESUtil.getInstance().encrypt(content);
		System.out.println("加密后：" + b64);
		// 解密
		String decryptResult = AESUtil.getInstance().decrypt(b64);
		System.out.println("解密后：" + decryptResult);

		User user=new User(1, null, 0, 3, null, null, null, null, null, null);
		b64=AESUtil.getInstance().encryptUser(user);
		System.out.println("加密后：" +b64);
		User user1=AESUtil.getInstance().decryptUser(b64);
		System.out.println("解密后：" +user1.toJsonNotshow1());
	}*/
}
