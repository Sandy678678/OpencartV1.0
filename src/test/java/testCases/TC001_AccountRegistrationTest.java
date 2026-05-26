package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	
	@Test(groups= {"Smoke","Master"})
	public void verifyAccountRegistration()
	{
		logger.info("Starting TC001_AccountRegistrationTest... ");
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount link");
		hp.clickRegister();
		logger.info("Clicked on Register link");
		
		AccountRegistrationPage regPage=new AccountRegistrationPage(driver);
		logger.info("Entering customer details in Registration page..");
		regPage.setFirstName(randomString().toUpperCase());
		regPage.setLastName(randomString().toUpperCase());
		regPage.setEmail(randomString()+"@gmail.com");
		regPage.setPhoneNo(randomNumber());
		
		String pwd=alphaNumeric();
		regPage.setPassword(pwd);
		regPage.setConfirmPassword(pwd);
		regPage.clickCheckPolicy();
		regPage.clickContinueBtn();
		
		logger.info("Validating expected message..");
		String confirmationMsg = regPage.getConfirmationMsg();
		if (confirmationMsg.equals("Your Account Has Been Created!"))
		{
			logger.info("Test Passed");
			Assert.assertTrue(true);
			
		}
		else
		{
			logger.info("Test Failed");
			Assert.assertTrue(false);
			
		}
		}
		catch(Exception e)
		{
			logger.error("Test Failed: "+e.getMessage());
			Assert.fail("Test Failed: "+e.getMessage());
		}
		
		finally
		{
			logger.info("Finished TC001_AccountRegistrationTest");
		}
	}
	
	

}
