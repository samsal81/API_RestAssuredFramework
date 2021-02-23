package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class getAllTest {

	@Test
	public void GetFunction() {

		
		Response response = 
				given()
					.baseUri("https://reqres.in/api/")
					.header("Content-Type", "application/json; charset=utf-8")
				.when()
					.log().all()
					.get("users")
				.then()
					.statusCode(200)
					.header("Content-Type", "application/json; charset=utf-8")
					.extract().response();
		
		@SuppressWarnings("rawtypes")
		ResponseBody respbody = response.body();
		String strresp = respbody.asString();
		
		//System.out.println(strresp);
		
		JsonPath jp = new JsonPath(strresp);
		int totalPages = jp.getInt("total_pages");
		int totalRecords = jp.getInt("total");
		int recordsPerPag = jp.getInt("per_page");
		int numOfPages = jp.getInt("total_pages");
		
		System.out.println("Total number of pages: " + totalPages);
		System.out.println("Total number of records: " + totalRecords);
		System.out.println("Records per page: " + recordsPerPag);
		System.out.println("Number of pages: " + numOfPages);
		
		int responseStat = response.getStatusCode();
		System.out.println("Response Status :" + responseStat);
		Assert.assertEquals(responseStat, 200);
		
		String responseHeader=response.getHeader("Content-Type");
		System.out.println(responseHeader);
		Assert.assertEquals(responseHeader, "application/json; charset=utf-8");
				
	}
}
