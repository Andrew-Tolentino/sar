package edu.iit.hawk.atolentino.account_management.boundaries;

import java.util.Iterator;

import edu.iit.hawk.atolentino.rate_management.entities.Rate;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.ProfileInformation;

public interface AccountUtilities {	
	public int getAid();

	public int addDriverRating(RequestInformation rateInformation, String raterFirstName, String date);
	
	public int addRiderRating(RequestInformation rateInformation, String raterFirstName, String date);
	
	public int getDriverRideParticipation();
	
	public int getRiderRideParticipation();

	public int getNumberOfDriverRatings();
	
	public int getNumberOfRiderRatings();
	
	public Double getDriverAverageRating();
	
	public Double getRiderAverageRating();
	
	public Iterator<Rate> getDriverRatingIterator();
	
	public Iterator<Rate> getRiderRatingIterator();
	
	public String getFirstName();
	
	public String getLastName();
	
	public String getPhoneNumber();
	
	public String getProfilePicture();
	
	public String getDateCreated();
	
	public boolean isActive();
	
	public boolean matchesFilter(String key);
	
	public void updateAccount(ProfileInformation profileInformation);
	
	public void incrementDriverRideParticipation();
	
	public void incrementRiderRideParticipation();
	

	
}