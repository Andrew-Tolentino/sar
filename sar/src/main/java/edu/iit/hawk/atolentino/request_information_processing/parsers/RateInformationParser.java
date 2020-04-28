package edu.iit.hawk.atolentino.request_information_processing.parsers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.entities.RateInformation;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;

public class RateInformationParser implements RequestInformationParser {

	@Override
	public RequestInformation parse(String body) throws NullPointerException, JsonSyntaxException {
		try {
			JsonObject jsonBody = new JsonParser().parse(body).getAsJsonObject();
			Integer rid = jsonBody.get(ResponseBodyUtility.RIDE_ID_KEY).isJsonNull()
					? null : jsonBody.get(ResponseBodyUtility.RIDE_ID_KEY).getAsInt();
			Integer sentById = jsonBody.get(ResponseBodyUtility.SENT_BY_ID_KEY).isJsonNull()
					? null : jsonBody.get(ResponseBodyUtility.SENT_BY_ID_KEY).getAsInt();
			Integer rating = jsonBody.get(ResponseBodyUtility.RATING_KEY).isJsonNull()
					? null : jsonBody.get(ResponseBodyUtility.RATING_KEY).getAsInt();
			String comment = jsonBody.get(ResponseBodyUtility.COMMENT_KEY).isJsonNull()
					? null : jsonBody.get(ResponseBodyUtility.COMMENT_KEY).getAsString();
			return new RateInformation(rid, sentById, rating, comment);
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
