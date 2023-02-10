package com.opencart.qa.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class excelUtility 
{
	public static final String path = "./src/test/resources/data/excelData.xlsx";
	private static FileInputStream fs = null;
	private static Workbook book;
	private static Sheet sheet;
	
	public static Object[][] getData(String sheetName)
	{
		Object[][] testData = null;
		try {
			fs = new FileInputStream(path);
			book = WorkbookFactory.create(fs);
			sheet  = book.getSheet(sheetName);
			
		} catch (IOException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		testData = new Object[rows][cols];
		
		for(int i =0;i<rows;i++)
		{
			for (int j=0;j<cols;j++)
			{
				testData[i][j]=sheet.getRow(i+1).getCell(j).toString();
			}
		}
		
		return testData;
	}
	
	
	
}
