package runners;

import io.cucumber.testng.CucumberOptions;

/**
 * The TestRunner is a class which we can provide tags to execute and we can
 * call this test runner in TestnG.xml to achieve parallel executions.
 *
 * @author Swamy
 */

@CucumberOptions(tags = "@demo")

public class TestRunner extends BaseRunner {

}
