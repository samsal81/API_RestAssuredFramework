package tests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class loginTest {

	@SuppressWarnings("unchecked")
	@Test
	public void Login () {
		
		@SuppressWarnings("rawtypes")
		HashMap payloadBody = new HashMap();
		payloadBody.put("email", "eve.holt@reqres.in");
		payloadBody.put("password", "pistol");
		Response response = 
				given()
					.baseUri("https://reqres.in/api/")
					.body(payloadBody)
	                .header("Accept", ContentType.JSON.getAcceptHeader())
	                .contentType(ContentType.JSON)
				.when()
					.post("login")
				.then()
					.log().all()
					.statusCode(200)
					.header("Content-Type", "application/json; charset=utf-8")
					.extract().response();
		
		String res = response.asString();
		
		System.out.println(res);
		
		int responseStat = response.getStatusCode();
		System.out.println("Response Status :" + responseStat);
		Assert.assertEquals(responseStat, 200);
		
		String responseHeader=response.getHeader("Content-Type");
		System.out.println(responseHeader);
		Assert.assertEquals(responseHeader, "application/json; charset=utf-8");
		
		JsonPath js= new JsonPath(response.asString());
		String token = js.get("token");
		
		System.out.println("Token: " + token);
	}
}
