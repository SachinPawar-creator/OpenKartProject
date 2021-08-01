package com.qa.openkart.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.openkart.base.BasePage;
import com.qa.openkart.utils.Constants;
import com.qa.openkart.utils.ElementUtil;
import com.qa.openkart.utils.JavaScriptUtil;

public class CartPage extends BasePage {

	public WebDriver driver;
	public ElementUtil util;
	public JavaScriptUtil js;

	private By cartitems = By.cssSelector("div#cart img");
	private By productCartData = By
			.xpath("//a[text()='MacBook']//parent::td//following-sibling::td[contains(@class,'text')]");
	private By productCartTest = By.xpath(
			"//img[@class='img-thumbnail']//parent::a//parent::td//following-sibling::td[contains(@class,'text-')]");
	private By productQunatity = By.xpath("//a[text()='MacBook']//parent::td//following-sibling::td//div/input");
	private By usecuponcode = By.xpath("(//a//i[@class='fa fa-caret-down'])[1]");
	private By estimatetaxes = By.xpath("(//a//i[@class='fa fa-caret-down'])[2]");
	private By usegiftcert = By.xpath("(//a//i[@class='fa fa-caret-down'])[3]");
	private By enterCupon = By.name("coupon");
	private By applyCupon = By.id("button-coupon");
	private By Country = By.id("input-country");
	private By zone = By.id("input-zone");
	private By pincode = By.id("input-postcode");
	private By quote = By.id("button-quote");
	private By checkquotebox = By.name("shipping_method");
	private By buttonshipping = By.id("button-shipping");
	private By checkoutclick = By.xpath("//a[text()='Checkout']");
	private By cuponalert = By.xpath("//div[@class='alert alert-danger alert-dismissible']/i");
	private By estimateAlert = By.xpath("//div[@class='alert alert-success alert-dismissible']/i");
	private By voucher = By.cssSelector("input#input-voucher");
	private By applyvoucher = By.id("button-voucher");
	private By vouchermsg = By.xpath("/html/body/div[2]/div[1]/text()");

	public CartPage(WebDriver driver)

	{

		this.driver = driver;
		util = new ElementUtil(driver);
		js = new JavaScriptUtil(driver);

	}

	public int totalNumberOFITemInCart()

	{

		return driver.findElements(cartitems).size();
	}

	public List<String> cartItemsNames() {
		List<String> store = new ArrayList<>();

		List<WebElement> list = driver.findElements(cartitems);

		for (WebElement e : list)

		{
			store.add(e.getAttribute("title"));

		}
		return store;
	}

	public List<String> cartproductDetails()

	{

		List<String> products = new ArrayList();
		List<WebElement> productMetaData = util.getElements(productCartTest);

		for (WebElement e : productMetaData)

		{
			String data = e.getText();

			if ((!data.isEmpty()) && (!data.isBlank())) {
				products.add(data);
			}

		}
		return products;
	}

	public boolean useCuponCode() {

		util.doActionMoveToElement(usecuponcode);
		util.doActionsClick(usecuponcode);
		util.doSendKeys(applyCupon, Constants.cuponcode);

		// return util.getElement(cuponalert).toString();
		return util.doIsDisplayed(cuponalert);

	}

	public boolean estimateTaxes() {
		util.doActionMoveToElement(estimatetaxes);
		util.doActionsClick(estimatetaxes);
		util.doSelectByVisibleText(Country, Constants.country);
		
		util.doActionsClick(zone);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		util.doSelectByVisibleText(zone, "Crete");
		util.doClear(pincode);
		util.doActionsSendKeys(pincode, Constants.cuponcode);
		util.doClick(quote);
		util.doActionsClick(checkquotebox);
		util.doClick(buttonshipping);
		return util.doIsDisplayed(estimateAlert);
		// 

	}

	public boolean giftCertificate() {
		util.doActionMoveToElement(usegiftcert);
		util.doActionsClick(usegiftcert);
		util.doSendKeys(applyvoucher, "12345");
		boolean value = util.doIsDisplayed(cuponalert);
		util.waitForElementToBeClickable(checkoutclick, 5000);
		util.doClick(checkoutclick);
		
		
		return value;

	}

}
