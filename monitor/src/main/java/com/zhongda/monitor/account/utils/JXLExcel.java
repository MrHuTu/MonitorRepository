package com.zhongda.monitor.account.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.springframework.stereotype.Component;

import com.zhongda.monitor.business.model.fictitious.PublicSensorData;

@Component
public class JXLExcel {

	private int row;

	// public void export(HttpServletResponse response, List<LaserDataShow>
	// list,
	// String[] head, Project project, String detectionName) {
	// try {
	// // 创建excel文件
	// WritableWorkbook book = Workbook.createWorkbook(response
	// .getOutputStream());
	// // 设置字体
	// WritableFont writableFont = new WritableFont(
	// WritableFont.createFont("宋体"), 12);
	//
	// WritableCellFormat writableCellFormat = new WritableCellFormat(
	// writableFont);// 创建样式对象
	// // 垂直水平居中
	// writableCellFormat.setAlignment(Alignment.CENTRE);
	// writableCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
	//
	// WritableSheet sheet = book.createSheet("sheet", 0);// 创建工作簿对象
	//
	// SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
	// "yyyy-MM-dd HH:mm:SS");
	//
	// row = 0;
	// laserDataHead(sheet, row, list, project, detectionName);// 设置表信息
	// row++;
	// headExcel(sheet, head, row);// 设置表头
	// // 传数据
	// row++;
	// for (int i = 0; i < list.size(); i++) {
	// sheet.addCell(new Label(3, row, simpleDateFormat.format(list
	// .get(i).getCurrentTimes()), writableCellFormat));
	// sheet.addCell(new Label(0, row,
	// list.get(i).getFirstData() + "", writableCellFormat));
	// sheet.addCell(new Label(1, row, simpleDateFormat.format(list
	// .get(i).getPreviousTime()), writableCellFormat));
	// sheet.addCell(new Label(2, row, list.get(i).getPreviousData()
	// + "", writableCellFormat));
	// sheet.addCell(new Label(4, row, list.get(i).getCurrentData()
	// + "", writableCellFormat));
	// sheet.addCell(new Label(5, row, list.get(i)
	// .getCurrentLaserChange() + "", writableCellFormat));
	// sheet.addCell(new Label(6, row, list.get(i)
	// .getTotalLaserChange() + "", writableCellFormat));
	// sheet.addCell(new Label(7, row, String.valueOf(list.get(i)
	// .getSpeedChange()), writableCellFormat));
	// row++;
	// }
	// book.write();
	// book.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (WriteException e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * 沉降
	 * 
	 * @param response
	 * @param list
	 * @param head
	 * @param project
	 * @param detectionName
	 */
	// public void export_static(HttpServletResponse response,
	// List<StaticLevelDataShow> list, String[] head, Project project,
	// String detectionName) {
	// try {
	// // 创建excel文件
	// WritableWorkbook book = Workbook.createWorkbook(response
	// .getOutputStream());
	// // 设置字体
	// WritableFont writableFont = new WritableFont(
	// WritableFont.createFont("宋体"), 12);
	//
	// WritableCellFormat writableCellFormat = new WritableCellFormat(
	// writableFont);// 创建样式对象
	// // 垂直水平居中
	// writableCellFormat.setAlignment(Alignment.CENTRE);
	// writableCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
	//
	// WritableSheet sheet = book.createSheet("sheet", 0);// 创建工作簿对象
	//
	// SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
	// "yyyy-MM-dd HH:mm:SS");
	//
	// row = 0;
	// laserDataHead_static(sheet, row, list, project, detectionName);// 设置表信息
	// row++;
	// headExcel(sheet, head, row);// 设置表头
	// // 传数据
	// row++;
	// for (int i = 0; i < list.size(); i++) {
	// sheet.addCell(new Label(3, row, simpleDateFormat.format(list
	// .get(i).getCurrentTimes()), writableCellFormat));
	// sheet.addCell(new Label(0, row,
	// list.get(i).getFirstData() + "", writableCellFormat));
	// sheet.addCell(new Label(1, row, simpleDateFormat.format(list
	// .get(i).getPreviousTime()), writableCellFormat));
	// sheet.addCell(new Label(2, row, list.get(i).getPreviousData()
	// + "", writableCellFormat));
	// sheet.addCell(new Label(4, row, list.get(i).getCurrentData()
	// + "", writableCellFormat));
	// sheet.addCell(new Label(5, row, list.get(i)
	// .getCurrentLaserChange() + "", writableCellFormat));
	// sheet.addCell(new Label(6, row, list.get(i)
	// .getTotalLaserChange() + "", writableCellFormat));
	// sheet.addCell(new Label(7, row, String.valueOf(list.get(i)
	// .getSpeedChange()), writableCellFormat));
	// sheet.addCell(new Label(8, row, String.valueOf(list.get(i)
	// .getCurrentTemperature()), writableCellFormat));
	// row++;
	// }
	// book.write();
	// book.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (WriteException e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * 通用检测类型导表
	 * 
	 * @param response
	 * @param list
	 * @param head
	 * @param project
	 * @param detectionName
	 */
	public void export_excel(HttpServletResponse response,
			List<PublicSensorData> list, String[] head, String projectName,
			String moniterTypeName, String detectionName) {
		try {
			// 创建excel文件
			WritableWorkbook book = Workbook.createWorkbook(response
					.getOutputStream());
			// 设置字体
			WritableFont writableFont = new WritableFont(
					WritableFont.createFont("宋体"), 12);

			WritableCellFormat writableCellFormat = new WritableCellFormat(
					writableFont);// 创建样式对象
			// 垂直水平居中
			writableCellFormat.setAlignment(Alignment.CENTRE);
			writableCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

			WritableSheet sheet = book.createSheet("sheet", 0);// 创建工作簿对象

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:SS");

			row = 0;
			universalData_Head(sheet, row, list, projectName, moniterTypeName,
					detectionName);// 设置表信息
			row++;
			headExcel(sheet, head, row);// 设置表头
			// 传数据
			row++;
			for (int i = 0; i < list.size(); i++) {
				sheet.addCell(new Label(3, row, simpleDateFormat.format(list
						.get(i).getCurrentTimes()), writableCellFormat));
				sheet.addCell(new Label(0, row,
						list.get(i).getFirstData() + "", writableCellFormat));
				sheet.addCell(new Label(1, row, simpleDateFormat.format(list
						.get(i).getPreviousTime()), writableCellFormat));
				sheet.addCell(new Label(2, row, list.get(i).getPreviousData()
						+ "", writableCellFormat));
				sheet.addCell(new Label(4, row, list.get(i).getCurrentData()
						+ "", writableCellFormat));
				sheet.addCell(new Label(5, row, list.get(i)
						.getCurrentLaserChange() + "", writableCellFormat));
				sheet.addCell(new Label(6, row, list.get(i)
						.getTotalLaserChange() + "", writableCellFormat));
				sheet.addCell(new Label(7, row, String.valueOf(list.get(i)
						.getSpeedChange()), writableCellFormat));
				if (null != list.get(i).getCurrentTemperature()) {
					sheet.addCell(new Label(8, row, String.valueOf(list.get(i)
							.getCurrentTemperature()), writableCellFormat));
				}
				row++;
			}
			book.write();
			book.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建表头
	 * 
	 * @param sheet
	 * @param head
	 * @param row
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public void headExcel(WritableSheet sheet, String[] head, Integer row)
			throws RowsExceededException, WriteException {
		// 字体样式
		WritableFont writableFont = new WritableFont(
				WritableFont.createFont("宋体"), 12);
		// 样式对象
		WritableCellFormat writableCellFormat = new WritableCellFormat(
				writableFont);
		// 垂直水平居中
		writableCellFormat.setAlignment(Alignment.CENTRE);
		writableCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		for (int i = 0; i < head.length; i++) {
			sheet.addCell(new Label(i, row, head[i], writableCellFormat));
		}
		// 行高和列宽
		sheet.setColumnView(3, 25);
		sheet.setColumnView(1, 25);
		sheet.setColumnView(0, 18);
		sheet.setColumnView(2, 18);
		sheet.setColumnView(4, 18);
		sheet.setColumnView(5, 18);
		sheet.setColumnView(6, 18);
		sheet.setColumnView(7, 20);
		sheet.setRowView(row, 400);
	}

	// public void laserDataHead(WritableSheet sheet, Integer row,
	// List<LaserDataShow> list, Project project, String detectionName) {
	// // 字体样式
	// WritableFont writableFont = new WritableFont(
	// WritableFont.createFont("宋体"), 12);
	// // 样式对象
	// WritableCellFormat writableCellFormat = new WritableCellFormat(
	// writableFont);
	//
	// SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
	// "yyyy-MM-dd HH:mm:SS");
	//
	// try {
	// sheet.mergeCells(0, row, 5, row);
	// sheet.addCell(new Label(0, row, "工程名称:" + project.getProjectName()
	// + "(" + project.getProjectName() + ")",
	// writableCellFormat));
	// row++;
	// sheet.mergeCells(0, row, 5, row);
	// sheet.addCell(new Label(0, row, "测试单位:中大检测", writableCellFormat));
	// row++;
	// sheet.mergeCells(0, row, 2, row);
	// sheet.addCell(new Label(0, row, "初次测试时间:"
	// + simpleDateFormat.format(list.get(0).getFirstTime()),
	// writableCellFormat));
	// sheet.mergeCells(3, row, 5, row);
	// sheet.addCell(new Label(3, row, "测试人:", writableCellFormat));
	// row++;
	// sheet.mergeCells(0, row, 2, row);
	// sheet.addCell(new Label(0, row, "传感器编号:"
	// + list.get(0).getSensorNumber(), writableCellFormat));
	// sheet.mergeCells(3, row, 5, row);
	// sheet.addCell(new Label(3, row, "测点名称:" + detectionName,
	// writableCellFormat));
	// row++;
	// sheet.mergeCells(0, row, 2, row);
	// sheet.addCell(new Label(0, row, "终端编号(DTU):"
	// + list.get(0).getSmuCmsId(), writableCellFormat));
	// sheet.mergeCells(3, row, 5, row);
	// sheet.addCell(new Label(3, row, "采集器通道:"
	// + list.get(0).getSmuCmsChannel(), writableCellFormat));
	// this.row = row;
	// } catch (RowsExceededException e) {
	// e.printStackTrace();
	// } catch (WriteException e) {
	// e.printStackTrace();
	// }
	// }

	// public void laserDataHead_static(WritableSheet sheet, Integer row,
	// List<StaticLevelDataShow> list, Project project, String detectionName) {
	// // 字体样式
	// WritableFont writableFont = new WritableFont(
	// WritableFont.createFont("宋体"), 12);
	// // 样式对象
	// WritableCellFormat writableCellFormat = new WritableCellFormat(
	// writableFont);
	//
	// SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
	// "yyyy-MM-dd HH:mm:SS");
	//
	// try {
	// sheet.mergeCells(0, row, 5, row);
	// sheet.addCell(new Label(0, row, "工程名称:" + project.getProjectName()
	// + "(" + project.getSysDictionary().getItemName() + ")",
	// writableCellFormat));
	// row++;
	// sheet.mergeCells(0, row, 5, row);
	// sheet.addCell(new Label(0, row, "测试单位:中大检测", writableCellFormat));
	// row++;
	// sheet.mergeCells(0, row, 2, row);
	// sheet.addCell(new Label(0, row, "初次测试时间:"
	// + simpleDateFormat.format(list.get(0).getFirstTime()),
	// writableCellFormat));
	// sheet.mergeCells(3, row, 5, row);
	// sheet.addCell(new Label(3, row, "测试人:", writableCellFormat));
	// row++;
	// sheet.mergeCells(0, row, 2, row);
	// sheet.addCell(new Label(0, row, "传感器编号:"
	// + list.get(0).getSensorId(), writableCellFormat));
	// sheet.mergeCells(3, row, 5, row);
	// sheet.addCell(new Label(3, row, "测点名称:" + detectionName,
	// writableCellFormat));
	// row++;
	// sheet.mergeCells(0, row, 2, row);
	// sheet.addCell(new Label(0, row, "终端编号(DTU):"
	// + list.get(0).getSmuCmsId(), writableCellFormat));
	// sheet.mergeCells(3, row, 5, row);
	// sheet.addCell(new Label(3, row, "采集器通道:"
	// + list.get(0).getSmuCmsChannel(), writableCellFormat));
	// this.row = row;
	// } catch (RowsExceededException e) {
	// e.printStackTrace();
	// } catch (WriteException e) {
	// e.printStackTrace();
	// }
	// }

	public void universalData_Head(WritableSheet sheet, Integer row,
			List<PublicSensorData> list, String projectName,
			String moniterTypeName, String detectionName) {
		// 字体样式
		WritableFont writableFont = new WritableFont(
				WritableFont.createFont("宋体"), 12);
		// 样式对象
		WritableCellFormat writableCellFormat = new WritableCellFormat(
				writableFont);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:SS");

		try {
			sheet.mergeCells(0, row, 5, row);
			sheet.addCell(new Label(0, row, "工程名称:" + projectName + "("
					+ moniterTypeName + ")", writableCellFormat));
			row++;
			sheet.mergeCells(0, row, 5, row);
			sheet.addCell(new Label(0, row, "测试单位:中大检测", writableCellFormat));
			row++;
			sheet.mergeCells(0, row, 2, row);
			sheet.addCell(new Label(0, row, "初次测试时间:"
					+ simpleDateFormat.format(list.get(0).getFirstTime()),
					writableCellFormat));
			sheet.mergeCells(3, row, 5, row);
			sheet.addCell(new Label(3, row, "测试人:", writableCellFormat));
			row++;
			sheet.mergeCells(0, row, 2, row);
			sheet.addCell(new Label(0, row, "传感器编号:"
					+ list.get(0).getSensorNumber(), writableCellFormat));
			sheet.mergeCells(3, row, 5, row);
			sheet.addCell(new Label(3, row, "测点名称:" + detectionName,
					writableCellFormat));
			row++;
			sheet.mergeCells(0, row, 2, row);
			sheet.addCell(new Label(0, row, "终端编号(DTU):"
					+ list.get(0).getSmuNumber(), writableCellFormat));
			sheet.mergeCells(3, row, 5, row);
			sheet.addCell(new Label(3, row, "采集器通道:"
					+ list.get(0).getSmuChannel(), writableCellFormat));
			this.row = row;
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

}
