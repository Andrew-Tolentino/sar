package edu.iit.hawk.atolentino.join_request_management;

import java.util.HashMap;
import java.util.Iterator;

import edu.iit.hawk.atolentino.request_information_processing.entities.JoinRequestInformation;
import edu.iit.hawk.atolentino.ride_management.interactors.JoinRequestManagerUtilities;
import edu.iit.hawk.atolentino.ride_management.interactors.JoinRequestUtilities;

public class JoinRequestManager implements JoinRequestManagerUtilities {
	private HashMap<Integer, JoinRequestUtilities> joinRequests = new HashMap<Integer, JoinRequestUtilities>();
	private static int counter = 1;

	@Override
	public int addJoinRequest(JoinRequestInformation joinRequestInformation) {		
		JoinRequestUtilities joinRequest = new JoinRequest(counter, joinRequestInformation);
		joinRequests.put(counter, joinRequest);
		counter++;
		return joinRequest.getJid();
	}
	
	@Override
	public int getAid(int jid) {
		JoinRequestUtilities joinRequest = joinRequests.get(jid);
		return joinRequest.getAid();
	}	
	
	@Override
	public void setPickUpConfirmed(int jid) {
		JoinRequestUtilities joinRequest = joinRequests.get(jid);
		joinRequest.setPickUpConfirmed();
	}

	@Override
	public void denyJoinRequest(int jid) {
		JoinRequestUtilities joinRequest = joinRequests.get(jid);
		joinRequest.setRideConfirmed(false);
	}
	
	@Override
	public void acceptJoinRequest(int jid) {
		JoinRequestUtilities joinRequest = joinRequests.get(jid);
		joinRequest.setRideConfirmed(true);		
	}

	@Override
	public boolean doesJoinRequestExist(int jid) {
		return joinRequests.containsKey(jid);
	}

	@Override
	public boolean sentJoinRequestToRide(int aid) {
		Iterator<JoinRequestUtilities> iterator = joinRequests.values().iterator();
		while(iterator.hasNext()) {
			JoinRequestUtilities joinRequest = iterator.next();
			if (joinRequest.getAid() == aid)  {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isRider(int aid) {
		Iterator<JoinRequestUtilities> iterator = joinRequests.values().iterator();
		while(iterator.hasNext()) {
			JoinRequestUtilities joinRequest = iterator.next();
			if (joinRequest.getAid() == aid)  {
				if (joinRequest.getRideConfirmed() == null) {
					return false;
				}
				else if (joinRequest.getRideConfirmed()) {
					return true;
				}
			}
		}
		return false;
	}
}
