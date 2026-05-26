package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="loginData",dataProviderClass=utilities.DataProviders.class, groups="DataDriven")
	public void loginDataDriven(String email, String password, String expected)
	{
		//logger.info("**** TC003_LoginDDT *********** ");
		
		try {
	
		//Actions on Home page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Actions on Login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLoginBtn();
		
		//Validating presence of MyAccount page for successful login
		MyAccountPage map=new MyAccountPage(driver);
		boolean myAccountValidation=map.isMyAccountPageExists();
		
		//Test Scenarios
		//1. Valid credentials --> Login successful --> PASSED
		//2. Valid credentials --> Login unsuccessful --> FAILED
		//3. Invalid credentials --> Login successful --> FAILED
		//4. Invalid credentials --> Login unsuccessful --> PASSED
		if(expected.equalsIgnoreCase("Valid"))
		{
			if (myAccountValidation==true)
			{
				map.clickLogout();
				logger.info("TEST CASE PASSED");
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("TEST CASE FAILED");
				Assert.fail();
			}
			
		}
		else
		{
			if (myAccountValidation==false)
			{
				logger.info("TEST CASE PASSED");
				Assert.assertTrue(true);
			}
			else
			{
				map.clickLogout();
				logger.error("TEST CASE FAILED");
				Assert.fail();
			}
		}
		}
		catch(Exception e)
		{
			logger.error("TEST CASE FAILED: "+e.getMessage());
			Assert.fail();
		}
		finally
		{
			logger.info("**** FINISHED TC003_LoginDDT ");
		}
		
		}
		
	
}
