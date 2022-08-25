package org.oobootcamp.warmup;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.oobootcamp.dto.Car;
import org.oobootcamp.dto.Ticket;
import org.oobootcamp.exception.ParkingLotAvailableException;
import org.oobootcamp.exception.TicketValidationException;

class ParkingLotManagerTest {


  private final ParkingLot parkingLot1 = new ParkingLot(1);
  private final ParkingLot parkingLot2 = new ParkingLot(1);
  private final GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLot2));
  private final ParkingLot parkingLot3 = new ParkingLot(1);
  private final SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLot3));
  private final ParkingLotManager parkingLotManager = new ParkingLotManager(List.of(parkingLot1),
      List.of(graduateParkingBoy, smartParkingBoy));

  @Test
  void should_park_to_2nd_parkingLot_when_parking_given_graduate_parking_boy_is_available() {
    Car car = new Car();

    Ticket ticket = parkingLotManager.parkCar(car);

    assertThat(car).isEqualTo(parkingLot2.pickUpCar(ticket));
  }

  @Test
  void should_park_to_3rd_parkingLot_when_parking_given_smart_parking_boy_is_available() {
    parkingLotManager.parkCar(new Car());
    Car car = new Car();

    Ticket ticket = parkingLotManager.parkCar(car);

    assertThat(car).isEqualTo(parkingLot3.pickUpCar(ticket));
  }

  @Test
  void should_park_to_1st_parkingLot_when_parking_given_manager_is_available_and_parking_boy_is_not_available() {
    parkingLotManager.parkCar(new Car());
    parkingLotManager.parkCar(new Car());
    Car car = new Car();

    Ticket ticket = parkingLotManager.parkCar(car);

    assertThat(car).isEqualTo(parkingLot1.pickUpCar(ticket));
  }

  @Test
  void should_park_failed_when_park_car_given_the_parkingLot_is_not_available() {
    parkingLotManager.parkCar(new Car());
    parkingLotManager.parkCar(new Car());
    parkingLotManager.parkCar(new Car());
    Car car = new Car();

    assertThrows(ParkingLotAvailableException.class, () -> parkingLotManager.parkCar(car));
  }

  @Test
  void should_pick_up_car_success_when_pick_up_car_given_ticket_is_valid() {
    Car car = new Car();
    Ticket ticket = parkingLotManager.parkCar(car);

    assertThat(car).isEqualTo(parkingLotManager.pickUpCar(ticket));
  }

  @Test
  void should_pick_up_car_failed_when_pick_car_given_ticket_is_fake() {
    Ticket ticket = new Ticket();

    assertThrows(TicketValidationException.class, () -> parkingLotManager.pickUpCar(ticket));
  }

  @Test
  void should_pick_up_car_failed_when_pick_up_car_given_ticket_is_used() {
    Ticket ticket = parkingLot1.parkCar(new Car());
    parkingLotManager.pickUpCar(ticket);
    parkingLot1.parkCar(new Car());

    assertThrows(TicketValidationException.class, () -> parkingLotManager.pickUpCar(ticket));
  }
}
