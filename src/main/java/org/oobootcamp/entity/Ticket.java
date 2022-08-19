package org.oobootcamp.entity;

public record Ticket(String number) {

  public String getNumber() {
    return number;
  }

}
