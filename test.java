import java.util.Scanner;

public class test {
	public static void main(String [] arrgs)
	{
		Scanner sc = new Scanner(System.in);
		String i = sc.next();
		char k[] =Encryption.main(i).toCharArray();
		char m[] =Decrypt.main(Encryption.main(i)).toCharArray();
		System.out.println(k.length + " " + m.length);
		
		System.out.print(i.equals(Decrypt.main(Encryption.main(i))));
	}
}