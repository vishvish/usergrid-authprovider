package com.koothooloo.openfire.auth;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.jivesoftware.util.JiveGlobals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by vish on 24/11/2015.
 * <p>
 * Provides access to the configuration required for creating
 * <code>UsergridAuthProviderConfig</code> instances.
 */
public class UsergridAuthProviderConfig {

    private static final Logger LOG = LoggerFactory.getLogger(UsergridAuthProviderConfig.class);

    private Config config;

    private static final String USERGRID_HOST = "usergrid.host";

    private static final String USERGRID_PORT = "usergrid.port";

    private static final String USERGRID_ORGANIZATION = "usergrid.organization";

    private static final String USERGRID_APPLICATION = "usergrid.application";

    private static final String USERGRID_CLIENT_ID = "usergrid.credentials.id";

    private static final String USERGRID_CLIENT_SECRET = "usergrid.credentials.secret";

    private static final String USERGRID_TOKEN = "usergrid.credentials.token";


    private UsergridAuthProviderConfig(Config config) {
        this.config = config;
        config.checkValid(ConfigFactory.defaultReference(), "usergrid");
    }

    public UsergridAuthProviderConfig() {
        this(ConfigFactory.load());
    }

    public String getUsergridHost() {
        LOG.debug("getUsergridHost: ", config.getString(USERGRID_HOST));
        // FIXME: needs a trailing slash configured
        return JiveGlobals.getProperty(USERGRID_HOST, config.getString(USERGRID_HOST));
    }

    public String getUsergridPort() {
        LOG.debug("getUsergridPort: ", config.getString(USERGRID_PORT));

        return JiveGlobals.getProperty(USERGRID_PORT, config.getString(USERGRID_PORT));
    }

    public String getUsergridOrganization() {
        LOG.debug("getUsergridOrganization: ", config.getString(USERGRID_ORGANIZATION));

        return JiveGlobals.getProperty(USERGRID_ORGANIZATION, this.config.getString(USERGRID_ORGANIZATION));
    }

    public String getUsergridApplication() {
        LOG.debug("getUsergridApplication: ", config.getString(USERGRID_APPLICATION));

        return JiveGlobals.getProperty(USERGRID_APPLICATION, this.config.getString(USERGRID_APPLICATION));
    }

    public String getUsergridClientId() {
        LOG.debug("getUsergridClientId: ", config.getString(USERGRID_CLIENT_ID));

        return JiveGlobals.getProperty(USERGRID_CLIENT_ID, this.config.getString(USERGRID_CLIENT_ID));
    }

    public String getUsergridClientSecret() {
        LOG.debug("getUsergridClientSecret: ", config.getString(USERGRID_CLIENT_SECRET));

        return JiveGlobals.getProperty(USERGRID_CLIENT_SECRET, this.config.getString(USERGRID_CLIENT_SECRET));
    }

    public String getUsergridToken() {
        LOG.debug("getUsergridToken: ", config.getString(USERGRID_TOKEN));

        return JiveGlobals.getProperty(USERGRID_TOKEN, this.config.getString(USERGRID_TOKEN));
    }
}