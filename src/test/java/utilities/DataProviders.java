package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

//import P_DDF1_Github.XLUtility;
import utilities.AXLUtility;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException {
		
		String path = ".\\testData\\OpenCart_LoginData.xlsx"; 
		AXLUtility xlutil = new AXLUtility(path);
		//XLUtility xlutil = new XLUtility(path);
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1",1);
		
		String loginData[][]=new String[totalrows][totalcols]; 
		
		for(int i=1;i<=totalrows;i++) 
		 {
			for(int j=0;j<totalcols;j++) 
			{
				loginData[i-1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}
		
		return loginData;  
} 

}
