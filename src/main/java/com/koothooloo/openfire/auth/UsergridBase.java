package com.koothooloo.openfire.auth;

import java.nio.file.Paths;

/**
 * Created by vish on 25/11/2015.
 */
public class UsergridBase {
    protected final UsergridAuthProviderConfig config;

    protected final String host;
    protected final int port;
    protected final String org;
    protected final String app;
    protected final String resource;


    public UsergridBase(String resource) {
        this.config = new UsergridAuthProviderConfig();
        this.host = config.getUsergridHost();
        this.port = Integer.parseInt(config.getUsergridPort());
        this.org = config.getUsergridOrganization();
        this.app = config.getUsergridApplication();
        this.resource = resource;
    }

    protected String getEndpoint() {
        return Paths.get("/", org, app, resource).toString();
    }
}
