package PageObjects;
 
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import Utilities.ExcelUtils;
 
public class newsPage extends basepage{
	
	BeCognizant_POM bc = new BeCognizant_POM(driver);
 
	JavascriptExecutor js;
	WebDriverWait wait;
	TakesScreenshot tk;
	List<WebElement>  expectednews ;
	List<String> expectnews = new ArrayList<>();
	public Logger logger;
	public String file = System.getProperty("user.dir") + "\\TestData\\outputfile.xlsx";
 
	@FindBy(id="news_text_title") List<WebElement> newsheader;
	@FindBy(id="title_text") WebElement innerNewsheader;
	
	
 
	public newsPage(WebDriver driver)
	{
		
		 super(driver);
		
		 js = (JavascriptExecutor) driver;
		
		 logger = LogManager.getLogger(this.getClass());
		
		 expectednews = new ArrayList<>();
 
	}
	
	public void show() throws InterruptedException
	{
		 expectednews = bc.selectNews();
		 for (WebElement ele : expectednews) {
			 expectnews.add(ele.getText());
		 }
	}
 
	
  // Validate all the news are displayed in Be.Cognizant are displayed When user clicks on See All
	
	public void workOnNewsValidation() throws InterruptedException
	{	
 
		  WebElement scroll = driver.findElement(By.xpath("//div[contains(@class,'c_a_beed2cf1')]"));
		  js.executeScript("arguments[0].scrollTop+=1000", scroll);
	      Thread.sleep(5000);
	      js.executeScript("arguments[0].scrollTop+=1000", scroll);
	      Thread.sleep(5000);
		  List<WebElement> total_news = driver.findElements(By.id("news_text_title"));
		  for(int i = 0 ; i < total_news.size();i++)  {
			  
			   for(int j = 0 ; j < expectnews.size();j++) {
			            
			       if(newsheader.get(i).getText().equalsIgnoreCase(expectnews.get(j)) )
			       {
			    	
				         js.executeScript("arguments[0].scrollIntoView();",newsheader.get(i));
				         System.out.println("Expected news :  " + expectnews.get(j));
				         System.out.println("Actual news   :  "+newsheader.get(i).getText());
				         System.out.println("Validation Successfull");
				         System.out.println("--------------------------------------------------");
				         try
				         {
					           ExcelUtils.setCellData(file, "Be cognizant", j+1, 7,expectnews.get(j));
					           ExcelUtils.setCellData(file, "Be cognizant", j+1, 8,newsheader.get(i).getText());
					
		                 }catch(Exception e) {
			                    logger.error("Something went wrong"+e);
		                   }	
			
			        }
		        }
		  }
			  
	}
		
	  //Verification of News header and Tooltip for each News 

	public void workOnNewsHeader() throws InterruptedException, IOException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
 			   try {
				   for(int i = 0; i < 5; i++) {
				       List<WebElement> newsList = driver.findElements(By.xpath("//*[@id='news_text_title']"));
				       WebElement text = newsList.get(i);
				       js.executeScript("arguments[0].scrollIntoView();",text);
				       Thread.sleep(1000);
				       wait.until(ExpectedConditions.elementToBeClickable(text));
				       text.click();
				       Thread.sleep(5000); 
				       innerNewsheader = driver.findElement(By.xpath("//*[@id='title_text']"));
					   wait.until(ExpectedConditions.elementToBeClickable(innerNewsheader));
					   Assert.assertEquals(innerNewsheader.getText(), innerNewsheader.getAttribute("title"));
					   System.out.println("Inner Header and inner header tooltip validated");

		   //  Display the contents of the top 5 news

					   JavascriptExecutor js1=(JavascriptExecutor)driver;
				       WebElement content=driver.findElement(By.xpath("//*[contains(@class,'ck-content')]"));
					   js1.executeScript("arguments[0].scrollIntoView();",content);
					   bc.takeScreenshot((i+1) +" News Content");
					   Thread.sleep(2000);
					   System.out.println("News "+(i+1)+" : \n ");
					   System.out.println("   " +content.getText());
					   System.out.println("---------------------------------------------------");
					   System.out.println("\n");

					   try {
							ExcelUtils.setCellData(file, "News Contents", i+1, 0,innerNewsheader.getText());
							ExcelUtils.setCellData(file, "News Contents", i+1, 1,innerNewsheader.getAttribute("title"));
							ExcelUtils.setCellData(file, "News Contents", i+1, 2,content.getText());
							driver.navigate().back();
							Thread.sleep(5000);
						} catch (IOException e) {
						  e.printStackTrace();
						}
				   }

 			   }catch(Exception e) {
 				   logger.error("Something went wring"+e);
 				   }
 			   }
	}

 