package britishTime;

import britishTime.exceptions.InvalidTimeFormatException;

/**
 * Interface defining the contract for converting time to spoken form.
 */
public interface TimeConverter {
    
    /**
     * Converts the input time to spoken form.
     * 
     * @param inputTime The input time in the format "HH:MM".
     * @return The spoken form of the input time.
     * @throws InvalidTimeFormatException If the input time format is invalid.
     */
    String convertToSpokenForm(String inputTime) throws InvalidTimeFormatException;
}

