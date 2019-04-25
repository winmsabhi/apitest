package test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import commonFunctions.CommonMethods;
import commonFunctions.JsonCreator;
import commonFunctions.Util;

public class Twittter {
	WebDriver driver = null;
	JsonCreator jsonObject = null;
	
	@BeforeSuite
	public void setup() {
		driver = CommonMethods.launchBrowser("Windows", "Chrome");
		driver.get(Util.getConfigData("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		jsonObject = new JsonCreator();
		
	}
	
	@Test(description = "This method will fetch the maximum retry count")
	public void test() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int maxRetweet = 0;
		if (driver.getTitle().equalsIgnoreCase(
				"STeP-IN Forum (@stepin_forum) | Twitter")) {
			List<WebElement> tweets = CommonMethods.findElements("xpath",
					".//*[@id='stream-items-id']/li");
			while (tweets.size() < 50) {
				System.out.println("Total tweets on this page are : "
						+ tweets.size());
				js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				tweets = CommonMethods.findElements("xpath",
						".//*[@id='stream-items-id']/li");
			}
			System.out.println("Total tweets on this page are : "
					+ tweets.size());
			for (int i = 0; i < tweets.size(); i++) {
				WebElement element = tweets.get(i);
				int tweetCount = 0;
				element.getText().indexOf("Retweet");
				int startindex = element.getText().indexOf("Retweet");
				try {
					tweetCount = Integer.parseInt(element.getText()
							.substring(startindex + 8, startindex + 9).trim());
				} catch (Exception e) {
				}
				if (tweetCount > maxRetweet) {
					maxRetweet = tweetCount;
				}
			}
		}
		System.out.println("Max Retweet count is " + maxRetweet);
		jsonObject.addProperty("top_retweet_count", Integer.toString(maxRetweet));
	}
	
	@Test(description = "This will give users ")
	public void getUsers() {
		List<String> userInfo = null;
		List<WebElement> users = driver.findElements(By
				.xpath(".//*[@class='RelatedUsers-users']/div"));
		int counter = 0;
		JSONArray jArray = new JSONArray();
		if (users.size() > 0) {
			while (counter < 3) {
				String name = null;
				String handleName = null;
				WebElement element = users.get(counter);
				WebElement mouseHower = element.findElement(By
						.tagName("Strong"));
				name = element.findElement(By.tagName("Strong")).getText();
				handleName = "@"
						+ element.findElement(By.tagName("b")).getText();
				counter += 1;
				//jArray.pu
				System.out.println("Name : " + name);
				System.out.println("Handle Name : " + handleName);
				// Actions actions = new Actions(driver);
				// actions.moveToElement(mouseHower).build().perform();
			}
		}
	}
}
