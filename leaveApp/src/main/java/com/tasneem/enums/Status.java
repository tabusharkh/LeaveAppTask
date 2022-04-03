package com.tasneem.enums;

public enum Status {

  NEW(0),
  CANCELED(1),
  APPROVED(2),
  REJECTED(3);

  private int value;
  private Status(int value) {
    this.value = value;
  }
}
