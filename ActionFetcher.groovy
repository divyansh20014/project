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


String textJson = exchange.getIn().getBody(String.class);

// Only proceed if the incoming JSON is not empty
if (textJson != null && !textJson.isEmpty()) {
    // Parse the JSON string
    JSONObject jsonObject1 = new JSONObject(textJson);
    JSONObject jsonObject = jsonObject1.getJSONObject("Data");

    // Map the fields from the JSON to the class fields
    EFMSChannelReferenceNumber = jsonObject.optString("EFMSChannelReferenceNumber", "");
    ChannelCode = jsonObject.optString("ChannelCode", "");
    TransferType = jsonObject.optString("TransferType", "");
    ActionDateTime = jsonObject.optString("ActionDateTime", "");
    CustomerId = jsonObject.optString("CustomerId", "");
    AccountId = jsonObject.optString("AccountId", "");
    BeneficiaryAccountId = jsonObject.optString("BeneficiaryAccountId", "");
    BeneficiaryName = jsonObject.optString("BeneficiaryName", "");
    POSMode = jsonObject.optString("POSMode", "");
    MaskedCardNumber = jsonObject.optString("MaskedCardNumber", "");
    CardPresent = jsonObject.optString("CardPresent", "");
    MerchantId = jsonObject.optString("MerchantId", "");
    PaymentPurpose = jsonObject.optString("PaymentPurpose", "");
    MerchantCategoryCode = jsonObject.optString("MerchantCategoryCode", "");
    OnlineBankingUserID = jsonObject.optString("OnlineBankingUserID", "");

    // Handle the nested "TransactionAmountInTransactionCurrency" object
    if (jsonObject.optJSONObject("TransactionAmountInTransactionCurrency") != null) {
        TransactionAmount = jsonObject.optJSONObject("TransactionAmountInTransactionCurrency").optString("Amount", "");
        TransactionCurrency = jsonObject.optJSONObject("TransactionAmountInTransactionCurrency").optString("Currency", "");
    }
}
