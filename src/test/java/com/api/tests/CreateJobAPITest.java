package com.api.tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTime;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {

	@Test  
	public void createJobAPITest() {
	
		Customer customer =  new Customer("Laxman", "Teli", "8446981010", "8446981020", "testlaxman@test1.com", "testlaxman@test1.com");
		CustomerAddress customerAddress = new CustomerAddress("A-22", "GS", "Shahunagar", "Siddivinayak Temple", "Chinchwad", "411222", "India", "MH");
		CustomerProduct customerProduct = new CustomerProduct(DateTime.getTimeWithDaysAgo(5), "13549925191115", "13549925191115", "13549925191115", DateTime.getTimeWithDaysAgo(5), 1, 1);
		Problems problems = new Problems(1, "Battery Issue");
		List<Problems> problemsList =  new ArrayList<Problems>();
		problemsList.add(problems);
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsList);
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.when().post("/job/create")
		.then()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data.job_number", Matchers.startsWith("JOB_"))
		.spec(SpecUtil.responseSpec());
		
		
		
	}
}
