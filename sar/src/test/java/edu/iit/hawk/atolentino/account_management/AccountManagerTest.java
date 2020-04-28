package edu.iit.hawk.atolentino.account_management;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

import edu.iit.hawk.atolentino.account_management.boundaries.AccountUtilities;
import edu.iit.hawk.atolentino.account_management.interactors.AccountManager;
import edu.iit.hawk.atolentino.rate_management.entities.Rate;
import edu.iit.hawk.atolentino.request_information_processing.entities.ProfileInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.RateInformation;

public class AccountManagerTest {
	private ProfileInformation profileInformationFixture;
	private AccountManager accountManager;
	private int aid;
	private static final String FIRST_NAME = "Andrew";
	private static final String LAST_NAME = "Tolentino";
	private static final String PHONE_NUMBER = "773-226-5515";
	private static final String PROFILE_PICTURE = "Profile Image";
	private static final boolean NOT_ACTIVE = false;
	
	private boolean doesAccountMatchContainProfileInformation(AccountUtilities account, ProfileInformation profileInformation) {
		return (
				account.getFirstName().equals(profileInformation.getFirstName()) &&
				account.getLastName().equals(profileInformation.getLastName()) &&
				account.getPhoneNumber().equals(profileInformation.getPhoneNumber()) &&
				account.getProfilePicture().equals(profileInformation.getProfilePicture()) && 
				account.isActive() == profileInformation.isActive()
				);
	}
	
	@BeforeEach
	void init() {
		profileInformationFixture = new ProfileInformation(FIRST_NAME, LAST_NAME, PHONE_NUMBER, PROFILE_PICTURE, NOT_ACTIVE);
		accountManager = new AccountManager();
		aid = accountManager.addAccount(profileInformationFixture);
	}
	
	@AfterEach
	void clean() {
		accountManager.deleteAccount(aid);
	}
	
	@Test
	void test_addAccount_withProfileInformationFixture_matchesAccountAdded() {
		AccountUtilities account = accountManager.getAccount(aid);
		assertTrue(doesAccountMatchContainProfileInformation(account, profileInformationFixture));
	}
	
	@Test
	void test_updateAccount_withActiveSetToTrue_updatedAccountShouldBeActive() {
		ProfileInformation profileInformation = new ProfileInformation(FIRST_NAME, LAST_NAME, PHONE_NUMBER, PROFILE_PICTURE, true);
		accountManager.updateAccount(aid, profileInformation);
		assertTrue(accountManager.isAccountActive(aid));
	}
	
	@Test
	void test_getAccounts_withEmptyStringForKey_shouldReturnIteratorContainingAid() {
		Iterator<AccountUtilities> iterator = accountManager.getAccounts("");
		AccountUtilities currentAccount = null;
		while(iterator.hasNext()) {
			currentAccount = iterator.next();
		}
		assertEquals(aid, currentAccount.getAid());
	}
	
	@Test
	void test_getAccounts_withFIRSTNAME_shouldReturnIteratorContainingAid() {
		Iterator<AccountUtilities> iterator = accountManager.getAccounts(FIRST_NAME);
		AccountUtilities account = null;
		while(iterator.hasNext()) {
			account = iterator.next();
		}
		assertEquals(aid, account.getAid());
	}
	
	@Test
	void test_getAccounts_withLASTNAME_shouldReturnIteratorContainingAid() {
		Iterator<AccountUtilities> iterator = accountManager.getAccounts(LAST_NAME);
		AccountUtilities account = null;
		while(iterator.hasNext()) {
			account = iterator.next();
		}
		assertEquals(aid, account.getAid());
	}
	
	@Test
	void test_getAccounts_withPHONENUMBER_shouldReturnIteratorContainingAid() {
		Iterator<AccountUtilities> iterator = accountManager.getAccounts(PHONE_NUMBER);
		AccountUtilities account = null;
		while(iterator.hasNext()) {
			account = iterator.next();
		}
		assertEquals(aid, account.getAid());
	}
	
	@Test
	void test_getAccounts_withKeyContainingNoDataAccountsHave_shouldReturnEmptyIterator() {
		Iterator<AccountUtilities> iterator = accountManager.getAccounts("blah");
		assertFalse(iterator.hasNext());
	}
	
	@Test
	void test_doesAccountExist_givenAccountAddedInInit_shouldReturnTrue() {
		assertTrue(accountManager.doesAccountExist(aid));
	}
	
	@Test
	void test_doesAccountExist_givenAidNotInAccountManager_shouldReturnFalse() {
		assertFalse(accountManager.doesAccountExist(-1));
	}
	
	@Test
	void test_isAccountActive_afterAddingAccount_shouldReturnFalse() {
		assertFalse(accountManager.isAccountActive(aid));
	}
	
	@Test
	void test_isPhoneNumberInUse_givenProfileInformationFixture_shouldReturnTrue() {
		assertTrue(accountManager.isPhoneNumberInUse(profileInformationFixture));
	}
	
	@Test
	void test_isPhoneNumberInUse_givenProfileInformationWithDifferentPhoneNumber_shouldReturnFalse() {
		ProfileInformation profileInformation = new ProfileInformation(
				FIRST_NAME, LAST_NAME, "773-226-5460", PROFILE_PICTURE, NOT_ACTIVE);
		assertFalse(accountManager.isPhoneNumberInUse(profileInformation));
	}
	
	@Nested
	class AccountManagerRatingsTest {
		private int raterAid;
		private RateInformation rateInformation;
		private static final String DATE = "13-Apr-2020";		
		private static final int RID = 1;
		private static final int RATING = 3;
		private static final String COMMENT = "Was not a bother during the ride";
				
		@BeforeEach
		void init() {
			raterAid = accountManager.addAccount(profileInformationFixture);
			rateInformation = new RateInformation(RID, raterAid, RATING, COMMENT);
		}
		
		@AfterEach
		void clean() {
			accountManager.deleteAccount(raterAid);
		}
		
		@Test
		void test_rateDriver_producesRatingToAccount() {
			accountManager.rateDriver(aid, DATE, rateInformation);
			AccountUtilities account = accountManager.getAccount(aid);
			Iterator<Rate> iterator = account.getDriverRatingIterator();
			Rate rate = null;
			while(iterator.hasNext()) {
				rate = iterator.next();
			}
			assertEquals(rate.getRaterId(), raterAid);
		}
		
		@Test
		void test_rateRider_producesRatingToAccount() {
			accountManager.rateRider(aid, DATE, rateInformation);
			AccountUtilities account = accountManager.getAccount(aid);
			Iterator<Rate> iterator = account.getRiderRatingIterator();
			Rate rate = null;
			while(iterator.hasNext()) {
				rate = iterator.next();
			}
			assertEquals(rate.getRaterId(), raterAid);
		}
	}
	
	@Test
	void test_incrementDriverRideParticipation_increasesDriverRideParticipationByOne() {
		accountManager.incrementDriverRideParticipation(aid);
		AccountUtilities account = accountManager.getAccount(aid);
		assertEquals(1, account.getDriverRideParticipation());
	}
	
	@Test
	void test_incrementRiderRideParticipation_increasesRiderRideParticipationByOne() {
		accountManager.incrementRiderRideParticipation(aid);
		AccountUtilities account = accountManager.getAccount(aid);
		assertEquals(1, account.getRiderRideParticipation());
	}
}
