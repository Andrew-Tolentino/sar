package edu.iit.hawk.atolentino.rate_management.interactors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import edu.iit.hawk.atolentino.account_management.interactors.RateManagerUtilities;
import edu.iit.hawk.atolentino.rate_management.entities.Rate;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.RateInformation;

public class RateManager implements RateManagerUtilities {
	private List<Rate> ratings = new ArrayList<Rate>();
	private static int counter = 0;
    private int numberOfRides = 0;
	private Double averageRating = null;
	
	@Override
	public int addRating(RequestInformation rateInformation, String raterFirstName, String date) {
		Rate newRate = new Rate(counter, (RateInformation) rateInformation, raterFirstName, date);
		ratings.add(newRate);
		updateAverageRating(newRate.getRating());
		counter++;
		return newRate.getSid();
	}
	
	private void updateAverageRating(int rating) {
		if (averageRating == null) {
			averageRating = (double) rating;
		}
		else {
			averageRating += rating;
			averageRating /= ratings.size();
		}
	}
	
	@Override
	public Double getAverageRating() {
		return averageRating;
	}
	
	@Override
	public void incrementNumberOfRides() {
		numberOfRides++;
	}
	
	@Override
	public int getNumberOfRides() {
		return numberOfRides;
	}
	
	@Override
	public int getNumberOfRatings() {
		return ratings.size();
	}
	
	@Override
	public Iterator<Rate> getRatingIterator() {
		return Collections.unmodifiableList(ratings).iterator();
	}
	
}
