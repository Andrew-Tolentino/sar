package edu.iit.hawk.atolentino.reports.interactors;

import java.util.Iterator;

import edu.iit.hawk.atolentino.reports.boundaries.ShareARideProcess;
import edu.iit.hawk.atolentino.ride_management.boundaries.RideUtilities;

public class Process911 extends ShareARideProcess {
	public static final int PID = 911;
	public static final String NAME = "Rides taken between two dates";

	public Process911(String startDate, String endDate, Iterator<RideUtilities> rides) {
		super(startDate, endDate, rides);
	}

	@Override
	public int getPid() {
		return PID;
	}

	@Override
	public String getName() {
		return NAME;
	}

}
