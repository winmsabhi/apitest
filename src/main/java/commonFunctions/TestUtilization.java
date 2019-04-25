package commonFunctions;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TestUtilization extends CommonMethods{
	
	public static String mailscreenshotpath;
	
	public static String captureScreenShot(){
		Calendar calendar = new GregorianCalendar();
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		int sec = calendar.get(Calendar.SECOND);
		int min = calendar.get(Calendar.MINUTE);
		int date = calendar.get(Calendar.DATE);
		int day = calendar.get(Calendar.HOUR_OF_DAY);
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			mailscreenshotpath = System.getProperty("user.dir")  
					+ "/test-output/Screenshots" + year+ "_"+ (month+1) +"_" + day+"_"+ min + "_" + sec+".jpeg"; 
			FileUtils.copyFile(srcFile, new File(mailscreenshotpath));		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return mailscreenshotpath;
	}
}
