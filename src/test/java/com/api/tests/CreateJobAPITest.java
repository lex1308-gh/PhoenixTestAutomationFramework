package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtil;

public class CreateJobAPITest {

	@Test  
	public void createJobAPITest() {
	
		Customer customer =  new Customer("Laxman", "Teli", "8446981010", "8446981020", "testlaxman@test1.com", "testlaxman@test1.com");
		CustomerAddress customerAddress = new CustomerAddress("A-22", "GS", "Shahunagar", "Siddivinayak Temple", "Chinchwad", "411222", "India", "MH");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z", "19346045295622", "19346045295622", "19346045295622", "2025-04-06T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Battery Issue");
		Problems problemsArray[] =  new Problems[1];
		problemsArray[0]=problems;
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.when().post("/job/create")
		.then().log().all().statusCode(200);
		
		
		
	}
}
