package com.qa.openkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openkart.base.BasePage;
import com.qa.openkart.utils.ElementUtil;

public class RegesterationPage extends BasePage {

	ElementUtil util;
	private By firstname1 = By.id("input-firstname");
	private By lastname1 = By.id("input-lastname");
	private By eamil = By.id("input-email");
	private By telephone1 = By.id("input-telephone");
	private By password1 = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");
	private By subscribeyes = By.xpath("//input[@type='radio' and @name='newsletter'  and @value='1']");
	private By subscribeno = By.xpath("//input[@type='radio' and @name='newsletter'  and @value='0']");
	private By agreementpolicty = By.xpath("//input[@type='checkbox' and @name='agree'  and @value='1']");
	private By continuebutton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By logoutbutton= By.linkText("Logout");
	private By registerpage= By.linkText("Register");


	public RegesterationPage(WebDriver driver) {

		this.driver = driver;
		util = new ElementUtil(driver);

	}

	
	public void registrationDataFillup(String firstname, String lastname, String email, String telephone,
			String password, String subscribe)

	{

		util.doSendKeys(firstname1, firstname);
		util.doSendKeys(lastname1, lastname);
		util.doSendKeys(eamil, email);
		util.doSendKeys(telephone1, telephone);
		util.doSendKeys(password1, password);
		util.doSendKeys(confirmpassword, password);

		if (subscribe.equals("yes")) {
			util.doClick(subscribeyes);
		} else {
			util.doClick(subscribeno);
		}

		util.doClick(agreementpolicty);
		util.doClick(continuebutton);
		util.doClick(logoutbutton);
		util.doClick(registerpage);

	}

}
