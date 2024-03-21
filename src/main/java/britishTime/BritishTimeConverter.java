package britishTime;

import britishTime.exceptions.InvalidTimeFormatException;
import britishTime.interfaces.HourSpoken;
import britishTime.interfaces.MinuteSpoken;
import factory.TimeConverter;

/**
 * A converter class that converts time to spoken form in British English.
 */
public class BritishTimeConverter implements TimeConverter {
    
    private MinuteSpoken minuteSpoken; // Instance of MinuteSpoken interface for converting minutes
    private HourSpoken hourSpoken; // Instance of HourSpoken interface for converting hours

    /**
     * Constructor for BritishTimeConverter.
     * 
     * @param minuteSpoken An implementation of MinuteSpoken interface for converting minutes.
     * @param hourSpoken An implementation of HourSpoken interface for converting hours.
     */
    public BritishTimeConverter(MinuteSpoken minuteSpoken, HourSpoken hourSpoken) {
        this.minuteSpoken = minuteSpoken;
        this.hourSpoken = hourSpoken;
    }

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
		String spokenHour = hourSpoken.getHourSpoken(hour % 12);
		String spokenMinute = minuteSpoken.getMinuteSpoken(minute);

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
}
