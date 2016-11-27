package com.koothooloo.openfire.auth.unit;

import com.koothooloo.openfire.auth.UsergridAuthProviderConfig;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by vish on 07/04/2016.
 */
public class UsergridAuthProviderConfigTest {
    public static UsergridAuthProviderConfig config;

    @BeforeClass
    public static void setUpClass() {
        config = new UsergridAuthProviderConfig();
    }

    @Test
    public void testHost() {
//        Assert.assertEquals("Host should be 10.1.1.161", "10.1.1.161", config.getUsergridHost());
    }

    @Test
    public void testPort() {
//        Assert.assertEquals("Port should be 8080", "8080", config.getUsergridPort());
    }

    @Test
    public void testOrganization() {
        Assert.assertEquals("Organization should be 'org'", "org", config.getUsergridOrganization());
    }

    @Test
    public void testApplication() {
        Assert.assertEquals("Applications should be 'app'", "app", config.getUsergridApplication());
    }

    @Test
    public void testClientId() {
//        Assert.assertEquals("Client ID should be b3U6icZL6fIUEeWJBwgAJwUSfA", "b3U6DEdQIk_UEeahbQgAJxY5Qg", config.getUsergridClientId());
    }

    @Test
    public void testOrganizations() {
//        Assert.assertEquals("Client secret should be b3U6I3I3fkB2MgAg26bwkIfIGsaETEc", "b3U6fZOpcVnBJNyUGs6HeBupdDrYDnM", config.getUsergridClientSecret());
    }
}
