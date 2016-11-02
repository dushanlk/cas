package org.jasig.cas.authentication;

/**
 * @author dushanp@hsenidmobile.com on 10/27/16
 * @since 4.0.0
 */
public interface LinkedAccountToken {

    /**
     * Get username.
     *
     * @return the username
     */
    String getUsername();

    /**
     * Get password.
     *
     * @return the password
     */
    String getPassword();

    /**
     * Get linked account username.
     *
     * @return the linked account username.
     */
    String getLinkedUsername();

    /**
     * Get token created time in millis.
     *
     * @return time in millis
     */
    long getTokenCreatedTime();

    /**
     * Validate token.
     *
     * @return true or false
     */
    boolean isTokenExpired();

    /**
     * Convert to String.
     *
     * @return String value of token
     */
    String toString();
}
