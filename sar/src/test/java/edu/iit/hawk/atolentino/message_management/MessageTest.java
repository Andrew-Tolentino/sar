package edu.iit.hawk.atolentino.message_management;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.message_management.entities.Message;
import edu.iit.hawk.atolentino.request_information_processing.entities.MessageInformation;
import edu.iit.hawk.atolentino.response_generator.boundaries.DateFormatter;

public class MessageTest {
	private Message message;
	
	private static final int AID = 1;
	private static final int MID = 1;
	private static final String MSG = "Hello";
	
	@BeforeEach
	void init() {
		MessageInformation messageInformation = new MessageInformation(AID, MSG);
		message = new Message(MID, messageInformation);
	}
	
	@Test
	void test_getMid_shouldReturnMID() {
		assertEquals(MID, message.getMid());
	}
	
	@Test
	void test_getAid_shouldReturnAID() {
		assertEquals(AID, message.getAid());
	}
	
	@Test
	void test_getMsg_shouldReturnMSG() {
		assertEquals(MSG, message.getMsg());
	}
	
	@Test
	void test_getDateCreated_hasProperFormat() {
		DateFormat dateFormat = DateFormatter.CREATED_DEFAULT_FORMAT;
		assertDoesNotThrow(() -> {
			dateFormat.parse(message.getDateCreated());
		});	
	}
}
