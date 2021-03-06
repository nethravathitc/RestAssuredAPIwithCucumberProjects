package stepdefinitions;

import org.junit.runner.RunWith;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Login {
	@Given("^User is on landing page$")
	public void user_is_on_landing_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("Inside Givem ");
	}

	@When("^User logins using username and password as \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_logins_using_username_and_password_as_and(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("Inside When with value "+arg1 + " "+arg2);
	}

	@Then("^user is logged in$")
	public void user_is_logged_in() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("inside then");
	}

	@Then("^Home page is shown up with \"([^\"]*)\"$")
	public void home_page_is_shown_up_with(String arg1)  {
	    // Write code here that turns the phrase above into concrete actions
	   System.out.println("Inside Then with value"+arg1);
	}
}
