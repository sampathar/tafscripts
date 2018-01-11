package com.wellsfargo.tests;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.rabit.testgen.util.RecorderUtil;

public class CreateNewTask {
private EventFiringWebDriver driver;
private RecorderUtil recorderObj= new RecorderUtil();
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
	        		  		
	    
	    		driver= recorderObj.Browser("wellsfargo.com",this.getClass().getSimpleName(),path);
	    		recorderObj.setDriver(driver);
	    		driver.manage().window().maximize();

	    	    baseUrl = "https://login.salesforce.com/";
	    	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    	  }
@Test
public void createTask() throws Exception {
	 try{
	   recorderObj.waitSeconds(5);
	   if(!isElementPresent(By.xpath("//span[text()='Tasks']"))) {
			driver.findElement(By.xpath("//button[text()='More']")).click();
		}
		driver.findElement(By.xpath("//span[text()='Tasks']")).click();
		driver.findElement(By.xpath("//span[text()='New Task']")).click();
		recorderObj.waitSeconds(3);
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		driver.findElement(By.xpath("//span[text()='Task Information']/following::span[text()[contains(.,'Subject')]]/following::input[1]")).sendKeys("Test");
		driver.findElement(By.xpath("//*[text()='Task Information']/following::span[text()[contains(.,'Due Date')]]/following::input[1]")).sendKeys("1/24/2018");
		
		driver.findElement(By.xpath("//span[text()='Task Information']/following::span[text()[contains(.,'Status')]]/following::a[1]")).click();
		driver.findElement(By.xpath("//a[@title='Completed']")).click();
		
		driver.findElement(By.xpath("//span[text()='Task Information']/following::span[text()[contains(.,'Priority')]]/following::a[1]")).click();
		driver.findElement(By.xpath("//a[@title='High']")).click();
		
		driver.findElement(By.xpath("//span[text()='Task Information']/following::span[text()[contains(.,'Related To')]]/following::a[1]")).click();
		driver.findElement(By.xpath("//a[@role='menuitem'][@title='Customers']")).click();
		recorderObj.waitSeconds(10);
		driver.findElement(By.xpath("//span[text()='Task Information']/following::span[text()[contains(.,'Comments')]]/following::textarea")).sendKeys("Test New Task");
		driver.findElement(By.xpath("//span[text()='Task Information']/following::button[@title='Save']")).click();
		recorderObj.waitSeconds(5);
		driver.findElement(By.xpath("//span[text()='Home']")).click();
		recorderObj.waitSeconds(5);
		if(!isElementPresent(By.xpath("//span[@title='My Tasks']"))) {
			driver.findElement(By.xpath("(//*[@title='Select a view of your tasks'])[1]")).click();
			driver.findElement(By.xpath("//a[@title='My Tasks']")).click();
		}
		
		if(!isElementPresent(By.xpath("//span[@title='My Tasks']/following::a[@class='rowLink'][1]/descendant::span[text()='Test']"))) {
			fail("Task Not Displayed in the home page.");
		}
		
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
if(driver!=null)driver.quit();
String verificationErrorString = verificationErrors.toString();
if (!"".equals(verificationErrorString)) {
fail(verificationErrorString);
}
}

private boolean isElementPresent(By by) {
try {
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.findElement(by);
driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
return true;
} catch (NoSuchElementException e) {
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	return false;
}
}



}





