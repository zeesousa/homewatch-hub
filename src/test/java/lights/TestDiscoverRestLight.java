package lights;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import exceptions.InvalidSubTypeException;
import exceptions.NetworkException;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import things.DiscoveryService;
import things.HttpThingService;
import things.ThingService;
import things.lights.Light;
import things.lights.LightServiceFactory;
import things.lights.RestLightService;

import java.net.UnknownHostException;
import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Ignore
public class TestDiscoverRestLight {
  @Rule
  public WireMockRule wireMockRule = new WireMockRule(options().port(8080).bindAddress("0.0.0.0"));

  @Test
  public void discoverRestLights() throws UnknownHostException, NetworkException, InvalidSubTypeException {
    LightStubs.stubGetStatus(wireMockRule, true);

    List<HttpThingService<Light>> restLightServiceList = new DiscoveryService<>(new LightServiceFactory(), "rest", 8080).discovery();
    assertThat(restLightServiceList.size(), is(1));
  }
}
