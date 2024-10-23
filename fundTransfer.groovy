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
        String aqo_acct_num = " ", rua_8byte_string_002 = " ", tpp_acct_type = " ", rqo_tran_time = " ", rqo_tran_date = " ", smh_authenticate_mtd = " ", tpp_acct_num = " ", rua_20byte_string_004 = " ", tpp_bank_cntry_code = " ", rua_10byte_string_005 = " ", tpp_bank_name = " ", tpp_cntry_code = " ", tpp_num = " ", rua_10byte_string_004 = " ", tpp_name = " ", rua_10byte_string_003 = " ", rua_8byte_string_001 = " ", hqo_limit_amt = " ", smh_cust_type = " ", xqo_cust_type = " ", aqd_avail_bal = " ", hob_ip_cntry_code = " ", hqo_device_id = " ", hqo_msg_type = " ", hqo_device_id_type = " ", xqo_emp_flg = " ", hob_ip_address = " ", rua_2byte_string_002 = " ", hob_ip_isp = " ", tbt_tran_amt = " ", tbt_billing_amt = " ", tbt_tran_curr_code = " ", tbt_description = " ", rua_30byte_string_001 = " ", tbt_tran_status = " ", tbt_tran_type = " ", rua_20byte_string_005 = " ", xqo_cust_num = " ", tbt_ref_num = " ", rua_10byte_string_002 = " ", rua_2byte_string_001 = " ";

        // Parse the incoming JSON
        JSONParser parser = new JSONParser();
        try {
            if (textJson != null && !textJson.isEmpty()) {
                JSONObject jsonObject = (JSONObject) parser.parse(textJson);

                // Direct mapping between ADCB fields and SAS variables
                if (jsonObject.get("accountNumber") != null)
                    aqo_acct_num = jsonObject.get("accountNumber").toString();
                if (jsonObject.get("orgName") != null)
                    tpp_acct_type = jsonObject.get("orgName").toString();
                if (jsonObject.get("reqTimeStamp") != null) {
                    rqo_tran_time = jsonObject.get("reqTimeStamp").toString();
                    rqo_tran_date = jsonObject.get("reqTimeStamp").toString();
                }
                if (jsonObject.get("authenticationMethod") != null)
                    smh_authenticate_mtd = jsonObject.get("authenticationMethod").toString();
                if (jsonObject.get("benefAccounNumber") != null)
                    tpp_acct_num = jsonObject.get("benefAccounNumber").toString();
                if (jsonObject.get("benefCountry") != null) {
                    tpp_bank_cntry_code = jsonObject.get("benefCountry").toString();
                    tpp_cntry_code = jsonObject.get("benefCountry").toString();
                }
                if (jsonObject.get("benefBankName") != null)
                    tpp_bank_name = jsonObject.get("benefBankName").toString();
                if (jsonObject.get("benefID") != null)
                    tpp_num = jsonObject.get("benefID").toString();
                if (jsonObject.get("benefName") != null)
                    tpp_name = jsonObject.get("benefName").toString();
                if (jsonObject.get("beneficiaryType") != null)
                    rua_8byte_string_001 = jsonObject.get("beneficiaryType").toString();
                if (jsonObject.get("channelDailyLimit") != null)
                    hqo_limit_amt = jsonObject.get("channelDailyLimit").toString();
                if (jsonObject.get("casaBalance") != null)
                    aqd_avail_bal = jsonObject.get("casaBalance").toString();
                if (jsonObject.get("CarrierCountry") != null)
                    hob_ip_cntry_code = jsonObject.get("CarrierCountry").toString();
                if (jsonObject.get("deviceIdValue") != null)
                    hqo_device_id = jsonObject.get("deviceIdValue").toString();
                if (jsonObject.get("deviceName") != null)
                    hqo_msg_type = jsonObject.get("deviceName").toString();
                if (jsonObject.get("platform") != null)
                    hqo_device_id_type = jsonObject.get("platform").toString();
                if (jsonObject.get("isStaff") != null)
                    xqo_emp_flg = jsonObject.get("isStaff").toString();
                if (jsonObject.get("clientIPAddress") != null)
                    hob_ip_address = jsonObject.get("clientIPAddress").toString();
                if (jsonObject.get("amountTransactionCurrency") != null)
                    tbt_tran_amt = jsonObject.get("amountTransactionCurrency").toString();
                if (jsonObject.get("amountLocalCurrency") != null)
                    tbt_billing_amt = jsonObject.get("amountLocalCurrency").toString();
                if (jsonObject.get("transactionCurrencyCode") != null)
                    tbt_tran_curr_code = jsonObject.get("transactionCurrencyCode").toString();
                if (jsonObject.get("transactionDescription") != null)
                    tbt_description = jsonObject.get("transactionDescription").toString();
                if (jsonObject.get("action") != null)
                    tbt_tran_type = jsonObject.get("action").toString();
                if (jsonObject.get("customerID") != null)
                    xqo_cust_num = jsonObject.get("customerID").toString();
                if (jsonObject.get("sysRefNumber") != null)
                    tbt_ref_num = jsonObject.get("sysRefNumber").toString();

                // Constructing the CSV string using the SAS variables
                String CSVString = aqo_acct_num + "," + rua_8byte_string_002 + "," + tpp_acct_type + "," + rqo_tran_time + "," + rqo_tran_date + "," + smh_authenticate_mtd + "," + tpp_acct_num + "," + tpp_bank_cntry_code + "," + tpp_bank_name + "," + tpp_cntry_code + "," + tpp_num + "," + tpp_name + "," + rua_8byte_string_001 + "," + hqo_limit_amt + "," + smh_cust_type + "," + aqd_avail_bal + "," + hob_ip_cntry_code + "," + hqo_device_id + "," + hqo_msg_type + "," + hqo_device_id_type + "," + xqo_emp_flg + "," + hob_ip_address + "," + tbt_tran_amt + "," + tbt_billing_amt + "," + tbt_tran_curr_code + "," + tbt_description + "," + tbt_tran_type + "," + xqo_cust_num + "," + tbt_ref_num;

                return CSVString;
            } else {
                System.out.println("JSON data is empty or null.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
	
