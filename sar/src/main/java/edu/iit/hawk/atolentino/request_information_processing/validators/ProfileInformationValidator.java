package edu.iit.hawk.atolentino.request_information_processing.validators;

import java.util.ArrayList;
import java.util.List;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;
import edu.iit.hawk.atolentino.request_information_processing.entities.ProfileInformation;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.ActiveValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.NameValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.NotEmptyValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.NotNullValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.PhoneNumberValidator;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;

public class ProfileInformationValidator implements RequestInformationValidator {
	private List<RequestInformationValidator> validators = new ArrayList<RequestInformationValidator>();
	
	public ProfileInformationValidator(ProfileInformation profileInformation, boolean systemActiveStatus) {
		validators.add(new NotNullValidator(
				profileInformation.getFirstName(),
				ResponseBodyUtility.INVALID_FIRST_NAME_ERROR_MESSAGE
				));
		validators.add(new NotEmptyValidator(
				profileInformation.getFirstName(),
				ResponseBodyUtility.INVALID_FIRST_NAME_ERROR_MESSAGE
				));
		validators.add(new NameValidator(
				profileInformation.getFirstName(),
				ResponseBodyUtility.INVALID_FIRST_NAME_ERROR_MESSAGE
				));
		
		
		validators.add(new NotNullValidator(
				profileInformation.getLastName(),
				ResponseBodyUtility.INVALID_LAST_NAME_ERROR_MESSAGE
				));
		validators.add(new NotEmptyValidator(
				profileInformation.getLastName(),
				ResponseBodyUtility.INVALID_LAST_NAME_ERROR_MESSAGE
				));
		validators.add(new NameValidator(
				profileInformation.getLastName(),
				ResponseBodyUtility.INVALID_LAST_NAME_ERROR_MESSAGE
				));
		
		
		validators.add(new NotNullValidator(
				profileInformation.getPhoneNumber(),
				ResponseBodyUtility.INVALID_PHONE_ERROR_MESSAGE
				));
		validators.add(new NotEmptyValidator(
				profileInformation.getPhoneNumber(),
				ResponseBodyUtility.INVALID_PHONE_ERROR_MESSAGE
				));
		validators.add(new PhoneNumberValidator(
				profileInformation.getPhoneNumber(),
				ResponseBodyUtility.INVALID_PHONE_ERROR_MESSAGE
				));
		
		
		validators.add(new NotNullValidator(
				profileInformation.getProfilePicture(),
				ResponseBodyUtility.INVALID_PICTURE_ERROR_MESSAGE
				));
		validators.add(new NotEmptyValidator(
				profileInformation.getProfilePicture(),
				ResponseBodyUtility.INVALID_PICTURE_ERROR_MESSAGE
				));
		
		
		validators.add(new NotNullValidator(
				profileInformation.isActive(),
				ResponseBodyUtility.INVALID_IS_ACTIVE_ERROR_MESSAGE
				));
		validators.add(new ActiveValidator(
				systemActiveStatus, 
				profileInformation.isActive(), 
				ResponseBodyUtility.INVALID_IS_ACTIVE_ERROR_MESSAGE
				));
	}

	@Override
	public void validate() throws IllegalArgumentException {
		for(RequestInformationValidator validator : validators) {
			validator.validate();
		}
	}
}
