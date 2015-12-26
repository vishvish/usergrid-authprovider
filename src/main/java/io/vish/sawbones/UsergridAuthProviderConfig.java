package io.vish.sawbones;

import org.jivesoftware.util.JiveGlobals;

/**
 * Created by vish on 24/11/2015.
 * <p>
 * Provides access to the configuration required for creating
 * <code>UsergridAuthProviderConfig</code> instances.
 */
public class UsergridAuthProviderConfig {

    private static final String USERGRID_HOST = "usergrid.host";

    private static final String USERGRID_PORT = "usergrid.port";

    private static final String USERGRID_ORGANIZATION = "usergrid.organization";

    private static final String USERGRID_APPLICATION = "usergrid.application";

    private static final String USERGRID_CLIENT_ID = "usergrid.credentials.id";

    private static final String USERGRID_CLIENT_SECRET = "usergrid.credentials.secret";

    public String getUsergridHost() {
        // FIXME: needs a trailing slash configured
        return JiveGlobals.getProperty(USERGRID_HOST, "localhost");
    }

    public int getUsergridPort() {
        String port = JiveGlobals.getProperty(USERGRID_PORT, "9090");
        return Integer.parseInt(port);
    }

    public String getUsergridOrganization() {
        return JiveGlobals.getProperty(USERGRID_ORGANIZATION, "test-organization");
    }

    public String getUsergridApplication() {
        return JiveGlobals.getProperty(USERGRID_APPLICATION, "test-app");
    }

    public String getUsergridClientId() {
        return JiveGlobals.getProperty(USERGRID_CLIENT_ID, "b3U69hrpMJ81EeWuvYVsCfW7Bg");
    }

    public String getUsergridClientSecret() {
        return JiveGlobals.getProperty(USERGRID_CLIENT_SECRET, "b3U6l06iskVmRm-D4YUoMGvciRFoG50");
    }
}