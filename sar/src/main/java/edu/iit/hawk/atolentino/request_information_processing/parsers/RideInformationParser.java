package edu.iit.hawk.atolentino.request_information_processing.parsers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformationParser;
import edu.iit.hawk.atolentino.request_information_processing.entities.RideInformation;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;

public class RideInformationParser implements RequestInformationParser {
	private String fromCity;
	private String fromZip;
	private String toCity;
	private String toZip;
	private String date;
	private String time;
	private String make;
	private String model;
	private String color;
	private String plateState;
	private String plateSerial;

	@Override
	public RequestInformation parse(String body) throws NullPointerException, JsonSyntaxException {
		try {
			JsonObject jsonBody = new JsonParser().parse(body).getAsJsonObject();
			initLocationInfo(jsonBody.get(ResponseBodyUtility.LOCATION_INFO_KEY).getAsJsonObject());
			initDateTime(jsonBody.get(ResponseBodyUtility.DATE_TIME_KEY).getAsJsonObject());
			initCarInfo(jsonBody.get(ResponseBodyUtility.CAR_INFO_KEY).getAsJsonObject());
			
			Integer aid = jsonBody.get(ResponseBodyUtility.ACCOUNT_ID_KEY).isJsonNull()
					? null : jsonBody.get(ResponseBodyUtility.ACCOUNT_ID_KEY).getAsInt();
			Integer maxPassengers = jsonBody.get(ResponseBodyUtility.MAX_PASSENGERS_KEY).isJsonNull()
					? null : jsonBody.get(ResponseBodyUtility.MAX_PASSENGERS_KEY).getAsInt();
						
			Double amountPerPassenger = jsonBody.get(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY).isJsonNull()
										? null 
										: initAmountPerPassenger(jsonBody.get(ResponseBodyUtility.AMOUNT_PER_PASSENGER_KEY).getAsDouble());
			
			String conditions = jsonBody.get(ResponseBodyUtility.CONDITIONS_KEY).isJsonNull()
					? null : jsonBody.get(ResponseBodyUtility.CONDITIONS_KEY).getAsString();
			
			return new RideInformation(
					aid, 
					fromCity, 
					fromZip,
					toCity,
					toZip,
					date, 
					time,
					make, 
					model, 
					color, 
					plateState,
					plateSerial, 
					maxPassengers, 
					amountPerPassenger, 
					conditions
					);
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
	
	private void initLocationInfo(JsonObject locationInfoBody) {
		fromCity = locationInfoBody.get(ResponseBodyUtility.FROM_CITY_KEY).isJsonNull()
				? null : locationInfoBody.get(ResponseBodyUtility.FROM_CITY_KEY).getAsString();
		fromZip = locationInfoBody.has(ResponseBodyUtility.FROM_ZIP_KEY) 
				  ?  locationInfoBody.get(ResponseBodyUtility.FROM_ZIP_KEY).getAsString() : "";
		
		toCity = locationInfoBody.get(ResponseBodyUtility.TO_CITY_KEY).isJsonNull() 
				? null : locationInfoBody.get(ResponseBodyUtility.TO_CITY_KEY).getAsString();
		toZip = locationInfoBody.has(ResponseBodyUtility.TO_ZIP_KEY)
				? locationInfoBody.get(ResponseBodyUtility.TO_ZIP_KEY).getAsString() : "";
	}
	
	private void initDateTime(JsonObject dateTimeBody) {
		date = dateTimeBody.get(ResponseBodyUtility.DATE_KEY).isJsonNull() 
				? null : dateTimeBody.get(ResponseBodyUtility.DATE_KEY).getAsString();
		time = dateTimeBody.get(ResponseBodyUtility.TIME_KEY).isJsonNull()
				? null : dateTimeBody.get(ResponseBodyUtility.TIME_KEY).getAsString();
	}
	
	private void initCarInfo(JsonObject carInfoBody) {
		make = carInfoBody.get(ResponseBodyUtility.MAKE_KEY).isJsonNull()
				? null : carInfoBody.get(ResponseBodyUtility.MAKE_KEY).getAsString();
		model = carInfoBody.get(ResponseBodyUtility.MODEL_KEY).isJsonNull()
				? null : carInfoBody.get(ResponseBodyUtility.MODEL_KEY).getAsString();
		color = carInfoBody.get(ResponseBodyUtility.COLOR_KEY).isJsonNull()
				? null : carInfoBody.get(ResponseBodyUtility.COLOR_KEY).getAsString();
		plateState = carInfoBody.get(ResponseBodyUtility.PLATE_STATE_KEY).isJsonNull()
				? null : carInfoBody.get(ResponseBodyUtility.PLATE_STATE_KEY).getAsString();
		plateSerial = carInfoBody.get(ResponseBodyUtility.PLATE_SERIAL_KEY).isJsonNull()
				? null : carInfoBody.get(ResponseBodyUtility.PLATE_SERIAL_KEY).getAsString();
	}
	
	private Double initAmountPerPassenger(Double amount) {
		if (amount == 0.0) {
			return null;
		}
		else return amount;
	}

}
