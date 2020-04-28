package edu.iit.hawk.atolentino.request_information_processing.entities;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;

public class RateInformation extends RequestInformation {
	private final Integer rid;
	private final Integer sentById;
	private final Integer rating;
	private String comment;
	
	public RateInformation(Integer rid, Integer sentById, Integer rating, String comment) {
		this.rid = rid;
		this.sentById = sentById;
		this.rating = rating;
		this.comment = comment;
	}
	
	@Override
	public Integer getRid() {
		return rid;
	}
	
	@Override
	public Integer getAid() {
		return sentById;
	}
	
	public Integer getRating() {
		return rating;
	}
	
	public String getComment() {
		return comment;
	}

	@Override
	public boolean isNill() {
		return false;
	}
}
