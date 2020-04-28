package edu.iit.hawk.atolentino.request_information_processing.parsers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.entities.UpdateJoinRequestInformation;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.PatchType;

public class UpdateJoinRequestInformationParser implements RequestInformationParser  {
	private Boolean status;
	private PatchType key;
	
	@Override
	public RequestInformation parse(String body) throws NullPointerException, JsonSyntaxException {
		try {
			JsonObject jsonBody = new JsonParser().parse(body).getAsJsonObject();
			Integer aid = jsonBody.get(ResponseBodyUtility.ACCOUNT_ID_KEY).isJsonNull()
					? null : jsonBody.get(ResponseBodyUtility.ACCOUNT_ID_KEY).getAsInt();
			initStatusAndKey(jsonBody);
			return new UpdateJoinRequestInformation(aid, status, key);
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
	
	private void initStatusAndKey(JsonObject jsonBody) {
		if (jsonBody.has(ResponseBodyUtility.RIDE_CONFIRMED_KEY)) {
			status = jsonBody.get(ResponseBodyUtility.RIDE_CONFIRMED_KEY).isJsonNull() ?
					 null : jsonBody.get(ResponseBodyUtility.RIDE_CONFIRMED_KEY).getAsBoolean();
			key = PatchType.RIDE_CONFIRMED;
		}
		
		if (jsonBody.has(ResponseBodyUtility.PICKUP_CONFIRMED_KEY)) {
			status = jsonBody.get(ResponseBodyUtility.PICKUP_CONFIRMED_KEY).isJsonNull() ?
					 null : jsonBody.get(ResponseBodyUtility.PICKUP_CONFIRMED_KEY).getAsBoolean();
			key = PatchType.PICKUP_CONFIRMED;
		}
	}
}
