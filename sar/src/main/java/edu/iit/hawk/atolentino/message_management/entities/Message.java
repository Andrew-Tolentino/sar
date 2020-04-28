package edu.iit.hawk.atolentino.message_management.entities;

import java.util.Calendar;

import edu.iit.hawk.atolentino.request_information_processing.entities.MessageInformation;
import edu.iit.hawk.atolentino.response_generator.boundaries.DateFormatter;

public class Message {
	private final int mid;
    private final Calendar dateCreated;
	private MessageInformation messageInformation;
	
	public Message(int mid, MessageInformation messageInformation) {
		this.mid = mid;
		dateCreated = Calendar.getInstance();
		this.messageInformation = messageInformation;
	}
	
	public int getMid() {
		return mid;
	}
	 
	public int getAid() {
		return  messageInformation.getAid();
	}
	
	public String getMsg() {
		return messageInformation.getMsg();
	}
	
    public String getDateCreated() {
    	return DateFormatter.getDefaultFormat(dateCreated);
    }	
}
