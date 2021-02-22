package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class getOneItem {

	@Test
	public void GetASingleItemFunction() {
		
		Response response = 
				given()
				.baseUri("https://reqres.in/api/")
				.header("Content-Type", "application/json; charset=utf-8")
				.queryParam("id", "1")
				.when()
				.get("users")
				.then()
				.header("Content-Type", "application/json; charset=utf-8")
				.extract().response();
		
		ResponseBody respbody = response.body();
		String respoBody = respbody.asString();
		//System.out.println(respoBody);
		
		JsonPath jp = new JsonPath(respoBody); 
		int id = jp.getInt("data.id");
		String email = jp.get("data.email");
		String FN = jp.get("data.first_name");
		String LN = jp.get("data.last_name");
		String avatar = jp.get("data.avatar");
		
		System.out.println("Id: "+id);
		System.out.println("Email: " +email);
		System.out.println("First Name: " +FN);
		System.out.println("Last Name: " +LN);
		System.out.println("Avatar: " +avatar);
		
		Assert.assertEquals(email, "george.bluth@reqres.in"); 
		Assert.assertEquals(FN, "George"); 
		Assert.assertEquals(LN, "Bluth"); 
	}
}
