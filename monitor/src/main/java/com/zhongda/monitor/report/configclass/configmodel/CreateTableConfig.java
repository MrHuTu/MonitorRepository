package com.zhongda.monitor.report.configclass.configmodel;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlCursor;

import com.zhongda.monitor.report.model.fictitious.SideTableDataModel;





/**
 * 动态创建表的参数类，控制表格的行，列及数据展示
 * @author huchao
 *
 */
public class CreateTableConfig {
	
	private XWPFDocument doc2;
	
	private XmlCursor cursor;
	
	private int line;
	
	private int row;
	
	private SideTableDataModel  sideTableDataModel;
	
	

	public CreateTableConfig() {
	
	}

	public CreateTableConfig(XWPFDocument doc2, XmlCursor cursor, int line,int row, SideTableDataModel sideTableDataModel) {
		super();
		this.doc2 = doc2;
		this.cursor = cursor;
		this.line = line;
		this.row = row;
		this.sideTableDataModel = sideTableDataModel;
	}

	public XWPFDocument getDoc2() {
		return doc2;
	}

	public void setDoc2(XWPFDocument doc2) {
		this.doc2 = doc2;
	}

	public XmlCursor getCursor() {
		return cursor;
	}

	public void setCursor(XmlCursor cursor) {
		this.cursor = cursor;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public SideTableDataModel getSideTableDataModel() {
		return sideTableDataModel;
	}

	public void setSideTableDataModel(SideTableDataModel sideTableDataModel) {
		this.sideTableDataModel = sideTableDataModel;
	}
	
	@Override
	public String toString() {
		return "CreateTableConfig [doc2=" + doc2 + ", cursor=" + cursor
				+ ", line=" + line + ", row=" + row + ", sideTableDataModel="
				+ sideTableDataModel + "]";
	}

	/**
	 * 边坡类型的配置参数     沉降  和收敛的配置
	 * @param doc2
	 * @param cursor
	 * @param sideTableDataModel
	 * @return
	 */
	public static CreateTableConfig  getSideTable(XWPFDocument doc2,XmlCursor cursor,SideTableDataModel sideTableDataModel){
		
		return new CreateTableConfig( doc2,  cursor,  9, 6, sideTableDataModel);
		
	}
}
