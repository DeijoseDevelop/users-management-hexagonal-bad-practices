package com.jcaa.usersmanagement.domain.exception;

public final class InvalidUserNameException extends DomainException {

  private InvalidUserNameException(final String message) {
    super(message);
  }

  private static final String EMPTY_MESSAGE = "The user name must not be empty.";
  private static final String TOO_SHORT_MESSAGE = "The user name must have at least %d characters.";

  public static InvalidUserNameException becauseValueIsEmpty() {
    return new InvalidUserNameException(EMPTY_MESSAGE);
  }

  public static InvalidUserNameException becauseLengthIsTooShort(final int minimumLength) {
    return new InvalidUserNameException(String.format(TOO_SHORT_MESSAGE, minimumLength));
  }
}
