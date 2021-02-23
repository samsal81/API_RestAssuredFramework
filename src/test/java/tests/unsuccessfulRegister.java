package tests;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class unsuccessfulRegister {

	@SuppressWarnings("unchecked")
	@Test
	public void FailedRegister() {
		
		@SuppressWarnings("rawtypes")
		HashMap payloadBody = new HashMap();
		payloadBody.put("email", "samsal@reqres.in");
		
		Response response = 
				given()
					.baseUri("https://reqres.in/api/")
					.body(payloadBody)
	                .header("Accept", ContentType.JSON.getAcceptHeader())
	                .contentType(ContentType.JSON)
				.when()
					.post("register")
				.then()
					.log().all()
					.statusCode(400)
					.header("Content-Type", "application/json; charset=utf-8")
					.extract().response();
		
		int responseStat = response.getStatusCode();
		System.out.println("Response Status: " + responseStat);
		Assert.assertEquals(responseStat, 400);
		
		JsonPath js= new JsonPath(response.asString());
		String error = js.get("error");
		
		System.out.println("Error Message: " + error);
		
		String responseHeader=response.getHeader("Content-Type");
		System.out.println(responseHeader);
		Assert.assertEquals(responseHeader, "application/json; charset=utf-8");
	}
}
