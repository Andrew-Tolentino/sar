package edu.iit.hawk.atolentino.request_information_processing.request_information;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.request_information_processing.entities.RateInformation;

public class RateInformationTest {
	private RateInformation rateInformation;
	private static final Integer RID = 1;
	private static final Integer SENT_BY_ID = 2;
	private static final Integer RATING = 3;
	private static final String COMMENT = "Was not a bother during the ride";
	
	@BeforeEach
	void init() {
		rateInformation = new RateInformation(RID, SENT_BY_ID, RATING, COMMENT);
	}
	
	@Test
	void test_getRid_shouldReturnRID() {
		assertEquals(RID, rateInformation.getRid());
	}
	
	@Test
	void test_getAid_shouldReturnAID() {
		assertEquals(SENT_BY_ID, rateInformation.getAid());
	}
	
	@Test
	void test_getRating_shouldReturnRATING() {
		assertEquals(RATING, rateInformation.getRating());
	}
	
	@Test
	void test_getComment_shouldReturnCOMMENT() {
		assertEquals(COMMENT, rateInformation.getComment());
	}
	
	@Test
	void test_isNill_shouldReturnFalse() {
		assertFalse(rateInformation.isNill());
	}
}
