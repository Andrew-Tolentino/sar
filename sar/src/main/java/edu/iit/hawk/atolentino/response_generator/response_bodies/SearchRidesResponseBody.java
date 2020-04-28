package edu.iit.hawk.atolentino.response_generator.response_bodies;

import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;
import edu.iit.hawk.atolentino.ride_management.boundaries.RideUtilities;

import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class SearchRidesResponseBody implements ResponseBodyUtility {
	private Iterator<RideUtilities> iterator;
	
	public SearchRidesResponseBody(Iterator<RideUtilities> iterator) {
		this.iterator = iterator;
	}
	
	@Override
	public String generateBody() {
		Gson gson = new Gson();
		JsonArray jsonArray = new JsonArray();
		while(iterator.hasNext()) {
			RideUtilities ride = iterator.next();
			JsonObject jsonObject = new JsonObject();
			jsonObject.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(ride.getRid()));
			jsonObject.add(ResponseBodyUtility.LOCATION_INFO_KEY, generateLocationInfoJson(ride, gson));
			jsonObject.add(ResponseBodyUtility.DATE_TIME_KEY, generateDateTimeJson(ride, gson));
			jsonArray.add(jsonObject);
		}	
		return jsonArray.toString();
	}
	
	private JsonObject generateLocationInfoJson(RideUtilities ride, Gson gson) {
		JsonObject locationInfo = new JsonObject();
		locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree(ride.getFromCity()));
		locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree(ride.getFromZip()));
		locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree(ride.getToCity()));
		locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree(ride.getToZip()));
		return locationInfo;
	}
	
	private JsonObject generateDateTimeJson(RideUtilities ride, Gson gson) {
		JsonObject dateTime = new JsonObject();
		dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree(ride.getDate()));
		dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree(ride.getTime()));
		return dateTime;
	}

}
