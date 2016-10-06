package com.koothooloo.openfire.auth;

import org.apache.usergrid.java.client.Usergrid;
import org.apache.usergrid.java.client.auth.UsergridUserAuth;
import org.apache.usergrid.java.client.model.UsergridUser;
import org.apache.usergrid.java.client.query.UsergridQuery;
import org.apache.usergrid.java.client.response.UsergridResponse;
import org.jivesoftware.openfire.auth.AuthProvider;
import org.jivesoftware.openfire.auth.ConnectionException;
import org.jivesoftware.openfire.auth.InternalUnauthenticatedException;
import org.jivesoftware.openfire.auth.UnauthorizedException;
import org.jivesoftware.openfire.user.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        UsergridUserAuth userAuth = new UsergridUserAuth(username, password);
        UsergridResponse response = Usergrid.authenticateUser(userAuth);
        if (!response.ok()) throw new UnauthorizedException();
    }

    public void authenticate(String username, String token, String digest) throws UnauthorizedException, ConnectionException, InternalUnauthenticatedException {
        throw new UnsupportedOperationException("Usergrid provider does not support digests.");
    }

    @Override
    public String getPassword(String username) throws UserNotFoundException {
        UsergridQuery query = new UsergridQuery("users").eq("username", username);
        UsergridResponse response = Usergrid.GET(query);

        if (response.getEntities().size() != 1) throw new UserNotFoundException();
        if (!(response.first() instanceof UsergridUser)) throw new UserNotFoundException();

        UsergridUser user = (UsergridUser) response.first();
        return user.getPassword();
    }

    @Override
    public void setPassword(String username, String password) throws UserNotFoundException {
        UsergridQuery query = new UsergridQuery("users").eq("username", username);
        UsergridResponse response = Usergrid.GET(query);

        if (response.getEntities().size() != 1) throw new UserNotFoundException();
        if (!(response.first() instanceof UsergridUser)) throw new UserNotFoundException();

        UsergridUser user = (UsergridUser) response.first();

        user.setPassword(password);
    }

    @Override
    public boolean supportsPasswordRetrieval() {
        return true;
    }

    public boolean isPlainSupported() {
        return true;
    }

    public boolean isDigestSupported() {
        return false;
    }

    @Override
    public boolean isScramSupported() {
        return false;
    }
}
