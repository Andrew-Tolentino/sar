package edu.iit.hawk.atolentino.message_management.interactors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import edu.iit.hawk.atolentino.message_management.entities.Message;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.MessageInformation;
import edu.iit.hawk.atolentino.ride_management.interactors.MessageManagerUtilities;

public class MessageManager implements MessageManagerUtilities {
	private List<Message> messages = new ArrayList<Message>();
	private static int counter = 1;
	
	@Override
	public int addMessage(RequestInformation messageInformation) {
		Message message = new Message(counter, (MessageInformation) messageInformation);
		messages.add(message);
		counter++;
		return message.getMid();
	}

	@Override
	public Iterator<Message> getMessages() {
		return Collections.unmodifiableList(messages).iterator();
	}
	
	
}
