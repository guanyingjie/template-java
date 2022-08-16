package org.oobootcamp.warmup;

import org.oobootcamp.exception.ParkingLotAvailableException;
import org.oobootcamp.exception.TicketValidationException;

import java.util.ArrayList;

public class ParkingLotManagement {
  private final ArrayList<String> parkedCars;

  public ParkingLotManagement(ArrayList<String> parkedCars) {
    this.parkedCars = parkedCars;
  }

  public String parkingCar(String carNumber, boolean available) throws ParkingLotAvailableException {
    if (!available) {
      throw new ParkingLotAvailableException();
    }
    parkedCars.add(carNumber);
    return carNumber;
  }

  public String takingCar(String ticketNo) throws TicketValidationException {
    if (parkedCars.contains(ticketNo)) {
      parkedCars.remove(ticketNo);
      return ticketNo;
    }
    throw new TicketValidationException();
  }
}
