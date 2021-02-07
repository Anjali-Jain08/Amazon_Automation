package testcases;

import testscripts.Amazon_DriverScript;
import testscripts.Amazon_Library;

public class searchproducts extends Amazon_DriverScript {
	public static void enterSearchItem() throws InterruptedException {
		Amazon_Library.searchItemsOnAmazon();
	}

}
