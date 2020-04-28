package edu.iit.hawk.atolentino.response_generator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import edu.iit.hawk.atolentino.account_management.boundaries.AccountUtilities;
import edu.iit.hawk.atolentino.account_management.interactors.Account;
import edu.iit.hawk.atolentino.message_management.entities.Message;
import edu.iit.hawk.atolentino.rate_management.entities.Rate;
import edu.iit.hawk.atolentino.reports.boundaries.ShareARideProcess;
import edu.iit.hawk.atolentino.reports.interactors.Process907;
import edu.iit.hawk.atolentino.reports.interactors.Process911;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.MessageInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.NillRequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.ProfileInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.RideInformation;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;
import edu.iit.hawk.atolentino.response_generator.response_bodies.MessageHistoryResponseBody;
import edu.iit.hawk.atolentino.response_generator.response_bodies.PostResponseBody;
import edu.iit.hawk.atolentino.response_generator.response_bodies.ReportResponseBody;
import edu.iit.hawk.atolentino.response_generator.response_bodies.SearchAccountsResponseBody;
import edu.iit.hawk.atolentino.response_generator.response_bodies.SearchRidesResponseBody;
import edu.iit.hawk.atolentino.response_generator.response_bodies.ValidationErrorBody;
import edu.iit.hawk.atolentino.response_generator.response_bodies.ViewAllReportsResponseBody;
import edu.iit.hawk.atolentino.response_generator.response_bodies.ViewRatingsResponseBody;
import edu.iit.hawk.atolentino.response_generator.response_bodies.ViewRatingsResponseBody.RatingType;
import edu.iit.hawk.atolentino.response_generator.response_bodies.ViewRideDetailResponseBody;
import edu.iit.hawk.atolentino.ride_management.boundaries.RideUtilities;
import edu.iit.hawk.atolentino.ride_management.interactors.Ride;

public class ResponseBodyUtilityTest {
	private Gson gson = new Gson();
	
	@Test
	void test_invalidAccountErrorMessage_shouldReturnExpectedMessage() {
		int AID = 1;
		String EXPECTED_MESSAGE = "This account (1) doesn't exist or is not active.";
		assertEquals(EXPECTED_MESSAGE, ResponseBodyUtility.invalidAccountErrorMessage(AID));
	}

	@Test
	void test_invalidAccountRideConfirmedErrorMessage_shouldReturnExpectedMessage() {
		int AID = 1;
		int RID = 1;
		String EXPECTED_MESSAGE = "This account (1) didn't create the ride (1)";
		assertEquals(EXPECTED_MESSAGE, ResponseBodyUtility.invalidAccountRideConfirmedErrorMessage(RID, AID));
	}
	
	@Test
	void test_invalidAccountPickUpConfirmedErrorMessage_shouldReturnExpectedMessage() {
		int AID = 1;
		int RID = 1;
		String EXPECTED_MESSAGE = "This account (1) has not requested to join this ride (1)";
		assertEquals(EXPECTED_MESSAGE, ResponseBodyUtility.invalidAccountPickUpConfirmedErrorMessage(RID, AID));
	}
	
	@Test
	void test_inactiveAccountCreateRideErrorMessage_shouldReturnExpectedMessage() {
		int AID = 1;
		String EXPECTED_MESSAGE = "This account (1) is not active, may not create a ride.";
		assertEquals(EXPECTED_MESSAGE, ResponseBodyUtility.inactiveAccountCreateRideErrorMessage(AID));
	}
	
	@Test
	void test_inactiveAccountCreateJoinRequestErrorMessage_shouldReturnExpectedMessage() {
		int AID = 1;
		String EXPECTED_MESSAGE = "This account (1) is not active, may not create a join ride request.";
		assertEquals(EXPECTED_MESSAGE, ResponseBodyUtility.inactiveAccountCreateJoinRequestErrorMessage(AID));
	}
	
	@Test
	void test_createSentJoinRequestAlreadyErrorMessage_shouldReturnExpectedMessage() {
		int AID = 1;
		int RID = 1;
		String EXPECTED_MESSAGE = "This account (1) is has already sent a join request to ride (1)";
		assertEquals(EXPECTED_MESSAGE, ResponseBodyUtility.createSentJoinRequestAlreadyErrorMessage(RID,AID));
	}
	
	@Test
	void test_createNonRideParticipantRatingErrorMessage_shouldReturnExpectedMessage() {
		int AID = 1;
		int RID = 1;
		String EXPECTED_MESSAGE = "This account (1) didn't create this ride (1) nor was it a passenger";
		assertEquals(EXPECTED_MESSAGE, ResponseBodyUtility.createNonRideParticipantRatingErrorMessage(RID,AID));
	}
	
	@Test
	void test_createNonExistingRideErrorMessage_shouldReturnExpectedMessage() {
		int RID = 1;
		String EXPECTED_MESSAGE = "This ride (1) does not exist";
		assertEquals(EXPECTED_MESSAGE, ResponseBodyUtility.createNonExistingRideErrorMessage(RID));
	}

	
	@Nested
	public class MessageHistoryResponseBodyTest {
		private String expectedJsonString;
		private final int MID = 1;
		private final int AID = 1;
		private final String MSG = "Hello";
		private Message message;
		
		@BeforeEach
		void init() {
			message = new Message(MID, new MessageInformation(AID, MSG));
			JsonArray jsonArray = new JsonArray();		
			JsonObject jsonObject = new JsonObject();
			jsonObject.add(ResponseBodyUtility.MESSAGE_ID_KEY, gson.toJsonTree(message.getMid()));
			jsonObject.add(ResponseBodyUtility.SENT_BY_ACCOUNT_KEY, gson.toJsonTree(message.getAid()));
			jsonObject.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree(message.getDateCreated()));
			jsonObject.add(ResponseBodyUtility.BODY_KEY, gson.toJsonTree(message.getMsg()));
			jsonArray.add(jsonObject);
			expectedJsonString = jsonArray.toString();
		}
		
		@Test
		void test_generateBody_shouldReturnExpectedJsonString() {
			List<Message> messages = new ArrayList<Message>();
			messages.add(message);
			Iterator<Message> iterator = messages.iterator();
			ResponseBodyUtility messageHistoryResponseBody = new MessageHistoryResponseBody(iterator);
			assertEquals(expectedJsonString, messageHistoryResponseBody.generateBody());
		}
	}
	
	@Nested
	public class PostResponseBodyTest {
		private final int value = 1;
		
		@Test
		void test_generateBody_shouldReturnExpectedJsonStringForRideCreation() {
			String key = ResponseBodyUtility.RIDE_ID_KEY;
			JsonObject jsonBody = new JsonObject();	
			jsonBody.add(key, gson.toJsonTree(value));
			String expectedJsonString = jsonBody.toString();
			
			ResponseBodyUtility postResponseBody = new PostResponseBody(key, value);
			assertEquals(expectedJsonString, postResponseBody.generateBody());
		}
		
		@Test
		void test_generateBody_shouldReturnExpectedJsonStringForMessageCreation() {
			String key = ResponseBodyUtility.MESSAGE_ID_KEY;
			JsonObject jsonBody = new JsonObject();	
			jsonBody.add(key, gson.toJsonTree(value));
			String expectedJsonString = jsonBody.toString();
			
			ResponseBodyUtility postResponseBody = new PostResponseBody(key, value);
			assertEquals(expectedJsonString, postResponseBody.generateBody());
		}
		
		@Test
		void test_generateBody_shouldReturnExpectedJsonStringForAccountCreation() {
			String key = ResponseBodyUtility.ACCOUNT_ID_KEY;
			JsonObject jsonBody = new JsonObject();	
			jsonBody.add(key, gson.toJsonTree(value));
			String expectedJsonString = jsonBody.toString();
			
			ResponseBodyUtility postResponseBody = new PostResponseBody(key, value);
			assertEquals(expectedJsonString, postResponseBody.generateBody());
		}
		
		@Test
		void test_generateBody_shouldReturnExpectedJsonStringForRatingCreation() {
			String key = ResponseBodyUtility.RATING_ID_KEY;
			JsonObject jsonBody = new JsonObject();	
			jsonBody.add(key, gson.toJsonTree(value));
			String expectedJsonString = jsonBody.toString();
			
			ResponseBodyUtility postResponseBody = new PostResponseBody(key, value);
			assertEquals(expectedJsonString, postResponseBody.generateBody());
		}
		
		@Test
		void test_generateBody_shouldReturnExpectedJsonStringForJoinRequestCreation() {
			String key = ResponseBodyUtility.JOIN_RIDE_ID_REQUEST_KEY;
			JsonObject jsonBody = new JsonObject();	
			jsonBody.add(key, gson.toJsonTree(value));
			String expectedJsonString = jsonBody.toString();
			
			ResponseBodyUtility postResponseBody = new PostResponseBody(key, value);
			assertEquals(expectedJsonString, postResponseBody.generateBody());
		}
	}
	
	@Nested
	public class ReportResponseBodyTest {
		private Iterator<RideUtilities> iterator;
		private String startDate = "14-Apr-2020";
		private RideUtilities ride;
		private final int RID = 123;
		private final int AID = 19;
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
		private final int MAX_PASSENGERS = 2;
		private final double AMOUNT_PER_PASSENGER = 15.00;
		private final String CONDITIONS = "No more than one carry on per passenger. No pets.";
		
		private String produceExpectedReportJson(RideUtilities ride, ShareARideProcess process) {
			JsonObject jsonBody = new JsonObject();
			JsonArray detail = new JsonArray();
			
			jsonBody.add(ResponseBodyUtility.REPORT_ID_KEY, gson.toJsonTree(process.getPid()));
			jsonBody.add(ResponseBodyUtility.NAME_KEY, gson.toJsonTree(process.getName()));
			jsonBody.add(ResponseBodyUtility.START_DATE_KEY, gson.toJsonTree(process.getStartDate()));
			jsonBody.add(ResponseBodyUtility.END_DATE_KEY, gson.toJsonTree(process.getEndDate()));
			jsonBody.add(ResponseBodyUtility.RIDES_KEY, gson.toJsonTree(1));
			
			JsonObject jsonObject = new JsonObject();
			jsonObject.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree(ride.getFromZip()));
			jsonObject.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree(ride.getToZip()));
			jsonObject.add(ResponseBodyUtility.COUNT_KEY, gson.toJsonTree(1));
			detail.add(jsonObject);
			
			jsonBody.add(ResponseBodyUtility.DETAIL_KEY, detail);
			return jsonBody.toString();
		}
		
		@BeforeEach
		void init() {
			List<RideUtilities> rides = new ArrayList<RideUtilities>();
			ride = new Ride(RID, new RideInformation(
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
					));
			rides.add(ride);
			iterator = rides.iterator();
		}
		
		@Test
		void test_generateBody_forProcess907_shouldReturnExpectedReportJson() {
			ShareARideProcess process = new Process907(startDate, "", iterator);
			String expectedReportJson = produceExpectedReportJson(ride, process);
			ResponseBodyUtility reportResponseBody = new ReportResponseBody(process);
			assertEquals(expectedReportJson, reportResponseBody.generateBody());
		}
		
		@Test
		void test_generateBody_forProcess911_shouldReturnExpectedReportJson() {
			ShareARideProcess process = new Process911(startDate, "", iterator);
			String expectedReportJson = produceExpectedReportJson(ride, process);
			ResponseBodyUtility reportResponseBody = new ReportResponseBody(process);
			assertEquals(expectedReportJson, reportResponseBody.generateBody());
		}
		
	}
	
	@Nested
	public class SearchAccountsResponseBodyTest {
		private Iterator<AccountUtilities> iterator;
		private AccountUtilities account;
		
		@BeforeEach
		void init() {
			List<AccountUtilities> accounts = new ArrayList<AccountUtilities>();
			ProfileInformation profileInformation = new ProfileInformation(
					"John", "Smith", "312-456-7890", "http://example.com/images/john-smith.jpeg", false);
			
			account = new Account(1, profileInformation);
			accounts.add(account);
			iterator = accounts.iterator();
		}
		
		@Test
		void test_generateBody_shouldReturnExpectedSearchAccountsJson() {
			JsonArray jsonArray = new JsonArray();		
			JsonObject jsonObject = new JsonObject();
			jsonObject.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(account.getAid()));
			jsonObject.add(ResponseBodyUtility.NAME_KEY, gson.toJsonTree(account.getFirstName() + " " + account.getLastName()));
			jsonObject.add(ResponseBodyUtility.DATE_CREATED_KEY, gson.toJsonTree(account.getDateCreated()));
			jsonObject.add(ResponseBodyUtility.IS_ACTIVE_KEY, gson.toJsonTree(account.isActive()));
			jsonArray.add(jsonObject);
			String expectedSearchAccountsJson = jsonArray.toString();
			
			ResponseBodyUtility searchAccountsResponseBody = new SearchAccountsResponseBody(iterator);
			assertEquals(expectedSearchAccountsJson, searchAccountsResponseBody.generateBody());
		}
	}
	
	@Nested
	public class SearchRidesResponseBodyTest {
		private Iterator<RideUtilities> iterator;
		private RideUtilities ride;
		private final int RID = 123;
		private final int AID = 19;
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
		private final int MAX_PASSENGERS = 2;
		private final double AMOUNT_PER_PASSENGER = 15.00;
		private final String CONDITIONS = "No more than one carry on per passenger. No pets.";
		
		@BeforeEach
		void init() {
			List<RideUtilities> rides = new ArrayList<RideUtilities>();
			ride = new Ride(RID, new RideInformation(
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
					));
			rides.add(ride);
			iterator = rides.iterator();
		}
		
		@Test
		void test_generateBody_shouldReturnExpectedSearchRidesJson() {
			JsonArray jsonArray = new JsonArray();
			JsonObject jsonObject = new JsonObject();
			
			JsonObject locationInfo = new JsonObject();
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree(ride.getFromCity()));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree(ride.getFromZip()));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree(ride.getToCity()));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree(ride.getToZip()));
			
			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree(ride.getDate()));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree(ride.getTime()));

			jsonObject.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(ride.getRid()));
			jsonObject.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			jsonObject.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);
			jsonArray.add(jsonObject);
			
			String expectedSearchRidesJson = jsonArray.toString();
			ResponseBodyUtility searchRidesResponseBody = new SearchRidesResponseBody(iterator);
			assertEquals(expectedSearchRidesJson, searchRidesResponseBody.generateBody());
		}
	}
	
	@Nested
	public class ValidationErrorBodyTest {
		@Test
		void test_generateBody_usingConstructorWithoutRequestInformation_shouldReturnExpectedValidationErrorJson() {
			String DETAIL = "Error";
			UriInfo URIINFO = Mockito.mock(UriInfo.class);
			int STATUS_CODE = 404;
			JsonObject jsonObject = new JsonObject();	
			jsonObject.add(ResponseBodyUtility.ERROR_RESPONSE_TYPE_KEY, gson.toJsonTree(ResponseBodyUtility.ERROR_RESPONSE_TYPE_RESOURCE_MESSAGE));
			jsonObject.add(ResponseBodyUtility.ERROR_RESPONSE_TITLE_KEY, gson.toJsonTree(ResponseBodyUtility.ERROR_RESPONSE_TITLE_VALIDATION_MESSAGE));
			jsonObject.add(ResponseBodyUtility.ERROR_RESPONSE_DETAIL_KEY, gson.toJsonTree(DETAIL));
			jsonObject.add(ResponseBodyUtility.ERROR_RESPONSE_STATUS_KEY, gson.toJsonTree(STATUS_CODE));
			jsonObject.add(ResponseBodyUtility.ERROR_RESPONSE_INSTANCE_KEY, gson.toJsonTree("/" + URIINFO.getPath()));
			String expectedValidationErrorBodyJson = jsonObject.toString();
			
			ValidationErrorBody validationErrorBody = new ValidationErrorBody(DETAIL, URIINFO, STATUS_CODE);
			assertEquals(expectedValidationErrorBodyJson, validationErrorBody.generateBody());
		}
		
		@Test
		void test_generateBody_usingConstructorWithRequestInformation_shouldReturnExpectedValidationErrorJson() {
			String DETAIL = "Error";
			UriInfo URIINFO = Mockito.mock(UriInfo.class);
			int STATUS_CODE = 404;
			RequestInformation nillRequestInformation = new NillRequestInformation(DETAIL);
			JsonObject jsonObject = new JsonObject();	
			jsonObject.add(ResponseBodyUtility.ERROR_RESPONSE_TYPE_KEY, gson.toJsonTree(ResponseBodyUtility.ERROR_RESPONSE_TYPE_RESOURCE_MESSAGE));
			jsonObject.add(ResponseBodyUtility.ERROR_RESPONSE_TITLE_KEY, gson.toJsonTree(ResponseBodyUtility.ERROR_RESPONSE_TITLE_VALIDATION_MESSAGE));
			jsonObject.add(ResponseBodyUtility.ERROR_RESPONSE_DETAIL_KEY, gson.toJsonTree(DETAIL));
			jsonObject.add(ResponseBodyUtility.ERROR_RESPONSE_STATUS_KEY, gson.toJsonTree(STATUS_CODE));
			jsonObject.add(ResponseBodyUtility.ERROR_RESPONSE_INSTANCE_KEY, gson.toJsonTree("/" + URIINFO.getPath()));
			String expectedValidationErrorBodyJson = jsonObject.toString();
			
			ValidationErrorBody validationErrorBody = new ValidationErrorBody(nillRequestInformation, URIINFO, STATUS_CODE);
			assertEquals(expectedValidationErrorBodyJson, validationErrorBody.generateBody());
		}
		
		
	}
	
	
	
	@Nested
	public class ViewAllReportsResponseBodyTest {
		@Test
		void test_generateBody_shouldReturnExpectedViewAllReportsJson() {
			String expectedViewAllReportsJson = 
					"[{\"pid\":907,\"name\":\"Rides posted between two dates\"},{\"pid\":911,\"name\":\"Rides taken between two dates\"}]";
			System.out.println(expectedViewAllReportsJson);
			ResponseBodyUtility viewAllReportsResponseBody = new ViewAllReportsResponseBody();
			assertEquals(expectedViewAllReportsJson, viewAllReportsResponseBody.generateBody());
		}
	}
	
	@Nested
	public class ViewRatingsResponseBodyTest {
		private AccountUtilities account;
		
		@BeforeEach
		void init() {
			List<AccountUtilities> accounts = new ArrayList<AccountUtilities>();
			ProfileInformation profileInformation = new ProfileInformation(
					"John", "Smith", "312-456-7890", "http://example.com/images/john-smith.jpeg", false);
			
			account = new Account(1, profileInformation);
			accounts.add(account);
		}
		
		@Test
		void test_generateBody_getDriverRatings_shouldReturnExpectedRatingJson() {
			JsonObject jsonBody = new JsonObject();
			JsonArray detail = new JsonArray();		
			
			jsonBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(account.getAid()));
			jsonBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree(account.getFirstName()));
			jsonBody.add(ResponseBodyUtility.RIDES_KEY, gson.toJsonTree(account.getDriverRideParticipation()));
			jsonBody.add(ResponseBodyUtility.RATINGS_KEY, gson.toJsonTree(account.getNumberOfDriverRatings()));
			jsonBody.add(ResponseBodyUtility.AVERAGE_RATING_KEY, gson.toJsonTree(
					 account.getDriverAverageRating() == null 
					? JsonNull.INSTANCE :  account.getDriverAverageRating()
					));
			jsonBody.add(ResponseBodyUtility.DETAIL_KEY, detail);
			String expectedJson = jsonBody.toString();
			
			ResponseBodyUtility viewRatingsResponseBody = new ViewRatingsResponseBody(account, RatingType.DRIVER);
			assertEquals(expectedJson, viewRatingsResponseBody.generateBody());
		}
		
		@Test
		void test_generateBody_getRiderRatings_shouldReturnExpectedRatingJson() {
			JsonObject jsonBody = new JsonObject();
			JsonArray detail = new JsonArray();		
			
			jsonBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(account.getAid()));
			jsonBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree(account.getFirstName()));
			jsonBody.add(ResponseBodyUtility.RIDES_KEY, gson.toJsonTree(account.getRiderRideParticipation()));
			jsonBody.add(ResponseBodyUtility.RATINGS_KEY, gson.toJsonTree(account.getNumberOfRiderRatings()));
			jsonBody.add(ResponseBodyUtility.AVERAGE_RATING_KEY, gson.toJsonTree(
					 account.getRiderAverageRating() == null 
					? JsonNull.INSTANCE :  account.getRiderAverageRating()
					));
			jsonBody.add(ResponseBodyUtility.DETAIL_KEY, detail);
			String expectedJson = jsonBody.toString();
			
			ResponseBodyUtility viewRatingsResponseBody = new ViewRatingsResponseBody(account, RatingType.RIDER);
			assertEquals(expectedJson, viewRatingsResponseBody.generateBody());
		}
		
	}
	
	@Nested
	public class ViewRideDetailResponseBodyTest {
		private AccountUtilities account;
		private RideUtilities ride;
		private final int RID = 123;
		private final int AID = 19;
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
		private final int MAX_PASSENGERS = 2;
		private final double AMOUNT_PER_PASSENGER = 15.00;
		private final String CONDITIONS = "No more than one carry on per passenger. No pets.";
		
		
		@BeforeEach
		void init() {
			ProfileInformation profileInformation = new ProfileInformation(
					"John", "Smith", "312-456-7890", "http://example.com/images/john-smith.jpeg", false);
			
			account = new Account(1, profileInformation);
			ride = new Ride(RID, new RideInformation(
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
					));
		}
		
		@Test
		void test_generateBody_generatesExpectedViewRideDetailJson() {
			JsonObject jsonBody = new JsonObject();

			jsonBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(ride.getRid()));
			
			JsonObject locationInfo = new JsonObject();
			jsonBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);
			locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree(ride.getFromCity()));
			locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree(ride.getFromZip()));
			locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree(ride.getToCity()));
			locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree(ride.getToZip()));
			
			jsonBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, locationInfo);

			JsonObject dateTime = new JsonObject();
			dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree(ride.getDate()));
			dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree(ride.getTime()));

			jsonBody.add(ResponseBodyUtility.DATE_TIME_KEY, dateTime);

			JsonObject carInfo = new JsonObject();
			carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree(ride.getMake()));
			carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree(ride.getModel()));
			carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree(ride.getColor()));
			carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree(ride.getPlateState()));
			carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree(ride.getPlateSerial()));
			jsonBody.add(ResponseBodyUtility.CAR_INFO_KEY, carInfo);

			jsonBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(ride.getMaxPassengers()));
			jsonBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(
					ride.getAmountPerPassenger() == null 
					? JsonNull.INSTANCE : ride.getAmountPerPassenger()
					));	
			jsonBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree(ride.getConditions()));
			jsonBody.add(ResponseBodyUtility.DRIVER_KEY, gson.toJsonTree(account.getFirstName()));
			jsonBody.add(ResponseBodyUtility.DRIVER_PICTURE_KEY, gson.toJsonTree(account.getProfilePicture()));
			jsonBody.add(ResponseBodyUtility.RIDES_KEY, gson.toJsonTree(account.getDriverRideParticipation()));
			jsonBody.add(ResponseBodyUtility.RATINGS_KEY, gson.toJsonTree(account.getNumberOfDriverRatings()));
			jsonBody.add(ResponseBodyUtility.AVERAGE_RATING_KEY, gson.toJsonTree(
					account.getDriverAverageRating() == null 
					? JsonNull.INSTANCE : account.getDriverAverageRating()
					));	
			
			JsonArray commentsAboutDriver = new JsonArray();
			Iterator<Rate> rateIterator = account.getDriverRatingIterator();

			while(rateIterator.hasNext()) {
				Rate rate = rateIterator.next();
				JsonObject jsonObject = new JsonObject();
				jsonObject.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(rate.getRid()));
				jsonObject.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree(rate.getDate()));
				jsonObject.add(ResponseBodyUtility.RATING_KEY, gson.toJsonTree(rate.getRating()));
				jsonObject.add(ResponseBodyUtility.COMMENT_KEY, gson.toJsonTree(rate.getComment()));
				commentsAboutDriver.add(jsonObject);
			}
			
			jsonBody.add(ResponseBodyUtility.COMMENTS_ABOUT_DRIVER_KEY, commentsAboutDriver);

			String expectedJson = jsonBody.toString();
			ResponseBodyUtility viewRideDetailResponseBody = new ViewRideDetailResponseBody(ride, account);
			assertEquals(expectedJson, viewRideDetailResponseBody.generateBody());
		}
	}	
}
