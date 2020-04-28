package edu.iit.hawk.atolentino.request_information_processing.request_information;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.request_information_processing.entities.NillRequestInformation;

public class NillRequestInformationTest {
	private NillRequestInformation nillRequestInformation;
	private static final String ERROR_MESSAGE = "Example Error Message";
	
	@BeforeEach
	void init() {
		nillRequestInformation = new NillRequestInformation(ERROR_MESSAGE);
	}
	
	@Test
	void test_getErrorMessage_shouldReturnERRORMESSAGE() {
		assertEquals(ERROR_MESSAGE, nillRequestInformation.getErrorMessage());
	}

	@Test
	void test_isNill_shouldReturnTrue() {
		assertTrue(nillRequestInformation.isNill());
	}
	
}
