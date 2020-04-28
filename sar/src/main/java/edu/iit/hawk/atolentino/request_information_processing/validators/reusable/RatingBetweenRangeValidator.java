package edu.iit.hawk.atolentino.request_information_processing.validators.reusable;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;

public class RatingBetweenRangeValidator implements RequestInformationValidator {
	private Integer value;
	private String errorMessage;
	
	private static final int MAX = 5;
	private static final int MIN = 1;
	
	public RatingBetweenRangeValidator(Integer value, String errorMessage) {
		this.value = value;
		this.errorMessage = errorMessage;
	}
	
	@Override
	public void validate() throws IllegalArgumentException {
		if (value < MIN || value > MAX) {
			throw new IllegalArgumentException(errorMessage);
		}
		
	}
}
