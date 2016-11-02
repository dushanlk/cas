package org.jasig.cas.authentication;

import org.joda.time.DateTime;

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
     * Token created time in millis.
     */
    private long tokenCreatedTime;

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
     * @param tokenCreatedTime      token created time in millis
     */
    public DefaultLinkedAccountToken(final String username, final String password, final String linkedAccountUsername,
                                     final long tokenCreatedTime) {
        this.username = username;
        this.password = password;
        this.linkedAccountUsername = linkedAccountUsername;
        this.tokenCreatedTime = tokenCreatedTime;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getLinkedUsername() {
        return linkedAccountUsername;
    }

    @Override
    public long getTokenCreatedTime() {
        return tokenCreatedTime;
    }

    @Override
    public boolean isTokenExpired() {
        //This token will consider as expired after 5 minutes (300000 milliseconds)
        final int tokenTimeoutInMillis = 300000;
        return new DateTime().getMillis() - tokenCreatedTime > tokenTimeoutInMillis;
    }

    @Override
    public String toString() {
        return username + "|" + password + "|" + linkedAccountUsername + "|" + tokenCreatedTime;
    }
}
