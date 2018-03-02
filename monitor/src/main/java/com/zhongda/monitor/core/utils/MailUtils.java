package com.zhongda.monitor.core.utils;


/**
 * 
 * Title : MailUtils管理
 * Description : MailUtils工具类
 * @Author dengzm
 * @Date 2018年3月1日 下午5:47:58
 */
public class MailUtils {
	
	/**
	 * 数据类告警
	 * 告警模板标识: DataAlarmTemplate
	 * 告警模板主题 : 告警消息
	 * 告警模板内容 : <div>
    				<span th:text=" 尊敬的${userName}用户："></span><br>       
        				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span th:text="您好！您的项目于${creatDate}监测到数据类告警：${monitorType}超过阈值。"></span><br>
        				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span th:text="项目名称：${projectName}"></span><br>
        				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span th:text="采集终端：${smuNumber} 传感器编号：${sensorNumber}"></span><br>
        				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span th:text="当前监测值：${currentData} 正常值范围:${minNormal}~${maxNormal}"></span><br>
        				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;详情请点击<a href="http://123.207.39.209/Detection">中大检测在线检测服务平台</a>查看，谢谢！<br>
       				<span>公司：湖南中大检测建设工程技术有限公司</span><br>
        			<span>地址：长沙市岳麓区学士路755号</span><br>
        			<span>联系电话：18673101894    座机：0731-88137366转8000  4008930909（转分机号）</span><br>
   					<a href="http://www.hnzdjc.com">中大检测官网网址</a>
    			</div>
	 * {userName}账户名  {creatDate}时间  {monitorType}监测的类型  {projectName}项目名   {smuNumber}采集终端    {sensorNumber}传感器编号  {currentData}监测到的超过阈值的当前值   {minNormal}最小阈值  {maxNormal}最大阈值
	 */
	public static final String DATA_ALARM_TEMPLATE="DataAlarmTemplate";
	/**
	 * 设备类告警
	 * 告警模板标识: DeviceAlarmTemplate
	 * 告警模板主题 : 告警消息
	 * 告警模板内容 : <div>       
       				<span th:text="尊敬的${userName}用户："></span><br>
       		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span th:text="您好！您的项目于${creatDate}监测到${monitorType}的设备类告警。"></span><br>
       		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span th:text="项目名称：${projectName}"></span><br>
       		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span th:text="采集终端：${smuNumber} 传感器编号：${sensorNumber}"></span><br>
       		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span th:text="${alarmContext}"></span><br>
       		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;详情请点击<a href="http://123.207.39.209/Detection">中大检测在线检测服务平台</a>查看，谢谢！<br>
       				<span>公司：湖南中大检测建设工程技术有限公司</span><br>
        			<span>地址：长沙市岳麓区学士路755号</span><br>
        			<span>联系电话：18673101894    座机：0731-88137366转8000  4008930909（转分机号）</span><br>
       	    		<a href="http://www.hnzdjc.com">中大检测官网网址</a>
    			</div>
	 * {userName}账户名  {creatDate}时间  {monitorType}监测的类型  {projectName}项目名   {smuNumber}采集终端    {sensorNumber}传感器编号  {alarmContext}故障类型
	 */
	public static final String DEVICE_ALARM_TEMPLATE="DeviceAlarmTemplate";
	
	public static final String ALARM_SUBJECT="告警消息";
}