package edu.iit.hawk.atolentino.response_generator.response_bodies;

import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.iit.hawk.atolentino.message_management.entities.Message;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;

public class MessageHistoryResponseBody implements ResponseBodyUtility {
	private Iterator<Message> iterator;
	
	public MessageHistoryResponseBody(Iterator<Message> iterator) {
		this.iterator = iterator;
	}
	
	@Override
	public String generateBody() {
		Gson gson = new Gson();
		JsonArray jsonArray = new JsonArray();		
		while(iterator.hasNext()) {
			Message message = iterator.next();
			JsonObject jsonObject = new JsonObject();
			jsonObject.add(ResponseBodyUtility.MESSAGE_ID_KEY, gson.toJsonTree(message.getMid()));
			jsonObject.add(ResponseBodyUtility.SENT_BY_ACCOUNT_KEY, gson.toJsonTree(message.getAid()));
			jsonObject.add(ResponseBodyUtility.DATE_KEY, gson.toJsonTree(message.getDateCreated()));
			jsonObject.add(ResponseBodyUtility.BODY_KEY, gson.toJsonTree(message.getMsg()));
			jsonArray.add(jsonObject);
		}
		return jsonArray.toString();
	}
}
