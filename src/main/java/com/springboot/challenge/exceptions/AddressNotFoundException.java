package com.springboot.challenge.exceptions;

public class AddressNotFoundException extends RuntimeException {
  public AddressNotFoundException(String cep) {
    super(String.format("Address with cep %s not found", cep));
  }
}
