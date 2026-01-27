//
package com.api.tests;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {

	
	private UserCredentials userCredentials;

	@BeforeMethod(description = "Setting up the payload")
	public void setup() {
		userCredentials = new UserCredentials("iamfd", "password");
	}

	
	
	@Test(description = "Verify if Login API is working for FD user", groups = { "api", "regression", "smoke","sanity" })
	public void loginAPITest() throws IOException {

		given().spec(SpecUtil.requestSpec(userCredentials)).when().post("login").then().spec(SpecUtil.responseSpec())
				.and().body("message", Matchers.equalTo("Success")).and().body("data.token", Matchers.notNullValue())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"))
				.extract().response();

	}
}
