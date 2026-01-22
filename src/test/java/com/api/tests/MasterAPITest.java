package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class MasterAPITest {

	@Test
	public void masterAPITest() {

		given().spec(SpecUtil.requestSpecWithAuth(Role.FD)).when()
				.post("master").then().spec(SpecUtil.responseSpec())
				.body("message", Matchers.equalTo("Success")).body("data", Matchers.notNullValue())
				.body("data", Matchers.hasKey("mst_oem")).body("data", Matchers.hasKey("mst_model"))
				.body("$", Matchers.hasKey("message")).body("$", Matchers.hasKey("data"))
				.body("data.mst_oem.size()", Matchers.greaterThan(0))
				.body("data.mst_model.size()", Matchers.greaterThan(0))
				.body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()))
				.body("data.mst_oem.name", Matchers.everyItem(Matchers.notNullValue()))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
	}

	@Test
	public void invlaidTokenMasterAPITest() {

		given().baseUri(ConfigManager.getProperty("BASE_URI")).header("Authorization", "").contentType("").log().all()
				.when().post("master").then().log().all().statusCode(401);
	}

}
