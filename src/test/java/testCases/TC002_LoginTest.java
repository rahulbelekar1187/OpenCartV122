package testCases;

import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	@Test(groups={"sanity", "Master"})
	public void verify_login() throws InterruptedException {
		
		try {
		//Home page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login page
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		Thread.sleep(2000);
		lp.clickLogin();
		Thread.sleep(2000);
		
		//MyAccount page
		MyAccountPage map = new MyAccountPage(driver);
		boolean targetpage = map.isMyAccountPageExist();
		
		//Assert.assertEquals(targetpage, true);
		Assert.assertTrue(targetpage);
		}
		catch(Exception e) {
			Assert.fail();
		}
	}

}
