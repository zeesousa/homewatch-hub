package things.thermostat;

import exceptions.InvalidSubTypeException;
import things.HttpThingService;
import things.HttpThingServiceFactory;
import things.ThingService;

import java.net.InetAddress;


public class ThermostatServiceFactory implements HttpThingServiceFactory<Thermostat> {
  @Override
  public HttpThingService<Thermostat> create(InetAddress address, Integer port, String subtype) throws InvalidSubTypeException {
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
    String subTypeUpper = subtype.toUpperCase();
    try {
      SubType.valueOf(subTypeUpper);

      return true;
    } catch (IllegalArgumentException ex) {
      return false;
    }
  }
}

enum SubType {
  REST
}