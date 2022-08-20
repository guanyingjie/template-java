package org.oobootcamp.warmup;

import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;

import java.util.List;

public class GraduateParkingBoy implements IParkingBoy{
  private final List<ParkingLot> parkingLots;

  public GraduateParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  @Override
  public Ticket park(Car car) {
    return null;
  }

  @Override
  public Car pickUp(Ticket ticket) {
    return null;
  }
}
