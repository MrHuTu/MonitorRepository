package com.zhongda.monitor.business.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordUtil2007 {
	
	private static final Logger logger = LoggerFactory.getLogger(WordUtil2007.class);
	
	/**
	 * 
	 * 根据指定的参数值、模板，生成 word 文档
	 * @param param
	 *            需要替换的变量
	 * @param template
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
			logger.error("替换占位符失败", e);
		}
		logger.info("替换占位符成功");
		return doc;

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

	public static void processParagraphs(List<XWPFParagraph> paragraphList,

	Map<String, Object> param, XWPFDocument doc) {

		if (paragraphList != null && paragraphList.size() > 0) {

			for (XWPFParagraph paragraph : paragraphList) {

				List<XWPFRun> runs = paragraph.getRuns();

				for (XWPFRun run : runs) {

					String text = run.getText(0);

					if (text != null) {

						boolean isSetText = false;

						for (Entry<String, Object> entry : param.entrySet()) {

							String key = entry.getKey();

							if (text.indexOf(key) != -1) {

								isSetText = true;

								Object value = entry.getValue();

								if (value instanceof String) {// 文本替换

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
	 * @param key
	 * 
	 *            定位的变量值
	 * 
	 * @param doc
	 * 
	 *            需要替换的DOC
	 */

	public static void insertTab(String key, XWPFDocument doc2) {

		List<XWPFParagraph> paragraphList = doc2.getParagraphs();

		if (paragraphList != null && paragraphList.size() > 0) {

			for (XWPFParagraph paragraph : paragraphList) {

				List<XWPFRun> runs = paragraph.getRuns();

				for (XWPFRun run : runs) {

					String text = run.getText(0);

					if (text != null) {

						if (text.indexOf(key) >= 0) {

							XmlCursor cursor = paragraph.getCTP().newCursor();

							XWPFTable tableOne = doc2.insertNewTbl(cursor);// ---这个是关键，创建一个表格
							
							
							

							XWPFTableRow tableOneRowOne = tableOne.getRow(0);//插入一行，带表头

							tableOneRowOne.getCell(0).setText("表头");
						
							
							 tableOneRowOne.addNewTableCell().setText("第1行第2列");

							 tableOneRowOne.addNewTableCell().setText("第1行第3列");

							 tableOneRowOne.addNewTableCell().setText("第1行第4列");

							XWPFTableRow tableOneRowTwo = tableOne.createRow();//差入一行，以第一行为标准生成单元格

							tableOneRowTwo.getCell(0).setText("第二行第一列");

							tableOneRowTwo.getCell(1).setText("第二行第二列");

							 tableOneRowTwo.getCell(2).setText("第2行第3列");
							 
							 tableOneRowTwo.getCell(3).setText("第2行第4列");

						

						}

					}

				}

			}

		}

	}

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
	 * 
	 * 测试用方法
	 */

	public static void main(String[] args) throws Exception {

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("${1}", "哈哈哈哈");

		param.put("${2}", "信息管理与信息系统");

		param.put("${3}", "男");

		param.put("${4}", "大学");

		param.put("${5}", "2016-09-21");

		Map<String, Object> twocode = new HashMap<String, Object>();

		twocode.put("width", 100);

		twocode.put("height", 100);

		twocode.put("type", "png");

		XWPFDocument doc = WordUtil2007.generateWord(param, " D:/temp/test1.docx");

		WordUtil2007.insertTab("${table}", doc); // /----------创建表

		WordUtil2007.insertImage("${image}", doc); // /----------创建图

		// ------替换多余的标志位----//

		param = new HashMap<String, Object>();

		//param.put("${test}", "下一个段落");

		param.put("${table}", "");

		param.put("${image}", "");

		WordUtil2007.processParagraphs(doc.getParagraphs(), param, doc);

		FileOutputStream fopts = new FileOutputStream("d:\\2007-2.docx");

		doc.write(fopts);

	}
	  

}