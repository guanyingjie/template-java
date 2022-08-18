package org.oobootcamp.warmup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.oobootcamp.entity.Car;
import org.oobootcamp.entity.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;
import org.oobootcamp.exception.TicketValidationException;

class ParkingLotTest {

  private final Map<Ticket, Car> parkedCars = new HashMap<>();

  private ParkingLot parkingLot;

  @Test
  void should_parked_success_when_parking_car_given_the_parkingLot_is_available() {
    Car car = new Car("京A12345");
    Ticket ticket = new Ticket("京A12345");
    parkedCars.put(ticket, car);
    parkingLot = new ParkingLot(parkedCars, 2);

    Car newCar = new Car("京A12346");

    Ticket newTicket = parkingLot.parkCar(newCar);

    assertEquals("京A12346", newTicket.getNumber());
  }

  @Test
  void should_parked_failed_when_parking_car_given_the_parkingLot_is_not_available() {
    Car car0 = new Car("京A12345");
    Car car1 = new Car("京A12345");
    Ticket ticket0 = new Ticket("京A12346");
    Ticket ticket1 = new Ticket("京A12346");
    parkedCars.put(ticket0, car0);
    parkedCars.put(ticket1, car1);
    parkingLot = new ParkingLot(parkedCars, 2);

    Car car = new Car("京A12347");

    assertThrows(ParkingLotAvailableException.class, () -> parkingLot.parkCar(car), "停车位已满!");
  }

  @Test
  void should_take_car_success_when_taking_car_given_the_user_have_car_in_parkingLot() {
    Car car = new Car("京A12345");
    Ticket ticket = new Ticket("京A12345");
    parkedCars.put(ticket, car);
    parkingLot = new ParkingLot(parkedCars, 2);

    Car car0 = parkingLot.pickUpCar(ticket);

    assertEquals("京A12345", car0.getNumber());
  }

  @Test
  void should_take_car_failed_when_taking_car_given_the_user_do_not_have_car_in_parkingLot() {
    parkingLot = new ParkingLot(parkedCars, 2);
    Ticket ticket = new Ticket("京A12345");

    assertThrows(TicketValidationException.class, () -> parkingLot.pickUpCar(ticket), "车票无效");
  }
}