package edu.iit.hawk.atolentino.request_information_processing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.parsers.JoinRequestInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.parsers.MessageInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.parsers.ProfileInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.parsers.RateInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.parsers.RideInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.parsers.UpdateJoinRequestInformationParser;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;

public class RequestInformationParserTest {
	private Gson gson = new Gson();
	private static String EMPTY_JSON = new JsonObject().toString();
	private static String MALFORMED_JSON = "{,,}";
	
	@Nested
	class JoinRequestInformationParsingTest {
		private RequestInformationParser joinRequestInformationParser = new JoinRequestInformationParser();
		
		@Test
		void test_parse_givenEMPTYJSON_shouldthrowNullPointerException() {
			assertThrows(NullPointerException.class, () -> {
				joinRequestInformationParser.parse(EMPTY_JSON);
			});
		}
		
		@Test
		void test_parse_givenMALFORMED_JSON_shouldthrowJsonSyntaxException() {
			assertThrows(JsonSyntaxException.class, () -> {
				joinRequestInformationParser.parse(MALFORMED_JSON);
			});
		}
		
		@Test
		void test_parse_givenProperRequestBody_shouldNotThrowAnyException() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(67));
			requestBody.add(ResponseBodyUtility.PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.RIDE_CONFIRMED_KEY, JsonNull.INSTANCE);
			requestBody.add(ResponseBodyUtility.PICKUP_CONFIRMED_KEY, JsonNull.INSTANCE);
			
			assertDoesNotThrow(() -> {
				joinRequestInformationParser.parse(requestBody.toString());
			});	
		}
		
		@Test
		void test_parse_givenInvalidRequestBody_withPickUpConfirmedSetToTrue_shouldNotThrowAnyException() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(67));
			requestBody.add(ResponseBodyUtility.PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.RIDE_CONFIRMED_KEY, JsonNull.INSTANCE);
			requestBody.add(ResponseBodyUtility.PICKUP_CONFIRMED_KEY, gson.toJsonTree(true));
			
			assertDoesNotThrow(() -> {
				joinRequestInformationParser.parse(requestBody.toString());
			});	
		}
		
		@Test
		void test_parse_givenInvalidRequestBody_withRideConfirmedSetToTrue_shouldNotThrowAnyException() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(67));
			requestBody.add(ResponseBodyUtility.PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.RIDE_CONFIRMED_KEY, gson.toJsonTree(true));
			requestBody.add(ResponseBodyUtility.PICKUP_CONFIRMED_KEY, JsonNull.INSTANCE);
			
			assertDoesNotThrow(() -> {
				joinRequestInformationParser.parse(requestBody.toString());
			});	
		}
		
		@Test
		void test_parse_givenInvalidRequestBody_withRideConfirmedSetToFalse_shouldNotThrowAnyException() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(67));
			requestBody.add(ResponseBodyUtility.PASSENGERS_KEY, gson.toJsonTree(2));
			requestBody.add(ResponseBodyUtility.RIDE_CONFIRMED_KEY, gson.toJsonTree(false));
			requestBody.add(ResponseBodyUtility.PICKUP_CONFIRMED_KEY, JsonNull.INSTANCE);
			
			assertDoesNotThrow(() -> {
				joinRequestInformationParser.parse(requestBody.toString());
			});	
		}
	}
	
	@Nested
	class MessageInformationParsingTest {
		private RequestInformationParser messageInformationParser = new MessageInformationParser();
		
		@Test
		void test_parse_givenEMPTYJSON_shouldthrowNullPointerException() {
			assertThrows(NullPointerException.class, () -> {
				messageInformationParser.parse(EMPTY_JSON);
			});
		}
		
		@Test
		void test_parse_givenMALFORMED_JSON_shouldthrowJsonSyntaxException() {
			assertThrows(JsonSyntaxException.class, () -> {
				messageInformationParser.parse(MALFORMED_JSON);
			});
		}
		
		@Test
		void test_parse_givenProperRequestBody_shouldNotThrowAnyException() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(67));
			requestBody.add(ResponseBodyUtility.MESSAGE_KEY, gson.toJsonTree("One passenger; could you pick me up at the Starbucks in Barrington at Main and Hough?"));
			
			assertDoesNotThrow(() -> {
				messageInformationParser.parse(requestBody.toString());
			});	
		}
	}
	
	@Nested
	class ProfileInformationParsingTest {
		private RequestInformationParser profileInformationParser = new ProfileInformationParser();
		
		@Test
		void test_parse_givenEMPTYJSON_shouldthrowNullPointerException() {
			assertThrows(NullPointerException.class, () -> {
				profileInformationParser.parse(EMPTY_JSON);
			});
		}
		
		@Test
		void test_parse_givenMALFORMED_JSON_shouldthrowJsonSyntaxException() {
			assertThrows(JsonSyntaxException.class, () -> {
				profileInformationParser.parse(MALFORMED_JSON);
			});
		}
		
		@Test
		void test_parse_givenProperRequestBody_shouldNotThrowAnyException() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree("John"));
			requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree("Smith"));
			requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree("312-456-7890"));
			requestBody.add(ResponseBodyUtility.PROFILE_PICTURE_KEY, gson.toJsonTree("http://example.com/images/john-smith.jpeg"));
			requestBody.add(ResponseBodyUtility.IS_ACTIVE_KEY, gson.toJsonTree(false));
			
			assertDoesNotThrow(() -> {
				profileInformationParser.parse(requestBody.toString());
			});	
		}
	}
	
	@Nested
	class RateInformationParsingTest {
		private RequestInformationParser rateInformationParser = new RateInformationParser();
		
		@Test
		void test_parse_givenEMPTYJSON_shouldthrowNullPointerException() {
			assertThrows(NullPointerException.class, () -> {
				rateInformationParser.parse(EMPTY_JSON);
			});
		}
		
		@Test
		void test_parse_givenMALFORMED_JSON_shouldthrowJsonSyntaxException() {
			assertThrows(JsonSyntaxException.class, () -> {
				rateInformationParser.parse(MALFORMED_JSON);
			});
		}
		
		@Test
		void test_parse_givenProperRequestBody_shouldNotThrowAnyException() {
			JsonObject requestBody = new JsonObject();
			requestBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(47));
			requestBody.add(ResponseBodyUtility.SENT_BY_ID_KEY, gson.toJsonTree(70));
			requestBody.add(ResponseBodyUtility.RATING_KEY, gson.toJsonTree(5));
			requestBody.add(ResponseBodyUtility.COMMENT_KEY, gson.toJsonTree("Blah."));
			
			assertDoesNotThrow(() -> {
				rateInformationParser.parse(requestBody.toString());
			});	
		}
	}
	
	@Nested
	class RideInformationParsingTest {
		private RequestInformationParser rideInformationParser = new RideInformationParser();

		@Test
		void test_parse_givenEMPTYJSON_shouldthrowNullPointerException() {
			assertThrows(NullPointerException.class, () -> {
				rideInformationParser.parse(EMPTY_JSON);
			});
		}
		
		@Test
		void test_parse_givenMALFORMED_JSON_shouldthrowJsonSyntaxException() {
			assertThrows(JsonSyntaxException.class, () -> {
				rideInformationParser.parse(MALFORMED_JSON);
			});
		}
		
		@Test
		void test_parse_givenProperRequestBody_shouldNotThrowAnyException() {
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
			
			assertDoesNotThrow(() -> {
				rideInformationParser.parse(requestBody.toString());
			});	
		}
		
		@Test
		void test_parse_givenProperRequestBody_withNullForAmountPerPassenger_shouldNotThrowAnyException() {
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
			requestBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, JsonNull.INSTANCE);
			requestBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree("No more than one carry on per passenger. No pets."));
			
			assertDoesNotThrow(() -> {
				rideInformationParser.parse(requestBody.toString());
			});	
		}
	}
	
	@Nested
	class UpdateJoinRequestInformationParsingTest {
		private RequestInformationParser updateJoinRequestInformationParser = new UpdateJoinRequestInformationParser();
		
		@Test
		void test_parse_givenEMPTYJSON_shouldthrowNullPointerException() {
			assertThrows(NullPointerException.class, () -> {
				updateJoinRequestInformationParser.parse(EMPTY_JSON);
			});
		}
		
		@Test
		void test_parse_givenMALFORMED_JSON_shouldthrowJsonSyntaxException() {
			assertThrows(JsonSyntaxException.class, () -> {
				updateJoinRequestInformationParser.parse(MALFORMED_JSON);
			});
		}
		
		@Test
		void test_parse_givenProperRequestBody_shouldNotThrowAnyException() {
			JsonObject rideConfirmedUpdate = new JsonObject();
			rideConfirmedUpdate.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(47));
			rideConfirmedUpdate.add(ResponseBodyUtility.RIDE_CONFIRMED_KEY, gson.toJsonTree(true));

			JsonObject pickupConfirmedUpdate = new JsonObject();
			pickupConfirmedUpdate.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(47));
			pickupConfirmedUpdate.add(ResponseBodyUtility.PICKUP_CONFIRMED_KEY, gson.toJsonTree(true));
			
			assertDoesNotThrow(() -> {
				updateJoinRequestInformationParser.parse(rideConfirmedUpdate.toString());
				updateJoinRequestInformationParser.parse(pickupConfirmedUpdate.toString());
			});	
		}
	}
}
