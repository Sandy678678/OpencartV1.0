package utilities;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="loginData")
	public String[][] getData() throws EncryptedDocumentException, IOException
	{
		String path=".\\testData\\Opencart_LoginData.xlsx"; //taking excel file location
		ExcelUtility xlUtil=new ExcelUtility(path);
		int rowNum=xlUtil.getRowNum("Sheet1");
		int columnNum=xlUtil.getCellNum("Sheet1", 1);
		String loginData[][]=new String[rowNum][columnNum];
		
		for(int i=1; i<=rowNum; i++)
		{
			for(int j=0; j<columnNum; j++)
			{
				loginData[i-1][j]= xlUtil.getCellValue("Sheet1", i, j);
				
			}
		}
		return loginData;
	}

}
