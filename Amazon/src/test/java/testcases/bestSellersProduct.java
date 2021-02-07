package testcases;

import testscripts.Amazon_DriverScript;
import testscripts.Amazon_Library;

public class bestSellersProduct extends Amazon_DriverScript {
	
	public static void bestSellersItemToCart() throws InterruptedException {
		Amazon_Library.addToCart_BestSellers();
	}

}
