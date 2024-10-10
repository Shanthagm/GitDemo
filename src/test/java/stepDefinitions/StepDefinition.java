package stepDefinitions;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils{
		
	 RequestSpecification req;
	 Response response;
	 ResponseSpecification resspec;
	 
	 TestDataBuild data = new TestDataBuild();
	 //make it static, otherwise u will get null pointer exception, when u run deleteAPI tc , 
	 //cause when u run next scenario n if it is not static then u can not re use in next method it will set to null
	
	 static String place_Id;
	
	  @Given("Add Place API {string}, {string}, {string};")
		 public void add_place_api(String name, String address, String language) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		     req = given().spec(requestSpecification())
					.body(data.addPlacePayload(name,address,language));
	}
	  
	  @When("User calls {string} with {string} http request")
	  public void user_calls_with_http_request(String resource, String method) {
	    
	     //constructor will be called with value of resource which you pass	
	
		  APIResources apiresource =	APIResources.valueOf(resource);
		System.out.println(apiresource.getResource());

	resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
		      response =	req.when().post(apiresource.getResource());
	    else if(method.equalsIgnoreCase("GET"))
			    response =	req.when().get(apiresource.getResource());
	}
	@Then("the API call got success with statuscode {int}")
	public void the_api_call_got_success_with_statuscode(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	     
		assertEquals(response.getStatusCode(), 200) ;
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is_ok(String key,  String expValue) {
	    // Write code here that turns the phrase above into concrete actions
		   
		   assertEquals(getJsonPath(response, key), expValue);
	}
	
		
	@Then("Verify  Place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expname, String resource) throws IOException {
	    
		 place_Id = getJsonPath(response, "place_id");
	
		req = given().spec(requestSpecification()).queryParam("place_id", place_Id);
		user_calls_with_http_request(resource, "GET");
		String acName = getJsonPath(response, "name");
		System.out.println("acName" +acName);
		System.out.println("expname" +expname);
		assertEquals(expname.trim(), acName.trim());
	}

	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	   
		req = given().spec(requestSpecification()).body(data.deletePayload(place_Id));
	}







}
