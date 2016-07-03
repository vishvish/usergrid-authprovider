package com.koothooloo.openfire.auth;

import com.koothooloo.openfire.auth.integration.UsergridAuthProviderTest;
import com.koothooloo.openfire.auth.integration.UsergridUserProviderTest;
import com.koothooloo.openfire.auth.unit.UsergridAuthProviderConfigTest;
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
