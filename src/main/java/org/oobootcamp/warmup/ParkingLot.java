package org.oobootcamp.warmup;

import java.util.HashMap;
import java.util.Map;

import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;
import org.oobootcamp.exception.TicketValidationException;

public class ParkingLot {

  private final Map<Ticket, Car> parkedCars = new HashMap<>();
  private int capacity;

  public ParkingLot(int capacity) {
    this.capacity = capacity;
  }

  public int getCapacity(){return capacity;}

  public Ticket parkCar(Car car) {
    if (capacity < parkedCars.size()) {
      throw new ParkingLotAvailableException();
    }
    Ticket ticket = new Ticket();

    parkedCars.put(ticket, car);
    capacity--;
    return ticket;
  }

  public Car pickUpCar(Ticket ticket) {
    if (!parkedCars.containsKey(ticket)) {
      throw new TicketValidationException();
    }
    Car car = parkedCars.get(ticket);
    parkedCars.remove(ticket);
    capacity++;
    return car;
  }
}
