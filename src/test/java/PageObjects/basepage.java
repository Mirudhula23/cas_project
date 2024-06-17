package PageObjects;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;

public class basepage {

	// only for constructor
	
	    static WebDriver driver;
		static TakesScreenshot tk;
		//Constructor
		  public basepage (WebDriver driver) {
		  basepage.driver= driver;
		  PageFactory.initElements(driver, this);
		  this.tk = (TakesScreenshot)driver; 
		  
		}
	  

	public void takeScreenshot(String name) throws IOException {
		 
		  File source= tk.getScreenshotAs(OutputType.FILE);
		  File target = new File("C:\\Users\\2317292\\Downloads\\cas_project 1\\cas_project\\Screenshots\\"+name+".png");
		  FileHandler.copy(source, target);
		  
		}
	
	public static String takeScreenshotReport(String name) throws IOException {
		
		  File source= tk.getScreenshotAs(OutputType.FILE);
		  File target = new File("C:\\Users\\2317292\\Downloads\\cas_project 1\\cas_project\\Screenshots\\"+name+".png");
		  FileHandler.copy(source, target);
		  
		  return target+"";
		  
		}
	

	}	  
	


	



