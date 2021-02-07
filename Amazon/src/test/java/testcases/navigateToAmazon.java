package testcases;

import testscripts.Amazon_DriverScript;
import testscripts.Amazon_Library;

public class navigateToAmazon extends Amazon_DriverScript {
	
    public static void navigateAndLogin() throws InterruptedException {
		
		System.out.println("Reached navigateAndRegister testcases");
		Amazon_Library.navigateAndLoginToAccount();
	}

}
