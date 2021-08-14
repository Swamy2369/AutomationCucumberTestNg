package stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.remote.RemoteWebDriver;

import frameworkBase.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * The Hooks class executes before and after scenarios and also it will add a
 * screenshot in the report for failure scenarios
 *
 * @author Swamy
 */

public class Hooks {

	private ScenarioContext scenarioContext_Obj;

	public Hooks(ScenarioContext scenarioContext) {
		this.scenarioContext_Obj = scenarioContext;
	}

	RemoteWebDriver driver = null;

	@Before
	public void setDriver(Scenario scenario) {
		scenarioContext_Obj.setBrowser(System.getProperty("browser"));
		scenarioContext_Obj.setScenario(scenario);
	}

	@AfterStep
	public void addScreenshotForScenario(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			scenarioContext_Obj.takeScreenshot();
		}
	}

	@After
	public void terminateDriverSession(Scenario scenario) {
		scenarioContext_Obj.terminateDriver();
	}

}