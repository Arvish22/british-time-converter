package application.britishTime.Impl;

import java.util.HashMap;

import application.britishTime.interfaces.HourSpoken;

/**
 * Implementation class for converting hours to spoken form.
 */
public class HourSpokenImpl implements HourSpoken {
    
    // HashMap to map hour values to spoken words
    private static final HashMap<Integer, String> HOUR_MAPPINGS = createHourMappings();

    /**
     * Converts the input hour to its spoken form.
     * 
     * @param hour The hour value to be converted.
     * @return The spoken form of the input hour.
     */
    @Override
    public String getHourSpoken(int hour) {
        return HOUR_MAPPINGS.get(hour);
    }
    
    /**
     * Helper method to create the mappings of hour values to spoken words.
     * 
     * @return A HashMap containing mappings of hour values to spoken words.
     */
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
}
