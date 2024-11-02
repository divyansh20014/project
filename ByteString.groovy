import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.camel.Exchange;
import org.json.JSONObject;

public class StringToByteBufProcessor{
    
	private String transactionId;
    private String transactionType;
	
    public void stringToByte(Exchange exchange) throws Exception {
        // Extract the request body as a JSON string
        String requestBody = exchange.getIn().getBody(String.class);

        // Parse the JSON string
        JSONObject jsonObject = new JSONObject(requestBody);

        // Extract transaction_type and transaction_id from the parsed JSON
        transactionType = jsonObject.getString("transaction_type");
        transactionId = jsonObject.getString("transaction_id");

        // Extract the transaction_fixedlength
        String transactionFixedLength = jsonObject.getString("transaction_fixedlength");

        // Convert the fixed-length string to ByteBuf
        ByteBuf byteBuf = Unpooled.copiedBuffer(transactionFixedLength, java.nio.charset.StandardCharsets.UTF_8);
        exchange.getIn().setBody(byteBuf);
    }
	
	public void byteToString(Exchange exchange) throws Exception {
        ByteBuf byteBuf = exchange.getIn().getBody(ByteBuf.class);
        byte[] byteArray = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(byteArray);

        // Convert to a response JSON format
        String responseString = String.format(
            "{\"transaction_id\": \"%s\", \"transaction_type\": \"%s\", \"response_fixedlength\": \"%s\"}",
            transactionId, transactionType, new String(byteArray, java.nio.charset.StandardCharsets.UTF_8)
        );

        exchange.getIn().setBody(responseString);
        byteBuf.release(); // Release ByteBuf to prevent memory leaks
    }
}





