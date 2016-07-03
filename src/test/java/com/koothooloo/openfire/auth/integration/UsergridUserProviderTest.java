package com.koothooloo.openfire.auth.integration;

import com.koothooloo.openfire.auth.UsergridUserProvider;
import org.jivesoftware.openfire.user.User;
import org.jivesoftware.openfire.user.UserNotFoundException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collection;

/**
 * Created by vish on 25/11/2015.
 */
public class UsergridUserProviderTest {
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testLoadRealUser() throws UserNotFoundException {
        UsergridUserProvider uup = new UsergridUserProvider();
        User user = uup.loadUser("tester");
        Assert.assertEquals("Email should be tester@example.com", "tester@example.com", user.getEmail());
    }

    @Test
    public void testLoadUnknownUser() throws UserNotFoundException {
        UsergridUserProvider uup = new UsergridUserProvider();
        thrown.expect(UserNotFoundException.class);
        uup.loadUser("nonexistentuser");
    }

    @Test
    public void testGetUsernames() {
        UsergridUserProvider uup = new UsergridUserProvider();
        Collection<String> usernames = uup.getUsernames();
        Assert.assertTrue("There should be at least 1 username", usernames.size() > 0);
    }
}
