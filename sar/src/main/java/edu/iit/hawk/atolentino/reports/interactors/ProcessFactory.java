package edu.iit.hawk.atolentino.reports.interactors;

import java.util.Iterator;

import edu.iit.hawk.atolentino.reports.boundaries.ShareARideProcess;
import edu.iit.hawk.atolentino.ride_management.boundaries.RideUtilities;

public class ProcessFactory {
	private String startDate;
	private String endDate;
	private Iterator<RideUtilities> rides;
	
	public ProcessFactory(String startDate, String endDate, Iterator<RideUtilities> rides) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.rides = rides;
	}
	
	public ShareARideProcess buildProcess(int pid) {
		ShareARideProcess process;
		switch(pid) {
		case Process907.PID:
			process = new Process907(startDate, endDate, rides);
			break;
			
		case Process911.PID:
			process = new Process911(startDate, endDate, rides);
			break;
			
		default:
			throw new IllegalArgumentException("Invalid pid argument");
		}
		return process;
	}
	
	public static String getProcessName(ProcessTypes type) {
		String name = null;		
		switch(type) {
		case PROCESS907:
			name = Process907.NAME;
			break;
			
		case PROCESS911:
			name = Process911.NAME;
			break;		
		}
		return name;
	}
}