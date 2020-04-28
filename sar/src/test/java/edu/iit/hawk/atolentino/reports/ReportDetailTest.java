package edu.iit.hawk.atolentino.reports;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.reports.entities.ReportDetail;

public class ReportDetailTest {
	private ReportDetail reportDetail; 
	private static final String FROM_ZIP = "60632";
	private static final String TO_ZIP = "60629";
	
	@BeforeEach
	void init() {
		reportDetail = new ReportDetail(FROM_ZIP, TO_ZIP);
	}
	
	@Test
	void test_getFromZip_shouldReturnFROMZIP() {
		assertEquals(FROM_ZIP, reportDetail.getFromZip());
	}
	
	@Test
	void test_getToZip_shouldReturnTOZIP() {
		assertEquals(TO_ZIP, reportDetail.getToZip());
	}
	
	@Test
	void test_getCount_default_shouldReturnOne() {
		assertEquals(1, reportDetail.getCount());
	}
	
	@Test
	void test_incrementCount_getCountShouldReturnTwo() {
		reportDetail.incrementCount();
		assertEquals(2, reportDetail.getCount());
	}
}
