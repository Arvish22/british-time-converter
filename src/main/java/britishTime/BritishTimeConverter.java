package britishTime;

import java.util.HashMap;

import britishTime.exceptions.InvalidTimeFormatException;

public class BritishTimeConverter implements TimeConverter {

    @Override
    public String convertToSpokenForm(String inputTime) throws InvalidTimeFormatException {
        // Split the input time string into hours and minutes
        String[] timeParts = inputTime.split(":");
        
        // Validate the input time format
        if(timeParts.length != 2) {
            throw new InvalidTimeFormatException("Invalid time format. Time must be in format HH:MM");
        }
        
        // Parse hours and minutes from string
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);
        
        // Validate hours and minutes range
        if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
            throw new InvalidTimeFormatException("Invalid time format. Hour must be between 0 and 23, and minute must be between 0 and 59.");
        }
        
        // Convert midnight and noon to spoken form
        if(hour == 0 && minute == 0) {
            return "midnight";
        } else if(hour == 12 && minute == 0) {
            return "noon";
        } else {
            // Convert other times to spoken form
            String spokenHour = getHourSpoken(hour % 12);
            String spokenMinute = getMinuteSpoken(minute);
            String period = (hour < 12)?"am":"pm";
            return spokenHour + " " + spokenMinute + " " + period;
        }
    }
    
    // Helper method to create hour mappings
    public HashMap<Integer, String> createHourMappings() {
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
            return "quarter";
        } else if (minute == 30) {
            return "half";
        } else if (minute == 45) {
            return "three quarters";
        } else if (minute < 30) {
            return "past " + getHourSpoken(minute);
        } else {
            return "to " + getHourSpoken(60 - minute);
        }
    }

    // Helper method to get spoken hour
    public String getHourSpoken(int hour) {
        HashMap<Integer, String> hourMappings = createHourMappings();
        return hourMappings.get(hour);
    }

}
