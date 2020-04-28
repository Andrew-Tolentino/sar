package edu.iit.hawk.atolentino.request_information_processing.validators;

import java.util.ArrayList;
import java.util.List;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;
import edu.iit.hawk.atolentino.request_information_processing.entities.UpdateJoinRequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.NotNullValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.TrueValueValidator;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.PatchType;

public class UpdateJoinRequestInformationValidator implements RequestInformationValidator {
	private List<RequestInformationValidator> validators = new ArrayList<RequestInformationValidator>();

	public UpdateJoinRequestInformationValidator(UpdateJoinRequestInformation updateJoinRequestInformation) {
		String errorMessage;
		
		validators.add(new NotNullValidator(
				updateJoinRequestInformation.getAid(), 
				ResponseBodyUtility.INVALID_ACCOUNT_ID_ERROR_MESSAGE
				));
		
		if (updateJoinRequestInformation.getType() == PatchType.RIDE_CONFIRMED) {
			errorMessage = ResponseBodyUtility.INVALID_RIDE_CONFIRMED_ERROR_MESSAGE;
			validators.add(new NotNullValidator(
					updateJoinRequestInformation.getStatus(), 
					errorMessage
					));
		}
		else if (updateJoinRequestInformation.getType() == PatchType.PICKUP_CONFIRMED) {
			errorMessage = ResponseBodyUtility.INVALID_PICKUP_CONFIRMED_ERROR_MESSAGE;
			validators.add(new NotNullValidator(
					updateJoinRequestInformation.getStatus(), 
					errorMessage
					));
			validators.add(new TrueValueValidator(
					updateJoinRequestInformation.getStatus(), 
					errorMessage
					));
		}
	}
	
	@Override
	public void validate() throws IllegalArgumentException {
		for(RequestInformationValidator validator : validators) {
			validator.validate();
		}		
	}
}
