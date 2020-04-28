package edu.iit.hawk.atolentino.account_management.boundaries;

import java.util.Iterator;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;

public interface AccountManagerBoundary {	
	public int addAccount(RequestInformation profileInformation);
		
	public void updateAccount(int aid, RequestInformation profileInformation);
	
	public Iterator<AccountUtilities> getAccounts(String key);
	
	public AccountUtilities getAccount(int aid);
	
	public boolean doesAccountExist(int aid);
	
	public boolean isAccountActive(int aid);
	
	public boolean isPhoneNumberInUse(RequestInformation profileInformation);
	
	public int rateDriver(int aid, String date, RequestInformation rateInformation);
		
	public int rateRider(int aid, String date, RequestInformation rateInformation);
	
	public void incrementDriverRideParticipation(int aid);
	
	public void incrementRiderRideParticipation(int aid);
	
	public void deleteAccount(int aid);
}
