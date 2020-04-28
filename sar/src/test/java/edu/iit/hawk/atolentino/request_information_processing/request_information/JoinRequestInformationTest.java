package edu.iit.hawk.atolentino.request_information_processing.request_information;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.request_information_processing.entities.JoinRequestInformation;

public class JoinRequestInformationTest {
	private JoinRequestInformation joinRequestInformation;
	private static final Integer AID = 1;
	private static final Integer PASSENGERS = 1;
	private Boolean RIDE_CONFIRMED = null;
	private Boolean PICKUP_CONFIRMED = null;
	
	@BeforeEach
	void init() {
		joinRequestInformation = new JoinRequestInformation(
				AID, PASSENGERS, RIDE_CONFIRMED, PICKUP_CONFIRMED);
	}
	
	@Test
	void test_getAid_shouldReturnAid() {
		assertEquals(AID, joinRequestInformation.getAid());
	}
	
	@Test
	void test_getNumberOfPassengers_shouldReturnPASSENGERS() {
		assertEquals(PASSENGERS, joinRequestInformation.getNumberOfPassengers());
	}
	
	@Test
	void test_getRideConfirmed_shouldReturnNull() {
		assertNull(joinRequestInformation.getRideConfirmed());
	}
	
	@Test
	void test_setRideConfirmed_withTrue_shouldReturnTrue() {
		joinRequestInformation.setRideConfirmed(true);
		assertTrue(joinRequestInformation.getRideConfirmed());
	}
	
	@Test
	void test_setRideConfirmed_withFalse_shouldReturnFalse() {
		joinRequestInformation.setRideConfirmed(false);
		assertFalse(joinRequestInformation.getRideConfirmed());
	}
	
	@Test
	void test_getPickUpConfirmed_shouldReturnNull() {
		assertNull(joinRequestInformation.getPickUpConfirmed());
	}
	
	@Test
	void test_setPickUpConfirmed_shouldReturnTrue() {
		joinRequestInformation.setPickUpConfirmed();
		assertTrue(joinRequestInformation.getPickUpConfirmed());
	}
	
	@Test
	void test_isNill_shouldReturnFalse() {
		assertFalse(joinRequestInformation.isNill());
	}
}
