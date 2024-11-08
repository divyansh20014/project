import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;

public class ActionExtractorProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        // Get the body, assuming it's a raw JSON string
        String jsonBody = exchange.getIn().getBody(String.class);
        
        try {
            // Convert the JSON string into a JSONObject
            JSONObject jsonObject = new JSONObject(jsonBody);
            
            // Extract the 'Action' field from the 'Data' object
            JSONObject dataObject = jsonObject.getJSONObject("Data");
            String action = dataObject.getString("Action");
            
            // Set the 'Action' field in the header
            exchange.getIn().setHeader("Action", action);
            
        } catch (Exception e) {
            // Handle errors like missing fields, JSON parsing issues
            exchange.getIn().setHeader("Action", null);
            System.out.println("Error extracting Action field: " + e.getMessage());
        }
    }
}
