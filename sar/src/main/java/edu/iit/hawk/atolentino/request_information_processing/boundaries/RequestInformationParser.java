package edu.iit.hawk.atolentino.request_information_processing.boundaries;

import com.google.gson.JsonSyntaxException;

public interface RequestInformationParser {
	public static final String MISSING_ELEMENTS_MESSAGE = "Contains missing element(s).";
	public static final String JSON_SYNTAX_MESSAGE = "Invalid request format.";
	public RequestInformation parse(String body) throws NullPointerException, JsonSyntaxException;
}
