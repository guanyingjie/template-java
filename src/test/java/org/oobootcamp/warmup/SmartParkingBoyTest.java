package org.oobootcamp.warmup;

import org.junit.jupiter.api.Test;
import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;
import org.oobootcamp.exception.TicketValidationException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SmartParkingBoyTest {

  private final ParkingLot parkingLotA = new ParkingLot(1);
  private final ParkingLot parkingLotB = new ParkingLot(2);
  private final ParkingLot parkingLotC = new ParkingLot(1);
  private List<ParkingLot> parkingLots;

  @Test
  void should_park_success_when_park_given_the_parkingLot_is_available() {
    parkingLots = List.of(parkingLotB, parkingLotA);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
    Car car = new Car();

    Ticket ticket = smartParkingBoy.parkCar(car);

    assertThat(parkingLotB.hasCar(ticket)).isTrue();
  }

  @Test
  void should_park_to_the_max_remain_capacity_parkingLot_when_park_car_given_multiple_parkingLot() {
    parkingLots = List.of(parkingLotA, parkingLotB);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
    Car car = new Car();

    Ticket ticket = smartParkingBoy.parkCar(car);

    assertThat(parkingLotB.hasCar(ticket)).isTrue();
  }

  @Test
  void should_park_to_the_first_empty_parkingLot_when_park_car_given_multiple_parkingLot_and_remain_capacity_are_equal() {
    parkingLots = List.of(parkingLotA, parkingLotC);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
    Car car = new Car();

    Ticket ticket = smartParkingBoy.parkCar(car);

    assertThat(parkingLotA.hasCar(ticket)).isTrue();
  }

  @Test
  void should_park_failed_when_park_car_given_the_parkingLot_is_not_available() {
    parkingLots = List.of(parkingLotA);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
    Car car0 = new Car();
    parkingLotA.parkCar(car0);

    Car car = new Car();

    assertThrows(ParkingLotAvailableException.class, () -> smartParkingBoy.parkCar(car));
  }

  @Test
  void should_pick_up_car_success_when_pick_up_car_given_ticket_is_valid() {
    parkingLots = List.of(parkingLotA);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
    Car car = new Car();
    Ticket ticket = smartParkingBoy.parkCar(car);

    Car car0 = smartParkingBoy.pickUpCar(ticket);

    assertThat(car0).isEqualTo(car);
  }

  @Test
  void should_pick_up_car_failed_when_pick_car_given_ticket_is_fake() {
    parkingLots = List.of(parkingLotA);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
    Car car = new Car();

    smartParkingBoy.parkCar(car);

    Ticket ticket = new Ticket();
    assertThrows(TicketValidationException.class, () -> smartParkingBoy.pickUpCar(ticket));
  }

  @Test
  void should_pick_up_car_failed_when_pick_up_car_given_ticket_is_used() {
    parkingLots = List.of(parkingLotA);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
    Car car = new Car();
    Ticket ticket = smartParkingBoy.parkCar(car);
    smartParkingBoy.pickUpCar(ticket);
    smartParkingBoy.parkCar(car);

    assertThrows(TicketValidationException.class, () -> smartParkingBoy.pickUpCar(ticket));
  }
}