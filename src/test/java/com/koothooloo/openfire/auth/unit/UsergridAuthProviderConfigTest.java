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
        Assert.assertEquals("Host should be 10.1.1.161", "10.1.1.161", config.getUsergridHost());
    }

    @Test
    public void testPort() {
        Assert.assertEquals("Port should be 8080", "8080", config.getUsergridPort());
    }

    @Test
    public void testOrganization() {
        Assert.assertEquals("Organization should be test-organization", "0c475022-4fd4-11e6-a16d-080027163942", config.getUsergridOrganization());
    }

    @Test
    public void testApplication() {
        Assert.assertEquals("Applications should be test-app", "0caf39f5-4fd4-11e6-a16d-080027163942", config.getUsergridApplication());
    }

    @Test
    public void testClientId() {
        Assert.assertEquals("Client ID should be b3U6icZL6fIUEeWJBwgAJwUSfA", "b3U6DEdQIk_UEeahbQgAJxY5Qg", config.getUsergridClientId());
    }

    @Test
    public void testOrganizations() {
        Assert.assertEquals("Client secret should be b3U6I3I3fkB2MgAg26bwkIfIGsaETEc", "b3U6fZOpcVnBJNyUGs6HeBupdDrYDnM", config.getUsergridClientSecret());
    }
}
