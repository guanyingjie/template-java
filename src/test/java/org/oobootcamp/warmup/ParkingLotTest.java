package org.oobootcamp.warmup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;
import org.oobootcamp.exception.TicketValidationException;

class ParkingLotTest {

  private ParkingLot parkingLot;

  @Test
  void should_parked_success_when_parking_car_given_the_parkingLot_is_available() {
    parkingLot = new ParkingLot(2, 1);
    Car newCar = new Car("京A12346");

    Ticket newTicket = parkingLot.parkCar(newCar);

    assertEquals("京A12346", newTicket.getTicketNo());
  }

  @Test
  void should_parked_failed_when_parking_car_given_the_parkingLot_is_not_available() {
    parkingLot = new ParkingLot(1, 1);
    Car car0 = new Car("京A12345");
    parkingLot.parkCar(car0);
    Car car1 = new Car("京A12346");

    assertThrows(ParkingLotAvailableException.class, () -> parkingLot.parkCar(car1), "停车位已满!");
  }

  @Test
  void should_take_car_success_when_taking_car_given_the_user_have_car_in_parkingLot() {
    parkingLot = new ParkingLot(2, 1);
    Car car = new Car("京A12345");
    Ticket ticket = parkingLot.parkCar(car);

    Car pickedCar = parkingLot.pickUpCar(ticket);

    assertEquals("京A12345", pickedCar.getLicensePlateNumber());
  }

  @Test
  void should_take_car_failed_when_taking_car_given_the_user_do_not_have_car_in_parkingLot() {
    parkingLot = new ParkingLot(2, 1);
    Ticket ticket = new Ticket("京A12345", 1);

    assertThrows(TicketValidationException.class, () -> parkingLot.pickUpCar(ticket), "车票无效");
  }

  @Test
  void should_pick_failed_when_pick_up_car_given_ticket_is_used() {
    parkingLot = new ParkingLot(2, 1);
    Car car = new Car("京A12345");
    Ticket oldTicket = parkingLot.parkCar(car);
    parkingLot.pickUpCar(oldTicket);

    assertThrows(TicketValidationException.class, () -> parkingLot.pickUpCar(oldTicket), "车票无效");
  }
}