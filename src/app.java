
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

class GlobalString {
	public static String file = setting.main(null);

}

public class app {
	public static void main(String[] arrgs) {
		final String file = GlobalString.file;

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
			if (p.equals(c.getStringCellValue())) {
				System.out.println("Welcome Doctor");
				doctor.main(null);

			} else if (p.equals(f.getStringCellValue())) {
				System.out.println("Welcome Admin");
			} else if (p.equals("1")) {
				PP.login(file);
			} else {
				PP.signup(file);
			}

		} catch (Exception e) {
			System.out.print(e);
		}

	}
}

class patient {
	docChoose Dc = new docChoose();
	String file = GlobalString.file;
	private static final SecureRandom secureRandom = new SecureRandom();
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
	private String PatientFName;
	private String PatientLName;
	private String PatientEmail;
	private long PatientPhone;
	private int PatientProb;
	private String PatientDocAss;
	private String Token = tokenCreate();
	private String Problem;

	public void setPatientFName(String patientFName) {
		PatientFName = patientFName;
	}

	EmptyEntry EE = new EmptyEntry();
	EmptyEntryPatient EEP = new EmptyEntryPatient();
	void docname(int sno) {

		try {

			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Doctors");
			try {
				

					Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(sno);
					Cell c = r.getCell(1);
					

					PatientDocAss = c.getStringCellValue();
					
				
			} catch (Exception e) {

			}

		} catch (Exception e) {

		}

	}

	void login(String file) {
		int index = 0;
		int z = 0;
		System.out.println("enter token number");
		Scanner sc = new Scanner(System.in);
		String Token1 = sc.next();
		try {
			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Patients");

			try {
				for (int i = 0; i < EEP.empty(); i++) {

					Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(i);
					Cell c = r.getCell(1);
					String temp = c.getStringCellValue();

					if (temp.equals(Token1)) {

						try {
							Cell m[] = { r.getCell(2), r.getCell(3), r.getCell(4), r.getCell(5), r.getCell(6),
									r.getCell(7), r.getCell(8) , r.getCell(9) , r.getCell(9) };

							z = 100;

							String name = m[0].getStringCellValue();
							System.out.println("Welcome " + name + " " + m[1].getStringCellValue());
							System.out.println("Email :\t" + m[2].getStringCellValue());
							System.out.println("Phone Number :\t" + (long) m[3].getNumericCellValue());
							System.out.println("Problem :\t" + m[6].getStringCellValue());
							System.out.println("Registerd Doc :\t" + m[5].getStringCellValue());
							System.out.println("Time Of Visit :\t" + m[7].getStringCellValue());
							
							i = 100;

						} catch (Exception e) {
							System.out.print(e);
						}

					}

				}
				if (z == 100) {

				} else {
					System.out.print("Wrong Token \n");

					app.main(null);
				}
			} catch (Exception e) {

			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void email() {
		Scanner sc = new Scanner(System.in);
		System.out.println(" Enter Your Email ID \n ");
		PatientEmail = sc.next();
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		if (pat.matcher(PatientEmail).matches() == false) {
			System.out.println("Wrong Email ID");
			email();
		} else {

		}

	}

	public void phone() {
		Scanner sc = new Scanner(System.in);

		System.out.println(" Enter Your Phone Number \n ");
		PatientPhone = sc.nextLong();
		Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");
		String str = String.valueOf(PatientPhone);
		Matcher match = ptrn.matcher(str);
		if (match.find() && match.group().equals(str) == false) {
			System.out.println("Wrong Phone Number");
			phone();
		}
	}

	public void signup(String file) {
		System.out
				.println("Welcome Patient \nFill in the following details to get started \n Enter Your First Name \n ");
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

		System.out.println(" Choose Your Specialization \n 1. Orthopedics\n" + " 2. Internal Medicine\n"
				+ " 3. Gynecology\n" + " 4. Dermatology\n" + " 5. Pediatrics\n" + " 6. Radiology\n"
				+ " 7. General Surgery\n" + " 8. Ophthalmology \n");
		PatientProb = sc.nextInt();
		System.out.println("Describe Your Problem");
		 Problem = sc.next();
		problem(Problem);

		docChoosed(PatientProb);

	}

	void time(int m) {

		try {

			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Doctors");
			Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(m);
			Cell c = r.getCell(12);
			String input = c.getStringCellValue();
			String[] words = input.split(" ");
			String finale[] = new String[input.length()];
			
			int i = 0;
			for (i = 0; i < words.length; i++) {
				finale[i] = words[i];

				System.out.println(i + 1 + "." + finale[i]);

			}
			Scanner sc = new Scanner(System.in);
			i = sc.nextInt();

			timchoose(i, finale, m);

			System.out.println("\nYour Token Is " + Token);

		} catch (Exception e) {
			System.out.print(e);
		}

	}

	void timchoose(int choice, String[] time, int index) {
		String timechoose ="";
		if (choice == 1) {
			timechoose =time[0];
			time[0] = "";
			
			removetime(index, time);
			writePatientdata(index,timechoose);
			writePatientTime(timechoose);
		} else if (choice == 2) {
			timechoose =time[1];
			time[1] = "";
			removetime(index, time);
			writePatientdata(index,timechoose);
			writePatientTime(timechoose);
		} else if (choice == 3) {
			timechoose =time[2];
			time[2] = "";
			removetime(index, time);
			writePatientdata(index,timechoose);
			writePatientTime(timechoose);
		} else if (choice == 4) {
			timechoose =time[3];
			time[3] = "";
			removetime(index, time);
			writePatientdata(index,timechoose);
			writePatientTime(timechoose);
		} else {

		}
	}
	void writePatientTime(String time) {
		try {
			EmptyEntryPatient eep = new EmptyEntryPatient(); 
			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Patients");

			Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(eep.empty()-1);

			Cell c = r.createCell(9);

			c.setCellValue(time);

			FileOutputStream fos = new FileOutputStream(file);
			WB.write(fos);

		} catch (Exception e) {

		}
	}
	void removetime(int index, String[] time) {
		String res = "";
		for (int i = 0; i < 4; i++) {
			res = res.concat(time[i]);
			res = res.concat(" ");
		}
		try {

			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Doctors");

			Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(index);

			Cell c = r.createCell(12);

			c.setCellValue(res);

			FileOutputStream fos = new FileOutputStream(file);
			WB.write(fos);

		} catch (Exception e) {

		}

	}

	void writePatientdata(int index, String time) {
		try {

			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Doctors");

			Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(index);

			Cell c = r.createCell(8);
			
			if(c.getStringCellValue() == null) {
			c.setCellValue(time + " 1: "  + PatientFName +" "+ PatientLName + " having " + Problem );

			FileOutputStream fos = new FileOutputStream(file);
			WB.write(fos);
			}
			else {
				Cell m = r.createCell(8);
				String k = ((Cell) r).getStringCellValue();
				System.out.println(k + "lmao");
				String val = "\n" +time + " 2: "  + PatientFName + " "+ PatientLName + " having " + Problem  ;
				k = val.concat(k);
				System.out.println(k);

				c.setCellValue(k);
				FileOutputStream fos = new FileOutputStream(file);
				WB.write(fos);
			}

		} catch (Exception e) {

		}
	}

	void problem(String Problem) {

		try {
			EmptyEntryPatient eep = new EmptyEntryPatient();
			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Patients");

			Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(eep.empty());

			Cell c = r.createCell(8);

			c.setCellValue(Problem);

			FileOutputStream fos = new FileOutputStream(file);
			WB.write(fos);
		} catch (Exception e) {

		}
	}

	void docChoosed(int m) {
		docChoose dc = new docChoose();
		WritePatient Wp = new WritePatient();
		getdoc gdc = new getdoc();

		if (m == 1) {

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
			// tokenCreate();
			Wp.WriteToken(Token);
			System.out.println("Select Your Time");
			time(m);
			login(file);

		} else if (m == 2) {

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

			// tokenCreate();
			Wp.WriteToken(Token);
			System.out.println("Select Your Time");
			time(m);
			login(file);
		} else if (m == 3) {
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
			System.out.println("Select Your Time");
			time(m);
			// tokenCreate();
			Wp.WriteToken(Token);
			login(file);
		} else if (m == 4) {
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
			System.out.println("Select Your Time");
			time(m);
			// tokenCreate();
			Wp.WriteToken(Token);
			login(file);
		} else if (m == 5) {
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
			System.out.println("Select Your Time");
			time(m);
			// tokenCreate();
			Wp.WriteToken(Token);
			login(file);
		} else if (m == 6) {
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
			System.out.println("Select Your Time");
			time(m);
			// tokenCreate();
			Wp.WriteToken(Token);
			login(file);
		} else if (m == 7) {
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
			System.out.println("Select Your Time");
			time(m);
			// tokenCreate();
			Wp.WriteToken(Token);
			login(file);
		} else if (m == 8) {
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
			System.out.println("Select Your Time");
			time(m);
			// tokenCreate();
			Wp.WriteToken(Token);
			login(file);
		} else if (m == 9) {
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
			System.out.println("Select Your Time");
			time(m);
			tokenCreate();
			Wp.WriteToken(Token);
			login(file);
		} else {
			System.out.print("Enter a valid number");
			docChoosed(PatientProb);
		}

	}

	void WriteValue(String Token) {

	}

	String tokenCreate() {
		byte[] randomBytes = new byte[24];
		secureRandom.nextBytes(randomBytes);
		Token = base64Encoder.encodeToString(randomBytes);

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

class time {
	static String curtime() {
		String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss ").format(new java.util.Date());

		return timeStamp;
	}
}

class EmptyEntry {
	private String file = GlobalString.file;

	int empty() {
		int res = 0;
		try {

			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Doctors");
			for (int i = 0; i < 99; i++) {
				Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(i);
				Cell c = r.getCell(1);
				String v = c.getStringCellValue();
				if (v != null) {
					res++;
				} else {
					i = 100;
				}

			}

		} catch (Exception e) {

		}
		return res;
	}
}

class docChoose {
	String file = GlobalString.file;

	void Choosed(String user) {
		EmptyEntry ee = new EmptyEntry();
		try {
			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Doctors");
			// System.out.println("S.NO.\t\tName\t\tExperience\t\tTime");
			Formatter fmt = new Formatter();
			System.out.println("-------------------------------------------------------------------");

			System.out.printf("%5s %20s\t\t%s %14s", "S.No.", "Name", "Exp(Year)", "Time\n");
			System.out.println("-------------------------------------------------------------------");

			try {
				for (int i = 0; i < ee.empty(); i++) {

					Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(i);
					Cell c = r.getCell(7);
					String temp = c.getStringCellValue();

					if (user.equals(temp) == true) {
						System.out.println();
						Cell Sno = r.getCell(0);
						int suno = (int) Sno.getNumericCellValue();
						Cell DocFname = r.getCell(1);
						Cell DocLname = r.getCell(2);
						Cell DocExp = r.getCell(6);
						int exp = (int) Sno.getNumericCellValue();
						Cell DocTime = r.getCell(12);
						System.out.printf("%5d %20s\t\t%02d %25s", suno, "Dr." + DocFname, exp, DocTime);

					}
				}
				System.out.println("\n-------------------------------------------------------------------");

				System.out.println("\n Choose your Doc By entering serial number");

			} catch (Exception e) {
				System.out.print(e);
			}

		} catch (Exception e) {
			System.out.print(e);

		}
	}

}

class EmptyEntryPatient {
	private String file = GlobalString.file;

	int empty() {
		int res = 0;
		try {

			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Patients");
			for (int i = 0; i < 99; i++) {
				Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(i);
				Cell c = r.getCell(1);
				String v = c.getStringCellValue();
				if (v != null) {
					res++;
				} else {
					i = 100;
				}

			}

		} catch (Exception e) {

		}
		return res;
	}
}

class WritePatient {
	EmptyEntryPatient eep = new EmptyEntryPatient();
	patient Pa = new patient();
	private String file = GlobalString.file;

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

		catch (Exception e) {
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

		} catch (Exception e) {
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

		} catch (Exception e) {
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

		} catch (Exception e) {
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

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

class getdoc {
	String docname(int m) {
		String doc = " ";
		String file = GlobalString.file;
		try {
			FileInputStream fs = new FileInputStream(file);
			Workbook WB = WorkbookFactory.create(fs);
			Sheet s = (Sheet) WB.getSheet("Doctors");
			Row r = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(m);
			Cell c = r.getCell(1);
			String v = c.getStringCellValue();

			doc = v;

		} catch (Exception e) {
			System.out.print(e + "  ii  jk jk k");
		}
		return doc;
	}
}

class docTime {
	void time() {

	}
}
