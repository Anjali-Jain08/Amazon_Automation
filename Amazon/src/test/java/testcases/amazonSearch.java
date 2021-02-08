package testcases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
// Executing this file alone is working as expected and doing all the actions perfectly but using the same code in hybrid framework is slowing down the process
// You can execute this class only to see the performed action on Amazon site.

public class amazonSearch {

	static WebDriver driver;

	public void amazonLogin() {
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.findElement(By.id("nav-link-accountList-nav-line-1")).click();
		driver.findElement(By.id("ap_email")).sendKeys("anjalijainbksc@gmail.com");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("ap_password")).sendKeys("anjali02");
		driver.findElement(By.id("signInSubmit")).click();

	}

	public void searchBestSellers() {
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Headphones");
		driver.findElement(By.id("nav-search-submit-button")).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);

		//Actions action = new Actions(driver);

		List<WebElement> products = driver.findElements(By.cssSelector(".s-result-item"));
		List<WebElement> bestSellersContainer = new ArrayList<WebElement>();

		for(WebElement e: products){
			if (e.findElements(By.cssSelector("div>div>div>a>span[data-component-props*=\"best-seller\"]")).size()!=0){
				bestSellersContainer.add(e);
			}
		}

		String handle = driver.getWindowHandle();

		for (WebElement e : bestSellersContainer){
			WebElement ele = e.findElement(By.cssSelector(".s-image"));
			clickElement(ele);
			Set<String> handles = driver.getWindowHandles();

			for(String handle1 : handles) {
				driver.switchTo().window(handle1);
			}

			wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button"))).click();
			System.out.println("add to cart got clicked");
			driver.switchTo().window(handle);
		}
	}

	public static void  clickElement(WebElement element){
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
		//System.out.println("Clicked on "+elementName);
		waitForPageLoad(driver);
		//return 0;
	}

	public static void waitForPageLoad(WebDriver driver1) {
		ExpectedCondition<Boolean> pageLoadCondition = new
				ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
		};

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "\\drivers\\chromedriver_win32-88\\chromedriver.exe");
		driver = new ChromeDriver(); 

		amazonSearch login = new amazonSearch();
		login.amazonLogin();
		login.searchBestSellers();
	}

}
