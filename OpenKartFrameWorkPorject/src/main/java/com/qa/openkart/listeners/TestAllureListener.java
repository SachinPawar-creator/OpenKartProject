package com.qa.openkart.listeners;

import org.testng.ITestContext;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.qa.openkart.base.BasePage;

import org.testng.ITestListener;


public class TestAllureListener extends BasePage implements ITestListener
{
    private static String getTestMethodName(final ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(final WebDriver driver) {
        return (byte[])((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
    
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(final String message) {
        return message;
    }
    
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(final String html) {
        return html;
    }
    
    public void onStart(final ITestContext iTestContext) {
        System.out.println("I am in onStart method " + iTestContext.getName());
    }
    
    public void onFinish(final ITestContext iTestContext) {
        System.out.println("I am in onFinish method " + iTestContext.getName());
    }
    
    public void onTestStart(final ITestResult iTestResult) {
        System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
    }
    
    public void onTestSuccess(final ITestResult iTestResult) {
        System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
    }
    
    public void onTestFailure(final ITestResult iTestResult) {
        System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
        final Object testClass = iTestResult.getInstance();
        if (getDriver() instanceof WebDriver) {
            System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
            this.saveScreenshotPNG(getDriver());
        }
        saveTextLog(String.valueOf(getTestMethodName(iTestResult)) + " failed and screenshot taken!");
    }
    
    public void onTestSkipped(final ITestResult iTestResult) {
        System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
    }
    
    public void onTestFailedButWithinSuccessPercentage(final ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}




