package commonFunLibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.PropertyFileUtil;

public class Function_library {
static WebDriver driver;
	public static WebDriver startBrowser() throws Throwable{
		if(PropertyFileUtil.getvalueforkey("Browser").equalsIgnoreCase("chrome"))
		{
		System.setProperty("webdriver.chrome.driver", "G:\\Practice\\OHRM_HYBRID\\Common Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		}
		else if(PropertyFileUtil.getvalueforkey("Browser").equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "G:\\Practice\\OHRM_HYBRID\\Common Drivers\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		return driver;
	}
	public static void openApplication(WebDriver driver) throws Throwable 
	{
		driver.get(PropertyFileUtil.getvalueforkey("Url"));
		driver.manage().window().maximize();
	}
	public static void waitForElement(WebDriver driver,String locatortype,String locatorvalue,String timetowait)
	{
		WebDriverWait wait=new WebDriverWait(driver, Integer.parseInt(timetowait));
		if(locatortype.equalsIgnoreCase("id"))
		{
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
		}
		else if(locatortype.equalsIgnoreCase("xpath")){
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
	}
		else if(locatortype.equalsIgnoreCase("linktext")){
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorvalue)));
	}
}

	public static void typeAction(WebDriver driver,String locatortype,String locatorvalue,String testdata)
	{
		if(locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorvalue)).clear();
			driver.findElement(By.id(locatorvalue)).sendKeys(testdata);
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorvalue)).clear();
			driver.findElement(By.name(locatorvalue)).sendKeys(testdata);
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorvalue)).clear();
			driver.findElement(By.xpath(locatorvalue)).sendKeys(testdata);
		}
	}
	public static void clickAction(WebDriver driver,String locatortype,String locatorvalue){
		if(locatortype.equalsIgnoreCase("id")){
			driver.findElement(By.id(locatorvalue)).click();
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorvalue)).click();
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorvalue)).click();
		}	
		else if(locatortype.equalsIgnoreCase("linktext")){
			driver.findElement(By.linkText(locatorvalue)).click();
	}
	}
	public static void captureData(WebDriver driver,String locatortype,String locatorvalue) throws Throwable

	{
		String snumber="";
		if(locatortype.equalsIgnoreCase("id"))
		{
			snumber=driver.findElement(By.id(locatorvalue)).getAttribute("value");
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			snumber=driver.findElement(By.name(locatorvalue)).getAttribute("value");
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			snumber=driver.findElement(By.xpath(locatorvalue)).getAttribute("value");
		}
		FileWriter fw=new FileWriter("G:\\Practice\\OHRM_HYBRID\\Captured Data\\EmployeeID.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(snumber);
		bw.flush();
		bw.close();
	}
	public static void tableValidation(WebDriver driver,String locatortype,String locatorvalue) throws Throwable
	{
		FileReader fr=new FileReader("G:\\Practice\\OHRM_HYBRID\\Captured Data\\EmployeeID.txt");
		@SuppressWarnings("resource")
		BufferedReader br=new BufferedReader(fr);
		String expdata=br.readLine();
		driver.findElement(By.id(PropertyFileUtil.getvalueforkey("searchid"))).clear();
		Thread.sleep(1000);
		driver.findElement(By.id(PropertyFileUtil.getvalueforkey("searchid"))).sendKeys(expdata);
		Thread.sleep(500);
		driver.findElement(By.id(PropertyFileUtil.getvalueforkey("searchbutton"))).click();
		Thread.sleep(2000);
		if(locatortype.equalsIgnoreCase("xpath"))
		{
		String actdata=driver.findElement(By.xpath(locatorvalue)).getText();
		System.out.println(actdata+" "+actdata);
		}
	}
	public static void selectAction(WebDriver driver,String locatortype,String locatorvalue,String testdata)
	{
		if(locatortype.equalsIgnoreCase("id"))
		{
			Select sel=new Select(driver.findElement(By.id(locatorvalue)));
			sel.selectByVisibleText(testdata);
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			Select sel=new Select(driver.findElement(By.name(locatorvalue)));
			sel.selectByVisibleText(testdata);
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			Select sel=new Select(driver.findElement(By.xpath(locatorvalue)));
			sel.selectByVisibleText(testdata);
		}
	}
	public static String generateDate()
	{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_hh_mm_ss");
		return sdf.format(date);
	}
  public static void closeBrowser() throws Throwable
  {
	  Thread.sleep(1000);
	driver.quit();
  }
  
}