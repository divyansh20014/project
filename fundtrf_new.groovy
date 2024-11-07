public class JsonMapping {
  
    public String AccountNumber = "", OrganizationName = "", ActualTransactionDateTime = "",
            AutheticationMethod = "", BeneficiaryAccountNumber = "", BeneficiaryBankCountryCode = "",
            BeneficiaryBankName = "", BeneficiaryCountry = "", BeneficiaryID = "", BeneficiaryName = "",
            BeneficiaryType = "", ChannelDailyLimit = "", CasaBalance = "", CarrierCountry = "",
            DeviceIdValue = "", DeviceName = "", Platform = "", IsStaff = "", ClientIPAddress = "",
            AmountInTransactionCurrency = "", AmountInAED = "", TransactionCurrencyCode = "",
            TransactionDescription = "", Action = "", CustomerID = "", tranDate = "", tranTime = "";

public void jsonToTransaction(Exchange exchange) {
    // Get the incoming JSON from the Camel exchange
    String textJson = exchange.getIn().getBody(String.class);

    // Only proceed if the incoming JSON is not empty
    if (textJson != null && !textJson.isEmpty()) {
        // Parse the JSON string
        JSONObject jsonObject = new JSONObject(textJson);

        // Map the fields from the JSON to the class fields
        AccountNumber = jsonObject.optString("AccountNumber", "");
        OrganizationName = jsonObject.optString("OrganizationName", "");
        ActualTransactionDateTime = jsonObject.optString("ActualTransactionDateTime", "");
        AutheticationMethod = jsonObject.optString("AutheticationMethod", "");
        BeneficiaryAccountNumber = jsonObject.optString("BeneficiaryAccountNumber", "");
        BeneficiaryBankCountryCode = jsonObject.optString("BeneficiaryBankCountryCode", "");
        BeneficiaryBankName = jsonObject.optString("BeneficiaryBankName", "");
        BeneficiaryCountry = jsonObject.optString("BeneficiaryCountry", "");
        BeneficiaryID = jsonObject.optString("BeneficiaryID", "");
        BeneficiaryName = jsonObject.optString("BeneficiaryName", "");
        BeneficiaryType = jsonObject.optString("BeneficiaryType", "");
        ChannelDailyLimit = jsonObject.optString("ChannelDailyLimit", "");
        CasaBalance = jsonObject.optString("CasaBalance", "");
        CarrierCountry = jsonObject.optString("CarrierCountry", "");
        DeviceIdValue = jsonObject.optString("DeviceIdValue", "");
        DeviceName = jsonObject.optString("DeviceName", "");
        Platform = jsonObject.optString("Platform", "");
        IsStaff = jsonObject.optString("IsStaff", "");
        ClientIPAddress = jsonObject.optString("ClientIPAddress", "");
        AmountInTransactionCurrency = jsonObject.optString("AmountInTransactionCurrency", "");
        AmountInAED = jsonObject.optString("AmountInAED", "");
        TransactionCurrencyCode = jsonObject.optString("TransactionCurrencyCode", "");
        TransactionDescription = jsonObject.optString("TransactionDescription", "");
        Action = jsonObject.optString("Action", "");
        CustomerID = jsonObject.optString("CustomerID", "");

        // Handle ActualTransactionDateTime and split it into date and time
        try {
            if (ActualTransactionDateTime != null && !ActualTransactionDateTime.isEmpty()) {
                // Parse the ActualTransactionDateTime field
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); // Input format
                Date date = sdf.parse(ActualTransactionDateTime);

                // Extract the date part
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                tranDate = dateFormat.format(date);

                // Extract the time part
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss.SSS");
                tranTime = timeFormat.format(date);

                System.out.println("Date: " + tranDate + ", Time: " + tranTime);
            }
        } catch (ParseException e) {
            System.out.println("Error parsing ActualTransactionDateTime: " + e.getMessage());
        }

        // Now map all the fields to the transaction object
        try {
            MessageAPI api = MessageAPI.getDefault();
            MessageApiEncoding apie = MessageApiEncoding.getDefault();
            LengthCodec lc = LengthCodec.PREPEND2;

            // Create transaction
            Transaction txn = new Transaction(apie);

            // Add segments to the transaction
        txn.addSegment(SegmentType.SMH);
		txn.addSegment(SegmentType.RRR);
		txn.addSegment(SegmentType.RQO);
		txn.addSegment(SegmentType.XQO);
		txn.addSegment(SegmentType.AQO);
		txn.addSegment(SegmentType.AQD);
		txn.addSegment(SegmentType.UNM);
		txn.addSegment(SegmentType.HQO);
		txn.addSegment(SegmentType.HOB);
		txn.addSegment(SegmentType.TBT);
		txn.addSegment(SegmentType.TPP);
		txn.addSegment(SegmentType.RUA);
		txn.addSegment(SegmentType.ROB);
		txn.addSegment(SegmentType.RDK);
		txn.addSegment(SegmentType.RUR);
		txn.addSegment(SegmentType.DUA);
		txn.addSegment(SegmentType.DEE);
		txn.addSegment(SegmentType.DNU);
		
    	// create fields
        Field smh_tran_type = api.getField("smh_tran_type");
        Field smh_resp_req = api.getField("smh_resp_req");
        Field smh_acct_type = api.getField("smh_acct_type");
        Field smh_activity_type = api.getField("smh_activity_type");
		Field smh_activity_detail1 = api.getField("smh_activity_detail1");
		Field smh_activity_detail2 = api.getField("smh_activity_detail2");
		Field smh_activity_detail3 = api.getField("smh_activity_detail3");
        Field smh_cust_type = api.getField("smh_cust_type");
       // Field smh_authenticate_mtd = api.getField("smh_authenticate_mtd");
        Field smh_channel_type = api.getField("smh_channel_type");
        Field smh_multi_org_name = api.getField("smh_multi_org_name");
        Field smh_client_tran_type = api.getField("smh_client_tran_type");


        
        Field tbt_tran_amt = api.getField("tbt_tran_amt");
		
		smh_tran_type.encodeText(txn,"TRX");
		smh_activity_detail1.encodeText(txn, "DEE");
		smh_activity_detail2.encodeText(txn, "DUA");
		smh_activity_detail3.encodeText(txn, "DNU");
		smh_cust_type.encodeText(txn, "I");
		smh_acct_type.encodeText(txn, "CS");
		//smh_authenticate_mtd.encodeText(txn, "NC");
		smh_channel_type.encodeText(txn, "O");
		smh_activity_type.encodeText(txn, "BF");
		smh_resp_req.encodeText(txn, "1");
		
		Field tpp_acct_num = api.getField("tpp_acct_num");
		tpp_acct_num.encodeText(txn, BeneficiaryAccountNumber);

		Field rqo_tran_time = api.getField("rqo_tran_time");
		rqo_tran_time.encodeText(txn, tranTime);

		Field rqo_tran_date = api.getField("rqo_tran_date");
		rqo_tran_date.encodeText(txn, tranDate);

		Field smh_authenticate_mtd = api.getField("smh_authenticate_mtd");
		smh_authenticate_mtd.encodeText(txn, AutheticationMethod);

		Field tpp_bank_cntry_code = api.getField("tpp_bank_cntry_code");
		tpp_bank_cntry_code.encodeText(txn, BeneficiaryBankCountryCode);

		Field tpp_bank_name = api.getField("tpp_bank_name");
		tpp_bank_name.encodeText(txn, BeneficiaryBankName);

		Field tpp_cntry_code = api.getField("tpp_cntry_code");
		tpp_cntry_code.encodeText(txn, BeneficiaryCountry);

		Field tpp_num = api.getField("tpp_num");
		tpp_num.encodeText(txn, BeneficiaryID);

		Field tpp_name = api.getField("tpp_name");
		tpp_name.encodeText(txn, BeneficiaryName);

		Field rua_8byte_string_001 = api.getField("rua_8byte_string_001");
		rua_8byte_string_001.encodeText(txn, BeneficiaryType);

		Field hqo_limit_amt = api.getField("hqo_limit_amt");
		hqo_limit_amt.encodeText(txn, ChannelDailyLimit);

		Field smh_cust_type_xqo_cust_type = api.getField("smh_cust_type/xqo_cust_type");
		smh_cust_type_xqo_cust_type.encodeText(txn, OrganizationName);

		Field aqd_avail_bal = api.getField("aqd_avail_bal");
		aqd_avail_bal.encodeText(txn, CasaBalance);

		Field hob_ip_cntry_code = api.getField("hob_ip_cntry_code");
		hob_ip_cntry_code.encodeText(txn, CarrierCountry);

		Field hqo_device_id = api.getField("hqo_device_id");
		hqo_device_id.encodeText(txn, DeviceIdValue);

		Field hqo_msg_type = api.getField("hqo_msg_type");
		hqo_msg_type.encodeText(txn, DeviceName);

		Field hqo_device_id_type = api.getField("hqo_device_id_type");
		hqo_device_id_type.encodeText(txn, Platform);

		Field xqo_emp_flg = api.getField("xqo_emp_flg");
		xqo_emp_flg.encodeText(txn, IsStaff);

		Field hob_ip_address = api.getField("hob_ip_address");
		hob_ip_address.encodeText(txn, ClientIPAddress);

		Field tbt_tran_amt = api.getField("tbt_tran_amt");
		tbt_tran_amt.encodeText(txn, AmountInTransactionCurrency);

		Field tbt_billing_amt = api.getField("tbt_billing_amt");
		tbt_billing_amt.encodeText(txn, AmountInAED);

		Field tbt_tran_curr_code = api.getField("tbt_tran_curr_code");
		tbt_tran_curr_code.encodeText(txn, TransactionCurrencyCode);

		Field tbt_description = api.getField("tbt_description");
		tbt_description.encodeText(txn, TransactionDescription);

		Field tbt_tran_status = api.getField("tbt_tran_status");
		tbt_tran_status.encodeText(txn, "");  // No mapping, empty string

		Field tbt_tran_type = api.getField("tbt_tran_type");
		tbt_tran_type.encodeText(txn, Action);

		Field xqo_cust_num = api.getField("xqo_cust_num");
		xqo_cust_num.encodeText(txn, CustomerID);

		Field tbt_ref_num = api.getField("tbt_ref_num");
		tbt_ref_num.encodeText(txn, "");  // No mapping, empty string

		Field rua_2byte_string_001 = api.getField("rua_2byte_string_001");
		rua_2byte_string_001.encodeText(txn, "");  // No mapping, empty string

            // Continue encoding other fields as per your logic
            // (same as your current field mappings for transaction data)

            // Convert the transaction to a string (fixed-length format) and print it
            String txnString = convertTransactionToString(txn);
            System.out.println("Transaction Fixed-Length Format:\n" + txnString);

        } catch (Exception e) {
            System.out.println("Error mapping JSON to Transaction: " + e.getMessage());
        }
    }

    // Helper method to convert the transaction into a fixed-length string representation
    private static String convertTransactionToString(Transaction txn) {
        StringBuilder txnBuilder = new StringBuilder();
        
        // Loop through each segment and append its byte data (as hex)
        for (SegmentType type : SegmentType.values()) {
            byte[] bytes = txn.getSegment(type);
            if (bytes != null) {
                txnBuilder.append(String.format("%s: %s\n", type.name(), bytesToHex(bytes)));
            }
        }
        return txnBuilder.toString();
    }

    // Helper method to convert byte array to hex string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
