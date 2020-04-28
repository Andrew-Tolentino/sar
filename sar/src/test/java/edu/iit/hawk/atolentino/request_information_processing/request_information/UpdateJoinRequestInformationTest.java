package edu.iit.hawk.atolentino.request_information_processing.request_information;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.PatchType;
import edu.iit.hawk.atolentino.request_information_processing.entities.UpdateJoinRequestInformation;

public class UpdateJoinRequestInformationTest {
	private UpdateJoinRequestInformation updateJoinRequestInformation;
	private static final Integer AID = 1;
	private static final Boolean STATUS = true;
	private static final PatchType KEY = PatchType.PICKUP_CONFIRMED;
	
	@BeforeEach
	void init() {
		updateJoinRequestInformation = new UpdateJoinRequestInformation(AID, STATUS, KEY);
	}
	
	@Test
	void test_getAid_shouldReturnAID() {
		assertEquals(AID, updateJoinRequestInformation.getAid());
	}
	
	@Test
	void test_getStatus_shouldReturnSTATUS() {
		assertEquals(STATUS, updateJoinRequestInformation.getStatus());
	}
	
	@Test
	void test_isNill_shouldReturnFalse() {
		assertFalse(updateJoinRequestInformation.isNill());
	}
	
	@Test
	void test_getType_shouldReturnKEY() {
		assertEquals(KEY, updateJoinRequestInformation.getType());
	}
}
