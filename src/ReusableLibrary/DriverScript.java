package ReusableLibrary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.mongodb.diagnostics.logging.Logger;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public abstract class DriverScript {

	
	public WebDriver driver = null;
	public ExtentReports extent = null;
	public ExtentTest logger = null;
	String reportFileName;

	public DriverScript(WebDriver driver) {
		this.driver = driver;

	}

	public DriverScript() {

	}

	public void driveTestExecution(String browser) {
		driver = initializeWebDriver(browser);
		logger = initializeTestReport();
		setUp();
		executeTestCase(logger);		
		closingLogger();
		quitWebDriver();
	}

	private WebDriver initializeWebDriver(String browser) {
		String RunningMode = null;

		RunningMode = getValueFromProperyFile("ExecutionMode");

		switch (RunningMode) {
		case "Local":

			//driver = getDriver(getValueFromProperyFile("Browser"));
			driver = getDriver(browser);
			/*
			 * System.setProperty("webdriver.chrome.driver",
			 * System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
			 * driver = new ChromeDriver();
			 */
			driver.manage().window().maximize();

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;
		case "Grid":
			if (getValueFromProperyFile("SauceLabsExecution").equalsIgnoreCase(
					"true")) {
				String remoteURL = getValueFromProperyFile("SauceLabURL");
				DesiredCapabilities capability = getCapabilities(getValueFromProperyFile("Browser"));
				try {
					driver = new RemoteWebDriver(new URL(remoteURL), capability);
				} catch (MalformedURLException e) {
					System.out.println(e.toString());
					logger.log(LogStatus.ERROR, e.toString());
				}
			} else {
				String RemoteURL = "http://(NodeIpAddress:portNumber/wd/hub";
				DesiredCapabilities capability = getCapabilities(getValueFromProperyFile("Browser"));
				try {
					driver = new RemoteWebDriver(new URL(RemoteURL), capability);
				} catch (MalformedURLException e) {
					System.out.println(e.toString());
					logger.log(LogStatus.ERROR, e.toString());
				}
			}

			break;
		default:
			logger.log(LogStatus.ERROR, "Execution Mode Not selected properly");
		}

		return driver;

	}

	public ExtentTest initializeTestReport() {

		System.out.println(System.getProperty("user.dir"));

		Date date = new Date();

		long time = date.getTime();
		System.out.println("Time in Milliseconds: " + time);

		Timestamp ts = new Timestamp(time);
		System.out.println("Current Time Stamp: " + ts);
		String timestamp = ts.toString().replaceAll("[^0-9]","_");

		extent = new ExtentReports(System.getProperty("user.dir")
				+ "/test-output/ExtentReport/ExtReport"+timestamp+".html", true);
		reportFileName=System.getProperty("user.dir")
				+ "/test-output/ExtentReport/ExtReport"+timestamp+".html"; 
		logger = extent.startTest("Amazon Testcase");
		
		return logger;
	}

	public void setUp() {
		String url;
		try {
			url = getValueFromProperyFile("URL");
			driver.get(url);
		} catch (Exception e) {

			logger.log(LogStatus.WARNING, e.toString());

		}
	}

	protected abstract void executeTestCase(ExtentTest log);

	public void quitWebDriver() {
		driver.manage().deleteAllCookies();
		driver.get(reportFileName);
		driver.quit();
	}

	public void closingLogger() {
		extent.endTest(logger);
		extent.flush();
		extent.close();

	}

	public String getValueFromProperyFile(String Input) {
		FileReader reader;
		String value = null;
		try {
			reader = new FileReader(System.getProperty("user.dir")
					+ "/CommonSettings.properties");
			Properties p = new Properties();
			p.load(reader);
			value = p.getProperty(Input);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log(LogStatus.WARNING, "No such input in properties file");

		}
		return value;
	}

	public DesiredCapabilities getCapabilities(String browser) {
		DesiredCapabilities capabilities = null;
		switch (browser) {
		case "CHROME":
			capabilities = DesiredCapabilities.chrome();
			capabilities.setBrowserName("chrome");
			break;

		case "FIREFOX":
			capabilities = DesiredCapabilities.firefox();
			capabilities.setBrowserName("firefox");
			break;

		case "IE":
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability("browserName", "internet explorer");
			break;
		default:
			logger.log(LogStatus.ERROR, "Select correct Browser");

		}
		return capabilities;
	}

	public WebDriver getDriver(String browser) {

		switch (browser) {
		case "Chrome":
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir")
							+ "/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "IE":
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir")
							+ "/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;

		case "Firefox":
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;

		default:
			System.err.println("Currently an unsupported browser [" + browser
					+ "] for driver execution.");
		}
		return driver;
	}

}
