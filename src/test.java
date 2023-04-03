import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test{

    public static void main(String[] args) throws Exception{

        String command = "ping -c pwd";

        Process proc = Runtime.getRuntime().exec(command);

        // Read the output

        BufferedReader reader =  
              new BufferedReader(new InputStreamReader(proc.getInputStream()));

        String line = "";
        while((line = reader.readLine()) != null) {
            System.out.print(line + "\n");
        }

        proc.waitFor();   

    }
}