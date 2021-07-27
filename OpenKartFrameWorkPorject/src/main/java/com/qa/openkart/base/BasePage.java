package com.qa.openkart.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public WebDriver driver;
	public Properties pro;

	/*
	 * 
	 * @different browser as per the entered value in the config file
	 * 
	 */

	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	public WebDriver init_browser(String browser) {
		System.out.println("The entered browser name is : " + browser);

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			tldriver.set(new ChromeDriver());
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tldriver.set(new FirefoxDriver());
		}

		else {
			System.out.println("The Browser You have entered is incorrect: " + browser);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();

		return getDriver();

	}
	/*
	 * The config file loading and reading the same
	 */
	
	public static synchronized WebDriver getDriver()
	
	{
		return tldriver.get();
	}

	public Properties init_config() {
		pro = new Properties();

		try {
			FileInputStream io = new FileInputStream(
					"C:\\Users\\sachi\\eclipse-workspace\\OpenKartFrameWorkPorject\\src\\main\\java\\com\\qa\\openkart\\configproperties\\config.properties");
			pro.load(io);

		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pro;

	}

	/*
	 * THis method is used to take screnshot also it will return the path of the
	 * screenshot
	 */
	public String getScreenshot()

	{

		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/Screenshots/" + System.currentTimeMillis() + ".png";

		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;

	}

}
