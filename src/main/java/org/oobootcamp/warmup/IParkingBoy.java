package org.oobootcamp.warmup;

import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;

public interface IParkingBoy {
  Ticket park(Car car);

  Car pickUp(Ticket ticket);
}
