package com.example.post.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
public class EmployeesPostRequests {
	String baseUri = "https://dummy.restapiexample.com/api";

	@Test
	@Ignore("Test Working") 
	public void getEmployees() {
		
		given()
			.baseUri(baseUri)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON).
		
		when()
			.request(Method.GET, "/v1/employees").
		
		then()
			.assertThat()
			
			.statusCode(200)
			.statusLine("HTTP/1.1 200 OK")
			.log().everything();
	}
	
	@Test
	@Ignore("Test Working")
	public void createEmployee() {
		String payload = "{\"id\":\"25\", \"employee_name\":\"New Employee C\",\"employee_salary\":\"4000\",\"employee_age\":\"29\"}";

		given()
			.baseUri(baseUri)
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(payload).

		when()
			.request(Method.POST, "/v1/create").
			
		then()
			.assertThat()
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.body("status", equalTo("success"))
				.body("message", equalTo("Successfully! Record has been added."))
				.body("data.employee_name", equalTo("New Employee C"))
				.log().all();
	}

	@Test
	@Ignore("Test Working")
	public void passBodyAsJsonObject() {
        final String name = "MapTest";
		final String age = "30";
		final String salary = "11111";
		final String image = "test.png";
		
		given()
        	.baseUri(baseUri)
        	.accept(ContentType.JSON)
        	.contentType(ContentType.JSON)
            .body(RequestBody.createRequestBody(name, age, salary, image))
            .log().all().
 
        when()
            .request(Method.POST, "/v1/create").
 
        then()
            .assertThat()
            .statusCode(200)
            .body("data.employee_name", equalTo(name))
            .body("data.employee_age", equalTo(age))
            .body("data.employee_salary", equalTo(salary))
            .body("data.profile_image", equalTo(image))
            .body("message", equalTo("Successfully! Record has been added."))
            .log().ifValidationFails();
    }
	
	@Test
	@Ignore("Test Working")
	public void passBodyAsJsonFile() {
		given()
        	.baseUri(baseUri)
        	
        	.accept(ContentType.JSON)
        	.contentType(ContentType.JSON)
            .body(new File("src/test/resources/data/employeeData.json"))
            .log().all().
 
        when()
            .request(Method.POST, "/v1/create").
 
        then()
            .assertThat()
            .statusCode(200)
            .body("data.employee_name", equalTo("Maptest"), 
            	  "data.employee_age", equalTo("29"), 
            	  "data.employee_salary", equalTo("1234567"), 
            	  "data.profile_image", equalTo("test.png"), 
            	  "message", equalTo("Successfully! Record has been added."))
            .log().ifValidationFails();
    }
	
	@Test
	@Ignore("Test used tio verify root folder")
	public void whenUsingJavaIoFile_thenReturnCurrentDirectory() {
	    String userDirectoryAbsolutePath = new File("").getAbsolutePath();
	    assertTrue(userDirectoryAbsolutePath.endsWith("restassured_demo"));	// project root folder
	}
}