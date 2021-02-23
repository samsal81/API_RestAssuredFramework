package tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class notFoundUser {

	@Test
	public void UnfoundUser() {
		Response response = 
				given()
					.baseUri("https://reqres.in/api/")
					.header("Content-Type", "application/json; charset=utf-8")
					.queryParam("id", "100")
				.when()
					.log().all()
					.get("users")
				.then()
					.log().all()
					.statusCode(404)
					.header("Content-Type", "application/json; charset=utf-8")
					.extract().response();
		
		String resp = response.asString();
		System.out.println(resp);
		
		int responseStat = response.getStatusCode();
		System.out.println("Response Status :" + responseStat);
		Assert.assertEquals(responseStat, 404);
		
		String responseHeader=response.getHeader("Content-Type");
		System.out.println(responseHeader);
		Assert.assertEquals(responseHeader, "application/json; charset=utf-8");
	}
}
