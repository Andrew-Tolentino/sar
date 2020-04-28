package edu.iit.hawk.atolentino.response_generator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.response_generator.boundaries.DateFormatter;

public class DateFormatterTest {
	@Test
	void test_CREATEDDEFAULTFORMAT_producesValidDateCreatedString() {
		String dateCreated = "03-Mar-2020, 08:13:57";
		SimpleDateFormat CREATED_DEFAULT_FORMAT = DateFormatter.CREATED_DEFAULT_FORMAT;
		assertDoesNotThrow(() -> {
			Date date = CREATED_DEFAULT_FORMAT.parse(dateCreated);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			assertEquals(dateCreated, DateFormatter.getDefaultFormat(calendar));
		});
	}
	
	@Test
	void test_CREATETIMEFORMAT_producesValidDateCreatedString() {
		String dateCreated = "08:13";
		SimpleDateFormat CREATED_DEFAULT_FORMAT = DateFormatter.CREATED_TIME_FORMAT;
		assertDoesNotThrow(() -> {
			Date date = CREATED_DEFAULT_FORMAT.parse(dateCreated);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			assertEquals(dateCreated, DateFormatter.getTimeFormat(calendar));
		});
	}
	
	@Test
	void test_CREATEDAYMONTHYEARFORMAT_producesValidDateCreatedString() {
		String dateCreated = "03-Mar-2020";
		SimpleDateFormat CREATED_DAY_MONTH_YEAR_FORMAT = DateFormatter.CREATED_DAY_MONTH_YEAR_FORMAT;
		assertDoesNotThrow(() -> {
			Date date = CREATED_DAY_MONTH_YEAR_FORMAT.parse(dateCreated);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			assertEquals(dateCreated, DateFormatter.getDayMonthYearFormat(calendar));
		});
	}
	
	@Test
	void test_getTimeStampFromDayMonthYear_producesAppropriateTimeStamp() {
		String dateCreated = "03-Mar-2020";
		long expectedTimeStamp = 1583215200;
		assertDoesNotThrow(() -> {
			long timeStamp = DateFormatter.getTimeStampFromDayMonthYear(dateCreated);
			assertEquals(expectedTimeStamp, timeStamp);
		});
	}
}
