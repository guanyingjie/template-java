package org.oobootcamp.dto;

import java.util.UUID;

public class Ticket {

  private String ticketNumber;

  public Ticket() {
    ticketNumber = UUID.randomUUID().toString();
  }

}
