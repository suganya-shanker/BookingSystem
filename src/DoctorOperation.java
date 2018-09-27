import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DoctorOperation extends MainClass {

	public void createdoctorprofile() throws FileNotFoundException {
		String newContent = "";
		System.out.println("Enter doctor name");
		String doctorName = s.next();
		System.out.println("Enter doctor start time like 8:00am");
		String starttime = s.next();
		System.out.println("Enter doctor end time like");
		String endtime = s.next();
		System.out.println("Enter doctor specilisation");
		String specialisation = s.next();
		// getting last doctor id
		Scanner sc = new Scanner(new File("doctorlist.txt"));
		String details = "";
		while (sc.hasNext()) {
			details = sc.nextLine();
		}
		int doctorId = Integer.parseInt(details.substring(0, 4));
		doctorId++;
		try {
			FileWriter fw = new FileWriter("doctorlist.txt", true);
			PrintWriter pw = new PrintWriter(fw);
			pw.write(doctorId + " " + doctorName + " " + starttime + " " + endtime + " " + specialisation);
			pw.write("\r\n"); // write new line
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updatedoctorprofile() throws FileNotFoundException {
		System.out.println("Enter doctor id to update details");
		int doctorId = s.nextInt();
		Scanner sc = new Scanner(new File("doctorlist.txt"));
		String[] doctorDetailsarray = new String[5];
		String details = "";
		while (sc.hasNext()) {
			String temp = sc.nextLine();
			int docId = Integer.parseInt(temp.substring(0, 4));
			System.out.println(docId);
			if (doctorId == docId) {
				details = temp;
				System.out.println("old doctor details"+details);
			}
		}
		doctorDetailsarray = details.split(" ");
		int option = 5;
		while (option != 4) {
			System.out.println(
					"Please choose option to update the doctor profile \n1.Name \n2.Timing \n3.Specialisation \n4.Exit");
			option = s.nextInt();
			switch (option) {
			case 1:
				System.out.println("Enter new doctor name");
				String doctorName = s.next();
				doctorDetailsarray[1] = doctorName;
				System.out.println("Doctorname successfully updated");
				break;
			case 2:
				System.out.println("Enter new timing for the doctor");
				String starttime = s.next();
				doctorDetailsarray[2] = starttime;
				String endtime = s.next();
				doctorDetailsarray[3] = endtime;
				System.out.println("Doctor timing successfully updated");
				break;
			case 3:
				System.out.println("Enter doctor's Specialisation");
				String Specialisation = s.next();
				doctorDetailsarray[4] = Specialisation;
				System.out.println("Doctor's Specialisation successfully updated");
				break;
			case 4:
				System.out.println("Doctor details successfully updated");
				break;
			default:
				System.out.println("Please enter valid option");
			}
		}
		// creating updated details string (Array to String)
		String updateddoctordetails = "";
		for (int i = 0; i < doctorDetailsarray.length; i++) {
			updateddoctordetails +=doctorDetailsarray[i] + " ";
		}
		System.out.println("updated doctor details "+updateddoctordetails);
		String newContent="";
		Scanner sd = new Scanner(new File("doctorlist.txt"));
		// inserting updated doctor details into the file
		while (sd.hasNext()) {
			String line = sd.nextLine();
			int docId = Integer.parseInt(line.substring(0, 4));
			if (doctorId == docId) {
				newContent+= updateddoctordetails + System.lineSeparator();
			} else {
				newContent+= line + System.lineSeparator();
			}
		}
		//System.out.println("updated list \n"+newContent);
		try {
			FileWriter fw = new FileWriter("doctorlist.txt");
			PrintWriter pw = new PrintWriter(fw);
			pw.write(newContent);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deletedoctorprofile() throws FileNotFoundException {
		System.out.println("Enter doctor id to delete details");
		int doctorId = s.nextInt();
		String newContent = "";
		String line = "";
		// Reading the file without matching doctor details
		Scanner sc = new Scanner(new File("doctorlist.txt"));
		while (sc.hasNext()) {
			line = sc.nextLine();
			int docId = Integer.parseInt(line.substring(0, 4));
			if (doctorId != docId) {
				newContent = newContent + line + System.lineSeparator();
			}
		}
		// Rewriting the file with delete doctor details
		try {
			FileWriter fw = new FileWriter("doctorlist.txt");
			PrintWriter pw = new PrintWriter(fw);
			pw.write(newContent);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void viewalldoctorprofile() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("doctorlist.txt"));
		while (sc.hasNext()) {
			String details = sc.nextLine();
			System.out.println(details);
		}
	}

}
