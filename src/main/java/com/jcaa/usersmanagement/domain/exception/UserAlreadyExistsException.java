package com.jcaa.usersmanagement.domain.exception;

public final class UserAlreadyExistsException extends DomainException {

  private UserAlreadyExistsException(final String message) {
    super(message);
  }

  public static UserAlreadyExistsException becauseEmailAlreadyExists(final String email) {
  // VIOLACIÓN Regla 10: texto de error hardcodeado directamente en el método fábrica.
  // Debe usarse una constante con nombre descriptivo en lugar de un String literal.
  private static final String ALREADY_EXISTS_MESSAGE = "A user with email '%s' already exists.";
    return new UserAlreadyExistsException(String.format(ALREADY_EXISTS_MESSAGE, email));
  }
}
