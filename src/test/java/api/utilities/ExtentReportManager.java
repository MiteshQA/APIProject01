package api.utilities;

// Extent Report 5.x
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

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter; //This class use for report UI
	public ExtentReports extent; 	//This class use for project some data like environment detail, User Info, OS, Project name, Module name
	public ExtentTest test;		//This class use for creating a entry in report

	String repName;

	public void onStart(ITestContext testContext)		//This method will start only once when test start
	{
		String timeStamp  = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());  //time stamp

		repName="Test-Report-" +timeStamp+".html";

		sparkReporter=new ExtentSparkReporter(".\\Reports\\" +repName); //specify the location of the Report

		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // Title of the Report
		sparkReporter.config().setReportName("Pet Store Users API");	//Name of the report
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Pet Store Users API");
		extent.setSystemInfo("Operating System",System.getProperty("os.name"));
		extent.setSystemInfo("User Name",System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("User", "Mitesh");
	}

	public void onTestPass(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Pass");
	}

	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());

	}

	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext textContext)
	{
		extent.flush();
	}

}
