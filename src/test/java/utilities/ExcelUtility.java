package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	FileInputStream file;
	String path;
	public ExcelUtility(String path)
	{
		this.path=path;
	}
	
	public int getRowNum(String sheetName) throws EncryptedDocumentException, IOException
	{
		file=new FileInputStream(path);
		Workbook wb=WorkbookFactory.create(file);
		Sheet sheet=wb.getSheet(sheetName);
		int rowCount=sheet.getLastRowNum();
		wb.close();
		file.close();
		return rowCount;
	}
	
	public int getCellNum(String sheetName, int rowNum) throws EncryptedDocumentException, IOException
	{
		file=new FileInputStream(path);
		Workbook wb=WorkbookFactory.create(file);
		int cellCount=wb.getSheet(sheetName).getRow(rowNum).getLastCellNum();
		wb.close();
		file.close();
		return cellCount;
	}
	
	public String getCellValue(String sheetName, int rowNum, int cellNum) throws EncryptedDocumentException, IOException
	{
		file=new FileInputStream(path);
		Workbook wb=WorkbookFactory.create(file);
		String data=wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		return data;
	}

}
