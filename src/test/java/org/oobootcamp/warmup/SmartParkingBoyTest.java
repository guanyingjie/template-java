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
  private List<ParkingLot> parkingLots;
  @Test
  void should_park_success_when_park_given_the_parkingLot_is_available() {
    parkingLots = List.of(new ParkingLot(1));
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
    Car car = new Car("京A12345");

    Ticket ticket = smartParkingBoy.parkCar(car);

    assertThat(ticket).isNotNull();
    assertThat(parkingLots.get(0).getCapacity()).isZero();
  }

  @Test
  void should_park_to_the_max_remain_capacity_parkingLot_when_park_car_given_multiple_parkingLot() {
    parkingLots = List.of(new ParkingLot(1), new ParkingLot(2));
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
    Car car = new Car("京A12345");

    Ticket ticket = smartParkingBoy.parkCar(car);

    assertThat(ticket).isNotNull();
    assertThat(parkingLots.get(1).getCapacity()).isEqualTo(1);
  }

  @Test
  void should_park_to_the_first_empty_parkingLot_when_park_car_given_multiple_parkingLot_and_remain_capacity_are_equal() {
    parkingLots = List.of(new ParkingLot(1), new ParkingLot(1));
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
    Car car = new Car("京A12345");

    Ticket ticket = smartParkingBoy.parkCar(car);

    assertThat(ticket).isNotNull();
    assertThat(parkingLots.get(0).getCapacity()).isZero();
  }

  @Test
  void should_park_failed_when_park_car_given_the_parkingLot_is_not_available() {
    parkingLots = List.of(new ParkingLot(1));
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
    Car car0 = new Car("京A12344");
    parkingLots.get(0).parkCar(car0);

    Car car = new Car("京A12345");

    assertThrows(ParkingLotAvailableException.class, () -> smartParkingBoy.parkCar(car));
  }

  @Test
  void should_pick_up_car_success_when_pick_up_car_given_ticket_is_valid() {
    parkingLots = List.of(new ParkingLot(1));
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
    Car car = new Car("京A12345");
    Ticket ticket = smartParkingBoy.parkCar(car);

    Car car0 = smartParkingBoy.pickUpCar(ticket);

    assertThat(car0).isEqualTo(car);
  }

  @Test
  void should_pick_car_failed_when_pick_car_given_ticket_is_invalid() {
    parkingLots = List.of(new ParkingLot(1));
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
    Car car = new Car("京A12345");

    smartParkingBoy.parkCar(car);

    Ticket ticket = new Ticket();
    assertThrows(TicketValidationException.class, () -> smartParkingBoy.pickUpCar(ticket));
  }
}