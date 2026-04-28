package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserUtility {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);
	}
	
	public BrowserUtility(String browserName) {
		logger.info("Launching browser for "+browserName);
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());//loose coupling
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());//loose coupling
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
		}
		else {
			logger.error("Invalid Browser Name..Please select chrome,firefox or edge browser only..");
			System.out.println("Invalid Browser Name..Please select chrome,firefox or edge browser only..");
		}
	}
	
	public BrowserUtility(Browser browserName) {
		logger.info("Launching browser for "+browserName);
		if(browserName == Browser.CHROME) {
			
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());//loose coupling
		}
		else if(browserName == Browser.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());//loose coupling
		}
		else if(browserName == Browser.EDGE) {
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
		}
	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("Launching browser for "+browserName);
		if(browserName == Browser.CHROME) {
			
			if(isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=old");
				options.addArguments("--window-size=1920,1080");
				WebDriverManager.chromedriver().setup();
				driver.set(new ChromeDriver(options));
			}
			else {
				WebDriverManager.chromedriver().setup();
				driver.set(new ChromeDriver());//loose coupling
			}
		}
		else if(browserName == Browser.FIREFOX) {
			if(isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=old");
				WebDriverManager.firefoxdriver().setup();
				driver.set(new FirefoxDriver(options));
			}
			else {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());//loose coupling
			}
		}
		else if(browserName == Browser.EDGE) {
			if(isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old");
				options.addArguments("disable-gpu");
				WebDriverManager.edgedriver().setup();
				driver.set(new EdgeDriver(options));
			}
			else {
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
			}
		}
	}
	
	public void closeBrowserWindow()
	{
		driver.get().quit();
	}

	public void maximizeWindow()
	{
		logger.info("Maximizing the browser window");
		driver.get().manage().window().maximize();
	}
	
	public void goToWebsite(String url)
	{
		logger.info("Visiting the website "+url);
		driver.get().get(url);
	}
	
	public void clickOn(By locator) {
		logger.info("Finding the element with the locator "+locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now performing Click");
		element.click();
	}
	
	public void enterText(By locator, String textToEnter)
	{
		logger.info("Finding the element with the locator "+locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now enter text "+textToEnter);
		element.sendKeys(textToEnter);
	}
	
	public String getVisibleText(By locator) {
		logger.info("Finding the element with the locator "+locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now returning the visible text "+element.getText());
		return element.getText();
	}
	
	public String takeScreenShot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		File screensohtData=screenshot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);
		String path = "./screenshots/"+name+" - "+ timeStamp +".png";
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screensohtData, screenshotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
		
	}
}
