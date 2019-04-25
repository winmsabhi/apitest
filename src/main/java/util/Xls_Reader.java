package util;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Xls_Reader {
	
	public String path;
	public FileInputStream fis = null;
	private HSSFWorkbook workbook = null;
	private HSSFSheet sheet = null;
	private HSSFRow row = null;
	private HSSFCell cell = null;
	
	public Xls_Reader(String path){
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new HSSFWorkbook();
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public int getRowCount(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1){
			return 0 ;
		}else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1 ;
			return number;
		}
	}
	
	public static String getLocatorType(String locatorName){
		return null;
	}
	
	public static String getLocatorValue(String locatorName){
		return null;
	}
}
