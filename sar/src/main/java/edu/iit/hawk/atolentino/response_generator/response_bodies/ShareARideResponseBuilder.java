package edu.iit.hawk.atolentino.response_generator.response_bodies;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;

public class ShareARideResponseBuilder {
	private ResponseBuilder responseBuilder;	
	
	public ShareARideResponseBuilder(Status status) {
		responseBuilder = Response.status(status);
	}
	
	public ShareARideResponseBuilder addResourceLocationHeader(String location, UriInfo uriInfo) {
		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
		uriBuilder.path(location);
		responseBuilder.contentLocation(uriBuilder.build());
		responseBuilder.header(ResponseBodyUtility.LOCATION_KEY, uriBuilder.build().toString());
		return this;
	}
	
	public ShareARideResponseBuilder addResponseBody(ResponseBodyUtility responseBody) {
		responseBuilder.entity(responseBody.generateBody());
		return this;
	}
		
	public Response build() {
		return responseBuilder.build();
	}
}
