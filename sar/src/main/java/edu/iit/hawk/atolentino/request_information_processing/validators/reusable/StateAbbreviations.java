package edu.iit.hawk.atolentino.request_information_processing.validators.reusable;

public enum StateAbbreviations {
	ALABAMA("AL"),
	ALASKA("AK"),
	AMERICAN_SAMOA("AS"),
	ARIZONA("AZ"),
	ARKANSAS("AR"),
	CALIFORNIA("CA"),
	COLORADO("CO"),
	CONNECTICUT("CT"),
	DELEWARE("DE"),
	FLORIDA("FL"),
	GEORGIA("GA"),
	GUAM("GU"),
	HAWAII("HI"),
	IDAHO("ID"),
	ILLINOIS("IL"),
	INDIANA("IN"),
	IOWA("IA"),
	KANSAS("KS"),
	KENTUCKY("KY"),
	LOUISIANA("LA"),
	MAINE("ME"),
	MARYLAND("MD"),
	MASSACHUSETTS("MA"),
	MICHIGAN("MI"),
	MINNESOTA("MN"),
	MISSOURI("MO"),
	MONTANA("MT"),
	NEBRASKA("NE"),
	NEVADA("NV"),
	NEW_HAMPSHIRE("NH"),
	NEW_JERSEY("NJ"),
	NEW_MEXICO("NM"),
	NEW_YORK("NY"),
	NORTH_CAROLINA("NC"),
	NORTH_DAKOTA("ND"),
	OHIO("OH"),
	OKLAHOMA("OK"),
	OREGON("OR"),
	PENNSYLVANIA("PA"),
	PUERTO_RICO("PR"),
	RHODE_ISLAND("RI"),
	SOUTH_CAROLINA("SC"),
	SOUTH_DAKOTA("SD"),
	TENNESSEE("TN"),
	TEXAS("TX"),
	UTAH("UT"),
	VERMONT("VT"),
	VIRGINIA("VA"),
	WASHINGTON("WA"),
	WEST_VIRGINIA("WV"),
	WISCONSIN("WI"),
	WYOMING("WY");
	
	private String state;
	
	private StateAbbreviations(String state) {
		this.state = state;
	}
	
	public String getState() {
		return state;
	}
	
}
