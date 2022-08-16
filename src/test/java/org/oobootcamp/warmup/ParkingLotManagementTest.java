package org.oobootcamp.warmup;

import org.junit.jupiter.api.Test;
import org.oobootcamp.exception.ParkingLotAvailableException;
import org.oobootcamp.exception.TicketValidationException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingLotManagementTest {
  ArrayList<String> parkedCars = new ArrayList<String>(Arrays.asList("京A12345", "京A12346"));

  private final ParkingLotManagement parkingLotManagement = new ParkingLotManagement(parkedCars);

  @Test
  void should_parked_success_when_parking_car_given_the_parkingLot_is_available() throws ParkingLotAvailableException {
    String tickerNo = parkingLotManagement.parkingCar("京A12345", true);

    assertEquals("京A12345", tickerNo);
  }

  @Test
  void should_parked_failed_when_parking_car_given_the_parkingLot_is_not_available() {

    assertThrows(ParkingLotAvailableException.class, () -> parkingLotManagement.parkingCar("京A12345", false));
  }

  @Test
  void should_take_car_success_when_taking_car_given_the_user_have_car_in_parkingLot() throws TicketValidationException {

    String carNumber = parkingLotManagement.takingCar("京A12345");

    assertEquals("京A12345", carNumber);
  }

  @Test
  void should_take_car_failed_when_taking_car_given_the_user_do_not_have_car_in_parkingLot() {

    assertThrows(TicketValidationException.class, () -> parkingLotManagement.takingCar("京A1239"));
  }
}