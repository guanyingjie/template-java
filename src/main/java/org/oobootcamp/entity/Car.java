package org.oobootcamp.entity;

public class Car {

  public String getNumber() {
    return number;
  }

  public Car(String number) {
    this.number = number;
  }

  private final String number;
}
