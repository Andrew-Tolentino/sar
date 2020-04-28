package edu.iit.hawk.atolentino.response_generator.boundaries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class DateFormatter {
	public static final String DEFAULT_FORMAT = "dd-MMM-yyyy, HH:mm:ss";
	public static final SimpleDateFormat CREATED_DEFAULT_FORMAT = new SimpleDateFormat(DEFAULT_FORMAT);
	
	public static final String TIME_FORMAT = "HH:mm";
	public static final SimpleDateFormat CREATED_TIME_FORMAT = new SimpleDateFormat(TIME_FORMAT);
	
	public static final String DAY_MONTH_YEAR_FORMAT = "dd-MMM-yyyy";
	public static final SimpleDateFormat CREATED_DAY_MONTH_YEAR_FORMAT = new SimpleDateFormat(DAY_MONTH_YEAR_FORMAT);
	
	public static String getDefaultFormat(Calendar c) {
		return CREATED_DEFAULT_FORMAT.format(c.getTime());
	}
	
	public static String getTimeFormat(Calendar c) {
		return CREATED_TIME_FORMAT.format(c.getTime());
	}
	
	public static String getDayMonthYearFormat(Calendar c) {
		return CREATED_DAY_MONTH_YEAR_FORMAT.format(c.getTime());
	}
	
	public static long getTimeStampFromDayMonthYear(String dateStr) throws ParseException {
		if (dateStr.isEmpty()) {
			return -1;
		}
		
		Date date = CREATED_DAY_MONTH_YEAR_FORMAT.parse(dateStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis() / 1000;
	}
}
