package edu.iit.hawk.atolentino.request_information_processing.validators.reusable;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;

public class StateValidator implements RequestInformationValidator {
	private String state;
	private String errorMessage;
	
	public StateValidator(String state, String errorMessage) {
		this.state = state;
		this.errorMessage = errorMessage;
	}
	
	private boolean isStateValid() {
		for (StateAbbreviations stateAbbrev : StateAbbreviations.values()) {
			if (stateAbbrev.getState().equals(state)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void validate() throws IllegalArgumentException {
		if (!isStateValid()) {
			throw new IllegalArgumentException(errorMessage);
		}	
	}
}
