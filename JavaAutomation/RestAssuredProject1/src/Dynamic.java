import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Dynamic {
	
	@Test(dataProvider="BookData")
	public void addBook(String isbn,String aisle) {
		
		RestAssured.baseURI="http://216.10.245.166";
		
		String response=given().log().all().headers("Content-Tpe","application/json")
		.body(payload.book(isbn,aisle))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js= new JsonPath(response);
		System.out.println("ID : "+js.getString("ID"));
		
		
	
		
	}

	@DataProvider(name="BookData") 
	public Object[][] getData()
	{
		return new Object[][] {{"asd","1111"},{"qwwe","2222"},{"sdsd","4567"}};
	}
}
