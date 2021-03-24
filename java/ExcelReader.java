/**
 * Project:homolo-heaframework
 * File:ExcelReader.java
 * Copyright 2004-2012 Homolo Co., Ltd. All rights reserved.
 */
package java;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * excel 读取工具类.
 *
 * @author Rory
 * @date Jun 9, 2012
 */
public final class ExcelReader {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelReader.class);

	private ExcelReader() {
	}

	public enum ExcelType {
		/** xls. */
		Xls,
		/** xlsx. */
		Xlsx
	}

	public static ExcelType getExcelType(String fileName) {
		if (org.apache.commons.lang3.StringUtils.isBlank(fileName)) {
			return null;
		}
		return fileName.toLowerCase(Locale.CHINESE).endsWith("xlsx") ? ExcelType.Xlsx : ExcelType.Xls;
	}

	/**
	 * 取第一个sheet里的数据。第一行为字段定义，会做为map的key，因此第一行的字段如果有重复的话，值会被覆盖掉.
	 *
	 * @param file 文件
	 * @return 返回读取出来的List map
	 */
	public static List<Map<String, Object>> readToMapList(File file) {
		return readToMapList(file, null);
	}

	/**
	 * 取第一个sheet里的数据。第一行为字段定义，会做为map的key，因此第一行的字段如果有重复的话，值会被覆盖掉.
	 *
	 * @param in the input stream
	 * @param excelType the excel type
	 * @return 返回读取出来的List map
	 */
	public static List<Map<String, Object>> readToMapList(InputStream in, ExcelType excelType) {
		return readToMapList(in, excelType, null);
	}

	/**
	 * 取第一个sheet里的数据。第一行为字段定义，会做为map的key，因此第一行的字段如果有重复的话，值会被覆盖掉.
	 *
	 * @param in
	 *            InputStream对象
	 * @param excelType
	 *            Excel类型
	 * @param fields
	 *            要读取的xls中的字段,如果为null或者空数组就返回所有的字段
	 * @return 返回读取出来的List map
	 */
	public static List<Map<String, Object>> readToMapList(InputStream in, ExcelType excelType, String[] fields) {
		return readToMapList(in, excelType, fields, 0);
	}

	/**
	 * 取第一个sheet里的数据。第一行为字段定义，会做为map的key，因此第一行的字段如果有重复的话，值会被覆盖掉.
	 *
	 * @param file
	 *            File对象
	 * @param fields
	 *            要读取的xls中的字段,如果为null或者空数组就返回所有的字段
	 * @return 返回读取出来的List map
	 */
	public static List<Map<String, Object>> readToMapList(File file, String[] fields) {
		return readToMapList(file, fields, 0);
	}

	/**
	 * 取指定位置sheet里的数据。第一行为字段定义，会做为map的key，因此第一行的字段如果有重复的话，值会被覆盖掉.
	 *
	 * @param file
	 *            File对象
	 * @param fields
	 *            要读取的xls中的字段,如果为null或者空数组就返回所有的字段
	 * @param sheetIndex
	 *            sheet坐标 从0开始
	 * @return 返回读取出来的List map
	 */
	public static List<Map<String, Object>> readToMapList(File file, String[] fields, Integer sheetIndex) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		try {
			ExcelType type = ExcelType.Xls;
			if (file.getName().toLowerCase(Locale.CHINESE).endsWith("xlsx")) {
				type = ExcelType.Xlsx;
			}
			return readToMapList(new FileInputStream(file), type, fields, sheetIndex);
		} catch (FileNotFoundException e) {
			LOGGER.error("file not found", e);
		}
		return dataList;
	}

	/**
	 * 取指定位置sheet里的数据。第一行为字段定义，会做为map的key，因此第一行的字段如果有重复的话，值会被覆盖掉.
	 *
	 * 等升级到 poi 4.x 的时候才能修改。
	 *
	 * @param inputStream
	 *            InputStream对象
	 * @param excelType excel 类型
	 * @param fields
	 *            要读取的xls中的字段,如果为null或者空数组就返回所有的字段
	 * @param sheetIndex
	 *            sheet坐标 从0开始
	 * @return 返回读取出来的List map
	 */
	@SuppressWarnings("deprecation")
	public static List<Map<String, Object>> readToMapList(InputStream inputStream, ExcelType excelType, String[] fields, Integer sheetIndex) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		Workbook wb = null;
		try {
			if (excelType.equals(ExcelType.Xlsx)) {
				wb = new XSSFWorkbook(inputStream);
				XSSFSheet sheet = ((XSSFWorkbook) wb).getSheetAt(sheetIndex);
				int rows = sheet.getLastRowNum();
				XSSFRow headerRow = sheet.getRow(0);
				Map<Integer, String> fieldIndex = new HashMap<Integer, String>();

				int headerCellCount = headerRow.getLastCellNum();
				for (int i = 0; i < headerCellCount; i++) {
					XSSFCell cell = headerRow.getCell(i);
					if (cell == null) {
						continue;
					}
					if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						String cellValue = cell.getStringCellValue();
						if (fields == null || fields.length == 0 || org.apache.commons.lang3.ArrayUtils.contains(fields, cellValue)) {
							fieldIndex.put(i, cellValue);
						}
					}
				}
				for (int r = 1; r <= rows; r++) {
					XSSFRow row = sheet.getRow(r);
					if (row == null) {
						continue;
					}
					Map<String, Object> dataMap = new HashMap<String, Object>();

					for (int c = 0; c < headerCellCount; c++) {
						if (fieldIndex.get(c) == null) {
							continue;
						}
						XSSFCell cell = row.getCell(c);
						Object value = null;
						if (cell != null) {
							switch (cell.getCellType()) {

							case HSSFCell.CELL_TYPE_FORMULA:
								value = cell.getCellFormula();
								break;

							case HSSFCell.CELL_TYPE_NUMERIC:
								if (DateUtil.isCellDateFormatted(cell)) {
									value = cell.getDateCellValue();
								} else {
									value = decimalFormat.format(cell.getNumericCellValue());
								}
								break;

							case HSSFCell.CELL_TYPE_STRING:
								value = cell.getStringCellValue();
								break;

							default:
							}
						}
						dataMap.put(fieldIndex.get(c), value == null ? null : value);
					}
					dataList.add(dataMap);
				}
			} else {
				wb = new HSSFWorkbook(inputStream);
				HSSFSheet sheet = ((HSSFWorkbook) wb).getSheetAt(sheetIndex);
				int rows = sheet.getLastRowNum();
				HSSFRow headerRow = sheet.getRow(0);
				Map<Integer, String> fieldIndex = new HashMap<Integer, String>();

				int headerCellCount = headerRow.getLastCellNum();
				for (int i = 0; i < headerCellCount; i++) {
					HSSFCell cell = headerRow.getCell(i);
					if (cell == null) {
						continue;
					}
					if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						String cellValue = cell.getStringCellValue();
						if (fields == null || fields.length == 0 || org.apache.commons.lang3.ArrayUtils.contains(fields, cellValue)) {
							fieldIndex.put(i, cellValue);
						}
					}
				}
				// DecimalFormat format = new DecimalFormat("#.##");
				for (int r = 1; r <= rows; r++) {
					HSSFRow row = sheet.getRow(r);
					if (row == null) {
						continue;
					}
					Map<String, Object> dataMap = new HashMap<String, Object>();

					for (int c = 0; c < headerCellCount; c++) {
						if (fieldIndex.get(c) == null) {
							continue;
						}
						HSSFCell cell = row.getCell(c);
						Object value = null;
						if (cell != null) {
							switch (cell.getCellType()) {

							case HSSFCell.CELL_TYPE_FORMULA:
								value = cell.getCellFormula();
								break;

							case HSSFCell.CELL_TYPE_NUMERIC:
								if (DateUtil.isCellDateFormatted(cell)) {
									value = cell.getDateCellValue();
								} else {
									value = decimalFormat.format(cell.getNumericCellValue());
								}
								break;

							case HSSFCell.CELL_TYPE_STRING:
								value = cell.getStringCellValue();
								break;

							default:
							}
						}
						dataMap.put(fieldIndex.get(c), value == null ? null : value);
					}
					dataList.add(dataMap);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(wb);
		}
		return dataList;
	}

	public static String cutZero(String v) {
		if (v.indexOf(".") > -1) {
			while (true) {
				if (v.lastIndexOf("0") == (v.length() - 1)) {
					v = v.substring(0, v.lastIndexOf("0"));
				} else {
					break;
				}
			}
			if (v.lastIndexOf(".") == (v.length() - 1)) {
				v = v.substring(0, v.lastIndexOf("."));
			}
		}
		return v;
	}
}
