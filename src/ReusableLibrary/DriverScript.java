package ReusableLibrary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public abstract class DriverScript {

	public WebDriver driver = null;
	public ExtentReports extent = null;
	public ExtentTest logger = null;

	public DriverScript(WebDriver driver) {
		this.driver = driver;

	}

	public DriverScript() {

	}

	private void setRelativePath() {
		String relativePath = new File(System.getProperty("user.dir"))
				.getAbsolutePath();

	}

	public void driveTestExecution() {
		driver = initializeWebDriver();
		logger = initializeTestReport();
		setUp();
		executeTestCase(logger);
		quitWebDriver();
		wrapUp();
	}

	private WebDriver initializeWebDriver() {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

	public ExtentTest initializeTestReport() {

		System.out.println(System.getProperty("user.dir"));

		Date date = new Date();

		long time = date.getTime();
		System.out.println("Time in Milliseconds: " + time);

		Timestamp ts = new Timestamp(time);
		System.out.println("Current Time Stamp: " + ts);
		String timestamp = ts.toString();

		extent = new ExtentReports(System.getProperty("user.dir")
				+ "/test-output/ExtentReport/ExtReport.html", true);
		logger = extent.startTest("Amazon Testcase");
		return logger;
	}

	public void setUp() {
		String url;
		try {
			url = getValueFromProperyFile("URL");
			driver.get(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(LogStatus.WARNING, e.toString());
		}
	}

	protected abstract void executeTestCase(ExtentTest log);

	public void quitWebDriver() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	public void wrapUp() {
		extent.endTest(logger);
		extent.flush();
		extent.close();

	}

	public String getValueFromProperyFile(String Input) {
		FileReader reader;
		String value = null;
		try {
			reader = new FileReader(System.getProperty("user.dir")
					+ "/PropertyFiles/CommonSettings.properties");
			Properties p = new Properties();
			p.load(reader);
			value = p.getProperty(Input);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(LogStatus.WARNING, "No such input in properties file");

		}
		return value;
	}

}
