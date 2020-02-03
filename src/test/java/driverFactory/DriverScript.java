package driverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunLibrary.Function_library;
import utilities.XLUtils;

public class DriverScript 
{
static WebDriver driver;
ExtentReports report;
ExtentTest test;
public void erp_Account() throws Throwable
{
	XLUtils xl=new XLUtils();
	int rc=xl.rowCount("MasterTestCases");
	for(int i=1;i<=rc;i++)
	{
		if(xl.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("y"))
		{
	String TCmodule=xl.getCellData("MasterTestCases", i, 1);
	report=new ExtentReports("./Reports//"+TCmodule+Function_library.generateDate()+".html");
	for(int j=1;j<=xl.rowCount(TCmodule);j++)
	{
		test=report.startTest(TCmodule);
		String Description=xl.getCellData(TCmodule, j, 0);
		String Function_name=xl.getCellData(TCmodule, j, 1);
		String Locator_Type=xl.getCellData(TCmodule, j, 2);
		String Locator_value=xl.getCellData(TCmodule, j, 3);
		String Test_Data=xl.getCellData(TCmodule, j, 4);
		try
		{
			if(Function_name.equalsIgnoreCase("startbrowser"))
			{
			 driver=Function_library.startBrowser();
			 test.log(LogStatus.INFO, Description);
			}
			else if(Function_name.equalsIgnoreCase("openapplication"))
			{
				Function_library.openApplication(driver);
				 test.log(LogStatus.INFO, Description);
			}
			else if(Function_name.equalsIgnoreCase("waitforelement"))
			{
				Function_library.waitForElement(driver,Locator_Type, Locator_value, Test_Data);
				 test.log(LogStatus.INFO, Description);
			}
			else if(Function_name.equalsIgnoreCase("typeaction"))
			{
				Function_library.typeAction(driver,Locator_Type, Locator_value, Test_Data);
				 test.log(LogStatus.INFO, Description);
			}
			else if(Function_name.equalsIgnoreCase("clickaction"))
			{
				Function_library.clickAction(driver,Locator_Type, Locator_value);
				 test.log(LogStatus.INFO, Description);
			}
			else if(Function_name.equalsIgnoreCase("capturedata"))
			{
				Function_library.captureData(driver,Locator_Type, Locator_value);
				 test.log(LogStatus.INFO, Description);
			}
			else if(Function_name.equalsIgnoreCase("closebrowser"))
			{
				Function_library.closeBrowser();
				 test.log(LogStatus.INFO, Description);
			}
			else if(Function_name.equalsIgnoreCase("tablevalidation"))
			{
				Function_library.tableValidation(driver,Locator_Type, Locator_value);
				 test.log(LogStatus.INFO, Description);
			}
			else if(Function_name.equalsIgnoreCase("selectaction"))
			{
				Function_library.selectAction(driver,Locator_Type, Locator_value, Test_Data);
				 test.log(LogStatus.INFO, Description);
			}
			xl.setCellData(TCmodule, j, 5,"Pass");
			xl.setCellData("MasterTestCases", i, 3, "pass");
			 test.log(LogStatus.PASS, Description);
		}catch (Throwable t)
		{
		System.out.println("Exception Handled");
		xl.setCellData(TCmodule, j, 5,"Fail");
		xl.setCellData("MasterTestCases", i, 3, "Fail");
		test.log(LogStatus.FAIL, Description);
	    }
		report.endTest(test);
		report.flush();
		}  
}else
{
	xl.setCellData("MasterTestCases", i, 3, "Blocked");
}
}
}
}