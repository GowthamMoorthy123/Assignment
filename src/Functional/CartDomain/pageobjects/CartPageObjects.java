package Functional.CartDomain.pageobjects;

import static pageobjects.ObjectLocator.CLASS;
import static pageobjects.ObjectLocator.CSS;
import static pageobjects.ObjectLocator.ID;
import static pageobjects.ObjectLocator.LINKTEXT;
import static pageobjects.ObjectLocator.XPATH;
import pageobjects.ObjectLocator;

public enum CartPageObjects {
	dropDown("select[class='a-native-dropdown']", CSS),
	addToCartBtn("//input[@id='add-to-cart-button']",XPATH),
	proceedToCheckout("a[class='hucSprite s_checkout hlb-checkout-button']",CSS),
	lblEmailAddressAndPhoneNumber("//label[contains(text(),'E-mail address or mobile phone number')]",XPATH),
	popUpClose("//button[@data-action='a-popover-close']",XPATH),
	lnkAddToOrder("div[class='addToOrderButton']",CSS);
	String strProperty = "";
	ObjectLocator locatorType = null;

	public String getProperty() {
		return strProperty;
	}

	public ObjectLocator getLocatorType() {
		return locatorType;
	}

	private CartPageObjects(String strPropertyValue, ObjectLocator locatorType) {
		this.strProperty = strPropertyValue;
		this.locatorType = locatorType;
	}

}
