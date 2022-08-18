package org.oobootcamp.entity;

public class Ticket {

  public String getNumber() {
    return number;
  }

  public Ticket(String number) {
    this.number = number;
  }

  private final String number;
}
