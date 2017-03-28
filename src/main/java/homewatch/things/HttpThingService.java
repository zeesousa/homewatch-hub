package homewatch.things;

import java.net.InetAddress;

public abstract class HttpThingService<T> implements ThingService<T> {
  private InetAddress ipAddress;
  private Integer port = null;

  protected HttpThingService() {
  }

  protected HttpThingService(InetAddress ipAddress) {
    this.ipAddress = ipAddress;
  }

  protected HttpThingService(InetAddress ipAddress, Integer port) {
    this.ipAddress = ipAddress;
    this.port = port;
  }

  public InetAddress getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(InetAddress ipAddress) {
    this.ipAddress = ipAddress;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  protected String getUrl() {
    if (port == null)
      return String.format("http://%s", this.ipAddress.getHostAddress());
    else
      return String.format("http://%s:%d", this.ipAddress.getHostAddress(), this.port);
  }
}