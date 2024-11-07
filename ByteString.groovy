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




    public static byte[] getTransactionBytes(Transaction txn) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Write the length of the transaction (if needed, use a method to write the length)
        LengthCodec lc = LengthCodec.PREPEND2;
        lc.writeLength(byteArrayOutputStream, txn.getLength());

        // Iterate over each segment and write the bytes to the output stream
        for (SegmentType segmentType : SegmentType.values()) {
            byte[] segmentBytes = txn.getSegment(segmentType);
            if (segmentBytes != null) {
                byteArrayOutputStream.write(segmentBytes);
            }
        }

        return byteArrayOutputStream.toByteArray();
    }

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.nio.ByteBuffer;
 
private static ByteBuf convertTransactionToByteBuf(Transaction txn, LengthCodec lc) throws Exception {
    // Calculate the total transaction length and allocate a ByteBuf
    int txnLength = txn.getLength();
    ByteBuf byteBuf = Unpooled.buffer(txnLength + lc.getEncodedLength(txnLength)); // Add extra space for length prefix
 
    // Write the length prefix using LengthCodec
    ByteBuffer lengthBuffer = ByteBuffer.allocate(lc.getEncodedLength(txnLength));
    lc.writeLength(lengthBuffer, txnLength);
    byteBuf.writeBytes(lengthBuffer.array());
 
    // Write each segment into the ByteBuf
    for (SegmentType type : SegmentType.values()) {
        byte[] bytes = txn.getSegment(type);
        if (bytes != null) {
            byteBuf.writeBytes(bytes);
        }
    }
 
    return byteBuf;
}

 public static ByteBuf convertTransactionToByteBuf(Transaction txn) throws Exception {
        // Aggregate all segments into a single byte array
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        for (SegmentType type : SegmentType.values()) {
            byte[] segmentBytes = txn.getSegment(type);

            if (segmentBytes != null) {
                byteArrayOutputStream.write(segmentBytes);
            }
        }

        // Convert the aggregated byte array into a ByteBuf
        byte[] transactionBytes = byteArrayOutputStream.toByteArray();
        return Unpooled.wrappedBuffer(transactionBytes);
    }
public static ByteBuf convertTransactionToByteBuf(Transaction txn) throws Exception {
    // Aggregate all segments into a single byte array
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    for (SegmentType type : SegmentType.values()) {
        byte[] segmentBytes = txn.getSegment(type);

        if (segmentBytes != null) {
            // Convert segment bytes to a string to filter non-printable characters
            StringBuilder filteredSegment = new StringBuilder();
            for (byte b : segmentBytes) {
                int character = b & 0xFF; // Convert byte to unsigned int
                filteredSegment.append((char) replaceNonPrintableCharacterByWhitespace(character));
            }

            // Convert the filtered string back to bytes in UTF-8
            byte[] filteredBytes = filteredSegment.toString().getBytes("UTF-8");
            byteArrayOutputStream.write(filteredBytes);
        }
    }

    // Convert the aggregated byte array into a ByteBuf
    byte[] transactionBytes = byteArrayOutputStream.toByteArray();
    return Unpooled.wrappedBuffer(transactionBytes);
}

private static final int WS = 32; // ASCII for space

private static int replaceNonPrintableCharacterByWhitespace(int character) {
    // Add your non-printable character list or use the provided one
    final char[] NON_PRINTABLE_EBCDIC_CHARS = new char[] {
       0x00, 0x01, 0x02, 0x03, 0x9C, 0x09, 0x86, 0x7F, 0x97, 0x8D, 0x8E, 
        0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x10, 0x11, 0x12, 0x13, 0x9D, 0x85, 0x08, 0x87, 0x18, 0x19, 0x92, 0x8F, 0x1C, 0x1D, 0x1E, 0x1F, 0x80, 
        0x81, 0x82, 0x83, 0x84, 0x0A, 0x17, 0x1B, 0x88, 0x89, 0x8A, 0x8B, 0x8C, 0x05, 0x06, 0x07, 0x90, 0x91, 0x16, 0x93, 0x94, 0x95, 0x96, 
        0x04, 0x98, 0x99, 0x9A, 0x9B, 0x14, 0x15, 0x9E, 0x1A, 0x20, 0xA0 ,0xFE, 0xFF, 0xFFFD
        // Add additional non-printable characters as needed
    };

    for (char nonPrintableChar : NON_PRINTABLE_EBCDIC_CHARS) {
        if (nonPrintableChar == (char) character) {
            return WS; // Replace with whitespace
        }
    }
    return character; // Return original if printable
}
