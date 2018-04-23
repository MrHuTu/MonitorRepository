package com.zhongda.monitor.report.configclass.tableclass;

import java.math.BigInteger;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;
import org.joda.time.DateTime;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import com.zhongda.monitor.report.configclass.configmodel.CreateTableConfig;
import com.zhongda.monitor.report.configclass.configmodel.TableBorder;
import com.zhongda.monitor.report.model.ReportData;
import com.zhongda.monitor.report.model.fictitious.SideTableData;
import com.zhongda.monitor.report.service.BastTableClass;
import com.zhongda.monitor.report.utils.Wordl2007Utis;




/**
 * 沉降样式   必须实现BastTableClass接口
 * @author huchao
 *
 */
public class SedimentationTableClass implements BastTableClass {
	@SuppressWarnings("unused")
	public  void createTable(CreateTableConfig config) {
		
		XWPFTable tableOne = config.getDoc2().insertNewTbl(config.getCursor());// ---这个是关键，创建一个表格
			
		 for(int i=1;i<=config.getLine();i++){//控制行
			 
			 if(i==1){
				 
				 XWPFTableRow tableOneRowOne = tableOne.getRow(0);//取表的表头		
				
	
				 for(int j=1;j<config.getRow();j++){//控制列
					 
					 XWPFTableCell cell = tableOneRowOne.addNewTableCell();
					
				 }
			 }else{
				 
				 XWPFTableRow tableOneRowTwo = tableOne.createRow();//差入一行，以第一行为标准生成单元格
				 
				for(int k=0;k<config.getRow();k++){
					
					XWPFTableCell cell = tableOneRowTwo.getCell(k);
					
				}
				 
			 }
		 }
		
		//XWPFTable tableOne = doc2.createTable(5, 6);

		
		 customizationTableClass(tableOne,config.getObj());

	}
	
    /***
     * 定制表格样式
     **/
	public static void customizationTableClass(XWPFTable tableOne,Object obj){
				SideTableData	model = (SideTableData)obj;
				//控制表格样式
				String amount = "9000";
				
				CTTbl ttbl = tableOne.getCTTbl();
					
				CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl.getTblPr();
					
				CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();												
				 
				tblWidth.setW(new BigInteger(amount));
					
				tblWidth.setType(STTblWidth.DXA);						
				
				Wordl2007Utis.mergeCellsHorizontal(tableOne, 0, 0, 5);
				Wordl2007Utis.mergeCellsHorizontal(tableOne, 1, 0, 5);

				Wordl2007Utis.mergeCellsHorizontal(tableOne, 2, 0, 2);
				Wordl2007Utis.mergeCellsHorizontal(tableOne, 2, 3, 5);

				Wordl2007Utis.mergeCellsHorizontal(tableOne, 3, 0, 2);
				Wordl2007Utis.mergeCellsHorizontal(tableOne, 3, 3, 5);
				
				Wordl2007Utis.mergeCellsHorizontal(tableOne, 4, 0, 2);
				Wordl2007Utis.mergeCellsHorizontal(tableOne, 4, 3, 5);
				
				XWPFTableRow row = tableOne.getRow(0);
				row.getCell(0).setText(model.getLineOne());		
				
				XWPFTableRow row1 = tableOne.getRow(1);
				row1.getCell(0).setText(model.getLineTwo());
				
				XWPFTableRow row2 = tableOne.getRow(2);
				row2.getCell(0).setText(model.getLineThreeCellone());
				row2.getCell(3).setText(model.getLineThreeCelltwo());
				
				XWPFTableRow row3 = tableOne.getRow(3);
				row3.getCell(0).setText(model.getLineFourCellone());
				row3.getCell(3).setText(model.getLineFourCellTwo());
								
				XWPFTableRow row4 = tableOne.getRow(4);
				row4.getCell(0).setText(model.getLineFiveCellOne());
				row4.getCell(3).setText(model.getLineFiveCellTwo());
				
				XWPFTableRow row5 = tableOne.getRow(5);
				row5.getCell(0).setText("序号");
				row5.getCell(1).setText("监测日期");
				row5.getCell(2).setText("初始值");
				row5.getCell(3).setText("测定值");
				row5.getCell(4).setText("当天沉降");
				row5.getCell(5).setText("累计沉降");
				
				List<ReportData> reportDataList = model.getReportDatas();
				
				XWPFTableRow row6 = tableOne.getRow(6);
				row6.getCell(0).setText("1");				
				if(reportDataList.size()!=3) return ;
				row6.getCell(1).setText(new DateTime(reportDataList.get(0).getCurrent_times()).toString("YYYY-MM-dd"));
				row6.getCell(2).setText(String.valueOf(reportDataList.get(0).getFirst_data()));
				row6.getCell(3).setText(String.valueOf(reportDataList.get(0).getCurrent_data()));
				row6.getCell(4).setText(String.valueOf(reportDataList.get(0).getCurrent_laser_change()));
				row6.getCell(5).setText(String.valueOf(reportDataList.get(0).getTotal_laser_change()));
				
				
				XWPFTableRow row7 = tableOne.getRow(7);
				row7.getCell(0).setText("2");				
			
				row7.getCell(1).setText(new DateTime(reportDataList.get(1).getCurrent_times()).toString("YYYY-MM-dd"));
				row7.getCell(2).setText(String.valueOf(reportDataList.get(1).getFirst_data()));
				row7.getCell(3).setText(String.valueOf(reportDataList.get(1).getCurrent_data()));
				row7.getCell(4).setText(String.valueOf(reportDataList.get(1).getCurrent_laser_change()));
				row7.getCell(5).setText(String.valueOf(reportDataList.get(1).getTotal_laser_change()));
				
				
				XWPFTableRow row8 = tableOne.getRow(8);
				row8.getCell(0).setText("3");				
				row8.getCell(1).setText(new DateTime(reportDataList.get(2).getCurrent_times()).toString("YYYY-MM-dd"));
				row8.getCell(2).setText(String.valueOf(reportDataList.get(2).getFirst_data()));
				row8.getCell(3).setText(String.valueOf(reportDataList.get(2).getCurrent_data()));
				row8.getCell(4).setText(String.valueOf(reportDataList.get(2).getCurrent_laser_change()));
				row8.getCell(5).setText(String.valueOf(reportDataList.get(2).getTotal_laser_change()));
	}
	
	/**
	 * 创建表格的标题 ,实用边坡表格模板
	 * @param doc2
	 * @param cursor
	 */
	public static void createTableSpance(XWPFDocument doc2, XmlCursor cursor,String title) {
		
		XWPFTable tableOne = doc2.insertNewTbl(cursor);// ---这个是关键，创建一个表格	
		
		CTTbl ttbl = tableOne.getCTTbl();
		
		CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl.getTblPr();
			
		CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();
			
		tblWidth.setW(new BigInteger("9000"));
			
		tblWidth.setType(STTblWidth.DXA);
		
	    XWPFTableRow tableOneRowOne = tableOne.getRow(0);//取表的表头		
	  
	    XWPFTableCell 	 cell =  tableOneRowOne.getCell(0);
		 
	    Wordl2007Utis.setCellText(cell,title,"000000",9000,"微软雅黑","000000",12,true);
		
		for(int i=0;i<5;i++){
			
		 tableOneRowOne.addNewTableCell();
			 
		}
		
		Wordl2007Utis.mergeCellsHorizontal(tableOne, 0, 0, 5);
		
		Wordl2007Utis.setBorder(tableOne,TableBorder.noneBorder());
	      
	}

	@Override
	public CreateTableConfig getTableClassConfig(XWPFDocument doc2, XmlCursor cursor,Object obj) {
		
		return new CreateTableConfig( doc2,  cursor,  9, 6, (SideTableData)obj);
	}
	 
}
