package runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * The BaseRunner is a class which will be called first when the execution
 * starts and we can also specify all the options of cucumber here
 *
 * @author Swamy
 */

@CucumberOptions(glue = { "stepDefinitions" }, features = { "classpath:features" }, monochrome = true, plugin = {
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, publish = true)

public class BaseRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
