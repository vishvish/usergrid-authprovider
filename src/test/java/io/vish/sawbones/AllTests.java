package io.vish.sawbones;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by vish on 24/11/2015.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        UsergridAuthProviderTest.class,
        UsergridUserProviderTest.class
})
public class AllTests {
}
