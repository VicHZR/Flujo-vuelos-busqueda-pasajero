# Reporte de Arquitectura y Revisión - time-booking-api

## 🔍 1. Análisis de Responsabilidades
* **¿Qué responsabilidades manejaba el sistema inicialmente?**
  El servicio de autenticación y lógica de negocio mezclaba detalles de infraestructura (como la generación directa de tokens JWT mediante `jjwt`) con las reglas de la aplicación.
* **¿Qué detalles técnicos conoce la capa Application?**
  Tras el refactor hexagonal, la capa de aplicación **ya no conoce** librerías externas de seguridad ni frameworks de persistencia; solo interactúa mediante puertos (`TokenIssuerPort`, `ReservationRepositoryPort`).
* **¿Qué se movió detrás de un Puerto?**
  La emisión y validación de tokens de seguridad (`TokenIssuerPort`).

---

## 📋 2. Registro Técnico del Proyecto

### 3 Problemas Encontrados:
1. **Acoplamiento de Excepciones:** `ResourceNotFoundException` residía inicialmente en la capa web, contaminando el flujo del núcleo.
2. **Dependencia Tecnológica Directa:** Los servicios de aplicación dependían de clases de infraestructura para la gestión de tokens.
3. **Falta de Atomicidad:** Las operaciones de escritura en `ReservationService` carecían de control transaccional explícito ante fallos en MySQL.

### 1 Refactor Realizado:
* **Desacoplamiento Hexagonal y Transaccionalidad:** Se reubicó la excepción al dominio, se implementó el puerto de salida `TokenIssuerPort` con su adaptador `JwtTokenAdapter`, y se añadieron anotaciones `@Transactional` para garantizar el rollback automático.

### 1 Oportunidad de Strategy / Factory Evaluada:
* Se analizó su aplicación para futuras variaciones en el cálculo de tarifas o tipos de notificaciones; sin embargo, se determinó mantener un flujo uniforme por simplicidad actual.

### 1 Decisión que Decidieron NO Aplicar y Por Qué:
* **No aplicar Patrón Strategy de inmediato:** Se decidió no sobreingenierizar el sistema con múltiples estrategias de reserva dado que el modelo de negocio actual maneja un flujo de dominio estándar, aplicando el principio **YAGNI** (*You Aren't Gonna Need It*).