package utilities;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentReports extent;
	public ExtentTest test;
	
	
	public void onStart(ITestContext context)
	{
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date date=new Date();
		String timeStamp=df.format(date);
		String repname="Test-Report-"+timeStamp+".html";
		ExtentSparkReporter sparkReporter=new ExtentSparkReporter(".\\reports\\"+ repname);
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");
		sparkReporter.config().setReportName("Opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User",System.getProperty("user.name"));
		extent.setSystemInfo("Enviroment","QA");
		
		String os=context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser=context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName()); //to display Test name
		test.assignCategory(result.getMethod().getGroups()); //to display groups in report
		test.log(Status.PASS, result.getName()+" got successfully executed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName()); //to display Test name
		test.assignCategory(result.getMethod().getGroups()); //to display groups in report
		test.log(Status.FAIL, result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			BaseClass bc=new BaseClass();
			test.addScreenCaptureFromPath(bc.captureScreen(result.getName()));
			
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}

}
