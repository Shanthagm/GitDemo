package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils  {
	
	//make it static so that , it initialize only once , i.e. class time
	public static RequestSpecification reqspec;
	
	
	public RequestSpecification  requestSpecification() throws IOException {
	//when we test for multiple data , it override old data in logging file,
		//so to avoid that , and to log for all the test data req n res use if cond
		if (reqspec==null) {
		PrintStream log  = new PrintStream(new FileOutputStream("logging.txt"));
		
		reqspec = new RequestSpecBuilder()
                .setBaseUri(getGlobalValue("baseUrl"))
                .addQueryParam("key", "qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType(ContentType.JSON).build();
				}
				return reqspec;
	}
	
	
	public static String getGlobalValue(String baseUrl) throws IOException {
		
		Properties prop  = new Properties();
		FileInputStream fis = new FileInputStream("E:\\APIFramework_WS\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(baseUrl);
	}

	
	
	public String getJsonPath(Response response, String key) {
		
		JsonPath js = new JsonPath(response.asString()) ;
		return js.get(key).toString();
	}
}
