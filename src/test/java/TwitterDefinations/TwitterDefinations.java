package TwitterDefinations;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonObject;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.authentication.OAuthSignature;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
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
	private Integer followers_count, friends_count;
	private String screen_name; 
	private ArrayList<String> tweets;
	private Map<String, String>  retweetCount = new HashMap<String, String>();
	private Map.Entry<Integer, Integer>  maxEntry = null;
	private JSONObject jsonObject, maxRetweet;
	private Scenario scenerio= null;
	
	@Before
	public void setScenerios(Scenario scenerio)
	{
		this.scenerio = scenerio;
	}

	@Given("^pass \"([^\"]*)\" and \"([^\"]*)\" to user_timeline api$")
	public void creatingTheApi(String arg1,String arg2) throws Throwable {
		InputStream input = new FileInputStream("src/test/resources/config/config.properties");
		prop.load(input);
		
		consumerKey = prop.getProperty("consumerKey");
		consumerSecret = prop.getProperty("consumerSecret");
		accessToken = prop.getProperty("accessToken");
		secretToken = prop.getProperty("secretToken");
		
		 url = prop.getProperty("user_timeline")+arg1+"&count="+arg2;
		 
		 scenerio.write("User timeline URL: "+url);
	}

	@When("^Hit user_timeline api and get max \"([^\"]*)\" tweet among retrieved tweets$")
	public void hit_user_timeline(String tagValue) throws Throwable {	
		RequestSpecification httpRequest = RestAssured.given();
		RestAssured.defaultParser = Parser.JSON;
		httpRequest.auth().oauth(consumerKey,consumerSecret,accessToken,secretToken,OAuthSignature.HEADER);
		httpRequest.contentType(ContentType.JSON);
		response = httpRequest.get(url);
		maxRetweet = findMaxValue(response, tagValue);
	}
	
	@Then("^Verify the user_timeline api response$")
	public void verifyuser_timelineReponse() throws Throwable {		
		scenerio.write("Twitter ID for max retweets count: "+maxRetweet.get("id"));
		scenerio.write("Tweet Text: "+maxRetweet.get("text"));	
		scenerio.write("ReTweet Count: "+maxRetweet.get("retweet_count"));
	}

	private JSONObject findMaxValue(Response response, String tagValue) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONArray obj = (JSONArray) parser.parse(response.asString());
		
		for(int i =0; i<obj.size();i++)
		{
			jsonObject = (JSONObject) obj.get(i);
			retweetCount.put(jsonObject.get("id").toString(), (String) (jsonObject.get(tagValue)==null?"NULL":jsonObject.get(tagValue).toString()));
		}

		 Map.Entry<String, String> maxEntry = null;

		    for (Map.Entry<String, String> entry : retweetCount.entrySet()) {

		        if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue().toString()) > 0) {
		            maxEntry = entry;
		        }
		    }
		    
		    System.out.println("MaxValue of "+ tagValue + " is" + maxEntry.getValue());
		    System.out.println("The tweet object is \n" );
		    for(int i =0; i<obj.size();i++)
			{
				jsonObject = (JSONObject) obj.get(i);
				if(jsonObject.get("id").toString().equalsIgnoreCase(maxEntry.getKey()))
				{
				System.out.println(jsonObject.toString());
				break;
				}
			}
		    return jsonObject;
	}

	@Given("^pass \"([^\"]*)\" to users_show api$")
	public void creatingUSerShowApi(String arg1) throws Throwable {
		InputStream input = new FileInputStream("src/test/resources/config/config.properties");
		prop.load(input);
		
		consumerKey = prop.getProperty("consumerKey");
		consumerSecret = prop.getProperty("consumerSecret");
		accessToken = prop.getProperty("accessToken");
		secretToken = prop.getProperty("secretToken");
		
		 url = prop.getProperty("show_user")+arg1;
		 scenerio.write("User show URL: "+url);
	}

	@When("^Hit users_show api$")
	public void hit_usershow_Api() throws Throwable {		
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.auth().oauth(consumerKey,consumerSecret,accessToken,secretToken,OAuthSignature.HEADER);
		response = httpRequest.get(url);
		
		JsonPath x = new JsonPath(response.asString());
		 followers_count = x.get("followers_count");
		 friends_count = x.get("friends_count");
		 screen_name = x.get("screen_name");
	}
	
	@Then("^Verify the users_show api response$")
	public void verifyReponse() throws Throwable {	
		scenerio.write("Followers: "+followers_count);
		scenerio.write("Following: "+friends_count);
		scenerio.write("Sceeen Name: "+screen_name);
	}
	
	@Then("^The hashtags are following$")
	public void getHashtagWithCount () {
		
		for(String st : response.toString().split(" ")){
		    if(st.startsWith("@")){
		        System.out.println(st);
		    }
		}
		
	}


}
