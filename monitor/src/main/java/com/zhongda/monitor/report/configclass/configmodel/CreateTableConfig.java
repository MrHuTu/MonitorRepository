package com.zhongda.monitor.report.configclass.configmodel;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlCursor;

import com.zhongda.monitor.report.model.fictitious.SideTableData;





/**
 * 动态创建表的参数类，控制表格的行，列及数据展示（做这个东西主要时我不想给一个方法传递一堆参数）
 * @author huchao
 *
 */
public class CreateTableConfig {
	
	private XWPFDocument doc2;
	
	private XmlCursor cursor;
	
	private int line;//行
	
	private int row;//列
	
	private Object  obj;
	
	

	public CreateTableConfig() {
	
	}

	public CreateTableConfig(XWPFDocument doc2, XmlCursor cursor, int line,int row,Object  obj) {
		super();
		this.doc2 = doc2;
		this.cursor = cursor;
		this.line = line;
		this.row = row;
		this.obj = obj;
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

	
	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	@Override
	public String toString() {
		return "CreateTableConfig [doc2=" + doc2 + ", cursor=" + cursor
				+ ", line=" + line + ", row=" + row + ", Object="
				+ obj + "]";
	}

	/**
	 * 边坡类型的配置参数     沉降  和收敛的配置
	 * @param doc2
	 * @param cursor
	 * @param Object 填充到表格的数据
	 * @return
	 */
	public static CreateTableConfig  getSideTable(XWPFDocument doc2,XmlCursor cursor,Object obj){
		
		return new CreateTableConfig( doc2,  cursor,  9, 6, (SideTableData)obj);
		
	}
}
