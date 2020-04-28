package edu.iit.hawk.atolentino.account_management;

import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.account_management.interactors.Account;
import edu.iit.hawk.atolentino.rate_management.entities.Rate;
import edu.iit.hawk.atolentino.request_information_processing.entities.ProfileInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.RateInformation;
import edu.iit.hawk.atolentino.response_generator.boundaries.DateFormatter;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

public class AccountTest {
	private static final int AID = 1;
	private static final String FIRST_NAME = "Andrew";
	private static final String LAST_NAME = "Tolentino";
	private static final String PHONE_NUMBER = "773-226-5515";
	private static final String PROFILE_PICTURE = "Profile Image";
	private static final boolean NOT_ACTIVE = false;
	private Account account;
	
	
	@BeforeEach
	void init() {
		ProfileInformation profileInformation = new ProfileInformation(FIRST_NAME, LAST_NAME, PHONE_NUMBER, PROFILE_PICTURE, NOT_ACTIVE);
		account = new Account(AID, profileInformation);
	}
	
	@Nested
	class DefaultAccountTest {
		@Test
		void test_getDriverRideParticipation_default_isZero() {
			assertEquals(0, account.getDriverRideParticipation());
		}
		
		@Test
		void test_getRiderRideParticipation_default_isZero() {
			assertEquals(0, account.getRiderRideParticipation());
		}
		
		@Test
		void test_getNumberOfDriverRatings_default_isZero() {
			assertEquals(0, account.getNumberOfDriverRatings());
		}
		
		@Test
		void test_getNumberOfRiderRatings_default_isZero() {
			assertEquals(0, account.getNumberOfRiderRatings());
		}
		
		@Test
		void test_getDriverAverageRating_default_isNull() {
			assertNull(account.getDriverAverageRating());
		}
		
		@Test
		void test_getRiderAverageRating_default_isNull() {
			assertNull(account.getRiderAverageRating());
		}
		
		@Test
		void test_getDriverRatingIterator_default_isEmpty() {
			Iterator<Rate> iterator = account.getDriverRatingIterator();
			assertFalse(iterator.hasNext());
		}
		
		@Test
		void test_getRiderRatingIterator_default_isEmpty() {
			Iterator<Rate> iterator = account.getRiderRatingIterator();
			assertFalse(iterator.hasNext());
		}
	}
	
	@Nested
	class AccountRatingTest {
		private RateInformation rateInformation;
		
		private static final String RATER_FIRST_NAME = "Raymond";
		private static final String DATE = "13-Apr-2020";		
		private static final int RID = 1;
		private static final int SENT_BY_ID = 2;
		private static final int RATING = 3;
		private static final String COMMENT = "Was not a bother during the ride";
		
		private boolean doesRateMatchWithFixtures(Rate rate) {
			return (
					RATER_FIRST_NAME.equals(rate.getRaterFistName()) &&
					DATE.equals(rate.getDate()) &&
					RID == rate.getRid() &&
					SENT_BY_ID == rate.getRaterId() &&
					RATING == rate.getRating() &&
					COMMENT.equals(rate.getComment())
					);
		}

		@BeforeEach
		void init() {
			rateInformation = new RateInformation(RID, SENT_BY_ID, RATING, COMMENT);
		}
		
		@Test
		void test_addDriverRating_rateDriver_numberOfDriverRatingsShouldBeOne() {
			account.addDriverRating(rateInformation, RATER_FIRST_NAME, DATE);
			assertEquals(1, account.getNumberOfDriverRatings());
		}
		
		@Test
		void test_addRiderRating_rateRider_numberOfRiderRatingsShouldBeOne() {
			account.addRiderRating(rateInformation, RATER_FIRST_NAME, DATE);
			assertEquals(1, account.getNumberOfRiderRatings());
		}
		
		@Test
		void test_getDriverAverageRating_afterRatingDriver_averageDriverRatingShouldBeRATING() {
			account.addDriverRating(rateInformation, RATER_FIRST_NAME, DATE);
			assertEquals(RATING, (double)account.getDriverAverageRating());;
		}
		
		@Test
		void test_getRiderAverageRating_afterRatingRider_averageRiderRatingShouldBeRATING() {
			account.addRiderRating(rateInformation, RATER_FIRST_NAME, DATE);
			assertEquals(RATING, (double)account.getRiderAverageRating());
		}
		
		@Test
		void test_getDriverRatingIterator_afterRatingDriver_containsMatchingFixtureRateInformation() {
			account.addDriverRating(rateInformation, RATER_FIRST_NAME, DATE);
			Iterator<Rate> iterator = account.getDriverRatingIterator();
			Rate rate = null;
			while(iterator.hasNext()) {
				rate = iterator.next();
			}
			assertTrue(doesRateMatchWithFixtures(rate));
		}
		
		@Test
		void test_getRiderRatingIterator_afterRatingRider_containsMatchingFixtureRateInformation() {
			account.addRiderRating(rateInformation, RATER_FIRST_NAME, DATE);
			Iterator<Rate> iterator = account.getRiderRatingIterator();
			Rate rate = null;
			while(iterator.hasNext()) {
				rate = iterator.next();
			}
			assertTrue(doesRateMatchWithFixtures(rate));
		}	
	}
	
	@Test
	void test_getAid_isAID() {
		assertEquals(AID, account.getAid());
	}
	
	@Test
	void test_getFirstName_isFIRSTNAME() {
		assertEquals(FIRST_NAME, account.getFirstName());
	}
	
	@Test
	void test_getLastName_isLASTNAME() {
		assertEquals(LAST_NAME, account.getLastName());
	}
	
	@Test
	void test_getPhoneNumber_isPHONENUMBER() {
		assertEquals(PHONE_NUMBER, account.getPhoneNumber());
	}
	
	@Test
	void test_getProfilePicture_isPROFILEPICTURE() {
		assertEquals(PROFILE_PICTURE, account.getProfilePicture());
	}
	
	@Test
	void test_getDateCreated_hasProperFormat() {
		DateFormat dateFormat = DateFormatter.CREATED_DEFAULT_FORMAT;
		assertDoesNotThrow(() -> {
			dateFormat.parse(account.getDateCreated());
		});	
	}
	
	@Test
	void test_isActive_isNOTACTIVE() {
		assertFalse(account.isActive());
	}
	
	@Test
	void test_matchesFilter_givenFIRSTNAME_isTrue() {
		assertTrue(account.matchesFilter(FIRST_NAME));
	}
	
	@Test
	void test_matchesFilter_givenLASTNAME_isTrue() {
		assertTrue(account.matchesFilter(LAST_NAME));
	}
	
	@Test
	void test_matchesFilter_givenPHONENUMBER_isTrue() {
		assertTrue(account.matchesFilter(PHONE_NUMBER));
	}
	
	@Test
	void test_matchesFilter_givenNonMatchingData_isFalse() {
		String blah = "blah";
		assertFalse(account.matchesFilter(blah));
	}
	
	@Test
	void test_updateAccount_setActiveToTrue_activatesAccount() {
		ProfileInformation profileInformation = new ProfileInformation(FIRST_NAME, LAST_NAME, PHONE_NUMBER, PROFILE_PICTURE, true);
		account.updateAccount(profileInformation);
		assertTrue(account.isActive());
	}
	
	@Test
	void test_incrementDriverRideParticipation_increasesDriverRideParticipationByOne() {
		account.incrementDriverRideParticipation();
		assertEquals(1, account.getDriverRideParticipation());
	}
	
	@Test
	void test_incrementRiderRideParticipation_increasesRiderRideParticipationByOne() {
		account.incrementRiderRideParticipation();
		assertEquals(1, account.getRiderRideParticipation());
	}

}
