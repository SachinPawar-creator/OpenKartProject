package com.qa.openkart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

public class JavaScriptUtil
{
    WebDriver driver;
    
    public JavaScriptUtil(final WebDriver driver) {
        this.driver = driver;
    }
    
    public void flash(final WebElement element) {
        final JavascriptExecutor js = (JavascriptExecutor)this.driver;
        final String bgcolor = element.getCssValue("backgroundColor");
        for (int i = 0; i < 20; ++i) {
            this.changeColor("rgb(0,200,0)", element);
            this.changeColor(bgcolor, element);
        }
    }
    
    private void changeColor(final String color, final WebElement element) {
        final JavascriptExecutor js = (JavascriptExecutor)this.driver;
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", new Object[] { element });
        try {
            Thread.sleep(20L);
        }
        catch (InterruptedException ex) {}
    }
    
    public void drawBorder(final WebElement element) {
        final JavascriptExecutor js = (JavascriptExecutor)this.driver;
        js.executeScript("arguments[0].style.border='3px solid red'", new Object[] { element });
    }
    
    public void generateAlert(final String message) {
        final JavascriptExecutor js = (JavascriptExecutor)this.driver;
        js.executeScript("alert('" + message + "')", new Object[0]);
    }
    
    public void clickElementByJS(final WebElement element) {
        final JavascriptExecutor js = (JavascriptExecutor)this.driver;
        js.executeScript("arguments[0].click();", new Object[] { element });
    }
    
    public void refreshBrowserByJS() {
        final JavascriptExecutor js = (JavascriptExecutor)this.driver;
        js.executeScript("history.go(0)", new Object[0]);
    }
    
    public String getTitleByJS() {
        final JavascriptExecutor js = (JavascriptExecutor)this.driver;
        final String title = js.executeScript("return document.title;", new Object[0]).toString();
        return title;
    }
    
    public String getPageInnerText() {
        final JavascriptExecutor js = (JavascriptExecutor)this.driver;
        final String pageText = js.executeScript("return document.documentElement.innerText;", new Object[0]).toString();
        return pageText;
    }
    
    public void scrollPageDown() {
        final JavascriptExecutor js = (JavascriptExecutor)this.driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)", new Object[0]);
    }
    
    public void scrollPageUp() {
        final JavascriptExecutor js = (JavascriptExecutor)this.driver;
        js.executeScript("window.scrollTo(document.body.scrollHeight,0)", new Object[0]);
    }
    
    public void scrollIntoView(final WebElement element) {
        final JavascriptExecutor js = (JavascriptExecutor)this.driver;
        js.executeScript("arguments[0].scrollIntoView(true);", new Object[] { element });
    }
    
    public String getBrowserInfo() {
        final JavascriptExecutor js = (JavascriptExecutor)this.driver;
        final String uAgent = js.executeScript("return navigator.userAgent;", new Object[0]).toString();
        return uAgent;
    }
    
    public void sendKeysUsingJSWithId(final String id, final String value) {
        final JavascriptExecutor js = (JavascriptExecutor)this.driver;
        js.executeScript("document.getElementById('" + id + "').value='" + value + "'", new Object[0]);
    }
    
    public void sendKeysUsingJSWithName(final String name, final String value) {
        final JavascriptExecutor js = (JavascriptExecutor)this.driver;
        js.executeScript("document.getElementByName('" + name + "').value='" + value + "'", new Object[0]);
    }
    
    public void checkPageIsReady() {
        final JavascriptExecutor js = (JavascriptExecutor)this.driver;
        if (js.executeScript("return document.readyState", new Object[0]).toString().equals("complete")) {
            System.out.println("Page Is loaded.");
            return;
        }
        for (int i = 0; i < 25; ++i) {
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
            if (js.executeScript("return document.readyState", new Object[0]).toString().equals("complete")) {
                break;
            }
        }
    }
}