package edu.iit.hawk.atolentino.request_information_processing.validators.reusable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;
import edu.iit.hawk.atolentino.response_generator.boundaries.DateFormatter;

public class TimeFormatValidator implements RequestInformationValidator {
	private String time;
	private String errorMessage;
	
	public TimeFormatValidator(String time, String errorMessage) {
		this.time = time;
		this.errorMessage = errorMessage;
	}
	
	@Override
	public void validate() throws IllegalArgumentException {
		DateFormat dateFormat = new SimpleDateFormat(DateFormatter.TIME_FORMAT);
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(time);
		} catch (ParseException ex) {
			throw new IllegalArgumentException(errorMessage);
		}		
	}
}
