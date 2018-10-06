package Functional.CartDomain.scripts;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;

import Functional.CartDomain.pages.CartPage;
import Functional.CartDomain.pages.DepartmentPage;
import Functional.CartDomain.pages.HomePage;

import ReusableLibrary.DriverScript;

public class Amazon001_SampleScript extends DriverScript {
	HomePage homepage;
	DepartmentPage departmentpage;
	CartPage cartpage;

	@Test
	public void runTest() {
		driveTestExecution();

	}

	@Override
	protected void executeTestCase(ExtentTest logger) {
		// TODO Auto-generated method stub
		homepage = new HomePage(driver, logger);
		homepage.clickShopByCategoryInHomePage();
		departmentpage = new DepartmentPage(driver, logger);
		departmentpage.clickKindleLinkInDepartmentPage();
		cartpage = new CartPage(driver, logger);
		cartpage.selectQuantity("2");
		cartpage.clickAddToCartBtn();
		cartpage.clickAddToOrder();
		

	}

}
