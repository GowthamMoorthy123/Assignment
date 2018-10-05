package ReusableLibrary;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class DriverScript extends Initialization {

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
		initializeWebDriver();
		// initializeTestIterations();
		initializeTestReport();
		// if(testParameters.getCurrentTestcase().contains("_M"))
		// report.bMergedTC = true;

		setUp();
		executeTestCase();

		quitWebDriver();
		wrapUp();
	}

	private void initializeWebDriver() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Selenium\\drivers\\chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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
