import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class work{
	public static void main(String[] arrgs) {
		try {
			FileInputStream fs = new FileInputStream(setting.main(null));
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Doctors");
			Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(14);
			Cell c = r.getCell(12);
			String input = c.getStringCellValue();
			String[] words = input.split(" ");
			String finale[] = new String[input.length()];
			System.out.println("Select Your Time");
			int i = 0;
			for (i = 0; i < words.length; i++) {
				finale[i] = words[i];
				
				System.out.println(i + 1 + "." + finale[i]);

			}
			System.out.println();
			
			for(int j = 0 ;  j< finale.length ; j++)
			{
				if(finale[j]==null) {
					finale[j] ="";
				}
				else {
				System.out.println(finale[j]);
				}
			}
			System.out.print(finale.length);
			
			

		} catch (Exception e) {
			System.out.print(e);

		}
		
	}
}