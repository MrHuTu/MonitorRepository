package com.zhongda.monitor.report.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.Units;
//import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhongda.monitor.report.configclass.ReportConfig;
import com.zhongda.monitor.report.configclass.configmodel.CreateTableConfig;
import com.zhongda.monitor.report.configclass.configmodel.TableBorder;
import com.zhongda.monitor.report.configclass.tableclass.dayreport.SedimentationTableClassForDay;
import com.zhongda.monitor.report.model.fictitious.SideTableData;
import com.zhongda.monitor.report.service.BastTableClass;


/**
 * 沉降收敛日报用的格式
 * @author huchao
 *
 */


public class Wordl2007Utis {
	
	private static Logger logger = LoggerFactory.getLogger(Wordl2007Utis.class);

	/**
	 * 
	 * 超值word2007的工具类
	 * 
	 * 
	 * 
	 * @param param
	 * 
	 *            需要替换的变量
	 * 
	 * @param template
	 * 
	 *            模板
	 */

	public static XWPFDocument generateWord(Map<String, Object> param,String template) {

		XWPFDocument doc = null;

		try {

			OPCPackage pack = POIXMLDocument.openPackage(template);

			doc = new XWPFDocument(pack);
		
			if (param != null && param.size() > 0) {

				// 处理段落

				List<XWPFParagraph> paragraphList = doc.getParagraphs();

				processParagraphs(paragraphList, param, doc);
				
				//处理表格
				processTable(doc,param);
				
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return doc;

	}

	/**
	 * 处理表格中的文本,替换文本中定义的变量；
	 */
	public static void processTable(XWPFDocument doc, Map<String, Object> param) {
		// 处理表格
		Iterator<XWPFTable> it = doc.getTablesIterator();

		while (it.hasNext()) {

			XWPFTable table = it.next();

			List<XWPFTableRow> rows = table.getRows();

			for (XWPFTableRow row : rows) {

				List<XWPFTableCell> cells = row.getTableCells();

				for (XWPFTableCell cell : cells) {

					List<XWPFParagraph> paragraphListTable = cell.getParagraphs();

					processParagraphs(paragraphListTable, param, doc);

				}
			}
		}
	}

	/**
	 * 
	 * 处理段落中文本，替换文本中定义的变量；
	 * 
	 * 
	 * 
	 * @param paragraphList
	 * 
	 *            段落列表
	 * 
	 * @param param
	 * 
	 *            需要替换的变量及变量值
	 * 
	 * @param doc
	 * 
	 *            需要替换的DOC
	 */

	public static void processParagraphs(List<XWPFParagraph> paragraphList,Map<String, Object> param, XWPFDocument doc) {

		if (paragraphList != null && paragraphList.size() > 0) {

			for (XWPFParagraph paragraph : paragraphList) {

				List<XWPFRun> runs = paragraph.getRuns();
				
				for(int i=0;i<runs.size();i++){
					
					XWPFRun run = runs.get(i);
				
					String text = run.getText(0);
					

					if (text != null && text.indexOf("_pic")<=0) {
						
						text = utilSign(text,run);
						
						if(text==null) continue;
					
						boolean isSetText = false;

						for (Entry<String, Object> entry : param.entrySet()) {

							String key = entry.getKey();

							if (text.equals(key)) {

								isSetText = true;

								Object value = entry.getValue();
								
								if(text.indexOf("T_")!=-1){
								
									XWPFRun r1 = paragraph.createRun();
									
									r1.setBold(true);
									
									r1.setFontSize(22);
									
									r1.setText(value.toString());
									
									text = "";
									
								}else if (value instanceof String) {// 文本替换

									text = text.replace(key, value.toString());

								}

							}

						}

						if (isSetText) {

							run.setText(text, 0);

						}

					}

				}

			}

		}

	}
	/**
	 * 替换页眉处理方法
	 */
	public static void replaceHeader(XWPFDocument doc,Map<String,Object> map){
		
		List<XWPFHeader> header = doc.getHeaderList();
		
		boolean replace = false;
		
		for (int i = 0; i < header.size(); i++) {
			
		    List<XWPFParagraph> headerPara = header.get(i).getParagraphs();
		    
		    for (int j = 0; j < headerPara.size(); j++) {
		    	
		    		List<XWPFRun> runs = headerPara.get(j).getRuns();
		    	
				for(int k=0;k<runs.size();k++){
									
					XWPFRun run = runs.get(k);
									
					String text = run.getText(0);
					
					String temp = text.trim();
					
					Iterator<String> keys =  map.keySet().iterator();
					
					while(keys.hasNext()){

						replace = true;

						String key = keys.next();
						
						if(temp.indexOf(key)!=-1){
							
							text = text.replace(key,map.get(key).toString());
							
							
							
						}
					}
					if(replace){
						run.setText(text, 0);
					}
									
				}

		    }
		}
	}

	/**
	 * 
	 * 在定位的位置插入表格；
	 * 
	 * 
	 * 
	 * @param map
	 * 
	 *            这个参数是insertTabSinge方法的返回值,value为Boolean时,插入表格标题
	 *            value是Obj时 插入表格，和表格数据
	 * 
	 * @param doc
	 * 
	 *            需要替换的DOC
	 *            
	 */

	public static void insertTab(XWPFDocument doc2,Map<String,Object> map,String classPath) {
		int count = 1;

		List<XWPFParagraph> paragraphList = doc2.getParagraphs();

		if (paragraphList != null && paragraphList.size() > 0) {

			for (XWPFParagraph paragraph : paragraphList) {

				List<XWPFRun> runs = paragraph.getRuns();
				for(int j=0;j<runs.size();j++){
					
					XWPFRun run = runs.get(j);				

					String text = run.getText(0);

					if (text!=null && text.length()>0) {
					Iterator<String> ite = 	map.keySet().iterator();
					while(ite.hasNext()){
						
						String key =  ite.next();
						Object obj =  map.get(key);
						if(obj  instanceof Boolean){
							
							 if (text.indexOf(key) >= 0) {
								 									
								run.setText("",0);
								
								XmlCursor cursor = paragraph.getCTP().newCursor();
								
								SedimentationTableClassForDay.createTableSpance(doc2, cursor,"("+count+")");
								
								count++;
							 }
						}else{
							if(text.indexOf(key) >= 0){																
									
								run.setText("",0);
								
								XmlCursor cursor = paragraph.getCTP().newCursor();
								
								//调用CreateTableConfig下的自定义方法，此定义方法返回一个封装了一个表格的全部数据(SideTableDataModel对象)
								CreateTableConfig createTableConfig= callMethod(classPath,doc2,cursor,obj);
							
								//调用ReportConfig中配置生成表格的类,该配置类必须以createTable方法为入口，自动填充数据
								callMethod(classPath,createTableConfig);
								
							}
							
						}
						
						
					}
						
				}

			}

		}

	}

}
	/**
	 * 指定位置插入图片
	 * @param key
	 * @param doc
	 */
	public static void insertImage(Map<String,Object> map, XWPFDocument doc) {

		List<XWPFParagraph> paragraphList = doc.getParagraphs();

		try {

			if (paragraphList != null && paragraphList.size() > 0) {

				for (XWPFParagraph paragraph : paragraphList) {

					List<XWPFRun> runs = paragraph.getRuns();
					
				for(int i=0;i<runs.size();i++){
					
					XWPFRun run = runs.get(i);
					
					String text = run.getText(0);
					
					System.out.println(text);
					
					if (text != null) {
						
						text = utilSign(text,run);
						
						if(text==null) continue;
						
						Iterator<String> ite = map.keySet().iterator();
						
						while(ite.hasNext()){
							
							String key = ite.next();
							
							if (text.equals(key)) {
								
								run.setText("",0);
								
								//run.addBreak();
								
								String path = (String) map.get(key);
	
								run.addPicture(
	
								new FileInputStream(path),
										Document.PICTURE_TYPE_JPEG,
										path, Units.toEMU(500),
										Units.toEMU(200)); // 200x200 pixels
	
								//run.addBreak(BreakType.PAGE);
	
							}
						}
						
						
					
							
						
						}

						
					}

				}

			}

		} catch (InvalidFormatException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (FileNotFoundException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	/**
	 * 在指定位置插入表格占位符,占位符数量必须是偶数。singe[i+1](当i+1为奇数，为表格标题占位符，偶数为表格)
	 * 
	 * @param key
	 * @param doc2
	 * @param model 与之对应的表格数据信息
	 * @return Map<String, Object> 例如  ${1}--false(表示表格标题),${2}---Object(表示表格对应的数据)
	 */
	public static Map<String, Object> insertTabSinge(XWPFDocument doc2,Map<String, List<String>> singe,List<Object> model,String repotrTyp) {
		
		int index = 0;
		
		logger.info("进如占位方法:Map长度"+singe.size());
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<XWPFParagraph> paragraphList = doc2.getParagraphs();

		if (paragraphList != null && paragraphList.size() > 0) {
			
			for (int j = 0; j < paragraphList.size(); j++) {
				
				XWPFParagraph paragraph = paragraphList.get(j);

				List<XWPFRun> runs = paragraph.getRuns();
				
				for(int k=0;k<runs.size();k++){
					
					XWPFRun run = runs.get(k);
				
					String text = run.getText(0);			
					
					text = utilSign(text,run);
					
 					if(text==null) continue;

					if (text!=null && text.length()>0) {
						
						//logger.info("当期遍历字段:"+text);

						Iterator<String> iter = singe.keySet().iterator();

						while (iter.hasNext()) {
							
							//logger.info("进入循环占位符状态");
							
							String key = iter.next();
							
							//logger.info("当前key:"+key+",判定状态:"+text.equals(key));
							
							
							//System.out.println("text:"+text+",key"+key);
							
							
							if (/*text.indexOf(key) != -1*/text.equals(key)) {
								
								logger.info("当期前表格占位符:"+key);
							
								List<String> listSinge = singe.get(key);
								
								text = "";
								
								run.setText(text, 0);
								
															
								for (int i = 0; i < listSinge.size(); i++) {
									 if((i+1)%2!=0){
										 map.put(listSinge.get(i),false);
									 }else{
										 if(repotrTyp.equals("D")){
											 try{
												 map.put(listSinge.get(i),model.get(0));
												 
												 model.remove(0);
											 }catch(IndexOutOfBoundsException e){
												 
												 logger.error("插入表格占位符时,必须传入相匹配的数据源");
											
											 }
										 }else if(repotrTyp.equals("W")){
											 
											 List<Object>  list  =  new ArrayList<Object>();
											 
											 //每次取出参数model中的5条数据放入map中，填充周报表格数据
											 for(int z=index;z<index+5;z++){
													
													if(z>model.size()-1){
														
														break;
														
													}else{
														
														list.add(model.get(z));					
														
													}								
													
												}
											 	//记录下次遍历model的下标
												index=index+5;
											 
												map.put(listSinge.get(i),list);
										 }
										
									 }
								
									createTableSinge(paragraph,(String) listSinge.get(i));
									

								}

							}
						
							
						}

					}

				}

			}

		}
		return map;

	}
	
	/**
	 * 夸列合并表格
	 * 
	 * @param table
	 * @param row
	 * @param fromCell
	 * @param toCell
	 */
	public static void mergeCellsHorizontal(XWPFTable table, int row,int fromCell, int toCell) {
		for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
			XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
			if (cellIndex == fromCell) {
				// The first merged cell is set with RESTART merge value
				cell.getCTTc().addNewTcPr().addNewHMerge()
						.setVal(STMerge.RESTART);
			} else {
				// Cells which join (merge) the first one, are set with CONTINUE
				cell.getCTTc().addNewTcPr().addNewHMerge()
						.setVal(STMerge.CONTINUE);
			}
		}
	}

	/**
	 * 夸行合并表格
	 * 
	 * @param table
	 * @param col
	 * @param fromRow
	 * @param toRow
	 */
	public static void mergeCellsVertically(XWPFTable table, int col, int fromRow,int toRow) {
		for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
			XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
			if (rowIndex == fromRow) {
				// The first merged cell is set with RESTART merge value
				cell.getCTTc().addNewTcPr().addNewVMerge()
						.setVal(STMerge.RESTART);
			} else {
				// Cells which join (merge) the first one, are set with CONTINUE
				cell.getCTTc().addNewTcPr().addNewVMerge()
						.setVal(STMerge.CONTINUE);
			}
		}
	}
	/**
	 * 设置表格边框样式
	 * @param tableOne
	 *  @param rgb
	 */
    	public static void setBorder(XWPFTable tableOne,TableBorder  tableBorder){//"single"
    		
    		CTTblBorders borders=tableOne.getCTTbl().getTblPr().addNewTblBorders();  
    		
	        CTBorder lBorder=borders.addNewLeft();  
	           
	       lBorder.setVal(STBorder.Enum.forString(tableBorder.getLeftWire()));  
	       
	        lBorder.setSz(new BigInteger(tableBorder.getLeftSz()));  
	        
	        lBorder.setColor(tableBorder.getLefColor());  
	       
	        CTBorder rBorder=borders.addNewRight();  
	        
	        rBorder.setVal(STBorder.Enum.forString(tableBorder.getRightWire()));  
	        
	        rBorder.setSz(new BigInteger(tableBorder.getRighttSz()));  
	        
	        rBorder.setColor(tableBorder.getRightColor());  
	          
	        CTBorder tBorder=borders.addNewTop();  
	        
	        tBorder.setVal(STBorder.Enum.forString(tableBorder.getTopWire())); 
	        
	        tBorder.setSz(new BigInteger(tableBorder.getToptSz()));  
	        
	        tBorder.setColor(tableBorder.getTopColor());  
	          
	        CTBorder bBorder=borders.addNewBottom();  
	        
	        bBorder.setVal(STBorder.Enum.forString(tableBorder.getBottomWire()));  
	        
	        bBorder.setSz(new BigInteger(tableBorder.getBottomSz()));  
	        
	        bBorder.setColor(tableBorder.getBottomColor());  
	        
    	}
	
	/**
	 * 给单元格设置文本内容,宽度，字体颜色
	 * @param xDocument
	 * @param cell
	 * @param text
	 * @param bgcolor
	 * @param width
	 */
	@SuppressWarnings("unused")
	private static void setCellText(XWPFTableCell cell/*, String text*/,int width) {
		
	
		setCellText(cell,null,"000000",width,"微软雅黑","000000",12,false);
	}
	/**
	 * 给单元格设置文本内容,宽度，字体颜色
	 * @param xDocument
	 * @param cell
	 * @param text
	 * @param bgcolor
	 * @param width
	 */
	public static void setCellText(XWPFTableCell cell, String text,String bgcolor, int width,String font,String Color,int FontSize,boolean bold) {
		
		cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
		 
		CTTc cttc = cell.getCTTc();
		
		
		CTTcPr cellPr = cttc.addNewTcPr();
		
		cellPr.addNewTcW().setW(BigInteger.valueOf(width));
		
		XWPFParagraph pIO = cell.addParagraph();
		
		cell.removeParagraph(0);
		
		XWPFRun rIO = pIO.createRun();
		
		rIO.setFontFamily(font);
		
		rIO.setColor(Color);
		
		rIO.setFontSize(FontSize);
		
		rIO.setBold(bold);
				
		
		if (text != null) {
			
			rIO.setText(text);
			
		}
		
     
      
        
       
	}

	/**
	 * 设置表格的高度
	 * @param xTable
	 * @param rowNomber
	 * @param cellNumber
	 * @return
	 */
	@SuppressWarnings("unused")
	private static void setCellHight(XWPFTable xTable, int rowNomber,int cellNumber,int height ) {
		
		XWPFTableRow row = null;
		
		row = xTable.getRow(rowNomber);
		
		row.setHeight(height);
			
	}

	/**
	 * 设置段落
	 * 
	 * @param location
	 *            文本位置 1--LEFT 2--CENTER 3--RIGHT
	 * @param font
	 *            字体样式 例如微软雅黑
	 * @param fontColour
	 *            字体颜色
	 * @param fontSize
	 *            字体打下
	 * @param isBold
	 *            字体加粗
	 * @param test
	 *            段落文本内容
	 */
	public static XWPFParagraph createParagraph(XWPFParagraph titleMes, int location,String font, String fontColour, int fontSize, boolean isBold,String text) {
		//XWPFParagraph titleMes = doc.createParagraph();
		if (location == 1) {
			
			titleMes.setAlignment(ParagraphAlignment.LEFT);
			
		} else if (location == 2) {
			
			titleMes.setAlignment(ParagraphAlignment.CENTER);
			
		} else if (location == 3) {
			
			titleMes.setAlignment(ParagraphAlignment.RIGHT);
			
		}
		
		XWPFRun r1 = titleMes.createRun();
		
		r1.setBold(isBold);
		
		r1.setFontFamily(font);
		
		r1.setText(text);
		
		r1.setFontSize(fontSize);
		
		r1.setColor(fontColour);
		
		return titleMes;

	}

	/**
	 * 生成表格占位符
	 */
	public static XWPFParagraph createTableSinge(XWPFParagraph titleMes, String text) {
		
		return createParagraph(titleMes, 2, "微软雅黑", "333333", 11, false, text);
		
	}
	/**
	 * 解决解析占位符混乱的问题.譬如word中的占位符${name},在程序解析的过程中会将这个占位符解析为["${","name",",","}"]这种模式,默写时候会理想解析成["${name}"]
	 * 进而替换掉这个标签
	 * 通过这个方法将占位符统一成["${name}"]这个模式
	 */
	private static String  utilSign(String text1,XWPFRun run){
		
		if(text1=="" || text1==null) return null;
		
		if(text1.trim()=="") return null;
		
		String text = text1;
		
				if(text.equals("${")){
					
					text = null;
					
					run.setText("", 0);
					
					return text;
					
				}else if(text.equals("}")){
					
					text = null;
					
					run.setText("", 0);
					
					return text;
					
				}
				
				if(text.indexOf("${")==-1){
					
					text = "${"+text;
				}
				if(text.indexOf("}")==-1){
					
					text = text +"}";
				}	
			
				return text;
	}
	/**
	 * 呼叫方法/创建表格
	 * @param method
	 * @param value
	 * @return 
	 */
	 @SuppressWarnings("unused")
	private static void callMethod(String tableClass,CreateTableConfig objs){
		 String className = tableClass;
		  String methodName =  "createTable";
		  @SuppressWarnings("rawtypes")
		Class clz = null;
		  Object obj = null;
		try {
			clz = Class.forName(className);
			 //  
			   obj = clz.newInstance();
			   if(obj instanceof BastTableClass){
				   //获取方法  			
				   Method m = obj.getClass().getMethod(methodName,CreateTableConfig.class);
				  //调用方法  
				
				   Object  result =  m.invoke(obj, objs);
			   }
			 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {		
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	 }
	 /**
		 * 呼叫方法  格式表格数据
		 * @param method
		 * @param value
		 * @return 
		 */
		
		private static CreateTableConfig callMethod(String classPath,XWPFDocument doc2,XmlCursor cursor,Object objP){
			  String methodName =  "getTableClassConfig";
			  
					 
			  CreateTableConfig  result = null;
			try {
				@SuppressWarnings("rawtypes")
				Class clz = Class.forName(classPath);
				 //  
				Object obj = clz.newInstance();			   		
				  //获取方法  			
				   Method m = obj.getClass().getMethod(methodName,XWPFDocument.class,XmlCursor.class,Object.class);
				  //调用方法  
				
				     result =  (CreateTableConfig) m.invoke(obj, doc2,cursor,objP);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
			 
		 }
	/**
	 * 
	 * 测试用方法
	 */

	public static void main(String[] args) throws Exception {

		Map<String, Object> param = new HashMap<String, Object>();

		XWPFDocument doc = Wordl2007Utis.generateWord(param,"d:/reportTemp/2007.docx");

		// 动态插入表格
		Map<String, List<String>> singe = new HashMap<String, List<String>>();

		List<String> listSinge = new ArrayList<String>();		
		
		//占位符，奇数为标题，偶数为表格
		listSinge.add("${2}");	
		
		listSinge.add("${3}");
		
	
		//表格数据在生成占位符的时候就已经存入了map结构,一个占位符对应一个表格数据(除了标题)
		ArrayList<Object> model = new ArrayList<Object>();
		SideTableData sideTableHeadModel  = new SideTableData();
		sideTableHeadModel.unifyLength();
	
		model.add(sideTableHeadModel);
		//**************************************//
		
		
		singe.put("${tablea}", listSinge);

		//在模板中的指定位置。插入2中类型的占位符--相对位移 和水平位移
		Map<String,Object> mapTable1 = insertTabSinge(doc, singe,model,"D");			
		
		//在生成占位符的地方创建表格。
		Wordl2007Utis.insertTab(doc,mapTable1,ReportConfig.WEEK_SEDIMENTATION_BANK); // /----------创建表
		
		 
	

	
		FileOutputStream fopts = new FileOutputStream("d:/在线检测报告/2007-2.docx");

		doc.write(fopts);

	}

}