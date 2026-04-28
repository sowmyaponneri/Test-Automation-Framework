package com.ui.pages;

import static com.constants.Env.QA;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import com.utility.BrowserUtility;
import com.utility.LoggerUtility;
import com.utility.PropertiesUtil;

public final class HomePage extends BrowserUtility{
	
	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),'Sign in')]");
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	public HomePage(Browser browserName,boolean isHeadless) {
		super(browserName,isHeadless);
		goToWebsite(PropertiesUtil.readProperty(QA, "URL"));
		//goToWebsite(JSONUtility.readJSON(QA).getUrl());
	}
	
	public HomePage(Browser browserName) {
		super(browserName);
		goToWebsite(PropertiesUtil.readProperty(QA, "URL"));
		//goToWebsite(JSONUtility.readJSON(QA).getUrl());
	}
	public HomePage(WebDriver driver) {
		super(driver);
	}
	public LoginPage goToLoginPage()
	{
		logger.info("Trying to perform click on sign in link to navigate to Login Page");
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage;
	}
	
	public void quit() {
		closeBrowserWindow();
	}
	

}
