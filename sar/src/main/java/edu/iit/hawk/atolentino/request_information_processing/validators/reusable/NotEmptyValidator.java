package edu.iit.hawk.atolentino.request_information_processing.validators.reusable;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;

public class NotEmptyValidator implements RequestInformationValidator {
	public String data;
	public String errorMessage;
	
	public NotEmptyValidator(String data, String errorMessage) {
		this.data = data;
		this.errorMessage = errorMessage;
	}
	
	@Override
	public void validate() throws IllegalArgumentException {
		if (data.isEmpty()) {
			throw new IllegalArgumentException(errorMessage);
		}
	}

}
