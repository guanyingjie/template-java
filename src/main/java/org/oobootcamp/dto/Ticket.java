package org.oobootcamp.dto;

public record Ticket(String number) {

  public String getNumber() {
    return number;
  }

}
