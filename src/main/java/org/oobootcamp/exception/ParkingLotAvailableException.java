package org.oobootcamp.exception;

public class ParkingLotAvailableException extends RuntimeException{
  public ParkingLotAvailableException(String message) {
    super(message);
  }
}
