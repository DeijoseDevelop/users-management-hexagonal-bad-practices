package com.jcaa.usersmanagement.domain.exception;

public final class InvalidUserIdException extends DomainException {

  private InvalidUserIdException(final String message) {
    super(message);
  }

  private static final String EMPTY_MESSAGE = "The user id must not be empty.";

  public static InvalidUserIdException becauseValueIsEmpty() {
    return new InvalidUserIdException(EMPTY_MESSAGE);
  }
}
