package ReusableLibrary;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class DriverScript  {
	
	public WebDriver driver = null;

	public DriverScript(WebDriver driver) {
		this.driver=driver;

	}
	public DriverScript()
	{
		
	}

	private void setRelativePath() {
		String relativePath = new File(System.getProperty("user.dir"))
				.getAbsolutePath();
		if (relativePath.contains("allocator")) {
			relativePath = new File(System.getProperty("user.dir")).getParent();
		}

	}

	public void driveTestExecution() {
		driver =initializeWebDriver();
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

	private void initializeTestReport() {

	}

	public void setUp() {
		driver.get("https://www.amazon.ca/");
	}

	protected abstract void executeTestCase();

	private void quitWebDriver() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	private void wrapUp() {

	}
}
