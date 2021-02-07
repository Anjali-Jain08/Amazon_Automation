package testscripts;

import java.io.IOException;

public class Amazon_Keyword extends Amazon_DriverScript {
	
	public static void navigateToAmazonSite() throws InterruptedException,
    IOException, BiffException {

		navigateToAmazon.navigateAndLogin();
	}
	
	public static void  searchProductInAmazon() throws InterruptedException {
		   searchproducts.enterSearchItem();
	}
	
	public static void addBestSellersToCart() throws InterruptedException {
		
		bestSellersProduct.bestSellersItemToCart();
		
	}

}
