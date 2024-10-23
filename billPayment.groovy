import org.apache.camel.Exchange;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import java.util.*;
import java.text.SimpleDateFormat;

public class sample_json {
    public String jsonMapping (Exchange exchange) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new java.util.Date());
        System.out.println("Message received from ADCB : " + timestamp);
        String textJson = exchange.getIn().getBody(String.class);
        System.out.println("JSON Payload : " + textJson);

        // Declare the variables for SAS fields
        String smh_channel_type = " ", smh_channel_name = " ", tbt_tran_type = " ", rua_20byte_string_005 = " ", rqo_tran_date = " ", rqo_tran_time = " ", xqo_cust_num = " ", aqo_acct_num = " ", rua_8byte_string_002 = " ", hqo_limit_amt = " ", aqd_avail_bal = " ", tbt_tran_status = " ", tbt_billing_amt = " ", tbt_tran_amt = " ", tbt_tran_curr_code = " ", hqo_device_id = " ", hob_ip_cntry_code = " ", hob_ip_address = " ", tbt_ref_num = " ", rua_10byte_string_002 = " ", rua_8byte_string_001 = " ", tpp_bank_cntry_code = " ", rua_10byte_string_005 = " ", tpp_acct_num = " ", rua_20byte_string_004 = " ", smh_authenticate_mtd = " ", rua_20byte_string_003 = " ", hob_ip_isp = " ", rua_8byte_string_003 = " ", hqo_card_num = " ", hqo_device_id_type = " ", hqo_msg_type = " ", rua_20byte_string_001 = " ", rua_20byte_string_002 = " ", rur_8byte_string_001 = " ", rua_2byte_string_001 = " ", smh_cust_type = " ", xqo_cust_type = " ", tbt_description = " ", rua_30byte_string_001 = " ", tpp_name = " ", rua_10byte_string_003 = " ", tpp_num = " ", rua_10byte_string_004 = " ", tpp_acct_type = " ", tpp_bank_name = " ", rua_2byte_string_003 = " ";

        // Parse the incoming JSON
        JSONParser parser = new JSONParser();
        try {
            if (textJson != null && !textJson.isEmpty()) {
                JSONObject jsonObject = (JSONObject) parser.parse(textJson);

                // Direct mapping between ADCB fields and SAS variables
                if (jsonObject.get("channel") != null)
                    smh_channel_type = jsonObject.get("channel").toString();
                if (jsonObject.get("channel") != null)
                    smh_channel_name = jsonObject.get("channel").toString();
                if (jsonObject.get("action") != null)
                    tbt_tran_type = jsonObject.get("action").toString();
                if (jsonObject.get("reqTimeStamp") != null) {
                    rqo_tran_date = jsonObject.get("reqTimeStamp").toString();
                    rqo_tran_time = jsonObject.get("reqTimeStamp").toString();
                }
                if (jsonObject.get("customerID") != null)
                    xqo_cust_num = jsonObject.get("customerID").toString();
                if (jsonObject.get("accountNumber") != null)
                    aqo_acct_num = jsonObject.get("accountNumber").toString();
                if (jsonObject.get("channelDailyLimit") != null)
                    hqo_limit_amt = jsonObject.get("channelDailyLimit").toString();
                if (jsonObject.get("casaBalance") != null)
                    aqd_avail_bal = jsonObject.get("casaBalance").toString();
                if (jsonObject.get("POST") != null)
                    tbt_tran_status = jsonObject.get("POST").toString();
                if (jsonObject.get("amountLocalCurrency") != null)
                    tbt_billing_amt = jsonObject.get("amountLocalCurrency").toString();
                if (jsonObject.get("amountTransactionCurrency") != null)
                    tbt_tran_amt = jsonObject.get("amountTransactionCurrency").toString();
                if (jsonObject.get("transactionCurrencyCode") != null)
                    tbt_tran_curr_code = jsonObject.get("transactionCurrencyCode").toString();
                if (jsonObject.get("deviceIdValue") != null)
                    hqo_device_id = jsonObject.get("deviceIdValue").toString();
                if (jsonObject.get("CarrierCountry") != null)
                    hob_ip_cntry_code = jsonObject.get("CarrierCountry").toString();
                if (jsonObject.get("clientIPAddress") != null)
                    hob_ip_address = jsonObject.get("clientIPAddress").toString();
                if (jsonObject.get("sysRefNumber") != null) {
                    tbt_ref_num = jsonObject.get("sysRefNumber").toString();
                    rua_10byte_string_002 = jsonObject.get("sysRefNumber").toString();
                }
                if (jsonObject.get("beneficiaryType") != null)
                    rua_8byte_string_001 = jsonObject.get("beneficiaryType").toString();
                if (jsonObject.get("benefCountry") != null) {
                    tpp_bank_cntry_code = jsonObject.get("benefCountry").toString();
                    rua_10byte_string_005 = jsonObject.get("benefCountry").toString();
                }
                if (jsonObject.get("benefAccounNumber") != null) {
                    tpp_acct_num = jsonObject.get("benefAccounNumber").toString();
                    rua_20byte_string_004 = jsonObject.get("benefAccounNumber").toString();
                }
                if (jsonObject.get("authenticationMethod") != null)
                    smh_authenticate_mtd = jsonObject.get("authenticationMethod").toString();
                if (jsonObject.get("merchantCategoryCode") != null)
                    rua_20byte_string_003 = jsonObject.get("merchantCategoryCode").toString();
                if (jsonObject.get("CardStatus") != null)
                    rua_8byte_string_003 = jsonObject.get("CardStatus").toString();
                if (jsonObject.get("cardNumber") != null)
                    hqo_card_num = jsonObject.get("cardNumber").toString();
                if (jsonObject.get("platform") != null)
                    hqo_device_id_type = jsonObject.get("platform").toString();
                if (jsonObject.get("deviceName") != null)
                    hqo_msg_type = jsonObject.get("deviceName").toString();
                if (jsonObject.get("merchantID") != null)
                    rua_20byte_string_001 = jsonObject.get("merchantID").toString();
                if (jsonObject.get("merchantName") != null)
                    rua_20byte_string_002 = jsonObject.get("merchantName").toString();
                if (jsonObject.get("creditCardLimit") != null)
                    rur_8byte_string_001 = jsonObject.get("creditCardLimit").toString();
                if (jsonObject.get("orgName") != null) {
                    smh_cust_type = jsonObject.get("orgName").toString();
                    xqo_cust_type = jsonObject.get("orgName").toString();
                }
                if (jsonObject.get("transactionDescription") != null) {
                    tbt_description = jsonObject.get("transactionDescription").toString();
                    rua_30byte_string_001 = jsonObject.get("transactionDescription").toString();
                }
                if (jsonObject.get("benefName") != null) {
                    tpp_name = jsonObject.get("benefName").toString();
                    rua_10byte_string_003 = jsonObject.get("benefName").toString();
                }
                if (jsonObject.get("benefID") != null) {
                    tpp_num = jsonObject.get("benefID").toString();
                    rua_10byte_string_004 = jsonObject.get("benefID").toString();
                }
                if (jsonObject.get("orgName") != null)
                    tpp_acct_type = jsonObject.get("orgName").toString();
                if (jsonObject.get("benefBankName") != null)
                    tpp_bank_name = jsonObject.get("benefBankName").toString();

                // Constructing the CSV string using the SAS variables
                String CSVString = smh_channel_type + "," + smh_channel_name + "," + tbt_tran_type + "," + rqo_tran_date + "," + rqo_tran_time + "," + xqo_cust_num + "," + aqo_acct_num + "," + hqo_limit_amt + "," + aqd_avail_bal + "," + tbt_tran_status + "," + tbt_billing_amt + "," + tbt_tran_amt + "," + tbt_tran_curr_code + "," + hqo_device_id + "," + hob_ip_cntry_code + "," + hob_ip_address + "," + tbt_ref_num + "," + rua_10byte_string_002 + "," + rua_8byte_string_001 + "," + tpp_bank_cntry_code + "," + rua_10byte_string_005 + "," + tpp_acct_num + "," + rua_20byte_string_004 + "," + smh_authenticate_mtd + "," + rua_20byte_string_003 + "," + rua_8byte_string_003 + "," + hqo_card_num + "," + hqo_device_id_type + "," + hqo_msg_type + "," + rua_20byte_string_001 + "," + rua_20byte_string_002 + "," + rur_8byte_string_001 + "," + smh_cust_type + "," + xqo_cust_type + "," + tbt_description + "," + rua_30byte_string_001 + "," + tpp_name + "," + rua_10byte_string_003 + "," + tpp_num + "," + rua_10byte_string_004 + "," + tpp_acct_type + "," + tpp_bank_name;
                System.out.println("CSV String : " + CSVString);

                // Store CSV in the exchange body
                exchange.getIn().setBody(CSVString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "JSON Parsing and Mapping completed successfully.";
    }
}
