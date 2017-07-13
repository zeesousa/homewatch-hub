package homewatch.things.services.motionsensors;

import com.fasterxml.jackson.annotation.JsonProperty;
import homewatch.things.Thing;
import homewatch.things.ThingServiceFactory;

public class MotionSensor implements Thing {
  @JsonProperty
  private final boolean movement;

  public MotionSensor() {
    this.movement = false;
  }

  public MotionSensor(boolean locked) {
    this.movement = locked;
  }

  public boolean hasMovement() {
    return movement;
  }

  @Override
  public ThingServiceFactory<MotionSensor> getFactory() {
    return new MotionSensorServiceFactory();
  }

  @Override
  public String getStringRepresentation() {
    return "motionsensors";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    MotionSensor motionsensor = (MotionSensor) o;

    return movement == motionsensor.movement;
  }

  @Override
  public int hashCode() {
    return (movement ? 1 : 0);
  }

  @Override
  public String toString() {
    return "MotionSensor{" +
        "movement=" + movement +
        '}';
  }
}