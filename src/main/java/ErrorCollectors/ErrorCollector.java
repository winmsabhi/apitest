package ErrorCollectors;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import util.Log;
import commonFunctions.TestUtilization;

public class ErrorCollector {
	private static Map<ITestResult, List<Throwable>> verificationFailuresMap = new HashMap<ITestResult, List<Throwable>>();
	private static Map<ITestResult, List<String>> snapshotMap = new HashMap<ITestResult, List<String>>();
	
	public static Map<ITestResult, List<String>> getSnapshotMap() {
		return snapshotMap;
	}
	
	public static void assertTrue(boolean condition) {
		Assert.assertTrue(condition);
	}
	
	public static void assertTrue(boolean condition, String message) {
		Assert.assertTrue(condition, message);
	}
	
	public static void assertFalse(boolean condition) {
		Assert.assertFalse(condition);
	}
	
	public static void assertFalse(boolean condition, String message) {
		Assert.assertFalse(condition, message);
	}
	
	public static void assertEquals(boolean actual, boolean expected) {
		Assert.assertEquals(actual, expected);
	}
	
	public static void assertEquals(String actual, String expected) {
		Assert.assertEquals(actual, expected);
	}
	
	public static void assertEquals(Object actual, Object expected) {
		Assert.assertEquals(actual, expected);
	}
	
	public static void assertEquals(Object[] actual, Object[] expected) {
		Assert.assertEquals(actual, expected);
	}
	
	public static void assertEquals(Object actual, Object expected,
			String message) {
		Assert.assertEquals(actual, expected, message);
	}
	
	public static void VerifyTrue(boolean condition) {
		try {
			assertTrue(condition);
		} catch (Exception e) {
			// TODO: handle exception
			String path = TestUtilization.captureScreenShot();
			addVerificationFailure(e, path);
		}
	}
	
	public static void VerifyFalse(boolean condition) {
		try {
			assertFalse(condition);
		} catch (Exception e) {
			// TODO: handle exception
			String path = TestUtilization.captureScreenShot();
			addVerificationFailure(e, path);
		}
	}
	
	public static void VerifyFalse(boolean condition, String message) {
		try {
			assertFalse(condition, message);
		} catch (Exception e) {
			// TODO: handle exception
			String path = TestUtilization.captureScreenShot();
			addVerificationFailure(e, path);
		}
	}
	
	public static void verifyEquals(boolean actual, boolean expected) {
		try {
			assertEquals(actual, expected);
		} catch (Exception e) {
			// TODO: handle exception
			String path = TestUtilization.captureScreenShot();
			addVerificationFailure(e, path);
		}
	}
	
	public static void verifyEquals(String actual, String expected) {
		try {
			assertEquals(actual, expected);
		} catch (Exception e) {
			// TODO: handle exception
			String path = TestUtilization.captureScreenShot();
			addVerificationFailure(e, path);
		}
	}
	
	public static void verifyEquals(Object actual, Object expected) {
		try {
			assertEquals(actual, expected);
		} catch (Exception e) {
			// TODO: handle exception
			String path = TestUtilization.captureScreenShot();
			addVerificationFailure(e, path);
		}
	}
	
	public static void verifyEquals(Object[] actual, Object[] expected) {
		try {
			assertEquals(actual, expected);
		} catch (Exception e) {
			// TODO: handle exception
			String path = TestUtilization.captureScreenShot();
			addVerificationFailure(e, path);
		}
	}
	
	public static void fail(String message) {
		String path = TestUtilization.captureScreenShot();
		Assert.fail(message + " Screenshot path :" + path);
	}
	
	public static void addVerificationFailure(Throwable e, String path) {
		List<Throwable> verificationFailures = geteVerificationFailures();
		List<String> snapshotPathList = getSnapshotList();
		verificationFailuresMap.put(Reporter.getCurrentTestResult(),
				verificationFailures);
		snapshotMap.put(Reporter.getCurrentTestResult(), snapshotPathList);
		verificationFailures.add(e);
		snapshotPathList.add(path);
	}
	
	public static List<Throwable> geteVerificationFailures() {
		List<Throwable> verificationFailures = verificationFailuresMap
				.get(Reporter.getCurrentTestResult());
		return verificationFailures == null ? new ArrayList<Throwable>()
				: verificationFailures;
	}
	
	public static List<String> getSnapshotList() {
		List<String> snapshotPathList = snapshotMap.get(Reporter
				.getCurrentTestResult());
		return snapshotPathList == null ? new ArrayList<String>()
				: snapshotPathList;
	}
	
	public static void VerifyFail(String message) {
		String path = null;
		try {
			Log.error(message);
			path = TestUtilization.captureScreenShot();
			Assert.fail(message + " Screenshot path :" + path);
		} catch (Exception e) {
			// TODO: handle exception
			addVerificationFailure(e, path);
		}
		
	}
}
