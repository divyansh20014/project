import org.apache.camel.Exchange;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.log4j.Logger

import java.util.*;
import java.io.*;

public class error_handle {
 
    public String errorMessageJson (Exchange exchange) {
	
        String errorJson = exchange.getIn().getBody(String.class);
		String additionalProp = exchange.getProperty("errorContent", String);
		//System.out.println("-----Error Message JSON -----");
		//System.out.println(errorJson);
		
		String logMessage = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class)?.getMessage();
		String errorMessage = getErrorMessage(logMessage);
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = new JSONObject();

		try {
			JSONArray errorsArray = new JSONArray();
			JSONObject errorObject = new JSONObject();
			if(errorMessage.equals("5018")) {
				errorObject.put("code", "80");
				errorObject.put("type", "error");
				errorObject.put("message", "connection refused");
				errorObject.put("additionalProp1", additionalProp);
			}
			else {
				errorObject.put("code", "90");
				errorObject.put("type", "error");
				errorObject.put("message", errorMessage);
				errorObject.put("additionalProp1", " ");
			}
			
			errorsArray.add(errorObject);
			
			jsonObject.put("errors", errorsArray);
			
			//System.out.println("--------- Error JSON String Form ------------");
			//System.out.println(jsonObject.toJSONString());
			
		} catch (Exception e) {
			System.out.println("Error while constructing error message JSON: " + e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	
	private String getErrorMessage(String logMessage) {
        // Extract only the error message without the exception details
        int colonIndex = logMessage.indexOf(":");
        if (colonIndex != -1) {
            return logMessage.substring(colonIndex + 1).trim();
        } else {
            return logMessage.trim();
        }
    }
}
				