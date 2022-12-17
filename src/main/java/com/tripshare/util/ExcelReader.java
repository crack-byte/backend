package com.tripshare.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

	public static List<List<String>> readExcel() throws IOException {
		List<List<String>> rows = new ArrayList<>();
		try (FileInputStream inputStream = new FileInputStream("name.xlsx")) {
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			for (Row row : sheet) {
				if (row.getRowNum() <= 1) {
					// Skip the header row
					continue;
				}
				short lastCellNum = row.getLastCellNum();
				List<String> list = new ArrayList<>();
				for (short i = 0; i < lastCellNum; i++) {
					CellType cellType = null;
					try {
						Cell cell = row.getCell(i);
						if (cell == null) break;
						cellType = cell.getCellType();
						if (cellType.equals(CellType.NUMERIC)) {
							list.add(String.valueOf(cell.getNumericCellValue()));
						} else if (cellType.equals(CellType.FORMULA) || cellType.equals(CellType._NONE)) {
							list.add("");
						} else {
							list.add(cell.getStringCellValue());
						}
					} catch (Exception e) {
						System.out.println(i);
						System.out.println(row.getRowNum());
						System.out.println(cellType);
					}
				}
				rows.add(list);
			}
			workbook.close();
		}
		return rows;
	}

}

