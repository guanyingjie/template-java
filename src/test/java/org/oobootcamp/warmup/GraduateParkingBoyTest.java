package org.oobootcamp.warmup;

import org.junit.jupiter.api.Test;
import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;
import org.oobootcamp.exception.TicketValidationException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GraduateParkingBoyTest {

  private final ParkingLot parkingLotA = new ParkingLot(1);
  private final ParkingLot parkingLotB = new ParkingLot(2);
  private List<ParkingLot> parkingLots;

  @Test
  void should_park_to_first_parkingLot_success_when_park_given_the_first_parkingLot_is_available() {
    parkingLots = List.of(parkingLotA, parkingLotB);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
    Car car = new Car();

    Ticket ticket = graduateParkingBoy.parkCar(car);

    assertThat(parkingLotA.hasCar(ticket)).isTrue();
  }

  @Test
  void should_park_to_second_parkingLot_success_when_park_given_the_first_parkingLot_is_not_available() {
    parkingLots = List.of(parkingLotA, parkingLotB);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
    Car car0 = new Car();
    parkingLotA.parkCar(car0);
    Car car = new Car();

    Ticket ticket = graduateParkingBoy.parkCar(car);

    assertThat(parkingLotA.isAvailable()).isFalse();
    assertThat(parkingLotB.hasCar(ticket)).isTrue();
  }

  @Test
  void should_park_failed_when_park_given_the_parkingLot_is_not_available() {
    parkingLots = List.of(parkingLotA);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
    Car car0 = new Car();
    parkingLotA.parkCar(car0);

    Car car = new Car();

    assertThat(parkingLotA.isAvailable()).isFalse();
    assertThrows(ParkingLotAvailableException.class, () -> graduateParkingBoy.parkCar(car));
  }

  @Test
  void should_pick_up_car_success_when_pick_up_car_given_ticket_is_valid() {
    parkingLots = List.of(parkingLotA, parkingLotB);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
    Car car = new Car();
    Ticket ticket = graduateParkingBoy.parkCar(car);

    Car car0 = graduateParkingBoy.pickUpCar(ticket);

    assertThat(car0).isEqualTo(car);
  }

  @Test
  void should_pick_up_car_failed_when_pick_up_car_given_ticket_is_fake() {
    parkingLots = List.of(parkingLotA, parkingLotB);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
    Ticket ticket = new Ticket();

    assertThrows(TicketValidationException.class, () -> graduateParkingBoy.pickUpCar(ticket));
  }

  @Test
  void should_pick_up_car_failed_when_pick_up_car_given_ticket_is_used() {
    parkingLots = List.of(parkingLotA, parkingLotB);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
    Car car = new Car();
    Ticket ticket = graduateParkingBoy.parkCar(car);
    graduateParkingBoy.pickUpCar(ticket);
    graduateParkingBoy.parkCar(car);

    assertThrows(TicketValidationException.class, () -> graduateParkingBoy.pickUpCar(ticket));
  }
}