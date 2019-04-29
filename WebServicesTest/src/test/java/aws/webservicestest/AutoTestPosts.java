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

public class AutoTestPosts {

	// @Test(priority = 1)
	public void testGetPosts() {
		given().get("http://ec2-54-84-52-184.compute-1.amazonaws.com:3000/posts").then().assertThat().statusCode(200)
				.and().contentType(ContentType.JSON).and().body("2.title", equalTo("eum et est occaecati")).and()
				.body("2.id", equalTo("4"));
	}

	// @Test(priority = 2)
	public void testPostPosts() {
		RequestSpecification reqSpec = RestAssured.given();

		reqSpec.header("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		json.put("userId", "1555");
		json.put("title", "Quality Assurance Specialist");
		json.put("body", "Software Development in Testing");
		json.put("id", "1559");

		reqSpec.body(json.toJSONString());

		Response resp = reqSpec.post("http://ec2-54-84-52-184.compute-1.amazonaws.com:3000/posts");

		int code = resp.getStatusCode();

		Assert.assertEquals(code, 201);

	}

	@Test(priority = 3)
	public void testPutPosts() {
		RequestSpecification reqSpec = RestAssured.given();

		reqSpec.header("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		json.put("userId", "1555");
		json.put("title", "QA Specialist");
		json.put("body", "Software Test Engineer");
		json.put("id", "1103");

		reqSpec.body(json.toJSONString());

		Response resp = reqSpec.put("http://ec2-54-84-52-184.compute-1.amazonaws.com:3000/posts/1103");

		int code = resp.getStatusCode();

		Assert.assertEquals(code, 200);
	}

}
