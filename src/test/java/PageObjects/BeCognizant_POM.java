package PageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
 
import Utilities.ExcelUtils;
 
 
public class BeCognizant_POM extends basepage{
	JavascriptExecutor js;
	WebDriverWait wait;
	String news;
	public String file = System.getProperty("user.dir") + "\\TestData\\outputfile.xlsx";
	public Logger logger;
 

	//constructor of the superclass
	public BeCognizant_POM(WebDriver driver) {
		super(driver);
		js=(JavascriptExecutor)driver;
		logger = LogManager.getLogger(this.getClass());

	}	

	//WebElements locators

		@FindBy(id="O365_MainLink_MePhoto") WebElement userinfo;
		@FindBy(id="mectrl_headerPicture") WebElement userclick;
		@FindBy(id="mectrl_currentAccount_primary") WebElement txt_username;
		@FindBy(id="mectrl_currentAccount_secondary") WebElement txt_email;
		@FindBy(xpath="//div[@id='6a300658-3c93-45bc-8746-5964a4379bbf']") WebElement newspage;
		@FindBy(xpath="//a[@data-automation-id='newsItemTitle']")List<WebElement> header;
		@FindBy(linkText="See all") WebElement seeall;
		@FindBy(xpath="//span[text()=\"News\"]") WebElement seeAllValidation;


	  //Action Methods 

	  // To click on the user information

		public void userInformation() throws InterruptedException {
			System.out.println(driver);
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(50));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("O365_MainLink_MePhoto")));
			Thread.sleep(5000);
			userinfo.click();
			}

	  // To get the user information

		public void userDetails() throws InterruptedException {
			System.out.println("Printing the User Information \n");
			System.out.println(txt_username.getText());
			System.out.println(txt_email.getText());
			System.out.println("-----------------------------------------------");
			Thread.sleep(2000);
			userclick.click();

			try {
				ExcelUtils.setCellData(file, "Be cognizant", 1, 0,txt_username.getText());
				ExcelUtils.setCellData(file, "Be cognizant", 1, 1,txt_email.getText());
				
			} catch(Exception e) {

				logger.error("Something went wring"+e);

			}		

		}

	//	Validate Around Cognizant News section is displayed in Be. Cognizant page.	

		public void aroundCognizant() {
			WebElement element=driver.findElement(By.xpath("//*[@id='5d7d4eec-cbe0-4c55-ae2e-f38d926d82a0']/div/div/div"));
			js.executeScript("arguments[0].scrollIntoView();",element);
			Assert.assertEquals(newspage.isDisplayed(),true);
			System.out.println(" Around Cognizant is displayed - Validation success");	
			System.out.println("----------------------------------------------------");

		}

    // Get all the News headers in Around Cognizant News Section and verify whether News header and Tool Tips of News are same

		public void workOnHeader() throws InterruptedException {
			System.out.println("All headers are displayed below : ");
			System.out.print("\n");

			for(WebElement ele: header) {
				System.out.println( "  * " +ele.getText());
				String tooltip=ele.getAttribute("title");
				Actions act = new Actions(driver);
				act.moveToElement(ele).perform();
				Thread.sleep(1000);
				Assert.assertEquals(ele.getText(),tooltip );
			}	

			Thread.sleep(3000);
			System.out.println("\n All news Header and Tooltip are same");

			try {
				int length = header.size();
				for(int i=0;i<length;i++) {
					String tooltip=header.get(i).getAttribute("title");
					try {
						ExcelUtils.setCellData(file, "Be cognizant", i+1, 2,header.get(i).getText());
						ExcelUtils.setCellData(file, "Be cognizant", i+1, 3,header.get(i).getAttribute("title"));
						ExcelUtils.setCellData(file, "Be cognizant", i+1, 4,(header.get(i).getText().equals(tooltip)?"EQUAL":"NOT EQUAL"));
						ExcelUtils.setCellData(file, "Be cognizant", i+1, 5,"PASS");
						ExcelUtils.setCellData(file, "Be cognizant", i+1, 6,(header.get(i).getText().equals(tooltip)?"PASS":"FAIL") );

					} catch (IOException e) {
					  e.printStackTrace();
					}
				}

			}catch(Exception e) {
				logger.error("Something went wring"+e);
			}
		}

	// Take the news in Be.Cognizant to validate

		public List<WebElement> selectNews() throws InterruptedException {
		    
			List<WebElement> ele = driver.findElements(By.xpath("//a[@aria-label='Organizational news site Be.Cognizant']/following::a[1]"));
            
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(50));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@aria-label='Organizational news site Be.Cognizant']/following::a[1]")));
	        return ele ;
	        }

	//	Click on See All 

		public void workOnseeAll() throws InterruptedException
		{
			WebElement See=driver.findElement(By.xpath("//a[@data-automation-id='newsSeeAllLink']"));
			js.executeScript("arguments[0].scrollIntoView();",See);
			seeall.click();
			Thread.sleep(3000);
			System.out.println("--------------------------------------------------");
			System.out.println("See All is Clicked");
		}
	
}
