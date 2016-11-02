package org.jasig.cas.authentication;

import java.io.Serializable;

/**
 * @author dushanp@hsenidmobile.com on 10/27/16
 * @since 4.0.0
 */
public class DefaultLinkedAccountToken implements LinkedAccountToken, Serializable {

    /**
     * The username.
     */
    private String username;

    /**
     * The password.
     */
    private String password;

    /**
     * The linked account username.
     */
    private String linkedAccountUsername;

    /**
     * No-arg constructor for serialization support.
     */
    public DefaultLinkedAccountToken() {
    }

    /**
     * Instantiates new instant.
     *
     * @param username              the username
     * @param password              the password
     * @param linkedAccountUsername linked account username
     */
    public DefaultLinkedAccountToken(final String username, final String password, final String linkedAccountUsername) {
        this.username = username;
        this.password = password;
        this.linkedAccountUsername = linkedAccountUsername;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public void setUsername(final String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getLinkedUsername() {
        return this.linkedAccountUsername;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String toString() {
        return username + "|" + password + "|" + linkedAccountUsername;
    }
}
