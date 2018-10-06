package Functional.homepage.scripts;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Functional.homepage.pages.HomePage;

import ReusableLibrary.DriverScript;

public class Amazon001_SampleScript extends DriverScript {
	HomePage homepage;

	@Test
	public void runTest() {
		driveTestExecution();

	}

	@Override
	protected void executeTestCase() {
		// TODO Auto-generated method stub
		homepage = new HomePage(driver);
		// logger.log(LogStatus.INFO, "HomePage Loaded");

		homepage.clickShopByCategoryInHomePage();

	}

}
