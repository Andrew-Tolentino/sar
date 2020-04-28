package edu.iit.hawk.atolentino.request_information_processing.creators;

import com.google.gson.JsonSyntaxException;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationCreator;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;
import edu.iit.hawk.atolentino.request_information_processing.entities.NillRequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.UpdateJoinRequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.parsers.UpdateJoinRequestInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.validators.UpdateJoinRequestInformationValidator;

public class UpdateJoinRequestInformationCreator implements RequestInformationCreator {

	@Override
	public RequestInformation create(String body) {
		try {
			RequestInformationParser updateJoinRequestInformationParser = new UpdateJoinRequestInformationParser();
			UpdateJoinRequestInformation updateJoinRequestInformation = (UpdateJoinRequestInformation) updateJoinRequestInformationParser.parse(body);
			RequestInformationValidator updateJoinInformationValidator = new UpdateJoinRequestInformationValidator(updateJoinRequestInformation);
			updateJoinInformationValidator.validate();
			return updateJoinRequestInformation;
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
