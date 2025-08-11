package com.example.parameters.tests;

import static io.restassured.RestAssured.given;

import org.junit.Test;

import io.restassured.http.ContentType;

import static org.hamcrest.CoreMatchers.equalTo;

public class Parameter_Demo {

	@Test
    public void verifyQueryParam() {
 
        String baseUri = "https://reqres.in/api";
 
        given()
        	.log().everything()
        	.header("Accept", ContentType.JSON, "ContentType", ContentType.JSON)
        	.baseUri(baseUri)
	        .queryParam("page", "2").
	 
	    when()
	        .get(baseUri + "/users/").
	              
	    then()
	    	.log().ifValidationFails()
//	    	.statusCode(200)
	        .body("page", equalTo(2))
	        .body("per_page", equalTo(6))
	        .body("total_pages", equalTo(2));
    }
}