package com.zhongda.monitor.business.model;

import java.util.List;
/**
 * 封装了前台项目日均值的实体类
 * @author Administrator
 *
 */
public class WiselyResponse {
	private List<ItemAvgData> responseMessage;
	
	

    public WiselyResponse(List<ItemAvgData> responseMessage){
        this.responseMessage = responseMessage;
    }
   
    public List<ItemAvgData>  getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(List<ItemAvgData>   responseMessage) {
        this.responseMessage = responseMessage;
    }
}
