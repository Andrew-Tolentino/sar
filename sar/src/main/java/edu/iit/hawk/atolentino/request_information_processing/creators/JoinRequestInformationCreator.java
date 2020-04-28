package edu.iit.hawk.atolentino.request_information_processing.creators;

import com.google.gson.JsonSyntaxException;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationCreator;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;
import edu.iit.hawk.atolentino.request_information_processing.entities.JoinRequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.NillRequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.parsers.JoinRequestInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.validators.JoinRequestInformationValidator;

public class JoinRequestInformationCreator implements RequestInformationCreator {

	@Override
	public RequestInformation create(String body) {
		try {
			RequestInformationParser joinRequestInformationParser = new JoinRequestInformationParser();
			JoinRequestInformation joinRequestInformation = (JoinRequestInformation) joinRequestInformationParser.parse(body);
			RequestInformationValidator joinRequestInformationValidator = new JoinRequestInformationValidator(joinRequestInformation);
			joinRequestInformationValidator.validate();
			return joinRequestInformation;
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
