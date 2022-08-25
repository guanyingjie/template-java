package org.oobootcamp.warmup;


import java.util.List;
import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;
import org.oobootcamp.exception.TicketValidationException;

public class ParkingLotManager {

  private final List<ParkingLot> parkingLots;
  private final List<ParkingBoy> parkingBoys;

  public ParkingLotManager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoys) {
    this.parkingLots = parkingLots;
    this.parkingBoys = parkingBoys;
  }


  public Ticket parkCar(Car car) {
    return parkingBoys.stream().filter(ParkingBoy::isAvailable).findFirst()
        .map(parkingBoy -> parkingBoy.parkCar(car))
        .orElseGet(() -> parkingLots.stream().filter(ParkingLot::isAvailable).findFirst()
            .map(parkingLot -> parkingLot.parkCar(car))
            .orElseThrow(ParkingLotAvailableException::new));
  }

  public Car pickUpCar(Ticket ticket) {
    return parkingBoys.stream().filter(parkingBoy -> parkingBoy.hasCar(ticket)).findFirst()
        .map(parkingBoy -> parkingBoy.pickUpCar(ticket))
        .orElseGet(() -> parkingLots.stream().filter(parkingLot -> parkingLot.hasCar(ticket)).findFirst()
            .map(parkingLot -> parkingLot.pickUpCar(ticket))
            .orElseThrow(TicketValidationException::new));
  }
}
