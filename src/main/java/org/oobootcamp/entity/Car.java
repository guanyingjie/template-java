package org.oobootcamp.entity;

public record Car(String number) {

  public String getNumber() {
    return number;
  }

}
