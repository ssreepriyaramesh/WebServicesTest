package aws.webservicestest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AutoTestUsers {

	@Test(priority = 1)
	public void testGetUsers() {
		given().get("http://ec2-54-84-52-184.compute-1.amazonaws.com:3000/users").then().assertThat().statusCode(200)
				.and().contentType(ContentType.JSON).and().body("0.id", equalTo("1")).and()
				.body("0.name", equalTo("Tanbir Mowla"));
	}

	@Test(priority = 2)
	public void testPostUsers() {
		RequestSpecification reqSpec = RestAssured.given();

		reqSpec.header("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		json.put("id", "1709");
		json.put("name", "Peony Rex");
		json.put("email", "peony@aws123.net");
		json.put("address", "Rex Street");

		reqSpec.body(json.toJSONString());

		Response resp = reqSpec.post("http://ec2-54-84-52-184.compute-1.amazonaws.com:3000/users");

		int code = resp.getStatusCode();

		Assert.assertEquals(code, 201);

	}

	@Test(priority = 3)
	public void testPutUsers() {
		RequestSpecification reqSpec = RestAssured.given();

		reqSpec.header("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		json.put("id", "1709");
		json.put("name", "Peony Rex");
		json.put("email", "peony@gmail123.net");
		json.put("address", "Rex Street");

		reqSpec.body(json.toJSONString());

		Response resp = reqSpec.put("http://ec2-54-84-52-184.compute-1.amazonaws.com:3000/users/1709");

		int code = resp.getStatusCode();

		Assert.assertEquals(code, 200);
	}

}
