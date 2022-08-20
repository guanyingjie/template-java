package org.oobootcamp.warmup;

import java.util.HashMap;
import java.util.Map;

import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;
import org.oobootcamp.exception.TicketValidationException;

public class ParkingLot {
  private final Map<Ticket, Car> parkedCars = new HashMap<>();

  public Integer getCapacity() {
    return capacity;
  }

  private Integer capacity;

  public Integer getParkingLogId() {
    return parkingLogId;
  }

  private Integer parkingLogId;

  public ParkingLot(Integer capacity, Integer parkingLogId) {
    this.capacity = capacity;
    this.parkingLogId = parkingLogId;
  }

  public Ticket parkCar(Car car) {
    if (capacity <= parkedCars.size()) {
      throw new ParkingLotAvailableException("停车位已满");
    }
    Ticket ticket = new Ticket(car.getLicensePlateNumber(),parkingLogId);

    parkedCars.put(ticket, car);
    --capacity;
    return ticket;
  }

  public Car pickUpCar(Ticket ticket) {

    if (!parkedCars.containsKey(ticket)) {
      throw new TicketValidationException("车票无效");
    }
    Car car = parkedCars.get(ticket);
    parkedCars.remove(ticket);
    ++capacity;
    return car;
  }
}
