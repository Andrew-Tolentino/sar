package edu.iit.hawk.atolentino.response_generator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;
import edu.iit.hawk.atolentino.response_generator.response_bodies.ShareARideResponseBuilder;
import edu.iit.hawk.atolentino.response_generator.response_bodies.ValidationErrorBody;

public class ShareARideResponseBuilderTest {
	@Test
	void test_constructor_returnsResponseWithExpectedStatus() {
		Status STATUS = Status.BAD_REQUEST;
		ShareARideResponseBuilder builder = new ShareARideResponseBuilder(STATUS);
		Response response = builder.build();
		assertEquals(STATUS.getStatusCode(), response.getStatus());
	}
	
	@Test
	void test_addResponseBody_returnsResponseWithResponseBody() {
		Gson gson = new Gson();
		String DETAIL = "Error";
		Status STATUS = Status.BAD_REQUEST;
		UriInfo URIINFO = Mockito.mock(UriInfo.class);
		JsonObject jsonObject = new JsonObject();	
		jsonObject.add(ResponseBodyUtility.ERROR_RESPONSE_TYPE_KEY, gson.toJsonTree(ResponseBodyUtility.ERROR_RESPONSE_TYPE_RESOURCE_MESSAGE));
		jsonObject.add(ResponseBodyUtility.ERROR_RESPONSE_TITLE_KEY, gson.toJsonTree(ResponseBodyUtility.ERROR_RESPONSE_TITLE_VALIDATION_MESSAGE));
		jsonObject.add(ResponseBodyUtility.ERROR_RESPONSE_DETAIL_KEY, gson.toJsonTree(DETAIL));
		jsonObject.add(ResponseBodyUtility.ERROR_RESPONSE_STATUS_KEY, gson.toJsonTree(STATUS.getStatusCode()));
		jsonObject.add(ResponseBodyUtility.ERROR_RESPONSE_INSTANCE_KEY, gson.toJsonTree("/" + URIINFO.getPath()));
		String expectedValidationErrorBodyJson = jsonObject.toString();
		
		ShareARideResponseBuilder builder = new ShareARideResponseBuilder(STATUS);
		builder.addResponseBody(new ValidationErrorBody(DETAIL, URIINFO, STATUS.getStatusCode()));
		Response response = builder.build();
		
		assertEquals(expectedValidationErrorBodyJson, response.getEntity());
	}
}
