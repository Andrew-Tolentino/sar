package edu.iit.hawk.atolentino.response_generator.response_bodies;

import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.iit.hawk.atolentino.account_management.boundaries.AccountUtilities;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;

public class SearchAccountsResponseBody implements ResponseBodyUtility {
	private Iterator<AccountUtilities> iterator;
	
	public SearchAccountsResponseBody(Iterator<AccountUtilities> iterator) {
		this.iterator = iterator;
	}

	@Override
	public String generateBody() {
		Gson gson = new Gson();
		JsonArray jsonArray = new JsonArray();		
		while(iterator.hasNext()) {
			AccountUtilities account = iterator.next();
			JsonObject jsonObject = new JsonObject();
			jsonObject.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(account.getAid()));
			jsonObject.add(ResponseBodyUtility.NAME_KEY, gson.toJsonTree(account.getFirstName() + " " + account.getLastName()));
			jsonObject.add(ResponseBodyUtility.DATE_CREATED_KEY, gson.toJsonTree(account.getDateCreated()));
			jsonObject.add(ResponseBodyUtility.IS_ACTIVE_KEY, gson.toJsonTree(account.isActive()));
			jsonArray.add(jsonObject);
		}
		return jsonArray.toString();
	}
	
	
}
