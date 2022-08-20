package org.oobootcamp.warmup;

import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;
import org.oobootcamp.exception.TicketValidationException;

import java.util.List;
import java.util.Objects;

public class GraduateParkingBoy implements IParkingBoy {
  private final List<ParkingLot> parkingLots;

  public GraduateParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  @Override
  public Ticket park(Car car) {
    return parkingLots.stream()
            .filter(parkingLot -> parkingLot.getCapacity() > 0)
            .findFirst()
            .map(parkingLot ->
                    parkingLot.parkCar(car))
            .orElseThrow(() -> new ParkingLotAvailableException("停车位已满"));
  }

  @Override
  public Car pickUp(Ticket ticket) {
    return parkingLots.stream()
            .filter(parkingLot -> Objects.equals(parkingLot.getParkingLogId(), ticket.getParkingLotId()))
            .findFirst()
            .map(parkingLot -> parkingLot.pickUpCar(ticket))
            .orElseThrow(() -> new TicketValidationException("该车票不属于本停车场"));
  }
}
