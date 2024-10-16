import org.apache.camel.Exchange
import org.apache.camel.Processor
import java.security.SecureRandom
import java.util.Base64
import java.util.concurrent.ConcurrentHashMap

class AuthTokenProcessor implements Processor {
    // Token store with expiration (in-memory map shared across processors)
    static Map<String, Long> tokenStore = new ConcurrentHashMap<>()
    static final long TOKEN_EXPIRY_TIME = 3600_000  // Token validity: 1 hour in milliseconds

    void process(Exchange exchange) throws Exception {
        String clientId = exchange.getIn().getHeader("client_id", String.class)
        String clientSecret = exchange.getIn().getHeader("client_secret", String.class)

        // Validate client credentials (hardcoded for example purposes)
        if ("validClientId".equals(clientId) && "validClientSecret".equals(clientSecret)) {
            // Generate a secure, long-format token
            String authToken = generateSecureToken()

            // Store the token along with its expiration timestamp
            long expirationTime = System.currentTimeMillis() + TOKEN_EXPIRY_TIME
            tokenStore.put(authToken, expirationTime)  // Store the token and its expiration

            // Set the token in the response
            exchange.getIn().setHeader("authToken", authToken)
        } else {
            throw new Exception("Invalid client credentials")
        }
    }

    // Method to generate a secure, long access token (Base64 encoded random bytes)
    private String generateSecureToken() {
        SecureRandom random = new SecureRandom()
        byte[] tokenBytes = new byte[32]  // 256-bit token
        random.nextBytes(tokenBytes)
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes)
    }
}
