package com.ip.rest.automation;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class RestAssuredOtherOperations {
	
	String endPointUrl ="https://api.twitter.com/1.1";
	String resourceUrl;
	RequestSpecBuilder builder;
	RequestSpecification reqSpec;
	
	@Before
	public void setup(){
		 builder= new RequestSpecBuilder();
		RequestSpecification spec =given().auth().oauth("ZDlzirIJisOW2IR3SnBfYygPm", "T0ZlQEbYdGEQDGo5mr8OgIcBW4bfF8xt2svEksysdi7KlDUgih",
				"738049748985204736-KqojDhKb9m7HsWA1ijQ9KaIgi2RLzoC", "Hits9HjjbtDPL8yqGxu5TsXbnrtXBRm7aEqLftbF0qOvf");
		builder.setContentType("application/json; charset=utf-8");
		 reqSpec = builder.addRequestSpecification(spec).build();
		
	}
	
	@Test
	public void testPostReqTwitterAPI(){
		resourceUrl="/statuses/update.json?status=RestAPITesting from JayWay is based on java";
		
		Response response=given().spec(reqSpec).when().post(endPointUrl+resourceUrl);
		
		System.out.println(response.getStatusCode());
		
		JSONObject jsonResponse = new JSONObject(response.body().asString());
		System.out.println(jsonResponse.toString());
		System.out.println("parsing json using JsobObject::"+jsonResponse.get("id_str"));
		assertEquals(jsonResponse.get("text"),"RestAPITesting from JayWay is based on java");
		assertNotNull(jsonResponse.get("id_str"));
		
	}
	
	@Test
	public void testPut(){
		resourceUrl="/account/update_profile.json?location=California";
		Response response=given().spec(reqSpec).when().post(endPointUrl+resourceUrl);
		
		System.out.println(response.getStatusCode());
		JsonPath jsonPath = response.jsonPath();
		System.out.println(jsonPath.prettyPrint());
		System.out.println("parsing json using json path::"+jsonPath.getString("status.source"));
	}
	
	@Test
	public void testDelete(){
		resourceUrl="/friendships/destroy.json?screen_name=ashish";
		Response response=given().spec(reqSpec).when().delete(endPointUrl+resourceUrl);
		
		JSONObject jsonResponse = new JSONObject(response.body().asString());
		System.out.println(jsonResponse.toString());
	}

}
