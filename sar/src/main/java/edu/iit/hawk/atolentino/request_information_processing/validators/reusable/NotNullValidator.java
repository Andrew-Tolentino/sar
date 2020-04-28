package edu.iit.hawk.atolentino.request_information_processing.validators.reusable;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;

public class NotNullValidator implements RequestInformationValidator {
	private Object o;
	private String errorMessage;
	
	public NotNullValidator(Object o, String errorMessage) {
		this.o = o;
		this.errorMessage = errorMessage;
	}

	@Override
	public void validate() throws IllegalArgumentException {
		if (o  == null) {
			throw new IllegalArgumentException(errorMessage);
		}
	}
}
