package com.example.get.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.IOException;

import org.junit.Test;
 
public class JsonCompare {
 
    @Test
    public void verifyGreaterResponseTime() throws IOException {
 
        given().
                 
        when()
        	.get("https://reqres.in/api/users/3").
 
        then()
        	.assertThat()
        	.body(matchesJsonSchemaInClasspath("src/test/resources/data/User.json"));
    }
}