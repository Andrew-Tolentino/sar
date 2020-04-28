package edu.iit.hawk.atolentino.ride_management.interactors;

import edu.iit.hawk.atolentino.request_information_processing.entities.JoinRequestInformation;

public interface JoinRequestManagerUtilities {
	public int addJoinRequest(JoinRequestInformation joinRequestInformation);
	
	public int getAid(int jid);
	
	public void denyJoinRequest(int jid);
	
	public void acceptJoinRequest(int jid);
	
	public void setPickUpConfirmed(int jid);
	
	public boolean doesJoinRequestExist(int jid);
	
	public boolean sentJoinRequestToRide(int aid);
	
	public boolean isRider(int aid);
			
	
}
