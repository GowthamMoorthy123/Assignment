package Functional.CartDomain.pageobjects;

import static pageobjects.ObjectLocator.CLASS;
import static pageobjects.ObjectLocator.CSS;
import static pageobjects.ObjectLocator.ID;
import static pageobjects.ObjectLocator.LINKTEXT;
import static pageobjects.ObjectLocator.XPATH;
import pageobjects.ObjectLocator;

public enum DepartmentPageObjects {
	lnkKindle("//a[text()='Kindle Paperwhite']", XPATH);
	String strProperty = "";
	ObjectLocator locatorType = null;

	public String getProperty() {
		return strProperty;
	}

	public ObjectLocator getLocatorType() {
		return locatorType;
	}

	private DepartmentPageObjects(String strPropertyValue, ObjectLocator locatorType) {
		this.strProperty = strPropertyValue;
		this.locatorType = locatorType;
	}

}
