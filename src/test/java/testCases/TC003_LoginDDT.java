package testCases;

import org.junit.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;
//import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups={"Datadriven", "Master"})
	public void verify_LoginDDT(String email, String pwd, String exp) throws InterruptedException {
		
		logger.info("********** Starting TC003_LoginDDT********* ");
		
		try {
		//Home page
				HomePage hp = new HomePage(driver);
				hp.clickMyAccount();
				hp.clickLogin();
				
				//Login page
				LoginPage lp = new LoginPage(driver);
				lp.setEmail(email);
				lp.setPassword(pwd);
				Thread.sleep(2000);
				lp.clickLogin();
				Thread.sleep(2000);
				
				//MyAccount page
				MyAccountPage map = new MyAccountPage(driver);
				boolean targetpage = map.isMyAccountPageExist();
				
				if(exp.equalsIgnoreCase("Valid")) {
					if(targetpage==true) {
						map.clickLogout();
						Assert.assertTrue(true);
					}
					else {
						Assert.assertTrue(false);
					}
				}
				
				if(exp.equalsIgnoreCase("Invalid")) {
					if(targetpage==true) {
						map.clickLogout();
						Assert.assertTrue(false);
					}
					else {
						Assert.assertTrue(true);
					}
				}
		} 
		catch(Exception e) {
			Assert.fail();
		}
				logger.info("********** Finish TC003_LoginDDT********* ");
				
	}

}
