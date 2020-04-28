package edu.iit.hawk.atolentino.ride_management;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.message_management.entities.Message;
import edu.iit.hawk.atolentino.request_information_processing.entities.JoinRequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.MessageInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.RideInformation;
import edu.iit.hawk.atolentino.ride_management.interactors.Ride;

public class RideTest {
	private Ride ride;
	private static final int RID = 123;
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
	
	@BeforeEach
	void init() {
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
	
	private boolean doesRideInformationMatch(Ride r, RideInformation rideInformation) {
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
	
	@Test
	void test_getRid_shouldReturnRID() {
		assertEquals(RID, ride.getRid());
	}
	
	@Test
	void test_getAid_shouldReturnAID() {
		assertEquals(AID, ride.getAid());
	}
	
	@Test
	void test_getFromCity_shouldReturnFROMCITY() {
		assertEquals(FROM_CITY, ride.getFromCity());
	}
	
	@Test
	void test_getFromZip_shouldReturnFROMZIP() {
		assertEquals(FROM_ZIP, ride.getFromZip());
	}
	
	@Test
	void test_getToCity_shouldReturnTOCITY() {
		assertEquals(TO_CITY, ride.getToCity());
	}
	
	@Test
	void test_getToZip_shouldReturnTOZIP() {
		assertEquals(TO_ZIP, ride.getToZip());
	}
	
	@Test
	void test_getDate_shouldReturnDATE() {
		assertEquals(DATE, ride.getDate());
	}
	
	@Test
	void test_getTime_shouldReturnTIME() {
		assertEquals(TIME, ride.getTime());
	}
	
	@Test
	void test_getMake_shouldReturnMAKE() {
		assertEquals(MAKE, ride.getMake());
	}
	
	@Test
	void test_getModel_shouldReturnMODEL() {
		assertEquals(MODEL, ride.getModel());
	}
	
	@Test
	void test_getColor_shouldReturnCOLOR() {
		assertEquals(COLOR, ride.getColor());
	}
	
	@Test
	void test_getPlateState_shouldReturnPLATESTATE() {
		assertEquals(PLATE_STATE, ride.getPlateState());
	}
	
	@Test
	void test_getPlateSerial_shouldReturnPLATESERIAL() {
		assertEquals(PLATE_SERIAL, ride.getPlateSerial());
	}
	
	@Test
	void test_getMaxPassenger_shouldReturnMAXPASSENGERS() {
		assertEquals(MAX_PASSENGERS, ride.getMaxPassengers());
	}
	
	@Test
	void test_getAmountPerPassenger_shouldReturnAMOUNTPERPASSENGER() {
		Double amountPerPassenger = AMOUNT_PER_PASSENGER;
		assertEquals(amountPerPassenger, ride.getAmountPerPassenger());
	}
	
	@Test
	void test_getConditions_shouldReturnCONDITIONS() {
		assertEquals(CONDITIONS, ride.getConditions());
	}
	
	@Test
	void test_getMessages_default_shouldBeEmpty() {
		assertFalse(ride.getMessages().hasNext());
	}
	
	@Test
	void test_matchesFilter_givenFROMCITY_shouldReturnTrue() {
		assertTrue(ride.matchesFilter(FROM_CITY, "", ""));
	}
	
	@Test
	void test_matchesFilter_givenFROMZIP_shouldReturnTrue() {
		assertTrue(ride.matchesFilter(FROM_ZIP, "", ""));
	}
	
	@Test
	void test_matchesFilter_givenNotUsedFromKey_shouldReturnFalse() {
		assertFalse(ride.matchesFilter("blah", "", ""));
	}
	
	@Test
	void test_matchesFilter_givenTOCITY_shouldReturnTrue() {
		assertTrue(ride.matchesFilter("", TO_CITY, ""));
	}
	
	@Test
	void test_matchesFilter_givenTOZIP_shouldReturnTrue() {
		assertTrue(ride.matchesFilter("", TO_ZIP, ""));
	}
	
	@Test
	void test_matchesFilter_givenNotUsedToKey_shouldReturnFalse() {
		assertFalse(ride.matchesFilter("", "blah", ""));
	}
	
	@Test
	void test_matchesFilter_givenDATE_shouldReturnTrue() {
		assertTrue(ride.matchesFilter("", "", DATE));
	}
	
	@Test
	void test_matchesFilter_givenNotUsedDateKey_shouldReturnFalse() {
		assertFalse(ride.matchesFilter("", "", "blah"));
	}
	
	@Test
	void test_matchesFilter_givenEmptyStrings_shouldReturnTrue() {
		assertTrue(ride.matchesFilter("", "", ""));
	}
	
	@Test
	void test_isDriver_givenDriverAid_shouldReturnTrue() {
		assertTrue(ride.isDriver(AID));
	}
	
	@Test
	void test_isDriver_givenFalseAid_shouldReturnFalse() {
		assertFalse(ride.isDriver(-1));
	}
	
	@Test
	void test_isRider_givenFalseAid_shouldReturnFalse() {
		assertFalse(ride.isDriver(-1));
	}
	
	@Test
	void test_sentJoinRequest_givenFalseAid_shouldReturnFalse() {
		assertFalse(ride.sentJoinRequestToRide(-1));
	}
	
	@Test
	void test_doesJoinRequestExist_givenFalseJid_shouldReturnFalse() {
		assertFalse(ride.doesJoinRequestExist(-1));
	}
	
	@Nested
	class JoinRequestRideTest {
		private JoinRequestInformation joinRequestInformation;
		private int jid;
		private final int AID = 1;
		
		@BeforeEach
		void init() {
			joinRequestInformation =  new JoinRequestInformation(AID, 2, null, null);
			jid = ride.addJoinRequest(joinRequestInformation);
		}
		
		@Test
		void test_doesJoinRequestExist_givenAddedJoinRequestJid_shouldReturnTrue() {
			assertTrue(ride.doesJoinRequestExist(jid));
		}
		
		@Test
		void test_sentJoinRequestExist_givenJoinRequestCreatorAID_shouldReturnTrue() {
			assertTrue(ride.sentJoinRequestToRide(AID));
		}
		
		@Test
		void test_isRider_defaultAfterAddingJoinRequest_shouldReturnFalse() {
			assertFalse(ride.isRider(AID));
		}
		
		@Test
		void test_setRide_confirmRide_AIDShouldBeARider() {
			ride.setRideConfirmed(jid, true);
			assertTrue(ride.isRider(AID));
		}
		
		@Test
		void test_setRide_denyRide_AIDShouldBeARider() {
			ride.setRideConfirmed(jid, false);
			assertFalse(ride.isRider(AID));
		}
		
		@Test
		void test_setPickUpConfirmed_shouldReturnTrue() {
			ride.setPickUpConfirmed(jid);
			assertTrue(joinRequestInformation.getPickUpConfirmed());
		}
	}
	
	@Nested
	class MessageRideTest {

		@Test
		void test_addMessage_shouldBeInIterator() {
			int AID = 1;
			String MSG = "Hello";
			ride.addMessage(new MessageInformation(AID, MSG));
			Iterator<Message> iterator = ride.getMessages();
			Message message = null;
			while(iterator.hasNext()) {
				message = iterator.next();
			}
			assertEquals(AID, message.getAid());
			assertEquals(MSG, message.getMsg());
		}
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
		ride.updateRide(rideInformation);
		assertTrue(doesRideInformationMatch(ride, rideInformation));
	}
}