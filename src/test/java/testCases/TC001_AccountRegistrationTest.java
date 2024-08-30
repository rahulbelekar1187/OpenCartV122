package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass { 
	
	@Test(groups={"Regression", "Master"})
	public void verify_account_registration() throws InterruptedException {
		
		logger.info("******Starting TC001_AccountRegistrationTest********");
		try {
		HomePage hp = new HomePage(driver);
		
		logger.info("Clicked on MyAccount Link");
		hp.clickMyAccount();
		
		logger.info("Clicked on Register Link");
		hp.clickRegister();
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details......");
		regpage.setFirstName(randomString());
		regpage.setLastName(randomString());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone(randomNumber());
		
		String pswd = randomAlphanumeric();
		regpage.setPassword(pswd);
		regpage.setConfirmPassword(pswd);
		regpage.setPrivacyPolicy();
		Thread.sleep(3000);
		regpage.clickContinue();
		
		logger.info("Validating expected msg......");
		String cnfmsg = regpage.getConfirmationMsg();
		Assert.assertEquals(cnfmsg, "Your Account Has Been Created!");
		}
		
		catch (Exception e) {
			logger.error("Test failed........");
			logger.debug("Debug logs....");
			Assert.fail();
		}
		
		logger.info("Finish TC001_AccountRegistrationTest.......");
	}
	
}
