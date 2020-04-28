package edu.iit.hawk.atolentino.request_information_processing.request_information;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.request_information_processing.entities.ProfileInformation;

public class ProfileInformationTest {
	private ProfileInformation profileInformation;
	private static final String FIRST_NAME = "Andrew";
	private static final String LAST_NAME = "Tolentino";
	private static final String PHONE_NUMBER = "773-226-5515";
	private static final String PROFILE_PICTURE = "Profile Image";
	private static final boolean NOT_ACTIVE = false;
	
	@BeforeEach
	void init() {
		profileInformation = new ProfileInformation(FIRST_NAME, LAST_NAME, PHONE_NUMBER, PROFILE_PICTURE, NOT_ACTIVE);
	}
	
	@Test
	void test_getFirstName_shouldReturnFIRSTNAME() {
		assertEquals(FIRST_NAME, profileInformation.getFirstName());
	}
	
	@Test
	void test_getLastName_shouldReturnLASTNAME() {
		assertEquals(LAST_NAME, profileInformation.getLastName());
	}
	
	@Test
	void test_getPhoneNumber_shouldReturnPHONENUMBER() {
		assertEquals(PHONE_NUMBER, profileInformation.getPhoneNumber());
	}
	
	@Test
	void test_getProfilePicture_shouldReturnPROFILEPICTURE() {
		assertEquals(PROFILE_PICTURE, profileInformation.getProfilePicture());
	}
	
	@Test
	void test_isActive_shouldReturnNOTACTIVE() {
		assertFalse(profileInformation.isActive());
	}
	
	@Test
	void test_matchesFilter_givenFIRSTNAME_shouldReturnTrue() {
		assertTrue(profileInformation.matchesFilter(FIRST_NAME));
	}
	
	@Test
	void test_matchesFilter_givenLASTNAME_shouldReturnTrue() {
		assertTrue(profileInformation.matchesFilter(LAST_NAME));
	}
	
	@Test
	void test_matchesFilter_givenPHONENUMBER_shouldReturnTrue() {
		assertTrue(profileInformation.matchesFilter(PHONE_NUMBER));
	}
	
	@Test
	void test_matchesFilter_givenEmptyString_shouldReturnTrue() {
		assertTrue(profileInformation.matchesFilter(""));
	}
	
	@Test
	void test_matchesFilter_givenNonMatchingKey_shouldReturnFalse() {
		assertFalse(profileInformation.matchesFilter("blah"));
	}
	
	@Test
	void test_isNill_shouldReturnFalse() {
		assertFalse(profileInformation.isNill());
	}
}
