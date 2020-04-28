package edu.iit.hawk.atolentino.rate_management;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.rate_management.entities.Rate;
import edu.iit.hawk.atolentino.rate_management.interactors.RateManager;
import edu.iit.hawk.atolentino.request_information_processing.entities.RateInformation;

public class RateManagerTest {
	private RateManager rateManager;
	
	@BeforeEach
	void init() {
		rateManager = new RateManager();
	}
	
	@Test
	void test_addRating_shouldBeFoundInRatingIterator() {
		String RATER_FIRST_NAME = "Raymond";
		String DATE = "13-Apr-2020";
		int RID = 1;
		int SENT_BY_ID = 2;
		int RATING = 3;
		String COMMENT = "Was not a bother during the ride";
		RateInformation rateInformation = new RateInformation(RID, SENT_BY_ID, RATING, COMMENT);
		int sid = rateManager.addRating(rateInformation, RATER_FIRST_NAME, DATE);
		Iterator<Rate> iterator = rateManager.getRatingIterator();
		Rate rate = null;
		while(iterator.hasNext()) {
			rate = iterator.next();
		}
		assertEquals(sid, rate.getSid());
	}
	
	@Test
	void test_getAverageRating_default_shouldReturnNull() {
		assertNull(rateManager.getAverageRating());
	}
	
	@Test 
	void test_addRating_afterRatingTwice_shouldReturnExpectedAverage() {
		int RATE_VALUE_ONE = 1;
		int RATE_VALUE_TWO = 2;
		
		String RATER_FIRST_NAME = "Raymond";
		String DATE = "13-Apr-2020";
		int RID = 1;
		int SENT_BY_ID = 2;
		String COMMENT = "Was not a bother during the ride";
		RateInformation rateInformationOne = new RateInformation(RID, SENT_BY_ID, RATE_VALUE_ONE, COMMENT);
		RateInformation rateInformationTwo = new RateInformation(RID, SENT_BY_ID, RATE_VALUE_TWO, COMMENT);
		rateManager.addRating(rateInformationOne, RATER_FIRST_NAME, DATE);
		rateManager.addRating(rateInformationTwo, RATER_FIRST_NAME, DATE);
		
		assertEquals((RATE_VALUE_ONE + RATE_VALUE_TWO) / 2.0, rateManager.getAverageRating());
	}
	
	@Test
	void test_getAverageRating_afterAddingARate_shouldRetturnRATING() {
		String RATER_FIRST_NAME = "Raymond";
		String DATE = "13-Apr-2020";
		int RID = 1;
		int SENT_BY_ID = 2;
		int RATING = 3;
		String COMMENT = "Was not a bother during the ride";
		RateInformation rateInformation = new RateInformation(RID, SENT_BY_ID, RATING, COMMENT);
		rateManager.addRating(rateInformation, RATER_FIRST_NAME, DATE);
		Double RATING_TO_DOUBLE = (double) RATING;
		assertEquals(RATING_TO_DOUBLE, rateManager.getAverageRating());
	}
	
	@Test
	void test_incrementNumberOfRides_numberOfRidesShouldReturnOne() {
		rateManager.incrementNumberOfRides();
		assertEquals(1, rateManager.getNumberOfRides());
	}
	
	@Test
	void test_getNumberOfRides_default_shouldReturnZero() {
		assertEquals(0, rateManager.getNumberOfRides());
	}
	
	@Test
	void test_getNumberOfRatings_default_shouldReturnZero() {
		assertEquals(0, rateManager.getNumberOfRatings());
	}
	
	@Test
	void test_getRatingIterator_default_shouldBeEmpty() {
		assertFalse(rateManager.getRatingIterator().hasNext());
	}
	 
}
