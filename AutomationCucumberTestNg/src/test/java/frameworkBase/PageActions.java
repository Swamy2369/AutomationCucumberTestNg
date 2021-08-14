package frameworkBase;

import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The PageActions class handles all the reusable wrapper methods. All the page
 * class should extends the PageActions class to implement the action methods
 *
 * @author Swamy
 */

public class PageActions {

	protected RemoteWebDriver driver;

	public PageActions(ScenarioContext scenarioContext_Obj) {
		driver = scenarioContext_Obj.getDriver();
	}

	private int waitTime = 60;

	public void launchApplication(String applicationURL) {
		driver.get(applicationURL);
	}

	public void clickElement(WebElement element) {
		Wait<WebDriver> wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}

	public void clickElement(By byElement) {
		WebElement element = driver.findElement(byElement);
		Wait<WebDriver> wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}

	public void clickElementByJS(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void sendKeysValue(WebElement element, String value) {
		Wait<WebDriver> wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(value);
	}

	public boolean isElementSelected(WebElement element) {
		return element.isSelected();
	}

	public String getText(WebElement element) {
		Wait<WebDriver> wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}

	public String getAttribute(WebElement element, String attributeName) {
		Wait<WebDriver> wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getAttribute(attributeName);
	}

	public void waitForPageToLoad() {
		Wait<WebDriver> wait = new WebDriverWait(driver, waitTime);
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}

	public void pressKeys(String key) {
		Actions act = new Actions(driver);
		act.sendKeys(key).build().perform();
	}

	public boolean isElementVisible(WebElement element) {
		return element.isDisplayed();
	}

	public boolean isElementVisible(By byElement) {
		return driver.findElement(byElement).isDisplayed();
	}

}