package edu.iit.hawk.atolentino.response_generator.response_bodies;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;

public class PostResponseBody implements ResponseBodyUtility {
	private String key;
	private int value;
	
	public PostResponseBody(String key, int value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String generateBody() {
		Gson gson = new Gson();
		JsonObject jsonBody = new JsonObject();	
		jsonBody.add(key, gson.toJsonTree(value));
		return jsonBody.toString();
	}

}
