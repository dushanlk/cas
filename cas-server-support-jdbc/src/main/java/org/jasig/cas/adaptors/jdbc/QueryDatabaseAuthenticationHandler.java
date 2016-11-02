package org.jasig.cas.adaptors.jdbc;

import org.apache.commons.lang3.StringUtils;
import org.jasig.cas.authentication.DefaultLinkedAccount;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.LinkedAccount;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.security.GeneralSecurityException;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that if provided a query that returns a password (parameter of query
 * must be username) will compare that password to a translated version of the
 * password provided by the user. If they match, then authentication succeeds.
 * Default password translator is plaintext translator.
 *
 * @author Scott Battaglia
 * @author Dmitriy Kopylenko
 * @author Marvin S. Addison
 *
 * @since 3.0.0
 */
@Component("queryDatabaseAuthenticationHandler")
public class QueryDatabaseAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler {

    @NotNull
    private String sql;

    @Override
    protected final HandlerResult authenticateUsernamePasswordInternal(final UsernamePasswordCredential credential)
            throws GeneralSecurityException, PreventedException {

        if (credential.getPassword() == null && credential.getLinkedAccountToken() != null) {
            return authLinkedAccountWithInitialCredentials(credential);
        } else {
            return authInternalAndGetLinkedAccounts(credential);
        }
    }

    /**
     * authenticate username and password and get linked accounts.
     *
     * @param credential username password credential
     * @return results set
     */
    private HandlerResult authInternalAndGetLinkedAccounts(final UsernamePasswordCredential credential)
            throws GeneralSecurityException, PreventedException {
        if (StringUtils.isBlank(this.sql) || getJdbcTemplate() == null) {
            throw new GeneralSecurityException("Authentication handler is not configured correctly");
        }

        final String username = credential.getUsername();
        final String encryptedPassword = this.getPasswordEncoder().encode(credential.getPassword());
        try {
            final String dbPassword = getJdbcTemplate().queryForObject(this.sql, String.class, username);
            if (!dbPassword.equals(encryptedPassword)) {
                throw new FailedLoginException("Password does not match value on record.");
            }
        } catch (final IncorrectResultSizeDataAccessException e) {
            if (e.getActualSize() == 0) {
                throw new AccountNotFoundException(username + " not found with SQL query");
            } else {
                throw new FailedLoginException("Multiple records found for " + username);
            }
        } catch (final DataAccessException e) {
            throw new PreventedException("SQL exception while executing query for " + username, e);
        }

        final int one = 1;

        final List<LinkedAccount> linkedAccounts = new LinkedList<>();
        linkedAccounts.add(new DefaultLinkedAccount(one, "mydstuser1", credential));
        linkedAccounts.add(new DefaultLinkedAccount(one, "mydstuser2", credential));
        linkedAccounts.add(new DefaultLinkedAccount(one, "mydstuser3", credential));
        linkedAccounts.add(new DefaultLinkedAccount(one, "mydstuser4", credential));
        linkedAccounts.add(new DefaultLinkedAccount(one, "mydstuser5", credential));
        linkedAccounts.add(new DefaultLinkedAccount(one, "mydstuser6", credential));
        linkedAccounts.add(new DefaultLinkedAccount(one, "mydstuser7", credential));

        return createHandlerResult(credential, this.principalFactory.createPrincipal(username), null, linkedAccounts);
    }

    /**
     * authenticate linked account with initial credentials.
     *
     * @param credential credentials
     * @return results set
     */
    private HandlerResult authLinkedAccountWithInitialCredentials(final UsernamePasswordCredential credential)
            throws GeneralSecurityException, PreventedException {
        final String sql = "SELECT users.password from users LEFT JOIN linked_accounts ON "
                + "linked_accounts.user_name=users.user_name WHERE "
                + "linked_accounts.old_user_name=? AND users.user_name=?;";

        if (StringUtils.isBlank(sql) || getJdbcTemplate() == null) {
            throw new GeneralSecurityException("Authentication handler is not configured correctly");
        }

        final String username = credential.getUsername();
        final String encryptedPassword = this.getPasswordEncoder().encode(credential.getLinkedAccountToken().getPassword());
        try {
            final String dbPassword = getJdbcTemplate().queryForObject(sql, String.class, username,
                    credential.getLinkedAccountToken().getUsername());

            if (!dbPassword.equals(encryptedPassword)) {
                throw new FailedLoginException("Password does not match value on record.");
            }
        } catch (final IncorrectResultSizeDataAccessException e) {
            if (e.getActualSize() == 0) {
                throw new AccountNotFoundException(username + " not found with SQL query");
            } else {
                throw new FailedLoginException("Multiple records found for " + username);
            }
        } catch (final DataAccessException e) {
            throw new PreventedException("SQL exception while executing query for " + username, e);
        }

        return createHandlerResult(credential, this.principalFactory.createPrincipal(username), null);
    }

    /**
     * @param sql The sql to set.
     */
    @Autowired
    public void setSql(@Value("${cas.jdbc.authn.query.sql:}") final String sql) {
        this.sql = sql;
    }

    @Override
    @Autowired(required = false)
    public void setDataSource(@Qualifier("queryDatabaseDataSource") final DataSource dataSource) {
        super.setDataSource(dataSource);
    }
}
