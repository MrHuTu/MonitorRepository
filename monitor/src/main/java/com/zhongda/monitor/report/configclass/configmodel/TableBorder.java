package com.zhongda.monitor.report.configclass.configmodel;
/**
 * 表格边框样式实体类
 * 边框默认类型为实体边框1磅
 * @author HUCHAO
 *
 */
public class TableBorder {
	
	public static final String NONE= "none";
	
	public static final String SINGLE= "single";
	
	private String LeftWire =SINGLE;
	
	private String  LeftSz ="15";
	
	private String LefColor ="0F0F0F";
	
	private String RightWire =SINGLE;
	
	private String  RighttSz ="15";
	
	private String RightColor ="0F0F0F";
	
	private String TopWire =SINGLE;
	
	private String  ToptSz ="15";
	
	private String TopColor ="0F0F0F";
	
	private String BottomWire =SINGLE;
	
	private String  BottomSz ="15";
	
	private String BottomColor ="0F0F0F";
	
	

	

	public String getLeftWire() {
		return LeftWire;
	}

	public void setLeftWire(String leftWire) {
		LeftWire = leftWire;
	}

	public String getLeftSz() {
		return LeftSz;
	}

	public void setLeftSz(String leftSz) {
		LeftSz = leftSz;
	}

	public String getLefColor() {
		return LefColor;
	}

	public void setLefColor(String lefColor) {
		LefColor = lefColor;
	}

	public String getRightWire() {
		return RightWire;
	}

	public void setRightWire(String rightWire) {
		RightWire = rightWire;
	}

	public String getRighttSz() {
		return RighttSz;
	}

	public void setRighttSz(String righttSz) {
		RighttSz = righttSz;
	}

	public String getRightColor() {
		return RightColor;
	}

	public void setRightColor(String rightColor) {
		RightColor = rightColor;
	}

	public String getTopWire() {
		return TopWire;
	}

	public void setTopWire(String topWire) {
		TopWire = topWire;
	}

	public String getToptSz() {
		return ToptSz;
	}

	public void setToptSz(String toptSz) {
		ToptSz = toptSz;
	}

	public String getTopColor() {
		return TopColor;
	}

	public void setTopColor(String topColor) {
		TopColor = topColor;
	}

	public String getBottomWire() {
		return BottomWire;
	}

	public void setBottomWire(String bottomWire) {
		BottomWire = bottomWire;
	}

	public String getBottomSz() {
		return BottomSz;
	}

	public void setBottomSz(String bottomSz) {
		BottomSz = bottomSz;
	}

	public String getBottomColor() {
		return BottomColor;
	}

	public void setBottomColor(String bottomColor) {
		BottomColor = bottomColor;
	}
	/**
	 * 无边框模式
	 */
	
	public static TableBorder noneBorder(){
		TableBorder   tableBorder= new TableBorder();
		tableBorder.setLeftWire(NONE);
		tableBorder.setRightWire(NONE);
		tableBorder.setTopWire(NONE);
		tableBorder.setBottomWire(NONE);
		return tableBorder;
		
	}
}
