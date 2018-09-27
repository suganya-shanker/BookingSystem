import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class MainClass implements RWfile {
	public static Scanner s = new Scanner(System.in);
	static Map<String, ArrayList<String>> bookedSlots = new HashMap<String, ArrayList<String>>();

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
		System.out.println("Welcome to SSS clinic");
		int login = 4;
		while (login != 3) {
			System.out.println("1.Login as admin \n2.login as patient \n3.Exit");
			login = s.nextInt();
			if (login == 1) {
				System.out.println("Enter option to perform operation");
				int doctoroperation = 6;
				while (doctoroperation != 5) {
					System.out.println(
							"1.Create new doctor profile \n2.update doctor profile \n3.Delete doctor profile \n4.view all doctor profile \n5.Exit");
					doctoroperation = s.nextInt();
					DoctorOperation docop = new DoctorOperation();
					switch (doctoroperation) {
					case 1:
						docop.createdoctorprofile();
						break;
					case 2:
						docop.updatedoctorprofile();
						break;
					case 3:
						docop.deletedoctorprofile();
						break;
					case 4:
						docop.viewalldoctorprofile();
						break;
					case 5:
						System.out.println("You successfully logged out as a admin");
						break;
					default:
						System.out.println("Enter proper value");
					}
				}
			} else if (login == 2) {
				System.out.println("Now you can book appointment!!!");
				boolean status = false;
				int doctorId = 0;
				String doctorName = "";
				String slotTime = "";
				while (doctorId != 6) {
					displayDoctorList();
					System.out.println("6.Exit");
					System.out.println("Enter doctor id");
					doctorId = s.nextInt();
					MainClass mc = new MainClass();
					mc.readslot();
					switch (doctorId) {
					case 1:
						DoctorDavid docDav = new DoctorDavid();
						doctorName = docDav.setDetails();
						status = true;
						break;
					case 2:
						DoctorRobert docRob = new DoctorRobert();
						doctorName = docRob.setDetails();
						status = true;
						break;
					case 3:
						DoctorJames docjam = new DoctorJames();
						doctorName = docjam.setDetails();
						status = true;
						break;
					case 4:
						DoctorPaul docpaul = new DoctorPaul();
						doctorName = docpaul.setDetails();
						status = true;
						break;
					case 5:
						DoctorRose docrose = new DoctorRose(); 
						doctorName = docrose.setDetails();
						status = true;
						break;
					case 6:
						System.out.println("Thank you! And you successfully logged out as patient.");
						status = false;
						break;
					default:
						System.out.println("Enter proper value");
						status = false;
					}
					if (status) {
						System.out.println("Enter slot number");
						int slotNumber = s.nextInt();
						slotTime = checkSlot(doctorName, slotNumber);
						try {
							createAppointment(doctorName, slotTime);
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}

					}
				}
			} else if (login == 3) {
				System.out.println("Thank you for contacting SSS clinic");
			} else {
				System.out.println("Enter proper value");
			}

		}
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

	public static String[] doctorDetails(int doctorId) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("doctorlist.txt"));
		String[] doctorDetailsarray = new String[3];
		int count = 0;
		String doctorName = "";
		String specialisation = "";
		while (sc.hasNext()) {
			String details = sc.nextLine();
			int id = Integer.parseInt(details.substring(0, 4));
			if (id == doctorId) {
				for (int i = 0; i < details.length(); i++) {
					if (details.charAt(i) == ' ') {
						count++;
					} else if (count == 1) {
						doctorName += details.charAt(i);
					} else if (count == 4) {
						specialisation += details.charAt(i);
					}
				}
			}
		}
		doctorDetailsarray[0] = doctorName;
		doctorDetailsarray[1] = specialisation;
		return doctorDetailsarray;
	}

	public void availableSlots(String doctorName) throws FileNotFoundException {
		// System.out.println(doctorName);
		ArrayList<String> bookedslotarray = bookedSlots.get(doctorName);
		String slotFromList = "";
		Scanner sc = new Scanner(new File("slots.txt"));
		while (sc.hasNext()) {
			if (sc.next().equals(doctorName)) {
				sc.nextLine();
				for (int i = 1; i <= 28; i++) {
					slotFromList = checkSlot(doctorName, i);
					if (bookedslotarray == null) {
						System.out.println(sc.nextLine());
					} else if (bookedslotarray.contains(slotFromList)) {
						System.out.println("slot already booked");
						sc.nextLine();
					} else {
						System.out.println(sc.nextLine());
					}
				}
			}
		}
	}

	public static String checkSlot(String doctorName, int slotNumber) throws FileNotFoundException {
		// System.out.println(doctorName + " " + slotNumber);
		Scanner sc = new Scanner(new File("slots.txt"));
		boolean status = true;
		String slotTime = "";
		while (sc.hasNext()) {
			String temp = sc.next();
			if (doctorName.equals(temp)) {
				slotTime = sc.nextLine();
				for (int i = 1; i <= 28; i++) {
					slotTime = sc.nextLine();
					if (slotNumber == i) {
						// System.out.println("slot is available");
						// System.out.println("selected slot time " + slotTime);
						slotTime = slotTime.substring(slotTime.length() - 5);
						status = false;
						break;
					}
				}
			}
		}
		if (status) {
			// System.out.println("slot is not available");
			return null;
		} else {
			return slotTime;
		}
	}

	/**
	 * @param doctorName
	 * @param timeSlot
	 * @throws FileNotFoundException
	 */
	public static void createAppointment(String doctorName, String timeSlot)
			throws FileNotFoundException, ClassNotFoundException {
		MainClass mc = new MainClass();
		mc.readslot();
		ArrayList<String> timeSlotarray = new ArrayList<String>();
		if (bookedSlots.containsKey(doctorName)) {
			timeSlotarray = bookedSlots.get(doctorName);
		}
		timeSlotarray.add(timeSlot);
		bookedSlots.put(doctorName, timeSlotarray);
		System.out.println("Do you have patient id \n1.Yes \n2.No");
		int option = s.nextInt();
		int patientId = 0;
		String name = "";
		String age = "";
		String contactno = "";
		if (option == 1) {
			System.out.println("Please enter patient id");
			patientId = s.nextInt();
			// Search for customer details in customer file
			Scanner sc = new Scanner(new File("customerlist.txt"));
			String details = "";
			int tempId = 0;
			String[] patientdetailsarray = new String[4];
			while (sc.hasNext()) {
				details = sc.nextLine();
				tempId = Integer.parseInt(details.substring(0, 4));
				if (tempId == patientId) {
					patientdetailsarray = details.split(" ");
				}
			}
			name = patientdetailsarray[1];
			age = patientdetailsarray[2];
			contactno = patientdetailsarray[3];
		} else {
			System.out.println("Enter your details");
			System.out.println("Name");
			name = s.next();
			System.out.println("Age");
			age = s.next();
			System.out.println("Contact number");
			contactno = s.next();
			// Genetate patient id
			Scanner sc = new Scanner(new File("customerlist.txt"));
			String details = "";
			while (sc.hasNext()) {
				details = sc.nextLine();
			}
			patientId = Integer.parseInt(details.substring(0, 4));
			patientId++;
			//writing new customer details into customerlist file
			try {
				FileWriter fw = new FileWriter("customerlist.txt", true);
				PrintWriter pw = new PrintWriter(fw);
				pw.write(patientId + " " + name + " " + age + " " + contactno);
				pw.write("\r\n"); // write new line
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Random r = new Random();
		String bookingId = String.format("%04d", r.nextInt(1001));
		// writing appointment details in appointmentdetails file
		try {
			FileWriter fw = new FileWriter("Appointmentdetails.txt", true);
			PrintWriter pw = new PrintWriter(fw);
			pw.write(bookingId + " " + doctorName + " " + timeSlot + " " + name + " " + age + " " + contactno); 
			pw.write("\r\n"); // write new line
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mc.writeslot();
		System.out.println("Hi "+name+", Your appointment successfully created with Doctor " + doctorName + " and Booking Id is: " + bookingId);
		
	}

	public void writeslot() {
		try {
			FileOutputStream f = new FileOutputStream(new File("bookedslots.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(bookedSlots);

			o.close();
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readslot() throws ClassNotFoundException {
		try {
			FileInputStream fi = new FileInputStream(new File("bookedslots.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			bookedSlots = (Map<String, ArrayList<String>>) oi.readObject();

			// System.out.println(bookedSlots.toString());

			oi.close();
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
