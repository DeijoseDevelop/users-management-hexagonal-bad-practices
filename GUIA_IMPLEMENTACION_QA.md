# Guía de Implementación: Refactorización y Mejora de Calidad (QA)

Esta guía detalla los pasos necesarios para corregir las "malas prácticas" identificadas en las capas de **Dominio** y **Aplicación** del proyecto de gestión de usuarios, basándose en los principios de **Arquitectura Hexagonal** y **Clean Code**.

---

## Fase 1: Refactorización del Dominio (`domain`)
El dominio es el núcleo del sistema y debe estar libre de dependencias externas y lógica técnica.

### Paso 1.1: Asegurar Inmutabilidad en Modelos
*   **Archivo:** `UserModel.java`
*   **Acción:**
    *   Cambiar las anotaciones `@Data` y `@AllArgsConstructor` por `@Value` de Lombok.
    *   Asegurar que todos los campos sean `final`.
    *   **Por qué:** Un modelo de dominio no debe exponer setters públicos; los cambios de estado deben realizarse a través de métodos con intención de negocio (ej: `activate()`).

### Paso 1.2: Eliminar Dependencias de Infraestructura
*   **Archivo:** `UserModel.java`
*   **Acción:** 
    *   Eliminar el import de `UserEntity`.
    *   Eliminar el método `toEntity()` o cualquier referencia a la capa de persistencia.
    *   **Por qué:** Las dependencias deben apuntar hacia el centro. El dominio no debe conocer los detalles de cómo se guarda en la base de datos.

### Paso 1.3: Limpieza de Value Objects
*   **Archivos:** `UserEmail.java`, `UserPassword.java`, `UserId.java`.
*   **Acciones:**
    *   Sustituir validaciones manuales `if (x == null)` por `Objects.requireNonNull(x, "message")` o `Objects.isNull()`.
    *   Eliminar cualquier log (`log.info`, etc.) de estas clases.
    *   Sustituir "Magic Numbers" por constantes descriptivas (ej: `MINIMUM_PASSWORD_LENGTH = 8`).
    *   **Por qué:** Los Value Objects deben ser puros y enfocarse únicamente en la validación de sus propios invariantes.

### Paso 1.4: Estandarización de Excepciones
*   **Directorio:** `domain/exception/`
*   **Acción:**
    *   Mover todos los textos de error "hardcodeados" a constantes `private static final String`.
    *   Asegurar que las excepciones de dominio no expongan constructores públicos si se prefiere el uso de métodos fábrica estáticos.

---

## Fase 2: Refactorización de la Aplicación (`application`)
La capa de aplicación debe orquestar el flujo sin mezclar niveles de abstracción.

### Paso 2.1: Aplicar CQS (Command Query Separation)
*   **Archivo:** `LoginService.java`, `UpdateUserService.java`.
*   **Acción:**
    *   Dividir métodos que realizan una consulta y modifican estado al mismo tiempo.
    *   Ejemplo: En `LoginService`, el método `getAndValidateUser` debe separarse en una consulta de búsqueda y una validación/comando posterior.

### Paso 2.2: Eliminar Parámetros Booleanos de Control
*   **Archivo:** `UpdateUserService.java`, `EmailNotificationService.java`.
*   **Acción:**
    *   Eliminar parámetros como `boolean notify`.
    *   Crear métodos separados: `updateAndNotify()` y `updateSilently()`.
    *   **Por qué:** Los booleanos de control indican que una función tiene más de una responsabilidad (viola SRP).

### Paso 2.3: Aplicar Ley de Deméter
*   **Acción:**
    *   Evitar encadenamientos como `user.getEmail().getValue()`.
    *   Implementar métodos de conveniencia en el modelo (ej: `user.getEmailAddress()`) para ocultar la estructura interna de los Value Objects.

### Paso 2.4: Simplificar Lógica de Servicios
*   **Archivo:** `CreateUserService.java`.
*   **Acción:**
    *   Extraer validaciones complejas a métodos privados con nombres que expliquen la intención (ej: `validateUserDoesNotExist`).
    *   Eliminar comentarios que explican "qué" hace el código; el nombre del método debe ser suficiente.

---

## Fase 3: Estandarización de DTOs y Puertos
### Paso 3.1: Limpieza de Lombok en Records
*   **Archivos:** `CreateUserCommand.java`, `GetUserByIdQuery.java`, etc.
*   **Acción:** 
    *   Eliminar `@Builder` si el record es simple y no lo requiere.
    *   Eliminar redundancias entre las capacidades nativas de los `record` de Java y las anotaciones de Lombok.

### Paso 3.2: Ubicación de Validaciones
*   **Acción:**
    *   Mover anotaciones `@Valid`, `@NotNull`, etc., desde las implementaciones de los servicios hacia las interfaces de los Puertos de Entrada (`application/port/in/`).
    *   **Por qué:** El contrato de validación pertenece a la interfaz, no a la implementación técnica.

---

## Checklist Final de Calidad
- [ ] ¿El dominio tiene CERO imports de `infrastructure`?
- [ ] ¿Se eliminaron todos los textos hardcodeados (mensajes de error)?
- [ ] ¿Se eliminaron los logs de la capa de dominio?
- [ ] ¿Todos los modelos son inmutables (`@Value`)?
- [ ] ¿Se eliminaron los parámetros booleanos que alteran el flujo de los métodos?
- [ ] ¿El código se lee de arriba hacia abajo sin necesidad de comentarios explicativos?
