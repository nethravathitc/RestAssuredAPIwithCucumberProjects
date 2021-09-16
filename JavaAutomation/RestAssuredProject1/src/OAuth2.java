import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import pojo.GetCourses;
import pojo.WebAutomation;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OAuth2 {
	
	@Test
	public void aAuthtest()
	{
		//using pojo class to parse the response
		RestAssured.baseURI="http://rahulshettyacademy.com";
		
		String[] courseTitle = {"Selenium Webdriver Java","Protractor","Cypress"};
		
		String access_token="ya29.a0ARrdaM8ZabbMOhFWNE0otz-cGgW7vnkkgzKx1M71yug25svpRWDWVswAlDF3jNAo6S-tmQzanzegR6iJiY2-nJWaxds6Z5t0aIJcMNoE0TztWFLXQgv-WGn4Ocy1ufQPDE66ocO069kd4NrDl3WWbpWUqyu5UA";
		
		GetCourses gc= given().queryParam("access_token",access_token).expect().defaultParser(Parser.JSON)
		.when().get("/getCourse.php").as(GetCourses.class);
		
		System.out.println("Instructor :"+(gc.getInstructor()).toString());
		String title="";
		for(int i=0;i<gc.getCourses().getApi().size();i++)
		{
		
			title=gc.getCourses().getApi().get(i).getCourseTitle();
			if(title.equalsIgnoreCase("Rest Assured Automation using Java"))
			{
				System.out.println("Price of the course "+title+" : "+ gc.getCourses().getApi().get(i).getPrice());
			}
			
			
		}
		
		List<WebAutomation> wa = gc.getCourses().getWebAutomation();
		ArrayList<String> actualcourses= new ArrayList<String>();
		for(int i=0;i<wa.size();i++)
		{
			System.out.println("Course "+(i+1)+": "+wa.get(i).getCourseTitle());
			actualcourses.add(wa.get(i).getCourseTitle());
		}
	
		// convert the array into List for easy of comparision
		List<String> expectTitle= Arrays.asList(courseTitle);
		Assert.assertTrue(expectTitle.containsAll(actualcourses));

	}

}
