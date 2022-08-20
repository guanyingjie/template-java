package org.oobootcamp.warmup;

import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;

import java.util.List;

public class GraduateParkingBoy extends IParkingBoy {

  public GraduateParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  public Ticket park(Car car) {
    return parkingLots.stream()
            .filter(parkingLot -> parkingLot.getCapacity() > 0)
            .findFirst()
            .map(parkingLot ->
                    parkingLot.parkCar(car))
            .orElseThrow(() -> new ParkingLotAvailableException("停车位已满"));
  }
}
