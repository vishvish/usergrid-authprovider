package com.koothooloo.openfire.auth;

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


    UsergridBase(String resource) {
        this.config = new UsergridAuthProviderConfig();
        this.host = config.getUsergridHost();
        this.port = Integer.parseInt(config.getUsergridPort());
        this.org = config.getUsergridOrganization();
        this.app = config.getUsergridApplication();
        this.resource = resource;
    }

    String getEndpoint() {
        return Paths.get("/", org, app, resource).toString();
    }
}
