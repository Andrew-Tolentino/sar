package edu.iit.hawk.atolentino.account_management.interactors;

import java.util.Calendar;
import java.util.Iterator;

import edu.iit.hawk.atolentino.account_management.boundaries.AccountUtilities;
import edu.iit.hawk.atolentino.rate_management.entities.Rate;
import edu.iit.hawk.atolentino.rate_management.interactors.RateManager;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.ProfileInformation;
import edu.iit.hawk.atolentino.response_generator.boundaries.DateFormatter;

public class Account implements AccountUtilities {
    private final int aid;
    private ProfileInformation profileInformation;
    private final Calendar dateCreated;
    private RateManagerUtilities driverRateManager;
    private RateManagerUtilities riderRateManager;
        
    public Account(int aid, ProfileInformation profileInformation) {
        this.aid = aid;
        this.profileInformation = profileInformation;
        dateCreated = Calendar.getInstance();
        driverRateManager = new RateManager();
        riderRateManager = new RateManager();
    }

	@Override
	public int getAid() {
    	return aid;
	}

	@Override
	public int addDriverRating(RequestInformation rateInformation, String raterFirstName, String date) {
    	return driverRateManager.addRating(rateInformation, raterFirstName, date);
	}

	@Override
	public int addRiderRating(RequestInformation rateInformation, String raterFirstName, String date) {
    	return riderRateManager.addRating(rateInformation, raterFirstName, date);
	}

	@Override
	public int getDriverRideParticipation() {
    	return driverRateManager.getNumberOfRides();
	}

	@Override
	public int getRiderRideParticipation() {
    	return riderRateManager.getNumberOfRides();
	}

	@Override
	public int getNumberOfDriverRatings() {
    	return driverRateManager.getNumberOfRatings();
	}

	@Override
	public int getNumberOfRiderRatings() {
    	return riderRateManager.getNumberOfRatings();
	}

	@Override
	public Double getDriverAverageRating() {
    	return driverRateManager.getAverageRating();
	}

	@Override
	public Double getRiderAverageRating() {
    	return riderRateManager.getAverageRating();
	}

	@Override
	public Iterator<Rate> getDriverRatingIterator() {
    	return driverRateManager.getRatingIterator();
	}

	@Override
	public Iterator<Rate> getRiderRatingIterator() {
    	return riderRateManager.getRatingIterator();
	}

	@Override
	public String getFirstName() {
    	return profileInformation.getFirstName();
	}

	@Override
	public String getLastName() {
    	return profileInformation.getLastName();
	}

	@Override
	public String getPhoneNumber() {
    	return profileInformation.getPhoneNumber();
	}

	@Override
	public String getProfilePicture() {
    	return profileInformation.getProfilePicture();
	}

	@Override
	public String getDateCreated() {
    	return DateFormatter.getDefaultFormat(dateCreated);
	}

	@Override
	public boolean isActive() {
        return profileInformation.isActive();
	}

	@Override
	public boolean matchesFilter(String key) {
    	return profileInformation.matchesFilter(key);
	}

	@Override
	public void updateAccount(ProfileInformation profileInformation) {
    	this.profileInformation = profileInformation;		
	}

	@Override
	public void incrementDriverRideParticipation() {
    	driverRateManager.incrementNumberOfRides();		
	}

	@Override
	public void incrementRiderRideParticipation() {
    	riderRateManager.incrementNumberOfRides();
	}
}