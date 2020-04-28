package edu.iit.hawk.atolentino.reports;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.reports.entities.ReportDetail;
import edu.iit.hawk.atolentino.reports.interactors.Report;

public class ReportTest {
	private Report report;
	
	@BeforeEach
	void init() {
		report = new Report();
	}
	
	@Test
	void test_addReportDetail_shouldExistAfterAdding() {
		String FROM_ZIP = "60632";
		String TO_ZIP = "60629";
		report.addReportDetail(FROM_ZIP, TO_ZIP);
		assertTrue(report.doesRideDetailExist(FROM_ZIP, TO_ZIP));
	}
	
	@Test
	void test_doesRideDetailExist_givenFromZipAndToZipThatAreNotInTheReport_shouldReturnFalse() {
		String FROM_ZIP = "60632";
		String TO_ZIP = "60629";
		assertFalse(report.doesRideDetailExist(FROM_ZIP, TO_ZIP));
	}
	
	@Test
	void test_incrementCount_givenFromZipAndToZipThatAreNotInTheReport_shouldThrowIllegalArgumentException() {
		String FROM_ZIP = "60632";
		String TO_ZIP = "60629";
		assertThrows(IllegalArgumentException.class, () -> {
			report.incrementCount(FROM_ZIP, TO_ZIP);
		});
	}
	
	@Test
	void test_incrementCount_afterIncrementing_shouldReturnTwp() {
		String FROM_ZIP = "60632";
		String TO_ZIP = "60629";
		report.addReportDetail(FROM_ZIP, TO_ZIP);
		report.incrementCount(FROM_ZIP, TO_ZIP);
		assertEquals(2, report.getSize());
	}
	
	@Test
	void test_getReportDetails_default_shouldBeEmpty() {
		assertFalse(report.getReportDetails().hasNext());
	}
	
	@Test
	void test_getReportDetails_afterAddingReportDetail_shouldContainReportDetail() {
		String FROM_ZIP = "60632";
		String TO_ZIP = "60629";
		report.addReportDetail(FROM_ZIP, TO_ZIP);
		ReportDetail reportDetail = null;
		Iterator<ReportDetail> iterator = report.getReportDetails();
		while(iterator.hasNext()) {
			reportDetail = iterator.next();
		}
		assertEquals(FROM_ZIP, reportDetail.getFromZip());
		assertEquals(TO_ZIP, reportDetail.getToZip());
	}
}
