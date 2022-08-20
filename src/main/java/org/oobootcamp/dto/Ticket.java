package org.oobootcamp.dto;

public class Ticket {
  private String ticketNo;
  private Integer parkingLotId;

  public Ticket(String ticketNo, Integer parkingLotId) {
    this.ticketNo = ticketNo;
    this.parkingLotId = parkingLotId;
  }

  public Integer getParkingLotId() {
    return parkingLotId;
  }

  public String getTicketNo() {
    return ticketNo;
  }

}