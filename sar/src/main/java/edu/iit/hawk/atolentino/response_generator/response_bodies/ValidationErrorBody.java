package edu.iit.hawk.atolentino.response_generator.response_bodies;

import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import edu.iit.hawk.atolentino.request_information_processing.boundaries.RequestInformation;
import edu.iit.hawk.atolentino.request_information_processing.entities.NillRequestInformation;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;

public class ValidationErrorBody implements ResponseBodyUtility {
	private String detail;
	private UriInfo uriInfo;
	private int statusCode;
	

	
	public ValidationErrorBody(String detail, UriInfo uriInfo, int statusCode) {
		this.detail = detail;
		this.uriInfo = uriInfo;
		this.statusCode = statusCode;
	}
	
	public ValidationErrorBody(RequestInformation nillRequestInformation, UriInfo uriInfo, int statusCode) {
		this.detail = ((NillRequestInformation) nillRequestInformation).getErrorMessage();
		this.uriInfo = uriInfo;
		this.statusCode = statusCode;
		
	}

	@Override
	public String generateBody() {
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();	
		jsonObject.add(ResponseBodyUtility.ERROR_RESPONSE_TYPE_KEY, gson.toJsonTree(ResponseBodyUtility.ERROR_RESPONSE_TYPE_RESOURCE_MESSAGE));
		jsonObject.add(ResponseBodyUtility.ERROR_RESPONSE_TITLE_KEY, gson.toJsonTree(ResponseBodyUtility.ERROR_RESPONSE_TITLE_VALIDATION_MESSAGE));
		jsonObject.add(ResponseBodyUtility.ERROR_RESPONSE_DETAIL_KEY, gson.toJsonTree(detail));
		jsonObject.add(ResponseBodyUtility.ERROR_RESPONSE_STATUS_KEY, gson.toJsonTree(statusCode));
		jsonObject.add(ResponseBodyUtility.ERROR_RESPONSE_INSTANCE_KEY, gson.toJsonTree("/" + uriInfo.getPath()));
		return jsonObject.toString();
	}
}
