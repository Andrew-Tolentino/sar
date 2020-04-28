package edu.iit.hawk.atolentino.join_request_management;

import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.request_information_processing.entities.JoinRequestInformation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

public class JoinRequestManagerTest {
	private JoinRequestManager joinRequestManager;
	JoinRequestInformation joinRequestInformationFixture;
	
	private static final int AID = 1;
	private static final int PASSENGERS = 2;
	private static Boolean RIDE_CONFIRMED = null;
	private static Boolean PICKUP_CONFIRMED = null;
	
	@BeforeEach
	void init() {
		joinRequestManager = new JoinRequestManager();
		joinRequestInformationFixture = new JoinRequestInformation(AID, PASSENGERS, RIDE_CONFIRMED, PICKUP_CONFIRMED);
	}
	
	@Test
	void test_addJoinRequest_withJoinRequestInformationFixture_getAidShouldReturnAID() {
		int jid = joinRequestManager.addJoinRequest(joinRequestInformationFixture);
		assertTrue(joinRequestManager.doesJoinRequestExist(jid));
	}
	
	@Test
	void test_doesJoinRequestExist_shouldReturnFalse() {
		assertFalse(joinRequestManager.doesJoinRequestExist(-1));
	}
	
	@Nested
	class JoinRequestManagerWithAddedJoinRequest {
		private int jid;

		@BeforeEach
		void init() {
			jid = joinRequestManager.addJoinRequest(joinRequestInformationFixture);
		}
		
		@Test
		void test_getAid_shouldReturnAID() {
			assertEquals(AID, joinRequestManager.getAid(jid));
		}
		
		@Test
		void test_denyJoinRequest_shouldReturnFalse() {
			joinRequestManager.denyJoinRequest(jid);
			assertFalse(joinRequestInformationFixture.getRideConfirmed());
		}
		
		@Test
		void test_acceptJoinRequest_shouldReturnTrue() {
			joinRequestManager.acceptJoinRequest(jid);
			assertTrue(joinRequestInformationFixture.getRideConfirmed());
		}
		
		@Test
		void test_does_joinRequestExist_shouldReturnTrue() {
			assertTrue(joinRequestManager.doesJoinRequestExist(jid));
		}
		
		@Test
		void test_sentJoinRequestToRide_withAID_shouldReturnTrue() {
			assertTrue(joinRequestManager.sentJoinRequestToRide(AID));
		}
		
		@Test
		void test_sentJoinRequestToRide_withNegativeOne_shouldReturnFalse() {
			assertFalse(joinRequestManager.sentJoinRequestToRide(-1));
		}
		
		@Test
		void test_isRider_afterAcceptingAIDJoinRequest_shouldReturnTrue() {
			joinRequestManager.acceptJoinRequest(jid);
			assertTrue(joinRequestManager.isRider(AID));
		}
		
		@Test
		void test_isRider_default_shouldReturnFalse() {
			assertFalse(joinRequestManager.isRider(AID));
		}
		
		@Test
		void test_isRider_afterDenyingAIDJoinRequest_shouldReturnFalse() {
			joinRequestManager.denyJoinRequest(jid);
			assertFalse(joinRequestManager.isRider(AID));
		}
	}
	
}
