package edu.iit.hawk.atolentino.request_information_processing.request_information;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.PatchType;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.JoinRequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.MessageInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.NillRequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.ProfileInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.RateInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.RideInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.UpdateJoinRequestInformation;

public class RequestInformationTest {
	
	@Nested
	public class JoinRequestInformationInheritedMethodsTest {
		private RequestInformation joinRequestInformation;
		private Integer aid = 1;
		
		@BeforeEach
		void init() {
			joinRequestInformation = new JoinRequestInformation(
					aid, null, null, null);
		}
		
		@Test
		void test_getAid_shouldReturnAid() {
			assertEquals(aid, joinRequestInformation.getAid());
		}
		
		@Test
		void test_getRid_shouldReturnNull() {
			assertNull(joinRequestInformation.getRid());
		}
		
		@Test
		void test_isNill_shouldReturnFalse() {
			assertFalse(joinRequestInformation.isNill());
		}
	}
	
	@Nested
	public class MessageInformationInheritedMethodsTest {
		private RequestInformation messageInformation;
		private Integer aid = 1;
		
		@BeforeEach
		void init() {
			messageInformation = new MessageInformation(aid, null);
		}
		
		@Test
		void test_getAid_shouldReturnAid() {
			assertEquals(aid, messageInformation.getAid());;
		}
		
		@Test
		void test_getRid_shouldReturnNull() {
			assertNull(messageInformation.getRid());
		}
		
		@Test
		void test_isNill_shouldReturnFalse() {
			assertFalse(messageInformation.isNill());
		}
 	}
	
	@Nested
	public class NillRequestInformationInheritedMethodsTest  {
		private RequestInformation nillRequestInformation;
		
		@BeforeEach
		void init() {
			nillRequestInformation = new NillRequestInformation(null);
		}
		
		@Test
		void test_getAid_shouldReturnNull() {
			assertNull(nillRequestInformation.getAid());
		}
		
		@Test
		void test_getRid_shouldReturnNull() {
			assertNull(nillRequestInformation.getRid());
		}
		
		@Test
		void test_isNill_shouldReturnTrue() {
			assertTrue(nillRequestInformation.isNill());
		}
	}
	
	@Nested
	public class ProfileInformationInheritedMethodsTest {
		private RequestInformation profileInformation;
		
		@BeforeEach
		void init() {
			profileInformation = new ProfileInformation(
					"Andrew", "Tolentino", "773-226-5515", "Profile Image", false);
		}
		
		@Test
		void test_getAid_shouldReturnNull() {
			assertNull(profileInformation.getAid());
		}
		
		@Test
		void test_getRid_shouldReturnNull() {
			assertNull(profileInformation.getRid());
		}
		
		@Test
		void test_isNill_shouldReturnFalse() {
			assertFalse(profileInformation.isNill());
		}	
	}
	
	@Nested
	public class RateInformationInheritedMethodsTest {
		private RequestInformation rateInformation;
		private Integer rid = 1;
		private Integer aid = 1;
		
		@BeforeEach
		void init() {
			rateInformation = new RateInformation(rid, aid, 1, "Hello");
		}
		
		@Test
		void test_getRid_shouldReturnRid() {
			assertEquals(rid, rateInformation.getRid());
		}
		
		@Test
		void test_getAid_shouldReturnAid() {
			assertEquals(aid, rateInformation.getAid());
		}
		
		@Test
		void test_isNill_shouldReturnFalse() {
			assertFalse(rateInformation.isNill());
		}
	}
	
	@Nested
	public class RideInformationInheritedMethodsTest {
		private RequestInformation rideInformation;
		private Integer aid = 1;
		
		@BeforeEach
		void init() {
			rideInformation = new RideInformation(
					aid,
					"Barrington", 
					"60010",
					"Milwaukee", 
					"53202", 
					"14-Apr-2020", 
					"09:00",
					"Audi",
					"A4",
					"Gray",
					"IL",
					"COVID19",
					2,
					15.0, 
					"No more than one carry on per passenger. No pets."
					);
		}
		
		@Test
		void test_getAid_shouldReturnAid() {
			assertEquals(aid, rideInformation.getAid());
		}
		
		@Test
		void test_getRid_shouldReturnNull() {
			assertNull(rideInformation.getRid());
		}
		
		@Test
		void test_isNill_shouldReturnFalse() {
			assertFalse(rideInformation.isNill());
		}
	}
	
	@Nested
	class UpdateJoinRequestInformationInheritedMethodsTest {
		private RequestInformation updateJoinRequestInformation;
		private Integer aid = 1;
		
		@BeforeEach
		void init() {
			updateJoinRequestInformation = new UpdateJoinRequestInformation(aid, false, PatchType.PICKUP_CONFIRMED);
		}
		
		@Test
		void test_getAid_shouldReturnAid() {
			assertEquals(aid, updateJoinRequestInformation.getAid());
		}
		
		@Test
		void test_getRid_shouldReturnNull() {
			assertNull(updateJoinRequestInformation.getRid());
		} 
		
		@Test
		void test_isNill_shouldReturnFalse() {
			assertFalse(updateJoinRequestInformation.isNill());
		}
	}
	
}
