package Functional.CartDomain.scripts;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;

import Functional.CartDomain.pages.CartPage;
import Functional.CartDomain.pages.DepartmentPage;
import Functional.CartDomain.pages.HomePage;

import ReusableLibrary.DriverScript;

@Listeners(Helper.listeners.class)
public class Amazon001_SampleScript extends DriverScript {
	HomePage homepage;
	DepartmentPage departmentpage;
	CartPage cartpage;
	@Parameters("browser")

	@Test
	public void runTest(String browser) {
		driveTestExecution(browser);

	}

	@Override
	protected void executeTestCase(ExtentTest logger) {
		homepage = new HomePage(driver, logger);
		homepage.clickShopByCategoryInHomePage();
		departmentpage = new DepartmentPage(driver, logger);
		departmentpage.clickKindleLinkInDepartmentPage();
		cartpage = new CartPage(driver, logger);
		cartpage.selectQuantity("2");
		cartpage.clickAddToCartBtn();
		//cartpage.cickExitPopUpFromSuggestedProducts();
		cartpage.clickAddToOrder();
		cartpage.clickProceedToCheckout();
		cartpage.verifySecureLoginPage();

	}

}
