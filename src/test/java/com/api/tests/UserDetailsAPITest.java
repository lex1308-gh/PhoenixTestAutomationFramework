//
package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class UserDetailsAPITest {

	@Test(description = "Verify if user details API response is shown correctly", groups = { "api", "smoke","regression" })
	public void userDetailsAPITest() throws IOException {

		given().spec(SpecUtil.requestSpecWithAuth(Role.FD)).when().get("userdetails").then()
				.spec(SpecUtil.responseSpec()).and()
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"))
				.extract().response();

	}
}
