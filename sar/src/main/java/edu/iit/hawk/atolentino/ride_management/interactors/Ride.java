package edu.iit.hawk.atolentino.ride_management.interactors;

import java.util.Iterator;

import edu.iit.hawk.atolentino.join_request_management.JoinRequestManager;
import edu.iit.hawk.atolentino.message_management.entities.Message;
import edu.iit.hawk.atolentino.message_management.interactors.MessageManager;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.JoinRequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.MessageInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.RideInformation;
import edu.iit.hawk.atolentino.ride_management.boundaries.RideUtilities;

public class Ride implements RideUtilities {
	private final int rid;
	private RideInformation rideInformation;
	private MessageManagerUtilities messageManager;
	private JoinRequestManagerUtilities joinRequestManager;
	
	public Ride(int rid, RideInformation rideInformation) {
		this.rid = rid;
		this.rideInformation = rideInformation;
		messageManager = new MessageManager();
		joinRequestManager = new JoinRequestManager();
	}

	@Override
	public int getRid() {
		return rid;
	}

	@Override
	public int getAid() {
		return rideInformation.getAid();
	}

	@Override
	public String getFromCity() {
		return rideInformation.getFromCity();
	}

	@Override
	public String getFromZip() {
		return rideInformation.getFromZip();
	}

	@Override
	public String getToCity() {
		return rideInformation.getToCity();
	}

	@Override
	public String getToZip() {
		return rideInformation.getToZip();
	}

	@Override
	public String getDate() {
		return rideInformation.getDate();
	}

	@Override
	public String getTime() {
		return rideInformation.getTime();
	}

	@Override
	public String getMake() {
		return rideInformation.getMake();
	}

	@Override
	public String getModel() {
		return rideInformation.getModel();
	}

	@Override
	public String getColor() {
		return rideInformation.getColor();
	}

	@Override
	public String getPlateState() {
		return rideInformation.getPlateState();
	}

	@Override
	public String getPlateSerial() {
		return rideInformation.getPlateSerial();
	}
	
	@Override
	public int getMaxPassengers() {
		return rideInformation.getMaxPassengers();
	}

	@Override
	public Double getAmountPerPassenger() {
		return rideInformation.getAmountPerPassenger();
	}

	@Override
	public String getConditions() {
		return rideInformation.getConditions();
	}

	@Override
	public int getJoinRequestCreator(int jid) {
		return joinRequestManager.getAid(jid);
	}
	
	@Override
	public Iterator<Message> getMessages() {
		return messageManager.getMessages();
	}

	@Override
	public boolean matchesFilter(String fromKey, String toKey, String dateKey) {
		return rideInformation.matchesFilter(fromKey, toKey, dateKey);
	}

	@Override
	public boolean isDriver(int aid) {
		return aid == rideInformation.getAid();
	}

	@Override
	public boolean isRider(int aid) {
		return joinRequestManager.isRider(aid);
	}

	@Override
	public boolean sentJoinRequestToRide(int aid) {
		return joinRequestManager.sentJoinRequestToRide(aid);
	}
	
	@Override
	public boolean doesJoinRequestExist(int jid) {
		return joinRequestManager.doesJoinRequestExist(jid);
	}

	@Override
	public int addJoinRequest(RequestInformation joinRequestInformation) {
		return joinRequestManager.addJoinRequest((JoinRequestInformation) joinRequestInformation);
	}

	@Override
	public int addMessage(RequestInformation messageInformation) {
		return messageManager.addMessage((MessageInformation) messageInformation);
	}

	@Override
	public void setRideConfirmed(int jid, boolean status) {
		if (status) {
			joinRequestManager.acceptJoinRequest(jid);
		}
		else {
			joinRequestManager.denyJoinRequest(jid);
		}
	}

	@Override
	public void setPickUpConfirmed(int jid) {
		joinRequestManager.setPickUpConfirmed(jid);
	}

	@Override
	public void updateRide(RequestInformation rideInformation) {
		RideInformation newRideInformation = (RideInformation) rideInformation;
		this.rideInformation = newRideInformation;
	}

}
