package edu.iit.hawk.atolentino.response_generator.boundaries;

public interface ResponseBodyUtility {
	public static final String RIDE_ID_KEY = "rid";
	public static final String MESSAGE_ID_KEY = "mid";
	public static final String ACCOUNT_ID_KEY = "aid";
	public static final String REPORT_ID_KEY = "pid";
	public static final String RATING_ID_KEY = "sid";
	public static final String JOIN_RIDE_ID_REQUEST_KEY = "jid";
	
	public static final String INVALID_RIDE_ID_ERROR_MESSAGE = "Invalid value for rid";
	public static final String INVALID_MESSAGE_ID_ERROR_MESSAGE = "Invalid value for mid";
	public static final String INVALID_ACCOUNT_ID_ERROR_MESSAGE = "Invalid value for aid";
	public static final String INVALID_REPORT_ID_ERROR_MESSAGE = "Invalid value for pid";
	public static final String INVALID_JOIN_RIDE_ID_ERROR_MESSAGE = "Invalid value for jid";
	
	public static final String NAME_KEY = "name";
	public static final String DATE_CREATED_KEY = "date_created";
	public static final String FIRST_NAME_KEY = "first_name";
	public static final String LAST_NAME_KEY = "last_name";
	public static final String PHONE_NUMBER_KEY = "phone";
	public static final String PROFILE_PICTURE_KEY = "picture";
	public static final String IS_ACTIVE_KEY = "is_active";
	
	public static final String INVALID_FIRST_NAME_ERROR_MESSAGE = "The first name appears to be invalid.";
	public static final String INVALID_LAST_NAME_ERROR_MESSAGE = "The last name appears to be invalid.";
	public static final String INVALID_PHONE_ERROR_MESSAGE = "Invalid phone number";
	public static final String INVALID_PICTURE_ERROR_MESSAGE = "Invalid profile picture";
	public static final String INVALID_IS_ACTIVE_ERROR_MESSAGE = "Invalid value for is_active";
	
	public static final String ERROR_RESPONSE_TYPE_KEY = "type";
	public static final String ERROR_RESPONSE_TITLE_KEY = "title";
	public static final String ERROR_RESPONSE_DETAIL_KEY = "detail";
	public static final String ERROR_RESPONSE_STATUS_KEY = "status";
	public static final String ERROR_RESPONSE_INSTANCE_KEY = "instance";
	public static final String ERROR_RESPONSE_TYPE_RESOURCE_MESSAGE = "http://cs.iit.edu/~virgil/cs445/project/api/problems/data-validation";
	public static final String ERROR_RESPONSE_TITLE_VALIDATION_MESSAGE = "Your request data didn't pass validation";
	
	public static final String LOCATION_INFO_KEY = "location_info";
	public static final String FROM_CITY_KEY = "from_city";
	public static final String FROM_ZIP_KEY = "from_zip";
	public static final String TO_CITY_KEY = "to_city";
	public static final String TO_ZIP_KEY = "to_zip";
	public static final String DATE_TIME_KEY = "date_time";
	public static final String DATE_KEY = "date";
	public static final String TIME_KEY = "time";
	public static final String CAR_INFO_KEY = "car_info";
	public static final String MAKE_KEY = "make";
	public static final String MODEL_KEY = "model";
	public static final String COLOR_KEY = "color";
	public static final String PLATE_STATE_KEY = "plate_state";
	public static final String PLATE_SERIAL_KEY = "plate_serial";
	public static final String MAX_PASSENGERS_KEY = "max_passengers";
	public static final String AMOUNT_PER_PASSENGER_KEY = "amount_per_passenger";
	public static final String CONDITIONS_KEY = "conditions";
	
	public static final String INVALID_FROM_CITY_ERROR_MESSAGE = "Invalid value for from_city";
	public static final String INVALID_FROM_ZIP_ERROR_MESSAGE = "Invalid value for from_zip";
	public static final String INVALID_TO_CITY_ERROR_MESSAGE = "Invalid value for to_city";
	public static final String INVALID_TO_ZIP_ERROR_MESSAGE = "Invalid value for to_zip";
	public static final String INVALID_DATE_ERROR_MESSAGE = "Invalid date";
	public static final String INVALID_TIME_ERROR_MESSAGE = "Invalid value for time";
	public static final String INVALID_MAKE_ERROR_MESSAGE = "Invalid value for make";
	public static final String INVALID_MODEL_ERROR_MESSAGE = "Invalid value for model";
	public static final String INVALID_COLOR_ERROR_MESSAGE = "Invalid value for color";
	public static final String INVALID_PLATE_STATE_ERROR_MESSAGE = "Invalid value for plate_state";
	public static final String INVALID_PLATE_SERIAL_ERROR_MESSAGE = "Invalid value for plate_serial";
	public static final String INVALID_MAX_PASSENGERS_ERROR_MESSAGE = "Invalid value for max_passengers";
	public static final String INVALID_AMOUNT_PER_PASSENGER_ERROR_MESSAGE = "Invalid value for amount_per_passenger";
	public static final String INVALID_CONDITIONS_ERROR_MESSAGE = "Invalid value for conditions";
	
	public static final String DRIVER_KEY = "driver";
	public static final String DRIVER_PICTURE_KEY = "driver_picture";
	public static final String RIDES_KEY = "rides";
	public static final String RATINGS_KEY = "ratings";
	public static final String RATING_KEY = "rating";
	public static final String AVERAGE_RATING_KEY = "average_rating";
	public static final String COMMENTS_ABOUT_DRIVER_KEY = "comments_about_driver";
	public static final String COMMENT_KEY = "comment";
	
	public static final String INVALID_RATING_ERROR_MESSAGE = "Invalid value for rating";
	public static final String INVALID_COMMENT_ERROR_MESSAGE = "Invalid value for comment";
	
	public static final String PASSENGERS_KEY = "passengers";
	public static final String RIDE_CONFIRMED_KEY = "ride_confirmed";
	public static final String PICKUP_CONFIRMED_KEY = "pickup_confirmed";
	
	public static final String INVALID_PASSENGERS_ERROR_MESSAGE = "Invalid value for passengers";
	public static final String INVALID_RIDE_CONFIRMED_ERROR_MESSAGE = "Invalid value for ride_confirmed";
	public static final String INVALID_PICKUP_CONFIRMED_ERROR_MESSAGE = "Invalid value for pickup_confirmed";
	
	public static final String MESSAGE_KEY = "msg";
	public static final String SENT_BY_ACCOUNT_KEY = "sent_by_aid";
	public static final String BODY_KEY = "body";
	
	public static final String INVALID_MESSAGE_ERROR_MESSAGE = "Invalid value for msg";
	
	public static final String SENT_BY_ID_KEY = "sent_by_id";
	public static final String INVALID_SENT_BY_ID_ERROR_MESSAGE = "Invalid value for sent_by_id";
	
	public static final String DETAIL_KEY = "detail";
	public static final String LOCATION_KEY = "Location";
	
	public static final String START_DATE_KEY = "start_date";
	public static final String END_DATE_KEY = "end_date";
	public static final String COUNT_KEY = "count";
		
	public static final String ACCOUNT_INVALID_CREATOR_ERROR_MESSAGE = "Only the creator of the ride may change it";
	public static final String ACCOUNT_CREATOR_JOIN_REQUEST_ERROR_MESSAGE = "The creator of the ride may not also become a rider";
	public static final String ACCOUNT_ALREADY_ACTIVE_ERROR_MESSAGE = "Account is already activated";
	public static final String ACCOUNT_NOT_ACTIVE_ERROR_MESSAGE = "Account is not active";
	public static final String ACCOUNT_RATE_THEMSELF_ERROR_MESSAGE = "Accounts cannot rate themselves";
	
	public static String invalidAccountErrorMessage(int aid) {
		return "This account ("+ aid +") doesn't exist or is not active.";
	}
	
	public static String invalidAccountRideConfirmedErrorMessage(int rid, int aid) {
		return "This account ("+ aid +") didn't create the ride ("+ rid +")";
	}
	
	public static String invalidAccountPickUpConfirmedErrorMessage(int rid, int aid) {
		return "This account ("+ aid +") has not requested to join this ride ("+ rid +")";
	}
	
	public static String inactiveAccountCreateRideErrorMessage(int aid) {
		return "This account ("+ aid +") is not active, may not create a ride.";
	}
	
	public static String inactiveAccountCreateJoinRequestErrorMessage(int aid) {
		return "This account ("+ aid +") is not active, may not create a join ride request.";
	}
	
	public static String createSentJoinRequestAlreadyErrorMessage(int rid, int aid) {
		return "This account (" + aid +") is has already sent a join request to ride ("+ rid +")";
	}
	
	public static String createNonRideParticipantRatingErrorMessage(int rid, int aid) {
		return "This account (" + aid + ") didn't create this ride ("
				+ rid + ") nor was it a passenger";
	}
	
	public static String createNonExistingRideErrorMessage(int rid) {
		return "This ride ("+ rid +") does not exist";
	}
	
	public String generateBody();
}
