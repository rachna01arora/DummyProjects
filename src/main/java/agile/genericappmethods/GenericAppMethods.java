package agile.genericappmethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import agile.utilities.Reporter;

public class GenericAppMethods extends Reporter implements WebDriverEventListener{
	public static RemoteWebDriver driver;
	public String Surl;
	public String SDriverpath;
	public String SUserID;
	public String SPassword;
	public Properties prop;
	
	public long takeSnap() {
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		try {
			FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE),
					new File("./Reports/images/" + number + ".jpg"));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return number;
	}
	
	public GenericAppMethods() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./src/main/resources/config.properties")));
			Surl = prop.getProperty("url");
			SDriverpath = prop.getProperty("Driverpath");
			//SUserID = prop.getProperty("UserID");
			//SPassword = prop.getProperty("Password");
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startapp(String property, String UserID, String Password){
		try {
		System.setProperty(property,SDriverpath );
		driver=new ChromeDriver();
		driver.get(Surl);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		reportStep("The application launched successfully", "PASS");
}       catch (WebDriverException e) {
	     reportStep("The application launched successfully", "FAIL");
}
		driver.findElementByXPath("//td[@align='right'][text()='UserID']/following::td/input[@type='text']").sendKeys(UserID);
		driver.findElementByXPath("//td[@align='right'][text()='Password']/following::td/input[@type='password']").sendKeys(Password);
	    driver.findElementByXPath("//input[@type='submit' and @name='btnLogin' and @value='LOGIN']").click();
}
	
	public void closeapp(){
		driver.close();
	}
	
	public void compareText(String Expected, String Actual){
		if(Expected.contentEquals(Actual)){
			reportStep("Expected value is " +Expected +"Actual value is"+ Actual +"are same" , "PASS");
		}
		else{
			reportStep("Expected value is " +Expected +"Actual value is"+ Actual +"are different" , "FAIL");
		}
	}
	
	public void checkTextDisplay(WebElement ele){
		explicitWait(ele);
		Boolean actual=ele.isDisplayed();
		if (actual){
			reportStep("text is displaying" , "PASS");
		}
		else{
			reportStep("text is not displaying" , "PASS");
		}
	}
	
	public void click(WebElement ele){
		explicitWait(ele);
		ele.click();
	}
	
	public void actionClick(WebElement ele){
		explicitWait(ele);
		Actions action=new Actions(driver);
		action.moveToElement(ele).click().perform();	
	}
	
	public void explicitWait(WebElement ele){
		WebDriverWait wait1 = new WebDriverWait(driver, 20);       
        wait1.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver,
			CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver,
			CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		
	}

	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void onException(Throwable throwable, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	
   
}
