package Functional.homepage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Functional.homepage.pageobjects.HomePageObjects;
import ReusableLibrary.Initialization;

public class HomePage extends Initialization {
	public HomePage() {
		
		
		//driver.

	}

	// TODO - this class can be deleted and recreated from the Object View

	public HomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		System.out.println(driver.getTitle());
		System.out.println(driver.getWindowHandle());
	}

	private WebElement getPageElement(HomePageObjects HomePageEnum) {
		try {
			return getElementByProperty(HomePageEnum.getProperty(),
					HomePageEnum.getLocatorType().toString());
		} catch (Exception e) {
			/*
			 * report.updateTestLog("Login Page - get page element",
			 * loginPageEnum.toString() + " object is not defined or found.",
			 * Status.FAIL);
			 */
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
			/*
			 * report.updateTestLog("Verify if " + strFindElementType + " - " +
			 * strObjectProperty + " is present", "Element with property " +
			 * strFindElementType + " - " + strObjectProperty + " not found",
			 * Status.FAIL);
			 */
			return null;
		}

		catch (Exception e) {
			/*
			 * report.updateTestLog("Verify if " + strFindElementType + " - " +
			 * strObjectProperty + " is present", "Element with property " +
			 * strFindElementType + " - " + strObjectProperty + " not found",
			 * Status.FAIL);
			 */
			return null;
		}
	}
	public void clickAnyElementInHomePage(String ShopBydepartmentElement)
	{
		//System.out.println(HomePageObjects.valueOf(ShopBydepartmentElement));
		WebElement elment =getPageElement(HomePageObjects.lnkShopBy);
		try {
			WebElement e1 = driver.findElement(By.xpath("//span[contains(text(),'Shop by')]"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.toString());
		}
		if(elment.isDisplayed())
		{
			elment.click();
		}
		else
		{
			System.out.println("Element not clickable");
		}
		//getPageElement(HomePageObjects.valueOf(ShopBydepartmentElement)).click();
	}
}
