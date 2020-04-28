package edu.iit.hawk.atolentino.ride_management.interactors;

public interface JoinRequestUtilities {
	public int getJid();
	
	public int getAid();
	
	public Boolean getRideConfirmed();
	
	public void setRideConfirmed(boolean status);
	
	public void setPickUpConfirmed();
}
