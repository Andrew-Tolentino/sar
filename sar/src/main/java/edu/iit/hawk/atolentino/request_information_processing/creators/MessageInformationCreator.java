package edu.iit.hawk.atolentino.request_information_processing.creators;

import com.google.gson.JsonSyntaxException;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationCreator;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;
import edu.iit.hawk.atolentino.request_information_processing.entities.MessageInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.NillRequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.parsers.MessageInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.validators.MessageInformationValidator;

public class MessageInformationCreator implements RequestInformationCreator {

	@Override
	public RequestInformation create(String body) {
		try {
			RequestInformationParser messageInformationParser = new MessageInformationParser();
			MessageInformation messageInformation = (MessageInformation) messageInformationParser.parse(body);
			RequestInformationValidator messageInformationValidator = new MessageInformationValidator(messageInformation);
			messageInformationValidator.validate();
			return messageInformation;
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
