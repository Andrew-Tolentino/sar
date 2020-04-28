package edu.iit.hawk.atolentino.response_generator.response_bodies;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.iit.hawk.atolentino.reports.interactors.ProcessFactory;
import edu.iit.hawk.atolentino.reports.interactors.ProcessTypes;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;

public class ViewAllReportsResponseBody implements ResponseBodyUtility {

	@Override
	public String generateBody() {
		Gson gson = new Gson();
		JsonArray jsonArray = new JsonArray();
		for(ProcessTypes type: ProcessTypes.values()) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.add(ResponseBodyUtility.REPORT_ID_KEY, gson.toJsonTree(type.getPid()));
			jsonObject.add(ResponseBodyUtility.NAME_KEY, gson.toJsonTree(ProcessFactory.getProcessName(type)));
			jsonArray.add(jsonObject);
		}
		return jsonArray.toString();
	}

}
