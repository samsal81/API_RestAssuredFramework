package tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class getOneItem {

	@Test
	public void GetASingleItemFunction() {
		
		Response response = 
				given()
				.baseUri("https://reqres.in/api/")
				.header("Content-Type", "application/json; charset=utf-8")
				.queryParam("id", "1")
				.when()
				.log().all()
				.get("users")
				.then()
				.log().all()
				.header("Content-Type", "application/json; charset=utf-8")
				.extract().response();
		
		System.out.println(response.toString());
		
	}
}
