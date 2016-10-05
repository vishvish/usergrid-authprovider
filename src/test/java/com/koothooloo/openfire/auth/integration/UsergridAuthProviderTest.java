package com.koothooloo.openfire.auth.integration;

import com.koothooloo.openfire.auth.UsergridAuthProvider;
import org.jivesoftware.openfire.auth.ConnectionException;
import org.jivesoftware.openfire.auth.InternalUnauthenticatedException;
import org.jivesoftware.openfire.auth.UnauthorizedException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by vish on 24/11/2015.
 */
public class UsergridAuthProviderTest {

    private UsergridAuthProvider uap;

    @Before
    public void setup() {
        uap = new UsergridAuthProvider();
    }

    @After
    public void teardown() {
        uap = null;
    }

    @Test
    public void testAuthenticate() throws ConnectionException, InternalUnauthenticatedException, UnauthorizedException {
        uap.authenticate("tester", "password");
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
