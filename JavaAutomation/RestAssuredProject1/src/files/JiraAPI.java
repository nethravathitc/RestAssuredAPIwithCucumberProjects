package files;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

public class JiraAPI {

	public static void main(String[] args) {
		
		RestAssured.baseURI="http://localhost:8080";
		
		//Login
		SessionFilter session = new SessionFilter();
		
		String loginResponse=given().log().all().header("Content-Type","application/json")
		.body("{ \"username\": \"nethravathi\", \"password\": \"p@ssw0rdTc\" }").filter(session)
		.when().post("/rest/auth/1/session")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(loginResponse);
		String name = js.getString("session.name");
		String value = js.getString("session.value");
		System.out.println("Name: " +name+" value: "+value);
		
		
		
		//Adding comment
		String body="Recent comment added by automation";
		String response=given().log().all().headers("Content-Type","application/json").pathParam("issueID", "10004")
		.body("{\r\n"
				+ "    \"body\": \""+body+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session)
		.when().post("/rest/api/2/issue/{issueID}/comment")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js1 = new JsonPath(response);
		int commentId = js1.getInt("id");
		System.out.println("The recent comment id : "+commentId);
		
		
		
		/*
		//Adding attachment
		given().log().all().pathParam("issueID","10004").header("X-Atlassian-Token"," no-check").header("Content-Type","multipart/form-data")
		.header("Cookie",name+"="+value)
		.multiPart("file",new File("JiraAttachement.txt"))
		.when().post("/rest/api/2/issue/{issueID}/attachments")
		.then().log().all().assertThat().statusCode(200);
		*/
		
		//get Issue
		String response1= given().log().all().filter(session).pathParam("issueID","10004").queryParam("fields","comment")
		.when().get("/rest/api/2/issue/{issueID}")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response1);
		JsonPath js2= new JsonPath(response1);
		int size=js2.getInt("fields.comment.comments.size()");
		System.out.println("Size is: "+size);
		String actualBodu="";
		for(int i=0;i<size;i++)
		{
			int id=js2.getInt("fields.comment.comments["+i+"].id");
			System.out.println("comment Id: "+js2.getInt("fields.comment.comments["+i+"].id"));
			
			if(id==commentId)
			{
				System.out.println("The comment Id is present");
			    actualBodu= js2.getString("fields.comment.comments["+i+"].body");
				System.out.println("Message :" +actualBodu);
				break;
				
			}
		}
		
		Assert.assertEquals(actualBodu, body);

	}

}
