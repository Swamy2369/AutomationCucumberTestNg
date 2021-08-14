package frameworkBase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * The DriverManager class initializes driver to the mentioned
 * browser, also We can implement mobile divers and API connections here
 *
 * @author Swamy
 */

public class DriverManager {

	public RemoteWebDriver driver;
	private int timeOut = 30;
	private String browserName = null;

	public RemoteWebDriver initiateDriver(String browser) {
		browserName = browser;
		switch (browserName) {
		case "chrome":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--no-sandbox");
			System.setProperty("webdriver.chrome.driver", "./src/test/java/library/Drivers/chromedriver.exe");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
			return driver;
		case "edge":
			System.setProperty("webdriver.edge.driver", "./src/test/java/library/Drivers/msedgedriver.exe");
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
			return driver;
		default:
			return null;

		}
	}

}