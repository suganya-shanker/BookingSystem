import java.io.FileNotFoundException;

public class DoctorJames implements Doctor{
	String doctorName;
	String specialisation; 
	String startTime;
	@Override
		public String setDetails() throws FileNotFoundException {
		MainClass mc=new MainClass();
		String[] details=mc.doctorDetails(3);
		doctorName=details[0];
		specialisation=details[1];
		mc.availableSlots(doctorName);
		//startTime="9:00am";
		return doctorName;
			}
}
