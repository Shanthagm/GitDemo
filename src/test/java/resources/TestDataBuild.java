package resources;
import pojo.AddPlace;
import pojo.Location;

import java.util.List;
import java.util.ArrayList;

public class TestDataBuild {
	
	public AddPlace  addPlacePayload(String name, String address, String language) {
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		
		
		List<String> list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		ap.setTypes(list);
		return ap;
	}
	
	
	
	
	public String deletePayload(String placeid) {
		
	String deletePayload = 	"{\r\n"
			+ "    \"place_id\":\""+placeid+"\"\r\n"
			+ "}";
	
	return deletePayload;
			
	}

}
