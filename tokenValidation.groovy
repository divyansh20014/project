import org.apache.camel.Exchange
import org.apache.camel.Processor

class TokenValidator implements Processor {
    void process(Exchange exchange) throws Exception {
        // Extract the Authorization header from the incoming request
        String authHeader = exchange.getIn().getHeader("Authorization", String.class)

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // Remove "Bearer " prefix to get the actual token
            String token = authHeader.substring(7)

            // Check if the token is valid and not expired
            if (isValidToken(token)) {
                return  // Token is valid, proceed with the request
            }
        }

        // Token is either missing, invalid, or expired
        throw new Exception("Invalid or expired token")
    }

    // Method to check if the token is valid and not expired
    private boolean isValidToken(String token) {
        Long expirationTime = AuthTokenProcessor.tokenStore.get(token)  // Fetch the token's expiration time from the store

        // Check if the token exists and if it's still valid (not expired)
        if (expirationTime != null && expirationTime > System.currentTimeMillis()) {
            return true  // Token is valid
        }
        return false  // Token is invalid or has expired
    }
}
