package org.oobootcamp.warmup;

import java.util.List;
import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.TicketValidationException;

public abstract class PickUpCarBoy {

  List<ParkingLot> parkingLots;

  public Car pickUpCar(Ticket ticket) {
    for (ParkingLot parkingLot : parkingLots) {
      try {
        return parkingLot.pickUpCar(ticket);
      } catch (Exception ignored) {
      }
    }
    throw new TicketValidationException();
  }
}
