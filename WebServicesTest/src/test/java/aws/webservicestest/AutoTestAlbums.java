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

public class AutoTestAlbums {

	@Test(priority = 1)
	public void testGetAlbums() {
		given().get("http://ec2-54-84-52-184.compute-1.amazonaws.com:3000/albums").then().assertThat().statusCode(200)
				.and().contentType(ContentType.JSON).and().body("7.userId", equalTo("1")).and()
				.body("7.title", equalTo("qui fuga est a eum"));
	}

	@Test(priority = 2)
	public void testPostPosts() {
		RequestSpecification reqSpec = RestAssured.given();

		reqSpec.header("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		json.put("userId", "1555");
		json.put("id", "1555");
		json.put("title", "TestNG Screenshots");

		reqSpec.body(json.toJSONString());

		Response resp = reqSpec.post("http://ec2-54-84-52-184.compute-1.amazonaws.com:3000/albums");

		int code = resp.getStatusCode();

		Assert.assertEquals(code, 201);

	}

	@Test(priority = 3)
	public void testPutPosts() {
		RequestSpecification reqSpec = RestAssured.given();

		reqSpec.header("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		json.put("userId", "1555");
		json.put("id", "1555");
		json.put("title", "TestNG Fine Screenshots");

		reqSpec.body(json.toJSONString());

		Response resp = reqSpec.put("http://ec2-54-84-52-184.compute-1.amazonaws.com:3000/albums/1555");

		int code = resp.getStatusCode();

		Assert.assertEquals(code, 200);
	}
}
