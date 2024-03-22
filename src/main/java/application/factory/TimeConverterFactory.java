package application.factory;

import application.britishTime.BritishTimeConverter;
import application.britishTime.Impl.HourSpokenImpl;
import application.britishTime.Impl.MinuteSpokenImpl;

/**
 * Factory class for obtaining instances of TimeConverter based on the provided format.
 */
public class TimeConverterFactory {

    /**
     * Gets a TimeConverter instance based on the provided format.
     * 
     * @param format The desired format of the time converter.
     * @return A TimeConverter instance corresponding to the specified format.
     * @throws IllegalArgumentException If the provided format is not supported.
     */
    public static TimeConverter getTimeConverter(String format) {
        if ("British".equalsIgnoreCase(format)) {
            return new BritishTimeConverter(new MinuteSpokenImpl(),new HourSpokenImpl());
        } else {
            throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }
}
