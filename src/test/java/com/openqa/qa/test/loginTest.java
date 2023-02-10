package com.openqa.qa.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.qa.utility.excelReader;
import com.opencart.qa.utility.excelUtility;

public class loginTest 
{
	WebDriver driver;
	excelUtility eutils;
	excelReader ereader;
	
	@DataProvider
	public Object[][] readExcelData()
	{
		return excelUtility.getData("Sheet1");
	}

	@BeforeClass
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\Desktop\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.get("https://www.w3schools.com/html/html_tables.asp");
	}
	
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test
	public void loadDataIntoExcel()
	{
		ereader = new excelReader(excelUtility.path);
		ereader.addSheet("HTML_Table");
		ereader.addColumn("HTML_Table", "Company Name");
		ereader.addColumn("HTML_Table", "Contact");
		ereader.addColumn("HTML_Table", "Country");
		String comp_before_xpath = "//*[@id='customers']/tbody/tr[";
		String cont_after_xpath = "]/td[2]";
		String contry_after_xpath = "]/td[3]";
		String after_xpath = "]/td[1]";
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id='customers']/tbody/tr")); 
		int rowCount = rows.size();
		for(int i = 2;i<=rowCount;i++)
		{
			String comp_Xpath = comp_before_xpath+i+after_xpath;
			String con_Xpath = comp_before_xpath+i+cont_after_xpath;
			String contry_Xpath = comp_before_xpath+i+contry_after_xpath;
			String companyName = driver.findElement(By.xpath(comp_Xpath)).getText();
			String contactName = driver.findElement(By.xpath(con_Xpath)).getText();
			String contryName = driver.findElement(By.xpath(contry_Xpath)).getText();
			ereader.setCellData("HTML_Table", "Company Name", i, companyName);
			ereader.setCellData("HTML_Table", "Contact", i, contactName);
			ereader.setCellData("HTML_Table", "Country", i, contryName);
		}
		
		
		
	}
	
	@Test(dataProvider="readExcelData")
	public void doLogin(String username, String password)
	{
		System.out.println(username);
		System.out.println(password);
		ereader = new excelReader(excelUtility.path);
//		//ereader.addSheet("TestData1");
//		ereader.addColumn("TestData1", "Status");
//		ereader.addColumn("TestData1", "empNumber");
//		
//		ereader.setCellData("TestData1", "Status", 2, "test1");
//		ereader.setCellData("TestData1", "Status", 3, "test2");
//		ereader.setCellData("TestData1", "Status", 4, "test3");
//		ereader.setCellData("TestData1", "Status", 5, "test1");
//		
//		ereader.setCellData("TestData1", "empNumber", 2, "123");
//		ereader.setCellData("TestData1", "empNumber", 3, "343");
//		ereader.setCellData("TestData1", "empNumber", 4, "344");
//		ereader.setCellData("TestData1", "empNumber", 5, "553");
//		ereader.setCellData("TestData1", "empNumber", 6, "554");
		
	}
	
	
	
	
}
