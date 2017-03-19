package locks;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import exceptions.InvalidSubTypeException;
import exceptions.NetworkException;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import things.DiscoveryService;
import things.HttpThingService;
import things.lights.Light;
import things.lights.LightServiceFactory;
import things.locks.Lock;
import things.locks.LockServiceFactory;
import things.locks.RestLockService;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Ignore
public class TestDiscoverRestLock {
  @Rule
  public WireMockRule wireMockRule = new WireMockRule(options().port(8080).bindAddress("0.0.0.0"));

  @Test
  public void discoverRestLocks() throws UnknownHostException, NetworkException, InvalidSubTypeException {
    LockStubs.stubGetStatus(wireMockRule, true);

    List<HttpThingService<Lock>> restLockServiceList = new DiscoveryService<>(new LockServiceFactory(), "rest", 8080).discovery();
    assertThat(restLockServiceList.size(), is(1));
  }
}
