package testscripts;

//import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import testcases.amazonSearch_Product_BestSellers;

//import jxl.JXLException;

public class Amazon_Library extends Amazon_DriverScript {
	
	static WebDriver wbdv;
	//--------------------Locators for navigate and Login to the amazon account------------------------
	//public static By Start_Here = By.id("nav-link-accountList-nav-line-1");
	public static By signIn = By.id("nav-link-accountList-nav-line-1");
	public static By customerEmail = By.id("ap_email");
	public static By continue_step= By.id("continue");
	public static By customerPassword = By.id("ap_password");
	public static By submit = By.id("signInSubmit");
	//--------------------Locators for Amazon search product------------------------
	public static By enterSearchKeyword = By.id("twotabsearchtextbox");
	public static By clickOnSearch = By.id("nav-search-submit-button");
	public static By totalProducts = By.cssSelector(".s-result-item");
	public static By totalBestSellers = By.cssSelector(".s-image");
	public static By action_MoveToElement = By.xpath("(//span[@class='a-badge-text'and contains(text(),'Best seller')]/ancestor::div[@class='sg-row']/following-sibling::div");
	public static By addToCart = By.id("add-to-cart-button");
	
	
	
	
	
	public static void navigate() {
		System.out.println("reached to navigate function");

		
		if(wbdv==null) {

			if(Config.getProperty("browser").equalsIgnoreCase("Chrome")){
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"\\drivers\\chromeDrivers\\chromedriver.exe");
				wbdv = new ChromeDriver();
			}
			else if(Config.getProperty("browser").equalsIgnoreCase("FireFox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"\\drivers\\geckodriver\\geckodriver.exe");
				wbdv = new FirefoxDriver();
			}

		}

		driver = new EventFiringWebDriver(wbdv);
		driver.navigate().to(Config.getProperty("test_site_url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
	}
	
	public static void login(int data_rownum) {

		String Email =null;
		String Password = null;

		try {
			Email = testdata.getCellData("Login","Email", data_rownum);
			Password = testdata.getCellData("Login","Password", data_rownum);

		}catch (Exception retrieve){

			System.out.println(retrieve.getMessage());
			System.out.println(retrieve.getCause());
			retrieve.printStackTrace();
		}

		System.out.println("Login into the Amazon account");
		Amazon_FunctionLibrary.click(signIn);
		Amazon_FunctionLibrary.input(customerEmail, Email);
		Amazon_FunctionLibrary.click(continue_step);
		Amazon_FunctionLibrary.input(customerPassword, Password);
		Amazon_FunctionLibrary.click(submit);
		System.out.println("Login successful");
	}
	
	public static void search_keyword(int data_rownum) {
		String searchProduct = null;
		
		try {
			searchProduct = testdata.getCellData("SearchItems", "Product_Name", data_rownum);
		} catch (Exception retrieve){

			System.out.println(retrieve.getMessage());
			System.out.println(retrieve.getCause());
			retrieve.printStackTrace();
		}
		Amazon_FunctionLibrary.input(enterSearchKeyword, searchProduct);
		Amazon_FunctionLibrary.click(clickOnSearch);
	}
	
	public static void addBestSellers_ProductToCart() {
		amazonSearch_Product_BestSellers.searchBestSellers();
		
		    }
			
	
	public static void navigateAndLoginToAccount() throws InterruptedException {

		navigate();
		login(1);
		System.out.println("Register data entered");

	}
	
	public static void searchItemsOnAmazon() throws InterruptedException {
		navigateAndLoginToAccount();
		search_keyword(1);
		
		
	}
	
	public static void addToCart_BestSellers() throws InterruptedException {
		searchItemsOnAmazon();
		System.out.println("out of search");
		addBestSellers_ProductToCart();
		
		
	}

}
