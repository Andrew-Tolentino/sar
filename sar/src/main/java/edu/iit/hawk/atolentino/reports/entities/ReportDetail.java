package edu.iit.hawk.atolentino.reports.entities;

public class ReportDetail {
	private String fromZip;
	private String toZip;
	private int count;
	
	public ReportDetail(String fromZip, String toZip) {
		this.fromZip = fromZip;
		this.toZip = toZip;
		this.count = 1;
	}
	
	public String getFromZip() {
		return fromZip;
	}
	
	public String getToZip() {
		return toZip;
	}
	
	public int getCount() {
		return count;
	}
	
	public void incrementCount() {
		count++;
	}
	
}
