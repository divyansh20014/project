import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import org.json.simple.parser.JSONParser;
import org.json.JSONArray;

public class TransactionProcessor {

    public void process(Exchange exchange) throws Exception 
	{

        // Retrieve the incoming request body as a string
        String responseBody = exchange.getIn().getBody(String.class);
	System.out.println("response body is :" +responseBody);
        // Parse the JSON string
       

        // Generate random values
        String CustomerId = responseBody.substring(665,695).trim();
        String ChannelRef = responseBody.substring(695,725).trim();
        String randomEfmsResponse = responseBody.substring(635,665).trim();
		String HoldTime= responseBody.substring(602,610).trim();
	String jsonResponse="";
	if(ChannelRef!=""){
		
		JSONObject dataJson = new JSONObject();
		dataJson.put("EFMSChannelReferenceNumber",ChannelRef);
		dataJson.put("CustomerId", CustomerId);
		dataJson.put("EFMSResponse", randomEfmsResponse);

		JSONObject resultJson = new JSONObject();
		resultJson.put("Data", dataJson);

		// Convert the result to a string and return it
		jsonResponse = resultJson.toString();
		
		
	}
	else{
	
	 if(randomEfmsResponse=="HOLD")
	 {
	 if(HoldTime!=""){
	 JSONObject dataJson = new JSONObject();
		dataJson.put("CustomerId", CustomerId);
		dataJson.put("EFMSResponse", randomEfmsResponse);
		dataJson.put("HoldPeriod", HoldTime);

		JSONObject resultJson = new JSONObject();
		resultJson.put("Data", dataJson);

		// Convert the result to a string and return it
		jsonResponse = resultJson.toString();	 
	 }
	 else{
	 JSONObject dataJson = new JSONObject();
		dataJson.put("CustomerId", CustomerId);
		dataJson.put("EFMSResponse", randomEfmsResponse);
		dataJson.put("HoldPeriod", "360");

		JSONObject resultJson = new JSONObject();
		resultJson.put("Data", dataJson);

		// Convert the result to a string and return it
		jsonResponse = resultJson.toString();	 
	 }
	 }
	 else{
	 JSONObject dataJson = new JSONObject();
		dataJson.put("CustomerId", CustomerId);
		dataJson.put("EFMSResponse", randomEfmsResponse);

		JSONObject resultJson = new JSONObject();
		resultJson.put("Data", dataJson);

		// Convert the result to a string and return it
		jsonResponse = resultJson.toString();
	 }
}
        // Set the JSON response as the exchange body
        exchange.getIn().setBody(jsonResponse);
    }

}
