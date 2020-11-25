package com.ibm.wude.utils;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
//	public static XSSFWorkbook getXssfWorkbook(String sheetName, String[] title, String[][] values, XSSFWorkbook wb) {
//		// 第一步，创建一个XSSFWorkbook，对应一个Excel文件
//		if (wb == null) {
//			wb = new XSSFWorkbook();
//		}
//		// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
//		XSSFSheet sheet = wb.createSheet(sheetName);
//		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
//		XSSFRow row = sheet.createRow(0);
//		// 第四步，创建单元格，并设置值表头 设置表头居中
//		XSSFCellStyle style = wb.createCellStyle();
//		style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
//		// 声明列对象
//		XSSFCell cell = null;
//		// 创建标题
//		for (int i = 0; i < title.length; i++) {
//			cell = row.createCell(i);
//			cell.setCellValue(title[i]);
//			cell.setCellStyle(style);
//		}
//		// 创建内容
//		for (int i = 0; i < values.length; i++) {
//			row = sheet.createRow(i + 1);
//			for (int j = 0; j < values[i].length; j++) {
//				// 将内容按顺序赋给对应的列对象
//				row.createCell(j).setCellValue(values[i][j]);
//			}
//		}
//		return wb;
//	}
//
//	// 发送响应流方法
//	public static void setResponseHeader(HttpServletResponse response, String fileName) {
//		try {
//			try {
//				// 设置表文件名的字符编码，不然中文文件名会乱码
//				fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			response.setContentType("application/octet-stream;charset=utf-8");
//			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//			response.addHeader("Pargam", "no-cache");
//			response.addHeader("Cache-Control", "no-cache");
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}

	/**
	 * 获取文档
	 * 
	 * @param sheetname 表单名
	 * @param title     标题栏
	 * @param content   内容
	 * @return
	 */
	public static XSSFWorkbook getWorkbook(String sheetname, String[] title, String[][] content) {
		// 新建文档实例
		XSSFWorkbook workbook = new XSSFWorkbook();

		// 在文档中添加表单
		XSSFSheet sheet = workbook.createSheet(sheetname);

		// 创建单元格格式，并设置居中
		XSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);

		// 创建第一行，用于填充标题
		XSSFRow titleRow = sheet.createRow(0);
		// 填充标题
		for (int i = 0; i < title.length; i++) {
			// 创建单元格
			XSSFCell cell = titleRow.createCell(i);
			// 设置单元格内容
			cell.setCellValue(title[i]);
			// 设置单元格样式
			cell.setCellStyle(style);
		}

		// 填充内容
		for (int i = 0; i < content.length; i++) {
			// 创建行
			XSSFRow row = sheet.createRow(i + 1);
			// 遍历某一行
			for (int j = 0; j < content[i].length; j++) {
				// 创建单元格
				XSSFCell cell = row.createCell(j);
				// 设置单元格内容
				cell.setCellValue(content[i][j]);
				// 设置单元格样式
				cell.setCellStyle(style);
			}
		}

		// 返回文档实例
		return workbook;
	}
}
