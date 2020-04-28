package edu.iit.hawk.atolentino.request_information_processing.entities;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;

public class ProfileInformation extends RequestInformation {
	private final String firstName;
	private final String lastName;
	private final String phoneNumber;
	private final String profilePicture;
	private final Boolean active;
		
	public ProfileInformation(String firstName, String lastName, String phoneNumber, String profilePicture, Boolean active) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.profilePicture = profilePicture;
		this.active = active;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getProfilePicture() {
		return profilePicture;
	}
	
	public Boolean isActive() {
		return active;
	}
	
	public boolean matchesFilter(String key) {
    	key = key.toLowerCase();
    	String firstNameLowerCase = firstName.toLowerCase();
    	String lastNameLowerCase = lastName.toLowerCase();
    	
    	if (firstNameLowerCase.contains(key) || lastNameLowerCase.contains(key) || phoneNumber.contains(key)) {
    		return true;
    	}
    	return false;
	}

	@Override
	public boolean isNill() {
		return false;
	}
}
