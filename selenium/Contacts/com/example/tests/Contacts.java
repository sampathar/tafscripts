package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import com.rabit.testgen.util.RecorderUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.firefox.FirefoxProfile;
import java.io.File;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Contacts {
private EventFiringWebDriver driver;
  private RecorderUtil recorderObj= new RecorderUtil();
  private String title;
private String assertionData;
private String[]  testData = new String [20];

  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
String path= this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath() + File.separator;
	String package1 = this.getClass().getPackage().getName();
	String [] parts= package1.split("\\.");
	for (int i = 0; i < parts.length; i++) {
	String part = parts[i];
	if(parts.length == i) {
	path = path + part;
	} else {
	path = path + part + File.separator;
	}
	}
	driver= recorderObj.Browser("autorabit.com-7sfUvY","AcValidation",path) ;
	recorderObj.setDriver(driver);
	driver.manage().window().maximize();

    baseUrl = "https://ariflightning-dev-ed.my.salesforce.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testAcValidation() throws Exception {

	 try{
	Actions action = new Actions(driver);
    driver.findElement(By.xpath("//form[contains(@id,'form')]/input[2]")).click();
	    driver.findElement(By.xpath("//li[@id='Account_Tab']/a")).click();
	
	    driver.findElement(By.xpath("//form[@id='hotlist']/table/tbody/tr/td[2]/input")).click();
	
	    driver.findElement(By.xpath("//td[@id='topButtonRow']/input")).click();
	
assertionData= driver.findElement(By.xpath("//div[@id='ep']/div[2]/div[3]/table/tbody/tr[2]/td[2]/div/div[2]")).getText();
	recorderObj.logMessage("Assertion: Expected: Error: You must enter a value ---  Actual: "+assertionData);
	    assertEquals("Error: You must enter a value for Account Info", assertionData);
  }

	catch (AssertionError e){
	recorderObj.screenshot(e);
	if(e.getMessage().contains("\n")){
		fail(e.getMessage().substring(0, e.getMessage().indexOf("\n")));
		}
		else fail(e.getMessage());
	}
	catch (Exception e){
	recorderObj.screenshot(e);
	if(e.getMessage().contains("\n")){
		fail(e.getMessage().substring(0, e.getMessage().indexOf("\n")));
		}
		else fail(e.getMessage());
	}
}
  @After
  public void tearDown() throws Exception {
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
