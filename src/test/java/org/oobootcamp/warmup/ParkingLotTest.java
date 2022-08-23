package org.oobootcamp.warmup;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;
import org.oobootcamp.exception.TicketValidationException;

class ParkingLotTest {

  private ParkingLot parkingLot;

  @Test
  void should_parked_success_when_park_car_given_the_parkingLot_is_available() {
    parkingLot = new ParkingLot(2);
    Car car = new Car();

    Ticket ticket = parkingLot.parkCar(car);
    assertThat(parkingLot.isParkedCar(ticket)).isTrue();
  }

  @Test
  void should_parked_failed_when_park_car_given_the_parkingLot_is_not_available() {
    parkingLot = new ParkingLot(1);
    Car car0 = new Car();
    parkingLot.parkCar(car0);

    Car car = new Car();

    assertThrows(ParkingLotAvailableException.class, () -> parkingLot.parkCar(car));
  }

  @Test
  void should_pick_up_car_success_when_pick_up_car_given_ticket_is_valid() {
    parkingLot = new ParkingLot(2);
    Car car = new Car();
    Ticket ticket = parkingLot.parkCar(car);

    Car car0 = parkingLot.pickUpCar(ticket);

    assertThat(car0).isEqualTo(car);
  }

  @Test
  void should_pick_up_car_failed_when_pick_up_car_given_ticket_is_invalid() {
    parkingLot = new ParkingLot(2);
    Ticket ticket = new Ticket();

    assertThrows(TicketValidationException.class, () -> parkingLot.pickUpCar(ticket));
  }

  @Test
  void should_pick_up_car_failed_when_pick_up_car_given_ticket_is_used() {
    parkingLot = new ParkingLot(2);
    Car car = new Car();
    Ticket ticket = parkingLot.parkCar(car);
    Car car0 = parkingLot.pickUpCar(ticket);
    parkingLot.parkCar(car);

    assertThat(car0).isEqualTo(car);
    assertThrows(TicketValidationException.class, () -> parkingLot.pickUpCar(ticket));
  }
}