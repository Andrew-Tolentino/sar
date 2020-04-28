package edu.iit.hawk.atolentino.request_information_processing.request_information;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.request_information_processing.entities.RideInformation;

public class RideInformationTest {
	private RideInformation rideInformation;
	private static final Integer AID = 19;
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
	private static final Integer MAX_PASSENGERS = 2;
	private static final Double AMOUNT_PER_PASSENGER = 15.00;
	private static final String CONDITIONS = "No more than one carry on per passenger. No pets.";
	
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
	}
	
	@Test
	void test_getAid_shouldReturnAID() {
		assertEquals(AID, rideInformation.getAid());
	}
	
	@Test
	void test_getFromCity_shouldReturnFROMCITY() {
		assertEquals(FROM_CITY, rideInformation.getFromCity());
	}
	
	@Test
	void test_getFromCity_shouldReturnFROMZIP() {
		assertEquals(FROM_ZIP, rideInformation.getFromZip());
	}
	
	@Test
	void test_getToCity_shouldReturnTOCITY() {
		assertEquals(TO_CITY, rideInformation.getToCity());
	}
	
	@Test
	void test_getToZip_shouldReturnTOZIP() {
		assertEquals(TO_ZIP, rideInformation.getToZip());
	}
	
	@Test
	void test_getDate_shouldReturnDATE() {
		assertEquals(DATE, rideInformation.getDate());
	}
	
	@Test
	void test_getTime_shouldReturnTIME() {
		assertEquals(TIME, rideInformation.getTime());
	}
	
	@Test
	void test_getMake_shouldReturnMAKE() {
		assertEquals(MAKE, rideInformation.getMake());
	}
	
	@Test
	void test_getModel_shouldReturnMODEL() {
		assertEquals(MODEL, rideInformation.getModel());
	}
	
	@Test
	void test_getColor_shouldReturnCOLOR() {
		assertEquals(COLOR, rideInformation.getColor());
	}
	
	@Test
	void test_getPlateState_shouldReturnPLATESTATE() {
		assertEquals(PLATE_STATE, rideInformation.getPlateState());
	}
	
	@Test
	void test_getPlateSerial_shouldReturnPLATESERIAL() {
		assertEquals(PLATE_SERIAL, rideInformation.getPlateSerial());
	}
	
	@Test
	void test_getMaxPassengers_shouldReturnMAXPASSENGERS() {
		assertEquals(MAX_PASSENGERS, rideInformation.getMaxPassengers());
	}
	
	@Test
	void test_getAmountPerPassenger_shouldReturnAMOUNTPERPASSENGERS() {
		assertEquals(AMOUNT_PER_PASSENGER, rideInformation.getAmountPerPassenger());
	}
	
	@Test
	void test_getConditions_shouldReturnCONDITIONS() {
		assertEquals(CONDITIONS, rideInformation.getConditions());
	}
	
	@Test
	void test_matchesFilter_givenFROMCITY_shouldReturnTrue() {
		assertTrue(rideInformation.matchesFilter(FROM_CITY, "", ""));
	}
	
	@Test
	void test_matchesFilter_givenFROMZIP_shouldReturnTrue() {
		assertTrue(rideInformation.matchesFilter(FROM_ZIP, "", ""));
	}
	
	@Test
	void test_matchesFilter_givenNotUsedFromKey_shouldReturnFalse() {
		assertFalse(rideInformation.matchesFilter("blah", "", ""));
	}
	
	@Test
	void test_matchesFilter_givenTOCITY_shouldReturnTrue() {
		assertTrue(rideInformation.matchesFilter("", TO_CITY, ""));
	}
	
	@Test
	void test_matchesFilter_givenTOZIP_shouldReturnTrue() {
		assertTrue(rideInformation.matchesFilter("", TO_ZIP, ""));
	}
	
	@Test
	void test_matchesFilter_givenNotUsedToKey_shouldReturnFalse() {
		assertFalse(rideInformation.matchesFilter("", "blah", ""));
	}
	
	@Test
	void test_matchesFilter_givenDATE_shouldReturnTrue() {
		assertTrue(rideInformation.matchesFilter("", "", DATE));
	}
	
	@Test
	void test_matchesFilter_givenNotUsedDateKey_shouldReturnFalse() {
		assertFalse(rideInformation.matchesFilter("", "", "blah"));
	}
	
	@Test
	void test_matchesFilter_givenEmptyStrings_shouldReturnTrue() {
		assertTrue(rideInformation.matchesFilter("", "", ""));
	}	
	@Test 
	void test_isNill_shouldReturnFalse() {
		assertFalse(rideInformation.isNill());
	}
}
