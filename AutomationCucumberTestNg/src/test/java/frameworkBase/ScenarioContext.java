package frameworkBase;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.Scenario;

/**
 * The ScenarioContext class controls the driver's instance and we can use this
 * active driver instance across all the pages in the project. The context
 * values are active till the current scenario terminates.
 * 
 * @author Swamy
 */

public class ScenarioContext {

	private RemoteWebDriver driver;
	private DriverManager driverManager_Obj = new DriverManager();
	private Map<String, Object> contextMap;
	private Scenario scenario;
	private String browser;

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public void setContextMap(Map<String, Object> contextMap) {
		this.contextMap = contextMap;
	}

	public ScenarioContext() {
		setContextMap(new HashMap<>());
	}

	public Map<String, Object> getContextMap() {
		return contextMap;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	public Scenario getScenario() {
		return scenario;
	}

	public RemoteWebDriver getDriver() {
		if (driver == null) {
			driver = driverManager_Obj.initiateDriver(getBrowser());
		}
		return driver;
	}

	public void terminateDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

	public void takeScreenshot() {
		try {
			if (scenario.isFailed()) {
				File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
				scenario.attach(fileContent, "image/png", "screenshot");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
