package edu.iit.hawk.atolentino.ride_management.interactors;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import edu.iit.hawk.atolentino.message_management.entities.Message;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.RideInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.UpdateJoinRequestInformation;
import edu.iit.hawk.atolentino.response_generator.boundaries.DateFormatter;
import edu.iit.hawk.atolentino.ride_management.boundaries.RideManagerBoundary;
import edu.iit.hawk.atolentino.ride_management.boundaries.RideUtilities;

public class RideManager implements RideManagerBoundary {
	private static HashMap<Integer, RideUtilities> rides = new HashMap<>();
	private static int counter = 1;

	@Override
	public int addRide(RequestInformation rideInformation) {
		Ride newRide = new Ride(counter, (RideInformation) rideInformation);
		rides.put(counter, newRide);
		counter++;
		return newRide.getRid();
	}

	@Override
	public void updateRide(int rid, RequestInformation rideInformation) {
		RideUtilities ride = rides.get(rid);
		ride.updateRide((RideInformation) rideInformation);
	}

	@Override
	public void deleteRide(int rid) {
		rides.remove(rid);
	}

	@Override
	public Iterator<RideUtilities> searchRide(String fromKey, String toKey, String dateKey) {
		if (fromKey.isEmpty() && toKey.isEmpty() && dateKey.isEmpty()) {
			return Collections.unmodifiableCollection(rides.values()).iterator();
		}
		return getRidesThatMatchFilter(fromKey, toKey, dateKey);
	}
	
	private Iterator<RideUtilities> getRidesThatMatchFilter(String fromKey, String toKey, String dateKey) {
		List<RideUtilities> filteredRides = new ArrayList<RideUtilities>();
		Iterator<RideUtilities> iterator = rides.values().iterator();
		while(iterator.hasNext()) {
			RideUtilities ride = iterator.next();
			if (ride.matchesFilter(fromKey, toKey, dateKey)) {
				filteredRides.add(ride);
			}
		}
		return Collections.unmodifiableList(filteredRides).iterator();
	}
	
	@Override
	public boolean doesRideExist(int rid) {
		return rides.containsKey(rid);
	}

	@Override
	public int addJoinRequest(int rid, RequestInformation joinRequestInformation) {
		RideUtilities ride = rides.get(rid);
		return ride.addJoinRequest(joinRequestInformation);
	}

	@Override
	public int addMessage(int rid, RequestInformation messageInformation) {
		RideUtilities ride = rides.get(rid);
		return ride.addMessage(messageInformation);
	}

	@Override
	public Iterator<Message> getMessages(int rid) {
		RideUtilities ride = rides.get(rid);
		return ride.getMessages();
	}

	@Override
	public boolean isAccountDriverOfRide(int rid, int aid) {
		RideUtilities ride = rides.get(rid);
		return ride.isDriver(aid);
	}

	@Override
	public boolean isAccountRiderOfRide(int rid, int aid) {
		RideUtilities ride = rides.get(rid);
		return ride.isRider(aid);
	}

	@Override
	public RideUtilities getRide(int rid) {
		return rides.get(rid);
	}

	@Override
	public int getJoinRequestCreatorId(int rid, int jid) {
		RideUtilities ride = rides.get(rid);
		return ride.getJoinRequestCreator(jid);
	}

	@Override
	public boolean sentJoinRequestToRide(int rid, int aid) {
		RideUtilities ride = rides.get(rid);
		return ride.sentJoinRequestToRide(aid);
	}

	@Override
	public boolean doesJoinRequestExist(int rid, int jid) {
		RideUtilities ride = rides.get(rid);
		return ride.doesJoinRequestExist(jid);
	}

	@Override
	public void updateRideConfirmed(int rid, int jid, RequestInformation updateJoinRequestInformation) {
		RideUtilities ride = rides.get(rid);
		boolean status = ((UpdateJoinRequestInformation) updateJoinRequestInformation).getStatus();
		ride.setRideConfirmed(jid, status);
	}

	@Override
	public void updatePickUpConfirmed(int rid, int jid) {
		RideUtilities ride = rides.get(rid);
		ride.setPickUpConfirmed(jid);
	}

	@Override
	public String getRideDate(int rid) {
		RideUtilities ride = rides.get(rid);
		return ride.getDate();
	}

	@Override
	public Iterator<RideUtilities> getRidesBetweenStartDateAndEndDate(String startDate, String endDate) throws ParseException {
		if (startDate.isEmpty() && endDate.isEmpty()) {
			return Collections.unmodifiableCollection(rides.values()).iterator();
		}		
		
		long startDateTimeStamp = DateFormatter.getTimeStampFromDayMonthYear(startDate);
		long endDateTimeStamp = DateFormatter.getTimeStampFromDayMonthYear(endDate);
		return findRidesBetweenStartDateAndEndDate(
				startDateTimeStamp == -1 ? Long.MIN_VALUE : startDateTimeStamp, 
				endDateTimeStamp == -1 ? Long.MAX_VALUE : endDateTimeStamp
				);
	}
	
	private Iterator<RideUtilities> findRidesBetweenStartDateAndEndDate(long startDate, long endDate) { 
		List<RideUtilities> filteredRides = new ArrayList<RideUtilities>();
		Iterator<RideUtilities> iterator = rides.values().iterator();
		SimpleDateFormat formatter = DateFormatter.CREATED_DAY_MONTH_YEAR_FORMAT;
		while (iterator.hasNext()) {
			RideUtilities ride = iterator.next();
			String dateStr = ride.getDate();
			try {
				Date date = formatter.parse(dateStr);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				long rideTimeStamp = calendar.getTimeInMillis()/ 1000;
				if (startDate <= rideTimeStamp && rideTimeStamp <= endDate) {
					filteredRides.add(ride);
				}
			} catch (ParseException ex) {
				ex.printStackTrace();
			}	
		}
		
		return Collections.unmodifiableList(filteredRides).iterator();
	}
}
