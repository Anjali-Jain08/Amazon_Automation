package testscripts;

import java.io.IOException;

import jxl.read.biff.BiffException;
import testcases.bestSellersProduct;
import testcases.navigateToAmazon;
import testcases.searchproducts;

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
