package edu.iit.hawk.atolentino.request_information_processing.validators.reusable;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;

public class IsNullValidator<T> implements RequestInformationValidator {
	private T value;
	private String errorMessage;
	
	public IsNullValidator(T value, String errorMessage) {
		this.value = value;
		this.errorMessage = errorMessage;
	}
	
	@Override
	public void validate() throws IllegalArgumentException {
		if (value != null) {
			throw new IllegalArgumentException(errorMessage);
		}
		
	}

}
