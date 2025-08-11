package com.example.auth.tests;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Ignore;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Method;

public class Authentication_Demo {

	@Test
	@Ignore("Working")
    public void validateBasicCredentials() {
        String baseUri = "https://httpbin.org";
        
		given()
	        .auth().preemptive().basic("user", "pass")
	        .baseUri(baseUri)
	        .header("Accept", ContentType.JSON, "ContentType", ContentType.JSON).
	    
	    when()
	    	.log().everything()
	        .request(Method.GET, "/basic-auth/user/pass").
	    
	    then()
	    	.log().everything()
	    	.assertThat()
	    	.statusCode(200)
	    	.statusLine("HTTP/1.1 200 OK")
	    	.body("authenticated", equalTo(true), 
	    		  "user", equalTo("user"))
	    	.header("Content-Type", equalTo("application/json"))
	    	.header("Content-Length", "47");
    }
	
	@Test
	@Ignore
    public void validateToken() {
		final String baseUri = "https://httpbin.org/";
	    final String token = "Basic dXNlcjpwYXNz";
	    
        given()
        	.baseUri(baseUri)
        	.header("Accept", "application/json")
            .header("Authorization", token)
            .contentType(ContentType.JSON)
            .log().all().
                
        when()
            .request(Method.GET, "basic-auth/user/pass").
            
        then()
        	.statusCode(200)
        	.body("authenticated", equalTo(true))
        	.body("user", equalTo("user"))
            .log().all(); 
    }
	
	@Test
	@Ignore
	public void getUsers() {
		String baseUri = "https://reqres.in/";
	
		given()
			.baseUri(baseUri)
			.header("Accept", "application/json")
			.header("ContentType", "application/json")
			.header("x-api-key", "reqres-free-v1")
			.log().all().
			
		when()
			.request(Method.GET, "api/users").
			
		then()
			.body("page", equalTo(1))
			.body("data.id.get(1)", equalTo(2))
			.body("data.id.size()", equalTo(6))
			.log().all();
	}
	
	@Test
    public void getAccessToken() {
    	String accessToken;
    	Response response;
    	String baseUri = "https://dev-xgdjgpnd5tt5h32b.eu.auth0.com/";
    	
    	response = given()
    		.baseUri(baseUri)
            .contentType("application/x-www-form-urlencoded")
            .formParam("grant_type", "client_credentials")
            .formParam("client_id", "wHjfcO05tfxnM9OTtyjvLJg9J8LL6JNY")
            .formParam("client_secret", "noSXJzNV1x1bf-aWnInCJ6cULgC9sVXq5WOvAV3rZGBh8sZ1Q0VMmGOZBxoVvgac")
            .log().all().
        
        when()
            .request(Method.POST, "oauth/token");
 
    	System.out.println("Response: " + response.asPrettyString());
//        accessToken = response.jsonPath().getString("access_token");
//        expiresIn = response.jsonPath().getInt("expires_in");
//        tokenExpiryTime = Instant.now().plusSeconds(expiresIn);
 
//        System.out.println("Access Token: " + accessToken);
//        System.out.println("Token Expiry Time: " + tokenExpiryTime);
    }
}