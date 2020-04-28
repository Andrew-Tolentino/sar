package edu.iit.hawk.atolentino.account_management.interactors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import edu.iit.hawk.atolentino.account_management.boundaries.AccountManagerBoundary;
import edu.iit.hawk.atolentino.account_management.boundaries.AccountUtilities;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.ProfileInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.RateInformation;

public class AccountManager implements AccountManagerBoundary {
	private static HashMap<Integer, AccountUtilities> accounts = new HashMap<>();
	private static int counter = 1;

	@Override
	public int addAccount(RequestInformation profileInformation) {
		AccountUtilities newAccount = new Account(counter, (ProfileInformation) profileInformation);
		accounts.put(counter, newAccount);
		counter++;
		return newAccount.getAid();
	}

	@Override
	public void updateAccount(int aid, RequestInformation profileInformation) {
		AccountUtilities accountToUpdate = accounts.get(aid);
		accountToUpdate.updateAccount((ProfileInformation) profileInformation);
	}

	@Override
	public Iterator<AccountUtilities> getAccounts(String key) {
		if (key.isEmpty()) {
			return Collections.unmodifiableCollection(accounts.values()).iterator(); 
		}
		return getAccountsThatMatchFilter(key); 
	}
	
	private Iterator<AccountUtilities> getAccountsThatMatchFilter(String key) {
		List<AccountUtilities> filteredAccounts = new ArrayList<AccountUtilities>();
		Iterator<AccountUtilities> iterator = accounts.values().iterator();
		while(iterator.hasNext()) {
			AccountUtilities account = iterator.next();
			if(account.matchesFilter(key)) {
				filteredAccounts.add(account);
			}
		}
		
		return Collections.unmodifiableList(filteredAccounts).iterator();
	}

	@Override
	public AccountUtilities getAccount(int aid) {
		return accounts.get(aid);
	}

	@Override
	public boolean doesAccountExist(int aid) {
		return accounts.containsKey(aid);
	}

	@Override
	public boolean isAccountActive(int aid) {
		if (doesAccountExist(aid)) {
			AccountUtilities account = accounts.get(aid);
			return account.isActive();	
		}
		return false;
	}
	
	@Override
	public int rateDriver(int aid, String date, RequestInformation rateInformation) {
		int raterId = rateInformation.getAid();
		AccountUtilities rater = accounts.get(raterId);
		String raterFirstName = rater.getFirstName();
		AccountUtilities accountToRate = accounts.get(aid);
		return accountToRate.addDriverRating((RateInformation) rateInformation, raterFirstName, date);
	}
	
	@Override
	public int rateRider(int aid, String date, RequestInformation rateInformation) {
		int raterId = rateInformation.getAid();
		AccountUtilities rater = accounts.get(raterId);
		String raterFirstName = rater.getFirstName();
		AccountUtilities accountToRate = accounts.get(aid);
		return accountToRate.addRiderRating((RateInformation) rateInformation, raterFirstName, date);
	}

	@Override
	public void incrementDriverRideParticipation(int aid) {
		AccountUtilities account = accounts.get(aid);
		account.incrementDriverRideParticipation();
		
	}

	@Override
	public void incrementRiderRideParticipation(int aid) {
		AccountUtilities account = accounts.get(aid);
		account.incrementRiderRideParticipation();		
	}

	@Override
	public boolean isPhoneNumberInUse(RequestInformation profileInformation) {
		Iterator<AccountUtilities> iterator = accounts.values().iterator();
		while(iterator.hasNext()) {
			AccountUtilities account = iterator.next();
			String phoneNumber = ((ProfileInformation) profileInformation).getPhoneNumber();
			if (account.getPhoneNumber().equals(phoneNumber)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void deleteAccount(int aid) {
		accounts.remove(aid);
	}	
}