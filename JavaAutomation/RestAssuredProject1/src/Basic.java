import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.nio.file.Files;
import java.nio.file.Path;

import org.testng.Assert;

import files.payload;

public class Basic {

	//given, when, then
	public static void main(String[] args) throws Exception {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		//Add place - post API
		String response=given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
		//.body(payload.AddPlace()).when().post("maps/api/place/add/json")
				.body(new String (Files.readAllBytes(Path.of("C:\\Users\\ntc\\Documents\\Learning\\RestAssuredAPI\\AddPlaceJsonFIle.txt")))).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String PlaceId= js.getString("place_id"); // write the json path of the key whose value needs to be extracted.
		
		System.out.println(PlaceId);
		
		//update place - put API
		String address="Vasundra krishnashreya, Benagluru";
		given().log().all().header("Content-Type","application/json").queryParam("key", "qaclick123")
		.body("{\r\n"
				+ "\"place_id\":\""+PlaceId+"\",\r\n"
				+ "\"address\":\""+address+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		//get API
		given().log().all().queryParam("key", "qaclick123").queryParam("place_id",PlaceId )
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).body("address", equalTo(address));
		
		// Assert.assertEquals(actual, expected); // extract the key from bosy and assert using testNG

	}

}
