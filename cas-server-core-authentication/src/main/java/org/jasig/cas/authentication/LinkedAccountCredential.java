package org.jasig.cas.authentication;

import java.io.Serializable;

/**
 * Credential for login using linked username.
 *
 * @author dushanp@hsenidmobile.com
 * @since 3.0.0
 */
public class LinkedAccountCredential implements Serializable {

    private String initialAuthToken;

    private String linkedUsername;

    private LinkedAccountToken extractedToken;

    /**
     * Initialing.
     */
    public LinkedAccountCredential() {
    }

    /**
     * Initializing.
     *
     * @param initialAuthToken token.
     * @param linkedUsername   username.
     */
    public LinkedAccountCredential(final String initialAuthToken, final String linkedUsername) {
        this.initialAuthToken = initialAuthToken;
        this.linkedUsername = linkedUsername;
        this.extractedToken = new DefaultLinkedAccountTokenGenerator().read(this.initialAuthToken);
    }

    public String getInitialAuthToken() {
        return initialAuthToken;
    }

    /**
     * Set initial auth token and extract.
     *
     * @param initialAuthToken initial auth token
     */
    public void setInitialAuthToken(final String initialAuthToken) {
        this.initialAuthToken = initialAuthToken;
        this.extractedToken = new DefaultLinkedAccountTokenGenerator().read(this.initialAuthToken);
    }

    public String getLinkedUsername() {
        return linkedUsername;
    }

    public void setLinkedUsername(final String linkedUsername) {
        this.linkedUsername = linkedUsername;
    }

    public LinkedAccountToken getExtractedToken() {
        return extractedToken;
    }

}
