package edu.iit.hawk.atolentino.request_information_processing.parsers;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.entities.JoinRequestInformation;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;

public class JoinRequestInformationParser implements RequestInformationParser {

	@Override
	public RequestInformation parse(String body) throws NullPointerException, JsonSyntaxException {
		try {
			JsonObject jsonBody = new JsonParser().parse(body).getAsJsonObject();
			Integer aid = jsonBody.get(ResponseBodyUtility.ACCOUNT_ID_KEY).isJsonNull()
					? null : jsonBody.get(ResponseBodyUtility.ACCOUNT_ID_KEY).getAsInt();
			Integer passengers = jsonBody.get(ResponseBodyUtility.PASSENGERS_KEY).isJsonNull()
					? null : jsonBody.get(ResponseBodyUtility.PASSENGERS_KEY).getAsInt();
			Boolean rideConfirmed = jsonBody.get(ResponseBodyUtility.RIDE_CONFIRMED_KEY).isJsonNull() 
									? null : jsonBody.get(ResponseBodyUtility.RIDE_CONFIRMED_KEY).getAsBoolean();
			Boolean pickupConfirmed = jsonBody.get(ResponseBodyUtility.PICKUP_CONFIRMED_KEY).isJsonNull() 
									  ? null : jsonBody.get(ResponseBodyUtility.PICKUP_CONFIRMED_KEY).getAsBoolean();
			return new JoinRequestInformation(aid, passengers, rideConfirmed, pickupConfirmed);
		}
		catch(NullPointerException ex) {
			ex.printStackTrace();
			throw new NullPointerException(MISSING_ELEMENTS_MESSAGE);
		}
		catch(JsonSyntaxException ex) {
			ex.printStackTrace();
			throw new JsonSyntaxException(JSON_SYNTAX_MESSAGE);
		}
	}

}
