package edu.iit.hawk.atolentino.request_information_processing.validators.reusable;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;

public class TrueValueValidator implements RequestInformationValidator {
	boolean value;
	String errorMessage;
	
	public TrueValueValidator(boolean value, String errorMessage) {
		this.value = value;
		this.errorMessage = errorMessage;
	}
	
	@Override
	public void validate() throws IllegalArgumentException {
		if (!value) {
			throw new IllegalArgumentException(errorMessage);
		}
	}

}
