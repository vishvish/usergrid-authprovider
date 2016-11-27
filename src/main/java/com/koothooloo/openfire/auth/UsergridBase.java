package com.koothooloo.openfire.auth;

import org.apache.usergrid.java.client.Usergrid;
import org.apache.usergrid.java.client.UsergridClient;
import org.apache.usergrid.java.client.UsergridEnums;
import org.apache.usergrid.java.client.auth.UsergridAppAuth;
import org.apache.usergrid.java.client.auth.UsergridAuth;

import java.nio.file.Paths;

/**
 * Created by vish on 25/11/2015.
 */
class UsergridBase {
    final UsergridAuthProviderConfig config;

    final String host;
    final int port;
    protected final String org;
    private final String app;
    private final String resource;
    private final String id;
    private final String secret;
    private final String token;

    private static UsergridClient client;

    UsergridBase(String resource) {
        this.config = new UsergridAuthProviderConfig();
        this.host = config.getUsergridHost();
        this.port = Integer.parseInt(config.getUsergridPort());
        this.org = config.getUsergridOrganization();
        this.app = config.getUsergridApplication();
        this.resource = resource;
        this.id = config.getUsergridClientId();
        this.secret = config.getUsergridClientSecret();
        this.token = config.getUsergridToken();

        if (client == null) {
            client = Usergrid.initSharedInstance(org, app, host);
            client.usingAuth(new UsergridAuth(token));
            client.authenticateApp();
        }
    }

    String getEndpoint() {
        return Paths.get("/", org, app, resource).toString();
    }
}
