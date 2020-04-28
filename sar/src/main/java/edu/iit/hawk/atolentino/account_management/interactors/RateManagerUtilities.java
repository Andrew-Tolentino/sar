package edu.iit.hawk.atolentino.account_management.interactors;

import java.util.Iterator;

import edu.iit.hawk.atolentino.rate_management.entities.Rate;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;

public interface RateManagerUtilities {
	public int addRating(RequestInformation rateInformation, String raterFirstName, String date);
	
	public Double getAverageRating();
	
	public void incrementNumberOfRides();
	
	public int getNumberOfRides();
	
	public int getNumberOfRatings();
	
	public Iterator<Rate> getRatingIterator();
}
