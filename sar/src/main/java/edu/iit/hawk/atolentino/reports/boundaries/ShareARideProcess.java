package edu.iit.hawk.atolentino.reports.boundaries;

import java.util.Iterator;

import edu.iit.hawk.atolentino.reports.interactors.ProcessTypes;
import edu.iit.hawk.atolentino.reports.interactors.Report;
import edu.iit.hawk.atolentino.ride_management.boundaries.RideUtilities;

public abstract class ShareARideProcess {
	private String startDate;
	private String endDate;
	private Iterator<RideUtilities> rides;
	
	public static boolean doesProcessExist(int pid) {
		for(ProcessTypes type : ProcessTypes.values()) {
			if (type.getPid() == pid) {
				return true;
			}
		}
		return false;
	}
	
	public ShareARideProcess(String startDate, String endDate, Iterator<RideUtilities> rides) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.rides = rides;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	protected Iterator<RideUtilities> getRides() {
		return this.rides;
	}
	
	public Report generateReport() {
		Report report = new Report();
		while(rides.hasNext()) {
			RideUtilities ride = rides.next();
			String fromZip = ride.getFromZip();
			String toZip = ride.getToZip();
			
			if (report.doesRideDetailExist(fromZip, toZip)) {
				report.incrementCount(fromZip, toZip);
			}
			else {
				report.addReportDetail(fromZip, toZip);
			}
		}
		return report;
	}
	
	public abstract int getPid();
		
	public abstract String getName();

}