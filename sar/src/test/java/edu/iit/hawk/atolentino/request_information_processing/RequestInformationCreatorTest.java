package edu.iit.hawk.atolentino.request_information_processing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationCreator;
import edu.iit.hawk.atolentino.request_information_processing.creators.JoinRequestInformationCreator;
import edu.iit.hawk.atolentino.request_information_processing.creators.MessageInformationCreator;
import edu.iit.hawk.atolentino.request_information_processing.creators.ProfileInformationCreator;
import edu.iit.hawk.atolentino.request_information_processing.creators.RateInformationCreator;
import edu.iit.hawk.atolentino.request_information_processing.creators.RideInformationCreator;
import edu.iit.hawk.atolentino.request_information_processing.creators.UpdateJoinRequestInformationCreator;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;

public class RequestInformationCreatorTest {
	private Gson gson = new Gson();
	private static String EMPTY_JSON = new JsonObject().toString();
	private static String MALFORMED_JSON = "{,,}";
	
	@Nested
	public class JoinRequestInformationCreatorTest {
		private RequestInformationCreator joinRequestInformationCreator = new JoinRequestInformationCreator();
		
		@Test
		void test_create_withValidJoinRequestInformationJson_shouldReturnNonNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(67));
			requestBody.add(ResponseBodyUtility.PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.RIDE_CONFIRMED_KEY, JsonNull.INSTANCE);
			requestBody.add(ResponseBodyUtility.PICKUP_CONFIRMED_KEY, JsonNull.INSTANCE);
			
			assertFalse(joinRequestInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_withNullAid_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, JsonNull.INSTANCE);
			requestBody.add(ResponseBodyUtility.PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.RIDE_CONFIRMED_KEY, JsonNull.INSTANCE);
			requestBody.add(ResponseBodyUtility.PICKUP_CONFIRMED_KEY, JsonNull.INSTANCE);
			
			assertTrue(joinRequestInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_withNullPassengers_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(67));
			requestBody.add(ResponseBodyUtility.PASSENGERS_KEY, JsonNull.INSTANCE);
			requestBody.add(ResponseBodyUtility.RIDE_CONFIRMED_KEY, JsonNull.INSTANCE);
			requestBody.add(ResponseBodyUtility.PICKUP_CONFIRMED_KEY, JsonNull.INSTANCE);
			
			assertTrue(joinRequestInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_withZeroPassengers_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(67));
			requestBody.add(ResponseBodyUtility.PASSENGERS_KEY, gson.toJsonTree(0));
			requestBody.add(ResponseBodyUtility.RIDE_CONFIRMED_KEY, JsonNull.INSTANCE);
			requestBody.add(ResponseBodyUtility.PICKUP_CONFIRMED_KEY, JsonNull.INSTANCE);
			
			assertTrue(joinRequestInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_withRideConfirmedNotNull_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(67));
			requestBody.add(ResponseBodyUtility.PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.RIDE_CONFIRMED_KEY, gson.toJsonTree(true));
			requestBody.add(ResponseBodyUtility.PICKUP_CONFIRMED_KEY, JsonNull.INSTANCE);
			
			assertTrue(joinRequestInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenEMPTYJSON_shouldReturnNillRequestInformation() {
			assertTrue(joinRequestInformationCreator.create(EMPTY_JSON).isNill());
		}
		
		@Test
		void test_create_givenMALFORMEDJSON_shouldReturnNillRequestInformation() {
			assertTrue(joinRequestInformationCreator.create(MALFORMED_JSON).isNill());
		}
	}
	
	@Nested
	public class MessageInformationCreatorTest {
		private RequestInformationCreator messageInformationCreator = new MessageInformationCreator();
	
		@Test
		void test_create_givenValidMessageInformationJson_shouldReturnNonNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(67));
			requestBody.add(ResponseBodyUtility.MESSAGE_KEY, gson.toJsonTree("One passenger; could you pick me up at the Starbucks in Barrington at Main and Hough?"));
			
			assertFalse(messageInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_withNullAid_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, JsonNull.INSTANCE);
			requestBody.add(ResponseBodyUtility.MESSAGE_KEY, gson.toJsonTree("One passenger; could you pick me up at the Starbucks in Barrington at Main and Hough?"));
			
			assertTrue(messageInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_withNullMsg_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(67));
			requestBody.add(ResponseBodyUtility.MESSAGE_KEY, JsonNull.INSTANCE);
			
			assertTrue(messageInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenEMPTYJSON_shouldReturnNillRequestInformation() {
			assertTrue(messageInformationCreator.create(EMPTY_JSON).isNill());
		}
		
		@Test
		void test_create_givenMALFORMEDJSON_shouldReturnNillRequestInformation() {
			assertTrue(messageInformationCreator.create(MALFORMED_JSON).isNill());
		}
	}
	
	@Nested
	public class ProfileInformationCreatorTest {
		private RequestInformationCreator profileInformationCreator = new ProfileInformationCreator(false);
	
		@Test
		void test_create_withValidProfileInformationJson_shouldReturnNonNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree("John"));
			requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree("Smith"));
			requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree("312-456-7890"));
			requestBody.add(ResponseBodyUtility.PROFILE_PICTURE_KEY, gson.toJsonTree("http://example.com/images/john-smith.jpeg"));
			requestBody.add(ResponseBodyUtility.IS_ACTIVE_KEY, gson.toJsonTree(false));
			
			assertFalse(profileInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_NEW_ACCOUNT_withValidProfileInformationJson_shouldReturnNonNillRequestInformation() {
			ProfileInformationCreator creator = ProfileInformationCreator.NEW_ACCOUNT();

			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree("John"));
			requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree("Smith"));
			requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree("312-456-7890"));
			requestBody.add(ResponseBodyUtility.PROFILE_PICTURE_KEY, gson.toJsonTree("http://example.com/images/john-smith.jpeg"));
			requestBody.add(ResponseBodyUtility.IS_ACTIVE_KEY, gson.toJsonTree(false));
			
			assertFalse(creator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_ACTIVE_ACCOUNT_withValidProfileInformationJson_shouldReturnNonNillRequestInformation() {
			ProfileInformationCreator creator = ProfileInformationCreator.ACTIVE_ACCOUNT();

			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree("John"));
			requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree("Smith"));
			requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree("312-456-7890"));
			requestBody.add(ResponseBodyUtility.PROFILE_PICTURE_KEY, gson.toJsonTree("http://example.com/images/john-smith.jpeg"));
			requestBody.add(ResponseBodyUtility.IS_ACTIVE_KEY, gson.toJsonTree(true));
			
			assertFalse(creator.create(requestBody.toString()).isNill());
		}
			
		
		@Test
		void test_create_withNullFirstName_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, JsonNull.INSTANCE);
			requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree("Smith"));
			requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree("312-456-7890"));
			requestBody.add(ResponseBodyUtility.PROFILE_PICTURE_KEY, gson.toJsonTree("http://example.com/images/john-smith.jpeg"));
			requestBody.add(ResponseBodyUtility.IS_ACTIVE_KEY, gson.toJsonTree(false));
			
			assertTrue(profileInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_withInvalidFirstName_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree("12345"));
			requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree("Smith"));
			requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree("312-456-7890"));
			requestBody.add(ResponseBodyUtility.PROFILE_PICTURE_KEY, gson.toJsonTree("http://example.com/images/john-smith.jpeg"));
			requestBody.add(ResponseBodyUtility.IS_ACTIVE_KEY, gson.toJsonTree(false));
			
			assertTrue(profileInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_withNullLastName_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree("John"));
			requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, JsonNull.INSTANCE);
			requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree("312-456-7890"));
			requestBody.add(ResponseBodyUtility.PROFILE_PICTURE_KEY, gson.toJsonTree("http://example.com/images/john-smith.jpeg"));
			requestBody.add(ResponseBodyUtility.IS_ACTIVE_KEY, gson.toJsonTree(false));
			
			assertTrue(profileInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_withInvalidLastName_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree("John"));
			requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree("12345"));
			requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree("312-456-7890"));
			requestBody.add(ResponseBodyUtility.PROFILE_PICTURE_KEY, gson.toJsonTree("http://example.com/images/john-smith.jpeg"));
			requestBody.add(ResponseBodyUtility.IS_ACTIVE_KEY, gson.toJsonTree(false));
			
			assertTrue(profileInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_withNullPhone_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree("John"));
			requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree("Smith"));
			requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, JsonNull.INSTANCE);
			requestBody.add(ResponseBodyUtility.PROFILE_PICTURE_KEY, gson.toJsonTree("http://example.com/images/john-smith.jpeg"));
			requestBody.add(ResponseBodyUtility.IS_ACTIVE_KEY, gson.toJsonTree(false));
			
			assertTrue(profileInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_withInvalidPhone_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree("John"));
			requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree("Smith"));
			requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree("77321-231-43242-"));
			requestBody.add(ResponseBodyUtility.PROFILE_PICTURE_KEY, gson.toJsonTree("http://example.com/images/john-smith.jpeg"));
			requestBody.add(ResponseBodyUtility.IS_ACTIVE_KEY, gson.toJsonTree(false));
			
			assertTrue(profileInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_withNullPicture_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree("John"));
			requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree("Smith"));
			requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree("773-226-5515"));
			requestBody.add(ResponseBodyUtility.PROFILE_PICTURE_KEY, JsonNull.INSTANCE);
			requestBody.add(ResponseBodyUtility.IS_ACTIVE_KEY, gson.toJsonTree(false));
			
			assertTrue(profileInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_withInvalidPicture_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree("John"));
			requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree("Smith"));
			requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree("773-226-5515"));
			requestBody.add(ResponseBodyUtility.PROFILE_PICTURE_KEY, gson.toJsonTree(""));
			requestBody.add(ResponseBodyUtility.IS_ACTIVE_KEY, gson.toJsonTree(false));
			
			assertTrue(profileInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_withNullActive_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree("John"));
			requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree("Smith"));
			requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree("773-226-5515"));
			requestBody.add(ResponseBodyUtility.PROFILE_PICTURE_KEY, gson.toJsonTree("http://example.com/images/john-smith.jpeg"));
			requestBody.add(ResponseBodyUtility.IS_ACTIVE_KEY, JsonNull.INSTANCE);
			
			assertTrue(profileInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_withInvalidActive_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree("John"));
			requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree("Smith"));
			requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree("773-226-5515"));
			requestBody.add(ResponseBodyUtility.PROFILE_PICTURE_KEY, gson.toJsonTree("http://example.com/images/john-smith.jpeg"));
			requestBody.add(ResponseBodyUtility.IS_ACTIVE_KEY, gson.toJsonTree(true));
			
			assertTrue(profileInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenEMPTYJSON_shouldReturnNillRequestInformation() {
			assertTrue(profileInformationCreator.create(EMPTY_JSON).isNill());
		}
		
		@Test
		void test_create_givenMALFORMEDJSON_shouldReturnNillRequestInformation() {
			assertTrue(profileInformationCreator.create(MALFORMED_JSON).isNill());
		}
	}
	
	@Nested
	public class RateInformationCreatorTest {
		private RequestInformationCreator rateInformationCreator = new RateInformationCreator();

		@Test
		void test_create_givenValidRateInformationJson_shouldReturnNonNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(47));
			requestBody.add(ResponseBodyUtility.SENT_BY_ID_KEY, gson.toJsonTree(70));
			requestBody.add(ResponseBodyUtility.RATING_KEY, gson.toJsonTree(5));
			requestBody.add(ResponseBodyUtility.COMMENT_KEY, gson.toJsonTree("Blah."));
			
			assertFalse(rateInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenNullRid_shouldReturnNonNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.RIDE_ID_KEY, JsonNull.INSTANCE);
			requestBody.add(ResponseBodyUtility.SENT_BY_ID_KEY, gson.toJsonTree(70));
			requestBody.add(ResponseBodyUtility.RATING_KEY, gson.toJsonTree(5));
			requestBody.add(ResponseBodyUtility.COMMENT_KEY, gson.toJsonTree("Blah."));
			
			assertTrue(rateInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenNullSentById_shouldReturnNonNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(47));
			requestBody.add(ResponseBodyUtility.SENT_BY_ID_KEY, JsonNull.INSTANCE);
			requestBody.add(ResponseBodyUtility.RATING_KEY, gson.toJsonTree(5));
			requestBody.add(ResponseBodyUtility.COMMENT_KEY, gson.toJsonTree("Blah."));
			
			assertTrue(rateInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenNullRating_shouldReturnNonNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(47));
			requestBody.add(ResponseBodyUtility.SENT_BY_ID_KEY, gson.toJsonTree(70));
			requestBody.add(ResponseBodyUtility.RATING_KEY, JsonNull.INSTANCE);
			requestBody.add(ResponseBodyUtility.COMMENT_KEY, gson.toJsonTree("Blah."));
			
			assertTrue(rateInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenRatingLessThanOne_shouldReturnNonNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(47));
			requestBody.add(ResponseBodyUtility.SENT_BY_ID_KEY, gson.toJsonTree(70));
			requestBody.add(ResponseBodyUtility.RATING_KEY, gson.toJsonTree(0));
			requestBody.add(ResponseBodyUtility.COMMENT_KEY, gson.toJsonTree("Blah."));
			
			assertTrue(rateInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenRatingGreaterThanFive_shouldReturnNonNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(47));
			requestBody.add(ResponseBodyUtility.SENT_BY_ID_KEY, gson.toJsonTree(70));
			requestBody.add(ResponseBodyUtility.RATING_KEY, gson.toJsonTree(6));
			requestBody.add(ResponseBodyUtility.COMMENT_KEY, gson.toJsonTree("Blah."));
			
			assertTrue(rateInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenNullComment_shouldReturnNonNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(47));
			requestBody.add(ResponseBodyUtility.SENT_BY_ID_KEY, gson.toJsonTree(70));
			requestBody.add(ResponseBodyUtility.RATING_KEY, gson.toJsonTree(5));
			requestBody.add(ResponseBodyUtility.COMMENT_KEY, JsonNull.INSTANCE);
			
			assertTrue(rateInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenEMPTYJSON_shouldReturnNillRequestInformation() {
			assertTrue(rateInformationCreator.create(EMPTY_JSON).isNill());
		}
		
		@Test
		void test_create_givenMALFORMEDJSON_shouldReturnNillRequestInformation() {
			assertTrue(rateInformationCreator.create(MALFORMED_JSON).isNill());
		}
	}
	
	@Nested
	public class RideInformationCreatorTest {
		private RequestInformationCreator rideInformationCreator = new RideInformationCreator();
		
		@Test
		void test_create_givenValidRideInformationJson_shouldReturnNonNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertFalse(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenValidRideInformationJson_withoutFromZip_shouldReturnNonNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertFalse(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenValidRideInformationJson_withOutToZip_shouldReturnNonNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertFalse(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenNullAid_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, JsonNull.INSTANCE);
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenNullFromCity_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, JsonNull.INSTANCE);
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenEmptyStringFromCity_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree(""));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenNullToCity_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, JsonNull.INSTANCE);
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenEmptyStringToCity_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree(""));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenNullMake_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, JsonNull.INSTANCE);
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenEmptyStringMake_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree(""));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}

		@Test
		void test_create_givenNullModel_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, JsonNull.INSTANCE);
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenEmptyStringModel_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree(""));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenNullColor_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, JsonNull.INSTANCE);
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenEmptyStringColor_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree(""));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}

		@Test
		void test_create_givenNullPlateSerial_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, JsonNull.INSTANCE);

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenEmptyStringPlateSerial_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree(""));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}

		@Test
		void test_create_givenNullDate_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, JsonNull.INSTANCE);
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenInvalidDateFormat_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("31-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenNullTime_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, JsonNull.INSTANCE);
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenInvalidTimeFormat_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("25:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenNullMaxPassengers_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, JsonNull.INSTANCE);
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenZeroMaxPassengers_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(0));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenLessThanZeroAmountPerPassengers_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(-1.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenNullPlateState_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, JsonNull.INSTANCE);
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenInvalidPlateState_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("I"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}
		
		@Test
		void test_create_givenNullConditions_shouldReturnNillRequestInformation() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(19));
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree("Barrington"));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree("60010"));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree("Milwaukee"));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree("53202"));
			
			requestBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree("14-Apr-2020"));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree("09:00"));
			
			requestBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree("Audi"));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree("A4"));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree("Gray"));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree("IL"));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree("COVID19"));

			requestBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);
			
			requestBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(15.0));
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, JsonNull.INSTANCE);
			
			assertTrue(rideInformationCreator.create(requestBody.toString()).isNill());
		}	
		
		@Test
		void test_create_givenEMPTYJSON_shouldReturnNillRequestInformation() {
			assertTrue(rideInformationCreator.create(EMPTY_JSON).isNill());
		}
		
		@Test
		void test_create_givenMALFORMEDJSON_shouldReturnNillRequestInformation() {
			assertTrue(rideInformationCreator.create(MALFORMED_JSON).isNill());
		}
	}
	
	@Nested
	public class UpdateJoinRequestInformationCreatorTest {
		private RequestInformationCreator updateJoinRequestInformationCreator = new UpdateJoinRequestInformationCreator();
		
		@Test
		void test_create_givenValidUpdateJoinRequestInformationJson_shouldReturnNonNillRequestInformation() {
			JsonObject rideConfirmedUpdate = new JsonObject();
			rideConfirmedUpdate.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(47));
			rideConfirmedUpdate.add(ResponseBodyUtility.RIDE_CONFIRMED_KEY, gson.toJsonTree(true));

			JsonObject pickupConfirmedUpdate = new JsonObject();
			pickupConfirmedUpdate.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(47));
			pickupConfirmedUpdate.add(ResponseBodyUtility.PICKUP_CONFIRMED_KEY, gson.toJsonTree(true));
			
			assertFalse(updateJoinRequestInformationCreator.create(rideConfirmedUpdate.toString()).isNill());
			assertFalse(updateJoinRequestInformationCreator.create(pickupConfirmedUpdate.toString()).isNill());
		}
		
		@Test
		void test_create_givenNullAid_shouldReturnNillRequestInformation() {
			JsonObject rideConfirmedUpdate = new JsonObject();
			rideConfirmedUpdate.add(ResponseBodyUtility.ACCOUNT_ID_KEY, JsonNull.INSTANCE);
			rideConfirmedUpdate.add(ResponseBodyUtility.RIDE_CONFIRMED_KEY, gson.toJsonTree(true));
			
			assertTrue(updateJoinRequestInformationCreator.create(rideConfirmedUpdate.toString()).isNill());
		}
		
		@Test
		void test_create_givenNullRideConfirmed_shouldReturnNillRequestInformation() {
			JsonObject rideConfirmedUpdate = new JsonObject();
			rideConfirmedUpdate.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(47));
			rideConfirmedUpdate.add(ResponseBodyUtility.RIDE_CONFIRMED_KEY, JsonNull.INSTANCE);
			
			assertTrue(updateJoinRequestInformationCreator.create(rideConfirmedUpdate.toString()).isNill());
		}
		
		@Test
		void test_create_givenNullPickupConfirmed_shouldReturnNillRequestInformation() {
			JsonObject pickupConfirmedUpdate = new JsonObject();
			pickupConfirmedUpdate.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(47));
			pickupConfirmedUpdate.add(ResponseBodyUtility.PICKUP_CONFIRMED_KEY, JsonNull.INSTANCE);
			
			assertTrue(updateJoinRequestInformationCreator.create(pickupConfirmedUpdate.toString()).isNill());
		}
		
		@Test
		void test_create_givenFalsePickupConfirmed_shouldReturnNillRequestInformation() {
			JsonObject pickupConfirmedUpdate = new JsonObject();
			pickupConfirmedUpdate.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(47));
			pickupConfirmedUpdate.add(ResponseBodyUtility.PICKUP_CONFIRMED_KEY, gson.toJsonTree(false));
			
			assertTrue(updateJoinRequestInformationCreator.create(pickupConfirmedUpdate.toString()).isNill());
		}
		
		@Test
		void test_create_givenEMPTYJSON_shouldReturnNillRequestInformation() {
			assertTrue(updateJoinRequestInformationCreator.create(EMPTY_JSON).isNill());
		}
		
		@Test
		void test_create_givenMALFORMEDJSON_shouldReturnNillRequestInformation() {
			assertTrue(updateJoinRequestInformationCreator.create(MALFORMED_JSON).isNill());
		}	
	}
}
