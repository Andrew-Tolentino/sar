package edu.iit.hawk.atolentino.request_information_processing.validators;

import java.util.ArrayList;
import java.util.List;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;
import edu.iit.hawk.atolentino.request_information_processing.entities.RateInformation;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.NotNullValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.RatingBetweenRangeValidator;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;

public class RateInformationValidator implements RequestInformationValidator {
	private List<RequestInformationValidator> validators = new ArrayList<RequestInformationValidator>();

	public RateInformationValidator(RateInformation rateInformation) {
		validators.add(new NotNullValidator(
				rateInformation.getRid(),
				ResponseBodyUtility.INVALID_RIDE_ID_ERROR_MESSAGE
				));
		
		validators.add(new NotNullValidator(
				rateInformation.getAid(),
				ResponseBodyUtility.INVALID_ACCOUNT_ID_ERROR_MESSAGE
				));
		
		validators.add(new NotNullValidator(
				rateInformation.getRating(),
				ResponseBodyUtility.INVALID_RATING_ERROR_MESSAGE
				));
		validators.add(new RatingBetweenRangeValidator(
				rateInformation.getRating(),
				ResponseBodyUtility.INVALID_RATING_ERROR_MESSAGE
				));
		
		validators.add(new NotNullValidator(
				rateInformation.getComment(),
				ResponseBodyUtility.INVALID_COMMENT_ERROR_MESSAGE
				));
	}
	
	@Override
	public void validate() throws IllegalArgumentException {
		for(RequestInformationValidator validator : validators) {
			validator.validate();
		}		
	}

}
