package org.oobootcamp.warmup;

import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;

import java.util.List;

public class GraduateParkingBoy extends ParkingBoy {

  public GraduateParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  public Ticket parkCar(Car car) {
    return parkingLots.stream()
            .filter(ParkingLot::isAvailable)
            .findFirst()
            .map(parkingLot -> parkingLot.parkCar(car))
            .orElseThrow(ParkingLotAvailableException::new);
  }
}
