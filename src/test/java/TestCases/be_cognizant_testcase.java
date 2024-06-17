package TestCases;

import java.io.IOException;
import java.net.SocketException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageObjects.BeCognizant_POM;
import PageObjects.newsPage;
import Utilities.ExcelUtils;
import io.github.bonigarcia.wdm.WebDriverManager;


public class be_cognizant_testcase {
	
	static WebDriver driver;
	public Logger logger;
	BeCognizant_POM bm;
	newsPage np;
	
	@BeforeTest
	@Parameters({"browser","url"})
	public void setUp(String br, String appurl) throws InterruptedException, IOException 
	{
		
		logger = LogManager.getLogger(this.getClass());
		if(br.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		     
		}
		else if(br.equalsIgnoreCase("edge")) 
		{
			driver=new EdgeDriver();	
		}
		else {
			System.out.println("Invalid browser name");
		}
	
		  driver.manage().window().maximize();
		  driver.get(appurl);
		  Thread.sleep(5000);
		  bm=new BeCognizant_POM(driver);
		  np=new newsPage(driver);
		  ExcelUtils.createExcel();
		  
	}	  
 
		 
	@Test(priority=1)
	 public void testUser() throws InterruptedException, IOException {
		   
        	logger.info("**News Around cognizant test cases started **");
            bm.userInformation();
            bm.takeScreenshot("testUser");
            Thread.sleep(3000);
       
    }
	
	// R1:Capture the user information.
	
	@Test(priority=2)
	void testUserDetails() throws InterruptedException, IOException
	{
		    bm.takeScreenshot("testUserDetails");
	        bm.userDetails();
	        logger.info("User Information captured");
	             
		
	}
	
	//R2:Validate Around Cognizant News section is displayed in Be. Cognizant page.
	
	@Test(priority=3)
	void AroundCognizant() throws InterruptedException, IOException
	{
		
		        bm.aroundCognizant();	
                logger.info("Around cognizant is validated");
		        Thread.sleep(2000);		
		        bm.takeScreenshot("AroundCognizant");
		
	}
	
	//R3:Get all the News headers in Around Cognizant News Section and verify whether News header and Tool Tips of News are same.

	@Test(priority = 4)
	void workonheader() throws InterruptedException, IOException
	{
		bm.workOnHeader();
		logger.info("Header and tooltip validated");
		bm.takeScreenshot("HeaderandTooltip");
	}
	
	@Test(priority=5)
	void  newsSelect() throws InterruptedException{
		
		bm.selectNews();
		np.show();
	}
		
	//R4:Click on See All 
	
	@Test(priority=7)
	void seeAllNaviagtion() throws InterruptedException, IOException
	{

		
		bm.workOnseeAll();
		logger.info("See all is clicked");
		Thread.sleep(5000);
		bm.takeScreenshot("ClickSeeAll");
		
		
	}
	
	//R4:  Validate all the news are displayed in Be. Cognizant are displayed When user clicks on See All.
	
	@Test(priority = 8)
	
	void newsValidation() throws InterruptedException, IOException {
  
			    Thread.sleep(10000);
		        np.workOnNewsValidation();
				logger.info("Be cognizant news is validated");
				np.takeScreenshot("be_cognizantValidation");
	       		
	}
	

	//R5:Click on each News header. Verify the News header and Tooltip for each News.
	
	@Test(priority = 9 )
	void seeAllNewsHeader() throws IOException, InterruptedException 
	{
		//R6:Print all the details of the News.
		//R7:Repeat Step 5 and Step 6 for Top 5 News.
	
			   Thread.sleep(2000);
				np.workOnNewsHeader();
				np.takeScreenshot("seeAllNewsHeader");
				logger.info("First 5 news header details are displayed");
		 
		logger.info("** News around cognizant test cases completed ** ");
		
	}	

	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
		
	
}


	

