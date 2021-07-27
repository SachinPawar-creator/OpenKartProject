package com.qa.openkart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.openkart.base.BasePage;

public class ElementUtil extends BasePage {
	WebDriver driver;
	JavaScriptUtil jsUtil;

	public ElementUtil(final WebDriver driver) {
		this.driver = driver;
		this.jsUtil = new JavaScriptUtil(this.driver);
	}

	public List<WebElement> getElements(final By locator) {
		final List<WebElement> elementsList = (List<WebElement>) this.driver.findElements(locator);
		return elementsList;
	}

	public WebElement getElement(final By locator) {
		WebElement element = null;
		try {
			System.out.println("locator is : " + locator);
			element = this.driver.findElement(locator);
			if (this.pro.getProperty("highlight").equalsIgnoreCase("yes")) {
				this.jsUtil.flash(element);
				
			}
			System.out.println("WebElement is created successfully : " + locator);
		} catch (Exception e) {
			System.out.println("some exception got occurred with this locator: " + locator);
		}
		return element;
	}

	public void doSendKeys(final By locator, final String value) {
		this.waitForElementPresent(locator, 10);
		this.getElement(locator).sendKeys(new CharSequence[] { value });
	}

	public void doClick(final By locator) {
		this.waitForElementPresent(locator, 10);
		this.getElement(locator).click();
	}

	public String doGetText(final By locator) {
		this.waitForElementPresent(locator, 10);
		return this.getElement(locator).getText();
	}

	public boolean doIsDisplayed(final By locator) {
		this.waitForElementPresent(locator, 10);
		return this.getElement(locator).isDisplayed();
	}

	public void doSelectByVisibleText(final By locator, final String value) {
		final Select select = new Select(this.getElement(locator));
		select.selectByVisibleText(value);
	}

	public void doSelectByIndex(final By locator, final int index) {
		final Select select = new Select(this.getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectByValue(final By locator, final String value) {
		final Select select = new Select(this.getElement(locator));
		select.selectByValue(value);
	}

	public int doDropDownOptionsCount(final By locator) {
		return this.doGetDropDownOptions(locator).size();
	}

	public ArrayList<String> doGetDropDownOptions(final By locator) {
		final ArrayList<String> ar = new ArrayList<String>();
		final Select select = new Select(this.getElement(locator));
		final List<WebElement> OptionsList = (List<WebElement>) select.getOptions();
		for (int i = 0; i < OptionsList.size(); ++i) {
			final String text = OptionsList.get(i).getText();
			ar.add(text);
		}
		return ar;
	}

	public void doSelectDropDownValue(final By locator, final String value) {
		final Select selectday = new Select(this.getElement(locator));
		final List<WebElement> OptionsList = (List<WebElement>) selectday.getOptions();
		for (int i = 0; i < OptionsList.size(); ++i) {
			final String text = OptionsList.get(i).getText();
			if (text.equals(value)) {
				OptionsList.get(i).click();
				break;
			}
		}
	}

	public void doSelectDropDownValueWithoutSelect(final By locator, final String value) {
		final List<WebElement> optionsList = this.getElements(locator);
		for (int i = 0; i < optionsList.size(); ++i) {
			final String text = optionsList.get(i).getText();
			if (text.equals(value)) {
				optionsList.get(i).click();
				break;
			}
		}
	}

	public void selectChoiceValues(final By locator, final String... value) {
		final List<WebElement> choiceList = this.getElements(locator);
		if (!value[0].equalsIgnoreCase("ALL")) {
			for (int i = 0; i < choiceList.size(); ++i) {
				final String text = choiceList.get(i).getText();
				System.out.println(text);
				for (int k = 0; k < value.length; ++k) {
					if (text.equals(value[k])) {
						choiceList.get(i).click();
						break;
					}
				}
			}
		} else {
			try {
				for (int all = 0; all < choiceList.size(); ++all) {
					choiceList.get(all).click();
				}
			} catch (Exception ex) {
			}
		}
	}

	public void doDragAndDrop(final By source, final By target) {
		final Actions action = new Actions(this.driver);
		final WebElement sourceEle = this.getElement(source);
		final WebElement targetEle = this.getElement(target);
		action.dragAndDrop(sourceEle, targetEle).build().perform();
	}

	public void doActionsSendKeys(final By locator, final String value) {
		final Actions action = new Actions(this.driver);
		action.sendKeys(this.getElement(locator), new CharSequence[] { value }).build().perform();
	}

	public void doActionsClick(final By locator) {
		this.waitForElementPresent(locator, 10);
		final Actions action = new Actions(this.driver);
		action.click(this.getElement(locator)).build().perform();
	}

	public List<WebElement> visibilityofAllElements(final By locator, final int timeout) {
		final WebDriverWait wait = new WebDriverWait(this.driver, (long) timeout);
		return (List<WebElement>) wait.until((Function) ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public WebElement waitForElementPresent(final By locator, final int timeout) {
		final WebDriverWait wait = new WebDriverWait(this.driver, (long) timeout);
		final WebElement element = (WebElement) wait
				.until((Function) ExpectedConditions.presenceOfElementLocated(locator));
		return element;
	}

	public WebElement waitForElementToBeVisible(final By locator, final int timeout) {
		final WebElement element = this.getElement(locator);
		final WebDriverWait wait = new WebDriverWait(this.driver, (long) timeout);
		wait.until((Function) ExpectedConditions.visibilityOf(element));
		return element;
	}

	public WebElement waitForElementToBeClickable(final By locator, final int timeout) {
		final WebDriverWait wait = new WebDriverWait(this.driver, (long) timeout);
		final WebElement element = (WebElement) wait.until((Function) ExpectedConditions.elementToBeClickable(locator));
		return element;
	}

	public boolean waitForUrl(final String url, final int timeout) {
		final WebDriverWait wait = new WebDriverWait(this.driver, (long) timeout);
		return (boolean) wait.until((Function) ExpectedConditions.urlContains(url));
	}

	public Alert waitForAlertToBePresent(final int timeout) {
		final WebDriverWait wait = new WebDriverWait(this.driver, (long) timeout);
		final Alert alert = (Alert) wait.until((Function) ExpectedConditions.alertIsPresent());
		return alert;
	}

	public void clickWhenReady(final By locator, final int timeout) {
		final WebDriverWait wait = new WebDriverWait(this.driver, (long) timeout);
		final WebElement element = (WebElement) wait.until((Function) ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public String waitForTitleToBePresent(final String title, final int timeout) {
		final WebDriverWait wait = new WebDriverWait(this.driver, (long) timeout);
		wait.until((Function) ExpectedConditions.titleContains(title));
		return this.driver.getTitle();
	}

	public List<String> getAttributesList(final String tagName, final String attributeName) {
		final List<String> attrList = new ArrayList<String>();
		final List<WebElement> elementList = (List<WebElement>) this.driver.findElements(By.tagName(tagName));
		for (final WebElement e : elementList) {
			final String text = e.getAttribute(attributeName);
			attrList.add(text);
		}
		return attrList;
	}

	public void doClickFromList(final By locator, final String linkText) {
		final List<WebElement> footerList = this.getElements(locator);
		for (int i = 0; i < footerList.size(); ++i) {
			final String text = footerList.get(i).getText();
			if (text.equals(linkText)) {
				footerList.get(i).click();
				break;
			}
		}
	}

	public void doSelectDropDownByVisibleText(final By locator, final String text) {
		final Select select = new Select(this.getElement(locator));
		select.selectByVisibleText(text);
	}

	public void doSelectDropDownByIndex(final By locator, final int index) {
		final Select select = new Select(this.getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropDownByValue(final By locator, final String value) {
		final Select select = new Select(this.getElement(locator));
		select.selectByValue(value);
	}

	public void selectDropDownValueWithoutSelectClass(final By locator, final String value) {
		final List<WebElement> optionsList = this.getElements(locator);
		for (final WebElement e : optionsList) {
			final String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	public List<WebElement> visibilityOfAllElements(final By locator, final int timeOut) {
		final WebDriverWait wait = new WebDriverWait(this.driver, (long) timeOut);
		return (List<WebElement>) wait.until((Function) ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void getPageLinksText(final By locator, final int timeOut) {
		this.visibilityOfAllElements(locator, timeOut).stream().forEach(ele -> System.out.println(ele.getText()));
	}

	public int getPageLinksCount(final By locator, final int timeOut) {
		return this.visibilityOfAllElements(locator, timeOut).size();
	}

	public String waitForTitlePresent(final String titleValue, final int timeOut) {
		final WebDriverWait wait = new WebDriverWait(this.driver, (long) timeOut);
		wait.until((Function) ExpectedConditions.titleIs(titleValue));
		return this.driver.getTitle();
	}

	public String waitForTitlePresent(final String titleValue, final int timeOut, final int intervalTime) {
		final WebDriverWait wait = new WebDriverWait(this.driver, (long) timeOut, (long) intervalTime);
		wait.until((Function) ExpectedConditions.titleIs(titleValue));
		return this.driver.getTitle();
	}

	public WebElement waitForElementToBeLocated(final By locator, final int timeOut) {
		final WebDriverWait wait = new WebDriverWait(this.driver, (long) timeOut);
		return (WebElement) wait.until((Function) ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementWithFluentWait(final By locator, final int timeOut, final int pollingTime) {
		final Wait<WebDriver> wait = (Wait<WebDriver>) new FluentWait((Object) this.driver)
				.withTimeout(Duration.ofSeconds(timeOut)).pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring((Class) NoSuchElementException.class).ignoring((Class) StaleElementReferenceException.class);
		return (WebElement) wait.until((Function) ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement retryingElement(final By locator) {
		WebElement element = null;
		for (int attempts = 0; attempts < 30; ++attempts) {
			try {
				element = this.driver.findElement(locator);
				break;
			} catch (StaleElementReferenceException e) {
				try {
					Thread.sleep(500L);
				} catch (InterruptedException ex) {
				}
			} catch (NoSuchElementException e2) {
				try {
					Thread.sleep(500L);
				} catch (InterruptedException ex2) {
				}
				System.out.println("element is not found in attempt : " + (attempts + 1));
			}
		}
		return element;
	}
}
