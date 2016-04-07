package io.neodoc.auth;

import io.neodoc.auth.integration.UsergridAuthProviderTest;
import io.neodoc.auth.integration.UsergridUserProviderTest;
import io.neodoc.auth.unit.UsergridAuthProviderConfigTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by vish on 24/11/2015.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        UsergridAuthProviderTest.class,
        UsergridUserProviderTest.class,
        UsergridAuthProviderConfigTest.class
})
public class AllTests {
}
