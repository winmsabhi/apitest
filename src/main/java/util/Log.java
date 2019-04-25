package util;

import org.apache.log4j.Logger;

public class Log {
	
	private static Logger Log = Logger.getLogger(Logger.class.getName());
	public static void error(String message){
		Log.error(message);
	}
}
