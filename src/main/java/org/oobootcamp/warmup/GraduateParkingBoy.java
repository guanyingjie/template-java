package org.oobootcamp.warmup;

import java.util.List;
import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;

public class GraduateParkingBoy extends ParkingBoy {

  public GraduateParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  public Ticket parkCar(Car car) {
    return parkingLots.stream()
        .filter(ParkingLot::isAvailable)
        .findFirst().orElseThrow(ParkingLotAvailableException::new).parkCar(car);
  }
}
