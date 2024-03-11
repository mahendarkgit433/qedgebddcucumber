package commonFunctions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class FunctionLibrary
{
	public static Properties prop;
	public static RemoteWebDriver driver;
	public static RemoteWebDriver startBrowser()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}
	//method to launch url
	public static void openUrl(String url)
	{
		driver.get(url);
		driver.manage().window().minimize();
	}
	// method to waitForElement	
	public static void waitForElement(String Locator_Type,String Locator_Value,String TestData)//TestData is time in seconds
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(Integer.parseInt(TestData)));
		if(Locator_Type.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator_Value)));
		}
		if(Locator_Type.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Locator_Value)));
		}
		if(Locator_Type.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Locator_Value)));
		}
		//Similarly for other locators
	}
	//Method for typeAction to perform type action in text box
	public static void typeAction(String Locator_Type,String Locator_Value,String TestData)
	{
		if(Locator_Type.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(Locator_Value)).clear();
			driver.findElement(By.xpath(Locator_Value)).sendKeys(TestData);
		}
		if(Locator_Type.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(Locator_Value)).clear();
			driver.findElement(By.id(Locator_Value)).sendKeys(TestData);
		}
		if(Locator_Type.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(Locator_Value)).clear();
			driver.findElement(By.name(Locator_Value)).sendKeys(TestData);
		}
		//Similarly for other locators
	}
	//Method for clickAction to perform click action in button/link/image/radio/checkbox
	public static void clickAction(String Locator_Type, String Locator_Value)
	{
		//driver.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id(Locator_Value)));
		if(Locator_Type.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(Locator_Value)).click();
		}
		if(Locator_Type.equalsIgnoreCase("id"))
		{
				driver.findElement(By.id(Locator_Value)).click();
		}
		if(Locator_Type.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(Locator_Value)).click();
		}
		//Similarly for other locators
	}
	//method to validate title of the page
	public static void validateTitle(String TestData)//TestData is expected title
	{
		String actualTitle = driver.getTitle();
		try
		{
			Assert.assertEquals(actualTitle, TestData,"Title is not matched");//Message Displays on when mis match happen
		}
		catch(AssertionError e)
		{
			Reporter.log(e.getMessage(),true);
		}
	}
	//method to close browser
	public static void closeBrowser()
	{
		driver.close();
	}
	//Generate Date Method for Report
	public static String generateDate()
	{
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		return sdf.format(d);
	}
	//Method for Dropdown or Listbox
	public static void dropDownAction(String Locator_Type,String Locator_Value,String TestData)
	{
		if(Locator_Type.equalsIgnoreCase("xpath"))
		{
			Select s = new Select(driver.findElement(By.xpath(Locator_Value)));
			s.selectByIndex(Integer.parseInt(TestData));
		}
		if(Locator_Type.equalsIgnoreCase("id"))
		{
			Select s = new Select(driver.findElement(By.id(Locator_Value)));
			s.selectByIndex(Integer.parseInt(TestData));
		}
		if(Locator_Type.equalsIgnoreCase("name"))
		{
			Select s = new Select(driver.findElement(By.name(Locator_Value)));
			s.selectByIndex(Integer.parseInt(TestData));
		}
	}
	//Method to capture Stock Number into a text file like notepad
	public static String captureStockNumber(String Locator_Type,String Locator_Value) throws Throwable
	{
		String stockNumber="";
		if(Locator_Type.equalsIgnoreCase("xpath"))
		{
			stockNumber = driver.findElement(By.xpath(Locator_Value)).getAttribute("value");
		}
		if(Locator_Type.equalsIgnoreCase("id"))
		{
			stockNumber = driver.findElement(By.id(Locator_Value)).getAttribute("value");
		}
		if(Locator_Type.equalsIgnoreCase("name"))
		{
			stockNumber = driver.findElement(By.name(Locator_Value)).getAttribute("value");
		}
		FileWriter fw=new FileWriter("src/test/resources/CaptureData/StockNumber.txt");//Physical file so memory required
		BufferedWriter bw=new BufferedWriter(fw);//Allocate Memory to fw file path
		bw.write(stockNumber);
		bw.flush();
		bw.close();
		return stockNumber;
	}
	//method for stock table
	public static void stockTable() throws Throwable 
	{
		FileReader fw=new FileReader("src/test/resources/CaptureData/StockNumber.txt");//Physical file so memory required
		BufferedReader bw=new BufferedReader(fw);//Allocate Memory to fw file path
		String exp_stock_number = bw.readLine();
		bw.close();
		//for every module search box button and panel are common so defined in property file
		if(!driver.findElement(By.xpath("//button[@data-caption='Search Panel']")).isDisplayed()) driver.findElement(By.xpath(prop.getProperty("search_panel"))).click();
		driver.findElement(By.xpath("//input[@placeholder='Search']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(exp_stock_number);
		driver.findElement(By.xpath("(//button[.='Search'])[1]")).click();
		Thread.sleep(4000);
		String act_stock_number = driver.findElement(By.xpath("//table[@id='tbl_a_stock_itemslist']/tbody/tr[1]/td[8]/descendant::span[2]")).getText();
		Reporter.log(exp_stock_number+"      "+act_stock_number,true);
		try
		{
			Assert.assertEquals(exp_stock_number, act_stock_number,"Stock number is not matching");
		}
		catch(AssertionError e)
		{
			Reporter.log(e.getMessage(),true);
		}
	}
	//Method to capture supplier number into a text file like notepad
	public static String captureSupplierNumber(String Locator_Type,String Locator_Value) throws Throwable
	{
		String supplierNumber="";
		if(Locator_Type.equalsIgnoreCase("xpath"))
		{
			supplierNumber = driver.findElement(By.xpath(Locator_Value)).getAttribute("value");
		}
		if(Locator_Type.equalsIgnoreCase("id"))
		{
			supplierNumber = driver.findElement(By.id(Locator_Value)).getAttribute("value");
		}
		if(Locator_Type.equalsIgnoreCase("name"))
		{
			supplierNumber = driver.findElement(By.name(Locator_Value)).getAttribute("value");
		}
		FileWriter fw=new FileWriter("src/test/resources/CaptureData/SupplierNumber.txt");//Physical file so memory required
		BufferedWriter bw=new BufferedWriter(fw);//Allocate Memory to fw file path
		bw.write(supplierNumber);
		bw.flush();
		bw.close();
		return supplierNumber;
	}
	//Method for supplier table
	public static void supplierTable() throws Throwable
	{

		FileReader fw=new FileReader("src/test/resources/CaptureData/SupplierNumber.txt");//Physical file so memory required
		BufferedReader bw=new BufferedReader(fw);//Allocate Memory to fw file path
		String exp_supplier_number = bw.readLine();
		bw.close();
		//for every module search box button and panel are common so defined in property file
		if(!driver.findElement(By.xpath("//button[@data-caption='Search Panel']")).isDisplayed()) driver.findElement(By.xpath("//button[@data-caption='Search Panel']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(exp_supplier_number);
		driver.findElement(By.xpath("(//button[.='Search'])[1]")).click();
		Thread.sleep(4000);
		String act_supplier_number = driver.findElement(By.xpath("(//table)[3]/tbody/tr[1]/td[6]/descendant::span[2]")).getText();
		Reporter.log(exp_supplier_number+"  &    "+act_supplier_number,true);
		try
		{
			Assert.assertEquals(exp_supplier_number, act_supplier_number,"Supplier number is not matching");
		}
		catch(AssertionError e)
		{
			Reporter.log(e.getMessage(),true);
		}
	}
	//Method to capture customer number into a text file like notepad
	public static String captureCustomerNumber(String Locator_Type,String Locator_Value) throws Throwable
	{
		String customerNumber="";
		if(Locator_Type.equalsIgnoreCase("xpath"))
		{
			customerNumber = driver.findElement(By.xpath(Locator_Value)).getAttribute("value");
		}
		if(Locator_Type.equalsIgnoreCase("id"))
		{
			customerNumber = driver.findElement(By.id(Locator_Value)).getAttribute("value");
		}
		if(Locator_Type.equalsIgnoreCase("name"))
		{
			customerNumber = driver.findElement(By.name(Locator_Value)).getAttribute("value");
		}
		FileWriter fw=new FileWriter("src/test/resources/CaptureData/CustomerNumber.txt");//Physical file so memory required
		BufferedWriter bw=new BufferedWriter(fw);//Allocate Memory to fw file path
		bw.write(customerNumber);
		bw.flush();
		bw.close();
		return customerNumber;
	}
	//Method for customer table
	public static void customerTable() throws Throwable
	{
		FileReader fw=new FileReader("src/test/resources/CaptureData/CustomerNumber.txt");//Physical file so memory required
		BufferedReader bw=new BufferedReader(fw);//Allocate Memory to fw file path
		String exp_customer_number = bw.readLine();
		bw.close();
		//for every module search box button and panel are common so defined in property file
		if(!driver.findElement(By.xpath("//button[@data-caption='Search Panel']")).isDisplayed()) driver.findElement(By.xpath("//button[@data-caption='Search Panel']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(exp_customer_number);
		driver.findElement(By.xpath("(//button[.='Search'])[1]")).click();
		Thread.sleep(4000);
		String act_customer_number = driver.findElement(By.xpath("(//table)[3]/tbody/tr[1]/td[5]/descendant::span[2]")).getText();
		Reporter.log(exp_customer_number+"  &    "+act_customer_number,true);
		try
		{
			Assert.assertEquals(exp_customer_number, act_customer_number,"Customer number is not matching");
		}
		catch(AssertionError e)
		{
			Reporter.log(e.getMessage(),true);
		}
	}
}