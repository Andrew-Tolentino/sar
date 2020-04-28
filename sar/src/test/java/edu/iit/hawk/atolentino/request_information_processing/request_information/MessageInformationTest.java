package edu.iit.hawk.atolentino.request_information_processing.request_information;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.request_information_processing.entities.MessageInformation;

public class MessageInformationTest {
	private MessageInformation messageInformation;
	private static final Integer AID = 1;
	private static final String MSG = "Hello";
	
	@BeforeEach
	void init() {
		messageInformation = new MessageInformation(AID, MSG);
	}
	
	@Test
	void test_getAid_shouldReturnAid() {
		assertEquals(AID, messageInformation.getAid());
	}
	
	@Test
	void test_getMsg_shouldReturnMSG() {
		assertEquals(MSG, messageInformation.getMsg());
	}
	
	@Test
	void test_isNill_shouldReturnFalse() {
		assertFalse(messageInformation.isNill());
	}
}
