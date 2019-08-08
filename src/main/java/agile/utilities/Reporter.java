package agile.utilities;

	import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

	import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

	public abstract class Reporter {

		public static ExtentHtmlReporter html;
		public static ExtentReports extent;
		public static ExtentTest test, suiteTest;
		public static ExtentTest svcTest;
		public String testCaseName, testNodes, testDescription, category, authors,nodes;

		public void startResult() {
			html = new ExtentHtmlReporter("./reports/Results.html");
			html.setAppendExisting(true);		
			extent = new ExtentReports();		
			extent.attachReporter(html);
		}

		public ExtentTest startTestModule(String testCaseName, String testDescription) {
			suiteTest = extent.createTest(testCaseName, testDescription);
			return suiteTest;
		}

		public ExtentTest startTestCase(String testNodes) {
			test = 	suiteTest.createNode(testNodes);
			return test;
		}

		public abstract long takeSnap();

		public void reportStep(String desc, String status, boolean bSnap)  {

			MediaEntityModelProvider img = null;
			if(bSnap && !status.equalsIgnoreCase("INFO")){

				long snapNumber = 100000L;
				snapNumber = takeSnap();
				try {
					img = MediaEntityBuilder.createScreenCaptureFromPath
							("./../reports/images/"+snapNumber+".jpg").build();
				} catch (IOException e) {				
				}
			}
			if(status.equalsIgnoreCase("PASS")) {
				test.pass(desc, img);			
			}else if (status.equalsIgnoreCase("FAIL")) {
				test.fail(desc, img);
			}else if (status.equalsIgnoreCase("WARNING")) {
				test.warning(desc, img);
			}else if (status.equalsIgnoreCase("INFO")) {
				test.info(desc);
			}						
		}

		public void reportStep(String desc, String status) {
			reportStep(desc, status, true);
		}

		public void endResult() {
			extent.flush();
		}	

		public static void reportRequest(String desc, String status) {
			
			MediaEntityModelProvider img = null;
			if(status.equalsIgnoreCase("PASS")) {
				svcTest.pass(desc, img);		
			}else if(status.equalsIgnoreCase("FAIL")) {
				svcTest.fail(desc, img);
				//throw new RuntimeException();
			}else if(status.equalsIgnoreCase("WARNING")) {
				svcTest.warning(desc, img);		
			}else {
				svcTest.info(desc);
			}	
		}		
	}
	
	//String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());  
	//html = new ExtentHtmlReporter("./Reports/report" + timeStamp + ".html");