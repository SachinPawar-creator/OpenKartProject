package com.qa.openkart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.openkart.base.NewTest;

public class ProductInfoTest extends NewTest {

	@BeforeClass
	public void porductInfoSetup()

	{
		homepage = loginpage.loginButton(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void verifyProductInfoTest()

	{
		Assert.assertTrue(homepage.searchMethod("MacBook"));
		productpage = homepage.selectproductNamefromResult("MacBook");

		Assert.assertTrue(productpage.getProductImages() == 5);
		Map<String, String> productinfo = productpage.getPorductInformation();
		System.out.println(productinfo);
		// {Brand=Apple, Availability=Out Of Stock, ExTaxPrice=$500.00, price=$500.00,
		// name=MacBook, Product Code=Product 16, Reward Points=600}

		Assert.assertEquals(productinfo.get("name"), "MacBook");
		Assert.assertEquals(productinfo.get("price"), "$500.00");
		Assert.assertEquals(productinfo.get("Brand"), "Apple");
		Assert.assertEquals(productinfo.get("Product Code"), "Product 16");

	}

	@Test(priority = 2, dependsOnMethods = "verifyProductInfoTest")
	public void verifyAddToCart()

	{

		Assert.assertTrue(productpage.addToCart().contains("Success:"));
		System.out.println("Items has been added to Cart Successfully");

		productpage.cartPage();

	}

}
