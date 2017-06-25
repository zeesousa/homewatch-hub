package homewatch.things.thermostat;

import homewatch.exceptions.InvalidSubTypeException;
import homewatch.things.NetworkThingService;
import homewatch.things.NetworkThingServiceFactory;
import homewatch.things.ThingService;

import java.net.InetAddress;


public class ThermostatServiceFactory implements NetworkThingServiceFactory<Thermostat> {
  @Override
  public NetworkThingService<Thermostat> create(InetAddress address, Integer port, String subtype) throws InvalidSubTypeException {
    switch (subtype) {
      case "rest":
        return new RestThermostatService(address, port);
      default:
        throw new InvalidSubTypeException();
    }
  }

  @Override
  public ThingService<Thermostat> create(String subtype) throws InvalidSubTypeException {
    switch (subtype) {
      case "rest":
        return new RestThermostatService();
      default:
        throw new InvalidSubTypeException();
    }
  }

  @Override
  public boolean isSubType(String subtype) {
    String subtypeUpper = subtype.toUpperCase();
    try {
      SubType.valueOf(subtypeUpper);

      return true;
    } catch (IllegalArgumentException ex) {
      return false;
    }
  }
}

enum SubType {
  REST
}