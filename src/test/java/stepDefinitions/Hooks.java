package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
		public void beforeScenario() throws IOException {
		
		StepDefinition m = new StepDefinition();
		if(StepDefinition.place_Id==null) {
		m.add_place_api("Shetty", "Spain", "English");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("Shetty", "GET");
			
		}
	}
	

}
