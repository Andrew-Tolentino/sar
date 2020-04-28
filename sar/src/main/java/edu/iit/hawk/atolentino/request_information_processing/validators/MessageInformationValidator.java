package edu.iit.hawk.atolentino.request_information_processing.validators;

import java.util.ArrayList;
import java.util.List;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;
import edu.iit.hawk.atolentino.request_information_processing.entities.MessageInformation;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.NotNullValidator;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;

public class MessageInformationValidator implements RequestInformationValidator {
	private List<RequestInformationValidator> validators = new ArrayList<RequestInformationValidator>();
	
	public MessageInformationValidator(MessageInformation messageInformation) {
		validators.add(new NotNullValidator(
				messageInformation.getAid(), 
				ResponseBodyUtility.INVALID_ACCOUNT_ID_ERROR_MESSAGE
				));
		
		validators.add(new NotNullValidator(
				messageInformation.getMsg(), 
				ResponseBodyUtility.INVALID_MESSAGE_ERROR_MESSAGE
				));
	}

	@Override
	public void validate() throws IllegalArgumentException {
		for(RequestInformationValidator validator : validators) {
			validator.validate();
		}
	}

}
