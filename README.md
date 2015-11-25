# UsergridAuthProvider

This is a custom authentication provider for Openfire that talks to a Usergrid API to get authentication information. There is also a custom user provider which enables Openfire to lookup Users that do not exist in its database yet, and pull them from Usergrid.

In order to add this to Openfire, build the artifact JAR and drop into the Openfire lib directory, along with the other dependency jars as illustrated in this screenshot - the files marked in red should be added.

![image](jars.png)

Openfire will need to be configured to use the auth provider as follows. Open the System properties page in the admin console and set up the auth properties as in the image:

![image](properties.png)

The following properties may be added to configure the location of the Usergrid endpoint:

    private static final String USERGRID_BASE_URL = "usergrid.host";

    private static final String USERGRID_ORGANIZATION = "usergrid.organization";

    private static final String USERGRID_APPLICATION = "usergrid.application";

    private static final String USERGRID_CLIENT_ID = "usergrid.credentials.id";

    private static final String USERGRID_CLIENT_SECRET = "usergrid.credentials.secret";

The properties have been added by default into our [fork of Openfire](https://github.com/vishvish/openfire), specifically into the `src/conf/openfire.xml` file. This is an easier way to configure the Openfire properties, and the snippet is reproduced below:

      <hybridAuthProvider>
        <primaryProvider>
          <className>
            io.vish.sawbones.UsergridAuthProvider
          </className>
        </primaryProvider>
        <secondaryProvider>
          <className>
            org.jivesoftware.openfire.auth.DefaultAuthProvider
          </className>
        </secondaryProvider>
      </hybridAuthProvider>
    
      <provider>
        <user>
          <className>org.jivesoftware.openfire.user.HybridUserProvider</className>
        </user>
        <auth>
          <className>org.jivesoftware.openfire.auth.HybridAuthProvider</className>
        </auth>
      </provider>
    
      <hybridUserProvider>
        <primaryProvider>
          <className>io.vish.sawbones.UsergridUserProvider</className>
        </primaryProvider>
        <secondaryProvider>
          <className>org.jivesoftware.openfire.user.DefaultUserProvider</className>
        </secondaryProvider>
      </hybridUserProvider>

