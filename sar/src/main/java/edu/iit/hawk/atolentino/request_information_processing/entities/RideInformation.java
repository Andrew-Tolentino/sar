package edu.iit.hawk.atolentino.request_information_processing.entities;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;

public class RideInformation extends RequestInformation {
	private final Integer aid;
	private final String fromCity;
	private final String fromZip;
	private final String toCity;
	private final String toZip;
	private final String date;
	private final String time;
	private final String make;
	private final String model;
	private final String color;
	private final String plateState;
	private final String plateSerial;
	private final Integer maxPassengers;
	private final Double amountPerPassenger;
	private final String conditions;
	
	public RideInformation (
			Integer aid,
			String fromCity,
			String fromZip,
			String toCity,
			String toZip,
			String date,
			String time,
			String make,
			String model,
			String color,
			String plateState,
			String plateSerial,
			Integer maxPassengers,
			Double amountPerPassenger,
			String conditions
			) {
		this.aid = aid;
		this.fromCity = fromCity;
		this.fromZip = fromZip;
		this.toCity = toCity;
		this.toZip = toZip;
		this.date = date;
		this.time = time;
		this.make = make;
		this.model = model;
		this.color = color;
		this.plateState = plateState;
		this.plateSerial = plateSerial;
		this.maxPassengers = maxPassengers;
		this.amountPerPassenger = amountPerPassenger;
		this.conditions = conditions;
	}
	
	@Override
	public Integer getAid() {
		return aid;
	}
	
	public String getFromCity() {
		return fromCity;
	}
	
	public String getFromZip() {
		return fromZip;
	}
	
	public String getToCity() {
		return toCity;
	}
	
	public String getToZip() {
		return toZip;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getTime() {
		return time;
	}
	
	public String getMake() {
		return make;
	}
	
	public String getModel() {
		return model;
	}
	
	public String getColor() {
		return color;
	}
	
	public String getPlateState() {
		return plateState;
	}
	
	public String getPlateSerial() {
		return plateSerial;
	}
	
	public Integer getMaxPassengers() {
		return maxPassengers;
	}
	
	public Double getAmountPerPassenger() {
		return amountPerPassenger;
	}
	
	public String getConditions() {
		return conditions;
	}
	
	public boolean matchesFilter(String fromKey, String toKey, String dateKey) {
		fromKey = fromKey.toLowerCase();
		toKey = toKey.toLowerCase();
		dateKey = dateKey.toLowerCase();
		
		return doFromPropertiesMatchFilter(fromKey) &&
				doToPropertiesMatchFilter(toKey) && 
				doDateTimePropertiesMatchFilter(dateKey);
	}
	
	private boolean doFromPropertiesMatchFilter(String fromKey) {
		String fromCityLowerCase = fromCity.toLowerCase();
		String fromZipLowerCase = fromZip.toLowerCase();
		
		if (fromKey.isEmpty()) {
			return true;
		}
		
		return fromCityLowerCase.contains(fromKey) || fromZipLowerCase.contains(fromKey);		
	}
	
	private boolean doToPropertiesMatchFilter(String toKey) {
		String toCityLowerCase = toCity.toLowerCase();
		String toZipLowerCase = toZip.toLowerCase();
		
		if (toKey.isEmpty()) {
			return true;
		}
		
		return toCityLowerCase.contains(toKey) || toZipLowerCase.contains(toKey);
	}
	
	private boolean doDateTimePropertiesMatchFilter(String dateKey) {
		String dateLowerCase = date.toLowerCase();
		String timeLowerCase = time.toLowerCase();
		
		if (dateKey.isEmpty()) {
			return true;
		}
		
		return dateLowerCase.contains(dateKey) || timeLowerCase.contains(dateKey);
	}

	@Override
	public boolean isNill() {
		return false;
	}
}
