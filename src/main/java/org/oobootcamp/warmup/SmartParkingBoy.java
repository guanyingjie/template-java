package org.oobootcamp.warmup;

import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

  public SmartParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  public Ticket parkCar(Car car) {
    return parkingLots.stream()
            .max(Comparator.comparing(ParkingLot::getEmptySpace))
            .map(parkingLot -> parkingLot.parkCar(car))
            .orElseThrow(ParkingLotAvailableException::new);
  }
}
