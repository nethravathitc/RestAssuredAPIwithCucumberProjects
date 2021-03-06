package stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.addPlacePojo;
import resources.APIEndpoint;
import resources.Payload;
import resources.Utils;

public class PlaceValidation extends Utils {
	
	RequestSpecification req;
	ResponseSpecification res;
	String responseString;
	Response responseObj;
	Payload data= new Payload();

	@Given("^Payload of the the add place API with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void payload_of_the_the_add_place_API_with(String name, String address, String language) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		res= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		req=given().spec(requestSpecification()).body(data.addplace(name,address,language));
	   
	}

	@When("^Request is made with \"([^\"]*)\" and method \"([^\"]*)\"$")
	public void request_is_made_with_and_method(String resource, String method) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		APIEndpoint resources= APIEndpoint.valueOf(resource);
		
		if(method.equalsIgnoreCase("POST"))
			responseString=req.when().post(resources.getResource()).asString();
		else if(method.equalsIgnoreCase("GET"))
			responseString=req.when().get(resources.getResource()).asString();
		
		//System.out.println("Inside when response: "+responseString);
	}

	@Then("^Verify \"([^\"]*)\" in response body as \"([^\"]*)\"$")
	public void verify_in_response_body_as(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//JsonPath js= new JsonPath(responseString);
		//System.out.println("arg1 "+arg1+" arg2: "+arg2);
		
	String expected = JsonPathFunc(responseString, arg1);
		assertEquals(expected, arg2);
	    
	}
	
	@Then("^Verify place_id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_place_id_created_maps_to_using(String Expectedname, String resource) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
	    //Add place_id to the request
		String place_id = JsonPathFunc(responseString, "place_id");
		req=given().spec(requestSpecification()).queryParam("place_id", place_id);
		request_is_made_with_and_method(resource, "GET");
		String responseKeyName=JsonPathFunc(responseString, "name");
		assertEquals(Expectedname,responseKeyName);

		
	}
}
