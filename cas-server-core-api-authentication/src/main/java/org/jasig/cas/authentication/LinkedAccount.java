package org.jasig.cas.authentication;

import java.io.Serializable;

/**
 * @author dushanp@hsenidmobile.com on 10/27/16
 * @since 4.0.0
 */
public interface LinkedAccount extends Serializable {

    /**
     * Get linked account ID.
     *
     * @return the linked account ID
     */
    int getId();

    /**
     * Get linked username.
     *
     * @return the linked username
     */
    String getLinkedUsername();

    /**
     * Get initial auth token.
     *
     * @return the initial auth token
     * */
    String getInitialAuthToken();

}
