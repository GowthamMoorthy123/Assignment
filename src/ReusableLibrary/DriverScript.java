package ReusableLibrary;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public abstract class DriverScript {

	public WebDriver driver = null;
	public ExtentReports extent;
	public ExtentTest logger = null;

	public DriverScript(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		

	}

	public DriverScript() {

	}

	private void setRelativePath() {
		String relativePath = new File(System.getProperty("user.dir"))
				.getAbsolutePath();
		if (relativePath.contains("allocator")) {
			relativePath = new File(System.getProperty("user.dir")).getParent();
		}

	}

	public void driveTestExecution() {
		driver = initializeWebDriver();
		// initializeTestIterations();
		initializeTestReport();
		// if(testParameters.getCurrentTestcase().contains("_M"))
		// report.bMergedTC = true;

		setUp();
		executeTestCase();

		quitWebDriver();
		wrapUp();
	}

	private WebDriver initializeWebDriver() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Selenium\\drivers\\chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

	public void initializeTestReport() {

		System.out.println(System.getProperty("user.dir"));

		Date date = new Date();

		long time = date.getTime();
		System.out.println("Time in Milliseconds: " + time);

		Timestamp ts = new Timestamp(time);
		System.out.println("Current Time Stamp: " + ts);
		String timestamp = ts.toString();

		extent = new ExtentReports(System.getProperty("user.dir")
				+ "/test-output/ExtentReport/ExtReport.html",
				true);	
		
		/*extent.addSystemInfo("Host Name", "Amazon.CA");
		extent.loadConfig(new File(System.getProperty("user.dir")
				+ "\\extent-config.xml"));*/

	}

	public void setUp() {
		driver.get("https://www.amazon.ca/");
	}

	protected abstract void executeTestCase();

	public void quitWebDriver() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	public void wrapUp() {
		extent.endTest(logger);
		extent.flush();
		extent.close();

	}
	
	/**
	 * Reports out based on the given verification point parameters. Elements
	 * are processed to be formatted with the Report sequence.
	 * @param expected
	 * @param status
	 * @param msg
	 */
	public void reportNGInfo(String expected,String msg){
		
		logger.log(LogStatus.INFO, msg);
		
	}
}
