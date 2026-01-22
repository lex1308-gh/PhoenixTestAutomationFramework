// Comments to be added soon

package com.api.tests;

import static io.restassured.RestAssured.*;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {

	@Test
	public void verifyCountAPIResponse() {

		given().spec(SpecUtil.requestSpecWithAuth(Role.FD)).when().get("/dashboard/count").then()
				.spec(SpecUtil.responseSpec())
				.body("message", Matchers.equalTo("Success"))
				.body("data", Matchers.notNullValue()).body("data.size()", Matchers.equalTo(3))
				.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
				.body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema.json"))
				.body("data.key",
						Matchers.containsInAnyOrder("pending_fst_assignment", "pending_for_delivery", "created_today"));
	}

	@Test  
	private void countAPITest_MissingAuthToken() {

		given().spec(SpecUtil.requestSpec()).when()
				.get("/dashboard/count").then().log().all().statusCode(401);
	}
}
