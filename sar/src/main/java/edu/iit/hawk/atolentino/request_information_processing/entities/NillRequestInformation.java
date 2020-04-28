package edu.iit.hawk.atolentino.request_information_processing.entities;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;

public final class NillRequestInformation extends RequestInformation {
	private final String errorMessage;
	
	public NillRequestInformation(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	@Override
	public boolean isNill() {
		return true;
	}

}
