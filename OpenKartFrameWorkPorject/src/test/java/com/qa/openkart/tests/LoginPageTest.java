package com.qa.openkart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.openkart.base.NewTest;
import com.qa.openkart.listeners.ExtentReportListener1;
import com.qa.openkart.utils.Constants;


@Listeners(ExtentReportListener1.class)
public class LoginPageTest extends NewTest {
	
	@Test
	public void loginpageTitleVerify()
	
	{
		String title= loginpage.openkartTitle();
		
		Assert.assertEquals(title, Constants.Login_Page_Title);
		
	}
	
	@Test
	public void forgotpassButtonVerify()
	
	{
		
		  boolean value= loginpage.forgotBTN(); 
		  Assert.assertTrue(value);		
	}
	@Test(enabled= false)
	public void doLogin()
	
	{
		loginpage.loginButton(prop.getProperty("username"), prop.getProperty("password"));
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
  }
