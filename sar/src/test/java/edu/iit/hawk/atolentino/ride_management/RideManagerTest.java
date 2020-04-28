package edu.iit.hawk.atolentino.ride_management;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.message_management.entities.Message;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.PatchType;
import edu.iit.hawk.atolentino.request_information_processing.entities.JoinRequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.MessageInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.RideInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.UpdateJoinRequestInformation;
import edu.iit.hawk.atolentino.ride_management.boundaries.RideUtilities;
import edu.iit.hawk.atolentino.ride_management.interactors.RideManager;

public class RideManagerTest {
	private RideManager rideManager;
	private RideInformation rideInformation;
	private int rid;
	private static final int AID = 19;
	private static final String FROM_CITY = "Barrington";
	private static final String FROM_ZIP = "60010";
	private static final String TO_CITY = "Milwaukee";
	private static final String TO_ZIP = "53202";
	private static final String DATE = "14-Apr-2020";
	private static final String TIME = "09:00";
	private static final String MAKE = "Audi";
	private static final String MODEL = "A4";
	private static final String COLOR = "Gray";
	private static final String PLATE_STATE = "IL";
	private static final String PLATE_SERIAL = "COVID19";
	private static final int MAX_PASSENGERS = 2;
	private static final double AMOUNT_PER_PASSENGER = 15.00;
	private static final String CONDITIONS = "No more than one carry on per passenger. No pets.";
	
	private boolean doesRideInformationMatch(RideUtilities r, RideInformation rideInformation) {
		return (
				r.getAid() == rideInformation.getAid() &&
				r.getFromCity().equals(rideInformation.getFromCity()) &&
				r.getFromZip().equals(rideInformation.getFromZip()) && 
				r.getToCity().equals(rideInformation.getToCity()) &&
				r.getToZip().equals(rideInformation.getToZip()) &&
				r.getDate().equals(rideInformation.getDate()) &&
				r.getTime().equals(rideInformation.getTime()) &&
				r.getMake().equals(rideInformation.getMake()) &&
				r.getModel().equals(rideInformation.getModel()) && 
				r.getColor().equals(rideInformation.getColor()) && 
				r.getPlateState().equals(rideInformation.getPlateState()) &&
				r.getPlateSerial().equals(rideInformation.getPlateSerial()) &&
				r.getAmountPerPassenger().equals(rideInformation.getAmountPerPassenger()) && 
				r.getConditions().equals(rideInformation.getConditions())
				);
	}
	
	@BeforeEach
	void init() {
		rideManager = new RideManager();
	}
	
	@Test
	void test_searchRide_defaultGivenEmptyStringKeys_isEmpty() {
		Iterator<RideUtilities> iterator = rideManager.searchRide("", "", "");
		assertFalse(iterator.hasNext());
	}
	
	@Test
	void test_getRidesBetweenStartDateAndEndDate_defaultGivenEmptyStringDates_isEmpty() {
		assertDoesNotThrow(() -> {
			Iterator<RideUtilities> iterator = rideManager.getRidesBetweenStartDateAndEndDate("", "");
			assertFalse(iterator.hasNext());
		});
	}
	
	@Nested
	class AddRideTest {
		@BeforeEach
		void init() {
			rideInformation = new RideInformation(
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
			rid = rideManager.addRide(rideInformation);
		}
		
		@AfterEach
		void clean() {
			rideManager.deleteRide(rid);
		}
		
		@Test
		void test_updateRide_givenNewRideInformation_shouldUpdateRideInformation() {
			String fromCity = "Chicago";
			String fromZip = "60616";
			String toCity = "Rockford";
			String toZip = "";
			String date = "30-Apr-2020";
			String time = "15:00";
			RideInformation rideInformation = new RideInformation(
					AID,
					fromCity, 
					fromZip, 
					toCity, 
					toZip, 
					date, 
					time, 
					MAKE, 
					MODEL, 
					COLOR, 
					PLATE_STATE, 
					PLATE_SERIAL, 
					MAX_PASSENGERS, 
					AMOUNT_PER_PASSENGER, 
					CONDITIONS
					);
			rideManager.updateRide(rid, rideInformation);
			assertTrue(doesRideInformationMatch(rideManager.getRide(rid), rideInformation));
		}
		
		@Test
		void test_searchRide_givenFROMCITY_shouldContainRID() {
			Iterator<RideUtilities> iterator = rideManager.searchRide(FROM_CITY, "", "");
			RideUtilities ride = null;
			while(iterator.hasNext()) {
				ride = iterator.next();
			}
			assertEquals(rid, ride.getRid());
		}
		
		@Test
		void test_searchRide_givenFROMZIP_shouldContainRID() {
			Iterator<RideUtilities> iterator = rideManager.searchRide(FROM_ZIP, "", "");
			RideUtilities ride = null;
			while(iterator.hasNext()) {
				ride = iterator.next();
			}
			assertEquals(rid, ride.getRid());
		}
		
		@Test
		void test_searchRide_givenNotUsedFromKey_shouldNotContainRID() {
			Iterator<RideUtilities> iterator = rideManager.searchRide("blah", "", "");
			assertFalse(iterator.hasNext());
		}
		
		@Test
		void test_searchRide_givenTOCITY_shouldContainRID() {
			Iterator<RideUtilities> iterator = rideManager.searchRide("", TO_CITY, "");
			RideUtilities ride = null;
			while(iterator.hasNext()) {
				ride = iterator.next();
			}
			assertEquals(rid, ride.getRid());
		}
		
		@Test
		void test_searchRide_givenTOZIP_shouldContainRID() {
			Iterator<RideUtilities> iterator = rideManager.searchRide("", TO_ZIP, "");
			RideUtilities ride = null;
			while(iterator.hasNext()) {
				ride = iterator.next();
			}
			assertEquals(rid, ride.getRid());
		}
		
		@Test
		void test_searchRide_givenNotUsedToKey_shouldNotContainRID() {
			Iterator<RideUtilities> iterator = rideManager.searchRide("", "blah", "");
			assertFalse(iterator.hasNext());
		}
		
		@Test
		void test_searchRide_givenDATE_shouldContainRID() {
			Iterator<RideUtilities> iterator = rideManager.searchRide("", "", DATE);
			RideUtilities ride = null;
			while(iterator.hasNext()) {
				ride = iterator.next();
			}
			assertEquals(rid, ride.getRid());
		}
		
		@Test
		void test_searchRide_givenNotUsedDateKey_shouldNotContainRID() {
			Iterator<RideUtilities> iterator = rideManager.searchRide("", "", "blah");
			assertFalse(iterator.hasNext());
		}
		
		@Test
		void test_searchRide_givenEmptyStrings_shouldContainRID() {
			Iterator<RideUtilities> iterator = rideManager.searchRide("", "", "");
			RideUtilities ride = null;
			while(iterator.hasNext()) {
				ride = iterator.next();
			}
			assertEquals(rid, ride.getRid());
		}
		
		@Test
		void test_doesRideExist_givenRID_shouldReturnTrue() {
			assertTrue(rideManager.doesRideExist(rid));
		}
		
		@Test
		void test_doesRideExist_givenUnusedRid_shouldReturnFalse() {
			assertFalse(rideManager.doesRideExist(-1));
		}
		
		@Test
		void test_addJoinRequest_shouldBeSavedToRide() {
			int aid = 2;
			JoinRequestInformation joinRequestInformation = new JoinRequestInformation(aid, 2, null, null);
			int jid = rideManager.addJoinRequest(rid, joinRequestInformation);
			assertTrue(rideManager.doesJoinRequestExist(rid, jid));
		}
		
		@Test
		void test_updateRideConfirmed_setToTrueAfterAddingJoinRequest_accountShouldBeARiderOfRide() {
			int aid = 2;
			JoinRequestInformation joinRequestInformation = new JoinRequestInformation(aid, 2, null, null);
			int jid = rideManager.addJoinRequest(rid, joinRequestInformation);
			UpdateJoinRequestInformation updateJoinRequestInformation = new UpdateJoinRequestInformation(aid, true, PatchType.RIDE_CONFIRMED);
			rideManager.updateRideConfirmed(rid, jid, updateJoinRequestInformation);
			assertTrue(rideManager.isAccountRiderOfRide(rid, aid));
		}
		
		@Test
		void test_updateRideConfirmed_setToFalseAfterAddingJoinRequest_accountShouldNotBeARiderOfRide() {
			int aid = 2;
			JoinRequestInformation joinRequestInformation = new JoinRequestInformation(aid, 2, null, null);
			int jid = rideManager.addJoinRequest(rid, joinRequestInformation);
			UpdateJoinRequestInformation updateJoinRequestInformation = new UpdateJoinRequestInformation(aid, false, PatchType.RIDE_CONFIRMED);
			rideManager.updateRideConfirmed(rid, jid, updateJoinRequestInformation);
			assertFalse(rideManager.isAccountRiderOfRide(rid, aid));
		}
		
		@Test
		void test_updatePickUpConfirmed_afterAddingJoinRequest_pickUpConfirmShouldBeTrue() {
			int aid = 2;
			JoinRequestInformation joinRequestInformation = new JoinRequestInformation(aid, 2, null, null);
			int jid = rideManager.addJoinRequest(rid, joinRequestInformation);
			rideManager.updatePickUpConfirmed(rid, jid);
			assertTrue(joinRequestInformation.getPickUpConfirmed());
		}
		
		@Test
		void test_isAccountRiderOfRide_afterSendingJoinRequest_shouldReturnFalse() {
			int aid = 2;
			JoinRequestInformation joinRequestInformation = new JoinRequestInformation(aid, 2, null, null);
			rideManager.addJoinRequest(rid, joinRequestInformation);
			assertFalse(rideManager.isAccountDriverOfRide(rid, aid));
		}
		
		@Test
		void test_addMessage_shouldBeFoundInGetMessages() {
			int aid = 2;
			String MSG = "Hello";
			int MID = rideManager.addMessage(rid, new MessageInformation(aid, MSG));
			Iterator<Message> iterator = rideManager.getMessages(rid);
			Message message = null;
			while(iterator.hasNext()) {
				message = iterator.next();
			}
			assertEquals(MID, message.getMid());
		}
		
		@Test
		void test_getMessages_default_isEmpty() {
			Iterator<Message> iterator = rideManager.getMessages(rid);
			assertFalse(iterator.hasNext());
		}
		
		@Test
		void test_isAccountDriverOfRide_givenAID_shouldReturnTrue() {
			assertTrue(rideManager.isAccountDriverOfRide(rid, AID));
		}
		
		@Test
		void test_isAccountDriverOfRide_givenNonDriverAid_shouldReturnFalse() {
			assertFalse(rideManager.isAccountDriverOfRide(rid, -1));
		}
		
		@Test
		void test_getRide_givenRID_shouldReturnRideMatchingRID() {
			RideUtilities ride = rideManager.getRide(rid);
			assertEquals(rid, ride.getRid());
		}
		
		@Test
		void test_getRideDate_givenRID_shouldReturnDATE() {
			assertEquals(DATE, rideManager.getRideDate(rid));
		}
		
		@Test
		void test_getRidesBetweenStartDateAndEndDate_givenDATEForStartDate_shouldContainRID() {
			assertDoesNotThrow(() -> {
				Iterator<RideUtilities> iterator = rideManager.getRidesBetweenStartDateAndEndDate(DATE, "");
				RideUtilities ride = null;
				while(iterator.hasNext()) {
					ride = iterator.next();
				}
				assertEquals(rid, ride.getRid());
			});
		}
		
		@Test
		void test_getRidesBetweenStartDateAndEndDate_givenEmptyString_shouldContainRID() {
			assertDoesNotThrow(() -> {
				Iterator<RideUtilities> iterator = rideManager.getRidesBetweenStartDateAndEndDate("", "");
				RideUtilities ride = null;
				while(iterator.hasNext()) {
					ride = iterator.next();
				}
				assertEquals(rid, ride.getRid());
			});
		}
		
		@Test
		void test_getRidesBetweenStartDateAndEndDate_givenEndDateBeforeDATE_shouldNotContainRID() {
			assertDoesNotThrow(() -> {
				Iterator<RideUtilities> iterator = rideManager.getRidesBetweenStartDateAndEndDate("", "13-Apr-2020");
				assertFalse(iterator.hasNext());
			});
		}
		
		@Test
		void test_getRidesBetweenStartDateAndEndDate_givenWrongFormatDate_shouldThrowParseException() {
			assertThrows(ParseException.class, () -> {
				rideManager.getRidesBetweenStartDateAndEndDate("blah", "blah");
			});
		}
	}
}
	
