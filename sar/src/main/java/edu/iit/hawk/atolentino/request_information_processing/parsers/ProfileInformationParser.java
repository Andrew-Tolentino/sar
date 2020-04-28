package edu.iit.hawk.atolentino.request_information_processing.parsers;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.entities.ProfileInformation;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;

public class ProfileInformationParser implements RequestInformationParser {	
	@Override
	public RequestInformation parse(String body) throws NullPointerException, JsonSyntaxException {
		try {
			JsonObject jsonBody = new JsonParser().parse(body).getAsJsonObject();
			String firstName = jsonBody.get(ResponseBodyUtility.FIRST_NAME_KEY).isJsonNull()
					? null : jsonBody.get(ResponseBodyUtility.FIRST_NAME_KEY).getAsString();
			String lastName = jsonBody.get(ResponseBodyUtility.LAST_NAME_KEY).isJsonNull()
					? null : jsonBody.get(ResponseBodyUtility.LAST_NAME_KEY).getAsString();
			String phoneNumber = jsonBody.get(ResponseBodyUtility.PHONE_NUMBER_KEY).isJsonNull() 
					? null : jsonBody.get(ResponseBodyUtility.PHONE_NUMBER_KEY).getAsString();
			String profilePicture = jsonBody.get(ResponseBodyUtility.PROFILE_PICTURE_KEY).isJsonNull() 
					? null : jsonBody.get(ResponseBodyUtility.PROFILE_PICTURE_KEY).getAsString();
			Boolean active = jsonBody.get(ResponseBodyUtility.IS_ACTIVE_KEY).isJsonNull()
					? null : jsonBody.get(ResponseBodyUtility.IS_ACTIVE_KEY).getAsBoolean();
			return new ProfileInformation(firstName, lastName, phoneNumber, profilePicture, active);
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