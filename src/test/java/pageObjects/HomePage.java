package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage 
{	
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//span[text()='My Account']")
	WebElement link_myAccount;
	
	@FindBy(linkText="Register")
	WebElement link_Register;
	
	@FindBy(linkText="Login")
	WebElement link_Login;
	
	public void clickMyAccount()
	{
		link_myAccount.click();
	}
	
	public void clickRegister()
	{
		link_Register.click();
	}
	
	public void clickLogin()
	{
		link_Login.click();
	}
}
