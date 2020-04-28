package edu.iit.hawk.atolentino.join_request_management;

import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.request_information_processing.entities.JoinRequestInformation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class JoinRequestTest {
	private JoinRequestInformation joinRequestInformation;
	
	private static final Integer AID = 1;
	private static final Integer PASSENGERS = 2;
	private static Boolean RIDE_CONFIRMED = null;
	private static Boolean PICKUP_CONFIRMED = null;
	
	@BeforeEach
	void init() {
		joinRequestInformation = new JoinRequestInformation(AID, PASSENGERS, RIDE_CONFIRMED, PICKUP_CONFIRMED);
	}
	
	@Test
	void test_getAid_isAID() {
		assertEquals(AID, joinRequestInformation.getAid());
	}
	
	@Test
	void test_getNumberOfPassengers_isPASSENGERS() {
		assertEquals(PASSENGERS, joinRequestInformation.getNumberOfPassengers());;
	}
	
	@Test
	void test_getRideConfirmed_default_shouldBeNull() {
		assertNull(joinRequestInformation.getRideConfirmed());
	}
	
	@Test
	void test_setRideConfirmed_setTrue_shouldReturnTrue() {
		joinRequestInformation.setRideConfirmed(true);
		assertTrue(joinRequestInformation.getRideConfirmed());
	}
	
	@Test
	void test_setRideConfirmed_setFalse_shouldReturnFalse() {
		joinRequestInformation.setRideConfirmed(false);
		assertFalse(joinRequestInformation.getRideConfirmed());
	}
	
	@Test
	void test_getPickUpConfirmed_default_shouldBeNull() {
		assertNull(joinRequestInformation.getPickUpConfirmed());
	}
	
	@Test
	void test_setPickUpConfirmed_shouldBeTrue() {
		joinRequestInformation.setPickUpConfirmed();
		assertTrue(joinRequestInformation.getPickUpConfirmed());
	}
}
