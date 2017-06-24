package homewatch.things;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

public abstract class HttpThingService<T> extends NetworkThingService<T> {
  public HttpThingService() {
  }

  public HttpThingService(InetAddress address) {
    super(address);
  }

  public HttpThingService(InetAddress address, Integer port) {
    super(address, port);
  }

  protected String getUrl() {
    if (this.getPort() == null)
      return String.format("http://%s", this.getAddress().getHostAddress());
    else
      return String.format("http://%s:%d", this.getAddress().getHostAddress(), this.getPort());
  }
}
