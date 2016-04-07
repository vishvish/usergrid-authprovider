package io.neodoc.auth;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.jivesoftware.openfire.auth.AuthProvider;
import org.jivesoftware.openfire.auth.ConnectionException;
import org.jivesoftware.openfire.auth.InternalUnauthenticatedException;
import org.jivesoftware.openfire.auth.UnauthorizedException;
import org.jivesoftware.openfire.user.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by vish on 24/11/2015.
 */
public class UsergridAuthProvider extends UsergridBase implements AuthProvider {
    private static final Logger LOG = LoggerFactory.getLogger(UsergridAuthProvider.class);

    public UsergridAuthProvider() {
        super("token");
    }

    @Override
    public void authenticate(String username, String password) throws UnauthorizedException, ConnectionException, InternalUnauthenticatedException {

        try {
            URL url = new URL("http", this.host, getEndpoint());
            HttpResponse<JsonNode> jsonResponse = Unirest.get(url.toString())
                    .header("accept", "application/json")
                    .queryString("grant_type", "password")
                    .queryString("username", username)
                    .queryString("password", password)
                    .asJson();

            int status = jsonResponse.getStatus();
            if (status == 200) {
                LOG.debug(url + " ");
                LOG.debug("Authentication succeeded.");
            } else {
                LOG.debug(url + " ");
                LOG.debug("Authentication failed. ");
                LOG.debug(jsonResponse.getBody().toString());
                throw new UnauthorizedException(jsonResponse.getBody().toString());
            }
        } catch (UnirestException | MalformedURLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void authenticate(String username, String token, String digest) throws UnauthorizedException, ConnectionException, InternalUnauthenticatedException {
        throw new UnsupportedOperationException("Usergrid provider does not support digests.");
    }

    @Override
    public String getPassword(String username) throws UserNotFoundException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPassword(String username, String password) throws UserNotFoundException {
        LOG.debug("setPassword");
        LOG.debug(username + " / " + password);
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean supportsPasswordRetrieval() {
        return false;
    }

    @Override
    public boolean isPlainSupported() {
        return true;
    }

    @Override
    public boolean isDigestSupported() {
        return false;
    }

    @Override
    public boolean isScramSupported() {
        return false;
    }
}
