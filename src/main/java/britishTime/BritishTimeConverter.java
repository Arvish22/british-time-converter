package britishTime;

import java.util.HashMap;
import britishTime.exceptions.InvalidTimeFormatException;
import factory.TimeConverter;

public class BritishTimeConverter implements TimeConverter {

	// Array to map minute values to spoken words
	private static final String[] MINUTES_MAPPINGS = { "one", "two", "three", "four", "five", "six", "seven", "eight",
			"nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
			"nineteen", "twenty", "twenty one", "twenty two", "twenty three", "twenty four", "twenty five",
			"twenty six", "twenty seven", "twenty eight", "twenty nine", "thirty" };

	// HashMap to map hour values to spoken words
	private static final HashMap<Integer, String> HOUR_MAPPINGS = createHourMappings();

	@Override
	public String convertToSpokenForm(String inputTime) throws InvalidTimeFormatException {
		// Split the input time string into hours and minutes
		String[] timeParts = inputTime.split(":");

		// Validate input format and throw exception if invalid
		validateTimeFormat(timeParts);

		int hour = Integer.parseInt(timeParts[0]);
		int minute = Integer.parseInt(timeParts[1]);

		// Convert midnight and noon to spoken form
		if (hour == 0 && minute == 0) {
			return "midnight";
		} else if (hour == 12 && minute == 0) {
			return "noon";
		} else if ((hour != 12 && minute == 0) || (minute > 30 && minute < 35)) {
			// For times where minute is 0 or between 31 and 34
			return generateSpokenForm(hour, minute, true);
		} else {
			// Convert other times to spoken form
			if (minute > 34) {
				hour = hour + 1;
			}

			return generateSpokenForm(hour, minute, false);
		}
	}

	private String generateSpokenForm(int hour, int minute, boolean hourFirst) {
		String spokenHour = HOUR_MAPPINGS.get(hour % 12);
		String spokenMinute = getMinuteSpoken(minute);

		// uncomment this line of code if period is needed
		//String period = (hour < 12) ? "am" : "pm";

		if (hourFirst)
			return spokenHour + " " + spokenMinute;

		return spokenMinute + " " + spokenHour;
	}

	// Method to validate the time format
	private void validateTimeFormat(String[] timeParts) throws InvalidTimeFormatException {
		if (timeParts.length != 2) {
			throw new InvalidTimeFormatException("Invalid time format. Time must be in format HH:MM");
		}

		int hour = Integer.parseInt(timeParts[0]);
		int minute = Integer.parseInt(timeParts[1]);

		if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
			throw new InvalidTimeFormatException(
					"Invalid time format. " + "Hour must be between 0 and 23, and minute must be between 0 and 59.");
		}
	}

	// Helper method to create hour mappings
	private static HashMap<Integer, String> createHourMappings() {
		HashMap<Integer, String> hourMappings = new HashMap<>();
		hourMappings.put(1, "one");
		hourMappings.put(2, "two");
		hourMappings.put(3, "three");
		hourMappings.put(4, "four");
		hourMappings.put(5, "five");
		hourMappings.put(6, "six");
		hourMappings.put(7, "seven");
		hourMappings.put(8, "eight");
		hourMappings.put(9, "nine");
		hourMappings.put(10, "ten");
		hourMappings.put(11, "eleven");
		hourMappings.put(0, "twelve");
		return hourMappings;
	}

	// Helper method to get spoken minute
	public String getMinuteSpoken(int minute) {
		if (minute == 0) {
			return "o'clock";
		} else if (minute == 15) {
			return "quarter past";
		} else if (minute == 30) {
			return "half past";
		} else if (minute == 45) {
			return "quarter to";
		} else if (minute < 30) {
			return MINUTES_MAPPINGS[minute - 1] + " past";
		} else if (minute > 30 && minute < 35) {
			return MINUTES_MAPPINGS[(minute - (minute % 30)) - 1] + " " + MINUTES_MAPPINGS[(minute % 30)-1];
		} else {
			return MINUTES_MAPPINGS[60 % (minute + 1)] + " to";
		}
	}
}
