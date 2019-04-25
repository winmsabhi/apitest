package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import commonFunctions.CommonMethods;
import commonFunctions.Util;




public class LaunchTest {
	
	@Test
	public void test(){
		WebDriver driver = CommonMethods.launchBrowser();
		driver.get(Util.getConfigData("url"));
	}
}
