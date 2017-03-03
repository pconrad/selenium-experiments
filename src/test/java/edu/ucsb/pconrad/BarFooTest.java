package edu.ucsb.pconrad;

/**
 * Created by pconrad on 3/2/17.
 */


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BarFooTest{


    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");

        driver = new FirefoxDriver();
        baseUrl = "https://my.sa.ucsb.edu";
        driver.get(baseUrl + "/public/curriculum/coursesearch.aspx");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testJavaJUnit4WebdriverYES() throws Exception {
        // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
        new Select(driver.findElement(By.id("ctl00_pageContent_courseList"))).selectByVisibleText("Computer Science - CMPSC");
        Thread.sleep(4000);

        new Select(driver.findElement(By.id("ctl00_pageContent_quarterList"))).selectByVisibleText("SPRING 2017");
        Thread.sleep(4000);

        driver.findElement(By.id("ctl00_pageContent_searchButton")).click();
        System.out.println("YES");
        Thread.sleep(4000);
        System.out.println("Page title is: " + driver.getTitle());
        Thread.sleep(4000);

    }

    @After
    public void tearDown() throws Exception {
        Thread.sleep(4000);

        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
