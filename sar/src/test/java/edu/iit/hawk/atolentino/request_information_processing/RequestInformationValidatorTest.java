package edu.iit.hawk.atolentino.request_information_processing;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.PatchType;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationValidator;
import edu.iit.hawk.atolentino.request_information_processing.entities.JoinRequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.MessageInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.ProfileInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.RateInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.RideInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.UpdateJoinRequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.validators.JoinRequestInformationValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.MessageInformationValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.ProfileInformationValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.RateInformationValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.RideInformationValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.UpdateJoinRequestInformationValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.ActiveValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.DateFormatValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.GreaterThanZeroValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.IsNullValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.NameValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.NotEmptyValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.NotNegativeValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.NotNullValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.PhoneNumberValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.RatingBetweenRangeValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.StateValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.TimeFormatValidator;
import edu.iit.hawk.atolentino.request_information_processing.validators.reusable.TrueValueValidator;

public class RequestInformationValidatorTest {
	private final String ERROR_MESSAGE= "";
	
	@Nested
	public class ActiveValidatorTest {
		@Test
		void test_validate_withBothStatusesTheSame_shouldNotThrowException() {
			RequestInformationValidator activeValidator = new ActiveValidator(true, true, ERROR_MESSAGE);
			assertDoesNotThrow(() -> {
				activeValidator.validate();
			});
		}
		
		@Test
		void test_validate_withBothOppositeStatusesTheSame_shouldThrowIllegalArgumentException() {
			RequestInformationValidator activeValidator = new ActiveValidator(true, false, ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				activeValidator.validate();
			});
		}
	}
	
	@Nested
	public class DateFormatValidatorTest {
		private final String VALID_DATE = "14-Apr-2020";
		private final String INVALID_DATE = "31-Apr-2020";
		private final String INVALID_DATE_FORMAT = "blah";
		
		@Test
		void test_validate_withVALIDDATE_shouldNotThrowException() {
			RequestInformationValidator dateFormatValidator = new DateFormatValidator(VALID_DATE, ERROR_MESSAGE);
			assertDoesNotThrow(() -> {
				dateFormatValidator.validate();
			});
		}
		
		@Test
		void test_validate_withINVALIDDATE_shouldthrowIllegalArgumentException() {
			RequestInformationValidator dateFormatValidator = new DateFormatValidator(INVALID_DATE, ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				dateFormatValidator.validate();
			});
		}
		
		@Test
		void test_validate_withINVALIDDATEFORMAT_shouldthrowIllegalArgumentException() {
			RequestInformationValidator dateFormatValidator = new DateFormatValidator(INVALID_DATE_FORMAT, ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				dateFormatValidator.validate();
			});
		}
	}
	
	@Nested
	public class GreaterThanZeroValidatorTest {
		private final int GREATER_THAN_ZERO = 1;
		private final int ZERO = 0;
		private final int LESS_THAN_ZERO = -1;
		
		@Test
		void test_validate_givenGRATERTHANZERO_shouldNotThrowException() {
			RequestInformationValidator greaterThanZeroValidator = new GreaterThanZeroValidator(GREATER_THAN_ZERO, ERROR_MESSAGE);
			assertDoesNotThrow(() -> {
				greaterThanZeroValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenZERO_shoulThrowIllegalArgumentException() {
			RequestInformationValidator greaterThanZeroValidator = new GreaterThanZeroValidator(ZERO, ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				greaterThanZeroValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenLESSTHANZERO_shoulThrowIllegalArgumentException() {
			RequestInformationValidator greaterThanZeroValidator = new GreaterThanZeroValidator(LESS_THAN_ZERO, ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				greaterThanZeroValidator.validate();
			});
		}
	}
	
	@Nested
	public class IsNullValidatorTest {
		private final Object O = new Object();
		private final Object NULL = null;
		
		@Test
		void test_validate_givenNULL_shouldNotThrowException() {
			RequestInformationValidator IsNullValidator = new IsNullValidator(NULL, ERROR_MESSAGE);
			assertDoesNotThrow(() -> {
				IsNullValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenO_shouldThrowIllegalArgumentException() {
			RequestInformationValidator IsNullValidator = new IsNullValidator(O, ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				IsNullValidator.validate();
			});
		}
	}
	
	@Nested
	public class NameValidatorTest {
		private final String VALID_NAME = "Andrew";
		private final String INVALID_NAME = "12345";
		
		@Test
		void test_validate_givenVALIDNAME_shouldNotThrowException() {
			RequestInformationValidator nameValidator = new NameValidator(VALID_NAME, ERROR_MESSAGE);
			assertDoesNotThrow(() -> {
				nameValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenINVALIDNAME_shouldThrowIllegalArgumentException() {
			RequestInformationValidator nameValidator = new NameValidator(INVALID_NAME, ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				nameValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenNullName_shouldThrowIllegalArgumentException() {
			RequestInformationValidator nameValidator = new NameValidator(null, ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				nameValidator.validate();
			});
		}
	}
	
	@Nested
	public class NotEmptyValidatorTest {
		private final String NON_EMPTY_STRING = "Blah";
		private final String EMPTY_STRING = "";
		
		@Test
		void test_validate_givenVALIDNAME_shouldNotThrowException() {
			RequestInformationValidator notEmptyValidator = new NotEmptyValidator(NON_EMPTY_STRING, ERROR_MESSAGE);
			assertDoesNotThrow(() -> {
				notEmptyValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenEMPTYSTRING_shouldThrowIllegalArgumentException() {
			RequestInformationValidator notEmptyValidator = new NotEmptyValidator(EMPTY_STRING, ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				notEmptyValidator.validate();
			});
		}
	}
	
	@Nested
	public class NotNegativeValidatorTest {
		private final double NON_NEGATIVE = 1;
		private final double NEGATIVE = -1;
		
		@Test
		void test_validate_givenNONNEGATIVE_shouldNotThrowException() {
			RequestInformationValidator notNegativeValidator = new NotNegativeValidator(NON_NEGATIVE, ERROR_MESSAGE);
			assertDoesNotThrow(() -> {
				notNegativeValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenNEGATIVE_shouldThrowIllegalArgumentException() {
			RequestInformationValidator notNegativeValidator = new NotNegativeValidator(NEGATIVE, ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				notNegativeValidator.validate();
			});
		}
	}
	
	@Nested
	public class NotNullValidatorTest {
		private final Object O = new Object();
		private final Object NULL = null;
		
		@Test
		void test_validate_givenO_shouldNotThrowException() {
			RequestInformationValidator notNullValidator = new NotNullValidator(O, ERROR_MESSAGE);
			assertDoesNotThrow(() -> {
				notNullValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenNULL_shouldThrowIllegalArgumentException() {
			RequestInformationValidator notNullValidator = new NotNullValidator(NULL, ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				notNullValidator.validate();
			});
		}
	}
	
	@Nested
	public class PhoneNumberValidatorTest {
		private final String VALID_PHONE_NUMBER = "773-226-5515";
		private final String PHONE_NUMBER_TOO_LONG_AREA_CODE = "7773-226-5515";
		private final String PHONE_NUMBER_TOO_LONG_NUMBER = "7773-22226-555515";
		private final String PHONE_NUMBER_TOO_MANY_DASHES = "77-73-22-226-555515-";
		private final String PHONE_NUMBER_NOT_ENOUGH_DASHES = "7773-22226555515-";
		
		@Test
		void test_validate_givenVALIDPHONENUMBER_shouldNotThrowException() {
			RequestInformationValidator phoneNumberValidator = new PhoneNumberValidator(VALID_PHONE_NUMBER, ERROR_MESSAGE);
			assertDoesNotThrow(() -> {
				phoneNumberValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenPHONENUMBERTOOLONGAREACODE_shouldThrowIllegalArgumentException() {
			RequestInformationValidator phoneNumberValidator = new PhoneNumberValidator(PHONE_NUMBER_TOO_LONG_AREA_CODE, ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				phoneNumberValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenPhoneNumberWithAreaCodeContainingLetters_shouldThrowIllegalArgumentException() {
			RequestInformationValidator phoneNumberValidator = new PhoneNumberValidator("a73-226-5515", ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				phoneNumberValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenPHONENUMBERTOOLONGNUMBER_shouldThrowIllegalArgumentException() {
			RequestInformationValidator phoneNumberValidator = new PhoneNumberValidator(PHONE_NUMBER_TOO_LONG_NUMBER, ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				phoneNumberValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMainNumberIsTooLong_shouldThrowIllegalArgumentException() {
			RequestInformationValidator phoneNumberValidator = new PhoneNumberValidator("773-226-55151", ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				phoneNumberValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenPHONENUMBERTOOMANYDASHES_shouldThrowIllegalArgumentException() {
			RequestInformationValidator phoneNumberValidator = new PhoneNumberValidator(PHONE_NUMBER_TOO_MANY_DASHES, ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				phoneNumberValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenPHONENUMBERNOTENOUGHDASHES_shouldThrowIllegalArgumentException() {
			RequestInformationValidator phoneNumberValidator = new PhoneNumberValidator(PHONE_NUMBER_NOT_ENOUGH_DASHES, ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				phoneNumberValidator.validate();
			});
		}
	}
	
	@Nested
	public class RatingBetweenRangeValidatorTest {
		@Test
		void test_validate_givenOne_shouldNotThrowException() {
			RequestInformationValidator ratingBetweenRangeValidator = new RatingBetweenRangeValidator(1, ERROR_MESSAGE);
			assertDoesNotThrow(() -> {
				ratingBetweenRangeValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenTwo_shouldNotThrowException() {
			RequestInformationValidator ratingBetweenRangeValidator = new RatingBetweenRangeValidator(2, ERROR_MESSAGE);
			assertDoesNotThrow(() -> {
				ratingBetweenRangeValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenThree_shouldNotThrowException() {
			RequestInformationValidator ratingBetweenRangeValidator = new RatingBetweenRangeValidator(3, ERROR_MESSAGE);
			assertDoesNotThrow(() -> {
				ratingBetweenRangeValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenFour_shouldNotThrowException() {
			RequestInformationValidator ratingBetweenRangeValidator = new RatingBetweenRangeValidator(4, ERROR_MESSAGE);
			assertDoesNotThrow(() -> {
				ratingBetweenRangeValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenFive_shouldNotThrowException() {
			RequestInformationValidator ratingBetweenRangeValidator = new RatingBetweenRangeValidator(5, ERROR_MESSAGE);
			assertDoesNotThrow(() -> {
				ratingBetweenRangeValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenLessThanOne_shouldThrowIllegalArgumentException() {
			RequestInformationValidator ratingBetweenRangeValidator = new RatingBetweenRangeValidator(0, ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				ratingBetweenRangeValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenGreaterThanFive_shouldThrowIllegalArgumentException() {
			RequestInformationValidator ratingBetweenRangeValidator = new RatingBetweenRangeValidator(6, ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				ratingBetweenRangeValidator.validate();
			});
		}
	}
	
	@Nested
	public class StateValidatorTest {
		private final String VALID_STATE = "IL";
		private final String INVALID_STATE = "I";
		
		@Test
		void test_validate_givenVALIDSTATE_shouldNotThrowException() {
			RequestInformationValidator stateValidator = new StateValidator(VALID_STATE, ERROR_MESSAGE);
			assertDoesNotThrow(() -> {
				stateValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenINVALIDSTATE_shouldThrowIllegalArgumentException() {
			RequestInformationValidator stateValidator = new StateValidator(INVALID_STATE, ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				stateValidator.validate();
			});
		}
	}
	
	@Nested
	public class TimeFormatValidatorTest {
		private final String VALID_TIME = "09:00";
		private final String INVALID_TIME = "25:55";
		
		@Test
		void test_validate_givenVALIDTIME_shouldNotThrowException() {
			RequestInformationValidator timeFormatValidator = new TimeFormatValidator(VALID_TIME, ERROR_MESSAGE);
			assertDoesNotThrow(() -> {
				timeFormatValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenINVALIDTIME_shouldThrowIllegalArgumentException() {
			RequestInformationValidator timeFormatValidator = new TimeFormatValidator(INVALID_TIME, ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				timeFormatValidator.validate();
			});
		}
	}
	
	@Nested
	public class TrueValueValidatorTest {
		@Test
		void test_validate_givenTrue_shouldNotThrowException() {
			RequestInformationValidator trueValueValidator = new TrueValueValidator(true, ERROR_MESSAGE);
			assertDoesNotThrow(() -> {
				trueValueValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenFalse_shouldThrowIllegalArgumentException() {
			RequestInformationValidator trueValueValidator = new TrueValueValidator(false, ERROR_MESSAGE);
			assertThrows(IllegalArgumentException.class, () -> {
				trueValueValidator.validate();
			});
		}
	}
	
	@Nested
	public class JoinRequestInformationValidatorTest {
		private final Integer AID = 1;
		private final Integer PASSENGERS = 1;
		private Boolean RIDE_CONFIRMED = null;
		private Boolean PICKUP_CONFIRMED = null;
		
		@Test
		void test_validate_givenValidJoinRequestInformation_shouldNotThrowException() {
			JoinRequestInformation joinRequestInformation = new JoinRequestInformation(
					AID, PASSENGERS, RIDE_CONFIRMED, PICKUP_CONFIRMED);
			RequestInformationValidator joinRequestInformationValidator = new JoinRequestInformationValidator(joinRequestInformation);
			assertDoesNotThrow(() -> {
				joinRequestInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenJoinRequestInformationWithNullAid_shouldThrowIllegalArgumentException() {
			JoinRequestInformation joinRequestInformation = new JoinRequestInformation(
					null, PASSENGERS, RIDE_CONFIRMED, PICKUP_CONFIRMED);
			RequestInformationValidator joinRequestInformationValidator = new JoinRequestInformationValidator(joinRequestInformation);			
			assertThrows(IllegalArgumentException.class, () -> {
				joinRequestInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenJoinRequestInformationWithNullPassengers_shouldThrowIllegalArgumentException() {
			JoinRequestInformation joinRequestInformation = new JoinRequestInformation(
					AID, null, RIDE_CONFIRMED, PICKUP_CONFIRMED);
			RequestInformationValidator joinRequestInformationValidator = new JoinRequestInformationValidator(joinRequestInformation);			
			assertThrows(IllegalArgumentException.class, () -> {
				joinRequestInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenJoinRequestInformationWithZeroPassengers_shouldThrowIllegalArgumentException() {
			JoinRequestInformation joinRequestInformation = new JoinRequestInformation(
					AID, 0, RIDE_CONFIRMED, PICKUP_CONFIRMED);
			RequestInformationValidator joinRequestInformationValidator = new JoinRequestInformationValidator(joinRequestInformation);			
			assertThrows(IllegalArgumentException.class, () -> {
				joinRequestInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenJoinRequestInformationWithNonNullRideConfirmed_shouldThrowIllegalArgumentException() {
			JoinRequestInformation joinRequestInformation = new JoinRequestInformation(
					AID, PASSENGERS, true, PICKUP_CONFIRMED);
			RequestInformationValidator joinRequestInformationValidator = new JoinRequestInformationValidator(joinRequestInformation);			
			assertThrows(IllegalArgumentException.class, () -> {
				joinRequestInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenJoinRequestInformationWithNonNullPickupConfirmed_shouldThrowIllegalArgumentException() {
			JoinRequestInformation joinRequestInformation = new JoinRequestInformation(
					AID, PASSENGERS, RIDE_CONFIRMED, true);
			RequestInformationValidator joinRequestInformationValidator = new JoinRequestInformationValidator(joinRequestInformation);			
			assertThrows(IllegalArgumentException.class, () -> {
				joinRequestInformationValidator.validate();
			});
		}
	}
	
	@Nested
	public class MessageInformationValidatorTest {
		private final Integer AID = 1;
		private final String MSG = "Hello";
		
		@Test
		void test_validate_givenValidMessageInformation_shouldNotThrowException() {
			MessageInformation messageInformation = new MessageInformation(AID, MSG);
			RequestInformationValidator messageInformationValidator = new MessageInformationValidator(messageInformation);
			assertDoesNotThrow(() -> {
				messageInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNullAid_shouldThrowIllegalArgumentException() {
			MessageInformation messageInformation = new MessageInformation(null, MSG);
			RequestInformationValidator messageInformationValidator = new MessageInformationValidator(messageInformation);	
			assertThrows(IllegalArgumentException.class, () -> {
				messageInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNullMsg_shouldThrowIllegalArgumentException() {
			MessageInformation messageInformation = new MessageInformation(AID, null);
			RequestInformationValidator messageInformationValidator = new MessageInformationValidator(messageInformation);	
			assertThrows(IllegalArgumentException.class, () -> {
				messageInformationValidator.validate();
			});
		}
	}
	
	@Nested
	public class ProfileInformationValidatorTest {
		private final String FIRST_NAME = "Andrew";
		private final String LAST_NAME = "Tolentino";
		private final String PHONE_NUMBER = "773-226-5515";
		private final String PROFILE_PICTURE = "Profile Image";
		private final boolean NOT_ACTIVE = false;
		
		@Test
		void test_validate_givenValidProfileInformation_shouldNotThrowException() {
			ProfileInformation profileInformation = new ProfileInformation(
					FIRST_NAME, LAST_NAME, PHONE_NUMBER, PROFILE_PICTURE, NOT_ACTIVE);
			RequestInformationValidator profileInformationValidator = new ProfileInformationValidator(profileInformation, false);
			assertDoesNotThrow(() -> {
				profileInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNullFirstName_shouldThrowIllegalArgumentException() {
			ProfileInformation profileInformation = new ProfileInformation(
					null, LAST_NAME, PHONE_NUMBER, PROFILE_PICTURE, NOT_ACTIVE);
			RequestInformationValidator profileInformationValidator = new ProfileInformationValidator(profileInformation, false);
			assertThrows(IllegalArgumentException.class, () -> {
				profileInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithEmptyFirstName_shouldThrowIllegalArgumentException() {
			ProfileInformation profileInformation = new ProfileInformation(
					"", LAST_NAME, PHONE_NUMBER, PROFILE_PICTURE, NOT_ACTIVE);
			RequestInformationValidator profileInformationValidator = new ProfileInformationValidator(profileInformation, false);
			assertThrows(IllegalArgumentException.class, () -> {
				profileInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithInvalidFirstName_shouldThrowIllegalArgumentException() {
			ProfileInformation profileInformation = new ProfileInformation(
					"123", LAST_NAME, PHONE_NUMBER, PROFILE_PICTURE, NOT_ACTIVE);
			RequestInformationValidator profileInformationValidator = new ProfileInformationValidator(profileInformation, false);
			assertThrows(IllegalArgumentException.class, () -> {
				profileInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNullLastName_shouldThrowIllegalArgumentException() {
			ProfileInformation profileInformation = new ProfileInformation(
					FIRST_NAME, null, PHONE_NUMBER, PROFILE_PICTURE, NOT_ACTIVE);
			RequestInformationValidator profileInformationValidator = new ProfileInformationValidator(profileInformation, false);
			assertThrows(IllegalArgumentException.class, () -> {
				profileInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithEmptyLastName_shouldThrowIllegalArgumentException() {
			ProfileInformation profileInformation = new ProfileInformation(
					FIRST_NAME, "", PHONE_NUMBER, PROFILE_PICTURE, NOT_ACTIVE);
			RequestInformationValidator profileInformationValidator = new ProfileInformationValidator(profileInformation, false);
			assertThrows(IllegalArgumentException.class, () -> {
				profileInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithInvalidLastName_shouldThrowIllegalArgumentException() {
			ProfileInformation profileInformation = new ProfileInformation(
					FIRST_NAME, "123", PHONE_NUMBER, PROFILE_PICTURE, NOT_ACTIVE);
			RequestInformationValidator profileInformationValidator = new ProfileInformationValidator(profileInformation, false);
			assertThrows(IllegalArgumentException.class, () -> {
				profileInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNullPhone_shouldThrowIllegalArgumentException() {
			ProfileInformation profileInformation = new ProfileInformation(
					FIRST_NAME, LAST_NAME, null, PROFILE_PICTURE, NOT_ACTIVE);
			RequestInformationValidator profileInformationValidator = new ProfileInformationValidator(profileInformation, false);
			assertThrows(IllegalArgumentException.class, () -> {
				profileInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithEmptyPhone_shouldThrowIllegalArgumentException() {
			ProfileInformation profileInformation = new ProfileInformation(
					FIRST_NAME, LAST_NAME, "", PROFILE_PICTURE, NOT_ACTIVE);
			RequestInformationValidator profileInformationValidator = new ProfileInformationValidator(profileInformation, false);
			assertThrows(IllegalArgumentException.class, () -> {
				profileInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithInvalidPhone_shouldThrowIllegalArgumentException() {
			ProfileInformation profileInformation = new ProfileInformation(
					FIRST_NAME, LAST_NAME, "773--225-3213131", PROFILE_PICTURE, NOT_ACTIVE);
			RequestInformationValidator profileInformationValidator = new ProfileInformationValidator(profileInformation, false);
			assertThrows(IllegalArgumentException.class, () -> {
				profileInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNullPicture_shouldThrowIllegalArgumentException() {
			ProfileInformation profileInformation = new ProfileInformation(
					FIRST_NAME, LAST_NAME, PHONE_NUMBER, null, NOT_ACTIVE);
			RequestInformationValidator profileInformationValidator = new ProfileInformationValidator(profileInformation, false);
			assertThrows(IllegalArgumentException.class, () -> {
				profileInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithEmptyPicture_shouldThrowIllegalArgumentException() {
			ProfileInformation profileInformation = new ProfileInformation(
					FIRST_NAME, LAST_NAME, PHONE_NUMBER, "", NOT_ACTIVE);
			RequestInformationValidator profileInformationValidator = new ProfileInformationValidator(profileInformation, false);
			assertThrows(IllegalArgumentException.class, () -> {
				profileInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNullActive_shouldThrowIllegalArgumentException() {
			ProfileInformation profileInformation = new ProfileInformation(
					FIRST_NAME, LAST_NAME, PHONE_NUMBER, PROFILE_PICTURE, null);
			RequestInformationValidator profileInformationValidator = new ProfileInformationValidator(profileInformation, false);
			assertThrows(IllegalArgumentException.class, () -> {
				profileInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithInvalidActive_shouldThrowIllegalArgumentException() {
			ProfileInformation profileInformation = new ProfileInformation(
					FIRST_NAME, LAST_NAME, PHONE_NUMBER, PROFILE_PICTURE, NOT_ACTIVE);
			RequestInformationValidator profileInformationValidator = new ProfileInformationValidator(profileInformation, true);
			assertThrows(IllegalArgumentException.class, () -> {
				profileInformationValidator.validate();
			});
		}
	}
	
	@Nested
	public class RateInformationValidatorTest {
		private final Integer RID = 1;
		private final Integer SENT_BY_ID = 2;
		private final String COMMENT = "Hello";
		
		@Test
		void test_validate_givenValidRateInformation_withRateBeingOne_shouldNotThrowException() {
			RateInformation rateInformation = new RateInformation(
					RID, SENT_BY_ID, 1, COMMENT);
			RequestInformationValidator rateInformationValidator = new RateInformationValidator(rateInformation);
			assertDoesNotThrow(() -> {
				rateInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenValidRateInformation_withRateBeingTwo_shouldNotThrowException() {
			RateInformation rateInformation = new RateInformation(
					RID, SENT_BY_ID, 2, COMMENT);
			RequestInformationValidator rateInformationValidator = new RateInformationValidator(rateInformation);
			assertDoesNotThrow(() -> {
				rateInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenValidRateInformation_withRateBeingThree_shouldNotThrowException() {
			RateInformation rateInformation = new RateInformation(
					RID, SENT_BY_ID, 3, COMMENT);
			RequestInformationValidator rateInformationValidator = new RateInformationValidator(rateInformation);
			assertDoesNotThrow(() -> {
				rateInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenValidRateInformation_withRateBeingFour_shouldNotThrowException() {
			RateInformation rateInformation = new RateInformation(
					RID, SENT_BY_ID, 4, COMMENT);
			RequestInformationValidator rateInformationValidator = new RateInformationValidator(rateInformation);
			assertDoesNotThrow(() -> {
				rateInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenValidRateInformation_withRateBeingFive_shouldNotThrowException() {
			RateInformation rateInformation = new RateInformation(
					RID, SENT_BY_ID, 5, COMMENT);
			RequestInformationValidator rateInformationValidator = new RateInformationValidator(rateInformation);
			assertDoesNotThrow(() -> {
				rateInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNullRid_shouldThrowIllegalArgumentException() {
			RateInformation rateInformation = new RateInformation(
					null, SENT_BY_ID, 1, COMMENT);
			RequestInformationValidator rateInformationValidator = new RateInformationValidator(rateInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rateInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNullSentById_shouldThrowIllegalArgumentException() {
			RateInformation rateInformation = new RateInformation(
					RID, null, 1, COMMENT);
			RequestInformationValidator rateInformationValidator = new RateInformationValidator(rateInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rateInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNullRating_shouldThrowIllegalArgumentException() {
			RateInformation rateInformation = new RateInformation(
					RID, SENT_BY_ID, null, COMMENT);
			RequestInformationValidator rateInformationValidator = new RateInformationValidator(rateInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rateInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithRatingLessThanOne_shouldThrowIllegalArgumentException() {
			RateInformation rateInformation = new RateInformation(
					RID, SENT_BY_ID, 0, COMMENT);
			RequestInformationValidator rateInformationValidator = new RateInformationValidator(rateInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rateInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithRatingGreaterThanFive_shouldThrowIllegalArgumentException() {
			RateInformation rateInformation = new RateInformation(
					RID, SENT_BY_ID, 6, COMMENT);
			RequestInformationValidator rateInformationValidator = new RateInformationValidator(rateInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rateInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNullComment_shouldThrowIllegalArgumentException() {
			RateInformation rateInformation = new RateInformation(
					RID, SENT_BY_ID, 1, null);
			RequestInformationValidator rateInformationValidator = new RateInformationValidator(rateInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rateInformationValidator.validate();
			});
		}
	}
	
	@Nested
	public class RideInformationValidatorTest {
		private final Integer AID = 19;
		private final String FROM_CITY = "Barrington";
		private final String FROM_ZIP = "60010";
		private final String TO_CITY = "Milwaukee";
		private final String TO_ZIP = "53202";
		private final String DATE = "14-Apr-2020";
		private final String TIME = "09:00";
		private final String MAKE = "Audi";
		private final String MODEL = "A4";
		private final String COLOR = "Gray";
		private final String PLATE_STATE = "IL";
		private final String PLATE_SERIAL = "COVID19";
		private final Integer MAX_PASSENGERS = 2;
		private final Double AMOUNT_PER_PASSENGER = 15.00;
		private final String CONDITIONS = "No more than one carry on per passenger. No pets.";
		
		@Test
		void test_validate_givenValidRideInformation_shouldNotThrowException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					DATE, 
					TIME, 
					MAKE, 
					MODEL, 
					COLOR, 
					PLATE_STATE, 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertDoesNotThrow(() -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenValidRideInformation_withAmountPerPassengerBeingNull_shouldNotThrowException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					DATE, 
					TIME, 
					MAKE, 
					MODEL, 
					COLOR, 
					PLATE_STATE, 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					null, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertDoesNotThrow(() -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNullAid_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					null,
					FROM_CITY, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					DATE, 
					TIME, 
					MAKE, 
					MODEL, 
					COLOR, 
					PLATE_STATE, 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNullFromCity_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					null, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					DATE, 
					TIME, 
					MAKE, 
					MODEL, 
					COLOR, 
					PLATE_STATE, 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithEmptyFromCity_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					"", 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					DATE, 
					TIME, 
					MAKE, 
					MODEL, 
					COLOR, 
					PLATE_STATE, 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNullToCity_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					null, 
					TO_ZIP, 
					DATE, 
					TIME, 
					MAKE, 
					MODEL, 
					COLOR, 
					PLATE_STATE, 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithEmptyToCity_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					"", 
					TO_ZIP, 
					DATE, 
					TIME, 
					MAKE, 
					MODEL, 
					COLOR, 
					PLATE_STATE, 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNullMake_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					DATE, 
					TIME, 
					null, 
					MODEL, 
					COLOR, 
					PLATE_STATE, 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithEmptyMake_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					DATE, 
					TIME, 
					"", 
					MODEL, 
					COLOR, 
					PLATE_STATE, 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
	
		@Test
		void test_validate_givenMessageInformationWithNullModel_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					DATE, 
					TIME, 
					MAKE, 
					null, 
					COLOR, 
					PLATE_STATE, 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithEmptyModel_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					DATE, 
					TIME, 
					MAKE, 
					"", 
					COLOR, 
					PLATE_STATE, 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
	
		@Test
		void test_validate_givenMessageInformationWithNullColor_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					DATE, 
					TIME, 
					MAKE, 
					MODEL, 
					null, 
					PLATE_STATE, 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithEmptyColor_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					DATE, 
					TIME, 
					MAKE, 
					MODEL, 
					"", 
					PLATE_STATE, 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNullPlateSerial_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					DATE, 
					TIME, 
					MAKE, 
					MODEL, 
					COLOR, 
					PLATE_STATE, 
					null, 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithEmptyPlateSerial_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					DATE, 
					TIME, 
					MAKE, 
					MODEL, 
					COLOR, 
					PLATE_STATE, 
					"", 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNullDate_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					null, 
					TIME, 
					MAKE, 
					MODEL, 
					COLOR, 
					PLATE_STATE, 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithInvalidDate_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					"31-Apr-2020", 
					TIME, 
					MAKE, 
					MODEL, 
					COLOR, 
					PLATE_STATE, 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNullTime_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					DATE, 
					null, 
					MAKE, 
					MODEL, 
					COLOR, 
					PLATE_STATE, 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithInvalidTime_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					DATE, 
					"25:00", 
					MAKE, 
					MODEL, 
					COLOR, 
					PLATE_STATE, 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNullMaxPassengers_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					DATE, 
					TIME, 
					MAKE, 
					MODEL, 
					COLOR, 
					PLATE_STATE, 
					PLATE_SERIAL, 
					null, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithZeroMaxPassengers_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					DATE, 
					TIME, 
					MAKE, 
					MODEL, 
					COLOR, 
					PLATE_STATE, 
					PLATE_SERIAL, 
					0, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNegativeAmountPerPassenger_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					DATE, 
					TIME, 
					MAKE, 
					MODEL, 
					COLOR, 
					PLATE_STATE, 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					-1.0, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithNullPlateState_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					DATE, 
					TIME, 
					MAKE, 
					MODEL, 
					COLOR, 
					null, 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenMessageInformationWithInvalidPlateState_shouldThrowIllegalArgumentException() {
			RideInformation rideInformation = new RideInformation(
					AID,
					FROM_CITY, 
					FROM_ZIP, 
					TO_CITY, 
					TO_ZIP, 
					DATE, 
					TIME, 
					MAKE, 
					MODEL, 
					COLOR, 
					"I", 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			RequestInformationValidator rideInformationValidator = new RideInformationValidator(rideInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				rideInformationValidator.validate();
			});
		}
	}

	@Nested
	public class UpdateJoinRequestInformationValidatorTest {
		private final Integer AID = 1;
		
		@Test
		void test_validate_givenValidUpdateJoinRequestInformation_withRideConfirmedSetToTrue_shouldNotThrowException() {
			UpdateJoinRequestInformation updateJoinRequestInformation = new UpdateJoinRequestInformation(
					AID, true, PatchType.RIDE_CONFIRMED);
			RequestInformationValidator updateJoinRequestInformationValidator = new UpdateJoinRequestInformationValidator(updateJoinRequestInformation);
			assertDoesNotThrow(() -> {
				updateJoinRequestInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenValidUpdateJoinRequestInformation_withRideConfirmedSetToFalse_shouldNotThrowException() {
			UpdateJoinRequestInformation updateJoinRequestInformation = new UpdateJoinRequestInformation(
					AID, false, PatchType.RIDE_CONFIRMED);
			RequestInformationValidator updateJoinRequestInformationValidator = new UpdateJoinRequestInformationValidator(updateJoinRequestInformation);
			assertDoesNotThrow(() -> {
				updateJoinRequestInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenValidUpdateJoinRequestInformation_withPickUpConfirmedSetToTrue_shouldNotThrowException() {
			UpdateJoinRequestInformation updateJoinRequestInformation = new UpdateJoinRequestInformation(
					AID, true, PatchType.PICKUP_CONFIRMED);
			RequestInformationValidator updateJoinRequestInformationValidator = new UpdateJoinRequestInformationValidator(updateJoinRequestInformation);
			assertDoesNotThrow(() -> {
				updateJoinRequestInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenUpdateJoinRequestInformation_withNullAid_shouldThrowIllegalArgumentException() {
			UpdateJoinRequestInformation updateJoinRequestInformation = new UpdateJoinRequestInformation(
					null, true, PatchType.RIDE_CONFIRMED);
			RequestInformationValidator updateJoinRequestInformationValidator = new UpdateJoinRequestInformationValidator(updateJoinRequestInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				updateJoinRequestInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenUpdateJoinRequestInformation_withNullForRideConfirmed_shouldThrowIllegalArgumentException() {
			UpdateJoinRequestInformation updateJoinRequestInformation = new UpdateJoinRequestInformation(
					AID, null, PatchType.RIDE_CONFIRMED);
			RequestInformationValidator updateJoinRequestInformationValidator = new UpdateJoinRequestInformationValidator(updateJoinRequestInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				updateJoinRequestInformationValidator.validate();
			});
		}
		
		@Test
		void test_validate_givenUpdateJoinRequestInformation_withFalseForPickUpConfirmed_shouldThrowIllegalArgumentException() {
			UpdateJoinRequestInformation updateJoinRequestInformation = new UpdateJoinRequestInformation(
					AID, false, PatchType.PICKUP_CONFIRMED);
			RequestInformationValidator updateJoinRequestInformationValidator = new UpdateJoinRequestInformationValidator(updateJoinRequestInformation);
			assertThrows(IllegalArgumentException.class, () -> {
				updateJoinRequestInformationValidator.validate();
			});
		}
	}
	
	
}
