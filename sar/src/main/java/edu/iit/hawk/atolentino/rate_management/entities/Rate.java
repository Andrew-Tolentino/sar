package edu.iit.hawk.atolentino.rate_management.entities;

import edu.iit.hawk.atolentino.request_information_processing.entities.RateInformation;

public class Rate {
	private final int sid;
	private final RateInformation rateInformation;
	private final String raterFirstName;
	private final String date;
	
	public Rate(int sid, RateInformation rateInformation, String raterFirstName, String date) {
		this.sid = sid;
		this.rateInformation = rateInformation;
		this.raterFirstName = raterFirstName;
		this.date = date;
	}
	
	public int getSid() {
		return sid;
	}
	
	public int getRid() {
		return rateInformation.getRid();
	}
	
	public int getRaterId() {
		return rateInformation.getAid();
	}
	
	public int getRating() {
		return rateInformation.getRating();
	}
	
	public String getComment() {
		return rateInformation.getComment();
	}
	
	public String getRaterFistName() {
		return raterFirstName;
	}
	
	public String getDate() {
		return date;
	}
	
}
