package com.autorabit.tests;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.rabit.testgen.util.RecorderUtil;

public class BasicValidation {
	private EventFiringWebDriver driver;
	private RecorderUtil recorderObj= new RecorderUtil();
	private String[] testData = new String[10];
	private String baseUrl;
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
	public void testNavigatesetup() throws Exception {
		try{
			
			try {
				driver.findElement(By.xpath("//span[@id='userNavLabel']")).click();
				driver.findElement(By.xpath("//span[@id='userNavLabel']/following::a[text()='Setup']")).click();
			} catch (Exception e) {
				driver.findElement(By.xpath("//a[text()='Setup']")).click();
			} 
			recorderObj.waitSeconds(5);
			
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
}
