package com.koothooloo.openfire.auth;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.jivesoftware.util.JiveGlobals;

/**
 * Created by vish on 24/11/2015.
 * <p>
 * Provides access to the configuration required for creating
 * <code>UsergridAuthProviderConfig</code> instances.
 */
public class UsergridAuthProviderConfig {

    private Config config;

    private static final String USERGRID_HOST = "usergrid.host";

    private static final String USERGRID_PORT = "usergrid.port";

    private static final String USERGRID_ORGANIZATION = "usergrid.organization";

    private static final String USERGRID_APPLICATION = "usergrid.application";

    private static final String USERGRID_CLIENT_ID = "usergrid.credentials.id";

    private static final String USERGRID_CLIENT_SECRET = "usergrid.credentials.secret";

    private UsergridAuthProviderConfig(Config config) {
        this.config = config;
        config.checkValid(ConfigFactory.defaultReference(), "usergrid");
    }

    public UsergridAuthProviderConfig() {
        this(ConfigFactory.load());
    }

    public String getUsergridHost() {
        // FIXME: needs a trailing slash configured
        return JiveGlobals.getProperty(USERGRID_HOST, config.getString(USERGRID_HOST));
    }

    public String getUsergridPort() {
        return JiveGlobals.getProperty(USERGRID_PORT, config.getString(USERGRID_PORT));
    }

    public String getUsergridOrganization() {
        return JiveGlobals.getProperty(USERGRID_ORGANIZATION, this.config.getString(USERGRID_ORGANIZATION));
    }

    public String getUsergridApplication() {
        return JiveGlobals.getProperty(USERGRID_APPLICATION, this.config.getString(USERGRID_APPLICATION));
    }

    public String getUsergridClientId() {
        return JiveGlobals.getProperty(USERGRID_CLIENT_ID, this.config.getString(USERGRID_CLIENT_ID));
    }

    public String getUsergridClientSecret() {
        return JiveGlobals.getProperty(USERGRID_CLIENT_SECRET, this.config.getString(USERGRID_CLIENT_SECRET));
    }
}