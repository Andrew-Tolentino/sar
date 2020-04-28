package edu.iit.hawk.atolentino.request_information_processing.validators.reusable;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;

public class ActiveValidator implements RequestInformationValidator {
	private Boolean systemActiveStatus;
	private Boolean requestActiveStatus;
	private String errorMessage;
	
	public ActiveValidator(Boolean systemActiveStatus, Boolean requestActiveStatus, String errorMessage) {
		this.systemActiveStatus = systemActiveStatus;
		this.requestActiveStatus = requestActiveStatus;
		this.errorMessage = errorMessage;
	}

	@Override
	public void validate() throws IllegalArgumentException {
		if (systemActiveStatus != requestActiveStatus) {
			throw new IllegalArgumentException(errorMessage);
		}
		
	}

}
