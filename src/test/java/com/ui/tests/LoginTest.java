package com.ui.tests;


import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pojo.User;
import com.utility.LoggerUtility;




@Listeners(com.ui.listeners.TestListener.class)
public class LoginTest extends TestBase{
	
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	@Test(description = "Verify that the user login with valid credentials", groups = {"e2e","sanity"},
			dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider",
			retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
	public void loginTest(User user) {
		assertEquals(homepage.goToLoginPage().doLoginWith(user.getEmailAddress(),user.getPassword()).getUserName(), "Sowmya ram");
	}
	
	@Test(description = "Verify that the user login with valid credentials", groups = {"e2e","sanity"},
			dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider")
	public void loginCSVTest(User user) {
		
		assertEquals(homepage.goToLoginPage().doLoginWith(user.getEmailAddress(),user.getPassword()).getUserName(), "Sowmya ram");
		
	}
	/*@Test(description = "Verify that the user login with valid credentials", groups = {"e2e","sanity"},
			dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestExcelDataProvider")
	public void loginExcelTest(User user) {
		assertEquals(homepage.goToLoginPage().doLoginWith(user.getEmailAddress(),user.getPassword()).getUserName(), "Sowmya ram");
	}*/
}
