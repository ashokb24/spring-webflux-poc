package com.ashok.springwebflux.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {
  private String message;

  public EmployeeNotFoundException(String message) {
    super(message);
    this.message = message;
  }

  public EmployeeNotFoundException() {
    super();
  }
}
