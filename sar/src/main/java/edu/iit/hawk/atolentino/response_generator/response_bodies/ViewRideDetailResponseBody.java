package edu.iit.hawk.atolentino.response_generator.response_bodies;

import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import edu.iit.hawk.atolentino.account_management.boundaries.AccountUtilities;
import edu.iit.hawk.atolentino.rate_management.entities.Rate;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;
import edu.iit.hawk.atolentino.ride_management.boundaries.RideUtilities;

public class ViewRideDetailResponseBody implements ResponseBodyUtility {
	private RideUtilities ride;
	private AccountUtilities account;
	
	public ViewRideDetailResponseBody(RideUtilities ride, AccountUtilities account) {
		this.ride = ride;
		this.account = account;
	}
	
	@Override
	public String generateBody() {
		Gson gson = new Gson();
		JsonObject jsonBody = new JsonObject();
		
		jsonBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(ride.getRid()));
		jsonBody.add(ResponseBodyUtility.LOCATION_INFO_KEY, generateLocationInfoJson(gson));
		jsonBody.add(ResponseBodyUtility.DATE_TIME_KEY, generateDateTimeJson(gson));
		jsonBody.add(ResponseBodyUtility.CAR_INFO_KEY, generateCarInfoJson(gson));
		jsonBody.add(ResponseBodyUtility.MAX_PASSENGERS_KEY, gson.toJsonTree(ride.getMaxPassengers()));
		jsonBody.add(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY, gson.toJsonTree(
				ride.getAmountPerPassenger() == null 
				? 0.0 : ride.getAmountPerPassenger()
				));	
		jsonBody.add(ResponseBodyUtility.CONDITIONS_KEY, gson.toJsonTree(ride.getConditions()));
		jsonBody.add(ResponseBodyUtility.DRIVER_KEY, gson.toJsonTree(account.getFirstName()));
		jsonBody.add(ResponseBodyUtility.DRIVER_PICTURE_KEY, gson.toJsonTree(account.getProfilePicture()));
		jsonBody.add(ResponseBodyUtility.RIDES_KEY, gson.toJsonTree(account.getDriverRideParticipation()));
		jsonBody.add(ResponseBodyUtility.RATINGS_KEY, gson.toJsonTree(account.getNumberOfDriverRatings()));
		jsonBody.add(ResponseBodyUtility.AVERAGE_RATING_KEY, gson.toJsonTree(
				account.getDriverAverageRating() == null 
				? JsonNull.INSTANCE : account.getDriverAverageRating()
				));	
		jsonBody.add(ResponseBodyUtility.COMMENTS_ABOUT_DRIVER_KEY, generateCommentsAboutDriver(gson));
		
		return jsonBody.toString();
	}
	
	private JsonObject generateLocationInfoJson(Gson gson) {
		JsonObject locationInfo = new JsonObject();
		locationInfo.add(ResponseBodyUtility.FROM_CITY_KEY, gson.toJsonTree(ride.getFromCity()));
		locationInfo.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree(ride.getFromZip()));
		locationInfo.add(ResponseBodyUtility.TO_CITY_KEY, gson.toJsonTree(ride.getToCity()));
		locationInfo.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree(ride.getToZip()));
		return locationInfo;
	}
	
	private JsonObject generateDateTimeJson(Gson gson) {
		JsonObject dateTime = new JsonObject();
		dateTime.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree(ride.getDate()));
		dateTime.add(ResponseBodyUtility.TIME_KEY, gson.toJsonTree(ride.getTime()));
		return dateTime;
	}
	
	private JsonObject generateCarInfoJson(Gson gson) {
		JsonObject carInfo = new JsonObject();
		carInfo.add(ResponseBodyUtility.MAKE_KEY, gson.toJsonTree(ride.getMake()));
		carInfo.add(ResponseBodyUtility.MODEL_KEY, gson.toJsonTree(ride.getModel()));
		carInfo.add(ResponseBodyUtility.COLOR_KEY, gson.toJsonTree(ride.getColor()));
		carInfo.add(ResponseBodyUtility.PLATE_STATE_KEY, gson.toJsonTree(ride.getPlateState()));
		carInfo.add(ResponseBodyUtility.PLATE_SERIAL_KEY, gson.toJsonTree(ride.getPlateSerial()));
		return carInfo;
	}
	
	private JsonArray generateCommentsAboutDriver(Gson gson) {
		JsonArray commentsAboutDriver = new JsonArray();
		Iterator<Rate> rateIterator = account.getDriverRatingIterator();

		while(rateIterator.hasNext()) {
			Rate rate = rateIterator.next();
			JsonObject jsonObject = new JsonObject();
			jsonObject.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(rate.getRid()));
			jsonObject.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree(rate.getDate()));
			jsonObject.add(ResponseBodyUtility.RATING_KEY, gson.toJsonTree(rate.getRating()));
			jsonObject.add(ResponseBodyUtility.COMMENT_KEY, gson.toJsonTree(rate.getComment()));
			commentsAboutDriver.add(jsonObject);
		}
		return commentsAboutDriver;
	}
}
