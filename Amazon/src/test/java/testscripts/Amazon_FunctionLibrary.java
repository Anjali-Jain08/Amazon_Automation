package testscripts;


import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Amazon_FunctionLibrary extends Amazon_DriverScript {

	public static void highlightElement(WebDriver driver, By Locator) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1])", driver.findElement(Locator), "background-color: yellow; outline: 1px solid rgb(136, 255, 136)");
	}

	public static void click(By Locator) {

		highlightElement(driver, Locator);
		driver.findElement(Locator).click();
	}

	public static void moveToElement(By Locator) {
		WebElement target = driver.findElement(Locator);
		Actions actions = new Actions(driver);
		actions.moveToElement(waitforElementVisibility(target)).build().perform();
		

	}
	
	/*public static void moveToElement() {
		WebElement target = driver.findElement(Locator);
		Actions actions = new Actions(driver);
		actions.moveToElement(target);
	}*/

	public static void input(By Locator, String Value) {

		highlightElement(driver, Locator);
		driver.findElement(Locator).sendKeys(Value);
		System.out.println("Input : " + Value);
	}

	public static void selectByText(By Locator, String text) {

		//List<WebElement> option = driver.findElement(Locator).click();
		Select select = new Select(driver.findElement(Locator));
		select.deselectByVisibleText(text);
	}
	
	public static int totalCount_BestSellers(By Locator) {
		List<WebElement> totalCount = driver.findElements(Locator);
		return totalCount.size();
	}
	
	public static WebElement waitforElementVisibility(WebElement target) {
		WebDriverWait wait = new WebDriverWait(driver, 20); 
		return wait.until(ExpectedConditions.visibilityOfElementLocated((By) target));
	}
	
	public static void waitForElementToBeClickable(By Locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20); 
		wait.until(ExpectedConditions.elementToBeClickable(Locator)).click();
		
	}
	
	public static void switchWindows() {
		//String handle = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		
		for(String handle1 : handles) {
			driver.switchTo().window(handle1);
		}
	}
	
	public static void switchDefaultWindow() {
		String handle = driver.getWindowHandle();
		driver.switchTo().window(handle);
	}
	
	 public static void  clickElement(WebElement element){
	       // System.out.println("Clicking on "+elementName);
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



}
