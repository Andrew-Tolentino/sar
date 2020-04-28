package edu.iit.hawk.atolentino.request_information_processing.entities;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.PatchType;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.PatchUtilities;

public class UpdateJoinRequestInformation extends RequestInformation implements PatchUtilities {
	private Integer aid;
	private Boolean status;
	private PatchType key;
	
	public UpdateJoinRequestInformation(Integer aid, Boolean status, PatchType key) {
		this.aid = aid;
		this.status = status;
		this.key = key;
	}
	
	@Override
	public Integer getAid() {
		return aid;
	}
	
	public Boolean getStatus() {
		return status;
	}
	
	@Override
	public boolean isNill() {
		return false;
	}
	
	@Override
	public PatchType getType() {
		return key;
	}
}
