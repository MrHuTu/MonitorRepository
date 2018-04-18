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
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
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

import com.zhongda.monitor.report.configclass.ReportConfig;
import com.zhongda.monitor.report.configclass.configmodel.CreateTableConfig;
import com.zhongda.monitor.report.configclass.configmodel.TableBorder;
import com.zhongda.monitor.report.configclass.tableclass.SedimentationTableClass;
import com.zhongda.monitor.report.model.fictitious.SideTableDataModel;
import com.zhongda.monitor.report.service.BastTableClass;




public class Wordl2007Utis {
	

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

					if (text != null) {

						boolean isSetText = false;

						for (Entry<String, Object> entry : param.entrySet()) {

							String key = entry.getKey();

							if (text.indexOf(key) != -1) {

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

					if (text.length()>0) {
					Iterator<String> ite = 	map.keySet().iterator();
					while(ite.hasNext()){
						
						String key =  ite.next();
						Object obj =  map.get(key);
						if(obj  instanceof Boolean){
							
							 if (text.indexOf(key) >= 0) {
								 									
								run.setText("",0);
								
								XmlCursor cursor = paragraph.getCTP().newCursor();
								
								SedimentationTableClass.createTableSpance(doc2, cursor,"("+count+")");
								
								count++;
							 }
						}else{
							if(text.indexOf(key) >= 0){																
									
								run.setText("",0);
								
								XmlCursor cursor = paragraph.getCTP().newCursor();
								
								String[] paths= classPath.split("\\|");
								
								if(paths.length<2){
									try {
										throw new Exception("请检查ReportConfig配置");
									} catch (Exception e) {
									
										e.printStackTrace();
									}
								}
								//调用CreateTableConfig下的自定义方法，此定义方法返回一个封装了一个表格的全部数据(SideTableDataModel对象)
								CreateTableConfig createTableConfig= callMethod(paths[1],doc2,cursor,(SideTableDataModel)obj);
							
								//调用ReportConfig中配置生成表格的类,该配置类必须以createTable方法为入口，自动填充数据
								callMethod(paths[0],createTableConfig);
								
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
	public static void insertImage(String key, XWPFDocument doc) {

		List<XWPFParagraph> paragraphList = doc.getParagraphs();

		try {

			if (paragraphList != null && paragraphList.size() > 0) {

				for (XWPFParagraph paragraph : paragraphList) {

					List<XWPFRun> runs = paragraph.getRuns();

					for (XWPFRun run : runs) {

						String text = run.getText(0);

						if (text != null) {

							if (text.indexOf(key) >= 0) {
								
								run.setText("",0);
								
								run.addBreak();

								run.addPicture(

								new FileInputStream("D:/hc.jpg"),
										Document.PICTURE_TYPE_JPEG,
										"D:/hc.jpg", Units.toEMU(200),
										Units.toEMU(200)); // 200x200 pixels

								run.addBreak(BreakType.PAGE);

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
	public static Map<String, Object> insertTabSinge(XWPFDocument doc2,Map<String, List<String>> singe,ArrayList<SideTableDataModel> model) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<XWPFParagraph> paragraphList = doc2.getParagraphs();

		if (paragraphList != null && paragraphList.size() > 0) {
			
			for (int j = 0; j < paragraphList.size(); j++) {
				
				XWPFParagraph paragraph = paragraphList.get(j);

				List<XWPFRun> runs = paragraph.getRuns();
				for(int k=0;k<runs.size();k++){
					
					XWPFRun run = runs.get(k);
				
					String text = run.getText(0);

					if (text.length()>0) {

						Iterator<String> iter = singe.keySet().iterator();

						while (iter.hasNext()) {

							String key = iter.next();

							if (text.indexOf(key) != -1) {
							
								List<String> listSinge = singe.get(key);
								
								text = "";
								
								run.setText(text, 0);
								
															
								for (int i = 0; i < listSinge.size(); i++) {
									 if((i+1)%2!=0){
										 map.put(listSinge.get(i),false);
									 }else{
										 try{
											 map.put(listSinge.get(i),model.get(0));
											 model.remove(0);
										 }catch(IndexOutOfBoundsException e){
											System.out.println("插入表格占位符时,必须传入相匹配的数据源");
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
	public void mergeCellsVertically(XWPFTable table, int col, int fromRow,int toRow) {
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
		
		private static CreateTableConfig callMethod(String noWmethodName,XWPFDocument doc2,XmlCursor cursor,SideTableDataModel sideTableDataModel){
			 String className = "com.zhongda.monitor.report.configclass.configmodel.CreateTableConfig";
			  String methodName =  noWmethodName;
			  
					 
			  CreateTableConfig  result = null;
			try {
				@SuppressWarnings("rawtypes")
				Class clz = Class.forName(className);
				 //  
				Object obj = clz.newInstance();			   		
				  //获取方法  			
				   Method m = obj.getClass().getMethod(methodName,XWPFDocument.class,XmlCursor.class,SideTableDataModel.class);
				  //调用方法  
				
				     result =  (CreateTableConfig) m.invoke(obj, doc2,cursor,sideTableDataModel);
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

		param.put("${name}", "哈哈哈哈");
		
		//param.put("${T_1}", "xxxxx报告");  大标题以T_开头

		Map<String, Object> twocode = new HashMap<String, Object>();

		twocode.put("width", 100);

		twocode.put("height", 100);

		twocode.put("type", "png");

		XWPFDocument doc = Wordl2007Utis.generateWord(param,"d:/reportTemp/test1.docx");

		// 动态插入表格
		Map<String, List<String>> singe = new HashMap<String, List<String>>();

		List<String> listSinge = new ArrayList<String>();		
		
		
		Map<String, List<String>> singe1 = new HashMap<String, List<String>>();

		List<String> listSinge1 = new ArrayList<String>();	
		//占位符，奇数为标题，偶数为表格
		listSinge.add("${2}");	
		
		listSinge.add("${3}");
		
		listSinge.add("${4}");	
		
		listSinge.add("${5}");
		
		listSinge.add("${11}");	
		
		listSinge.add("${12}");
		//表格数据
		ArrayList<SideTableDataModel> model = new ArrayList<SideTableDataModel>();
		SideTableDataModel sideTableHeadModel  = new SideTableDataModel();
		sideTableHeadModel.unifyLength();
	
		model.add(sideTableHeadModel);
		model.add(sideTableHeadModel);
		model.add(sideTableHeadModel);
		//**************************************//
		listSinge1.add("${6}");	
		
		listSinge1.add("${7}");
		
		listSinge1.add("${8}");	
		
		listSinge1.add("${9}");
		ArrayList<SideTableDataModel> model1 = new ArrayList<SideTableDataModel>();
		SideTableDataModel sideTableHeadModel1  = new SideTableDataModel();
		sideTableHeadModel1.unifyLength();
		model1.add(sideTableHeadModel1);
		model1.add(sideTableHeadModel1);
		
		singe.put("${singe}", listSinge);
		singe1.put("${singe1}", listSinge1);
		//在模板中的指定位置。插入2中类型的占位符--相对位移 和水平位移
		Map<String,Object> mapTable1 = insertTabSinge(doc, singe,model);
		Map<String,Object> mapTable2 =insertTabSinge(doc, singe1,model1);
		
		
		//在生成占位符的地方创建表格。
		Wordl2007Utis.insertTab(doc,mapTable1,ReportConfig.SEDIMENTATION); // /----------创建表
		Wordl2007Utis.insertTab(doc,mapTable2,ReportConfig.COVERGENCE); // /----------创建表
		 
		//WordUtil2007.insertImage("${image}", doc); // /----------创建图

	
		FileOutputStream fopts = new FileOutputStream("d:/在线检测报告/2007-2.docx");

		doc.write(fopts);

	}

}