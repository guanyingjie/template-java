package org.oobootcamp.dto;

public record Car(String number) {

  public String getNumber() {
    return number;
  }

}
