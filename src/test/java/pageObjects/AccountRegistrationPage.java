package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	WebDriver driver;
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(id="input-firstname")
	WebElement txtFirstName;
	
	@FindBy(id="input-lastname")
	WebElement txtLastName;
	
	@FindBy(id="input-email")
	WebElement txtEmail;
	
	@FindBy(id="input-telephone")
	WebElement txtPhoneNo;
	
	@FindBy(id="input-password")
	WebElement txtPassword;
	
	@FindBy(id="input-confirm")
	WebElement txtPasswordConfirm;
	
	@FindBy(xpath="//input[@name='agree']") 
	WebElement chkdPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	WebElement msgAccountCreationSuccessful;
	
	public void  setFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setPhoneNo(String pnum)
	{
		txtPhoneNo.sendKeys(pnum);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd)
	{
		txtPasswordConfirm.sendKeys(pwd);
	}
	
	public void clickCheckPolicy()
	{
		chkdPolicy.click();
	}
	
	public void clickContinueBtn()
	{
		btnContinue.click();
	}
	
	public String getConfirmationMsg()
	{
	try
	{
		return msgAccountCreationSuccessful.getText();
	}
	catch(Exception e)
	{
		return (e.getMessage());
	}
	}
	
	}


