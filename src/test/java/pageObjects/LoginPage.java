package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(id="input-email")
	WebElement txtEmail;
	
	@FindBy(id="input-password")
	WebElement txtPwd;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginBtn;
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setPassword(String pwd)
	{
		txtPwd.sendKeys(pwd);
	}
	
	public void clickLoginBtn()
	{
		loginBtn.click();
	}

}
