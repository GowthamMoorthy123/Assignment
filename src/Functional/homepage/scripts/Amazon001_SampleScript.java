package Functional.homepage.scripts;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Functional.homepage.pages.HomePage;
import Functional.homepage.pages.Sample;
import ReusableLibrary.DriverScript;


public class Amazon001_SampleScript extends DriverScript{
	HomePage homepage;
	Sample smple;
	
	@Test
	public void runTest(){
		driveTestExecution();
		
	}

	@Override
	protected void executeTestCase()  {
		// TODO Auto-generated method stub
		/*System.out.println("Gowtham");
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());*/
		 
		 homepage = new HomePage(driver,logger);
		 logger.log(LogStatus.INFO, "HomePage Loaded");
		 
		homepage.clickShopByCategoryInHomePage();
	
		
	}

	
}
