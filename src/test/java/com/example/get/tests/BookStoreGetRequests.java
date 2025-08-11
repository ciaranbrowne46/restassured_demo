package com.example.get.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Ignore;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Method;

public class BookStoreGetRequests {
	String baseUri = "https://demoqa.com/BookStore/v1/Books";
		
	@Ignore("Test Working") 
	@Test
	public void getBooks() {
		
		String prettyResponse = given()
			.baseUri(baseUri)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON).
		
		when()
			.request(Method.GET).
		
		then()
			.assertThat()
			
			.statusCode(200)
			.statusLine("HTTP/1.1 200 OK")
			.extract().asPrettyString();
		
		System.out.println("Pretty response: " + prettyResponse);
	}
	
	@Ignore("Test Working") 
	@Test
	public void verifyNumberOfBooks() {
		given()
			.baseUri(baseUri)
			.contentType(ContentType.JSON).
		
		when()
			.request(Method.GET).
			
		then()
			.assertThat()
			.body("books.title.size()", equalTo(8));
	}
	
	@Ignore("Test Working")
	@Test
	public void verifyTitleOfBook() {
		given()
			.baseUri(baseUri)
			.contentType(ContentType.JSON).
		
		when()
			.request(Method.GET).
			
		then()
			.assertThat()
			.body("books.title.get(2)", equalTo("Designing Evolvable Web APIs with ASP.NET"));
	}
	
	@Ignore("Test Working") 
	@Test
	public void verifyIsbnOfLastBook() {
		given()
			.baseUri(baseUri)
			.contentType(ContentType.JSON).
		
		when()
			.request(Method.GET).

		then()
			.assertThat()
			.body("books[7].isbn", equalTo("9781593277574"));
	}
	
	@Ignore("Test Working") 
	@Test
	public void printTitleOfBooks() {
		given()
			.baseUri(baseUri)
			.contentType(ContentType.JSON).
		
		when()
			.request(Method.GET).
			
		then()
			.extract()
			.response().jsonPath().getList("books.title").forEach(System.out::println);
	}
}