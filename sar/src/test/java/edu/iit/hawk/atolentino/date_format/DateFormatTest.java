package edu.iit.hawk.atolentino.date_format;

import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.response_generator.boundaries.DateFormatter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.TimeZone;


public class DateFormatTest {
	private static final long TIME_STAMP = 1586122589262L;
	private static final String DEFAULT_FORMAT = "05-Apr-2020, 16:36:29";
	private static final String TIME_FORMAT = "16:36";
	private static final String DAY_MONTH_YEAR_FORMAT = "05-Apr-2020";
	private TimeZone timeZone = TimeZone.getTimeZone("America/Chicago");

	
	@Test
	void testDateFormatReturnsExpectedDefaultFormatOutput() {
		Calendar c = Calendar.getInstance();
		c.setTimeZone(timeZone);
		c.setTimeInMillis(TIME_STAMP);
		assertEquals(DEFAULT_FORMAT, DateFormatter.getDefaultFormat(c));
	}
	
	@Test
	void testDateFormatReturnsExpectedTimeFormatOutput() {
		Calendar c = Calendar.getInstance();
		c.setTimeZone(timeZone);
		c.setTimeInMillis(TIME_STAMP);
		assertEquals(TIME_FORMAT, DateFormatter.getTimeFormat(c));		
	}
	
	@Test
	void testDateFormatReturnsExpectedDayMonthYearFormatOutput() {
		Calendar c = Calendar.getInstance();
		c.setTimeZone(timeZone);
		c.setTimeInMillis(TIME_STAMP);
		assertEquals(DAY_MONTH_YEAR_FORMAT, DateFormatter.getDayMonthYearFormat(c));		
	}
}
