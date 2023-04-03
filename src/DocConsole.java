import java.io.FileInputStream;
import java.text.SimpleDateFormat;

import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DocConsole{
	public static void main(int index) {
		
		System.out.println("Welcome Doctor");
		System.out.println("Choose From Below Options");
		System.out.print("\n 1. View Your Patients \n 2. View Your Details \n 3. Change Password \n ");
		Scanner sc = new Scanner(System.in);
		int Choice = sc.nextInt();
		function fn = new function();
		if(Choice == 1)
		{
			fn.viewpatient(index);
		}
		else if(Choice == 2)
		{
			fn.viewdetails();
		}
		else if(Choice == 3)
		{
			fn.chngpass();
		}
		else {
			main(index);
		}
		
	}
	static String curtime() {
		String timeStamp = new SimpleDateFormat("HH.mm").format(new java.util.Date());

		return timeStamp;
	}
}


class function{
	void viewpatient(int index) {
		try {
			FileInputStream fs = new FileInputStream(setting.main(null));
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Doctors");
			Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(index);
			Cell c = r.getCell(8);
			if(c.getStringCellValue() != null) {
			String input = c.getStringCellValue();
			String[] words = input.split("\n");
			String finale[] = new String[input.length()]; 
			int i = 0;
			try {
			for (i = 0; i < 4; i++) {
				
					finale[i] = words[i];
					
					System.out.println(i+1  + " .  " + finale[i] );
				
				

			}
			}
			catch(Exception e ) {
				
			}
			System.out.println();
			
			}
			else {
				System.out.println("No patient");

			}
		}
		catch (Exception e ) {
			System.out.println("No patient \n" );
		}
	}
	void viewdetails() {
		
	}
	void chngpass() {
		
	}
	
	
	
	void f() {
		try  {
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			String file = setting.main(null);
			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Doctors");
			Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(0);
			Cell c = r.getCell(11);
			
		}
		catch (Exception e )
		{
			System.out.print(e);
			
		}
	}
}

