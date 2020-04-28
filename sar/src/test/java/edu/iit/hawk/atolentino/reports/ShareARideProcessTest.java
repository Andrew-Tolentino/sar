package edu.iit.hawk.atolentino.reports;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.reports.boundaries.ShareARideProcess;
import edu.iit.hawk.atolentino.reports.interactors.Process907;
import edu.iit.hawk.atolentino.reports.interactors.Process911;
import edu.iit.hawk.atolentino.reports.interactors.Report;
import edu.iit.hawk.atolentino.request_information_processing.entities.RideInformation;
import edu.iit.hawk.atolentino.ride_management.boundaries.RideUtilities;
import edu.iit.hawk.atolentino.ride_management.interactors.Ride;

public class ShareARideProcessTest {
	private Iterator<RideUtilities> iterator;
	private static final int RID = 123;
	private static final int AID = 19;
	private static final String FROM_CITY = "Barrington";
	private static final String FROM_ZIP = "60010";
	private static final String TO_CITY = "Milwaukee";
	private static final String TO_ZIP = "53202";
	private static final String DATE = "14-Apr-2020";
	private static final String TIME = "09:00";
	private static final String MAKE = "Audi";
	private static final String MODEL = "A4";
	private static final String COLOR = "Gray";
	private static final String PLATE_STATE = "IL";
	private static final String PLATE_SERIAL = "COVID19";
	private static final int MAX_PASSENGERS = 2;
	private static final double AMOUNT_PER_PASSENGER = 15.00;
	private static final String CONDITIONS = "No more than one carry on per passenger. No pets.";
	
	@BeforeEach
	void init() {
		List<RideUtilities> rides = new ArrayList<RideUtilities>();
		Ride ride = new Ride(RID, new RideInformation(
				AID,
				FROM_CITY, 
				FROM_ZIP, 
				TO_CITY, 
				TO_ZIP, 
				DATE, 
				TIME, 
				MAKE, 
				MODEL, 
				COLOR, 
				PLATE_STATE, 
				PLATE_SERIAL, 
				MAX_PASSENGERS, 
				AMOUNT_PER_PASSENGER, 
				CONDITIONS
				));
		rides.add(ride);
		iterator = rides.iterator();
	}
	
	@Test
	void test_doesProcessExist_givenValidProcessNumber_shouldReturnTrue() {
		assertTrue(ShareARideProcess.doesProcessExist(907));
	}
	
	@Test
	void test_doesProcessExist_givenInvalidProcessNumber_shouldReturnFalse() {
		assertFalse(ShareARideProcess.doesProcessExist(-1));
	}
	
	@Test
	void test_getStartDate_usingProcess907_shouldReturnsExpectedStartDate() {
		String startDate = "14-Apr-2020";
		ShareARideProcess process = new Process907(startDate, "", iterator);
		assertEquals(startDate, process.getStartDate());
	}
	
	@Test
	void test_getStartDate_usingProcess911_shouldReturnsExpectedStartDate() {
		String startDate = "14-Apr-2020";
		ShareARideProcess process = new Process911(startDate, "", iterator);
		assertEquals(startDate, process.getStartDate());
	}
	
	@Test
	void test_getEndDate_usingProcess907_shouldReturnsExpectedStartDate() {
		String endDate = "14-Apr-2020";
		ShareARideProcess process = new Process907("", endDate, iterator);
		assertEquals(endDate, process.getEndDate());
	}
	
	@Test
	void test_getEndDate_usingProcess911_shouldReturnsExpectedStartDate() {
		String endDate = "14-Apr-2020";
		ShareARideProcess process = new Process911("", endDate, iterator);
		assertEquals(endDate, process.getEndDate());
	}
	
	@Test
	void test_generateReport_usingProcess907_shouldHaveSizeOne() {
		ShareARideProcess process = new Process907("", "", iterator);
		Report report = process.generateReport();
		assertEquals(1, report.getSize());
	}
	
	@Test
	void test_generateReport_usingProcess911_shouldHaveSizeOne() {
		ShareARideProcess process = new Process911("", "", iterator);
		Report report = process.generateReport();
		assertEquals(1, report.getSize());
	}
	
}
