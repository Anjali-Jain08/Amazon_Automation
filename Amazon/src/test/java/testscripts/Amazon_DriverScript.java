package testscripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import datatable.Amazon_xlsReader;
import jxl.JXLException;



public class Amazon_DriverScript {

		public static EventFiringWebDriver driver = null;
		public static FileInputStream fin = null; 
		public static Amazon_xlsReader controller =  null;
		public static Amazon_xlsReader testdata = null;
		public static Properties Config = null;
		public static String firstSheetName= null;
		public static String currentTest=null;
		public static String keyword = null;
		public static String stepDescription =null;
		public static String currentTsid =null;
		public static String failTest = "Fail";
		public static String methodresult = null;
		public static String proceedOnFail;
		public static WebDriver wbdv = null;
		
		@BeforeTest
		public static void Initialization() throws IOException {
			System.out.println("Start");
			Config = new Properties();
			fin = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\config\\Amazon_config.properties");
			Config.load(fin);
			controller = new Amazon_xlsReader(System.getProperty("user.dir") + "\\src\\test\\java\\config\\amazon_controller.xls");
			testdata =  new Amazon_xlsReader(System.getProperty("user.dir") + "\\src\\test\\java\\config\\amazon_testdata.xls");

		}
		
		@Test
		public static void Testapp() throws  IOException, NullPointerException, JXLException {
	        
			System.out.println("Testapp starts");
			//To get first sheet name from the keyword excel sheet i.e., Amazon_controller1.xls
					firstSheetName = controller.getFirstSheetname();
					System.out.println("first sheet name is : " +  firstSheetName);

					//Loop to get total row count of the first sheet, to get the cell data of each row
					for(int tcid=1; tcid<controller.getRowCount(firstSheetName); tcid++) {

						//Store cell data of each row accroding to the colummn name
						String currentTest = controller.getCellData(firstSheetName, "TCID", tcid);
						System.out.println("current test " + currentTest);

						//Check if celldata of column name Runmode has Y in t's rows or not
						if(controller.getCellData(firstSheetName, "Runmode", tcid).equalsIgnoreCase("Y")) {

							//Loop to get the row count of the sheetname stored in currentTest
							for(int tsid=1; tsid<controller.getRowCount(currentTest); tsid++) {

								//Store TestScenario's Keyword
								keyword = controller.getCellData(currentTest, "Keyword", tsid);
								System.out.println("Keyword is : " + keyword);
					

						try {

							Method method = Amazon_Keyword.class.getMethod(keyword);
							System.out.println(keyword);
							method.invoke(method);
							
						}catch (Throwable e) {

							System.out.println(e.getMessage());
							System.out.println(e.getCause());
							e.printStackTrace();
						}
						
						
						
					} 
				}
				}

				if (wbdv != null) {
					driver.close();
				}

			}

		@AfterTest
		public static void Endscript() {
			
			//driver.close();
			System.out.println("Script run successfully");
		}

}
