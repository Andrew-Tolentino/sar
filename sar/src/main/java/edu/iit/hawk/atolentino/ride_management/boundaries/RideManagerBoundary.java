package edu.iit.hawk.atolentino.ride_management.boundaries;

import java.text.ParseException;
import java.util.Iterator;

import edu.iit.hawk.atolentino.message_management.entities.Message;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;

public interface RideManagerBoundary {
	public int addRide(RequestInformation rideInformation);
	
	public void updateRide(int rid, RequestInformation rideInformation);
	
	public void deleteRide(int rid);
	
	public Iterator<RideUtilities> searchRide(String fromKey, String toKey, String dateKey);
	
	public int addJoinRequest(int rid, RequestInformation joinRequestInformation);
	
	public void updateRideConfirmed(int rid, int jid, RequestInformation updateJoinRequestInformation);
	
	public void updatePickUpConfirmed(int rid, int jid);

	public int addMessage(int rid, RequestInformation messageInformation);
	
	public Iterator<Message> getMessages(int rid);
		
	public boolean isAccountDriverOfRide(int rid, int aid);
	
	public boolean isAccountRiderOfRide(int rid, int aid);
	
	public RideUtilities getRide(int rid);
	
	public int getJoinRequestCreatorId(int rid, int jid);
	
	public boolean doesRideExist(int rid);
	
	public boolean doesJoinRequestExist(int rid, int jid);
	
	public boolean sentJoinRequestToRide(int rid, int aid);
	
	public String getRideDate(int rid);
		
	public Iterator<RideUtilities> getRidesBetweenStartDateAndEndDate(String startDate, String endDate) throws ParseException;
}
