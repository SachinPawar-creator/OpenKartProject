package com.qa.openkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openkart.base.BasePage;
import com.qa.openkart.utils.ElementUtil;

public class LoginPage extends BasePage
{
	private WebDriver driver;
	private ElementUtil util; 
	
	private By ForgotPassButton= By.linkText("Forgotten Password");
	
	private By EmailID1= By.id("input-email");
	private By Password1= By.id("input-password");
	private By Loginbtn= By.xpath("//input[@type='submit']");
	private By registerpage= By.linkText("Register");

	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		util= new ElementUtil(driver);
	}
	
	
	public String openkartTitle()
	
	{
		return util.waitForTitleToBePresent(driver.getTitle(), 10);
		
	}
	
	public boolean forgotBTN()
	
	{
		return util.doIsDisplayed(ForgotPassButton);
	}
	
	public HomePage loginButton(String username, String password)
	
	{
		
		util.doSendKeys(EmailID1, username);
		util.doSendKeys(Password1, password);
		util.doClick(Loginbtn);
		
		return new HomePage(driver); 
		
	}
	
	
	public RegesterationPage navigatetoRegistrationPage()
	
	{ 
		util.doClick(registerpage);
		return new RegesterationPage(driver); 
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
