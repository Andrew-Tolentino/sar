package edu.iit.hawk.atolentino.request_information_processing.validators.reusable;

import java.util.regex.Pattern;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;

public class NameValidator implements RequestInformationValidator {
	private final static String LETTERS_ONLY_REGEX = "[a-zA-Z]+";
	private String name;
	private String errorMessage;
	
	public NameValidator(String name, String errorMessage) {
		this.name = name;
		this.errorMessage = errorMessage;
	}
	 
	private void validateNotNull() throws IllegalArgumentException {
		if (name == null) {
			throw new IllegalArgumentException(errorMessage);
		}
	}
	
	private void validateName() throws IllegalArgumentException {
		if (!Pattern.matches(LETTERS_ONLY_REGEX, name)) {
			throw new IllegalArgumentException(errorMessage);
		}
	}
	
	@Override
	public void validate() throws IllegalArgumentException {
		validateNotNull();
		validateName();
	}

}
