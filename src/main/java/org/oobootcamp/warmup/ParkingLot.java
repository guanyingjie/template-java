package org.oobootcamp.warmup;

import java.util.HashMap;
import java.util.Map;

import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;
import org.oobootcamp.exception.TicketValidationException;

public class ParkingLot {

  private final Map<Ticket, Car> parkedCars = new HashMap<>();
  private final int capacity;

  public ParkingLot(int capacity) {
    this.capacity = capacity;
  }

  public Ticket parkCar(Car car) {
    if (!isAvailable()) {
      throw new ParkingLotAvailableException();
    }
    Ticket ticket = new Ticket();

    parkedCars.put(ticket, car);
    return ticket;
  }

  public Car pickUpCar(Ticket ticket) {
    if (!hasCar(ticket)) {
      throw new TicketValidationException();
    }

    return parkedCars.remove(ticket);
  }

  public boolean isAvailable() {
    return capacity > parkedCars.size();
  }

  public boolean hasCar(Ticket ticket) {
    return parkedCars.containsKey(ticket);
  }

  public Integer getEmptySpace() {
    return capacity - parkedCars.size();
  }
}
