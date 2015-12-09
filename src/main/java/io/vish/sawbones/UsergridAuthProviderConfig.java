package io.vish.sawbones;

import org.jivesoftware.util.JiveGlobals;

/**
 * Created by vish on 24/11/2015.
 * <p>
 * Provides access to the configuration required for creating
 * <code>UsergridAuthProviderConfig</code> instances.
 */
public class UsergridAuthProviderConfig {

    private static final String USERGRID_BASE_URL = "usergrid.host";

    private static final String USERGRID_ORGANIZATION = "usergrid.organization";

    private static final String USERGRID_APPLICATION = "usergrid.application";

    private static final String USERGRID_CLIENT_ID = "usergrid.credentials.id";

    private static final String USERGRID_CLIENT_SECRET = "usergrid.credentials.secret";

    public String getUsergridBaseUrl() {
        // FIXME: needs a trailing slash configured
        return JiveGlobals.getProperty(USERGRID_BASE_URL, "usergrid-1.tfto.net/");
    }

    public String getUsergridOrganization() {
        return JiveGlobals.getProperty(USERGRID_ORGANIZATION, "test-organization");
    }

    public String getUsergridApplication() {
        return JiveGlobals.getProperty(USERGRID_APPLICATION, "test-app");
    }

    public String getUsergridClientId() {
        return JiveGlobals.getProperty(USERGRID_CLIENT_ID, "b3U6NlBMqmD-EeWZ_Glhm0KmBg");
    }

    public String getUsergridClientSecret() {
        return JiveGlobals.getProperty(USERGRID_CLIENT_SECRET, "b3U6w0O0U0Mzlvx5PK4yvOfzvLiyjC0");
    }
}