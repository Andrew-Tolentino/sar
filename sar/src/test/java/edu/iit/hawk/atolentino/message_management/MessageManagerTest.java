package edu.iit.hawk.atolentino.message_management;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.message_management.entities.Message;
import edu.iit.hawk.atolentino.message_management.interactors.MessageManager;
import edu.iit.hawk.atolentino.request_information_processing.entities.MessageInformation;

public class MessageManagerTest {
	private MessageManager messageManager;
	
	@BeforeEach 
	void init( ) {
		messageManager = new MessageManager();
	}
	
	@Test
	void test_addMessage_withMessageFixture_shouldBeSeenInGetMessages() {
		int AID = 1;
		String MSG = "Hello";
		MessageInformation messageInformation = new MessageInformation(AID, MSG);
		messageManager.addMessage(messageInformation);
		Message message = null;
		Iterator<Message> iterator = messageManager.getMessages();
		while(iterator.hasNext()) {
			message = iterator.next();
		}
		assertEquals(AID, message.getAid());
	}
	
	@Test
	void test_getMessages_default_iteratorIsEmpty() {
		assertFalse(messageManager.getMessages().hasNext());
	}
}
