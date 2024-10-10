package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features", plugin="json:target/jsonReports/cucmber-report.json", glue= {"stepDefinitions"})
public class TestRunner {
//tags ="@AddPlace" mvn compile, test, verify to execute tcs in command prompt 
}
