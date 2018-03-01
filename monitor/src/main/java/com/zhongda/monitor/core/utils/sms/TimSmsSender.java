package com.zhongda.monitor.core.utils.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhongda.monitor.core.utils.sms.SmsSenderResult.SmsMultiSenderResult;
import com.zhongda.monitor.core.utils.sms.SmsSenderResult.SmsSingleSenderResult;
 
/**
 * 腾讯云短信SDK
 * @author Administrator
 */
public class TimSmsSender {
	
	int appid = 1400045250;
	String appkey = "dc8effab07d2a4791e744e3e165a002f";
    String singleUrl = "https://yun.tim.qq.com/v5/tlssmssvr/sendsms";
    String multiUrl = "https://yun.tim.qq.com/v5/tlssmssvr/sendmultisms2";
    
	SmsSenderUtil util = new SmsSenderUtil();

	/**
	 * 指定模板向单个用户发送短信
	 * @param nationCode 国家码，如 86 为中国
	 * @param phoneNumber 不带国家码的手机号
	 * @param templId 模板Id
	 * @param params 模板参数列表，如模板 {1}...{2}...{3}，那么需要带三个参数
	 * @param sign 签名，如果填空，系统会使用默认签名
	 * @param extend 扩展码，可填空
	 * @param ext 服务端原样返回的参数，可填空
	 * @return {@link}SmsSingleSenderResult
	 * @throws Exception
	 */
	public SmsSingleSenderResult sendWithParam(
			String nationCode,
			String phoneNumber,
			int templId,
			List<String> params,
			String sign,
			String extend,
			String ext) throws Exception {

		if (null == nationCode || 0 == nationCode.length()) {
			nationCode = "86";
		}
		if (null == params) {
			params = new ArrayList<>();
		}
		if (null == sign) {
			sign = "";
		}
		if (null == extend) {
			extend = "";
		}		
		if (null == ext) {
			ext = "";
		}
		
		long random = util.getRandom();
		long curTime = System.currentTimeMillis()/1000;
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> requestBody = new HashMap<String, Object>();
	    Map<String, String> telBody = new HashMap<String, String>();
	    
	    telBody.put("nationcode", nationCode);
	    telBody.put("mobile", phoneNumber);

	    requestBody.put("tel", telBody);
	    requestBody.put("sig", util.calculateSigForTempl(appkey, random, curTime, phoneNumber));
	    requestBody.put("tpl_id", templId);
	    requestBody.put("params", params);
	    requestBody.put("sign", sign);
	    requestBody.put("time", curTime);
	    requestBody.put("extend", extend);
	    requestBody.put("ext", ext);

		String wholeUrl = String.format("%s?sdkappid=%d&random=%d", singleUrl, appid, random);
        HttpURLConnection conn = util.getPostHttpConn(wholeUrl);

        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
        wr.write(mapper.writeValueAsString(requestBody));
        wr.flush();

        // 显示 POST 请求返回的内容
        StringBuilder sb = new StringBuilder();
        int httpRspCode = conn.getResponseCode();
        SmsSingleSenderResult result;
        if (httpRspCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            result = mapper.readValue(sb.toString(), SmsSingleSenderResult.class);
        } else {
        	result = new SmsSingleSenderResult();
        	result.result = httpRspCode;
        	result.errmsg = "http error " + httpRspCode + " " + conn.getResponseMessage();
        }
        return result;
	}
	
	/**
	 * 指定模板群发多个用户
	 * @param nationCode 国家码，如 86 为中国
	 * @param phoneNumbers 不带国家码的手机号列表
	 * @param templId 模板 id
	 * @param params 模板参数列表
	 * @param sign 签名，如果填空，系统会使用默认签名
	 * @param extend 扩展码，可以填空
	 * @param ext 服务端原样返回的参数，可以填空
	 * @return {@link}SmsMultiSenderResult
	 * @throws Exception
	 */
	public SmsMultiSenderResult sendWithParam(
			String nationCode,
			List<String> phoneNumbers,
			int templId,
			List<String> params,
			String sign,
			String extend,
			String ext) throws Exception {

		if (null == nationCode || 0 == nationCode.length()) {
			nationCode = "86";
		}
		if (0 == phoneNumbers.size()) {
			throw new Exception("phoneNumbers size error");
		}
		if (null == params) {
			params = new ArrayList<>();
		}
		if (null == sign) {
			sign = "";
		}
		if (null == extend) {
			extend = "";
		}		
		if (null == ext) {
			ext = "";
		}
		
		long random = util.getRandom();
		long curTime = System.currentTimeMillis()/1000;		

		// 按照协议组织 post 请求包体
		ObjectMapper mapper = new ObjectMapper();	
        Map<String, Object> requestBody = new HashMap<String, Object>();
        
        requestBody.put("tel", util.phoneNumbersToList(nationCode, phoneNumbers));                
        requestBody.put("sign", sign);
        requestBody.put("tpl_id", templId);
        requestBody.put("params", params);
        requestBody.put("sig", util.calculateSigForTempl(appkey, random, curTime, phoneNumbers));
        requestBody.put("time", curTime);
        requestBody.put("extend", extend);
        requestBody.put("ext", ext);        

		String wholeUrl = String.format("%s?sdkappid=%d&random=%d", multiUrl, appid, random);
        HttpURLConnection conn = util.getPostHttpConn(wholeUrl);

        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
        wr.write(mapper.writeValueAsString(requestBody));
        wr.flush();
		
        // 显示 POST 请求返回的内容
        StringBuilder sb = new StringBuilder();
        int httpRspCode = conn.getResponseCode();
        SmsMultiSenderResult result;
        if (httpRspCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            result = mapper.readValue(sb.toString(), SmsMultiSenderResult.class);
        } else {
        	result = new SmsMultiSenderResult();
        	result.result = httpRspCode;
        	result.errmsg = "http error " + httpRspCode + " " + conn.getResponseMessage();
        }
        
        return result;
	}
	
}
