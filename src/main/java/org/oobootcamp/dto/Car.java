package org.oobootcamp.dto;

public class Car {
  public Car(String licensePlateNumber) {
    this.licensePlateNumber = licensePlateNumber;
  }

  private final String licensePlateNumber;

  public String getLicensePlateNumber() {
    return licensePlateNumber;
  }

}