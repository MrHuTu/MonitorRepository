package com.zhongda.monitor.core.utils.sms;

import java.util.ArrayList;

/**
 * 短信返回接受实体类
 * @author Administrator
 */
public class SmsSenderResult {
	
	static class SmsSingleSenderResult {
		
		public int result;
		public String errmsg = "";
		public String ext = "";
		public String sid = "";
		public int fee;
		
		public String toString() {
			return String.format(
					"SmsSingleSenderResult\nresult %d\nerrMsg %s\next %s\nsid %s\nfee %d",
					result, errmsg, ext, sid, fee);		
		}
	}
	
	static class SmsMultiSenderResult {
	
		public int result;
		public String errmsg = "";
		public String ext = "";
		public ArrayList<Detail> detail;
		
		public String toString() {
			return String.format(
					"SmsMultiSenderResult\nresult %d\nerrMsg %s\next %s\ndetails %s",
					result, errmsg, ext, detail);
		}
	}
	
	static class Detail {
		public int result;
		public String errmsg = "";
		public String mobile = "";
		public String nationcode = "";
		public String sid = "";
		public int fee;
		
		public String toString() {
			if (0 == result) {
				return String.format(
						"Detail result %d\nerrMsg %s\nphoneNumber %s\nnationCode %s\nsid %s\nfee %d",
						result, errmsg, mobile, nationcode, sid, fee);
			} else {
				return String.format(
						"result %d\nerrMsg %s\nphoneNumber %s\nnationCode %s",
						result, errmsg, mobile, nationcode);				
			}
		}
	}
}
