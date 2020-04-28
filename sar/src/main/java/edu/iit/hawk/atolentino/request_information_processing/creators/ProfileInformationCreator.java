package edu.iit.hawk.atolentino.request_information_processing.creators;

import com.google.gson.JsonSyntaxException;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationCreator;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;
import edu.iit.hawk.atolentino.request_information_processing.entities.NillRequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.ProfileInformation;
import edu.iit.hawk.atolentino.request_information_processing.parsers.ProfileInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.validators.ProfileInformationValidator;

public class ProfileInformationCreator implements RequestInformationCreator {
	private final boolean systemActiveStatus;
	
	public static ProfileInformationCreator NEW_ACCOUNT() {
		return new ProfileInformationCreator(false);
	}
	
	public static ProfileInformationCreator ACTIVE_ACCOUNT() {
		return new ProfileInformationCreator(true);
	}		
	public ProfileInformationCreator(boolean systemActiveStatus) {
		this.systemActiveStatus = systemActiveStatus;
	}

	@Override
	public RequestInformation create(String body) {
		try {
			RequestInformationParser profileInformationParser = new ProfileInformationParser();
			ProfileInformation profileInformation = (ProfileInformation) profileInformationParser.parse(body);
			RequestInformationValidator profileInformationValidator = new ProfileInformationValidator(profileInformation, systemActiveStatus);
			profileInformationValidator.validate();
			return profileInformation;
		}
		catch(IllegalArgumentException ex) {
			return new NillRequestInformation(ex.getMessage());
		}
		catch(NullPointerException ex) {
			return new NillRequestInformation(ex.getMessage());
		}
		catch(JsonSyntaxException ex) {
			return new NillRequestInformation(ex.getMessage());
		}
		
	}

}
