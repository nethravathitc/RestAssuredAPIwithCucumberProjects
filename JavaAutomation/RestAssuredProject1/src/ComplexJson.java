import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js = new JsonPath(payload.courses());
		
		// . Print No of courses returned by API
		
		int noOfCourses = js.getInt("courses.size()");
		System.out.println("No of courses : "+noOfCourses);
		
		
		//Print Purchase Amount
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase amount is  :" + purchaseAmount);

		//Print Title of the first course
		String Ftitle = js.getString("courses[0].title");
		System.out.println("First tile : "+ Ftitle);
	
		//Print All course titles and their respective Prices
		for(int i=0;i<noOfCourses;i++)
		{
			System.out.println("course "+(i+1)+" : "+ js.getString("courses["+i+"].title")+"\t"+js.getShort("courses["+i+"].price"));
		}
		
		//Print no of copies sold by RPA Course
		for(int i=0;i<noOfCourses;i++)
		{
			if(js.getString("courses["+i+"].title").contains("RPA"))
			{
				System.out.println("No of copies of the course "+ js.getString("courses["+i+"].title")+" is " +js.getShort("courses["+i+"].copies"));
				break;
			}
		}
		 //. Verify if Sum of all Course prices matches with Purchase Amount
		int sum=0;
		for(int i=0;i<noOfCourses;i++)
		{
			sum=sum+ (js.getInt("courses["+i+"].price")*js.getInt("courses["+i+"].copies"));
		}
		System.out.println(sum);
		Assert.assertEquals(js.getInt("dashboard.purchaseAmount"), sum);
	}
	

}
