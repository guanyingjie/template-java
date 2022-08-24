package org.oobootcamp.warmup;

import java.util.List;
import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.TicketValidationException;

public abstract class ParkingBoy {

  List<ParkingLot> parkingLots;

  public abstract Ticket parkCar(Car car);

  public Car pickUpCar(Ticket ticket) {
    for (ParkingLot parkingLot : parkingLots) {
      try {
        return parkingLot.pickUpCar(ticket);
      } catch (TicketValidationException ignored) {
        // do nothing
      }
    }
    throw new TicketValidationException();
  }
}
