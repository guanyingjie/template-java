package org.oobootcamp.warmup;

import java.util.Map;
import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;
import org.oobootcamp.exception.TicketValidationException;

public record ParkingLot(Map<Ticket, Car> parkedCars, int capacity) {

  public Ticket parkCar(Car car) {
    if (capacity <= parkedCars.size()) {
      throw new ParkingLotAvailableException("停车位已满");
    }
    Ticket ticket = new Ticket(car.getNumber());

    parkedCars.put(ticket, car);
    return ticket;
  }

  public Car pickUpCar(Ticket ticket) {

    if (!parkedCars.containsKey(ticket)) {
      throw new TicketValidationException("车票无效");
    }
    Car car = parkedCars.get(ticket);
    parkedCars.remove(ticket);
    return car;
  }
}
