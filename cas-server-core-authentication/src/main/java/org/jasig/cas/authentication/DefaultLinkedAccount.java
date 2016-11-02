package org.jasig.cas.authentication;

import org.joda.time.DateTime;

/**
 * @author dushanp@hsenidmobile.com on 10/27/16
 * @since 4.0.0
 */
public class DefaultLinkedAccount implements LinkedAccount {

    /**
     * The linked account ID.
     */
    private int id;

    /**
     * The linked account username.
     */
    private String linkedUsername;

    /**
     * The initial auth token.
     */
    private String initialAuthToken;

    /**
     * No-arg constructor for serialization support.
     */
    public DefaultLinkedAccount() {
    }

    /**
     * Initialize default linked account.
     *
     * @param id             account id
     * @param linkedUsername linked account username
     * @param credential     credential
     */
    public DefaultLinkedAccount(final int id, final String linkedUsername, final UsernamePasswordCredential credential) {
        final DefaultLinkedAccountToken token = new DefaultLinkedAccountToken(
                credential.getUsername(), credential.getPassword(), linkedUsername, new DateTime().getMillis());
        this.id = id;
        this.linkedUsername = linkedUsername;
        this.initialAuthToken = new DefaultLinkedAccountTokenGenerator().create(token);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getLinkedUsername() {
        return linkedUsername;
    }

    @Override
    public String getInitialAuthToken() {
        return initialAuthToken;
    }
}
