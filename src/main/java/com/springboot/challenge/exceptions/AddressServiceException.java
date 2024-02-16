package com.springboot.challenge.exceptions;

import org.springframework.web.client.HttpStatusCodeException;

public class AddressServiceException extends RuntimeException {
  public AddressServiceException(String message) { super(message);
  }
}
