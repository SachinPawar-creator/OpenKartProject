package com.qa.openkart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.openkart.base.NewTest;
import com.qa.openkart.listeners.ExtentReportListener;
import com.qa.openkart.utils.Constants;

public class HomePageTest extends NewTest {

	@BeforeClass
	public void HomePageSetup()

	{
		homepage = loginpage.loginButton(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test(priority = 1)
	public void HomePageVerification() {
		String titlename = homepage.homePageTilte();
		Assert.assertEquals(titlename, "My Orders");
	}

	@Test(priority = 2)
	public void itemsintheCart()

	{
		String items = homepage.cartTotal();
		System.out.println("The total itmes contains in the cart: " + items);
		Assert.assertTrue(items.contains(" $0.00"));

	}

	@Test(priority = 3)
	public void menuDataVerification() {

		Assert.assertEquals(homepage.menuData(), "[Reward Points]");

	}
	@Test(priority= 5)
	public void searchMethodVerification()
	{
		Assert.assertTrue(homepage.searchMethod("Imac"));
	}
	@Test(priority=4)
	public void multipleItemsVerficaiton() 
	{
		Assert.assertEquals(homepage.multipleItemsDisplay(), Constants.getAccountSectionLIst());
	}

}