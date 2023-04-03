import java.io.FileInputStream;
import java.sql.*;
import java.time.Duration;
import java.time.LocalTime;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DocTimeSet{
	static String file = setting.main(null);

	public static void main(String[] args) {
		//worktime();
		int index =18;
		freeworkslots(index);
		
	}
	 static void worktime(int index) {
		try {
			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Doctors");
			Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(index);
			Cell c = r.getCell(9);
			Time myTime;
			
			myTime = Time.valueOf(c.getStringCellValue());
			System.out.println(myTime);
			
			Cell k = r.getCell(11);
			Time myTime2 = Time.valueOf(k.getStringCellValue());
			System.out.println(myTime2);
			String startTime = c.getStringCellValue();
	        String endTime = k.getStringCellValue();
	        
	        LocalTime start = LocalTime.parse(startTime);
	        LocalTime end = LocalTime.parse(endTime);
	        
	        Duration duration = Duration.between(start, end);
	        long minutes = duration.toMinutes();
	        long m = 60;
	        System.out.println("Duration: " + minutes/m);

		}
		catch(Exception e ) {
			System.out.print(e);
		}	
	}
	 
	 static void freeworkslots(int index) {

			try {
				FileInputStream fs = new FileInputStream(file);
				Workbook WB = WorkbookFactory.create(fs);
				Sheet s = (Sheet) WB.getSheet("Doctors");
				Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(index);
				Cell c = r.getCell(12);
				System.out.print(c.getStringCellValue());
				
				
			}
			catch(Exception e) {
				
			}
			
		}
	 
	 static String alpha() {
		 
		return file;
		 
	 }
	 
}
