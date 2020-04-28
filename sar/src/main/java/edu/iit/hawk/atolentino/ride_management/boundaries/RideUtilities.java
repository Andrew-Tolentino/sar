package edu.iit.hawk.atolentino.ride_management.boundaries;

import java.util.Iterator;

import edu.iit.hawk.atolentino.message_management.entities.Message;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;

public interface RideUtilities {
	public int getRid();
	
	public int getAid();
	
	public String getFromCity();
	
	public String getFromZip();
	
	public String getToCity();
	
	public String getToZip();
	
	public String getDate();
	
	public String getTime();
	
	public String getMake();
	
	public String getModel();
	
	public String getColor();
	
	public String getPlateState();
	
	public String getPlateSerial();
	
	public int getMaxPassengers();
	
	public Double getAmountPerPassenger();
	
	public String getConditions();
	
	public int getJoinRequestCreator(int jid);
	
	public Iterator<Message> getMessages();
	
	public boolean matchesFilter(String fromKey, String toKey, String dateKey);
	
	public boolean isDriver(int aid);
	
	public boolean isRider(int aid);
	
	public boolean sentJoinRequestToRide(int aid);
	
	public boolean doesJoinRequestExist(int jid);
	
	public int addJoinRequest(RequestInformation joinRequestInformation);
		
	public int addMessage(RequestInformation messageInformation);
	
	public void setRideConfirmed(int jid, boolean status);
	
	public void setPickUpConfirmed(int jid);
	
	public void updateRide(RequestInformation joinRequestInformation);
}
