//
package com.api.tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.Oem;
import com.api.constant.Platform;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.constant.Service_Location;
import com.api.constant.Warranty_Status;
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
	
		Customer customer =  new Customer("Laxman", "Teli", "8446981010", "", "testlaxman@test1.com", "");
		CustomerAddress customerAddress = new CustomerAddress("A-22", "GS", "Shahunagar", "Siddivinayak Temple", "Chinchwad", "411222", "India", "MH");
		CustomerProduct customerProduct = new CustomerProduct(DateTime.getTimeWithDaysAgo(5), "15549925191111", "15549925191111", "15549925191111", DateTime.getTimeWithDaysAgo(5), Product.NEXUS_2.getCode(), Model.Nexus_2_blue.getCode());
		
		
		Problems problems = new Problems(1, "Battery Issue");
		List<Problems> problemsList =  new ArrayList<Problems>();
		problemsList.add(problems);
		
		CreateJobPayload createJobPayload = new CreateJobPayload(Service_Location.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRENTY.getCode(), Oem.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemsList);
		
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
