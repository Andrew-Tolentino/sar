package edu.iit.hawk.atolentino.request_information_processing.creators;

import com.google.gson.JsonSyntaxException;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationCreator;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;
import edu.iit.hawk.atolentino.request_information_processing.entities.NillRequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.RideInformation;
import edu.iit.hawk.atolentino.request_information_processing.parsers.RideInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.validators.RideInformationValidator;

public class RideInformationCreator implements RequestInformationCreator {

	@Override
	public RequestInformation create(String body) {
		try {
			RequestInformationParser rideInformationParser = new RideInformationParser();
			RideInformation rideInformation = (RideInformation) rideInformationParser.parse(body);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			rideInformationValidator.validate();
			return rideInformation;
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
