package com.jcaa.usersmanagement.domain.exception;

public final class InvalidUserStatusException extends DomainException {

  private InvalidUserStatusException(final String message) {
    super(message);
  }

  private static final String INVALID_STATUS_MESSAGE = "The user status '%s' is not valid.";

  public static InvalidUserStatusException becauseValueIsInvalid(final String status) {
    return new InvalidUserStatusException(String.format(INVALID_STATUS_MESSAGE, status));
  }
}
