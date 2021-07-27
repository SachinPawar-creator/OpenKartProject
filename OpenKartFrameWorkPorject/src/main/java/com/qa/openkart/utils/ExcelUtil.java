package com.qa.openkart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
;

public class ExcelUtil {

	static String Sheet_Path = "C:\\Users\\sachi\\Desktop\\Testing.xlsx";

	static Workbook book;
	static Sheet sheet;

	public static Object[][] getExcelData(String sheetname)

	{
		Object data[][]= null; 

		try {
			FileInputStream file = new FileInputStream(Sheet_Path);

			book = WorkbookFactory.create(file);
			sheet = book.getSheet(sheetname);
			
			
			 data= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()]; 
			
			
			for (int i = 0; i <sheet.getLastRowNum(); i++) {
				for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++)
				
				{
					            data[i][j] =sheet.getRow(i+1).getCell(j).toString(); 
				}
			}
			
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
return data; 
	}

	

}
