import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomerOperation {
public static void createpatient() {
	System.out.println("Enter your details");
	System.out.println("Name");
	String name = s.next();
	System.out.println("Age");
	String age = s.next();
	System.out.println("Contact number");
	String contactno = s.next();
	//Genetate patient id
	try {
		FileWriter fw = new FileWriter("customerlist.txt", true);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(name + " " + age + " " + contactno);
		pw.write("\r\n"); // write new line
		pw.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}
