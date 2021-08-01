package com.qa.openkart.tests;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.openkart.base.NewTest;
import com.qa.openkart.utils.Constants;

public class CartTest extends NewTest {
	
	
	
	@BeforeClass
	public void landingonCartTest() 
	
	{
		homepage = loginpage.loginButton(prop.getProperty("username"), prop.getProperty("password"));
		productpage= homepage.selectproductNamefromResult("MacBook"); 

		cartpage= productpage.CartPage(); 
		
	}
	
	@Test(priority=1)
	public void verifyItemsinCart()
	{
		  int items= cartpage.totalNumberOFITemInCart(); 
		Assert.assertEquals(2, items);
		
	}

	@Test(priority=2)
	public void verifyCartItemsNames()
	{
	
		Assert.assertEquals(cartpage.cartItemsNames(), Constants.getCartAddedList());


	}
	
	@Test(priority=3)
	public void verifyCartProductData()
	{
		cartpage.cartproductDetails();
		
		
	}
	@Test(priority=4)

	public void verifyCuponCode()
	{
		Assert.assertTrue(cartpage.useCuponCode());
		
		
		
	}
	@Test(priority=5, enabled=true)

	public void verifyEstimateTaxes()
	{
		Assert.assertTrue(cartpage.estimateTaxes());
	}
	
	@Test(priority=6, enabled=true)

	public void verifyGiftCertificate()
	{
		Assert.assertTrue(cartpage.giftCertificate());
	}
	
}
