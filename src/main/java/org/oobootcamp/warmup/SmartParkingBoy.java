package org.oobootcamp.warmup;

import java.util.Comparator;
import java.util.List;
import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;

public class SmartParkingBoy extends ParkingBoy {

  public SmartParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  public Ticket parkCar(Car car) {
    return parkingLots.stream()
        .max(Comparator.comparing(ParkingLot::getAvailableCapacity))
        .map(parkingLot -> parkingLot.parkCar(car))
        .orElseThrow(ParkingLotAvailableException::new);
  }
}
