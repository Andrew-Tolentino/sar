package edu.iit.hawk.atolentino.request_information_processing.validators.reusable;

import java.text.DateFormat;
import java.text.ParseException;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;
import edu.iit.hawk.atolentino.response_generator.boundaries.DateFormatter;

public class DateFormatValidator implements RequestInformationValidator {
	private  String date;
	private String errorMessage;
		
	public DateFormatValidator(String date, String errorMessage) {
		this.date = date;
		this.errorMessage = errorMessage;
	}
	
	@Override
	public void validate() throws IllegalArgumentException {
		DateFormat dateFormat = DateFormatter.CREATED_DAY_MONTH_YEAR_FORMAT;
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(date);
		}
		catch (ParseException ex) {
			ex.printStackTrace();
			throw new IllegalArgumentException(errorMessage);
		}
	}

}
