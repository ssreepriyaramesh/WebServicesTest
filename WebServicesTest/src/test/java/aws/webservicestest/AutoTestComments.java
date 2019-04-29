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

public class AutoTestComments {

	@Test(priority = 1)
	public void testGetComments() {
		given().get("http://ec2-54-84-52-184.compute-1.amazonaws.com:3000/comments").then().assertThat().statusCode(200)
				.and().contentType(ContentType.JSON).and().body("1.id", equalTo("4")).and()
				.body("1.email", equalTo("Lew@alysha.tv"));
	}

	@Test(priority = 2)
	public void testPostPosts() {
		RequestSpecification reqSpec = RestAssured.given();

		reqSpec.header("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		json.put("postId", "13");
		json.put("id", "3011");
		json.put("name", "reviewer301");
		json.put("email", "Nikita@garfield.biz");
		json.put("body", "Rest Assured, TestNG, Maven");

		reqSpec.body(json.toJSONString());

		Response resp = reqSpec.post("http://ec2-54-84-52-184.compute-1.amazonaws.com:3000/comments");

		int code = resp.getStatusCode();

		Assert.assertEquals(code, 201);

	}

	@Test(priority = 3)
	public void testPutPosts() {
		RequestSpecification reqSpec = RestAssured.given();

		reqSpec.header("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		json.put("postId", "1");
		json.put("id", "3");
		json.put("name", "Nikita");
		json.put("email", "Nikita@garfield.biz");
		json.put("body", "updated subject notes: QA Automation");

		reqSpec.body(json.toJSONString());

		Response resp = reqSpec.put("http://ec2-54-84-52-184.compute-1.amazonaws.com:3000/comments/3");

		int code = resp.getStatusCode();

		Assert.assertEquals(code, 200);
	}

}
