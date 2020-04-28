package edu.iit.hawk.atolentino.reports.interactors;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import edu.iit.hawk.atolentino.reports.entities.ReportDetail;

public class Report {
	private HashMap<String, ReportDetail> reportDetails = new HashMap<String, ReportDetail>();
		
	public void addReportDetail(String fromZip, String toZip) {
		reportDetails.put(fromZip + toZip, new ReportDetail(fromZip, toZip));
	}
	
	public boolean doesRideDetailExist(String fromZip, String toZip) {
		return reportDetails.containsKey(fromZip + toZip);
	}
	
	public void incrementCount(String fromZip, String toZip) throws IllegalArgumentException {
		if (!doesRideDetailExist(fromZip, toZip)) {
			throw new IllegalArgumentException("Bucket for fromZip ("+ fromZip +") and toZip("+ toZip +") does not exist");
		}
		ReportDetail reportDetail = reportDetails.get(fromZip + toZip);
		reportDetail.incrementCount();
	}
	
	public int getSize() {
		int size = 0;
		Iterator<ReportDetail> iterator = reportDetails.values().iterator();
		while(iterator.hasNext()) {
			ReportDetail reportDetail = iterator.next();
			size += reportDetail.getCount();
		}
		return size;
	}
	
	public Iterator<ReportDetail> getReportDetails() {
		return Collections.unmodifiableCollection(reportDetails.values()).iterator();
	}
	
}
