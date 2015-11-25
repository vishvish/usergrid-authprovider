package io.vish.sawbones;

import java.util.Map;
import java.util.Optional;

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

    private static final String USERGRID_RESOURCE = "usergrid.resource";

    /**
     * Map of configuration properties.
     */
    private Map<String, String> configProperties;

    /**
     * Constructs instances of <code>UsergridAuthProviderConfig</code> with the map
     * of configuration properties used to configure the instance.
     *
     * @param configProperties The configuration properties for the instance.
     */
    UsergridAuthProviderConfig(final Map<String, String> configProperties) {
        if (configProperties == null) {
            throw new IllegalArgumentException(
                    "Configuration properties not defined");
        }
        this.configProperties = configProperties;
    }

    public String getUsergridBaseUrl() {
        Optional<String> value = Optional.ofNullable(configProperties.get(USERGRID_BASE_URL));
        if (value.isPresent()) {
            return value.get();
        } else {
            // FIXME: needs a trailing slash configured
            return "usergrid-1.tfto.net/";
        }
    }

    public String getUsergridOrganization() {
        Optional<String> value = Optional.ofNullable(configProperties.get(USERGRID_ORGANIZATION));
        if (value.isPresent()) {
            return value.get();
        } else {
            return "test-organization";
        }
    }

    public String getUsergridApplication() {
        Optional<String> value = Optional.ofNullable(configProperties.get(USERGRID_APPLICATION));
        if (value.isPresent()) {
            return value.get();
        } else {
            return "test-app";
        }
    }

    public String getUsergridResource() {
        Optional<String> value = Optional.ofNullable(configProperties.get(USERGRID_RESOURCE));
        if (value.isPresent()) {
            return value.get();
        } else {
            return "token";
        }
    }
}