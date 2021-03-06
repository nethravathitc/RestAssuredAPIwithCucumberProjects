import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.addPlacePojo;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class AddPlace {
	
	// request body using Pojo classes
	@Test
	public void addPlae()
	{
		addPlacePojo p= new addPlacePojo();
		Location l= new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		p.setLocation(l);
		p.setAccuracy(50);
		p.setName("Belli Nilaya");
		p.setPhone_number("123456789");
		p.setAddress("Nagarabhavi 2nd stage");
		
		List<String> t= new ArrayList<>();
		t.add("shoe park\"");
		t.add("shop");
		p.setTypes(t);
		
		p.setWebsite("http://wwww.dfdd.com");
		p.setLanguage("English");
		
		/*
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
		.body(p)
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200);
		
		*/
		// with specBuilder
		
		RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON)
				.addQueryParam("key", "qaclick123").build();
		
		ResponseSpecification res= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
	String responseString=given().spec(req).body(p).when().post("maps/api/place/add/json").then().log().all().spec(res).extract().asString();
		
	}

}
