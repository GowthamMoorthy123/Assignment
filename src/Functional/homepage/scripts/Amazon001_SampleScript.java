package Functional.homepage.scripts;

import org.testng.annotations.Test;

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
		System.out.println("Gowtham");
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		smple = new Sample();
		 homepage = new HomePage(driver);
		 
		homepage.clickAnyElementInHomePage("lnkShopBy");
	
		
	}

	
}
