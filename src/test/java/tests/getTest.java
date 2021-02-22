package tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class getTest {

	@Test
	public void GetFunction() {

		
		Response response = 
				given()
				.baseUri("https://reqres.in/api/")
				.header("Content-Type", "application/json; charset=utf-8")
				.when()
				.log().all()
				.get("users?page=2")
				.then()
				.log().all()
				.statusCode(200)
				.header("Content-Type", "application/json; charset=utf-8")
				.extract().response();
		
		String strresp = response.toString();
		
		System.out.println(strresp);
				
	}
}
