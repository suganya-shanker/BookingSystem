import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Doctorlist {
	static Scanner s = new Scanner(System.in);

	public static void main(String args[]) throws FileNotFoundException {
		System.out.println("Welcome to SSS clinic");
		displayDoctorList();
		System.out.println("Enter doctor id");
		char doctorId = s.nextLine().charAt(0);
		System.out.println(doctorId);
		availableSlots(doctorId);
		customerDetails();
	}

	public static void displayDoctorList() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("doctorlist.txt"));
		int doctorId = 1;
		while (sc.hasNext()) {
			String details = sc.nextLine();
			int count = 0;
			String doctorName = "";
			String specialisation = "";
			for (int i = 0; i < details.length(); i++) {
				if (details.charAt(i) == ' ') {
					count++;
				} else if (count == 1) {
					doctorName += details.charAt(i);
				} else if (count == 4) {
					specialisation += details.charAt(i);
				}
			}
			System.out.println(doctorId + ". " + doctorName + " ( " + specialisation + " )");
			System.out.println();
			doctorId++;
		}
	}

	
	public static void availableSlots(int doctorId) throws FileNotFoundException {
		String doctorName="";
		System.out.println("available slot method");
		Scanner dl = new Scanner(new File("doctorlist.txt"));
		while (dl.hasNext()) {
			String details = dl.nextLine();
			char doctorNum = details.charAt(0);
			System.out.println(doctorNum);
			if (doctorNum == doctorId) {
				System.out.println("in if condition"+doctorNum);
				int count = 0;
				for (int i = 0; i < details.length(); i++) {
					if (details.charAt(i) == ' ') {
						count++;
					} else if (count == 1) {
						doctorName=details;
						System.out.println(doctorName);
						break;
					} 
				}
				}
		}
		Scanner sc = new Scanner(new File("slots.txt"));
		while (sc.hasNext()) {
			if(sc.next().equals(doctorName)&&sc.hasNext()) {
				for(int i=1;i<=28;i++) {
			System.out.println(sc.next());
				}
			}
		}

	}
	public static void customerDetails() throws FileNotFoundException {
		System.out.println("Enter your details");
		System.out.println("Name");
		String name=s.next();
		System.out.println("Age");
		String age=s.next();
		System.out.println("Contact number");
		String contactno=s.next();
		try {
            FileWriter fw = new FileWriter("customerlist.txt", true);
           PrintWriter pw=new PrintWriter(fw); 
            pw.write(name+" "+age+" "+contactno);
            pw.write("\r\n");   // write new line
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }	
	Scanner sc = new Scanner(new File("customerlist.txt"));
	while (sc.hasNext()) {
		String details = sc.nextLine();
		System.out.println(details);
	}
	}

}
