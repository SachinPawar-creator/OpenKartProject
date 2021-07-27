package com.qa.openkart.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.openkart.base.BasePage;
import com.qa.openkart.utils.ElementUtil;

public class HomePage extends BasePage {

	private WebDriver driver;
	private ElementUtil util; 

	private By homepage = By.xpath("//h2[text()='My Orders']");
	private By CartTotal = By.id("cart-total");
	private By alltheMenu = By.cssSelector("a.list-group-item");
	private By searchValue = By.cssSelector("div#search input[type='text']");
	private By buttonClick = By.cssSelector("div#search button[type='button']");
	private By productsClick = By.cssSelector("div.product-layout");
	private By multipleItems = By.cssSelector("div#content h2");
	private By resultItems= By.cssSelector(".product-thumb h4 a");
	List<WebElement> menulist;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		
		util= new ElementUtil(driver);

	}

	public String homePageTilte()

	{
		return driver.findElement(homepage).getText();

	}

	public String cartTotal() {
		if (driver.findElement(CartTotal).isDisplayed()) {
			return driver.findElement(CartTotal).getText();
		}
		return null;

	}

	public String menuData() {
		menulist = driver.findElements(alltheMenu);

		List<String> List = menulist.stream().filter(ele -> ele.getText().contains("Reward Points"))
				.map(ele -> ele.getText()).collect(Collectors.toList());
		List.stream().forEach(ele -> System.out.println(ele));
		return List.toString();
	}

	public boolean searchMethod(String value)

	{
		driver.findElement(searchValue).sendKeys(value);
		driver.findElement(buttonClick).click();
		if (driver.findElements(productsClick).size() > 0) {
			return true;
		}

		return false;
	}

	public List<String> multipleItemsDisplay() {
		List<String> ArrayLIst = new ArrayList<String>();
		List<WebElement> data = driver.findElements(multipleItems);
		System.out.println("The size is " + data.size());
		for (WebElement e : data)

		{
			System.out.println(e.getText());
			ArrayLIst.add(e.getText());

		}
		return ArrayLIst;
	}

	public PasswordPage passwordChange()

	{
		List<WebElement> password = driver.findElements(alltheMenu);
		// int size = password.size();

		for (WebElement e : password) {
			String text = e.getText();

			if (text.contains("password")) {
				e.click();
			}

		}
		return new PasswordPage();

	}
	
	public ProductInfoPage selectproductNamefromResult(String productname)
	{
		
		       List<WebElement> resultItemss = util.getElements(resultItems);
		       
		       System.out.println("Total Number of element searched items" + resultItemss.size());
		       
		       for(WebElement e :resultItemss )
		       {
		    	   
		    	   if(e.getText().equals(productname))
		    	   {
		    		   e.click();
		    		   break; 
		    	   }
		    	   
		       }
		       return new  ProductInfoPage(driver);
	}

}
