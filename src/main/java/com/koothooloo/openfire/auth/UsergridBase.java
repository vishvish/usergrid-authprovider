package com.koothooloo.openfire.auth;

import org.apache.usergrid.java.client.Usergrid;
import org.apache.usergrid.java.client.UsergridClient;
import org.apache.usergrid.java.client.UsergridEnums;
import org.apache.usergrid.java.client.auth.UsergridAppAuth;

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

    private static UsergridClient client;

    UsergridBase(String resource) {
        this.config = new UsergridAuthProviderConfig();
        this.host = config.getUsergridHost();
        this.port = Integer.parseInt(config.getUsergridPort());
        this.org = config.getUsergridOrganization();
        this.app = config.getUsergridApplication();
        this.resource = resource;

        if (client == null) {
            String id = config.getUsergridClientId();
            String secret = config.getUsergridClientSecret();
            client = Usergrid.initSharedInstance(org, app, host, UsergridEnums.UsergridAuthMode.APP);
            client.setAppAuth(new UsergridAppAuth(id, secret));
            UsergridAppAuth auth = new UsergridAppAuth();
            client.authenticateApp();
        }
    }

    String getEndpoint() {
        return Paths.get("/", org, app, resource).toString();
    }
}
