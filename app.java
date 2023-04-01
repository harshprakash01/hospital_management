import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Formatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class app{
	public static void main(String[] arrgs)
	{
		String file = "/Users/xtemper/eclipse-workspace/Hospital-Management-App/Hospital.xlsx";

		patient PP = new patient();
		
		System.out.println("Login or sign up " + "\nPress 1 for Login" + "\nPress Any Key to Sign up\n");
		Scanner sc = new Scanner(System.in);
		String p = sc.next();
		
		try {
			
			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Admin");
			Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(1);
			Cell c = r.getCell(1);
			Cell f = r.getCell(2);
			if(p.equals(c.getStringCellValue())) {
				System.out.println("Welcome Doctor");
				doctor.main(null);
				
			}
			else if (p.equals(f.getStringCellValue())) {
				System.out.println("Welcome Admin");
			}
			else if(p.equals("1")) {
				PP.login();
			}
			else {
			PP.signup();
			}
			
		}
		catch (Exception e ) {	
			System.out.print(e);
		}
		
		
	}
}

class patient {
	docChoose Dc = new docChoose();
	String file = "/Users/xtemper/eclipse-workspace/Hospital-Management-App/Hospital.xlsx";
	private static final SecureRandom secureRandom = new SecureRandom(); 
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
	private String PatientFName;
	private String PatientLName;
	private String PatientEmail;
	private long  PatientPhone;
	private int PatientProb;
	private String PatientDocAss;
	private String Token;
	
	
	public void setPatientFName(String patientFName) {
        PatientFName = patientFName;
    }
	
	EmptyEntry EE = new EmptyEntry();
	EmptyEntryPatient EEP = new EmptyEntryPatient(); 
	private int LastEmpty = EE.empty();
	
	
	void docname(int sno) {
		
		try {
			
			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Doctors");
			try {
				for (int i = 0 ; i < LastEmpty ; i ++ ) {
					
					Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(i);
					Cell c = r.getCell(0);
					int temp = (int)c.getNumericCellValue();
					if(temp == sno) {
						Cell n = r.getCell(0);
						
						PatientDocAss = n.getStringCellValue();
					}
				}
			}	
			catch (Exception e ) {
				
			}
			
		}
		catch (Exception e ) {
			
		}
		
	}
	
	
	void login() {
		int index = 0 ; 
		String file = "/Users/xtemper/eclipse-workspace/Hospital-Management-App/Hospital.xlsx";
		int z = 0 ;
		System.out.println("enter token number");
		Scanner sc = new Scanner(System.in);
		String Token1 = sc.next();
		try {
			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Patients");
			
			try {
			for (int i = 0 ; i < EEP.empty() ; i ++ ) {
				
				Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(i);
				Cell c = r.getCell(1);
				String temp = c.getStringCellValue();
				

				if(temp.equals(Token1)) {
					
					try {
					Cell m[] = {r.getCell(2),r.getCell(3),r.getCell(4),r.getCell(5),r.getCell(6),r.getCell(7),r.getCell(8)};
					
					z = 100 ;
					
				    String name = m[0].getStringCellValue();
					System.out.println("Welcome " + name + " " +m[1].getStringCellValue());
					System.out.println("Email :\t" + m[2].getStringCellValue());
					System.out.println("Phone Number :\t" + (long)m[3].getNumericCellValue());
					System.out.println("Problem :\t" + m[4].getStringCellValue());
					System.out.println("Registerd Doc :\t" + m[5].getStringCellValue());
					i = 100;
					
					}
					catch (Exception e ) {
						System.out.print(e);
					}
					
				}
				
			
			}
			if(z == 100)
			{
				
			}
			else {
				System.out.print("Wrong Token \n");
				
				app.main(null);
		}
			}			catch (Exception e ) {
				
			}	
		
		}
		catch (Exception e ) {
			System.out.println(e);
		}
		
		}
	
	public void email() {
		Scanner sc = new Scanner(System.in);
		System.out.println(" Enter Your Email ID \n ");
		PatientEmail = sc.next();
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
		 Pattern pat = Pattern.compile(emailRegex);
		 if(pat.matcher(PatientEmail).matches() == false) {
			 System.out.println("Wrong Email ID");
				email();
			}
		 else {
			 
		 }
		 
		
	}
	public void phone() {
		Scanner sc = new Scanner(System.in);

		System.out.println(" Enter Your Phone Number \n ");
		PatientPhone = sc.nextLong();
		Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");  
		String str = String.valueOf(PatientPhone);
		Matcher match = ptrn.matcher(str);  
		if(match.find() && match.group().equals(str) == false) {
			 System.out.println("Wrong Phone Number");
			 phone();
		}
	}
	public void signup(){
		System.out.println("Welcome Patient \nFill in the following details to get started \n Enter Your First Name \n ");
		Scanner sc = new Scanner(System.in);
		
		String name = sc.next();
		char k[] = name.toCharArray();
		Character.toUpperCase(k[0]);
		name = new String(k);
		setPatientFName(name);
		System.out.println(" Enter Your Last Name \n ");
		PatientLName = sc.next();
		char k1[] = PatientLName.toCharArray();
		PatientLName = new String(k1);
		
		
		email();
		
		phone();
		
		System.out.println(" Choose Your Specialization \n 1. Orthopedics\n" + " 2. Internal Medicine\n" + " 3. Obstetrics\n" + " 4. Dermatology\n" + " 5. Pediatrics\n" + " 6. Radiology\n" + " 7. General Surgery\n" + " 8. Ophthalmology" + " 9. Gynecology\n");
		PatientProb = sc.nextInt();
		
		
		docChoosed(PatientProb);
		
		
	}
	void docChoosed(int m ){
		docChoose dc = new docChoose();
		WritePatient Wp = new WritePatient();
		getdoc gdc = new getdoc();
		
		if(m == 1)
		{
			

			dc.Choosed("Orthopedics");
			
			Scanner sc = new Scanner(System.in);
						
			 m = sc.nextInt();
			 docname(m);
			 Wp.WriteFName(PatientFName);
			 Wp.WriteDoc(PatientDocAss);
			 Wp.WriteEmail(PatientEmail);
			 Wp.WriteLName(PatientLName);
			 Wp.WritePn(PatientPhone);
			 Wp.WriteProb("Orthopedics");
			 Wp.WriteDoc(gdc.docname(m));
			 tokenCreate();
			 Wp.WriteToken(Token);
			 login();
				
		}
		else if(m ==2 )
		{
			
			dc.Choosed("Internal Medicine");
			Scanner sc = new Scanner(System.in);
			
			
			 m = sc.nextInt();
			 docname(m);
			 Wp.WriteFName(PatientFName);
			 Wp.WriteDoc(PatientDocAss);
			 Wp.WriteEmail(PatientEmail);
			 Wp.WriteLName(PatientLName);
			 Wp.WritePn(PatientPhone);
			 Wp.WriteDoc(gdc.docname(m));
			 Wp.WriteProb("Internal Medicine");
			 
			 tokenCreate();
			 Wp.WriteToken(Token);
			 login();
		}
		else if(m == 3)
		{
			dc.Choosed("Obstetrics");
			Scanner sc = new Scanner(System.in);
			 m = sc.nextInt();
			 docname(m);
			 Wp.WriteDoc(gdc.docname(m));
			 Wp.WriteFName(PatientFName);
			 Wp.WriteDoc(PatientDocAss);
			 Wp.WriteEmail(PatientEmail);
			 Wp.WriteLName(PatientLName);
			 Wp.WritePn(PatientPhone);
			 Wp.WriteProb("Obstetrics");
			 
			 tokenCreate();
			 Wp.WriteToken(Token);
			 login();
		}
		else if(m == 4 )
		{
			dc.Choosed("Dermatology");
			Scanner sc = new Scanner(System.in);

			 m = sc.nextInt();
			 Wp.WriteDoc(gdc.docname(m));
			 docname(m);
			 Wp.WriteFName(PatientFName);
			 Wp.WriteDoc(PatientDocAss);
			 Wp.WriteEmail(PatientEmail);
			 Wp.WriteLName(PatientLName);
			 Wp.WritePn(PatientPhone);
			 Wp.WriteProb("Dermatology");
			 
			 tokenCreate();
			 Wp.WriteToken(Token);
			 login();
		}
		else if(m == 5)
		{
			 Scanner sc = new Scanner(System.in);
			 m = sc.nextInt();
			 docname(m);
			 Wp.WriteDoc(gdc.docname(m));
			 Wp.WriteFName(PatientFName);
			 Wp.WriteDoc(PatientDocAss);
			 Wp.WriteEmail(PatientEmail);
			 Wp.WriteLName(PatientLName);
			 Wp.WritePn(PatientPhone);
			 Wp.WriteProb("Pediatrics");
			 
			 tokenCreate();
			 Wp.WriteToken(Token);
			 login();
		}
		else if(m ==6 )
		{
			dc.Choosed("Radiology");
			Scanner sc = new Scanner(System.in);

			 m = sc.nextInt();
			 docname(m);
			 Wp.WriteDoc(gdc.docname(m));
			 Wp.WriteFName(PatientFName);
			 Wp.WriteDoc(PatientDocAss);
			 Wp.WriteEmail(PatientEmail);
			 Wp.WriteLName(PatientLName);
			 Wp.WritePn(PatientPhone);
			 Wp.WriteProb("Radiology");
			 
			 tokenCreate();
			 Wp.WriteToken(Token);
			 login();
		}
		else if(m == 7)
		{
			dc.Choosed("General Surgery");
			Scanner sc = new Scanner(System.in);
			

			 m = sc.nextInt();
			 docname(m);
			 Wp.WriteDoc(gdc.docname(m));
			 Wp.WriteFName(PatientFName);
			 Wp.WriteDoc(PatientDocAss);
			 Wp.WriteEmail(PatientEmail);
			 Wp.WriteLName(PatientLName);
			 Wp.WritePn(PatientPhone);
			 Wp.WriteProb("General Surgery");
			 
			 tokenCreate();
			 Wp.WriteToken(Token);
			 login();
		}
		else if(m == 8)
		{
			Scanner sc = new Scanner(System.in);
			
			dc.Choosed("Ophthalmology");
			 m = sc.nextInt();
			 docname(m);
			 Wp.WriteDoc(gdc.docname(m));
			 Wp.WriteFName(PatientFName);
			 Wp.WriteDoc(PatientDocAss);
			 Wp.WriteEmail(PatientEmail);
			 Wp.WriteLName(PatientLName);
			 Wp.WritePn(PatientPhone);
			 Wp.WriteProb("Internal Medicine");
			 
			 tokenCreate();
			 Wp.WriteToken(Token);
			 login();
		}
		else if(m==9) {
			Scanner sc = new Scanner(System.in);
			
			dc.Choosed("Gynecology");

			 m = sc.nextInt();
			 docname(m);
			 Wp.WriteDoc(gdc.docname(m));
			 Wp.WriteFName(PatientFName);
			 Wp.WriteDoc(PatientDocAss);
			 Wp.WriteEmail(PatientEmail);
			 Wp.WriteLName(PatientLName);
			 Wp.WritePn(PatientPhone);
			 Wp.WriteProb("Internal Medicine");
			 
			 tokenCreate();
			 Wp.WriteToken(Token);
			 login();
		}
		else {
			System.out.print("Enter a valid number");
			docChoosed(PatientProb)		;	
		}
		
		
		
		
	}
	void WriteValue(String Token) {
		
	}
	String tokenCreate() {
		byte[] randomBytes = new byte[24];
	    secureRandom.nextBytes(randomBytes);
	    Token = base64Encoder.encodeToString(randomBytes);
	    System.out.println("Your Token Is" +  base64Encoder.encodeToString(randomBytes));

	    return base64Encoder.encodeToString(randomBytes);
	}
	
	public String getToken() {
        return Token;
    }
	public String getPatientFName() {
        return PatientFName;
    }
    public String getPatientLName() {
        return PatientLName;
    }
    public String getPatientEmail() {
        return PatientEmail;
    }
    public long getPatientPhone() {
        return PatientPhone;
    }
    public int getPatientProb() {
        return PatientProb;
    }
    public String getPatientDocAss() {
        return PatientDocAss;
    }
    public void setPatientDocAss(String patientDocAss) {
        PatientDocAss = patientDocAss;
    }
	
}


class time{
	static String curtime(){
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss ").format(new java.util.Date());

		return timeStamp;
	}
}

class EmptyEntry {
	private String file = "/Users/xtemper/eclipse-workspace/Hospital-Management-App/Hospital.xlsx";
	int empty() {
		int res =0 ;
		try {
			

			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Doctors");
			for (int i = 0 ; i < 99 ; i++)
			{
				Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(i);
				Cell c = r.getCell(1);
				String v = c.getStringCellValue();
				if (v != null) {
					res++;
				 }
				else {
					i = 100;
				}
				
				
				
			}
			
		}
		catch (Exception e )
		{
			
		}
		return res;
	}
}

class docChoose{
	String file = "/Users/xtemper/eclipse-workspace/Hospital-Management-App/Hospital.xlsx";
	void Choosed(String user)
	{
		EmptyEntry ee = new EmptyEntry(); 
		try {
			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Doctors");
			//System.out.println("S.NO.\t\tName\t\tExperience\t\tTime");
			Formatter fmt = new Formatter();  
	        System.out.println("--------------------------------------------------------------");

			System.out.printf("%5s %20s\t\t%s %7s", "S.No.", "Name", "Exp(Year)", "Time\n");
	        System.out.println("--------------------------------------------------------------");


			try {
			for (int i = 0 ; i < ee.empty() ; i ++ ) {
				
				Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(i);
				Cell c = r.getCell(7);
				String temp = c.getStringCellValue();
				
				
				
				
				if(user.equals(temp)== true) {
					System.out.println();
					Cell Sno = r.getCell(0);
					int suno = (int)Sno.getNumericCellValue();
					Cell DocFname = r.getCell(1);
					Cell DocLname = r.getCell(2);
					Cell DocExp = r.getCell(6);
					int exp = (int)Sno.getNumericCellValue();
					Cell DocTime = r.getCell(9);
					System.out.printf("%5d %20s\t\t%02d %15s", suno ,"Dr." + DocFname , exp, DocTime);
					
					
				}
			}
	        System.out.println("\n--------------------------------------------------------------");

			System.out.println("\n Choose your Doc By entering serial number");

		 }catch(Exception e) {
			 System.out.print(e);
		 }
			
		}
		catch(Exception e) {
			System.out.print(e);
			
		}
	}
	
	
	
	
}






class EmptyEntryPatient {
	private String file = "/Users/xtemper/eclipse-workspace/Hospital-Management-App/Hospital.xlsx";
	int empty() {
		int res =0 ;
		try {
			

			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Patients");
			for (int i = 0 ; i < 99 ; i++)
			{
				Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(i);
				Cell c = r.getCell(1);
				String v = c.getStringCellValue();
				if (v != null) {
					res++;
				 }
				else {
					i = 100;
				}
				
				
				
			}
			
		}
		catch (Exception e )
		{
			
		}
		return res;
	}
}


class WritePatient {
    EmptyEntryPatient eep = new EmptyEntryPatient();
    patient Pa = new patient();
    private String file = "/Users/xtemper/eclipse-workspace/Hospital-Management-App/Hospital.xlsx";

    void WriteToken(String token) {
        try {
        	

            FileInputStream fs = new FileInputStream(file);
            Workbook WB = WorkbookFactory.create(fs);
            Sheet s = (Sheet) WB.getSheet("Patients");

            Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(eep.empty());

            Cell c = r.createCell(1);

            c.setCellValue(token);

            FileOutputStream fos = new FileOutputStream(file);
            WB.write(fos);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    void WriteFName(String Fname) {
    	
		try {
    		
			
     		FileInputStream fs = new FileInputStream(file);
     		Workbook WB = WorkbookFactory.create(fs);
            Sheet s = (Sheet) WB.getSheet("Patients");

     		
     		Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(eep.empty());
     		
     		Cell c = r.createCell(2);
     		
     		c.setCellValue(Fname);
     		
     		FileOutputStream fos = new FileOutputStream(file);
     		WB.write(fos);
     		
     		
		}
        	
		
		catch (Exception e ) {
			System.out.println(e);
		}
    }
    void WriteLName(String Lname) {
			try {
	    		
				
	     		FileInputStream fs = new FileInputStream(file);
	     		Workbook WB = WorkbookFactory.create(fs);
	            Sheet s = (Sheet) WB.getSheet("Patients");

	     		
	     		Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(eep.empty());
	     		
	     		Cell c = r.createCell(3);
	     		
	     		c.setCellValue(Lname);
	     		
	     		FileOutputStream fos = new FileOutputStream(file);
	     		WB.write(fos);
	     		
	     		
	     		
	        	
			}
			catch (Exception e ) {
				System.out.println(e);
			}
    }
    void WriteEmail(String Email) {
				try {
		    		
					
		     		FileInputStream fs = new FileInputStream(file);
		     		Workbook WB = WorkbookFactory.create(fs);
		            Sheet s = (Sheet) WB.getSheet("Patients");
;
		     		
		     		Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(eep.empty());
		     		
		     		Cell c = r.createCell(4);
		     		
		     		c.setCellValue(Email);
		     		
		     		FileOutputStream fos = new FileOutputStream(file);
		     		WB.write(fos);
		     		
		     		
		     		
		        	
				}
				catch (Exception e ) {
					System.out.println(e);
				}
    }

    void WritePn(long patientPhone) {
					try {
			    		
						
			     		FileInputStream fs = new FileInputStream(file);
			     		Workbook WB = WorkbookFactory.create(fs);
			            Sheet s = (Sheet) WB.getSheet("Patients");

			     		
			     		Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(eep.empty());
			     		
			     		Cell c = r.createCell(5);
			     		
			     		c.setCellValue(patientPhone);
			     		
			     		FileOutputStream fos = new FileOutputStream(file);
			     		WB.write(fos);
			     		
			     		
			     		
			        	
					}
					catch (Exception e ) {
						System.out.println(e);
					}
    }

    void WriteProb(String patientProb) {
        try {

            FileInputStream fs = new FileInputStream(file);
            Workbook WB = WorkbookFactory.create(fs);
            Sheet s = (Sheet) WB.getSheet("Patients");


            Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(eep.empty());

            Cell c = r.createCell(6);

            c.setCellValue(patientProb);

            FileOutputStream fos = new FileOutputStream(file);
            WB.write(fos);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void WriteDoc(String Doc) {
		try {
					    		
								
					FileInputStream fs = new FileInputStream(file);
					     		Workbook WB = WorkbookFactory.create(fs);
					            Sheet s = (Sheet) WB.getSheet("Patients");

					     		
					     		Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(eep.empty());
					     		
					     		Cell c = r.createCell(7);
					     		
					     		c.setCellValue(Doc);
					     		
					     		FileOutputStream fos = new FileOutputStream(file);
					     		WB.write(fos);
					     		
					     		
					     		
					        	
							}
					catch (Exception e ) {
								System.out.println(e);
							}
}
    }


class getdoc{
	String docname(int m ) {
		String doc =" ";
		String file = "/Users/xtemper/eclipse-workspace/Hospital-Management-App/Hospital.xlsx";
		try {
			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Doctors");
			Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(m);
			Cell c = r.getCell(1);
			String v = c.getStringCellValue();

			doc = v;
			
		}
		catch (Exception e ) {
			System.out.print(e + "  ii  jk jk k");
		}
		return doc;
	}
}








