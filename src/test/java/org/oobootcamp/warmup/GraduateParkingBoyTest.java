package org.oobootcamp.warmup;

import org.junit.jupiter.api.Test;
import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;
import org.oobootcamp.exception.TicketValidationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraduateParkingBoyTest {
  @Test
  void should_park_success_when_park_given_parkingLot_has_empty_space() {
    List<ParkingLot> parkingLots = List.of(
            new ParkingLot(2, 1));
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
    Car car = new Car("京A12345");

    Ticket ticket = graduateParkingBoy.park(car);

    assertEquals(car.getLicensePlateNumber(), ticket.getTicketNo());
    assertEquals(1, parkingLots.get(0).getCapacity());
  }

  @Test
  void should_park_to_second_parkingLot_success_when_park_given_the_first_parkingLot_is_full() {
    List<ParkingLot> parkingLots = List.of(
            new ParkingLot(1, 1),
            new ParkingLot(1, 1)
    );
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
    Car car0 = new Car("京A12344");
    parkingLots.get(0).parkCar(car0);

    Car car = new Car("京A12345");

    Ticket ticket = graduateParkingBoy.park(car);

    assertEquals(car.getLicensePlateNumber(), ticket.getTicketNo());
    assertEquals(0, parkingLots.get(1).getCapacity());
  }

  @Test
  void should_park_failed_when_park_given_the_parkingLot_is_full() {
    List<ParkingLot> parkingLots = List.of(
            new ParkingLot(1, 1)
    );
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
    Car car0 = new Car("京A12344");
    parkingLots.get(0).parkCar(car0);

    Car car = new Car("京A12345");

    assertThrows(ParkingLotAvailableException.class, () -> graduateParkingBoy.park(car));
  }

  @Test
  void should_pick_car_success_when_pick_car_given_ticket_is_valid() {
    List<ParkingLot> parkingLots = List.of(
            new ParkingLot(1, 1)
    );
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
    Car car = new Car("京A12345");
    Ticket ticket = graduateParkingBoy.park(car);

    Car car1 = graduateParkingBoy.pickUp(ticket);

    assertEquals(car.getLicensePlateNumber(), car1.getLicensePlateNumber());
  }

  @Test
  void should_pick_car_failed_when_pick_car_given_the_ticket_can_not_find_a_car() {
    List<ParkingLot> parkingLots = List.of(
            new ParkingLot(1, 1)
    );
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
    Ticket ticket = new Ticket("京A12345", 1);

    assertThrows(TicketValidationException.class, () -> graduateParkingBoy.pickUp(ticket));
  }
}