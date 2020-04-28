package edu.iit.hawk.atolentino.response_generator.response_bodies;

import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import edu.iit.hawk.atolentino.account_management.boundaries.AccountUtilities;
import edu.iit.hawk.atolentino.rate_management.entities.Rate;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;

public class ViewRatingsResponseBody implements ResponseBodyUtility {
	private AccountUtilities account;
	private int rides;
	private int ratings;
	private Double averageRating;
	private Iterator<Rate> rateIterator;
	
	public ViewRatingsResponseBody(AccountUtilities account, RatingType type) {
		this.account = account;
		
		if (type == RatingType.DRIVER) {
			rides = account.getDriverRideParticipation();
			ratings = account.getNumberOfDriverRatings();
			averageRating = account.getDriverAverageRating();
			rateIterator = account.getDriverRatingIterator();
		}
		
		else if (type == RatingType.RIDER) {
			rides = account.getRiderRideParticipation();
			ratings = account.getNumberOfRiderRatings();
			averageRating = account.getRiderAverageRating();
			rateIterator = account.getRiderRatingIterator();
		}
		
	}
	
	@Override
	public String generateBody() {
		Gson gson = new Gson();
		JsonObject jsonBody = new JsonObject();
		JsonArray detail = new JsonArray();		
		
		jsonBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(account.getAid()));
		jsonBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree(account.getFirstName()));
		jsonBody.add(ResponseBodyUtility.RIDES_KEY, gson.toJsonTree(rides));
		jsonBody.add(ResponseBodyUtility.RATINGS_KEY, gson.toJsonTree(ratings));
		jsonBody.add(ResponseBodyUtility.AVERAGE_RATING_KEY, gson.toJsonTree(
				averageRating == null 
				? JsonNull.INSTANCE : averageRating
				));
		jsonBody.add(ResponseBodyUtility.DETAIL_KEY, detail);
		
		while(rateIterator.hasNext()) {
			Rate rate = rateIterator.next();
			JsonObject jsonObject = new JsonObject();
			jsonObject.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(rate.getRid()));
			jsonObject.add(ResponseBodyUtility.SENT_BY_ID_KEY, gson.toJsonTree(rate.getRaterId()));
			jsonObject.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree(rate.getRaterFistName()));
			jsonObject.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree(rate.getDate()));
			jsonObject.add(ResponseBodyUtility.RATING_KEY, gson.toJsonTree(rate.getRating()));
			jsonObject.add(ResponseBodyUtility.COMMENT_KEY, gson.toJsonTree(rate.getComment()));
			
			detail.add(jsonObject);
		}
		return jsonBody.toString();
	}
	
	public static enum RatingType {
		DRIVER,
		RIDER
	}
}
