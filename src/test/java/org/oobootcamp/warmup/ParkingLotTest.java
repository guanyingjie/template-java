package org.oobootcamp.warmup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;
import org.oobootcamp.exception.TicketValidationException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingLotTest {
  private final List<String> parkedCars = new ArrayList<>();

  private ParkingLot parkingLot;

  @BeforeEach
  void setUp() {
    parkedCars.add("京A12345");
    parkedCars.add("京A12346");
  }

  @Test
  void should_parked_success_when_parking_car_given_the_parkingLot_has_space() throws ParkingLotAvailableException {
    parkingLot = new ParkingLot(parkedCars, 3);

    Ticket ticket = parkingLot.parkCar("京A12345");

    assertEquals("京A12345", ticket.getTicketNo());
  }

  @Test
  void should_parked_failed_when_parking_car_given_the_parkingLot_not_have_space() {
    parkingLot = new ParkingLot(parkedCars, 2);

    assertThrows(ParkingLotAvailableException.class, () -> parkingLot.parkCar("京A12345"));
  }

  @Test
  void should_take_car_success_when_taking_car_given_the_user_have_car_in_parkingLot() throws TicketValidationException {
    parkingLot = new ParkingLot(parkedCars, 2);

    Car car = parkingLot.pickUpCar("京A12345");

    assertEquals("京A12345", car.getCarPlateNumber());
  }

  @Test
  void should_take_car_failed_when_taking_car_given_the_user_do_not_have_car_in_parkingLot() {
    parkingLot = new ParkingLot(parkedCars, 2);

    assertThrows(TicketValidationException.class, () -> parkingLot.pickUpCar("京A1239"));
  }
}