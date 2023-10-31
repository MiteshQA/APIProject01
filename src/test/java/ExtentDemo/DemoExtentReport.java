package ExtentDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoExtentReport {

	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("./Reports/spark.html");

	WebDriver driver;

	@BeforeTest
	public void BrowserLaunch()
	{
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("My Report");
		extent.attachReporter(spark);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get("https://www.google.com");



	}

	@Test
	public void Testcase_001()
	{
		ExtentTest test = extent.createTest("Verify the pagetitle").assignAuthor("Mitesh").
				assignCategory("Functional test cases").assignDevice("Windows");
		test.info("I am capturing the page title");
		String pagetitle = driver.getTitle();
		test.info("Captured page title as: " +pagetitle);
		if(pagetitle.equals("google"))
		{
			test.pass("Page title is verified: title captured" + pagetitle);
		}
		else
		{
			test.fail("Page title is not matched with expected results: "+pagetitle);
		}
	}

	@AfterTest
	public void teardown()
	{
		extent.flush();
		driver.quit();
	}


}
