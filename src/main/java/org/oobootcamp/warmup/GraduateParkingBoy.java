package org.oobootcamp.warmup;

import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;

import java.util.List;

public class GraduateParkingBoy extends PickUpCarBoy {

  public GraduateParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  public Ticket parkCar(Car car) {
    return parkingLots.stream()
            .filter(parkingLot -> parkingLot.getCapacity() > 0)
            .findFirst()
            .map(parkingLot -> parkingLot.parkCar(car))
            .orElseThrow(ParkingLotAvailableException::new);
  }
}
