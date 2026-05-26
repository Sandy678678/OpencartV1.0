package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	@BeforeClass(groups= {"Smoke","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException, InterruptedException
	{
		FileInputStream file=new FileInputStream("./src\\test\\resources\\config.properties");
		p=new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		if (p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			switch(os)
			{
			case "windows":capabilities.setPlatform(Platform.WIN11);break;
			case "mac": capabilities.setPlatform(Platform.MAC);break;
			default: System.out.println("Invalid OS"); return;
			}
			
			switch(br)
			{
			case "chrome":capabilities.setBrowserName("chrome"); break;
			case "edge":capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox":capabilities.setBrowserName("firefox"); break;
			default: System.out.println("Invalid browser name");
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		switch(br.toLowerCase())
		{
		case "chrome": driver=new ChromeDriver();break;
		case "edge": driver=new EdgeDriver();break;
		case "firefox": driver=new FirefoxDriver();break;
		default: System.out.println("Invalid browser name");return;
		}
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appUrl"));
		
	}
	
	@AfterClass(groups= {"Smoke","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	//Methods for generating random Strings
	
	public String randomString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(10);
		return generatedString;
	}
	
	public String randomNumber()
	{
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public String alphaNumeric()
	{
		String generateString=RandomStringUtils.randomAlphabetic(4);
		String generateNumber=RandomStringUtils.randomNumeric(3);
		return(generateString+"@"+generateNumber);
	}
	
	public String captureScreen(String path) throws IOException
	{
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss");
		Date date=new Date();
		String timeStamp=df.format(date);
		TakesScreenshot ts=(TakesScreenshot)driver;
		File temp=ts.getScreenshotAs(OutputType.FILE);
		String dest= System.getProperty("user.dir")+"\\screenshots\\"+path+timeStamp+".jpg";
		File per=new File(dest);
		FileHandler.copy(temp, per);
		return dest;
		
	}
	
}
