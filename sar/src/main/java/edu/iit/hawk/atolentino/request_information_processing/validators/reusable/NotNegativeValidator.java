package edu.iit.hawk.atolentino.request_information_processing.validators.reusable;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;

public class NotNegativeValidator implements RequestInformationValidator {
	private double number;
	private String errorMessage;
	
	public NotNegativeValidator(double number, String errorMessage) {
		this.number = number;
		this.errorMessage = errorMessage;
	}

	@Override
	public void validate() throws IllegalArgumentException {
		if (number < 0) {
			throw new IllegalArgumentException(errorMessage);
		}
	}
	
}
