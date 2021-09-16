package cucumber.options;

import org.junit.runner.RunWith;


import io.cucumber.junit.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/feature", glue= {"stepdefinitions"})
public class TestRunner {

}
