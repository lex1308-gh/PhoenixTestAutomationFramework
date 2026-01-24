package com.api.utils;

import org.hamcrest.Matchers;

import com.api.constant.Role;
import com.api.pojo.UserCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {

	//Utility classes should have only static methods
	
	//For get-delete requests - without body
	public static RequestSpecification requestSpec() {
		
		RequestSpecification requestSpecification=new RequestSpecBuilder()
		.setBaseUri(ConfigManager.getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		
		return requestSpecification;
	}
	
	
	
	//for put-patch-post -- with body
	public static RequestSpecification requestSpec(Object payload) {
		
		RequestSpecification requestSpecification=new RequestSpecBuilder()
		.setBaseUri(ConfigManager.getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.setBody(payload)
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		
		return requestSpecification;
	}
	
	
    public static RequestSpecification requestSpecWithAuth(Role role ) {
		
		RequestSpecification requestSpecification=new RequestSpecBuilder()
		.setBaseUri(ConfigManager.getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.addHeader("Authorization", AuthTokenProvider.getToken(role))
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		
		return requestSpecification;
	}
    
   public static RequestSpecification requestSpecWithAuth(Role role, Object payload ) {
		
		RequestSpecification requestSpecification=new RequestSpecBuilder()
		.setBaseUri(ConfigManager.getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.addHeader("Authorization", AuthTokenProvider.getToken(role))
		.setBody(payload)
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		
		return requestSpecification;
	}
	
	
	public static ResponseSpecification responseSpec() {
		
		ResponseSpecification responseSpecification= new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectResponseTime(Matchers.lessThan(3000L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
		
	}
	
}
