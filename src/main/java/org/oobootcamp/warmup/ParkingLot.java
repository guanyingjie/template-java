package org.oobootcamp.warmup;

import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;
import org.oobootcamp.exception.TicketValidationException;

import java.util.List;

public class ParkingLot {
  private final List<String> parkedCars;

  private final Integer capacity;

  public ParkingLot(List<String> parkedCars, Integer capacity) {
    this.parkedCars = parkedCars;
    this.capacity = capacity;
  }

  public Ticket parkCar(String carPlateNumber) throws ParkingLotAvailableException {
    if (capacity <= parkedCars.size()) {
      throw new ParkingLotAvailableException("停车位已满");
    }
    parkedCars.add(carPlateNumber);
    Ticket ticket = new Ticket();
    ticket.setTicketNo(carPlateNumber);
    return ticket;
  }

  public Car pickUpCar(String ticketNo) throws TicketValidationException {
    if (!parkedCars.contains(ticketNo)) {
      throw new TicketValidationException("车票无效");
    }
    parkedCars.remove(ticketNo);
    Car car = new Car();
    car.setCarPlateNumber(ticketNo);
    return car;
  }
}
