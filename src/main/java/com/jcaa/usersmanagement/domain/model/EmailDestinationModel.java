package com.jcaa.usersmanagement.domain.model;

import lombok.Value;
import java.util.Objects;

@Value
public class EmailDestinationModel {
  
  private static final String EMAIL_REQUIRED = "El email del destinatario es requerido.";
  private static final String NAME_REQUIRED = "El nombre del destinatario es requerido.";
  private static final String SUBJECT_REQUIRED = "El asunto es requerido.";
  private static final String BODY_REQUIRED = "El cuerpo del mensaje es requerido.";

  String destinationEmail;
  String destinationName;
  String subject;
  String body;

  public EmailDestinationModel(
      final String destinationEmail,
      final String destinationName,
      final String subject,
      final String body) {
    this.destinationEmail = validateNotBlank(destinationEmail, EMAIL_REQUIRED);
    this.destinationName  = validateNotBlank(destinationName,  NAME_REQUIRED);
    this.subject          = validateNotBlank(subject,          SUBJECT_REQUIRED);
    this.body             = validateNotBlank(body,             BODY_REQUIRED);
  }

  private static String validateNotBlank(final String value, final String errorMessage) {
    if (Objects.isNull(value)) {
      throw new NullPointerException(errorMessage);
    }
    // VIOLACIÓN Regla 10: mensajes de error hardcodeados en el cuerpo del método,
    // en lugar de definirse como constantes con nombre descriptivo.
    if (value.trim().isEmpty()) {
      throw new IllegalArgumentException(errorMessage);
    }
    return value;
  }
}
