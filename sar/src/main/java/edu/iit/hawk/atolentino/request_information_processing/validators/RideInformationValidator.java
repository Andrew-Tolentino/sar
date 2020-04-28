package edu.iit.hawk.atolentino.request_information_processing.validators;

import java.util.ArrayList;
import java.util.List;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;
import edu.iit.hawk.atolentino.request_information_processing.entities.RideInformation;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.DateFormatValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.GreaterThanZeroValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.NotEmptyValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.NotNegativeValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.NotNullValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.StateValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.TimeFormatValidator;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;

public class RideInformationValidator implements RequestInformationValidator {
	private List<RequestInformationValidator> validators = new ArrayList<RequestInformationValidator>();

	public RideInformationValidator(RideInformation rideInformation) {
		validators.add(new NotNullValidator(
				rideInformation.getAid(), 
				ResponseBodyUtility.INVALID_ACCOUNT_ID_ERROR_MESSAGE
				));
		
		validators.add(new NotNullValidator(
				rideInformation.getFromCity(), 
				ResponseBodyUtility.INVALID_FROM_CITY_ERROR_MESSAGE
				));
		validators.add(new NotEmptyValidator(
				rideInformation.getFromCity(), 
				ResponseBodyUtility.INVALID_FROM_CITY_ERROR_MESSAGE
				));
		
		validators.add(new NotNullValidator(
				rideInformation.getToCity(), 
				ResponseBodyUtility.INVALID_TO_CITY_ERROR_MESSAGE
				));
		validators.add(new NotEmptyValidator(
				rideInformation.getToCity(), 
				ResponseBodyUtility.INVALID_TO_CITY_ERROR_MESSAGE
				));
		
		validators.add(new NotNullValidator(
				rideInformation.getMake(), 
				ResponseBodyUtility.INVALID_MAKE_ERROR_MESSAGE
				));
		validators.add(new NotEmptyValidator(
				rideInformation.getMake(), 
				ResponseBodyUtility.INVALID_MAKE_ERROR_MESSAGE
				));
		
		validators.add(new NotNullValidator(
				rideInformation.getModel(), 
				ResponseBodyUtility.INVALID_MODEL_ERROR_MESSAGE
				));
		validators.add(new NotEmptyValidator(
				rideInformation.getModel(), 
				ResponseBodyUtility.INVALID_MODEL_ERROR_MESSAGE
				));
		
		validators.add(new NotNullValidator(
				rideInformation.getColor(), 
				ResponseBodyUtility.INVALID_COLOR_ERROR_MESSAGE
				));
		validators.add(new NotEmptyValidator(
				rideInformation.getColor(), 
				ResponseBodyUtility.INVALID_COLOR_ERROR_MESSAGE
				));
		
		validators.add(new NotNullValidator(
				rideInformation.getPlateSerial(), 
				ResponseBodyUtility.INVALID_PLATE_SERIAL_ERROR_MESSAGE
				));
		validators.add(new NotEmptyValidator(
				rideInformation.getPlateSerial(), 
				ResponseBodyUtility.INVALID_PLATE_SERIAL_ERROR_MESSAGE
				));
		
		validators.add(new NotNullValidator(
				rideInformation.getDate(),
				ResponseBodyUtility.INVALID_DATE_ERROR_MESSAGE
				));
		validators.add(new DateFormatValidator(
				rideInformation.getDate(),
				ResponseBodyUtility.INVALID_DATE_ERROR_MESSAGE
				));
		
		validators.add(new NotNullValidator(
				rideInformation.getTime(),
				ResponseBodyUtility.INVALID_TIME_ERROR_MESSAGE
				));
		validators.add(new TimeFormatValidator(
				rideInformation.getTime(),
				ResponseBodyUtility.INVALID_TIME_ERROR_MESSAGE
				));
		
		validators.add(new NotNullValidator(
				rideInformation.getMaxPassengers(), 
				ResponseBodyUtility.INVALID_MAX_PASSENGERS_ERROR_MESSAGE
				));
		validators.add(new GreaterThanZeroValidator(
				rideInformation.getMaxPassengers(), 
				ResponseBodyUtility.INVALID_MAX_PASSENGERS_ERROR_MESSAGE
				));
		
		if (rideInformation.getAmountPerPassenger() != null) {
			validators.add(new NotNegativeValidator(
					rideInformation.getAmountPerPassenger(),
					ResponseBodyUtility.INVALID_AMOUNT_PER_PASSENGER_ERROR_MESSAGE
					));
		}
	
		validators.add(new NotNullValidator(
				rideInformation.getPlateState(), 
				ResponseBodyUtility.INVALID_PLATE_STATE_ERROR_MESSAGE
				));	
		validators.add(new StateValidator(
				rideInformation.getPlateState(), 
				ResponseBodyUtility.INVALID_PLATE_STATE_ERROR_MESSAGE
				));	
		
		validators.add(new NotNullValidator(
				rideInformation.getConditions(), 
				ResponseBodyUtility.INVALID_CONDITIONS_ERROR_MESSAGE
				));	
	}
	
	
	@Override
	public void validate() throws IllegalArgumentException {
		for(RequestInformationValidator validator : validators) {
			validator.validate();
		}
	}

}
