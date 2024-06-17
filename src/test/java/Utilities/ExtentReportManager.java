package Utilities;
import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import PageObjects.basepage;
 
	public class ExtentReportManager implements ITestListener
	{
		public ExtentSparkReporter sparkReporter;  // UI of the report
		public ExtentReports extent;  //populate common info on the report
		public ExtentTest test; // creating test case entries in the report and update status of the test methods
		String screenshot;

		public void onStart(ITestContext context) {
			sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/Reports/myReport.html");//specify location of the report
			sparkReporter.config().setDocumentTitle("Automation Report"); // TiTle of report
			sparkReporter.config().setReportName("Functional "); // name of the report
			sparkReporter.config().setTheme(Theme.DARK);
			extent=new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Computer Name","localhost");
			extent.setSystemInfo("Environment","QEA");
			extent.setSystemInfo("Application","Be Cognizant");
			extent.setSystemInfo("Tester Name","S.V.Mirudhula");
			extent.setSystemInfo("Os","Windows11");
			extent.setSystemInfo("Browser name","Chrome,Edge");
		}
 
		public void onTestSuccess(ITestResult result) {
			test = extent.createTest(result.getName()); // create a new entry in the report
			test.log(Status.PASS, "Test case PASSED is:" + result.getName()); // update status p/f/s
			try {
				 screenshot = basepage.takeScreenshotReport(result.getName());
				 test.pass("Screenshot for Test Success", MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
 
		public void onTestFailure(ITestResult result) {
			test = extent.createTest(result.getName());
			test.log(Status.FAIL, "Test case FAILED is:" + result.getName());
			test.log(Status.FAIL, "Test Case FAILED cause is: " + result.getThrowable()); 
			try {
				 screenshot = basepage.takeScreenshotReport(result.getName());
				 test.fail("Screenshot for Test Failure", MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
 
		public void onTestSkipped(ITestResult result) {
			test = extent.createTest(result.getName());
			test.log(Status.SKIP, "Test case SKIPPED is:" + result.getName());
			test.log(Status.SKIP, "Test Case SKIPPED cause is: " + result.getThrowable()); 
		}

		public void onFinish(ITestContext context) {
			extent.flush();
		}
	}
 
 