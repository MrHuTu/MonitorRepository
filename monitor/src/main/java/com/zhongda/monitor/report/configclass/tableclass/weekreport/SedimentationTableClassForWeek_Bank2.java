package com.zhongda.monitor.report.configclass.tableclass.weekreport;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import com.zhongda.monitor.report.configclass.configmodel.CreateTableConfig;
import com.zhongda.monitor.report.configclass.configmodel.TableBorder;
import com.zhongda.monitor.report.model.fictitious.ReportWeekData_Dates;
import com.zhongda.monitor.report.model.fictitious.ReportWeekData_Head;
import com.zhongda.monitor.report.service.BastTableClass;
import com.zhongda.monitor.report.utils.ReportDateUtils;
import com.zhongda.monitor.report.utils.Wordl2007Utis;




/**
 * 周报统一样式
 * @author huchao
 *
 */
public class SedimentationTableClassForWeek_Bank2 implements BastTableClass {
	
	//private static  Logger logger = LoggerFactory.getLogger(SedimentationTableClassForWeek_Bank2.class);
	
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
			
		customizationTableClass(tableOne,config.getObj());

	}
	
    /***
     * 定制表格样式,自动填充数据
     **/
	@SuppressWarnings("unchecked")
	public static void customizationTableClass(XWPFTable tableOne,Object obj){
		
				List<ReportWeekData_Head>	model = (List<ReportWeekData_Head>)obj;
				
				fillData(model);
				
				String projectName = null;
				
				if(model.size()>0){
					
					
					projectName= model.get(0).getProjectName();
					
				}
				//控制表格样式
				String amount = "9000";
				
				CTTbl ttbl = tableOne.getCTTbl();
					
				CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl.getTblPr();
					
				CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();												
				 
				tblWidth.setW(new BigInteger(amount));
					
				tblWidth.setType(STTblWidth.DXA);		
											
				//合并行单元格				
				Wordl2007Utis.mergeCellsHorizontal(tableOne, 0, 0, 5);
				
				Wordl2007Utis.mergeCellsHorizontal(tableOne, 1, 0, 5);
				
				Wordl2007Utis.mergeCellsHorizontal(tableOne, 2, 1, 5);
							
				//合并列单元格
				Wordl2007Utis.mergeCellsVertically(tableOne,0,2,6);
												
				//表格主题
				XWPFTableRow row = tableOne.getRow(0);				
				row.getCell(0).setText("              工程名称:"+projectName);
				
				XWPFTableRow row1 = tableOne.getRow(1);	
				row1.getCell(0).setText("                           测试单位:"+"湖南中大建设工程监测技术有限公司");
				
				XWPFTableRow row2_r = tableOne.getRow(2);					
				Wordl2007Utis.setCellText(row2_r.getCell(0), "日期", "000000", 1500, "微软雅黑", "000000", 12, false);
				
				row2_r.getCell(1).setText("                                   累计变化量");
				
				for(int j=1;j<=6;j++){//定位列
					
					//取出全部表格数据中的第一条，并将取出的数据移除model集合，下一条数据将会占用0下标(数据已经是排好顺序的)
					ReportWeekData_Head  reportWeekData_Head = 	getReportWeekData_Head(model);	
					
					List<ReportWeekData_Dates> reportWeekData_Dates  = null;
					
					//取出该列数据
					if(reportWeekData_Head!=null){
						
						 reportWeekData_Dates  = reportWeekData_Head.getReportWeekDataDates();
						
					}else{
						
						return;
						
					}
					//进行数据填充									
					for(int i=3;i<=14;i++){//定位行
						
						if(i>=3 && i<=6){//设置每列数据的表格头
							
							XWPFTableCell rowTemp = tableOne.getRow(i).getCell(j);	
							
							if(i==3)rowTemp.setText("传感器编号:"+reportWeekData_Head.getSensorNumber());
							if(i==4)rowTemp.setText("终端号:"+reportWeekData_Head.getSmuNumber());
							if(i==5)rowTemp.setText("采集器通道:"+reportWeekData_Head.getSmuChannel());
							if(i==6)rowTemp.setText("测点:"+reportWeekData_Head.getMonitorPoint());
						}else{//设置每列数据的数据值
							
							ReportWeekData_Dates reportWeekData_Date = null;
							
							if(!reportWeekData_Dates.isEmpty()){
								
								reportWeekData_Date = reportWeekData_Dates.get(0);
																
								//定位第j列第i行的单元格
								XWPFTableCell rowTemp = tableOne.getRow(i).getCell(j);
								
								XWPFTableCell rowTemp0 = tableOne.getRow(i).getCell(0);
								
							
								rowTemp.setText(reportWeekData_Date.getTotalLaserChange());
								
								//日期只填充一次，mybatis查询出的数据已经按照时间排序了,当某列数据缺少了某天的数据时，缺少的这条数据会插入与之匹配的位置,将会个时间栏位的日期对应，
								//不会打乱顺序出现日期和数据对应不上的情况。 会将缺少的数据用"无数据"替代,
								if(j==1) rowTemp0.setText(reportWeekData_Date.getTime());								
								
								//移除被填充过的数据
								reportWeekData_Dates.remove(0);
								
							}							
																					
						}
						
					}
				
				}								
				
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
	//配置createTable的参数
	
	@Override
	public CreateTableConfig getTableClassConfig(XWPFDocument doc2, XmlCursor cursor,Object obj) {
		
		return new CreateTableConfig( doc2,  cursor,  14, 6, obj);
	}
	 
	/**
	 * 重数据源中取得数据,并将使用过得数据移除集合
	 * @param model
	 * @return
	 */
	private static ReportWeekData_Head getReportWeekData_Head(List<ReportWeekData_Head> model){
		
		ReportWeekData_Head  obj= null;
		
		if(model.size()>0){
			
			obj = model.get(0);
			
			model.remove(0);
		}
		return obj;		
	}
	
	/**
	 * List<ReportWeekData_Dates> reportWeekDataDates;记录了周报的数据，每个reportWeekDataDates必修为七条数据
	 * 也就是七个ReportWeekData_Dates非空对象
	 * 在某些情况下。数据可能不完整，缺失某天上的数据，这里就是补充数据缺失的方法
	 * 
	 */

	private static void fillData(List<ReportWeekData_Head> reportWeekData_Heads){
		
		Date date = new Date();
		
		int dayOfweek  = ReportDateUtils.getDayOfWeek(date);
		
		//在ReportWeekData_Heads取出一条数据中取出一条数据长度是dayOfweek的，当做参照依据，如果没有匹配的数据条数取最大的某条当做参考依据
		ReportWeekData_Head reportWeekData_Head  = null;
		
		int max = 0;
		
		int j = 0;
		
		for(int i=0;i<reportWeekData_Heads.size();i++){
			
			int count = reportWeekData_Heads.get(i).getReportWeekDataDates().size();
			
			max = max>count?max:count;
			
			j=i;//用来记录取得max时候的reportWeekData_Heads下标
			
			if(count==dayOfweek){
				
				reportWeekData_Head = reportWeekData_Heads.get(i);
				
				break ;
			}			
		}
		
		if(reportWeekData_Head==null){
			
			reportWeekData_Head = reportWeekData_Heads.get(j);
			
		}
		//如果一个表的全部数据条数不一致。者补充数据
		List<ReportWeekData_Dates> basePoint = reportWeekData_Head.getReportWeekDataDates();//参考点
		
		for(int i=0;i<basePoint.size();i++){
			
			for(int k=0;k<reportWeekData_Heads.size();k++){
				
				List<ReportWeekData_Dates> verification = reportWeekData_Heads.get(k).getReportWeekDataDates();
				
				if(verification.size() == basePoint.size()) continue;
				
				String basePointTime = basePoint.get(i).getTime();
												
				for(int f=0;f<verification.size();f++){
					
					ReportWeekData_Dates  verificationReportWeekData_Dates = verification.get(f);//取到表格中的某条数据
					
					//和参考点对比,找出遗漏的数据（用基点日器来判断）
					if(verificationReportWeekData_Dates.getTime().equals(basePointTime)) continue;
					
					//遗漏的数据（没有采集到的数据）
					ReportWeekData_Dates reportWeekData_Dates = new ReportWeekData_Dates();
					
					reportWeekData_Dates.setTime(basePointTime);
					
					reportWeekData_Dates.setTotalLaserChange("无数据");
					
					verification.add(i, reportWeekData_Dates);
										
				}				
			}						
		}
	}
}
