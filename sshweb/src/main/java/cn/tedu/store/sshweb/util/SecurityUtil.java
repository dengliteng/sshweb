package cn.tedu.store.sshweb.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 一些加密算法
 * @author deng
 *
 */
public class SecurityUtil {

	
	public static void main(String[] args) {
		String str = "admin";
		System.out.println(md5(str, "111qqq"));
	}
	
	//MD5加密
	public static String md5(String str){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] rs = md.digest();     //hash加密运算
			return new BigInteger(1, rs).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//MD5加密
	public static String md5(String str1,String str2){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str1.getBytes());
			md.update(str2.getBytes());
			byte[] rs = md.digest();    //hash加密运算
			return new BigInteger(1, rs).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//SHA-1加密
	public static String sha(String str){
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			sha.update(str.getBytes());
			byte[] by = sha.digest();
			StringBuffer sb = new StringBuffer();
			for(byte b : by){
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
