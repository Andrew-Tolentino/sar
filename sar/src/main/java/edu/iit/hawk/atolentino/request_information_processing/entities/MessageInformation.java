package edu.iit.hawk.atolentino.request_information_processing.entities;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;

public class MessageInformation extends RequestInformation {
	private final Integer aid;
	private final String msg;
	 
	public MessageInformation(Integer aid, String msg) {
		this.aid = aid;
		this.msg = msg;
	}
	
	@Override
	public Integer getAid() {
		return aid;
	}
	
	public String getMsg() {
		return msg;
	}

	@Override
	public boolean isNill() {
		return false;
	}
}
