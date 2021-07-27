package com.qa.openkart.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.openkart.base.NewTest;
import com.qa.openkart.utils.ExcelUtil;

public class RegistrationTest extends NewTest
{

	
	
	@BeforeClass
	public void registerPageOpen()
	
	{
		registerpage =loginpage.navigatetoRegistrationPage(); 
	}
	
	
	@DataProvider
	public Object[][] getRegisterData()
	
	{
		
		Object data[][]  = ExcelUtil.getExcelData("regesteration");
		
		return data; 
		
		
	}
	
	
	@Test(dataProvider= "getRegisterData")
	public  void verifyRegisteration(String firstname, String lastname, String email, String telephone,
			String password, String subscribe )
	
	{
		
		registerpage.registrationDataFillup(firstname, lastname, email, telephone, password, subscribe);
		
		
		
	}
	
	
	
	
}
