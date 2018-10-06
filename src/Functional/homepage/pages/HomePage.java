package Functional.homepage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Functional.homepage.pageobjects.HomePageObjects;
import ReusableLibrary.DriverScript;
import ReusableLibrary.Initialization;

public class HomePage extends DriverScript {

	// TODO - this class can be deleted and recreated from the Object View
ExtentTest Logger;
	public HomePage(WebDriver driver,ExtentTest Logger) {
		super(driver);
		this.logger=Logger;
//logger.log(LogStatus.INFO, "Home page loaded");
	//	reportNGInfo("INFO","Home Page Loaded");
		// TODO Auto-generated constructor stub
		
	}

	private WebElement getPageElement(HomePageObjects HomePageEnum) {
		try {
			return getElementByProperty(HomePageEnum.getProperty(),
					HomePageEnum.getLocatorType().toString());
			
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Element not found in DOM");
			return null;
		}
	}

	public WebElement getElementByProperty(String strObjectProperty,
			String strFindElementType) {

		try {
			if (strFindElementType.equalsIgnoreCase("CSS"))
				return driver.findElement(By.cssSelector(strObjectProperty));
			else if (strFindElementType.equalsIgnoreCase("XPATH"))
				return driver.findElement(By.xpath(strObjectProperty));
			else if (strFindElementType.equalsIgnoreCase("ID"))
				return driver.findElement(By.id(strObjectProperty));
			else if (strFindElementType.equalsIgnoreCase("NAME"))
				return driver.findElement(By.name(strObjectProperty));
			else if (strFindElementType.equalsIgnoreCase("LINKTEXT"))
				return driver.findElement(By.linkText(strObjectProperty));
			else if (strFindElementType.equalsIgnoreCase("TAG"))
				return driver.findElement(By.tagName(strObjectProperty));
			else if (strFindElementType.equalsIgnoreCase("CLASS"))
				return driver.findElement(By.className(strObjectProperty));
			else if (strFindElementType.equalsIgnoreCase("PARTIALLINKTEXT"))
				return driver
						.findElement(By.partialLinkText(strObjectProperty));
			else
				return null;
		} catch (org.openqa.selenium.NoSuchElementException nsee) {
			logger.log(LogStatus.FAIL, "Element not found");
			return null;
		}

		catch (Exception e) {
			logger.log(LogStatus.FAIL, "Element not found");
			return null;
			
		}
	}

	public void clickShopByCategoryInHomePage() {
		// System.out.println(HomePageObjects.valueOf(ShopBydepartmentElement));
		WebElement elment = getPageElement(HomePageObjects.lnkShopBy);
		if (elment.isDisplayed()) {
			elment.click();
			logger.log(LogStatus.PASS, "Link Shop by category found and clicked");
		} else {
			logger.log(LogStatus.FAIL, "Link Shop by category not found");
		}
		// getPageElement(HomePageObjects.valueOf(ShopBydepartmentElement)).click();
	}

	@Override
	protected void executeTestCase(ExtentTest log) {
		// TODO Auto-generated method stub

	}
}
