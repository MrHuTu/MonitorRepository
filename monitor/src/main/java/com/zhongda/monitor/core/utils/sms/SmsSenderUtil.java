package com.zhongda.monitor.core.utils.sms;

import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 短信发送工具类
 * @author Administrator
 */
class SmsSenderUtil {
	
	//随机数
    protected Random random = new Random();
       
    /**
     * 将字符串进行SHA-256加密
     * @param str stringMD5
     * @return
     * @throws NoSuchAlgorithmException
     */
    protected String strToHash(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] inputByteArray = str.getBytes();
        messageDigest.update(inputByteArray);
        byte[] resultByteArray = messageDigest.digest();
        return byteArrayToHex(resultByteArray);
    }

    /**
     * 将字节数组转换成16进制字符串
     * @param byteArray 要转换的字节数组
     * @return
     */
    public String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        return new String(resultCharArray);
    }
    
    /**
     * 生成100000-999999之间的随机数
     * @return
     */
    public int getRandom() {
    	return random.nextInt(999999)%900000+100000;
    }
    
    /**
     * 通过url获取post方法的http连接
     * @param url
     * @return
     * @throws Exception
     */
    public HttpURLConnection getPostHttpConn(String url) throws Exception {
        URL object = new URL(url);
        HttpURLConnection conn;
        conn = (HttpURLConnection) object.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestMethod("POST");
        return conn;
	}
    
    /**
     * 将多个接收人的手机号码以逗号连接，和其他参数一起计算app凭证
     * @param appkey 
     * @param random
     * @param curTime
     * @param phoneNumbers 多个用户手机号
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String calculateSigForTempl(
    		String appkey,
    		long random,
    		long curTime,    		
    		List<String> phoneNumbers) throws NoSuchAlgorithmException {
        String phoneNumbersString = phoneNumbers.get(0);
        int length = phoneNumbers.size();
        for (int i = 1; i < length; i++) {
        	phoneNumbersString += "," + phoneNumbers.get(i);
		}
        return strToHash(String.format(
        		"appkey=%s&random=%d&time=%d&mobile=%s",
        		appkey, random, curTime, phoneNumbersString));
    }
    
    /**
     * 将接收人的手机号码和其他参数一起计算app凭证
     * @param appkey
     * @param random
     * @param curTime
     * @param phoneNumber
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String calculateSigForTempl(
    		String appkey,
    		long random,
    		long curTime,    		
    		String phoneNumber) throws NoSuchAlgorithmException {
    	List<String> phoneNumbers = new ArrayList<>();
    	phoneNumbers.add(phoneNumber);
    	return calculateSigForTempl(appkey, random, curTime, phoneNumbers);
    }
    
    /**
     * 将所有用户的号码和国家码重新组合，生成带有国家码的号码集合
     * @param nationCode 国家码
     * @param phoneNumbers 手机号码集合
     * @return
     */
   public List<Map<String, String>> phoneNumbersToList(String nationCode, List<String> phoneNumbers) {
	   List<Map<String, String>> tel = new ArrayList<Map<String, String>>();
        for (String phoneNumber : phoneNumbers) {
        	Map<String, String> telElement = new HashMap<String, String>();
            telElement.put("nationcode", nationCode);
            telElement.put("mobile", phoneNumber);
            tel.add(telElement);
		}
        return tel;
    }
  
}
