package com.ip.rest.automation;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.*;
import java.util.List;

import org.junit.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class RestAssuredAPIGetOperation{
	
	String endPointUrl="https://maps.googleapis.com";
	String resourceUrl="/maps/api/geocode/json";
	
	@Test
	public void testRestApiStatusCode(){
		int statusCode = given().param("address","Dublin, CA")				
				.contentType(ContentType.JSON)
		.param("key","AIzaSyAjPkhQMPZs2G1Iwx5FAe69KVoDKpoURoQ")
		.get(endPointUrl+resourceUrl).getStatusCode();
		
		System.out.println("status code is "+statusCode);
		assertTrue(200==statusCode);
		
	}
	
	@Test
	public void testResponseHeader(){
		Response response = given().param("address","Dublin, CA")				
		.contentType(ContentType.JSON)
.param("key","AIzaSyAjPkhQMPZs2G1Iwx5FAe69KVoDKpoURoQ")
.get(endPointUrl+resourceUrl);
		
		System.out.println(response.header("Content-Type"));		
		
		response.then().assertThat()
		.header("Content-Encoding", "gzip")
		.header("Content-Type", "application/json; charset=UTF-8");			
		
	}
	
	@Test
	public void testStatus(){
		Response response = given().param("address","Dublin, CA")				
				.contentType(ContentType.JSON)
		.param("key","AIzaSyAjPkhQMPZs2G1Iwx5FAe69KVoDKpoURoQ")
		.get(endPointUrl+resourceUrl).then().extract().response();
	   System.out.println(response.jsonPath().getString("results.formatted_address"));
	   System.out.println(response.jsonPath().getString("results.geometry.viewport.southwest.lng"));
		assertEquals("OK", response.path("status"));
	}
	
	@Test
	public void testResponseData(){
		JsonPath  jsonpath = given().param("address","Dublin, CA")				
				.contentType(ContentType.JSON)
		.param("key","AIzaSyAjPkhQMPZs2G1Iwx5FAe69KVoDKpoURoQ")
		.get(endPointUrl+resourceUrl).jsonPath();
		System.out.println(jsonpath.getString("results"));
		
		Response response =given().param("address","Dublin, CA")				
		.contentType(ContentType.JSON)
.param("key","AIzaSyAjPkhQMPZs2G1Iwx5FAe69KVoDKpoURoQ")
.get(endPointUrl+resourceUrl).then().extract().response();
		List<String> list = response.path("results.long_name");
		System.out.println(list.size());
		
	}

}
