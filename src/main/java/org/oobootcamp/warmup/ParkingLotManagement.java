package org.oobootcamp.warmup;

import org.oobootcamp.exception.ParkingLotAvailableException;
import org.oobootcamp.exception.TicketValidationException;

import java.util.List;

public class ParkingLotManagement {
  private final List<String> parkedCars;

  public ParkingLotManagement(List<String> parkedCars) {
    this.parkedCars = parkedCars;
  }

  public String parkCar(String carNumber, boolean available) throws ParkingLotAvailableException {
    if (!available) {
      throw new ParkingLotAvailableException("停车位已满");
    }
    parkedCars.add(carNumber);
    return carNumber;
  }

  public String pickUpCar(String ticketNo) throws TicketValidationException {
    if (!parkedCars.contains(ticketNo)) {
      throw new TicketValidationException("车票无效");
    }
    parkedCars.remove(ticketNo);
    return ticketNo;
  }
}
