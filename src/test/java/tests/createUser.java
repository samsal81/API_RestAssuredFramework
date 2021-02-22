package tests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class createUser {

	@SuppressWarnings("unchecked")
	@Test
	public void CreateUsr() {

		@SuppressWarnings("rawtypes")
		HashMap payloadBody = new HashMap();
		payloadBody.put("name", "Osama Al-Saleh");
		payloadBody.put("job", "QA Automation Eng");

		Response response = 
				given()
					.baseUri("https://reqres.in/api/")
					.body(payloadBody)
					.header("Content-Type", "application/json; charset=utf-8")
				.when()
					.post("users")
				.then()
					.statusCode(201)
					.header("Content-Type", "application/json; charset=utf-8")
					.extract().response();

		int responseStat = response.getStatusCode();
		System.out.println("Response Status :" + responseStat);
		Assert.assertEquals(responseStat, 201);
		
		String responseHeader=response.getHeader("Content-Type");
		System.out.println(responseHeader);
		Assert.assertEquals(responseHeader, "application/json; charset=utf-8");
		
		String ResponseBody=response.getBody().asString();
		System.out.println(ResponseBody);
		
		JsonPath js= new JsonPath(ResponseBody);
		int ID = js.getInt("id");
		String Name = js.get("name"); 
		String Job = js.get("job"); 
		  
		System.out.println("Id: " + ID);
		System.out.println("Name: " + Name);
		System.out.println("Job: " + Job);
		
		Assert.assertEquals(Name, "Osama Al-Saleh");
		Assert.assertEquals(Job, "QA Automation Eng");
	}
}
