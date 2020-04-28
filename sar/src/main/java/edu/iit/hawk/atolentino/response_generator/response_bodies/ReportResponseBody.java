package edu.iit.hawk.atolentino.response_generator.response_bodies;

import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import edu.iit.hawk.atolentino.reports.boundaries.ShareARideProcess;
import edu.iit.hawk.atolentino.reports.entities.ReportDetail;
import edu.iit.hawk.atolentino.reports.interactors.Report;
import edu.iit.hawk.atolentino.response_generator.boundaries.ResponseBodyUtility;

public class ReportResponseBody implements ResponseBodyUtility {
	private ShareARideProcess process;
	
	public ReportResponseBody(ShareARideProcess process) {
		this.process = process;
	}
	

	@Override
	public String generateBody() {
		Gson gson = new Gson();
		JsonObject jsonBody = new JsonObject();
		JsonArray detail = new JsonArray();
		
		jsonBody.add(ResponseBodyUtility.REPORT_ID_KEY, gson.toJsonTree(process.getPid()));
		jsonBody.add(ResponseBodyUtility.NAME_KEY, gson.toJsonTree(process.getName()));
		jsonBody.add(ResponseBodyUtility.START_DATE_KEY, gson.toJsonTree(process.getStartDate()));
		jsonBody.add(ResponseBodyUtility.END_DATE_KEY, gson.toJsonTree(process.getEndDate()));

		Report report = process.generateReport();
		jsonBody.add(ResponseBodyUtility.RIDES_KEY, gson.toJsonTree(report.getSize()));
		Iterator<ReportDetail> iterator = report.getReportDetails();
		while(iterator.hasNext()) {
			ReportDetail reportDetail = iterator.next();
			JsonObject jsonObject = new JsonObject();
			jsonObject.add(ResponseBodyUtility.FROM_ZIP_KEY, gson.toJsonTree(reportDetail.getFromZip()));
			jsonObject.add(ResponseBodyUtility.TO_ZIP_KEY, gson.toJsonTree(reportDetail.getToZip()));
			jsonObject.add(ResponseBodyUtility.COUNT_KEY, gson.toJsonTree(reportDetail.getCount()));
			detail.add(jsonObject);
		}
		jsonBody.add(ResponseBodyUtility.DETAIL_KEY, detail);
		return jsonBody.toString();
	}
}
