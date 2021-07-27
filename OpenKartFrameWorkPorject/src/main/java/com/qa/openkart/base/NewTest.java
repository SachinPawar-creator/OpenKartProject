package com.qa.openkart.base;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.qa.openkart.pages.CartPage;
import com.qa.openkart.pages.HomePage;
import com.qa.openkart.pages.LoginPage;
import com.qa.openkart.pages.ProductInfoPage;
import com.qa.openkart.pages.RegesterationPage;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class NewTest {

	public BasePage basepage;
	public HomePage homepage; 
	public Properties prop;
	public WebDriver driver;
	public LoginPage loginpage;
	public ProductInfoPage productpage; 
	public CartPage cartpage; 
	public  RegesterationPage registerpage; 

	/*
	 * browser initialization
	 */
	@BeforeTest
	public void setUP()

	{
		basepage = new BasePage();
		prop = basepage.init_config();
		driver = basepage.init_browser(prop.getProperty("browser"));
		loginpage= new LoginPage(driver);
		driver.get(prop.getProperty("url"));

	}
/*
 * browser closing 
 */
	@AfterTest
	public void tearDown() {

		driver.quit();
	}

}
