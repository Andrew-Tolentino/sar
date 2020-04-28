package edu.iit.hawk.atolentino.reports;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.reports.interactors.Process907;
import edu.iit.hawk.atolentino.ride_management.boundaries.RideUtilities;

public class Process907Test {
	private Process907 process907;
	private static final String NAME = "Rides posted between two dates";
	
	@BeforeEach
	void init() {
		List<RideUtilities> rides = new ArrayList<RideUtilities>();
		process907 = new Process907("", "", rides.iterator());
	}
	
	@Test
	void test_getPid_returns907() {
		assertEquals(907, process907.getPid());
	}
	
	@Test
	void test_getName_returnsNAME() {
		assertEquals(NAME, process907.getName());
	}
}
