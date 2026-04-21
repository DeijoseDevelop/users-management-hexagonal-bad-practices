package com.jcaa.usersmanagement.domain.exception;

public final class UserAlreadyExistsException extends DomainException {

  private UserAlreadyExistsException(final String message) {
    super(message);
  }

  private static final String ALREADY_EXISTS_MESSAGE = "A user with email '%s' already exists.";

  public static UserAlreadyExistsException becauseEmailAlreadyExists(final String email) {
    return new UserAlreadyExistsException(String.format(ALREADY_EXISTS_MESSAGE, email));
  }
}
