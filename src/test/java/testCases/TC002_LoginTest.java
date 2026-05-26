package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"Regression","Master"})
	public void validateLogin()
	{
		logger.info("**** STARTING TC002_LoginTest *********** ");
		
		try {
	
		//Actions on Home page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Actions on Login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLoginBtn();
		
		//Validating presence of MyAccount page for successful login
		MyAccountPage map=new MyAccountPage(driver);
		boolean myAccountValidation=map.isMyAccountPageExists();
		if(myAccountValidation==true)
		{
			map.clickLogout();
			logger.info("TEST CASE PASSED");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("TEST CASE FAILED");
			Assert.assertTrue(false);
		}
		}catch(Exception e)
		{
			logger.error("TEST CASE FAILED: "+e.getMessage());
			Assert.fail();
		}
		finally
		{
			logger.info("**** FINISHED TC002_LoginTest ****");
		}
		
			
	}

}
