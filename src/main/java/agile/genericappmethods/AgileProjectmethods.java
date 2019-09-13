package agile.genericappmethods;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import agile.utilities.DataInputProvider;

import com.aventstack.extentreports.ExtentTest;

public class AgileProjectmethods extends GenericAppMethods {
	public ExtentTest testSuite;
	public String dataSheetName;
	
	
	@BeforeSuite
	public void beforeSuite(){
		startResult();
	}

	@BeforeClass
	public void beforeClass() throws FileNotFoundException, IOException{		
		startTestModule(testCaseName, testDescription);	
		//startApp();
	}
	
	@BeforeMethod
	public void beforeMethod() throws FileNotFoundException, IOException
	{
		test = startTestCase(nodes);
		test.assignCategory(category);
		test.assignAuthor(authors);
		//startApp();
	}
		
	public ExtentTest startTestCase(String testCaseName, String testDescription) {
		testSuite = extent.createTest(testCaseName, testDescription);		
		return testSuite;
	}
	public ExtentTest startTestModule(String nodes) {
		test = testSuite.createNode(nodes);
		return test;
	}
		
	@AfterMethod
	public void afterMethod() throws FileNotFoundException, IOException
	{
		//driver.close();
	}
	
	
	@AfterSuite
	public void afterSuite(){
		endResult();
	}

	@AfterTest
	public void afterTest(){
	}
	
	@DataProvider(name="fetchData")
	public Object[][] getData(){
		
		return DataInputProvider.getSheet(dataSheetName);	
			
	}
}
