import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DoctorDavid implements Doctor{
	String doctorName;
	String specialisation; 
	String startTime;
	@Override
	public String setDetails() throws FileNotFoundException {
		MainClass mc=new MainClass();
		String[] details=mc.doctorDetails(1041);
		doctorName=details[0];
		specialisation=details[1];
		mc.availableSlots(doctorName);
		return doctorName;
	}
	
	
}
