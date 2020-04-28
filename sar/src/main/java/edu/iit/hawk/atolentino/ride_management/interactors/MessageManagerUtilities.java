package edu.iit.hawk.atolentino.ride_management.interactors;

import java.util.Iterator;

import edu.iit.hawk.atolentino.message_management.entities.Message;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;

public interface MessageManagerUtilities {
	public int addMessage(RequestInformation messageInformation);
	
	public Iterator<Message> getMessages();
}
