package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.path.json.JsonPath;

public class Utils {
	
	RequestSpecification req=null;
	public RequestSpecification requestSpecification() throws IOException
	{
		String url=globalVariable("baseURL"); //"https://rahulshettyacademy.com/"; //;
		System.out.println("inside utility "+url);
		
		if(req==null);
		{
		PrintStream log = new PrintStream(new FileOutputStream("log.txt"));
		req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON)
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
		}
		return req;
	}

	public static String globalVariable(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fs = new FileInputStream("C:\\Users\\ntc\\Documents\\Learning\\RestAssuredAPI\\JavaAutomation\\Automation\\src\\test\\java\\resources\\global.properties");
		prop.load(fs);
		System.out.println(prop.getProperty(key));
		return prop.getProperty(key);
		
		
	}
	
	public String JsonPathFunc(String res, String key)
	{
		JsonPath js = new JsonPath((res.toString()));
		//System.out.println("Response String :" +res.toString());
		return js.get(key);
		
		
	}
}
