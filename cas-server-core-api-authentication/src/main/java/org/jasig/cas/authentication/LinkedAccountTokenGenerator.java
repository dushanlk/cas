package org.jasig.cas.authentication;

/**
 * @author dushanp@hsenidmobile.com on 10/27/16
 * @since 4.0.0
 */
public interface LinkedAccountTokenGenerator {

    /**
     * Create token.
     * @param token LinkedAccountToken
     * @return the token
     */
    String create(LinkedAccountToken token);

    /**
     * Extract token.
     * @param token String
     * @return LinkedAccountToken
     */
    LinkedAccountToken read(String token);
}
