package com.koothooloo.openfire.auth.integration;

import com.koothooloo.openfire.auth.UsergridAuthProvider;
import org.jivesoftware.openfire.auth.ConnectionException;
import org.jivesoftware.openfire.auth.InternalUnauthenticatedException;
import org.jivesoftware.openfire.auth.UnauthorizedException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by vish on 24/11/2015.
 */
public class UsergridAuthProviderTest {
    @Test
    public void testAuthenticate() throws ConnectionException, InternalUnauthenticatedException, UnauthorizedException {
        UsergridAuthProvider uap = new UsergridAuthProvider();
        try {
            uap.authenticate("tester", "tester");
        } catch (UnauthorizedException | ConnectionException | InternalUnauthenticatedException e) {
            e.printStackTrace();
        }
    }

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAuthenticateFailure() throws ConnectionException, InternalUnauthenticatedException, UnauthorizedException {
        UsergridAuthProvider uap = new UsergridAuthProvider();
        thrown.expect(UnauthorizedException.class);
        uap.authenticate("vishvish", "rubbish");
    }
}
