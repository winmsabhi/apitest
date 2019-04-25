package commonFunctions;

import java.util.ArrayList;

//import net.sourceforge.htmlunit.corejs.javascript.ast.ErrorCollector;

import java.util.List;


//import org.apache.commons.logging.Log;
import org.apache.poi.ss.formula.DataValidationEvaluator.OperatorEnum;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import util.Log;
import util.Xls_Reader;
import ErrorCollectors.ErrorCollector;

import com.gargoylesoftware.htmlunit.Cache;

public class CommonMethods {
	public static WebDriver driver;
	public final static String USERNAME = "charu37";
	public final static String AUTOMATE_KEY = "PKpe2CDFmbZiMG4Q9frw";
	public final static String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY
			+ "@hub.browserstack.com/wd/hub";
	public static java.net.URL browseStackURL;
	private static ArrayList<ITestContext> iTestContextsList;
	
	public static void contexts() {
		iTestContextsList = new ArrayList<ITestContext>();
	}
	
	public static ArrayList<ITestContext> getContexts() {
		return iTestContextsList;
	}
	
	public static void addContextsToList(ITestContext context) {
		iTestContextsList.add(context);
	}
	
	public static void scrollPageRight() {
		try {
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT)
					.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT)
					.perform();
			actions.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT)
					.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT)
					.perform();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void scrollPageLeft() {
		try {
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ARROW_LEFT)
					.sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ARROW_LEFT)
					.perform();
			actions.sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ARROW_LEFT)
					.sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ARROW_LEFT)
					.perform();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void scrollPageUp() {
		try {
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP)
					.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).perform();
			actions.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP)
					.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).perform();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void scrollPageDown() {
		try {
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
					.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
					.perform();
			actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
					.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
					.perform();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void refreshPage() {
		waitForXSeconds(2000);
		driver.navigate().refresh();
		waitForXSeconds(5000);
		waitForElementPresence("page_loading");
	}
	
	public static void DragWebElement(WebElement element) {
		Actions action = new Actions(driver);
		action.dragAndDropBy(element, 250, 0).build().perform();
	}
	
	public static void rightClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.contextClick(element).build().perform();
	}
	
	public static void doubleClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.doubleClick(element).build().perform();
	}
	
	public static void exitFilterBox() {
		Actions actions = new Actions(driver);
		actions.click().build().perform();
	}
	
	public static void exitFilterDropDown() {
		Actions actions = new Actions(driver);
		actions.moveByOffset(-100, 100).build().perform();
		actions.click().build().perform();
	}
	
	public static WebElement findElement(String locatorName) {
		String locatorType = Xls_Reader.getLocatorType(locatorName);
		String locatorValue = Xls_Reader.getLocatorValue(locatorName);
		WebElement element = null;
		if (locatorType.equalsIgnoreCase("xpath")) {
			try {
				element = driver.findElement(By.xpath(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose xpath is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose xpath is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("id")) {
			try {
				element = driver.findElement(By.id(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose id is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose xpidath is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("name")) {
			try {
				element = driver.findElement(By.name(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose name is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose name is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("linktest")) {
			try {
				element = driver.findElement(By.linkText(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose linktext is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose linktext is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("css")) {
			try {
				element = driver.findElement(By.cssSelector(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose css selector is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose css selector is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("tagname")) {
			try {
				element = driver.findElement(By.tagName(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose taganame is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose taganame is:"
								+ locatorValue);
			}
		} else {
			Log.error("Web driver failed to locate the element whose locator is :"
					+ locatorValue);
			ErrorCollector
					.VerifyFail("Web driver failed to locate the element whose locator is:"
							+ locatorValue);
		}
		return element;
	}
	
	public static WebElement findElement(String locatorType, String locatorValue) {
		WebElement element = null;
		if (locatorType.equalsIgnoreCase("xpath")) {
			try {
				element = driver.findElement(By.xpath(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose xpath is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose xpath is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("id")) {
			try {
				element = driver.findElement(By.id(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose id is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose xpidath is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("name")) {
			try {
				element = driver.findElement(By.name(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose name is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose name is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("linktest")) {
			try {
				element = driver.findElement(By.linkText(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose linktext is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose linktext is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("css")) {
			try {
				element = driver.findElement(By.cssSelector(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose css selector is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose css selector is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("tagname")) {
			try {
				element = driver.findElement(By.tagName(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose taganame is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose taganame is:"
								+ locatorValue);
			}
		} else {
			Log.error("Web driver failed to locate the element whose locator is :"
					+ locatorValue);
			ErrorCollector
					.VerifyFail("Web driver failed to locate the element whose locator is:"
							+ locatorValue);
		}
		return element;
	}
	
	public static List<WebElement> findElements(String locatorName) {
		String locatorType = Xls_Reader.getLocatorType(locatorName);
		String locatorValue = Xls_Reader.getLocatorValue(locatorName);
		List<WebElement> elements = null;
		if (locatorType.equalsIgnoreCase("xpath")) {
			try {
				elements = driver.findElements(By.xpath(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose xpath is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose xpath is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("id")) {
			try {
				elements = driver.findElements(By.id(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose id is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose xpidath is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("name")) {
			try {
				elements = driver.findElements(By.name(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose name is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose name is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("linktest")) {
			try {
				elements = driver.findElements(By.linkText(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose linktext is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose linktext is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("css")) {
			try {
				elements = driver.findElements(By.cssSelector(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose css selector is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose css selector is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("tagname")) {
			try {
				elements = driver.findElements(By.tagName(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose taganame is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose taganame is:"
								+ locatorValue);
			}
		} else {
			Log.error("Web driver failed to locate the element whose locator is :"
					+ locatorValue);
			ErrorCollector
					.VerifyFail("Web driver failed to locate the element whose locator is:"
							+ locatorValue);
		}
		return elements;
	}
	
	public static List<WebElement> findElements(String locatorType,
			String locatorValue) {
		List<WebElement> elements = null;
		if (locatorType.equalsIgnoreCase("xpath")) {
			try {
				elements = driver.findElements(By.xpath(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose xpath is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose xpath is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("id")) {
			try {
				elements = driver.findElements(By.id(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose id is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose xpidath is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("name")) {
			try {
				elements = driver.findElements(By.name(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose name is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose name is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("linktest")) {
			try {
				elements = driver.findElements(By.linkText(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose linktext is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose linktext is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("css")) {
			try {
				elements = driver.findElements(By.cssSelector(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose css selector is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose css selector is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("tagname")) {
			try {
				elements = driver.findElements(By.tagName(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose taganame is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose taganame is:"
								+ locatorValue);
			}
		} else {
			Log.error("Web driver failed to locate the element whose locator is :"
					+ locatorValue);
			ErrorCollector
					.VerifyFail("Web driver failed to locate the element whose locator is:"
							+ locatorValue);
		}
		return elements;
	}
	
	public static void clickElement(String locatorName) {
		String locatorType = Xls_Reader.getLocatorType(locatorName);
		String locatorValue = Xls_Reader.getLocatorValue(locatorName);
		if (locatorType.equalsIgnoreCase("xpath")) {
			try {
				driver.findElement(By.xpath(locatorValue)).click();
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose xpath is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose xpath is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("id")) {
			try {
				driver.findElement(By.id(locatorValue)).click();
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element whose id is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element whose id is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("name")) {
			try {
				driver.findElement(By.name(locatorValue)).click();
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element whose name is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element whose name is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("linktest")) {
			try {
				driver.findElement(By.linkText(locatorValue)).click();
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element  whose linktext is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element  whose linktext is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("css")) {
			try {
				driver.findElement(By.cssSelector(locatorValue)).click();
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element  whose css selector is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element  whose css selector is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("tagname")) {
			try {
				driver.findElement(By.tagName(locatorValue)).click();
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element  whose taganame is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element  whose taganame is:"
								+ locatorValue);
			}
		} else {
			Log.error("Web driver failed to click the element  whose locator is :"
					+ locatorValue);
			ErrorCollector
					.VerifyFail("Web driver failed to click the element  whose locator is:"
							+ locatorValue);
		}
	}
	
	public static void typeText(String locatorName, String inputText) {
		String locatorType = Xls_Reader.getLocatorType(locatorName);
		String locatorValue = Xls_Reader.getLocatorValue(locatorName);
		if (locatorType.equalsIgnoreCase("xpath")) {
			try {
				driver.findElement(By.xpath(locatorValue)).sendKeys(inputText);
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose xpath is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose xpath is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("id")) {
			try {
				driver.findElement(By.id(locatorValue)).sendKeys(inputText);
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element whose id is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element whose id is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("name")) {
			try {
				driver.findElement(By.name(locatorValue)).sendKeys(inputText);
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element whose name is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element whose name is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("linktest")) {
			try {
				driver.findElement(By.linkText(locatorValue)).sendKeys(
						inputText);
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element  whose linktext is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element  whose linktext is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("css")) {
			try {
				driver.findElement(By.cssSelector(locatorValue)).sendKeys(
						inputText);
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element  whose css selector is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element  whose css selector is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("tagname")) {
			try {
				driver.findElement(By.tagName(locatorValue))
						.sendKeys(inputText);
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element  whose taganame is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element  whose taganame is:"
								+ locatorValue);
			}
		} else {
			Log.error("Web driver failed to click the element  whose locator is :"
					+ locatorValue);
			ErrorCollector
					.VerifyFail("Web driver failed to click the element  whose locator is:"
							+ locatorValue);
		}
	}
	
	public static void selectDropDownValue(String locatorName, String searchText) {
		String locatorType = Xls_Reader.getLocatorType(locatorName);
		String locatorValue = Xls_Reader.getLocatorValue(locatorName);
		if (locatorType.equalsIgnoreCase("xpath")) {
			try {
				Select dropDown = new Select(driver.findElement(By
						.xpath(locatorValue)));
				dropDown.selectByVisibleText(searchText);
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose xpath is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose xpath is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("id")) {
			try {
				Select dropDown = new Select(driver.findElement(By
						.id(locatorValue)));
				dropDown.selectByVisibleText(searchText);
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element whose id is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element whose id is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("name")) {
			try {
				Select dropDown = new Select(driver.findElement(By
						.name(locatorValue)));
				dropDown.selectByVisibleText(searchText);
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element whose name is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element whose name is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("linktest")) {
			try {
				Select dropDown = new Select(driver.findElement(By
						.linkText(locatorValue)));
				dropDown.selectByVisibleText(searchText);
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element  whose linktext is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element  whose linktext is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("css")) {
			try {
				Select dropDown = new Select(driver.findElement(By
						.cssSelector(locatorValue)));
				dropDown.selectByVisibleText(searchText);
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element  whose css selector is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element  whose css selector is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("tagname")) {
			try {
				Select dropDown = new Select(driver.findElement(By
						.tagName(locatorValue)));
				dropDown.selectByVisibleText(searchText);
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element  whose taganame is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element  whose taganame is:"
								+ locatorValue);
			}
		} else {
			Log.error("Web driver failed to click the element  whose locator is :"
					+ locatorValue);
			ErrorCollector
					.VerifyFail("Web driver failed to click the element  whose locator is:"
							+ locatorValue);
		}
	}
	
	public static boolean isElementPresent(String locatorName) {
		String locatorType = Xls_Reader.getLocatorType(locatorName);
		String locatorValue = Xls_Reader.getLocatorValue(locatorName);
		boolean flag = false;
		if (locatorType.equalsIgnoreCase("xpath")) {
			try {
				if (driver.findElement(By.xpath(locatorValue)).isDisplayed()) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose xpath is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose xpath is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("id")) {
			try {
				if (driver.findElement(By.id(locatorValue)).isDisplayed()) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element whose id is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element whose id is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("name")) {
			try {
				if (driver.findElement(By.name(locatorValue)).isDisplayed()) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element whose name is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element whose name is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("linktest")) {
			try {
				if (driver.findElement(By.linkText(locatorValue)).isDisplayed()) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element  whose linktext is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element  whose linktext is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("css")) {
			try {
				if (driver.findElement(By.cssSelector(locatorValue))
						.isDisplayed()) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element  whose css selector is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element  whose css selector is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("tagname")) {
			try {
				if (driver.findElement(By.tagName(locatorValue)).isDisplayed()) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element  whose taganame is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element  whose taganame is:"
								+ locatorValue);
			}
		} else {
			Log.error("Web driver failed to click the element  whose locator is :"
					+ locatorValue);
			ErrorCollector
					.VerifyFail("Web driver failed to click the element  whose locator is:"
							+ locatorValue);
			flag = false;
		}
		return flag;
	}
	
	public static boolean isElementPresent(String locatorType,
			String locatorValue) {
		boolean flag = false;
		if (locatorType.equalsIgnoreCase("xpath")) {
			try {
				if (driver.findElement(By.xpath(locatorValue)).isDisplayed()) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose xpath is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose xpath is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("id")) {
			try {
				if (driver.findElement(By.id(locatorValue)).isDisplayed()) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element whose id is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element whose id is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("name")) {
			try {
				if (driver.findElement(By.name(locatorValue)).isDisplayed()) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element whose name is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element whose name is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("linktest")) {
			try {
				if (driver.findElement(By.linkText(locatorValue)).isDisplayed()) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element  whose linktext is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element  whose linktext is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("css")) {
			try {
				if (driver.findElement(By.cssSelector(locatorValue))
						.isDisplayed()) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element  whose css selector is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element  whose css selector is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("tagname")) {
			try {
				if (driver.findElement(By.tagName(locatorValue)).isDisplayed()) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to click the element  whose taganame is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to click the element  whose taganame is:"
								+ locatorValue);
			}
		} else {
			Log.error("Web driver failed to click the element  whose locator is :"
					+ locatorValue);
			ErrorCollector
					.VerifyFail("Web driver failed to click the element  whose locator is:"
							+ locatorValue);
			flag = false;
		}
		return flag;
	}
	
	public static void waitForXSeconds(int timeinMillis) {
		long timeNow = System.currentTimeMillis();
		long expectedWait = timeNow + timeinMillis;
		while (expectedWait > timeNow) {
			timeNow = System.currentTimeMillis();
		}
	}
	
	public static void waitForElementPresence(String locatorName) {
		String locatorType = Xls_Reader.getLocatorType(locatorName);
		String locatorValue = Xls_Reader.getLocatorValue(locatorName);
		WebDriverWait wait = new WebDriverWait(driver, 120);
		if (locatorType.toUpperCase().equals("CSS")) {
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By
						.cssSelector(locatorValue)));
			} catch (Throwable t) {
				Log.error(locatorName + ": was not present");
				ErrorCollector.VerifyFail(locatorName + ": was not present");
			}
		} else if (locatorType.toUpperCase().equals("XPATH")) {
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By
						.xpath(locatorValue)));
			} catch (Throwable t) {
				Log.error(locatorName + ": was not present");
				ErrorCollector.VerifyFail(locatorName + ": was not present");
			}
		} else if (locatorType.toUpperCase().equals("LINKTEXT")) {
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By
						.linkText(locatorValue)));
			} catch (Throwable t) {
				Log.error(locatorName + ": was not present");
				ErrorCollector.VerifyFail(locatorName + ": was not present");
			}
		}
	}
	
	public static void waitTillElementVisble(String locatorName) {
		String locatorType = Xls_Reader.getLocatorType(locatorName);
		String locatorValue = Xls_Reader.getLocatorValue(locatorName);
		WebDriverWait wait = new WebDriverWait(driver, 120);
		if (locatorType.toUpperCase().equals("CSS")) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By
						.cssSelector(locatorValue)));
			} catch (Throwable t) {
				Log.error(locatorName + ": was not present");
				ErrorCollector.VerifyFail(locatorName + ": was not present");
			}
		} else if (locatorType.toUpperCase().equals("XPATH")) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath(locatorValue)));
			} catch (Throwable t) {
				Log.error(locatorName + ": was not present");
				ErrorCollector.VerifyFail(locatorName + ": was not present");
			}
		} else if (locatorType.toUpperCase().equals("LINKTEXT")) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By
						.linkText(locatorValue)));
			} catch (Throwable t) {
				Log.error(locatorName + ": was not present");
				ErrorCollector.VerifyFail(locatorName + ": was not present");
			}
		}
	}
	
	public static void waitTillElementVisble(String locatorType,
			String locatorValue) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		if (locatorType.toUpperCase().equals("CSS")) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By
						.cssSelector(locatorValue)));
			} catch (Throwable t) {
				Log.error(locatorValue + ": was not present");
				ErrorCollector.VerifyFail(locatorValue + ": was not present");
			}
		} else if (locatorType.toUpperCase().equals("XPATH")) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath(locatorValue)));
			} catch (Throwable t) {
				Log.error(locatorValue + ": was not present");
				ErrorCollector.VerifyFail(locatorValue + ": was not present");
			}
		} else if (locatorType.toUpperCase().equals("LINKTEXT")) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By
						.linkText(locatorValue)));
			} catch (Throwable t) {
				Log.error(locatorValue + ": was not present");
				ErrorCollector.VerifyFail(locatorValue + ": was not present");
			}
		}
	}
	
	public static WebDriver launchBrowser() {
		DesiredCapabilities capabilities = null;
		String browserType = Util.getConfigData("browser");
		try {
			if (browserType.toUpperCase().equals("CHROME")) {
				System.out.println("chrome");
				capabilities = DesiredCapabilities.chrome();
				capabilities.setBrowserName("chrome");
				capabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir")
								+ "//drivers//chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				capabilities
						.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
				driver = new ChromeDriver(capabilities);
			} else if (browserType.toUpperCase().equals("IE")) {
				System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
				DesiredCapabilities cap = DesiredCapabilities
						.internetExplorer();
				cap.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				driver = new InternetExplorerDriver(cap);
			} else if (browserType.toUpperCase().equals("FIREFOX")) {
				driver = new FirefoxDriver();
			} else {
				// Log.error("Browser not valid : " + browserType);
			}
		} catch (Throwable t) {
			// TODO: handle exception
			// Log.error("Unable to launch browser :" + browserType);
			// ErrorCollector.VerifyFail("unable to launch browser :" +
			// browserType);
		}
		return driver;
	}
	
	public static void clearEditBox(String locatorName) {
		String locatorType = Xls_Reader.getLocatorType(locatorName);
		String locatorValue = Xls_Reader.getLocatorValue(locatorName);
		WebElement element = null;
		if (locatorType.equalsIgnoreCase("xpath")) {
			try {
				driver.findElement(By.xpath(locatorValue)).clear();
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose xpath is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose xpath is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("id")) {
			try {
				driver.findElement(By.id(locatorValue)).clear();
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose id is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose xpidath is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("name")) {
			try {
				driver.findElement(By.name(locatorValue)).clear();
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose name is:"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose name is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("linktest")) {
			try {
				driver.findElement(By.linkText(locatorValue)).clear();
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose linktext is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose linktext is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("css")) {
			try {
				driver.findElement(By.cssSelector(locatorValue)).clear();
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose css selector is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose css selector is:"
								+ locatorValue);
			}
		} else if (locatorType.equalsIgnoreCase("tagname")) {
			try {
				driver.findElement(By.tagName(locatorValue)).clear();
			} catch (Throwable e) {
				// TODO: handle exception
				Log.error("Web driver failed to locate the element whose taganame is :"
						+ locatorValue);
				ErrorCollector
						.VerifyFail("Web driver failed to locate the element whose taganame is:"
								+ locatorValue);
			}
		} else {
			Log.error("Web driver failed to locate the element whose locator is :"
					+ locatorValue);
			ErrorCollector
					.VerifyFail("Web driver failed to locate the element whose locator is:"
							+ locatorValue);
		}
	}
	
	public static WebDriver launchBrowser(String Platform, String Browser) {
		DesiredCapabilities caps = new DesiredCapabilities();
		//String browserType = Util.getConfigData("browser");
		try {
			caps.setCapability("browserstack.local", "false");
			switch (Browser) {
			case "Chrome":
				caps.setCapability("browser", "Chrome");
				caps.setCapability("browser_version", "62.0");
				break;
			case "Edge":
				caps.setCapability("browser", "Edge");
				caps.setCapability("browser_version", "18.0");
				break;
			case "IE":
				caps.setCapability("browser", "IE");
				caps.setCapability("browser_version", "11.0");
				break;
			default:
				caps.setCapability("browser", "Chrome");
				caps.setCapability("browser_version", "62.0");
				break;
			}
			switch (Platform) {
			case "Windows":
				caps.setCapability("os_version", "10");
				break;
			case "Android":
				caps.setCapability("os_version", "7.0");
				caps.setCapability("device", "Samsung Galaxy S8");
				caps.setCapability("real_mobile", "true");
				caps.setCapability("browserstack.local", "false");
				break;
			default:
				caps.setCapability("os_version", "10");
				break;
			}
			browseStackURL = new java.net.URL(URL);
		} catch (Throwable t) {
			// TODO: handle exception
			// Log.error("Unable to launch browser :" + browserType);
			// ErrorCollector.VerifyFail("unable to launch browser :" +
			// browserType);
		}
		return new RemoteWebDriver(browseStackURL, caps);
	}
}
