package org.oobootcamp.warmup;

import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.TicketValidationException;

import java.util.List;
import java.util.Objects;

public abstract class IParkingBoy {
  List<ParkingLot> parkingLots;

  public Car pickUp(Ticket ticket){
    return parkingLots.stream()
            .filter(parkingLot -> Objects.equals(parkingLot.getParkingLogId(), ticket.getParkingLotId()))
            .findFirst()
            .map(parkingLot -> parkingLot.pickUpCar(ticket))
            .orElseThrow(() -> new TicketValidationException("该车票不属于本停车场"));

  }
}
