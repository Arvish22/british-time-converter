package application;
import application.britishTime.exception.InvalidTimeFormatException;
import application.factory.TimeConverter;
import application.factory.TimeConverterFactory;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimeConverter tc = TimeConverterFactory.getTimeConverter("British");
		try {
			System.out.println(tc.convertToSpokenForm("6:34"));
		} catch (InvalidTimeFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
