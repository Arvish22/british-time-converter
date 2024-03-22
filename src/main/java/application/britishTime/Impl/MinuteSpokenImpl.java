package application.britishTime.Impl;

import application.britishTime.interfaces.MinuteSpoken;
/**
 * Implementation class for converting minutes to spoken form.
 */
public class MinuteSpokenImpl implements MinuteSpoken {
    
    // Array to map minute values to spoken words
    private static final String[] MINUTES_MAPPINGS = { "one", "two", "three", "four", "five", "six", "seven", "eight",
            "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
            "nineteen", "twenty", "twenty one", "twenty two", "twenty three", "twenty four", "twenty five",
            "twenty six", "twenty seven", "twenty eight", "twenty nine", "thirty" };

    /**
     * Converts the input minute to its spoken form.
     * 
     * @param minute The minute value to be converted.
     * @return The spoken form of the input minute.
     */
    @Override
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
