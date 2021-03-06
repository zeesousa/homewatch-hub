package homewatch.things.services.lights;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import homewatch.exceptions.NetworkException;
import homewatch.stubs.LightStubs;
import homewatch.things.ThingService;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestRestLight {
  private static final int PORT = 8080;

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(options().port(PORT).bindAddress("0.0.0.0"));

  private static ThingService<Light> lightService;

  @BeforeClass
  public static void setup() throws UnknownHostException {
    lightService = new RestLightService(InetAddress.getLocalHost().getHostName(), PORT);
  }

  @Test
  public void getStatus() throws UnknownHostException, NetworkException {
    LightStubs.stubGetRest(wireMockRule, true);

    Light light = lightService.get();

    assertThat(light.isOn(), is(true));
    verify(getRequestedFor(urlPathEqualTo("/status")));
  }

  @Test
  public void getStatusOff() throws UnknownHostException, NetworkException {
    LightStubs.stubGetRest(wireMockRule, false);

    Light light = lightService.get();

    assertThat(light.isOn(), is(false));
    verify(getRequestedFor(urlPathEqualTo("/status")));
  }

  @Test
  public void setStatusOn() throws IOException, NetworkException {
    LightStubs.stubPutRest(wireMockRule, true);

    Light light = new Light(true);
    lightService.put(light);

    verify(putRequestedFor(urlPathEqualTo("/status")).withRequestBody(equalTo("{\"power\":true}")));
  }

  @Test
  public void setStatusOff() throws IOException, NetworkException, InterruptedException {
    LightStubs.stubPutRest(wireMockRule, false);

    Light light = new Light(false);
    lightService.put(light);

    verify(putRequestedFor(urlPathEqualTo("/status")).withRequestBody(equalTo("{\"power\":false}")));
  }

  @Test
  public void testGoodPing() {
    LightStubs.stubGetRest(wireMockRule, true);

    boolean ping = lightService.ping();

    assertTrue(ping);
  }

  @Test
  public void testBadPing() throws UnknownHostException {
    ThingService<Light> badLightService = new RestLightService(InetAddress.getLocalHost().getHostName());

    boolean ping = badLightService.ping();

    assertFalse(ping);
  }
}
