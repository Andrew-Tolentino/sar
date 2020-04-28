package edu.iit.hawk.atolentino.request_information_processing.validators;

import java.util.ArrayList;
import java.util.List;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;
import edu.iit.hawk.atolentino.request_information_processing.entities.JoinRequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.GreaterThanZeroValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.IsNullValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.NotNullValidator;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;

public class JoinRequestInformationValidator implements RequestInformationValidator {
	private List<RequestInformationValidator> validators = new ArrayList<RequestInformationValidator>();

	public JoinRequestInformationValidator(JoinRequestInformation joinRequestInformation) {
		validators.add(new NotNullValidator(
				joinRequestInformation.getAid(), 
				ResponseBodyUtility.INVALID_ACCOUNT_ID_ERROR_MESSAGE
				));
		
		validators.add(new NotNullValidator(
				joinRequestInformation.getNumberOfPassengers(), 
				ResponseBodyUtility.INVALID_PASSENGERS_ERROR_MESSAGE
				));
		validators.add(new GreaterThanZeroValidator(
				joinRequestInformation.getNumberOfPassengers(), 
				ResponseBodyUtility.INVALID_PASSENGERS_ERROR_MESSAGE
				));
		
		validators.add(new IsNullValidator<Boolean>(
				joinRequestInformation.getRideConfirmed(),
				ResponseBodyUtility.INVALID_RIDE_CONFIRMED_ERROR_MESSAGE
				));
		
		validators.add(new IsNullValidator<Boolean>(
				joinRequestInformation.getPickUpConfirmed(),
				ResponseBodyUtility.INVALID_PICKUP_CONFIRMED_ERROR_MESSAGE
				));
	}
	
	@Override
	public void validate() throws IllegalArgumentException {
		for(RequestInformationValidator validator : validators) {
			validator.validate();
		}
	}

}
