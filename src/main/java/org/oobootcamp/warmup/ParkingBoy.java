package org.oobootcamp.warmup;

import java.util.List;
import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.TicketValidationException;

public abstract class ParkingBoy {

  protected List<ParkingLot> parkingLots;

  public abstract Ticket parkCar(Car car);

  public Car pickUpCar(Ticket ticket) {
    return parkingLots.stream().filter(parkingLot -> parkingLot.hasCar(ticket)).findFirst()
        .orElseThrow(TicketValidationException::new).pickUpCar(ticket);
  }

  public boolean isAvailable() {
    return parkingLots.stream().anyMatch(ParkingLot::isAvailable);
  }

  public boolean hasCar(Ticket ticket) {
    return parkingLots.stream().anyMatch(parkingLot -> parkingLot.hasCar(ticket));
  }
}
