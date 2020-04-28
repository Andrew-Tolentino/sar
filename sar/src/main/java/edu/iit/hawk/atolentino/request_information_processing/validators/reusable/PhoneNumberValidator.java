package edu.iit.hawk.atolentino.request_information_processing.validators.reusable;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;

public class PhoneNumberValidator implements RequestInformationValidator {
	private String[] phoneNumberSplit;
	private int numberOfDashes;
	private String errorMessage;
	
	private static final int REQUIRED_NUMBER_OF_DIGITS_IN_AREA_CODE = 3;
	private static final int REQUIRED_NUMBER_OF_DASHES = 2;
	private static final int REQUIRED_NUMBER_OF_DIGITS_IN_PHONE_NUMBER = 7;
	private static final int AREA_CODE_INDEX = 0;
	
	public PhoneNumberValidator(String phoneNumber, String errorMessage) {
		phoneNumberSplit = parsePhoneNumber(phoneNumber);
		numberOfDashes = calculateNumberOfDashes(phoneNumber);
		this.errorMessage = errorMessage;
	}
	
	private String[] parsePhoneNumber(String phoneNumber) {
		if (phoneNumber == null) {
			return new String[0];
		}
		String[] phoneNumberSplit = phoneNumber.split("-");
		return phoneNumberSplit;
	}
	
	private int calculateNumberOfDashes(String phoneNumber) {
		if (phoneNumber == null) {
			return 0;
		}
		int dashCounter = 0;
		for (int i = 0; i < phoneNumber.length(); i++) {
			if (phoneNumber.charAt(i)  == '-') {
				dashCounter++;
			}
		}
		return dashCounter;
	}
	
	private String getRemainingPhoneNumber() {
		String remainingPhoneNumber = "";
		for (int i = AREA_CODE_INDEX + 1; i < phoneNumberSplit.length; i++) {
			remainingPhoneNumber += phoneNumberSplit[i];
		}
		return remainingPhoneNumber;
	}
	
	private String getAreaCode() {
		return phoneNumberSplit[AREA_CODE_INDEX];
	}
	
	private void validateAreaCode() throws IllegalArgumentException {
		try {
			String areaCode = getAreaCode();
			Integer.parseInt(areaCode);
			if (areaCode.length() != REQUIRED_NUMBER_OF_DIGITS_IN_AREA_CODE) {
				throw new IllegalArgumentException(errorMessage);
			}
		}
		catch(NumberFormatException ex) {
			ex.printStackTrace();
			throw new IllegalArgumentException(errorMessage);
		}
	}
	
	private void validateRemainingPhoneNumber() throws IllegalArgumentException {
		try {
			String remainingPhoneNumber = getRemainingPhoneNumber();
			Integer.parseInt(remainingPhoneNumber);
			if (remainingPhoneNumber.length() != REQUIRED_NUMBER_OF_DIGITS_IN_PHONE_NUMBER) {
				throw new IllegalArgumentException(errorMessage);
			}
		}
		catch(NumberFormatException ex) {
			throw new IllegalArgumentException(errorMessage);
		}
	}
	
	private void validatePhoneNumberDashFormat() throws IllegalArgumentException {
		if (numberOfDashes != REQUIRED_NUMBER_OF_DASHES) {
			throw new IllegalArgumentException(errorMessage);
		}
	}
	
	@Override
	public void validate() throws IllegalArgumentException {
		validateAreaCode();
		validateRemainingPhoneNumber();
		validatePhoneNumberDashFormat();		
	}

}
