package TwitterDefinations;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.json.JSONObject;
import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.authentication.OAuthSignature;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TwitterDefinations {
	
	private static String url;
	private static Response response;
	private static int expectedStatusCode;
	private static String consumerKey,consumerSecret,accessToken,secretToken;
	//private JsonConversionUtil jsonConversionUtil;
	private JSONObject responseJsonObject;
	private Properties prop = new Properties();

	@Given("^pass \"([^\"]*)\" and \"([^\"]*)\" to user_timeline api$")
	public void creatingTheApi(String arg1,String arg2) throws Throwable {
		InputStream input = new FileInputStream("src/test/resources/config/config.properties");
		prop.load(input);
		
		consumerKey = prop.getProperty("consumerKey");
		consumerSecret = prop.getProperty("consumerSecret");
		accessToken = prop.getProperty("accessToken");
		secretToken = prop.getProperty("secretToken");
		
		 url = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name="+arg1+"&count="+arg2;
	}

	@When("^Hit user_timeline api$")
	public void hit_WeatherData_Api() throws Throwable {		
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.auth().oauth(consumerKey,consumerSecret,accessToken,secretToken,OAuthSignature.HEADER);
		response = httpRequest.get(url);
	}

	@Then("^Verify the user_timeline api response$")
	public void verify_WeatherData_Reponse() throws Throwable {		
		System.out.println(response.asString());
	}


}
