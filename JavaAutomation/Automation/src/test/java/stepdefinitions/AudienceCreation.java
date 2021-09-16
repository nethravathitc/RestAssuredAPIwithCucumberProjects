package stepdefinitions;

import org.junit.runner.RunWith;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
public class AudienceCreation {

    @Given("^User is in Audience Dashboard$")
    public void user_is_in_audience_dashboard() throws Throwable {
       System.out.println("inside GIven");
    }

    @When("^Creates a simple condition using create option$")
    public void creates_a_simple_condition_using_create_option() throws Throwable {
    	System.out.println("inside When");
    }

    @Then("^Audience should be created$")
    public void audience_should_be_created() throws Throwable {
    	System.out.println("inside Then");
    }

    @And("^popup message should showup$")
    public void popup_message_should_showup() throws Throwable {
    	System.out.println("inside And");
    }

    @And("^Created audience should appear on dasboard$")
    public void created_audience_should_appear_on_dasboard() throws Throwable {
    	System.out.println("inside And");
    }

}