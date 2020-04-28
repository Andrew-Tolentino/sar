package edu.iit.hawk.atolentino.request_information_processing.creators;

import com.google.gson.JsonSyntaxException;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationCreator;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;
import edu.iit.hawk.atolentino.request_information_processing.entities.NillRequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.RateInformation;
import edu.iit.hawk.atolentino.request_information_processing.parsers.RateInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.validators.RateInformationValidator;

public class RateInformationCreator implements RequestInformationCreator {

	@Override
	public RequestInformation create(String body) {
		try {
			RequestInformationParser rateInformationParser = new RateInformationParser();
			RateInformation rateInformation = (RateInformation) rateInformationParser.parse(body);
			RequestInformationValidator rateInformationValidator = new RateInformationValidator(rateInformation);
			rateInformationValidator.validate();
			return rateInformation;
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
