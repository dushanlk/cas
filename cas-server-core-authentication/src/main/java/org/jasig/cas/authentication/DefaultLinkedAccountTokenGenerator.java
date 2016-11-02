package org.jasig.cas.authentication;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author dushanp@hsenidmobile.com on 10/27/16
 * @since 4.0.0
 */
public class DefaultLinkedAccountTokenGenerator implements LinkedAccountTokenGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultLinkedAccountTokenGenerator.class);

    private static final String SECRET_KEY = "E25799E65@!18c08";

    private static final String TRANSFORM = "AES/CBC/PKCS5Padding";

    private static final SecretKeySpec KEY = new SecretKeySpec(getUTF8Bytes(SECRET_KEY), "AES");

    private static final IvParameterSpec IV = new IvParameterSpec(getUTF8Bytes(SECRET_KEY));

    @Override
    public String create(final LinkedAccountToken token) {
        try {
            final Cipher cipher = Cipher.getInstance(TRANSFORM);
            cipher.init(Cipher.ENCRYPT_MODE, KEY, IV);

            final byte[] encrypted = cipher.doFinal(token.toString().getBytes());
            return Base64.encodeBase64String(encrypted);

        } catch (final Exception ex) {
            LOGGER.error("Error occurred while creating initial auth token for [{}]", token);
        }

        return null;
    }

    @Override
    public LinkedAccountToken read(final String token) {
        try {
            final Cipher cipher = Cipher.getInstance(TRANSFORM);
            cipher.init(Cipher.DECRYPT_MODE, KEY, IV);

            final byte[] original = cipher.doFinal(Base64.decodeBase64(token));
            final String plainText = new String(original);
            final String[] dividedPlainText = plainText.split("\\|");

            final int numOfTokenParts = 4;
            final int indexUsername = 0;
            final int indexPassword = 1;
            final int indexLinkedUsername = 2;
            final int indexTokenCreatedTime = 3;

            if (dividedPlainText.length == numOfTokenParts) {
                return new DefaultLinkedAccountToken(
                        dividedPlainText[indexUsername],
                        dividedPlainText[indexPassword],
                        dividedPlainText[indexLinkedUsername],
                        new Long(dividedPlainText[indexTokenCreatedTime]));
            }

        } catch (final Exception ex) {
            LOGGER.error("Error occurred while extracting token [{}]", token);
        }

        return null;
    }

    private static byte[] getUTF8Bytes(final String input) {
        return input.getBytes(StandardCharsets.UTF_8);
    }
}
