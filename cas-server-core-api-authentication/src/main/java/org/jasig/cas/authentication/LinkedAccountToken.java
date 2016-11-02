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
     * Set username.
     *
     * @param username username
     */
    void setUsername(String username);

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
     * Validate token.
     *
     * @return true or false
     */
    boolean isValid();

    /**
     * Convert to String.
     *
     * @return String value of token
     */
    String toString();
}
