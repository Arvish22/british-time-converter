package britishtime;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import britishTime.BritishTimeConverter;
import britishTime.exceptions.InvalidTimeFormatException;
import britishTime.impl.HourSpokenImpl;
import britishTime.impl.MinuteSpokenImpl;

public class BritishTimeConverterTest {

	@Test
	public void testValidInputTime() {
	    BritishTimeConverter converter = new BritishTimeConverter(new MinuteSpokenImpl(),new HourSpokenImpl());

	    try {
	        assertEquals("one o'clock", converter.convertToSpokenForm("1:00"));
	        assertEquals("five past two", converter.convertToSpokenForm("2:05"));
	        assertEquals("ten past three", converter.convertToSpokenForm("3:10"));
	        assertEquals("quarter past four", converter.convertToSpokenForm("4:15"));
	        assertEquals("twenty past five", converter.convertToSpokenForm("5:20"));
	        assertEquals("twenty five past six", converter.convertToSpokenForm("6:25"));
	        assertEquals("six thirty two", converter.convertToSpokenForm("6:32"));
	        assertEquals("half past seven", converter.convertToSpokenForm("7:30"));
	        assertEquals("twenty five to eight", converter.convertToSpokenForm("7:35"));
	        assertEquals("twenty to nine", converter.convertToSpokenForm("8:40"));
	        assertEquals("quarter to ten", converter.convertToSpokenForm("9:45"));
	        assertEquals("ten to eleven", converter.convertToSpokenForm("10:50"));
	        assertEquals("five to twelve", converter.convertToSpokenForm("11:55"));
	        assertEquals("midnight", converter.convertToSpokenForm("00:00"));
	        assertEquals("noon", converter.convertToSpokenForm("12:00"));
	    } catch (InvalidTimeFormatException e) {
	        e.printStackTrace();
	    }
	}


    @Test(expected = InvalidTimeFormatException.class)
    public void testInvalidInputTime() throws InvalidTimeFormatException {
        BritishTimeConverter converter = new BritishTimeConverter(new MinuteSpokenImpl(),new HourSpokenImpl());
        converter.convertToSpokenForm("25:00"); // Invalid hour
    }

    @Test
    public void testCornerCases() {
        BritishTimeConverter converter = new BritishTimeConverter(new MinuteSpokenImpl(),new HourSpokenImpl());

        try {
            assertEquals("midnight", converter.convertToSpokenForm("00:00"));
            assertEquals("noon", converter.convertToSpokenForm("12:00"));
        } catch (InvalidTimeFormatException e) {
            e.printStackTrace();
        }
    }
}
