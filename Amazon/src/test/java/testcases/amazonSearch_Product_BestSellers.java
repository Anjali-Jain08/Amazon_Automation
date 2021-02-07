package testcases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import testscripts.Amazon_DriverScript;

public class amazonSearch_Product_BestSellers{
	    static WebDriver driver;
		
		public static void searchBestSellers() {
			System.out.println("entered search");
			WebDriverWait wait = new WebDriverWait(driver, 20);
			
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
			}}
			
			 public static void  clickElement(WebElement element){
			        JavascriptExecutor js =(JavascriptExecutor)driver;
			        js.executeScript("arguments[0].click();", element);
			        waitForPageLoad(driver);
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


}
