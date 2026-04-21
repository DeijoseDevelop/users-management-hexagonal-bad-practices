package com.jcaa.usersmanagement.domain.exception;

public final class UserNotFoundException extends DomainException {

  // VIOLACIÓN Regla 10: texto de error hardcodeado directamente en el método fábrica.
  // Debe usarse una constante con nombre descriptivo en lugar de un String literal.
  private static final String NOT_FOUND_BY_ID_MESSAGE = "The user with id '%s' was not found.";

  private UserNotFoundException(final String message) {
    super(message);
  }

  public static UserNotFoundException becauseIdWasNotFound(final String userId) {
    return new UserNotFoundException(String.format(NOT_FOUND_BY_ID_MESSAGE, userId));
  }
}
