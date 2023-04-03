import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class doctor{
	public static void main(String[] arrgs)
	{
		String file = GlobalString.file;
		
		try {
			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Admin");
			Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(2);
			System.out.print("Enter DocAccess Passwd");
			

			Scanner sc = new Scanner(System.in);
			String pass = sc.next();
			Cell c = r.getCell(1);
			
			if(pass.equals(c.getStringCellValue())) {
				doclogn.login(file);
				
			}
		}
		catch (Exception e ) {
			System.out.print("Wrong Pass");
			}
	}
		
}
class docfn{
	
}

class timeupdate{
	static String ctime(){
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss ").format(new java.util.Date());

		return timeStamp;
	}
}
class doclogn {
	static void login(String file){
		System.out.println("Enter Your Room Number");
		try {
			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Doctors");
			Scanner sc = new Scanner(System.in);
			int pass = sc.nextInt();
			System.out.println("Enter Your Password");
			String thispass= sc.next();
			Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(pass);
			Cell c = r.getCell(10);
			String v = c.getStringCellValue();
			try {
			if(v.equals(thispass)) {
				String opass = "TempPass";
				opass = opass.concat(String.valueOf(pass));
				
				if(opass.equals(thispass)) {
					
					passchng.chng(file , pass);
					
				}
				
					else {
						System.out.println("Wrong Pass");

					}
				
				
			}
			
			else if(thispass.equals(Decrypt.main(v))) {
				
				
				
					fn.welcome(pass);			
				

				}
			

			else {
				System.out.println("Wrong Pass");

			}
			}
			catch (Exception e) {
				
			}
			

		
		}
		catch(Exception e ) {
			
		}
}

}

class passchng {
	static void chng(String file , int pass ) {
		try {
		FileInputStream fs = new FileInputStream(file);
		Workbook WB = WorkbookFactory.create(fs);
		Sheet s = (Sheet) WB.getSheet("Doctors");
		Scanner sc = new Scanner(System.in);
		
		Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(pass);
		Cell c = r.getCell(10);
		System.out.println("Create a Password");
		String Password = sc.next();
		System.out.println("Verify the Password");
		String VPassword = sc.next();
		if(Password.equals(VPassword)) {
			String i = Encryption.main(Password);
	
				Cell mo = r.getCell(10);
				mo.setCellValue(i);
		 		FileOutputStream fos = new FileOutputStream(file);
		 		WB.write(fos);
			
			}
		else {
			System.out.println("Password Doesnt Match");
			chng(file , pass);
		}
		}
		
		catch(Exception e1 ) {
			System.out.println(e1);
		}
		//DocUserAcc
		
		
	}
}


class fn {
	static void welcome(int index) {
		System.out.print("Welcome");
		DocConsole.main(index);
	}
	void DetailConsole(int RoomNum , String file) {
		System.out.println("Welcome" );
		try {
			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Doctors");
			Scanner sc = new Scanner(System.in);
			
			Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(RoomNum);
			Cell c = r.getCell(10);
		}
		catch (Exception e)
		{
			
		}
	}
	
}
