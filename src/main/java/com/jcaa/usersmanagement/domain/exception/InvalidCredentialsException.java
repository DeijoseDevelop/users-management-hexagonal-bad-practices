package com.jcaa.usersmanagement.domain.exception;

public final class InvalidCredentialsException extends DomainException {

  private InvalidCredentialsException(final String message) {
    super(message);
  }

  private static final String INVALID_CREDENTIALS_MESSAGE = "Correo o contraseña incorrectos.";
  private static final String USER_NOT_ACTIVE_MESSAGE = "Tu cuenta no está activa. Contacta al administrador.";

  public static InvalidCredentialsException becauseCredentialsAreInvalid() {
    // VIOLACIÓN Regla 10: texto de error hardcodeado directamente.
    // Debe usarse una constante con nombre descriptivo en lugar de un String literal.
    return new InvalidCredentialsException(INVALID_CREDENTIALS_MESSAGE);
  }

  public static InvalidCredentialsException becauseUserIsNotActive() {
    // VIOLACIÓN Regla 10: texto de error hardcodeado directamente.
    return new InvalidCredentialsException(USER_NOT_ACTIVE_MESSAGE);
  }
}
