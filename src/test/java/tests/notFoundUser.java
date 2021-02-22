package tests;

import static io.restassured.RestAssured.given;

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
	}
}
