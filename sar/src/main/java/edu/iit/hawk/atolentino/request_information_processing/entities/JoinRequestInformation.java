package edu.iit.hawk.atolentino.request_information_processing.entities;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;

public class JoinRequestInformation extends RequestInformation {
	private final Integer aid;
	private final Integer passengers;
	private Boolean rideConfirmed;
	private Boolean pickUpConfirmed;
	
	public JoinRequestInformation(Integer aid, Integer passengers, Boolean rideConfirmed, Boolean pickUpConfirmed) {
		this.aid = aid;
		this.passengers = passengers;
		this.rideConfirmed = rideConfirmed;
		this.pickUpConfirmed = pickUpConfirmed;
	}
	
	@Override
	public Integer getAid() {
		return aid;
	}
	
	public Integer getNumberOfPassengers() {
		if (passengers == null) {
			return null;
		}
		return passengers;
	}
	
	public Boolean getRideConfirmed() {
		return rideConfirmed;
	}
	
	public void setRideConfirmed(Boolean status) {
		this.rideConfirmed = status;
	}
	
	public Boolean getPickUpConfirmed() {
		return pickUpConfirmed;
	}
	
	public void setPickUpConfirmed() {
		this.pickUpConfirmed = true;
	}

	@Override
	public boolean isNill() {
		return false;
	}

}
