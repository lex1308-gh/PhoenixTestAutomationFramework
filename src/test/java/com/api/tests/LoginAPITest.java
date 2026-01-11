package com.api.tests;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {

	@Test
	public void loginAPITest() throws IOException {

		UserCredentials userCredentials = new UserCredentials("iamfd", "password");

		given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(ContentType.JSON).and()
				.accept(ContentType.JSON).and().body(userCredentials).and().log().uri().log().headers().log().body()
				.log().method().when().post("login").then().log().all().statusCode(200).and()
				.time(Matchers.lessThan(2000L)).and().body("message", Matchers.equalTo("Success")).and()
				.body("data.token", Matchers.notNullValue())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"))
				.extract().response();

	}
}
