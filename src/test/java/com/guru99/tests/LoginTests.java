package com.guru99.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests extends BaseTests{
	
	@Parameters({"username","password"})
	@Test
	public void verifyUserLoginWithCorrectCredentials(String username, String password) {
		
		reportUtils.createATestCase("Verify Credentials");
		
		reportUtils.addTestLog(Status.INFO, "Performing log.");
		
		loginpage.loginToApp(username, password);
		String expTitle= "Guru99 Bank Manager Homepage";
		String actTitle = commonDriver.getTitleOfPage();
		
		reportUtils.addTestLog(Status.INFO, "Assert.");
		Assert.assertEquals(actTitle, expTitle);
	}
}
