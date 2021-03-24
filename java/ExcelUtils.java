/**
 * Project:homolo-heaframework
 * File:ExcelUtils.java
 * Copyright 2004-2016 Homolo Co., Ltd. All rights reserved.
 */
package java;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel 工具类.
 *
 * @author rory
 * @date Jul 28, 2016
 */
public final class ExcelUtils {

	private ExcelUtils() {
	}

	/**
	 * 设置 sheet 的某个单元格的值，如果 {@code value} 为 {@code null}，则清空单元格。. 这个api需要 poi 4.x 的时候才能修改.
	 * @param sheet
	 *            XSSFSheet 对象
	 * 设置 sheet 的某个单元格的值，如果 {@code value} 为 {@code null}，则清空单元格.
	 *
	 * 这个api需要 poi 4.x 的时候才能修改。
	 *
	 * @param row
	 *            单元格所在的行，从1开始
	 * @param column
	 *            单元格所在的列，从1开始
	 * @param value
	 *            单元格的值,如果值为 {@code null}, 则清空该单元格的值
	 */
	@SuppressWarnings("deprecation")
	public static void setValue(XSSFSheet sheet, int row, int column, Object value) {
		if (row < 1 || column < 1) {
			return;
		}
		XSSFRow r = sheet.getRow(row - 1);
		if (r != null) {
			XSSFCell cell = r.getCell(column - 1);
			if (cell != null) {
				if (value == null && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
					cell.setCellType(Cell.CELL_TYPE_BLANK);
				} else if (value instanceof String) {
					cell.setCellValue((String) value);
				} else if (value instanceof Integer) {
					cell.setCellValue((Integer) value);
				} else if (value instanceof Double) {
					cell.setCellValue((Double) value);
				}
			}
		}
	}

	/**
	 * 这里处理excel的sheetName,不能包含特殊字符.
	 *
	 * @param sheetName sheet's name
	 * @return 处理后的sheetName
	 * @see Workbook#createSheet(String)
	 */
	public static String convertSheetName(String sheetName) {
		if (StringUtils.isBlank(sheetName)) {
			return sheetName;
		}
		return StringUtils.replaceEach(sheetName, new String[] {"\\", "/", ":", "*", "?", "[", "]", "\u0000", "\u0003"},
				new String[] {"_", "_", "_", "_", "_", "_", "_", "_", "_"});
	}

	/**
	 * 重新计算excel里公式单元格的值，如果不重新计算其它单元格的值变化了，有公式的单元格的值不会自动变化. 这个api需要 poi 4.x 的时候才能修改.
	 *
	 * @param wb
	 *            工作簿
	 */
	@SuppressWarnings("deprecation")
	public static void evaluateFormula(XSSFWorkbook wb) {
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
		for (Sheet sheet : wb) {
			for (Row r : sheet) {
				for (Cell c : r) {
					if (c.getCellType() == Cell.CELL_TYPE_FORMULA) {
						evaluator.evaluateFormulaCell(c);
					}
				}
			}
		}
	}
}
