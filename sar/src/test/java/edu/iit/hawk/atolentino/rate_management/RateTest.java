package edu.iit.hawk.atolentino.rate_management;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.rate_management.entities.Rate;
import edu.iit.hawk.atolentino.request_information_processing.entities.RateInformation;

public class RateTest {
	private Rate rate;
	
	private static final String RATER_FIRST_NAME = "Raymond";
	private static final String DATE = "13-Apr-2020";		
	private static final int RID = 1;
	private static final int SENT_BY_ID = 2;
	private static final int SID = 1;
	private static final int RATING = 3;
	private static final String COMMENT = "Was not a bother during the ride";
	
	@BeforeEach
	void init() {
		RateInformation rateInformation = new RateInformation(RID, SENT_BY_ID, RATING, COMMENT);
		rate = new Rate(SID, rateInformation, RATER_FIRST_NAME, DATE);
	}
	
	@Test
	void test_getSid_shouldReturnSID() {
		assertEquals(SID, rate.getSid());
	}
	
	@Test
	void test_getRid_shouldReturnRID() {
		assertEquals(RID, rate.getRid());
	}
	
	@Test
	void test_getRaterId_shouldReturnSENTBYID() {
		assertEquals(SENT_BY_ID, rate.getRaterId());
	}
	
	@Test
	void test_getRating_shouldReturnRATING() {
		assertEquals(RATING, rate.getRating());
	}
	
	@Test
	void test_getComment_shouldReturnCOMMENT() {
		assertEquals(COMMENT, rate.getComment());
	}
	
	@Test
	void test_getRaterFirstName_shouldReturnRATERFIRSTNAME() {
		assertEquals(RATER_FIRST_NAME, rate.getRaterFistName());
	}
	
	@Test
	void test_getDate_shouldReturnDATE() {
		assertEquals(DATE, rate.getDate());
	}

}
