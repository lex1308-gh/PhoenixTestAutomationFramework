package com.api.tests;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {

	@Test
	public void loginAPITest() {

		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		
		given()
		 	.baseUri("http://64.227.160.186:9000/v1")
		 	.contentType(ContentType.JSON)
		 	.and()
		 	.accept(ContentType.JSON)
		 	.and()
		 	.body(userCredentials)
		 	.and()
		 	.log().uri()
		 	.log().headers()
		 	.log().body()
		 	.log().method()
		 .when()
		 	.post("login")
		 .then()
		 	.log().all()
		 	.statusCode(200)
		 	.and()
		 	.time(Matchers.lessThan(2000L))
		 	.and()
		 	.body("message", Matchers.equalTo("Success"))
		 	.and()
		 	.body("data.token", Matchers.notNullValue())
		 	.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"))
		 	.extract().response();
		 	
	}
}
