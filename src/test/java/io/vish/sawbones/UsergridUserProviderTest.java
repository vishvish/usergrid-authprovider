package io.vish.sawbones;

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
        User user = uup.loadUser("vish");
        Assert.assertEquals("Email should be vish@vish.io", "vish@vish.io", user.getEmail());
    }

    @Test
    public void testLoadUnknownUser() throws UserNotFoundException {
        UsergridUserProvider uup = new UsergridUserProvider();
        thrown.expect(UserNotFoundException.class);
        uup.loadUser("blahblahblah");
    }

    @Test
    public void testGetUsernames() {
        UsergridUserProvider uup = new UsergridUserProvider();
        Collection<String> usernames = uup.getUsernames();
        Assert.assertTrue("There should be at least 1 username", usernames.size() > 0);
    }
}
