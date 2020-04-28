package edu.iit.hawk.atolentino.reports;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.reports.interactors.Process911;
import edu.iit.hawk.atolentino.ride_management.boundaries.RideUtilities;

public class Process911Test {
	private Process911 process911;
	private static final String NAME = "Rides taken between two dates";
	
	@BeforeEach
	void init() {
		List<RideUtilities> rides = new ArrayList<RideUtilities>();
		process911 = new Process911("", "", rides.iterator());
	}
	
	@Test
	void test_getPid_returns911() {
		assertEquals(911, process911.getPid());
	}
	
	@Test
	void test_getName_returnsNAME() {
		assertEquals(NAME, process911.getName());
	}
}

