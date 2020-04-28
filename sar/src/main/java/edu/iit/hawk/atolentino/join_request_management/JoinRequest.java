package edu.iit.hawk.atolentino.join_request_management;

import edu.iit.hawk.atolentino.request_information_processing.entities.JoinRequestInformation;
import edu.iit.hawk.atolentino.ride_management.interactors.JoinRequestUtilities;

public class JoinRequest implements JoinRequestUtilities {
	private final int jid;
	private JoinRequestInformation joinRequestInformation;
	
	public JoinRequest(int jid, JoinRequestInformation joinRequestInformation) {
		this.jid = jid;
		this.joinRequestInformation = joinRequestInformation;
	}

	@Override
	public int getJid() {
		return jid;
	}

	@Override
	public int getAid() {
		return joinRequestInformation.getAid();
	}

	@Override
	public Boolean getRideConfirmed() {
		return joinRequestInformation.getRideConfirmed();
	}

	@Override
	public void setRideConfirmed(boolean status) {
		joinRequestInformation.setRideConfirmed(status);
		
	}

	@Override
	public void setPickUpConfirmed() {
		joinRequestInformation.setPickUpConfirmed();
	}
}
