package com.qa.openkart.pages;

import java.awt.List;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.openkart.base.BasePage;
import com.qa.openkart.utils.ElementUtil;

public class ProductInfoPage extends BasePage {

	private WebDriver driver;
	ElementUtil util;
	private By productheader = By.cssSelector("div#content h1");
	private By productMetada = By.cssSelector("div#content .list-unstyled:nth-of-type(1) li");
	private By productPrice = By.cssSelector("div#content .list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addtocartButton = By.id("button-cart");
	private By productimages = By.cssSelector(".thumbnails li img");
	private By cartadded = By.cssSelector("div#product-product div.alert");
	private By shoppingcart = By.linkText("shopping cart");
	private By shoppingCartButton= By.xpath("(//button[@type='button'])[position()=5]");
	private By cartButton= By.xpath("(//i[@class='fa fa-shopping-cart'])[position()=3]");
	

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);

	}

	public Map<String, String> getPorductInformation()

	{
		Map<String, String> productinfo = new HashMap<>();
		java.util.List<WebElement> productmedatalist = util.getElements(productMetada);
		productinfo.put("name", util.getElement(productheader).getText());

		for (WebElement e : productmedatalist)

		{

			productinfo.put(e.getText().split(":")[0].trim(), e.getText().split(":")[1].trim());
		}

		java.util.List<WebElement> productPriceList = util.getElements(productPrice);

		productinfo.put("price", productPriceList.get(0).getText().trim());
		productinfo.put("ExTaxPrice", productPriceList.get(1).getText().split(":")[1].trim());

		return productinfo;
	}

	public void getQuntity(String qty)

	{
		util.doSendKeys(quantity, qty);
	}

	public String addToCart() {
		util.doClick(addtocartButton);
		return util.doGetText(cartadded);

	}

	public int getProductImages() {
		System.out.println(util.getElements(productimages).size());
		return util.getElements(productimages).size();
	}

	public void cartPage() {
		util.doClick(shoppingcart);

	
	}
	
	public CartPage  CartPage()
	{
		util.doClick(shoppingCartButton);
		util.doActionsClick(cartButton);
		return new CartPage(driver);
	}
}
