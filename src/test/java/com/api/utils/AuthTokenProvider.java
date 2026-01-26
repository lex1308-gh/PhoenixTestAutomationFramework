//
package com.api.utils;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;

import com.api.constant.Role;
import com.api.request.model.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {

	// Private constructor wont allow object of class to be created outside class
	private AuthTokenProvider() {

	}

	public static String getToken(Role role) {

		UserCredentials userCredentials = null;
		if (role == Role.FD) {

			userCredentials = new UserCredentials("iamfd", "password");

		} else if (role == Role.SUP) {

			userCredentials = new UserCredentials("iamsup", "password");

		} else if (role == Role.ENG) {

			userCredentials = new UserCredentials("iameng", "password");

		} else if (role == Role.QC) {

			userCredentials = new UserCredentials("iamqc", "password");
		}

		// Make request for loginAPI and extract the token
		String token = given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(userCredentials).when().post("login").then().log().ifValidationFails()
				.statusCode(200).body("message", Matchers.equalTo("Success")).extract().body().jsonPath()
				.getString("data.token");

		return token;
	}

}
