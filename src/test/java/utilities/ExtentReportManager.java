package utilities; 

import java.awt.Desktop;

// Listener class used to generate extent reports 

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import org.apache.tools.ant.types.resources.selectors.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager extends TestListenerAdapter {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test; 
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
	/*	SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date();
		String currentdatetimestamp=df.format(dt);  */
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp 
		
		repName="Test-Report-"+timestamp+".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);  // specify location
				
		sparkReporter.config().setDocumentTitle("Opencart Automation report"); // title of report
		sparkReporter.config().setReportName("Opencart Functional Testing"); //name of the report
		//sparkReporter.config().setTestViewChartLocation(ChartLocation.TOP); // location of the chart
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "admin");
		extent.setSystemInfo("submodule", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	} 
	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName()); // create new entry in th report
		test.assignCategory(result.getMethod().getGroups());   // to display groups in report
		test.log(Status.PASS, result.getName()+"got successfully executed");
	}
	
	public void ontestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName()); // create new entry in th report
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+ "got failed"); // send the passed information 
		test.log(Status.INFO, result.getThrowable().getMessage());
		
			try {
				String imgPath = new BaseClass().captureScreen(result.getName());
				test.addScreenCaptureFromPath(imgPath);
			}
			
			catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	
	public void onTestSkipped(ITestResult result) {
		
		test=extent.createTest(result.getName()); // create new entry in th report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+ "got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
