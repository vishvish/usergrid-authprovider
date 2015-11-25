package io.vish.sawbones;

import org.jivesoftware.util.JiveProperties;

import java.nio.file.Paths;

/**
 * Created by vish on 25/11/2015.
 */
public class UsergridBase {
    protected UsergridAuthProviderConfig config;

    protected String host;
    protected String org;
    protected String app;
    protected String resource;


    public UsergridBase(String resource) {
        JiveProperties jiveProperties = JiveProperties.getInstance();
        this.config = new UsergridAuthProviderConfig(jiveProperties);
        this.host = config.getUsergridBaseUrl();
        this.org = config.getUsergridOrganization();
        this.app = config.getUsergridApplication();
        this.resource = resource;
    }

    protected String getEndpoint() {
        return Paths.get(org, app, resource).toString();
    }
}